/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinigameThrow;

import java.awt.Graphics;
import java.awt.Rectangle;
import videogame.Animation;
import videogame.Assets;
import videogame.Item;

/**
 *
 * @author PatoSaldivar
 */
public class TrashThrown extends Item {

    private int width;
    private int height;
    private int moveX;
    private int moveY;
    private int xSpeed=0;
    private int ySpeed=0;
    private boolean gravity;
    private Animation animation;
    
    public TrashThrown(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
        animation = new Animation(Assets.inTrash,200);
        moveX=0;
        moveY=0;
        
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }
    

    @Override
    public void tick() {
        
        animation.tick();
        
           if(moveX<=50){
           x+=xSpeed;
           moveX++;
           }
           if(gravity){
               y+=ySpeed;
 
           }else if(moveY<=50){
               y-=ySpeed;
               moveY+=2;
           }else{
               gravity=true;
               y+=ySpeed;
           }

       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), x, y, width, height, null);
    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(x,y,width,height);
    }
    
    
}
