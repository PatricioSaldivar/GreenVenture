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
public class TrashMinigameClassify extends Item {

    private int width;
    private int height;
    private int speed;
    private boolean trashType;   // True = Organic Trash, Flase = Inorganic Trash
    private MinigameTrashClassify minigame;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean moved = false;
    private int imageIndex;     // to store the index of the image that will be choose in a random way

    public TrashMinigameClassify(int x, int y, int width, int height, int speed, boolean trashType, MinigameTrashClassify minigame) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.minigame = minigame;
        this.speed = speed;
        this.trashType = trashType;
        //Random index generator depending of the trash type
        if(trashType){
            imageIndex = (int)(Math.random() * 8);
        } else {
            imageIndex = (int)(Math.random() * 18);
        }
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isTrashType() {
        return trashType;
    }

    public void setTrashType(boolean trashType) {
        this.trashType = trashType;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMoved() {
        return moved;
    }
    
    

    @Override
    public void tick() {
        // change direction of x position and y position if colision         
        //IF the trash hasnt been moved from the middle
        if (!moved) {
            //IF it hasnt compelte the moving process but it is moving to the left or right its position will move according to where its moving
            if ((movingLeft || movingRight)) {
                if (movingLeft) {
                    setX(getX() - 20);
                } else {
                    setX(getX() + 20);
                }
                //IF it hasnt been moved or is being move the trash will move down
            }else {
            setY(getY() + getSpeed());
        }
                //If its X has passed its position it will reposition to its final point
            if (getX() > minigame.getWidth() - 128 - width / 2) {
                moved = true;
                setX(352);
            } else if (getX() <= 96) {
                moved = true;
                setX(96);
            }
            //If the trash has been moved it will only move down
        } else {
            setY(getY() + getSpeed());
        }
    }

    @Override
    public void render(Graphics g) {
        //draws image of trash can depending if it is organic or inorganic
        int i;
 
        if (this.isTrashType()) {
            g.drawImage(Assets.orTrash[imageIndex], getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.inTrash[imageIndex], getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());

    }

}
