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
 * @author BonfireStudios
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
    private int direction;
    private int iniX;
    private int iniY;
    private int xMove = 0;
    private int yMove = 0;
    private int trashMakerHelper;
    private int id;
    private boolean justThrowedTrash = false;
    private int justThrowedTrashHelper = 0;
    private boolean talking;
    private Animation alertAnimation;
    private int randomDist;
    private int distanceTraveled;
    private Animation facing;

    public NPC(int x, int y, int width, int height, int type, Game game, Screen screen, int id) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.type = type;
        this.game = game;
        this.screen = screen;
        iniX = x;
        iniY = y;
        //trashMakerHelper = (int) (Math.random() *(500)+ 50);
        trashMakerHelper = 500;
        this.id = id;
        alertAnimation = new Animation(Assets.npcAlert, 100);
        direction = 4;
        facing = new Animation(Assets.npcAlert, 100);

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

    public void setTrashThrown(int n) {
        this.trashThrown = n;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTalking(boolean talking) {
        this.talking = talking;
    }

    public void setJustThrowedTrash(boolean justThrowedTrash) {
        this.justThrowedTrash = justThrowedTrash;
    }

    public boolean isJustThrowedTrash() {
        return justThrowedTrash;
    }

    public boolean isTalking() {
        return talking;
    }

    public Animation getAlertAnimation() {
        return alertAnimation;
    }

    public void setAlertAnimation(Animation alertAnimation) {
        this.alertAnimation = alertAnimation;
    }

    public Animation getFacing() {
        return facing;
    }

    public void setFacing(Animation facing) {
        this.facing = facing;
    }

    @Override
    public void tick() {
        alertAnimation.tick();
        if (!talking) {
            facing.tick();
            switch (direction) {
                //Moves Up, checks colission
                case 0:
                    yMove += speed;
                    for (int i = 0; i < game.getSolids().size(); i++) {
                        if (getPerimetroForSolidsUp().intersects(game.getSolids().get(i).getPerimetroUp(screen.getX(), screen.getY()))) {
                            yMove -= speed;
                            direction = 4;
                            break;
                        }
                    }
                    for (int i = 0; i < game.getCrosswalks().size(); i++) {
                        //Checks collisions with crosswalks when going from top to down and checks if theres a car
                        if (getPerimetroForSolidsUp().intersects(game.getCrosswalks().get(i).getPerimetroUp(screen.getX(), screen.getY())) && game.getCrosswalks().get(i).isCarIn()) {
                            yMove += speed;
                            direction = 4;
                            break;
                        }
                    }
                    distanceTraveled++;
                    if (distanceTraveled > randomDist) {
                        direction = 4;
                    }
                    break;

                case 1:
                    //Moves Right, checks colission
                    xMove += speed;
                    for (int i = 0; i < game.getSolids().size(); i++) {
                        if (getPerimetroForSolidsRight().intersects(game.getSolids().get(i).getPerimetroRight(screen.getX(), screen.getY()))) {
                            xMove -= speed;
                            direction = 4;
                            break;
                        }
                    }
                    for (int i = 0; i < game.getCrosswalks().size(); i++) {
                        //Checks collisions with crosswalks when going from top to down and checks if theres a car
                        if (getPerimetroForSolidsRight().intersects(game.getCrosswalks().get(i).getPerimetroRight(screen.getX(), screen.getY())) && game.getCrosswalks().get(i).isCarIn()) {
                            yMove += speed;
                            direction = 4;
                            break;
                        }
                    }
                    distanceTraveled++;
                    if (distanceTraveled > randomDist) {
                        direction = 4;
                    }
                    break;

                case 2:
                    //Moves Down, checks colission
                    yMove -= speed;
                    for (int i = 0; i < game.getSolids().size(); i++) {
                        if (getPerimetroForSolidsDown().intersects(game.getSolids().get(i).getPerimetroDown(screen.getX(), screen.getY()))) {
                            yMove += speed;
                            direction = 4;
                            break;
                        }
                    }
                    for (int i = 0; i < game.getCrosswalks().size(); i++) {
                        //Checks collisions with crosswalks when going from top to down and checks if theres a car
                        if (getPerimetroForSolidsDown().intersects(game.getCrosswalks().get(i).getPerimetroDown(screen.getX(), screen.getY())) && game.getCrosswalks().get(i).isCarIn()) {
                            yMove += speed;
                            direction = 4;
                            break;
                        }
                    }

                    distanceTraveled++;
                    if (distanceTraveled > randomDist) {
                        direction = 4;
                    }
                    break;

                case 3:
                    //Moves Left, checks colission
                    xMove -= speed;
                    for (int i = 0; i < game.getSolids().size(); i++) {
                        if (getPerimetroForSolidsLeft().intersects(game.getSolids().get(i).getPerimetroLeft(screen.getX(), screen.getY()))) {
                            xMove += speed;
                            direction = 4;
                            break;
                        }
                    }
                    for (int i = 0; i < game.getCrosswalks().size(); i++) {
                        //Checks collisions with crosswalks when going from top to down and checks if theres a car
                        if (getPerimetroForSolidsLeft().intersects(game.getCrosswalks().get(i).getPerimetroLeft(screen.getX(), screen.getY())) && game.getCrosswalks().get(i).isCarIn()) {
                            yMove += speed;
                            direction = 4;
                            break;
                        }
                    }
                    distanceTraveled++;
                    if (distanceTraveled > randomDist) {
                        direction = 4;
                    }

                    break;
                //Restarts the random movement of each NPC
                case 4:
                    distanceTraveled = 0;
                    direction = (int) (Math.random() * 4);
                    switch (direction) {
                        case 0:
                            randomDist = (int) (Math.random() * (4096 - iniY - yMove));
                            facing = new Animation(Assets.npcAlert, 100);
                            break;
                        case 1:
                            randomDist = (int) (Math.random() * (4096 - iniX - xMove));
                            facing = new Animation(Assets.npcAlert, 100);
                            break;
                        case 2:
                            randomDist = (int) (Math.random() * (iniY + yMove));
                            facing = new Animation(Assets.npcAlert, 100);
                            break;
                        case 3:
                            randomDist = (int) (Math.random() * (iniX + xMove));
                            facing = new Animation(Assets.npcAlert, 100);
                            break;
                    }

                    break;
            }
            x = iniX - screen.getX() + xMove;
            y = iniY - screen.getY() + yMove;

            if (justThrowedTrash) {
                justThrowedTrashHelper--;
            }

            if (justThrowedTrash && justThrowedTrashHelper < 1) {
                justThrowedTrash = false;
            }

            if (trashThrown < 5) {
                if (trashMaker > trashMakerHelper) {
                    int randType = (int) (Math.random() * (27));
                    game.getTrash().add(new Trash(x + screen.getX() + 16, y + screen.getY() + 16, 32, 32, randType, game, screen, id));
                    trashMaker = 0;
                    trashThrown++;
                    justThrowedTrash = true;
                    justThrowedTrashHelper = 50;
                }
                trashMaker++;
            }
        } else {
            justThrowedTrash = false;
            justThrowedTrashHelper = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
        if (justThrowedTrash || talking) {
            g.drawImage(alertAnimation.getCurrentFrame(), getX(), getY() - 16, 16, 16, null);
        }
    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getPerimetroForSolidsDown() {
        return new Rectangle(getX(), getY(), getWidth(), 16);

    }

    public Rectangle getPerimetroForSolidsUp() {
        return new Rectangle(getX(), getY() + getHeight() - 16, getWidth(), 16);

    }

    public Rectangle getPerimetroForSolidsRight() {
        return new Rectangle(getX() + getWidth() - 16, getY(), 16, getHeight());

    }

    public Rectangle getPerimetroForSolidsLeft() {
        return new Rectangle(getX(), getY(), 16, getHeight());

    }

}
