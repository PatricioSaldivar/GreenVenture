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

    private int xMove = 0;
    private int yMove = 0;
    private int iniX;
    private int iniY;
    private int speed = 10;
    private Screen screen;
    private Game game;
    private int direction;
    private int alreadyChecked = -1;

    Car(int x, int y, int width, int height, Screen screen, Game game) {
        super(x, y, width, height);
        this.game = game;
        iniX = x;
        iniY = y;
        this.screen = screen;

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getSMoveX() {
        return xMove;
    }

    public void setSMoveX(int SMoveX) {
        this.xMove = SMoveX;
    }

    public int getSMoveY() {
        return yMove;
    }

    public void setSMoveY(int SMoveY) {
        this.yMove = SMoveY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setAlreadyChecked(int alreadyChecked) {
        this.alreadyChecked = alreadyChecked;
    }

    public int getxMove() {
        return xMove;
    }

    public void setxMove(int xMove) {
        this.xMove = xMove;
    }

    public int getyMove() {
        return yMove;
    }

    public void setyMove(int yMove) {
        this.yMove = yMove;
    }

    public int getIniX() {
        return iniX;
    }

    public void setIniX(int iniX) {
        this.iniX = iniX;
    }

    public int getIniY() {
        return iniY;
    }

    public void setIniY(int iniY) {
        this.iniY = iniY;
    }

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
        g.drawImage(Assets.inTrashCan, getX(), getY(), getWidth(), getHeight(), null);

    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

}
