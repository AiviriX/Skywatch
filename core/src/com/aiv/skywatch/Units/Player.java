package com.aiv.skywatch.Units;

import com.aiv.skywatch.SpaceObject;
import com.aiv.skywatch.Bullets.Bullet;

import java.util.ArrayList;
import java.util.Iterator;

import com.aiv.skywatch.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;


//Framework for unit creation
public class Player extends SpaceObject {
    private Texture image;
    //Character's momentum??
    private float characterX;
    private float characterY;
    
    private float x;
    private float y;

    private float rotation = 1;
    private float velocity = 0;
    private float zoomSpeed = 1;
    private float delta;

    private Sprite sprite;
    private Batch batch, hudbatch;
    private BitmapFont font;
    private Vector2 vec; //Player sprite x, y
    private Game game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private ArrayList<Bullet> bullets;
    private Asteroid asteroid = new Asteroid();

    public Player(String dir){
        //Create a new player object
        try {
            this.image = new Texture(dir);
        } catch (RuntimeException e ) {
            this.image = new Texture("triangle-3.png");
        } 

        game = new Game();
        //Font for drawing text on screen
        font = new BitmapFont();
        batch = new SpriteBatch();
        hudbatch = new SpriteBatch();
        //Sprite for the given texture
        sprite = new Sprite(image, 32, 32);
        sprite.setRotation(rotation);
        vec = new Vector2(0, 0);

        sprite.translate(game.getWidth(), game.getHeight());

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()/2, camera);
        bullets = new ArrayList<Bullet>();
        

    }

    public Texture getCharacter(){ return image;  }
    public Sprite getSprite(){ move(); return sprite; }
    public float getX() { return this.sprite.getX(); }
    public float getY() { return this.sprite.getY(); }
    public Camera getPlayerCamera() {
        return this.camera;
    }
    public Vector2 getVector2(){
        return this.vec;
    }

    public void resizeViewport(int width, int height){

    }

    public void wrap() {
        if (sprite.getX() > 1366 * 2){
            sprite.setX(1366*2);
        } 

        if (sprite.getX() < 0){
            sprite.setX(0);
        }

        if (sprite.getY() > 768 * 2){
            sprite.setY(768 * 2);
        }

        if (sprite.getY() < 0){
            sprite.setY(0);
        }
        
        //Warns If leaving mission area
        if (sprite.getX() > (1366 * 2 - 200) || sprite.getX() < 200){
            font.draw(hudbatch,"Warning: Leaving Mission Area ", (1280 / 2) - 32, 720 / 2);
        }

        if (sprite.getY() > (768 * 2) - 200 || sprite.getY() < 200){
            font.draw(hudbatch,"Warning: Leaving Mission Area ", (1280 / 2) - 32, 720 / 2);
        }

    }


    @Override
    public void render(){

        batch.begin();
        delta = Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);

            //Rotate Right
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                if(rotation < 1){
                    rotation = 360;
                }
                sprite.setRotation((float) (rotation-=3));
            }
            //Rotate Left
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                if(rotation >= 360){
                    rotation = 1;
                }
                sprite.setRotation((float) (rotation+=3));
            }
            
            if (Gdx.input.isKeyPressed(Input.Keys.UP)){
                velocity += 0.1f;
            } else if (velocity < 0) {
                velocity = 0;
            } else if ( velocity != 0){
                velocity -= 0.04;
            }

            if (velocity > 5){
                velocity = 5;
            }
            characterX = (float) (velocity * Math.cos(Math.toRadians(rotation)));
            characterY =  (float) (velocity * Math.sin(Math.toRadians(rotation)));
            sprite.translate(characterX, characterY);
    
            //Move Down?
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                velocity-=1f;
                if (velocity < 0f){
                    velocity = 0;
                }
            }
    
            //Placeholder for camera zoom / camera pan
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                camera.zoom -= zoomSpeed * delta;
                //Zoom limit 
                if(camera.zoom <= 0.5){
                    camera.zoom = 0.5f;
                }
            }
    
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                camera.zoom += zoomSpeed * delta;
                if(camera.zoom >= 10){ 
                    camera.zoom = 10f;
                }
            }
            
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                bullets.add(new Bullet(sprite.getX(), sprite.getY(), rotation, 1));
                //right
            }

            //Set Vector2 for x and y of player sprite.
            vec.set(sprite.getX(), sprite.getY());

            
            for(Bullet bullet: bullets){
                bullet.render((SpriteBatch)batch);
            }

            //Bullet update
            for ( Iterator<Bullet> it = bullets.iterator(); it.hasNext(); ){             
                Bullet bullets = it.next();
                bullets.update(delta);
                if(bullets.remove){
                    it.remove();
                }
            }

            asteroid.render((SpriteBatch) batch);
            asteroid.update(Gdx.graphics.getDeltaTime());
            //Camera to texture center not 0,0
            resizeViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            camera.position.set(getX() + 32, getY() + 32, 0);
            camera.update();
            //These are supposed to be in a HUD overlay.
            sprite.draw(batch);
            batch.end();

            hudbatch.begin();
            font.draw(hudbatch,"Rotation Axis: " + String.valueOf(rotation), 10, 710);
            font.draw(hudbatch,"Speed: " + String.valueOf(velocity), 10, 690);
            font.draw(hudbatch,"x: " + sprite.getX() + " y: " + sprite.getY(), 10, 675);
            wrap();
            hudbatch.end();
    }
    
    
}

