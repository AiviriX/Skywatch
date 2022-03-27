package com.aiv.skywatch.Screens;

import com.aiv.skywatch.SpaceObject;
import com.aiv.skywatch.Bullets.Bullet;
import com.aiv.skywatch.Units.Asteroid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.aiv.skywatch.SkyGame;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObjectArray.less;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;


//Framework for unit creation
public class MainGameScreen implements Screen {
    private final int WIDTH = 32;
    private final int HEIGHT = 32;

    private Texture image;
    //Character's momentum??
    private float characterX;
    private float characterY;
    
    private float nexttime;
    private float currenttime;
    private float x;
    private float y;
    private float rotation = 1;
    private float velocity = 0;
    private float zoomSpeed = 1;
    private float delta;
    private int lives = 3;

    private Rectangle rectangle;
    private float asteroidSpawnTimer;
    Random random;

    private Sprite sprite;
    private Batch batch, hudbatch;
    private BitmapFont font;
    private Vector2 vec; //Player sprite x, y
    private SkyGame game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private ArrayList<Bullet> bullets;
    private ArrayList<Asteroid> asteroids;
    private Asteroid asteroid;
    Texture img, backgroundTexture;
    TextureRegion imgTextureRegion;
    public int iFrame = 4;



    //Asteroid Related
    private float min_spawn_time = 0.1f; //Can change values to make asteroids fall faster/slower
    private float max_spawn_time = 1f;
    
    public MainGameScreen(SkyGame game){
        //Create a new player object
        try {
            this.image = new Texture("ship.png");
        } catch (RuntimeException e ) {
            this.image = new Texture("triangle-3.png");
        }

        this.game = game;
        //Font for drawing text on screen
        font = new BitmapFont();
        batch = new SpriteBatch();
        hudbatch = new SpriteBatch();
        //Sprite for the given texture
        sprite = new Sprite(image, 32, 32);
        sprite.setRotation(rotation);
        vec = new Vector2(0, 0);
        
        sprite.translate(1366, 768);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()/2, camera);
        bullets = new ArrayList<Bullet>();

        //Hitbox
        this.rectangle = new Rectangle(x, y, 32, 32);
        

        //Asteroid related
        asteroids = new ArrayList<Asteroid>(); //Array List for Asteroid
        random = new Random(); //Random for Asteroid spawner
        asteroidSpawnTimer = random.nextFloat() * (max_spawn_time - min_spawn_time) + min_spawn_time; //spawn timer 

        //Draw background texture
        backgroundTexture = new Texture("genericSpace.jpg"); 
		backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		imgTextureRegion = new TextureRegion(backgroundTexture);
		imgTextureRegion.setRegion(-1366, -768,backgroundTexture.getWidth()*2, backgroundTexture.getHeight()*2);
    }

    public Texture getCharacter(){ return image;  }
    public float getX() { return this.sprite.getX(); }
    public float getY() { return this.sprite.getY(); }
    public Camera getPlayerCamera() { return this.camera; }
    public Vector2 getVector2(){ return this.vec; }
    public Rectangle getHitbox(){ return this.rectangle; }
    public float getRotation(){ return this.rotation; }
    public float getVelocity(){ return velocity; }

    public void resizeViewport(int width, int height){
    }

    //Immunity frames for the player when the ship gets hit.
    public void getHit(SpaceObject object){
        velocity = 0;
        lives--;
        //If player gets hit, trigger immunity for 4 seconds
        nexttime = System.nanoTime() + (float)(2*(Math.pow(10, 9)));
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

    public boolean intersects(Rectangle otherRectangle){
        Rectangle thisRectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        return thisRectangle.overlaps(otherRectangle);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {

        });
    }

    @Override
    public void render(float delta) {
        System.out.println("Hi!");
        currenttime = System.nanoTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(imgTextureRegion, 0, 0);

        x = getX();
        y = getY();
        if (lives <= 0){
            System.out.println("Game Over!");
        }


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
            rectangle.setX(getX());
            rectangle.setY(getY());
    
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
                bullets.add(new Bullet(sprite.getX(), sprite.getY(), rotation));
                //right
            }
           
            //Set Vector2 for x and y of player sprite.
            vec.set(sprite.getX(), sprite.getY());

            //Draw Bullets and Asteroids and update them
            for(Bullet bullet: bullets){
                bullet.render((SpriteBatch)batch);
            }
            
            for(Asteroid asteroid : asteroids){
                asteroid.render((SpriteBatch) batch);
                //If player gets hit.
                if (this.getHitbox().overlaps(asteroid.getHitbox())){
                    //Checks if the time is greater than the end of I frames
                    if (currenttime > nexttime){                      
                        System.out.println("true" + lives);
                        getHit(asteroid); 
                    }
                } else {
                    
                }
                
                //If a bullet hits an asteroid.
                for (Bullet bullet : bullets){
                    if (bullet.getHitbox().overlaps(asteroid.getHitbox())){
                        bullet.remove = true;
                        asteroid.remove = true;
                    }
                }      
            }
            
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)){
                System.out.println("P");
                this.dispose();
            }

            //Collision detection for asteroid and bullet, or asteroid and player.
            for (Iterator<Asteroid> it = asteroids.iterator(); it.hasNext(); ){
                Asteroid asteroids = it.next();

                if(asteroids.remove){
                    it.remove();
                }
            }

            for ( Iterator<Bullet> it = bullets.iterator(); it.hasNext(); ){             
                Bullet bullets = it.next();
                bullets.update(delta);
    
                
                if(bullets.remove){
                    it.remove();                    
                }             
            }

            if (asteroids.isEmpty()){
                System.out.println("Asteroids are empty");
            }


            //Asteroid Spawn Timer
            asteroidSpawnTimer -= delta;
            if (asteroidSpawnTimer <=0){
                asteroidSpawnTimer = random.nextFloat() * (max_spawn_time - min_spawn_time) + min_spawn_time;
                asteroids.add(new Asteroid(random.nextInt(2470 - 16),rotation));
            }

            //Asteroid Render 
            for (Asteroid asteroid: asteroids){

                asteroid.render((SpriteBatch) batch); //renders top-bottom asteroids

            }
            
            

            //asteroid Update
            for ( Iterator<Asteroid> it = asteroids.iterator(); it.hasNext(); ){             
                Asteroid asteroids = it.next();

                asteroids.update(delta);

                if(asteroids.remove){
                    it.remove();
                }
            }
            

            //Camera to texture center not 0,0
            resizeViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            camera.position.set(x + 32, y + 32, 0);
            camera.update();
            sprite.draw(batch);
            batch.end();

            hudbatch.begin();
            font.draw(hudbatch,"Rotation Axis: " + String.valueOf(rotation), 10, 710);
            font.draw(hudbatch,"Speed: " + String.valueOf(velocity), 10, 690);
            font.draw(hudbatch,"x: " + sprite.getX() + " y: " + sprite.getY(), 10, 675);
            font.draw(hudbatch, "Lives " + lives, 10, 650);
            wrap();
            hudbatch.end();

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        
    }
}

