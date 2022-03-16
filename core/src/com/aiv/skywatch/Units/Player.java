package com.aiv.skywatch.Units;

import com.aiv.skywatch.SpaceObject;
import com.aiv.skywatch.Bullets.Bullet;

import java.util.ArrayList;

import com.aiv.skywatch.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.InputProcessor;


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
    private float zoomSpeed = 1;
 

    private Sprite sprite;
    private Batch batch;
    private BitmapFont font;
    private Vector2 vec; //Player sprite x, y
    private Game game;
    private Viewport viewport;
    private OrthographicCamera camera;

    private ArrayList<Bullet> bullets;
    
    public Player(String dir){
        //Create a new player object
        try {
            this.image = new Texture(dir);
        } catch (RuntimeException e ) {
            this.image = new Texture("triangle-3.png");
        } 

        game = new Game();
        //Font for drawing text on screen
        font = new BitmapFont();
        batch = new SpriteBatch();
        //Sprite for the given texture
        sprite = new Sprite(image, 32, 32);
        sprite.setRotation(rotation);
        vec = new Vector2(0, 0);
        sprite.translate(game.getWidth(), game.getHeight());
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()/2, camera);

        bullets = new ArrayList<Bullet>();
        

    }

    public Texture getCharacter(){ return image;  }
    public Sprite getSprite(){ return sprite; }
    public float getX() { return sprite.getX(); }
    public float getY() { return sprite.getY(); }
    public Vector2 getVector2() { return vec; }
    public void resize(){

    }

    public void resizeViewport(int width, int height){
        viewport.update(width, height, false);
    }

    public OrthographicCamera getPlayerCamera(){
        return this.camera;
    }

    public void draw(){
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for(Bullet bullet: bullets){
            bullet.render((SpriteBatch)batch);
        }
        //Camera to texture center not 0,0
        resizeViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(getX() + 32, getY() + 32, 0);
        camera.update();
        //These are supposed to be in a HUD overlay.
        font.draw(batch,"Rotation Axis: " + String.valueOf(rotation), 10, 710);
        font.draw(batch,"Speed: " + String.valueOf(velocity), 10, 690);
        font.draw(batch,"x: " + sprite.getX() + " y: " + sprite.getY(), 10, 675);
        sprite.draw(batch);
        this.move(Gdx.graphics.getDeltaTime());

        batch.end();
        
    }

    public void move(float delta){
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

        //Placeholder for camera zoom / camera pan
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            camera.zoom -= zoomSpeed * delta;
            //Zoom limit 
            if(camera.zoom <= 0.5){
                camera.zoom = 0.5f;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            camera.zoom += zoomSpeed * delta;
            if(camera.zoom >= 10){
                camera.zoom = 10;
            }
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            bullets.add(new Bullet(sprite.getX(), sprite.getY()));
            //right
        }

        //Bullet update
        ArrayList<Bullet> bulletRemove = new ArrayList<Bullet>();
        for (Bullet bullet : bullets){
            bullet.update(delta);
            if (bullet.remove == true){
                bullets.remove(bullet);
            }
        }


        //Set Vector2 for x and y of player sprite.
        vec.set(sprite.getX(), sprite.getY());
    }
    

}

