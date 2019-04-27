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
public class TrashCan extends Item {
    private int width;
    private int height;
    private boolean trashCanType;   // True = Organic Trash Can, Flase = Inorganic Trash Can
    private MinigameTrashClassify minigame;
   
     public TrashCan(int x, int y, int width, int height, boolean trashCanType, MinigameTrashClassify minigame) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.minigame = minigame;
        this.trashCanType = trashCanType;
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

    public boolean isTrashCanType() {
        return trashCanType;
    }

    public void setTrashCanType(boolean trashCanType) {
        this.trashCanType = trashCanType;
    }
    
    @Override
    public void tick() {
        
    }
    
    @Override
    public void render(Graphics g) {
        //draws image of trash can depending if it is organic or inorganic
        if(isTrashCanType()){
            g.drawImage(Assets.orTrashCan, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.inTrashCan, getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());

    }
     
    
    
}
