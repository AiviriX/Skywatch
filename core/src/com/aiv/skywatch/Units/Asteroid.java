package com.aiv.skywatch.Units;

import com.aiv.skywatch.SpaceObject;
import com.aiv.skywatch.Tools.Hitbox;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;



public class Asteroid extends SpaceObject{
    public static float speed;
    private float rotationalVelocity;
    private float staticRotation;
    private static Texture texture;
    private Rectangle rectangle;
    Hitbox hitbox;
    float x, y;
    int spdx, spdy;

    public boolean remove = false;

    private Sprite sprite;
    

    public Asteroid(int spdx, int spdy){
        if (texture == null){
            Asteroid.texture = new Texture("triangle2.png");
        }
        this.x = 1360;
        this.y = 720;
        sprite = new Sprite(Asteroid.texture);
        this.rectangle = new Rectangle(x, y, 64, 64);
        this.spdx = spdx;
        this.spdy = spdy;
    }

    public void update(float deltaTime){
        x += spdx;
        y += spdy;
        if (sprite.getX() > 1366 * 2){
            x = 0;
            System.out.println("Wtf");
        } 

        if (sprite.getX() < 0){
            x = 1366 * 2;
        }

        if (sprite.getY() > 768 * 2){
            y = 0;
        }

        if (sprite.getY() < 0){
            y = 768 * 2;
        }

        rectangle.setX(x);
        rectangle.setY(y);
        sprite.setX(x);
        sprite.setY(y);
    }

    public Rectangle getBoundingBox(){
        return this.rectangle;
    }

    public void render(SpriteBatch batch){
        batch.draw(sprite, this.x + 8, this.y + 8);
        this.update(Gdx.graphics.getDeltaTime());
    }

    public Rectangle getHitbox(){
        return this.rectangle; 
    }

    
}