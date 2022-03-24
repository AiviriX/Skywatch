package com.aiv.skywatch.Bullets;

import com.aiv.skywatch.Tools.Hitbox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import org.w3c.dom.css.Rect;

public class Bullet {
    public static final float SPEED = 1000f;

    public static float speed;
    private float rotationalVelocity;
    private float staticRotation;
    private static Texture texture;
    private final int WIDTH = 16;
    private final int HEIGHT = 16;
    private Rectangle rectangle;
    float x, y;
    Hitbox hitbox;
    public boolean remove = false;

    private Sprite sprite;
    
    /**
     * The Bullet class allows you to draw bullets from a ship object. Can also be used to extend bullet subclasses
     * @param x - X Origin of the bullet
     * @param y - Y Origin of the bullet
     */
    public Bullet(float x, float y, float rotation){
        if (texture == null){
            Bullet.texture = new Texture("BulletSprite.png");
        }
        
        this.x = x;
        this.y = y;
        this.rotationalVelocity = rotation;
        this.staticRotation = rotation;
        this.rectangle = new Rectangle(x, y, 16,16);
//        this.hitbox = new Hitbox(x, y, WIDTH, HEIGHT);
        sprite = new Sprite(Bullet.texture);
        sprite.setSize(16, 16);
    }

    

    //Find out where the ship points and fire at that direction & despawns bullet when they reach the corner.
    public void update(float deltaTime){
        y += (SPEED * Math.sin(Math.toRadians(rotationalVelocity)) * deltaTime);
        x += (SPEED * Math.cos(Math.toRadians(rotationalVelocity)) * deltaTime);
        sprite.setRotation(staticRotation);

        //Turn this to a timer
        if (y > 768 * 2 || y < 0){
            remove = true;
        }

        if(x > 1366 * 2|| x < 0){
            remove = true;
        } 

    
        rectangle.setX(x);
        rectangle.setY(y);
    }

    public void render(SpriteBatch batch){
        batch.draw(sprite, this.x + 8, this.y + 8);
    }
    
    public Rectangle getHitbox(){
        return this.rectangle; 
    }

    
}
