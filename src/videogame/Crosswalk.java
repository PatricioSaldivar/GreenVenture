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
 * @author PatoSaldivar
 */
public class Crosswalk extends Item{
    private int width;
    private int height;
    private final Game game;
    private boolean someoneIn = false;
    private final int iniX;
    private final int iniY;
    private Screen screen;
    private boolean carIn = false;
    public Crosswalk(int x, int y, int width, int height, Game game, Screen screen) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.screen = screen;
        iniX = x;
        iniY = y;
        
    }
    /**
     * 
     * @return width 
     */
    public int getWidth() {
        return width;
    }
    /**
     * 
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * 
     * @return height
     */
    public int getHeight() {
        return height;
    }
    /**
     * 
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * 
     * @return someoneIn
     */
    public boolean isSomeoneIn() {
        return someoneIn;
    }

    public boolean isCarIn() {
        return carIn;
    }

    public void setCarIn(boolean carIn) {
        this.carIn = carIn;
    }

    public int getIniX() {
        return iniX;
    }

    public int getIniY() {
        return iniY;
    }
    
    
    

    @Override
    public void tick() {
        someoneIn=false;
        carIn=false;
        x = iniX - screen.getX();
        y = iniY - screen.getY();
        if(game.getCar().getPerimetro().intersects(getPerimetro())){
            carIn=true;
        }
        
       for(int i=0; i<game.getNpcs().size(); i++){
          if(getPerimetro().intersects(game.getNpcs().get(i).getPerimetro())){
                    someoneIn = true;
                  }
       }
            if(getPerimetro().intersects(game.getPlayer().getPerimetro())){
                someoneIn = true;
           }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getPerimetro() {
         return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
        public Rectangle getPerimetroLeft(int posX, int posY) {
        return new Rectangle(iniX-posX+width-1,iniY-posY,1,height);
    }
        public Rectangle getPerimetroRight(int posX, int posY) {
        return new Rectangle(iniX-posX,iniY-posY,1,height);
    }
            public Rectangle getPerimetroDown(int posX, int posY) {
        return new Rectangle(iniX-posX,iniY-posY+height-1,width,1);
    }
                public Rectangle getPerimetroUp(int posX, int posY) {
        return new Rectangle(iniX-posX,iniY-posY,width,1);
    }
    
    
}
