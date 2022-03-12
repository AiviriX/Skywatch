package com.aiv.skywatch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;


import com.aiv.skywatch.Units.Player;


public class Game extends ApplicationAdapter implements ApplicationListener {
	SpriteBatch batch;
	Texture img;
	Character chare;
	Player player;
	Texture backgroundTexture;	
	Sprite backgroundSprite;

	@Override
	public void create () {	
		batch = new SpriteBatch();
		img = new Texture("triangle.png");
		//chare = new Character("A");
		player = new Player("A");
		backgroundTexture = new Texture("genericSpace.jpg");
		backgroundSprite = new Sprite(backgroundTexture);
	}

	@Override
	public void resize(int width, int height){
		
	}

	@Override
	//Main Loop
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClearColor(0, 0, 0,255);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		player.move();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		
	}

		

}
