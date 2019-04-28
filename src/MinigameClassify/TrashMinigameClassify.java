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
   
     public TrashMinigameClassify(int x, int y, int width, int height, int speed, boolean trashType, MinigameTrashClassify minigame) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.minigame = minigame;
        this.speed = speed;
        this.trashType = trashType;
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
    
    
    
    
    
     
    @Override
    public void tick() {
        
           // change direction of x position and y position if colision         
//            if (getY()+getHeight() > minigame.getHeight()) {
                
// } 
      
        setY(getY() + getSpeed());
        
    }
    
    @Override
    public void render(Graphics g) {
        //draws image of trash can depending if it is organic or inorganic
        if(this.isTrashType()){
            g.drawImage(Assets.trash[1], getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.trash[2], getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());

    }
      
     
     
     
    
}
