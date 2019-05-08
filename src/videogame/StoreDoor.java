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
public class StoreDoor extends Item {
    private int width;
    private int height;
    private Game game;
    private Screen screen;
    private int iniX;
    private int iniY;
    private int id;
    private int indexHelper;
    private int index;

public StoreDoor(int x, int y, int width, int height, Game game, Screen screen, int id) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.screen = screen;
        iniX=x;
        iniY=y;
        this.id = id;
        indexHelper = 10;
        index = 0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   
    @Override
    public void tick() {       
        x = iniX-game.getPlayer().getSMoveX();
        y = iniY-game.getPlayer().getSMoveY();
        
        //Updates index for to render animation of door opening
        if(game.getPlayer().getPerimetro().intersects(getPerimetroOpen())){
            if(index==3){
            Assets.doorOpen.play();
            Assets.doorOpen.setLooping(false);
            }
            indexHelper--;
            if(indexHelper<1 && index<3){
                index++;
                indexHelper=5;
            }
        }else{
            indexHelper--;
            if(indexHelper<1 && index>0){
                index--;
                indexHelper=5;
            }
            
        }
        
        
        
    }

    /**
     * 
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        //Renders corresponding image from the array
        g.drawImage(Assets.door[index], x, y, 77,67,null);
    }

    /**
     * To get perimeter of the rectangle that allows to enter the given store
     * @return Rectangle
     */
    @Override
    public Rectangle getPerimetro() {
      return new Rectangle(getX(), getY()-10, getWidth(), 12);
    }
    /**
     * To get perimeter of rectangle that activates animation of door
     * @return Rectangle
     */
    public Rectangle getPerimetroOpen() {
      return new Rectangle(getX(), getY(), getWidth(), 57);
    }
    
    
    
    
}
