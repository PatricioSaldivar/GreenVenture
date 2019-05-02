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
    private Screen screen;
    private final int iniX;
    private final int iniY;
    public boolean dead=false;
    private int npcId;
            

    public Trash(int x, int y, int width, int height, int type, Game game, Screen screen, int npcId) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.type = type;
        this.game = game;
        this.screen = screen;
        this.npcId = npcId;
        iniX=x;
        iniY=y;
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

    public boolean isDead() {
        return dead;
    }

    public int getNpcId() {
        return npcId;
    }
    

    @Override
    public void tick() {
        x= iniX-screen.getX();
        y= iniY-screen.getY();
        
        if(game.getPlayer().intersectsTrash(this) && game.getPlayer().isPick()) {
            if(game.getPlayer().getCapacity()>game.getPlayer().getInventory()){
                game.getPlayer().setInventory(game.getPlayer().getInventory()+1);
                dead=true;
            } else {
                Assets.pickDenied.play();
            }
        }

          
    }

    public void render(Graphics g) {
        g.drawImage(Assets.trash[getType()], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
}
