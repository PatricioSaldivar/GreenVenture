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
public class roadChange extends Item {
    private int width;
    private int height;
    private int iniX;
    private int iniY;
    private Screen screen;
    private final int possibleDirections[];
    
    roadChange(int x, int y, int width, int height, Screen screen, int possibleDirections[]){
        super(x,y);
        this.width = width;
        this.height = height;
        this.possibleDirections = possibleDirections;
        this.screen = screen;
        iniX = x;
        iniY =y;
 
        
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


    

    public int giveRandomDirection(){
       return possibleDirections[(int) (Math.random() * possibleDirections.length)];
    }
    
    @Override
    public void tick() {
        x = iniX - screen.getX();
        y = iniY - screen.getY();
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    
    @Override
    public Rectangle getPerimetro() {
         return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
        public Rectangle getPerimetroUp() {
         return new Rectangle(getX(), getY(), getWidth(), 10);
    }
            public Rectangle getPerimetroDown() {
         return new Rectangle(getX(), getY()+ height - 10, getWidth(), 10);
    }
                public Rectangle getPerimetroRight() {
         return new Rectangle(getX() + width -10, getY(), 10, getHeight());
    }
                    public Rectangle getPerimetroLeft() {
         return new Rectangle(getX(), getY(), 10, getHeight());
    }
    
}
