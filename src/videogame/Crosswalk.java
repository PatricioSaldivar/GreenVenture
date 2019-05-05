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
    public Crosswalk(int x, int y, int width, int height, Game game, Screen screen) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.screen = screen;
        iniX = x;
        iniY = y;
        
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

    public boolean isSomeoneIn() {
        return someoneIn;
    }
    

    @Override
    public void tick() {
        someoneIn=false;
        x = iniX - screen.getX();
        y = iniY - screen.getY();
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
    
    
}
