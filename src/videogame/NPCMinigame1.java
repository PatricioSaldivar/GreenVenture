/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author yeyog
 */
public class NPCMinigame1 extends Item {
    private int width;
    private int height;
    private Game game;
    private Screen screen;
    private int iniX;
    private int iniY;
    private int id;
    private boolean talking;
    private Animation alertAnimation;
    private Animation facing;
    private BufferedImage conversation;


public NPCMinigame1(int x, int y, int width, int height, Game game, Screen screen, int id) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.screen = screen;
        iniX=x;
        iniY=y;
        this.id = id;
        alertAnimation = new Animation(Assets.npcDialogue,300);
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

    public boolean isTalking() {
        return talking;
    }

    public void setTalking(boolean talking) {
        this.talking = talking;
    }

    public Animation getFacing() {
        return facing;
    }

    public void setFacing(Animation facing) {
        this.facing = facing;
    }

    public BufferedImage getConversation() {
        return conversation;
    }

    public void setConversation(BufferedImage conversation) {
        this.conversation = conversation;
    }

    public int getId() {
        return id;
    }
    
    
   
    @Override
    public void tick() {
        alertAnimation.tick();        
    }

    @Override
    public void render(Graphics g) {
        x= iniX-screen.getX();
        y= iniY-screen.getY();
       g.drawImage(conversation, getX(), getY(), getWidth(), getHeight(), null);
       g.drawImage(alertAnimation.getCurrentFrame(),getX(),getY()-10,25,25,null);
    }

    @Override
    public Rectangle getPerimetro() {
      return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
}
