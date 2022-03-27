package com.aiv.skywatch;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.aiv.skywatch.Screens.MainMenuScreen;
import com.aiv.skywatch.Tools.GameCamera;
import com.aiv.skywatch.Tools.ScrollingBackground;

public class SpaceGame extends Game {
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public static boolean IS_MOBILE = false;
	
	public SpriteBatch batch;
	public ScrollingBackground scrollingBackground;
	public GameCamera cam;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new GameCamera(WIDTH, HEIGHT);
		
		if (Gdx.app.getType() == ApplicationType.Android || Gdx.app.getType() == ApplicationType.iOS)
			IS_MOBILE = true;
		IS_MOBILE = true;
		
		this.scrollingBackground = new ScrollingBackground();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined());
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		cam.update(width, height);
		super.resize(width, height);
	}
	
}