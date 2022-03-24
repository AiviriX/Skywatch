package com.aiv.skywatch.Units;

import com.aiv.skywatch.SpaceObject;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import com.aiv.skywatch.Game;


public class Asteroid extends SpaceObject {
    /*
        TODO:
        Asteroid Spawning
            - From where will they spawn
            - How many will spawn
            - 
    */
    private static float SPEED = 200;
    private static Texture texture;
    private float staticRotation;
    private Sprite sprite;
    public boolean remove = false;

    float x,y,z;
    

    public Asteroid(float x, float rotation){
        if (texture == null){
            Asteroid.texture = new Texture("asteroid_128.png");
        }
        
        
        this.x = x;
        this.y = 1540;
        this.z = 0;
        this.staticRotation = rotation;
        sprite = new Sprite(Asteroid.texture);
        sprite.setSize(32, 32);
        
    }

    public void update(float deltaTime){

        y -= SPEED * deltaTime;
        sprite.setRotation(staticRotation); //physics

        if (y < -50){
            remove = true;
        }


    }

    public void render(SpriteBatch batch){
        batch.draw(sprite, x, y);
    }


}
