package com.aiv.skywatch.Screens;

import com.aiv.skywatch.SkyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class MainMenuScreen extends ScreenAdapter {

    SkyGame game;

    public MainMenuScreen(SkyGame game) {
        this.game = game;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new MainGameScreen(game));
					System.out.println("Ho");
					hide();
                }

                if(keyCode == Input.Keys.X){
                    game.setScreen(new GameOverScreen(game));
                    System.out.println("hi");
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.draw(game.batch, "SKYWATCH", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "Press x to gameOver.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "Press space to play.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        game.font.draw(game.batch, "Spacebar to shoot, Up key to accelerate,\n Left Right Keys to turn. Happy Hunting!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .1f);
        game.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}