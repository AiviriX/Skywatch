package com.aiv.skywatch;

import com.aiv.skywatch.Screen.LoadingScreen;
import com.aiv.skywatch.Screen.ScreenType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Screen;

import com.aiv.skywatch.Units.Player;

import java.util.EnumMap;


public class Game extends com.badlogic.gdx.Game {
	//Default window size
	float delta;
	private static final String TAG = Game.class.getSimpleName();
	private EnumMap<ScreenType, Screen> screenCache;
	@Override
	public void create () {
		screenCache = new EnumMap<ScreenType, Screen>(ScreenType.class);

	}

	public void setScreen(final ScreenType screenType){
		final Screen screen = screenCache.get(screenType);
		if(screen == null){
			//Screen not created, create it
			try{
				Gdx.app.debug(TAG, "Creating new screen " + screenType);
				final Object newScreen = (Screen) ClassReflection.getConstructor(Game.class).newInstance(this);
				screenCache.put(screenType, (Screen) newScreen);
			} catch (Exception e) {
				throw new GdxRuntimeException("Screen " + screenType + " could not be created ", e);
			}
		} else {
			Gdx.app.debug(TAG, "Switching to screen type " + screenType);
			setScreen(screen);
		}
	}

	@Override
	public void resize(int width, int height){

	}

	@Override
	//Main Loop
	public void render () {


	}

	@Override
	public void dispose () {

	}
}
