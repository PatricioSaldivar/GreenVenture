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

public StoreDoor(int x, int y, int width, int height, Game game, Screen screen, int id) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.screen = screen;
        iniX=x;
        iniY=y;
        this.id = id;
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
        x = iniX-screen.getX();
        y = iniY-screen.getY();
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getPerimetro() {
      return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    
}
