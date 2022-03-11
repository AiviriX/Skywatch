package com.aiv.skywatch.Units;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor{
    private Texture texture;
    private Actor actor;
    private Batch batch;
    private Vector2 vec;
    private Sprite sprite;

    //Character Positions
    private float characterX = 0;
    private float characterY = 0;

    private float linearX;
    private float linearY;
    private double rotation;
    private float velocity;
    public float acceleration = 2f;
    public float deceleration = 10f;
    
    
    public Player(String dir){
        try {
            this.texture = new Texture(Gdx.files.internal(dir));
        } catch (RuntimeException e){
            this.texture = new Texture("triangle.png");
        }
        batch = new SpriteBatch();
        actor = new Actor();
        sprite = new Sprite(this.texture, 64, 64);
        
        
    }

    @Override
    public void draw(Batch batch, float alpha){
        act(Gdx.graphics.getDeltaTime());
    }

    @Override 
    public void act(float delta){
        actor.draw(batch, delta);
    }
    public Texture getImage() { return texture; }



    public void move(){
        //Rotate Right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            actor.setPosition(characterX++, characterY);
        }
        //Rotate Left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            actor.setX(characterX+1);
        } 

        //TODO: Get rotation and accelerate 
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){

        }


        batch.begin();
        this.actor.draw(batch, 1);  
        batch.end();
    }
}
