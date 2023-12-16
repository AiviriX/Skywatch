package com.aiv.skywatch;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public abstract class SpaceObject {
    private Rectangle rectangle;

    public SpaceObject(){

    }

    public Rectangle getHitbox(){
        return this.rectangle; 
    }

}

