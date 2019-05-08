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
public class Car extends Solid {

    private int xMove = 0;              // store xmove
    private int yMove = 0;              // store ymove
    private int iniX;                   // store iniX
    private int iniY;                   // store iniY
    private int speed = 10;             // store speed
    private Screen screen;              // store scren
    private Game game;                  // store game
    private int direction;              // store direction
    private int alreadyChecked = -1;    // store alreakdy checed
    private Animation up;               // store animation
    private Animation down;             // store animation
    private Animation right;            // store animation
    private Animation left;             // store animation
    /**
     * 
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param screen
     * @param game 
     */
    Car(int x, int y, int width, int height, Screen screen, Game game) {
        super(x, y, width, height);
        this.game = game;
        iniX = x;
        iniY = y;
        this.screen = screen;
        up = new Animation(Assets.carUp,100);
        down = new Animation(Assets.carDown,100);
        right = new Animation(Assets.carRight,100);
        left = new Animation(Assets.carLeft,100);

    }
    /**
     *Gets the game 
     *
     * @return game and <code>game</code> 
     */
    public Game getGame() {
        return game;
    }
    /**
     * Set the game
     * 
     * @param game <code>game</code>
     */
    public void setGame(Game game) {
        this.game = game;
    }
    /**
    * Get the xMove
    * 
    * @return xMove and <code>int</code>
    */
    public int getSMoveX() {
        return xMove;
    }
    /**
     * set SMoveX
     * 
     * @param SMoveX <code>int</code> 
     */
    public void setSMoveX(int SMoveX) {
        this.xMove = SMoveX;
    }
    /**
     * Gets the SMoveY
     * @return yMove and <code>int</code>
     */
    public int getSMoveY() {
        return yMove;
    }
    /**
     * Sets the sMoveY 
     * @param SMoveY <code>int</code>
     */
    public void setSMoveY(int SMoveY) {
        this.yMove = SMoveY;
    }
    /**
     * Get the direction
     * 
     * @return direction and <code>int</code> 
     */
    public int getDirection() {
        return direction;
    }
    /**
     * Set the direction
     * @param direction <code>int</code>
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    /**
     * Set the alreadyChecked
     * 
     * @param alreadyChecked <code>int</int>
     */
    public void setAlreadyChecked(int alreadyChecked) {
        this.alreadyChecked = alreadyChecked;
    }
    /**
     * Get the xMove
     * 
     * @return xMove and <code>int</code> 
     */
    public int getxMove() {
        return xMove;
    }
    /**
     * Set the xMove
     * 
     * @param xMove<code>int</code>
     */
    public void setxMove(int xMove) {
        this.xMove = xMove;
    }
    /**
     * 
     * 
     * @return yMove
     */
    public int getyMove() {
        return yMove;
    }
    /**
     * 
     * 
     * @param yMove 
     */
    public void setyMove(int yMove) {
        this.yMove = yMove;
    }
    /**
     * 
     * 
     * @return iniX 
     */
    public int getIniX() {
        return iniX;
    }
    /**
     * 
     * @param iniX 
     */
    public void setIniX(int iniX) {
        this.iniX = iniX;
    }
    /**
     * 
     * @return iniY 
     */
    public int getIniY() {
        return iniY;
    }
    /**
     * 
     * @param iniY 
     */
    public void setIniY(int iniY) {
        this.iniY = iniY;
    }
    /**
     * 
     * 
     * @return  alreadyChecked
     */
    public int getAlreadyChecked() {
        return alreadyChecked;
    }
    
    
    @Override
    public void tick() {
        //Movement in roads
        switch (direction) {
            case 1:
                yMove -= speed;
                break;
            case 2:
                yMove += speed;
                break;
            case 3:
                xMove -= speed;
                break;
            case 4:
                xMove += speed;
                break;
        }
        //Movement with the screen
        x = iniX - screen.getX() + xMove;
        y = iniY - screen.getY() + yMove;

        //Checks if theres a person on the Crosswalk
        for (int i = 0; i < game.getCrosswalks().size(); i++) {
            if (getPerimetro().intersects(game.getCrosswalks().get(i).getPerimetro()) && game.getCrosswalks().get(i).isSomeoneIn()) {

                switch (direction) {
                    case 1:
                        y += speed;
                        yMove += speed;
                        break;
                    case 2:
                        y -= speed;
                        yMove -= speed;
                        break;
                    case 3:
                        x += speed;
                        xMove += speed;
                        break;
                    case 4:
                        x -= speed;
                        xMove -= speed;
                        break;
                }

            }

        }
        for (int i = 0; i < game.getRoadChanges().size(); i++) {
             if (i != alreadyChecked) {
                switch (direction) {
                    case 1:
                        if (getPerimetro().intersects(game.getRoadChanges().get(i).getPerimetroUp())) {
                            iniX = game.getRoadChanges().get(i).getX() + 10 + screen.getX();
                            iniY = game.getRoadChanges().get(i).getY() + 10 + screen.getY();
                            xMove = 0;
                            yMove = 0;
                            direction = game.getRoadChanges().get(i).giveRandomDirection();
                            alreadyChecked = i;
                        }
                        break;
                    case 2:
                        if (getPerimetro().intersects(game.getRoadChanges().get(i).getPerimetroDown())) {
                            iniX = game.getRoadChanges().get(i).getX() + 10 + screen.getX();
                            iniY = game.getRoadChanges().get(i).getY() + 10 + screen.getY();
                            xMove = 0;
                            yMove = 0;
                            direction = game.getRoadChanges().get(i).giveRandomDirection();
                            alreadyChecked = i;
                        }

                        break;
                    case 3:
                        if (getPerimetro().intersects(game.getRoadChanges().get(i).getPerimetroLeft())) {
                            iniX = game.getRoadChanges().get(i).getX() + 10 + screen.getX();
                            iniY = game.getRoadChanges().get(i).getY() + 10 + screen.getY();
                            xMove = 0;
                            yMove = 0;
                            direction = game.getRoadChanges().get(i).giveRandomDirection();
                            alreadyChecked = i;
                        }
                        break;
                    case 4:
                        if (getPerimetro().intersects(game.getRoadChanges().get(i).getPerimetroRight())) {
                            iniX = game.getRoadChanges().get(i).getX() + 10 + screen.getX();
                            iniY = game.getRoadChanges().get(i).getY() + 10 + screen.getY();
                            xMove = 0;
                            yMove = 0;
                            direction = game.getRoadChanges().get(i).giveRandomDirection();
                            alreadyChecked = i;
                        }
                        break;
                }
            }
        }

        if (iniX + xMove > 4224 || iniX + xMove < -160 || iniY + yMove > 4224 || iniY + yMove < -160) {
            xMove = 0;
            yMove = 0;

            int next;
            next = (int) (Math.random() * 3);
            switch (next) {
                case 0:
                    direction = 3;
                    iniX = 4096;
                    iniY = 320;
                    break;

                case 1:
                    direction = 4;
                    iniX = -128;
                    iniY = 1472;
                    break;

                case 2:
                    direction = 3;
                    iniX = 4096;
                    iniY = 3968;
                    break;
            }

        }

    }

    @Override
    public void render(Graphics g) {
        x = iniX - screen.getX() + xMove;
        y = iniY - screen.getY() + yMove;
        //g.drawImage(Assets.inTrashCan, getX(), getY(), getWidth(), getHeight(), null);
        switch(direction){
            case 1:
                g.drawImage(up.getCurrentFrame(),getX(),getY(),getWidth(),getHeight(),null);
                break;
            case 2:
                g.drawImage(down.getCurrentFrame(),getX(),getY(),getWidth(),getHeight(),null);
                break;
            case 3:
                g.drawImage(left.getCurrentFrame(),getX(),getY(),getWidth(),getHeight(),null);
                break;
            case 4:
                g.drawImage(right.getCurrentFrame(),getX(),getY(),getWidth(),getHeight(),null);
                break;
        }

    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

}
