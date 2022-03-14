package com.aiv.skywatch;

import com.aiv.skywatch.Screen.LoadingScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Screen;

import com.aiv.skywatch.Units.Player;



public class Game extends ApplicationAdapter {
	//Default window size

	private int GAME_WIDTH = 1280;
	private int GAME_HEIGHT = 720;
	SpriteBatch batch;
	Texture img, backgroundTexture;
	Character chare;
	Player player;
	Sprite backgroundSprite;
	OrthographicCamera camera;
	Stage stage;
	Viewport viewport;
	MapRenderer map;
	

	float delta; 
	public int getWidth(){
		return this.GAME_WIDTH;
	}

	public int getHeight(){
		return this.GAME_HEIGHT;
	}

	@Override
	public void create () {	
		delta = 100f;
		// map = new MapRenderer();
		batch = new SpriteBatch();
		img = new Texture("triangle.png");
		player = new Player("A");
		backgroundTexture = new Texture("genericSpace.jpg");
		backgroundSprite = new Sprite(backgroundTexture);
		camera = new OrthographicCamera(50,50);
		camera.setToOrtho(false);
		viewport = new FitViewport(1280, 720, camera);

	}

	@Override
	public void resize(int width, int height){
		viewport.update(width, height, false);
	}

	@Override
	//Main Loop
	public void render () {
		batch.setProjectionMatrix(camera.combined);
		resize(GAME_WIDTH, GAME_HEIGHT);
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClearColor(.141f, .213f, .255f, .255f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		viewport.apply();
		camera.translate(player.getAcceleration(), player.getAcceleration(), 0);
		camera.position.set(player.getX() ,player.getY(), 0);
		camera.position.set	((float) player.getX(),(float) player.getY(), 0);

		//Batch Begin
		batch.begin();
		batch.draw(backgroundTexture, 0 ,0);
		camera.update();
		batch.end();
		//Batch End

		player.draw();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
