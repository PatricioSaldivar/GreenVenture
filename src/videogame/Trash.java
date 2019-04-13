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
 * @author eugenio
 */
public class Trash extends Item{
    
    private int width;
    private int height;
    private Game game;
    private int type;

    public Trash(int x, int y, int width, int height, int type, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.type = type;
        this.game = game;
    }
    
    public int getX(){
       return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getType(){
        return type;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.trash[getType()], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
}
