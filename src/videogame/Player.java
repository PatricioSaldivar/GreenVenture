/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 *
 * @author BonfireStudios
 */
public class Player extends Item {

    private int width;
    private int height;
    private Game game;
    private int SMoveX = 0;
    private int SMoveY = 0;
    private double money = 20.00;
    private int speed =5;
    private int capacity = 10;
    private int inventory = 0;
    private boolean pick = false;
    private boolean conversation = false;
    private boolean talking = false;
    private int glass = 0;
    private int electronics = 0;
    private int organic = 0;
    private int paper = 0;
    private int plastic = 0;
    private int aluminum = 0;
    private int trashUpgrade = 1;                // to save the level of trash price upgrade
    private double speedUpgrade = 0;            // to save the level of speed upgrade
    private double capacityUpgrade = 0;         // to save the level of the capacity upgrade
    private Animation animationUp;
    private Animation animationDown;
    private Animation animationRight;
    private Animation animationLeft;
    private int direction; // 1 = Down , 2 = Up, 3 = Right, 4 = Left
    


    public Player(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        //The speed of the animation need to be changed depending of the player speed
        this.animationUp = new Animation(Assets.playerUp,300);
        this.animationDown = new Animation(Assets.playerDown,300);
        this.animationRight = new Animation(Assets.playerRight,300);
        this.animationLeft = new Animation(Assets.playerLeft,300);
        this.direction = 1;
    }

    /**
     * To get the size of the inventory that has the player
     *
     * @return an <code>int</code> value with the inventory
     */
    public int getInventory() {
        return inventory;
    }

    /**
     * To get the width of the player
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To set the camara movement in x
     *
     * @param <code>SMoveX</code> value with SMovex
     */
    public void setSMoveX(int SMoveX) {
        this.SMoveX = SMoveX;
    }

    /**
     * To set the camara movement in y
     *
     * @param <code>SMoveY</code> value with SMoveY
     */
    public void setSMoveY(int SMoveY) {
        this.SMoveY = SMoveY;
    }

    /**
     * To set the money of the player
     *
     * @param <code>money</code> value with the money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * To set the speed of the player
     *
     * @param <code>speed</code> value with speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }


    /**
     * To get the money
     *
     * @return an <code>int</code> value with the money
     */
    public double getMoney() {
        return money;
    }

    /**
     * To get the speed of the player
     *
     * @return an <code>int</code> value with the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * To get the capacity
     *
     * @return an <code>int</code> value with the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * To get the height of the player
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To set the width of the player
     *
     * @param width <code>int </code> value with width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set the height of the player
     *
     * @param height <code>int</code> value with height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * To get the x coordinate of the camara
     *
     * @return an <code>int</code> value with the x coordinate of the camara
     */
    public int getSMoveX() {
        return SMoveX;
    }

    /**
     * To get the y coordinate of the camara
     *
     * @return an <code>int</code> value with the y coordinate of the camara
     */
    public int getSMoveY() {
        return SMoveY;
    }

    public boolean isPick() {
        return pick;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setPick(boolean pick) {
        this.pick = pick;
    }

    public void setTalking(boolean talking) {
        this.talking = talking;
    }

    public boolean isConversation() {
        return conversation;
    }

    public void setConversation(boolean conversation) {
        this.conversation = conversation;
    }

    public boolean isTalking() {
        return talking;
    }

    public int getGlass() {
        return glass;
    }

    public void setGlass(int glass) {
        this.glass = glass;
    }

    public int getElectronics() {
        return electronics;
    }

    public void setElectronics(int electronics) {
        this.electronics = electronics;
    }

    public int getOrganic() {
        return organic;
    }

    public void setOrganic(int organic) {
        this.organic = organic;
    }

    public int getPaper() {
        return paper;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    public int getPlastic() {
        return plastic;
    }

    public void setPlastic(int plastic) {
        this.plastic = plastic;
    }

    public int getAluminum() {
        return aluminum;
    }

    public void setAluminum(int aluminum) {
        this.aluminum = aluminum;
    }

    public int getTrashUpgrade() {
        return trashUpgrade;
    }

    public void setTrashUpgrade(int trashUpgrade) {
        this.trashUpgrade = trashUpgrade;
    }

    public double getSpeedUpgrade() {
        return speedUpgrade;
    }

    public void setSpeedUpgrade(double speedUpgrade) {
        this.speedUpgrade = speedUpgrade;
    }

    public double getCapacityUpgrade() {
        return capacityUpgrade;
    }

    public void setCapacityUpgrade(double capacityUpgrade) {
        this.capacityUpgrade = capacityUpgrade;
    }
    
    

    

    @Override
    public void tick() {
        // moving player depending on flags
        this.animationDown.tick();
        this.animationUp.tick();
        this.animationRight.tick();
        this.animationLeft.tick();
        //Also moves the camara coordinates depending on flags
        pick = game.getKeyManager().space && !game.getKeyManager().helperSpace;
        if (!talking && !conversation) {
            if (game.getKeyManager().up) {
                direction = 2;
                if (getY() > game.getHeight() / 2 - height / 2) {
                    if (getY() - speed <= game.getHeight() / 2 - height / 2) {
                        setY(game.getHeight() / 2 - height / 2);
                    } else {
                        setY(getY() - speed);
                    }
                } else {
                    if (SMoveY <= 0) {
                        setY(getY() - speed);
                        SMoveY = 0;
                    } else {
                        SMoveY = SMoveY - speed;
                    }
                }

            }

            if (game.getKeyManager().down) {
                direction = 1;
                if (getY() < game.getHeight() / 2 - height / 2) {
                    if (getY() + speed >= game.getHeight() / 2 - height / 2) {
                        setY(game.getHeight() / 2 - height / 2);
                    } else {
                        setY((getY() + speed));
                    }
                } else {
                    if (SMoveY + speed >= Assets.background.getHeight() * 7 / 8) {
                        SMoveY = (Assets.background.getHeight() * 7 / 8);
                        setY(getY() + speed);
                    } else {
                        SMoveY = SMoveY + speed;
                    }
                }
            }

            if (game.getKeyManager().left) {
                direction = 4;
                if (getX() > game.getWidth() / 2 - width / 2) {
                    if (getX() - speed <= game.getWidth() / 2 - width / 2) {
                        setX(game.getWidth() / 2 - width / 2);
                    } else {
                        setX(getX() - speed);
                    }
                } else {
                    if (SMoveX <= 0) {
                        setX(getX() - speed);
                        SMoveX = 0;
                    } else {
                        SMoveX = SMoveX - speed;
                    }
                }

            }
            if (game.getKeyManager().right) {
                direction = 3;
                if (getX() < game.getWidth() / 2 - width / 2) {
                    if (getX() + speed >= game.getWidth() / 2 - width / 2) {
                        setX(game.getWidth() / 2 - width / 2);
                    } else {
                        setX(getX() + speed);
                    }
                } else {
                    if (SMoveX + speed >= Assets.background.getWidth() * 7 / 8) {
                        SMoveX = (Assets.background.getWidth() * 7 / 8);
                        setX(getX() + speed);
                    } else {
                        SMoveX = SMoveX + speed;
                    }
                }
            }
            // reset x position and y position if colision
            if (getX() + 64 >= game.getWidth()) {
                setX(game.getWidth() - 64);
            } else if (getX() <= 0) {
                setX(0);
            }
            //if (getY() + 64 >= game.getHeight()) {
             //   setY(game.getHeight() - 64);
            //} else if (getY() <= 0) {
             //   setY(0);
            //}

            for (int i = 0; i < game.getSolids().size(); i++) {
                //Checks collisions with solids when going from left to right
                if (getPerimetroForSolidsRight().intersects(game.getSolids().get(i).getPerimetroRight(SMoveX, SMoveY))) {
                    if (x == game.getWidth() / 2 - width / 2) {
                        SMoveX = game.getSolids().get(i).x - game.getWidth() / 2 - width / 2;
                    } else {
                        x = game.getSolids().get(i).x -64 - SMoveX;
                    }
                }
                //Checks collisions with solids when going from righ to left
                if (getPerimetroForSolidsLeft().intersects(game.getSolids().get(i).getPerimetroLeft(SMoveX, SMoveY))) {
                    if (x == game.getWidth() / 2 - width / 2) {
                        SMoveX = game.getSolids().get(i).x + game.getSolids().get(i).getWidth() - game.getWidth() / 2 + width / 2;
                    } else {
                        x = game.getSolids().get(i).x + game.getSolids().get(i).getWidth() - SMoveX;
                    }
                }
                //Checks collisions with solids when going from top to down
                if (getPerimetroForSolidsUp().intersects(game.getSolids().get(i).getPerimetroUp(SMoveX, SMoveY))) {
                    if (y == game.getHeight() / 2 - height / 2) {
                        SMoveY = game.getSolids().get(i).y - game.getHeight() / 2 - height / 2;
                    } else {
                        y = game.getSolids().get(i).y- 64- SMoveY;
                    }
                }
                //Checks collisions with solids when going from down to top
                if (getPerimetroForSolidsDown().intersects(game.getSolids().get(i).getPerimetroDown(SMoveX, SMoveY))) {
                    if (y == game.getHeight() / 2 - height / 2) {
                        SMoveY = game.getSolids().get(i).y + game.getSolids().get(i).getHeight() - game.getHeight() / 2 + height / 2;
                    } else {
                        y = game.getSolids().get(i).y + game.getSolids().get(i).getHeight()- SMoveY;
                    }
                }

            }
            
            for(int i=0; i<game.getCrosswalks().size(); i++){
                //Checks collisions with solids when going from top to down
                if (getPerimetroForSolidsUp().intersects(game.getCrosswalks().get(i).getPerimetroUp(SMoveX, SMoveY)) && game.getCrosswalks().get(i).isCarIn()) {
                    if (y == game.getHeight() / 2 - height / 2) {
                        SMoveY = game.getCrosswalks().get(i).getIniY() - game.getHeight() / 2 - height / 2;
                    } else {
                         y = game.getCrosswalks().get(i).y- 64- SMoveY;
                    }
                }
                //Checks collisions with solids when going from down to top
                if (getPerimetroForSolidsDown().intersects(game.getCrosswalks().get(i).getPerimetroDown(SMoveX, SMoveY))&& game.getCrosswalks().get(i).isCarIn()) {
                    if (y == game.getHeight() / 2 - height / 2) {
                        SMoveY = game.getCrosswalks().get(i).getIniY() + game.getCrosswalks().get(i).getHeight() - game.getHeight() / 2 + height / 2 ;
                    } else {
                        y = game.getCrosswalks().get(i).y + game.getSolids().get(i).getHeight()- SMoveY;                   }
                }
                 if (getPerimetroForSolidsRight().intersects(game.getCrosswalks().get(i).getPerimetroRight(SMoveX, SMoveY))&& game.getCrosswalks().get(i).isCarIn()) {
                    if (x == game.getWidth() / 2 - width / 2) {
                        SMoveX = game.getCrosswalks().get(i).getIniX() - game.getWidth() / 2 - width / 2;
                    } else {
                        x = game.getCrosswalks().get(i).getIniX() -64 - SMoveX;
                    }
                }
                //Checks collisions with solids when going from righ to left
                if (getPerimetroForSolidsLeft().intersects(game.getCrosswalks().get(i).getPerimetroLeft(SMoveX, SMoveY))&& game.getCrosswalks().get(i).isCarIn()) {
                    if (x == game.getWidth() / 2 - width / 2) {
                        SMoveX = game.getCrosswalks().get(i).getIniX() + game.getCrosswalks().get(i).getWidth() - game.getWidth() / 2 + width / 2;
                    } else {
                        x = game.getCrosswalks().get(i).getIniX() + game.getCrosswalks().get(i).getWidth() - SMoveX;
                    }
                }
            }
                  
        }
        
    }

    @Override
    public void render(Graphics g) {
        //Render down animation
        if(direction == 1 && game.getKeyManager().down && !conversation){
            g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        
        //Render down static
        }else if(direction == 1){
            g.drawImage(Assets.playerFacingDown, getX(), getY(), getWidth(), getHeight(), null);
        }
         //Render up animation
        if(direction == 2  && game.getKeyManager().up && !conversation){
            g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        
        //Render up static
    }else if(direction == 2 ){
            g.drawImage(Assets.playerFacingUp, getX(), getY(), getWidth(), getHeight(), null);
        }
         //Render right animation
        if(direction == 3  && game.getKeyManager().right && !conversation){
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        //Render right static
        }else if(direction == 3){
            g.drawImage(Assets.playerFacingRight, getX(), getY(), getWidth(), getHeight(), null);
        }
         //Render left animation
        if(direction == 4 && game.getKeyManager().left && !conversation){
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        
        //Render left static
        }else if(direction == 4){
            g.drawImage(Assets.playerFacingLeft, getX(), getY(), getWidth(), getHeight(), null);
        }

    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());

    }

    public Rectangle getPerimetroForSolidsDown() {
        return new Rectangle(getX(), getY(), getWidth(), speed);

    }

    public Rectangle getPerimetroForSolidsUp() {
        return new Rectangle(getX(), getY()+getHeight()-speed, getWidth(),speed);

    }

    public Rectangle getPerimetroForSolidsRight() {
        return new Rectangle(getX()+ getWidth()-speed, getY(), speed, getHeight());

    }

    public Rectangle getPerimetroForSolidsLeft() {
        return new Rectangle(getX(), getY(), speed, getHeight());

    }

    public boolean intersectsTrash(Trash t) {
        return getPerimetro().intersects(t.getPerimetro());
    }
}
