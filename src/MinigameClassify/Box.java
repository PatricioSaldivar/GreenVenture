/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinigameClassify;

import java.awt.Graphics;
import java.awt.Rectangle;
import videogame.Assets;
import videogame.Item;
import videogame.MinigameTrashClassify;

/**
 *
 * @author BonfireStudios
 */
//This is the box glove class
public class Box extends Item {

    private int width;
    private int height;
    private boolean boxMoving = false;
    private boolean boxReturn = false;
    private MinigameTrashClassify minigame;
    private int leftX = 448;
    private boolean boxSide = false;
    private int speed = 20;

    public Box(int x, int y, int width, int height, MinigameTrashClassify minigame) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.minigame = minigame;
        
    }

    /**
     * To get the height of the trashcan
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To set the height of the trashcan
     *
     * @param <code>height</code> value with height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * To set the width of the trashcan
     *
     * @param <code>width</code> value with width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To get the width of the trashcan
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    public boolean isBoxMoving() {
        return boxMoving;
    }

    public boolean isBoxReturn() {
        return boxReturn;
    }
    
    

    @Override
    public void tick() {
        //IF a box glove is moving
        if (boxMoving) {
            //If it is the right glove the right glove x will move
            if (boxSide) {
                setX(getX() + speed);
                /*Calculate the position of the guantlet after hitting the trash, if pisition is true return the guantlet to original position
            this.getWidth was multiply 2 times because the guantlet is subract two times
                 */
                if (getX() > (minigame.getWidth() - (this.getWidth() * 2) - (minigame.getInTrashCan().getWidth() / 2) - 32)) {
                    //Original Position
                    boxReturn=true;
                    boxMoving = false;
                }
                //if it is the left glove the left glove x will move
            } else {
                leftX-= speed;
            
            //Calculate the position of the guantlet after hitting the trash, if pisition is true return the guantlet to original position
            if (leftX < ((minigame.getInTrashCan().getWidth() / 2) + 32 + this.getWidth())) {
                //Original position
                boxReturn=true;
                boxMoving = false;
            }
            }
            //If a box glove is returning from hitting a trash
        }else if(boxReturn){
            //If the box glove is the right one the x will be modified
            if (boxSide) {
                x-=speed;
                if(x<=0){
                    x=0;
                boxReturn=false;
                }
                //If the left glove is returning its x will be modified until it reaches its inital position
            }else{
                leftX+=speed;
                if(leftX>=448){
                    leftX=448;
                    boxReturn=false;
                }
            }
            //IF any box guantlet is moving or returning you may press the arrows for a gauntlet be set to moving
        }else {

                // If the guantlet is in the initial position the player can press right to move the guantlet so it can hit the trash
                if (minigame.getKeyManager().right && !minigame.getKeyManager().helperRight) {
                    boxMoving = true;
                    boxSide=true;
                    
                }

                // If the guantlet is in the initial position the player can press right to move the guantlet so it can hit the trash
                if (minigame.getKeyManager().left && !minigame.getKeyManager().helperLeft) {
                    boxMoving = true;
                    boxSide=false;
                }

            }
        }

    @Override
    public void render(Graphics g) {
        //draws image of box guantlet depending if it goes to the right or left
            g.drawImage(Assets.rightBox, getX(), getY(), getWidth(), getHeight(), null);
            g.drawImage(Assets.rightExt,getX()-384,getY(),384,getHeight(),null);
            g.drawImage(Assets.leftBox, leftX, getY(), getWidth(), getHeight(), null);
             g.drawImage(Assets.leftExt,leftX+width,getY(),384,getHeight(),null);
    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());

    }
        public Rectangle getPerimetroLeft() {
        return new Rectangle(leftX, getY(), getWidth(), getHeight());

    }

}
