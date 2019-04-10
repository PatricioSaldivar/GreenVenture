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
 * @author antoniomejorado
 */
public class Player extends Item {

    private int width;
    private int height;
    private Game game;
    private int SMoveX = 0;
    private int SMoveY = 0;
    private double money = 1000.10;
    private int speed = 5;
    private int speedCamara = 10;
    private int capacity = 100;
    private int inventory = 100;

    public Player(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
    }

    public int getInventory() {
        return inventory;
    }

    public int getWidth() {
        return width;
    }

    public void setSMoveX(int SMoveX) {
        this.SMoveX = SMoveX;
    }

    public void setSMoveY(int SMoveY) {
        this.SMoveY = SMoveY;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSpeedCamara(int speedCamara) {
        this.speedCamara = speedCamara;
    }

    public double getMoney() {
        return money;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCapacity() {
        return capacity;
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

    public int getSMoveX() {
        return SMoveX;
    }

    public int getSMoveY() {
        return SMoveY;
    }

    @Override
    public void tick() {
        // moving player depending on flags
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
                    setY(getY() + speed);
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
