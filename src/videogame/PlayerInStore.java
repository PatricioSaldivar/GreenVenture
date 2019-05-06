/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author BonfireStudios
 */
public class PlayerInStore extends Item {
    private int width;
    private int height;
    private int speed;
    private Game game;
    private Animation animationUp;
    private Animation animationDown;
    private Animation animationRight;
    private Animation animationLeft;
    private int direction; // 1 = Down , 2 = Up, 3 = Right, 4 = Left
            

public PlayerInStore(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
         //The speed of the animation need to be changed depending of the player speed
        this.animationUp = new Animation(Assets.playerUp,200);
        this.animationDown = new Animation(Assets.playerDown,200);
        this.animationRight = new Animation(Assets.playerRight,200);
        this.animationLeft = new Animation(Assets.playerLeft,200);
        this.direction = 1;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setGame(Game game) {
        this.game = game;
    }
   
    @Override
    public void tick() {   
        // moving player depending on flags
        this.animationDown.tick();
        this.animationUp.tick();
        this.animationRight.tick();
        this.animationLeft.tick();
        if (game.getKeyManager().up) {
            direction = 2;
            setY(getY() - speed);
        }
        if (game.getKeyManager().down) {
            direction = 1;
            setY(getY() + speed);
        }
        if (game.getKeyManager().right) {
            direction = 3;
            setX(getX() + speed);
        }
        if (game.getKeyManager().left) {
            direction = 4;
            setX(getX() - speed);
        }
        
        // reset x position and y position if colision
        if (getX() + 65 >= game.getWidth()) {
            setX(game.getWidth() - 65);
        }
        else if (getX() <= 0) {
            setX(0);
        }
        
        if (getY() + 65 >= game.getHeight()) {
            setY(game.getHeight() - 65);
        }
        else if (getY() <= 0) {
            setY(0);
        }
        
      
    }

    @Override
    public void render(Graphics g) {
        //Render down animation
        if(direction == 1 && game.getKeyManager().down){
            g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        
        //Render down static
        }else if(direction == 1){
            g.drawImage(Assets.playerFacingDown, getX(), getY(), getWidth(), getHeight(), null);
        }
         //Render up animation
        if(direction == 2  && game.getKeyManager().up){
            g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        
        //Render up static
    }else if(direction == 2 ){
            g.drawImage(Assets.playerFacingUp, getX(), getY(), getWidth(), getHeight(), null);
        }
         //Render right animation
        if(direction == 3  && game.getKeyManager().right){
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        //Render right static
        }else if(direction == 3){
            g.drawImage(Assets.playerFacingRight, getX(), getY(), getWidth(), getHeight(), null);
        }
         //Render left animation
        if(direction == 4 && game.getKeyManager().left){
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        
        //Render left static
        }else if(direction == 4){
            g.drawImage(Assets.playerFacingLeft, getX(), getY(), getWidth(), getHeight(), null);
        }

    }

    @Override
    public Rectangle getPerimetro() {
      return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    
}
