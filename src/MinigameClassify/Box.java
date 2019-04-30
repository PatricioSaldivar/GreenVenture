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
    private boolean boxSide;   // True = RightSide, Flase = LeftSide
    private MinigameTrashClassify minigame;

    public Box(int x, int y, int width, int height, boolean BoxSide, MinigameTrashClassify minigame) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.minigame = minigame;
        this.boxSide = BoxSide;
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

    public void setBoxSide(boolean BoxSide) {
        this.boxSide = BoxSide;
    }

    public boolean isBoxSide() {
        return boxSide;
    }

    public boolean isBoxMoving() {
        return boxMoving;
    }

    @Override
    public void tick() {
        if (boxMoving) {
            if (isBoxSide()) {
                setX(getX() + 5);
                /*Calculate the position of the guantlet after hitting the trash, if pisition is true return the guantlet to original position
            this.getWidth was multiply 2 times because the guantlet is subract two times
                 */
                if (getX() > (minigame.getWidth() - (this.getWidth() * 2) - (minigame.getInTrashCan().getWidth() / 2) - 32)) {
                    //Original Position
                    setX(0);
                    boxMoving = false;
                }
            } else {
                setX(getX() - 5);
            
            //Calculate the position of the guantlet after hitting the trash, if pisition is true return the guantlet to original position
            if (getX() < ((minigame.getInTrashCan().getWidth() / 2) + 32 + this.getWidth())) {
                //Original position
                setX(448);
                boxMoving = false;
            }
            }
        } else {

            //Movement of guantlet in right side
            if (isBoxSide()) {
                // If the guantlet is in the initial position the player can press right to move the guantlet so it can hit the trash
                if (minigame.getKeyManager().right && !minigame.getKeyManager().helperRight) {
                    boxMoving = true;
                    // setX(minigame.getWidth() - (this.getWidth()*2) - (minigame.getInTrashCan().getWidth()/2) - 32);
                }
                //Movement of guantlet in left side
            } else {

                // If the guantlet is in the initial position the player can press right to move the guantlet so it can hit the trash
                if (minigame.getKeyManager().left && !minigame.getKeyManager().helperLeft) {
                    boxMoving = true;
                    //setX((minigame.getInTrashCan().getWidth()/2) + 32 + this.getWidth());
                }

            }
        }
    }

    @Override
    public void render(Graphics g) {
        //draws image of box guantlet depending if it goes to the right or left
        if (isBoxSide()) {
            g.drawImage(Assets.rightBox, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.leftBox, getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());

    }

}
