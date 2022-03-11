package com.aiv.skywatch.Units;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

//Framework for unit creation
public class Character {
    private Texture image;
    private float characterX;
    private float characterY;
    private float linearX;
    private float linearY;
    private float rotation;
    private float velocity;

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
            this.image = new Texture("triangle.png");
        } 
        batch = new SpriteBatch();
        sprite = new Sprite(image, 64, 64);
        vec = new Vector2(0, 0);
    }

    public Texture getCharacter(){ return image;  }
    public Sprite getSprite(){ return sprite; }
    public float getX() { return characterX; }
    public float getY() { return characterY; }

    public void moveLeft() { sprite.setX(characterX-=4); }
    public void moveRight() { sprite.setY(characterX+=4); }
    public void moveUp() { sprite.setPosition(characterX, characterY+=acceleration); }
    public void moveDown() { sprite.setPosition(characterX, characterY-=4); }
    
    public void accelerate(){
        double l = Math.cos(sprite.getRotation()*3.14/180) * 17;
        
    }
    // public Sprite draw(){
        
    // }

    //Receive input and move the object
    public void move(){
        //Rotate Right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            sprite.setRotation(rotation-=10);
            if (rotation > 360) { rotation = 1; }
        }
        //Rotate Left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            sprite.setRotation(rotation+=10);
        } else { 

        }
        //TODO: Get rotation and accelerate 
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            acceleration++;
            // sprite.setPosition(characterX, sprite.getY() + acceleration);
            sprite.setY(characterY + (float)(acceleration/(Math.sin(rotation))));
            sprite.setX(characterX + (float)(acceleration/(Math.cos(rotation))));
            if (sprite.getY() >= 720){
                sprite.setY(0);
            }
        } else {
            // linearY = acceleration;
            // if (acceleration < 0f){
            //     acceleration = 0;
            // } else {
            //     acceleration-=acceleration;
            //     System.out.println("decrease " + acceleration);
            // }
            // sprite.setPosition(characterX, characterY = sprite.getY() + (int) linearY);
        }

        //Move Down?
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            moveDown();
        }
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
}
