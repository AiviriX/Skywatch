package com.aiv.skywatch.Units;

import com.aiv.skywatch.SpaceObject;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Asteroid extends SpaceObject {
    /*
        TODO:
        Asteroid Spawning
            - From where will they spawn
            - How many will spawn
            - 
    */
    private float SPEED, WIDTH, x, y;
    private float spawnX, spawnY;
    private Texture texture;
    private Asteroid asteroid;
    public boolean dead = false;
    private Player player;

    public Asteroid(Player player){
        //Handling asteroid spawns within a radius from a player.
        this.player = player;
        this.x = Gdx.graphics.getWidth() * 2;
        this.y = Gdx.graphics.getWidth() * 2;
        this.texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        //Get the players coordinates and create a spawning radius
    }

    public void onHit(){
        
    }

    public void update(float deltaTime){
        
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y);
    }
}
