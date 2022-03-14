package com.aiv.skywatch.Units;

import com.aiv.skywatch.SpaceObject;
import com.aiv.skywatch.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.OrthographicCamera;


//Framework for unit creation
public class Player extends SpaceObject {
    private Texture image;
    //Character's momentum??
    private float characterX;
    private float characterY;
    
    private float x;
    private float y;

    private float rotation = 1;
    private float velocity = 0;

    public float acceleration = 2f;
    public float deceleration = 10f;

    private Sprite sprite;
    private Batch batch;
    private BitmapFont font;
    private Vector2 vec;
    private Game game;
    
    public Player(String dir){
        //Create a new player object
        try {
            this.image = new Texture(dir);
        } catch (RuntimeException e ) {
            this.image = new Texture("triangle2.png");
        } 

        game = new Game();
        //Font for drawing text on screen
        font = new BitmapFont();
        batch = new SpriteBatch();
        //Sprite for the given texture
        sprite = new Sprite(image, 64, 64);

        sprite.setRotation(rotation);
        vec = new Vector2(0, 0);
        sprite.translate(game.getWidth() / 2, game.getHeight() / 2);
    }

    public Texture getCharacter(){ return image;  }
    public Sprite getSprite(){ move(); return sprite; }
    public float getX() { return this.sprite.getX(); }
    public float getY() { return this.sprite.getY(); }
    public float getAcceleration(){
        return acceleration;
    }

    public void draw(){
        batch.begin();
        this.move();
        sprite.draw(batch);
        font.draw(batch,"Rotation Axis: " + String.valueOf(rotation), 10, 710);
        font.draw(batch,"Speed: " + String.valueOf(velocity), 10, 690);
        font.draw(batch,"x: " + sprite.getX() + " y: " + sprite.getY(), 10, 675);
        batch.end();
    }

    /* Receive input from keyboard and move/render object using
    spritebatch*/
    @Override
    public void move(){
        //Rotate Right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(rotation < 1){
                rotation = 360;
            }
            sprite.setRotation((float) (rotation-=3));
        }
        //Rotate Left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(rotation >= 360){
                rotation = 1;
            }
            sprite.setRotation((float) (rotation+=3));
        }



        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            velocity += 0.1f;
        } else if (velocity < 0) {
            velocity = 0;
        } else if ( velocity != 0){
            velocity -= 0.04;
        }

        if (velocity > 5){
            velocity = 5;
        }
        characterX = (float) (velocity * Math.cos(Math.toRadians(rotation)));
        characterY =  (float) (velocity * Math.sin(Math.toRadians(rotation)));
        sprite.translate(characterX, characterY);

        //Move Down?
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            velocity-=1f;
            if (velocity < 0f){
                velocity = 0;
            }
        }
        
    }
}
