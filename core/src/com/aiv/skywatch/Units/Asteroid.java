package com.aiv.skywatch.Units;

import com.aiv.skywatch.SpaceObject;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;



public class Asteroid extends SpaceObject{
    public static float speed;
    private float rotationalVelocity;
    private float staticRotation;
    private static Texture texture;

    float x, y;

    public boolean remove = false;

    private Sprite sprite;
    

    public Asteroid(){
        if (texture == null){
            Asteroid.texture = new Texture("triangle2.png");
        }
        
        this.x = 1360;
        this.y = 720;
        sprite = new Sprite(Asteroid.texture);
    }

    //Find out where  the ship points and fire at that direction 
    public void update(float deltaTime){
        wrap();
        x+=5;
        sprite.setX(x);
    }

    @Override
    public void wrap(){
        if (sprite.getX() > 1366 * 2){
            sprite.setX(1366*2);
            System.out.println("WTf");
        } 

        if (sprite.getX() < 0){
            sprite.setX(0);
        }

        if (sprite.getY() > 768 * 2){
            sprite.setY(768 * 2);
        }

        if (sprite.getY() < 0){
            sprite.setY(0);
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(sprite, this.x + 8, this.y + 8);
        
    }
    
}