package com.aiv.skywatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


public abstract class SpaceObject extends Sprite implements Drawable{
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

    public SpaceObject(){

    }

    @Override
	public void draw(Batch batch, float x, float y, float width, float height){

    }

    @Override
	public float getLeftWidth (){
        return this.leftWidth;
    }

	public void setLeftWidth (float leftWidth){
        this.leftWidth = leftWidth;
    }

	public float getRightWidth (){
        return this.rightWidth;
    }

	public void setRightWidth (float rightWidth){
        this.rightWidth = rightWidth;
    }

	public float getTopHeight (){
        return this.topHeight;
    }

	public void setTopHeight (float topHeight){
        this.topHeight = topHeight;
    }

	public float getBottomHeight (){
        return this.bottomHeight;
    }

	public void setBottomHeight (float bottomHeight){
        this.bottomHeight = bottomHeight;
    }

	public float getMinWidth (){
        return this.minWidth;
    }

	public void setMinWidth (float minWidth){
        this.minWidth = minWidth;
    }

	public float getMinHeight (){
        return this.minHeight;
    }

	public void setMinHeight (float minHeight){
        this.minHeight = minHeight;
    }

    public float getVelocity(){
        return velocity;
    }

    public void move(){
        
    }


}
 