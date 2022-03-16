package com.aiv.skywatch.Bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
    public static final float SPEED = 2;

    public static float speed;

    private static Texture texture;

    float x, y;

    public boolean remove = false;
    
    /**
     * The Bullet class allows you to draw bullets from a ship object. Can also be used to extend bullet subclasses
     * @param x - X Origin of the bullet
     * @param y - Y Origin of the bullet
     */
    public Bullet(float x, float y){
        if (texture == null){
            Bullet.texture = new Texture("triangle2.png");
        } 

        this.x = x;
        this.y = y;
    }

    public void update(float deltaTime){
        y += SPEED * deltaTime ;
        if (y > Gdx.graphics.getHeight()){
            remove = true;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, this.x, this.y);
    }
    

}
