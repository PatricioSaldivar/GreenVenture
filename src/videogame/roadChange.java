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
public class roadChange extends Solid {
    private final int width;
    private final int height;
    private Screen screen;
    
    private final int possibleDirections[];
    
    roadChange(int x, int y, int width, int height, Screen screen, int possibleDirections[]){
        super(x,y,width,height,screen);
        this.width = width;
        this.height = height;
        this.possibleDirections = possibleDirections;
 
    }

    public int giveRandomDirection(){
       return possibleDirections[(int) (Math.random() * possibleDirections.length)];
    }
    
    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public Rectangle getPerimetro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
