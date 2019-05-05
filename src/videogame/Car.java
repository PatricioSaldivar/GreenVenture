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
public class Car extends Item {

    private int x;
    private int y;
    private int SMoveX;
    private int SMoveY;
    private int iniX;
    private int iniY;
    private int width;
    private int height;
    private Game game;
    private int direction;
    private boolean destroy;

    Car(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        iniX = x;
        iniY = y;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getSMoveX() {
        return SMoveX;
    }

    public void setSMoveX(int SMoveX) {
        this.SMoveX = SMoveX;
    }

    public int getSMoveY() {
        return SMoveY;
    }

    public void setSMoveY(int SMoveY) {
        this.SMoveY = SMoveY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void tick() {
        
        //Movement in roads
        switch (direction) {
            case 1:
                SMoveY -= 5;
                break;
            case 2:
                SMoveY += 5;
                break;
            case 3:
                SMoveX -= 5;
                break;
            case 4:
                SMoveX += 5;
                break;
        }

        x = iniX + SMoveX - game.getScreen().getX();
        y = iniY + SMoveY - game.getScreen().getY();
        //Checks if theres a person on the Crosswalk
        for (int i = 0; i < game.getCrosswalks().size(); i++) {
            if (getPerimetro().intersects(game.getCrosswalks().get(i).getPerimetro())) {
                ///Add stop movement
                        switch (direction) {
            case 1:
                y += 5;
                break;
            case 2:
                y -= 5;
                break;
            case 3:
                x += 5;
                break;
            case 4:
                x -= 5;
                break;
        }

            }

        }
        for (int i = 0; i < game.getRoadChanges().size(); i++) {
            if (getPerimetro().contains(game.getRoadChanges().get(i).getPerimetro())) {
                direction = game.getRoadChanges().get(i).giveRandomDirection();
            }
        }
        
        if(iniX+SMoveX > 4096 || iniX+SMoveX < -128){
            destroy=true;
            
        }
        if(iniY +SMoveY > 4096 || iniY + SMoveY < -128){
            destroy = true;
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.inTrashCan, getX()-game.getScreen().getX(), getY()-game.getScreen().getY(), getWidth(), getHeight(), null);

    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

}
