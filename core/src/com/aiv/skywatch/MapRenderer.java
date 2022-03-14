package com.aiv.skywatch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Matrix4;
import com.aiv.skywatch.Units.Player;

public class MapRenderer  {
    Player player;
    SpriteBatch batch = new SpriteBatch();
    FPSLogger logger = new FPSLogger();
    MapRenderer map;
    OrthographicCamera cam;

    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();

    public MapRenderer(){
        this.player = new Player("A");
        this.cam = new OrthographicCamera();
        this.cam.setToOrtho(false, w, h);
        this.cam.position.set(player.getX(), player.getY(), 0f);
        
        
    }

    
    public void setView(OrthographicCamera camera){
        
    }

    
    public void render(){

    }

    
    public void setView(Matrix4 projectionMatrix, float viewboundsX, float viewboundsY, float viewboundsWidth,
            float viewboundsHeight) {
        // TODO Auto-generated method stub
        
    }

    public void render(int[] layers) {
        // TODO Auto-generated method stub
        
    }

 
}

