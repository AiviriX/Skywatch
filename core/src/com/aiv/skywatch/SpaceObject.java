package com.aiv.skywatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public abstract class SpaceObject extends ApplicationAdapter{
    private Texture image;
    private Actor actor;
    private Batch batch;
    private Vector2 vec;

    //Character Positions
    private float characterX;
    private float characterY;
    
    private float linearX;
    private float linearY;
    private double rotation;
    private float velocity;

    private float leftWidth;
    private float rightWidth;
    private float topHeight;
    private float bottomHeight;
    private float minHeight;
    private float minWidth;
    private Rectangle rectangle;

    public SpaceObject(){

    }




    public Rectangle getHitbox(){
        return this.rectangle; 
    }

}

 