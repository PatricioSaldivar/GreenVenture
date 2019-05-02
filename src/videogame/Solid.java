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
public class Solid extends Item{
    private int width;
    private int height;
    private Screen screen;
    public Solid(int x, int y, int width, int height, Screen screen){
        super(x,y);
        this.width = width;
        this.height = height;
        this.screen = screen;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        
    }

    public Rectangle getPerimetroLeft(int posX, int posY) {
        return new Rectangle(x-posX+width-1,y-posY,1,height);
    }
        public Rectangle getPerimetroRight(int posX, int posY) {
        return new Rectangle(x-posX,y-posY,1,height);
    }
            public Rectangle getPerimetroDown(int posX, int posY) {
        return new Rectangle(x-posX,y-posY+height-1,width,1);
    }
                public Rectangle getPerimetroUp(int posX, int posY) {
        return new Rectangle(x-posX,y-posY,width,1);
    }

    @Override
    public Rectangle getPerimetro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
