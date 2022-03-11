package com.aiv.skywatch.Units;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Bitmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

//Framework for unit creation
public class Character extends SpaceObject {
    private Texture image;
    //Character position in (x, y)
    private float characterX;
    private float characterY;

    private float rotation = 1;
    private float velocity = 0;

    public float acceleration = 2f;
    public float deceleration = 10f;

    private Sprite sprite;
    private Batch batch;
    private BitmapFont font;
    private Vector2 vec;
    
    public Character(String dir){
        //Create a new player object
        try {
            this.image = new Texture(dir);
        } catch (RuntimeException e ) {
            this.image = new Texture("triangle2.png");
        } 

        font = new BitmapFont();
        batch = new SpriteBatch();
        sprite = new Sprite(image, 64, 64);
        // sprite.setOrigin(32/2, 32/2);
        sprite.setRotation(rotation);
        vec = new Vector2(0, 0);
        
    }

    public Texture getCharacter(){ return image;  }
    public Sprite getSprite(){ move(); return sprite; }
    public float getX() { return characterX; }
    public float getY() { return characterY; }


    
    public void accelerateForward(float speed){
        setAccelerationAS(sprite.getRotation(), speed);
    }

    public void setAccelerationAS(float angleDeg, float speed){

    }

    public void update(){

    }
    

    //Receive input and move the object
    public void move(){
        //Rotate Right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(rotation < 1){
                rotation = 360;
            }
            sprite.setRotation((float) (rotation--));
            System.out.println(sprite.getRotation());
        }
        //Rotate Left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(rotation > 359){
                rotation = 1;
            }

            sprite.setRotation((float) rotation++);
            System.out.println(sprite.getRotation());

        }



        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            velocity += 0.1f;
        } else if (velocity < 0) {
            velocity = 0;
        } else if ( velocity != 0){
            velocity -= 0.04;
        }

        if (velocity > 10){
            velocity = 10;
        }

        System.out.println(velocity);

        characterX = (float) (velocity * Math.cos(Math.toRadians(rotation)));
        characterY =  (float) (velocity * Math.sin(Math.toRadians(rotation)));
        sprite.translate(characterX, characterY);
        //Move Down?
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            
        }
        batch.begin();
        sprite.draw(batch);
        font.draw(batch,"Rotation Axis: " + String.valueOf(rotation), 10, 710);
        font.draw(batch,"Speed: " + String.valueOf(velocity), 10, 690);
        batch.end();
        
    }
}
