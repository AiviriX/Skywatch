package com.aiv.skywatch.Units;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

//Framework for unit creation
public class Character extends Sprite {
    private Texture image;
    private float characterX;
    private float characterY;
    private float linearX;
    private float linearY;
    private float rotation = 1;
    private float velocity = 0;

    private Sprite sprite;
    private Batch batch;
    private Vector2 vec;
    public float acceleration = 2f;
    public float deceleration = 10f;
    
    public Character(String dir){
        //Create a new player object
        try {
            this.image = new Texture(dir);
        } catch (RuntimeException e ) {
            this.image = new Texture("triangle2.png");
        } 
        batch = new SpriteBatch();
        sprite = new Sprite(image, 64, 64);
        sprite.setRotation(rotation);
        vec = new Vector2(0, 0);
    }

    @Override
    public void draw(Batch spritebatch){
        
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
        batch.end();
        
    }
}
