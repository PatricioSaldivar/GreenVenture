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
 * @author Bonfire
 */
public class NPC extends Item {
private int width;
private int height;
private int trashMaker;
private int trashThrown;
private Game game;
private int type;
private Screen screen;
private int speed = 1;
private int flagUp=0;
private int flagRight=0;
private int flagDown=0;
private int flagLeft=0;
private int iniX;
private int iniY;
private int xMove=0;
private int yMove=0;
private int trashMakerHelper;
private int id;


public NPC(int x, int y, int width, int height, int type, Game game, Screen screen, int id) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.type = type;
        this.game = game;
        this.screen = screen;
        iniX=x;
        iniY=y;
        trashMakerHelper = (int) (Math.random() *(500)+ 50);
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTrashMaker() {
        return trashMaker;
    }

    public int getTrashThrown() {
        return trashThrown;
    }

    public int getType() {
        return type;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTrashMaker(int trashMaker) {
        this.trashMaker = trashMaker;
    }

    public void setTrashThrown() {
        this.trashThrown= this.trashThrown-1;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    


    @Override
    public void tick() {
        if(flagUp<150){
            yMove-=speed;
            flagUp++;
        }else if(flagRight<150){
            xMove+=speed;
            flagRight++;
        }else if(flagDown<150){
            yMove+=speed;
            flagDown++;
        }else if(flagLeft<150){
            xMove-=speed;
            flagLeft++;
        }else{
            flagUp=0;
            flagRight=0;
            flagDown=0;
            flagLeft=0;
        }
        x= iniX-screen.getX()+xMove;
        y= iniY-screen.getY()+yMove;
        
        if(trashThrown<5){
            if(trashMaker>trashMakerHelper){
            int randType = (int) (Math.random() * ((5 - 0) + 1)) + 0;
            game.getTrash().add(new Trash(x+screen.getX(), y+screen.getY(), 32, 32, randType, game, screen, id));
            trashMaker=0;
            trashThrown++;
        }
        trashMaker++;
        }
    }

    @Override
    public void render(Graphics g) {
       g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public Rectangle getPerimetro() {
      return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
}
