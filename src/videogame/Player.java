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
    private double money = 00.00;
    private int speed = 5;
    private int speedCamara = 5;
    private int capacity = 10;
    private int inventory = 0;
    private boolean pick = false;
    private boolean conversation = false;
    private boolean talking = false;
    private int previousX;
    private int previousY;
    private int previousSMoveX;
    private int previousSMoveY;

    public Player(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
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
     * To set the camara movement speed
     *
     * @param <code>SpeedCamara</code> value with speed of camara
     */
    public void setSpeedCamara(int speedCamara) {
        this.speedCamara = speedCamara;
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
     * @param <code>width</code> value with width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set the height of the player
     *
     * @param <code>height</code> value with height
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

    @Override
    public void tick() {
        // moving player depending on flags
        //Also moves the camara coordinates depending on flags
        pick = game.getKeyManager().space && !game.getKeyManager().helperSpace;
        if (!talking && !conversation) {
            previousX = x;
            previousY = y;
            previousSMoveX = SMoveX;
            previousSMoveY = SMoveY;
            if (game.getKeyManager().up) {
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
                        SMoveY = SMoveY - speedCamara;
                    }
                }

            }

            if (game.getKeyManager().down) {
                if (getY() < game.getHeight() / 2 - height / 2) {
                    if (getY() + speed >= game.getHeight() / 2 - height / 2) {
                        setY(game.getHeight() / 2 - height / 2);
                    } else {
                        setY((getY() + speed));
                    }
                } else {
                    if (SMoveY + speedCamara >= Assets.background.getHeight() * 3 / 4) {
                        SMoveY = (Assets.background.getHeight() * 3 / 4);
                        setY(getY() + speed);
                    } else {
                        SMoveY = SMoveY + speedCamara;
                    }
                }
            }

            if (game.getKeyManager().left) {
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
                        SMoveX = SMoveX - speedCamara;
                    }
                }

            }
            if (game.getKeyManager().right) {
                if (getX() < game.getWidth() / 2 - width / 2) {
                    if (getX() + speed >= game.getWidth() / 2 - width / 2) {
                        setX(game.getWidth() / 2 - width / 2);
                    } else {
                        setX(getX() + speed);
                    }
                } else {
                    if (SMoveX + speedCamara >= Assets.background.getWidth() * 3 / 4) {
                        SMoveX = (Assets.background.getWidth() * 3 / 4);
                        setX(getX() + speed);
                    } else {
                        SMoveX = SMoveX + speedCamara;
                    }
                }
            }
            // reset x position and y position if colision
            if (getX() + 64 >= game.getWidth()) {
                setX(game.getWidth() - 64);
            } else if (getX() <= 0) {
                setX(0);
            }
            if (getY() + 64 >= game.getHeight()) {
                setY(game.getHeight() - 64);
            } else if (getY() <= 0) {
                setY(0);
            }

            for (int i = 0; i < game.getSolids().size(); i++) {
                //Checks collisions with solids when going from left to right
                if (getPerimetroForSolidsRight().intersects(game.getSolids().get(i).getPerimetroRight(SMoveX, SMoveY))) {
                    if (x == game.getWidth() / 2 - width / 2) {
                        SMoveX = game.getSolids().get(i).x - game.getWidth() / 2 - width / 2;
                    } else {
                        x = game.getSolids().get(i).x - width;
                    }
                }
                //Checks collisions with solids when going from righ to left
                if (getPerimetroForSolidsLeft().intersects(game.getSolids().get(i).getPerimetroLeft(SMoveX, SMoveY))) {
                    if (x == game.getWidth() / 2 - width / 2) {
                        SMoveX = game.getSolids().get(i).x + game.getSolids().get(i).getWidth() - game.getWidth() / 2 + width / 2;
                    } else {
                        x = game.getSolids().get(i).x + game.getSolids().get(i).getWidth();
                    }
                }
                //Checks collisions with solids when going from top to down
                if (getPerimetroForSolidsUp().intersects(game.getSolids().get(i).getPerimetroUp(SMoveX, SMoveY))) {
                    if (y == game.getHeight() / 2 - height / 2) {
                        SMoveY = game.getSolids().get(i).y - game.getHeight() / 2 - height / 2;
                    } else {
                        y = game.getSolids().get(i).y - height;
                    }
                }
                //Checks collisions with solids when going from down to top
                if (getPerimetroForSolidsDown().intersects(game.getSolids().get(i).getPerimetroDown(SMoveX, SMoveY))) {
                    if (y == game.getHeight() / 2 - height / 2) {
                        SMoveY = game.getSolids().get(i).y + game.getSolids().get(i).getHeight() - game.getHeight() / 2 + height / 2;
                    } else {
                        y = game.getSolids().get(i).y + game.getSolids().get(i).getHeight();
                    }
                }

            }
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
