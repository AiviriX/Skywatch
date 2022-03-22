package com.aiv.skywatch.Tools;

import com.badlogic.gdx.math.Rectangle;

/**
 * Simulates Hitboxes and collision for space objects.
 */
public class Hitbox extends Rectangle{
    float x, y;
    int width, height;

    public Hitbox(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith(Hitbox rect){
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x &&  y + height > rect.height;
    }

    
}
