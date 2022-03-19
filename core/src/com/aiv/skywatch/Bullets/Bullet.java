package com.aiv.skywatch.Bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
    public static final float SPEED = 10;

    public static float speed;
    private float rotationalVelocity;
    private float staticRotation;
    private static Texture texture;

    float x, y;

    public boolean remove = false;

    private Sprite sprite;
    
    /**
     * The Bullet class allows you to draw bullets from a ship object. Can also be used to extend bullet subclasses
     * @param x - X Origin of the bullet
     * @param y - Y Origin of the bullet
     */
    public Bullet(float x, float y, float rotation, int size){
        if (texture == null){
            Bullet.texture = new Texture("BulletSprite.png");
        }
        
        this.x = x;
        this.y = y;
        this.rotationalVelocity = rotation;
        this.staticRotation = rotation;
        sprite = new Sprite(Bullet.texture);
        sprite.setSize(16, 16);
        
    }

    //Find out where  the ship points and fire at that direction 
    public void update(float deltaTime){
        y += (SPEED * Math.sin(Math.toRadians(rotationalVelocity)));
        x += (SPEED * Math.cos(Math.toRadians(rotationalVelocity)));
        sprite.setRotation(staticRotation);

        //Turn this to a timer
        if (y > 768 * 2 || y < 0){
            remove = true;
        }

        if(x > 1366 * 2|| x < 0){
            remove = true;
        } 

        


    }

    public void render(SpriteBatch batch){
        batch.draw(sprite, this.x + 8, this.y + 8);
    }
    

}
