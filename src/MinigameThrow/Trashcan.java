/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinigameThrow;

import java.awt.Graphics;
import java.awt.Rectangle;
import videogame.Assets;
import videogame.Item;

/**
 *
 * @author PatoSaldivar
 */
public class Trashcan extends Item {
    private int width;
    private int height;

    public Trashcan(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height= height;
    }

    @Override
    public void tick() {
        int randX = (int) (Math.random()*128) + 256;
        int randY = (int) (Math.random()*256) + 128;
        x=randX;
        y=randY;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.inTrashCan, x, y, width, height, null);
    }

    public Rectangle getPerimetroIn() {
       return new Rectangle(x,y,width/4,height/4);
    }
        public Rectangle getPerimetroOff() {
       return new Rectangle(x+width/4,y+height/4,width*3/4,height*3/4);
    }

    @Override
    public Rectangle getPerimetro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
