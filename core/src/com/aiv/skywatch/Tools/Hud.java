// package com.aiv.skywatch.Tools;

// import java.sql.BatchUpdateException;

// import com.aiv.skywatch.Units.Player;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.Camera;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.Sprite;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;


// public class Hud {
//     private SpriteBatch batch;
//     private BitmapFont font;
//     private Player player;
//     private Camera camera;

//     public Hud (SpriteBatch batch, Player player){
// //        font = new BitmapFont();
//         this.player = player;
//         this.camera = player.getPlayerCamera();
//     }

//     public void draw(){
//         font.draw(batch,"Rotation Axis: " + String.valueOf(player.getRotation()), 10, 710);
//         font.draw(batch,"Speed: " + String.valueOf(player.getVelocity()), 10, 690);
//         font.draw(batch,"x: " + player.getX() + " y: " + player.getY(), 10, 675);
//     }
// }
