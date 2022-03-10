package com.aiv.skywatch.Units;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

//Framework for unit creation
public class Character {
    private Texture image;
    private int characterX;
    private int characterY;
    private float rotation = 360f;
    private Sprite sprite;
    private Batch batch;
    
    public Character(String dir){
        try {
            this.image = new Texture(dir);
        } catch (RuntimeException e ) {
            this.image = new Texture("triangle.png");
        } 
        batch = new SpriteBatch();
        sprite = new Sprite(image, 64, 64);
    }

    public Texture getCharacter(){ return image;  }
    public Sprite getSprite(){ return sprite; }
    public int getX() { return characterX; }
    public int getY() { return characterY; }

    public void moveLeft() { sprite.setX(characterX-=4); }
    public void moveRight() { sprite.setY(characterX+=4); }
    public void moveUp() { sprite.setPosition(characterX, characterY+=4); }
    public void moveDown() { sprite.setPosition(characterX, characterY-=4); }
    
    public void accelerate(){
        
    }
    // public Sprite draw(){
        
    // }

    //Moves the object
    public void move(){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            sprite.setRotation(rotation-=10);
            if (rotation > 360) { rotation = 1; }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            sprite.setRotation(rotation+=10);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            moveUp();
        } 

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            moveDown();
        }
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
}
