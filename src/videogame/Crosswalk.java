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
    private Game game;
    private boolean someoneIn = false;
    public Crosswalk(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
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

    @Override
    public void tick() {
        someoneIn=false;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getPerimetro() {
         return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    
}
