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
 * @author BonfireStudios
 */
public class NPC extends Item {

    private int width;
    private int height;
    private int trashMaker;
    private int trashThrown;
    private Game game;
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
    private Animation walking;
    private int trashContainerId;
    private int trashToTrashContainer = 0;
    private BufferedImage conversation;

    public NPC(int x, int y, int width, int height, Game game, Screen screen, int id, int trashContainerId) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.screen = screen;
        this.trashContainerId = trashContainerId;
        iniX = x;
        iniY = y;
        trashMakerHelper = (int) (Math.random() *(500)+ 50);
        this.id = id;
        alertAnimation = new Animation(Assets.npcAlert, 100);
        direction = 4;
        facing = new Animation(Assets.npcUp[id], 300);

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

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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

    public int getTrashMakerHelper() {
        return trashMakerHelper;
    }

    public void setTrashMakerHelper(int trashMakerHelper) {
        this.trashMakerHelper = trashMakerHelper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJustThrowedTrashHelper() {
        return justThrowedTrashHelper;
    }

    public void setJustThrowedTrashHelper(int justThrowedTrashHelper) {
        this.justThrowedTrashHelper = justThrowedTrashHelper;
    }

    public int getRandomDist() {
        return randomDist;
    }

    public void setRandomDist(int randomDist) {
        this.randomDist = randomDist;
    }

    public int getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(int distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public Animation getWalking() {
        return walking;
    }

    public void setWalking(Animation walking) {
        this.walking = walking;
    }

    public int getTrashContainerId() {
        return trashContainerId;
    }

    public void setTrashContainerId(int trashContainerId) {
        this.trashContainerId = trashContainerId;
    }

    public int getTrashToTrashContainer() {
        return trashToTrashContainer;
    }

    public void setTrashToTrashContainer(int trashToTrashContainer) {
        this.trashToTrashContainer = trashToTrashContainer;
    }

    public BufferedImage getConversation() {
        return conversation;
    }

    public void setConversation(BufferedImage conversation) {
        this.conversation = conversation;
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
                //Put real animations
                case 4:
                    distanceTraveled = 0;
                    direction = (int) (Math.random() * 4);
                    switch (direction) {
                        case 0:
                            randomDist = (int) (Math.random() * (4096 - iniY - yMove));
                            facing = new Animation(Assets.npcDown[id], 300);
                            break;
                        case 1:
                            randomDist = (int) (Math.random() * (4096 - iniX - xMove));
                            facing = new Animation(Assets.npcRight[id], 300);
                            break;
                        case 2:
                            randomDist = (int) (Math.random() * (iniY + yMove));
                            facing = new Animation(Assets.npcUp[id], 300);
                            break;
                        case 3:
                            randomDist = (int) (Math.random() * (iniX + xMove));
                            facing = new Animation(Assets.npcLeft[id], 300);
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

            if (trashThrown < 10) {
                if (trashMaker > trashMakerHelper) {
                    int randTrashMaker = (int) (Math.random() * 4);
                    if (randTrashMaker < trashToTrashContainer) {
                        int randType = (int) (Math.random() * 10);
                        if (game.getTrashContainers().get(trashContainerId).getHowManyTrash() < game.getTrashContainers().get(trashContainerId).getMaxTrash()) {
                            if (randType < 2) {
                                //Probability of .2 (0,1)
                                game.getTrashContainers().get(trashContainerId).setOrganic(game.getTrashContainers().get(trashContainerId).getOrganic() + 1);
                            } else if (randType < 4) {
                                //Probability of .2 (2,3)
                                game.getTrashContainers().get(trashContainerId).setPaper(game.getTrashContainers().get(trashContainerId).getPaper() + 1);
                            } else if (randType < 6) {
                                //Probability of .2 (4,5)
                                game.getTrashContainers().get(trashContainerId).setPlastic(game.getTrashContainers().get(trashContainerId).getPlastic() + 1);
                            } else if (randType < 7) {
                                //Probability of .1 (6)
                                game.getTrashContainers().get(trashContainerId).setElectronics(game.getTrashContainers().get(trashContainerId).getElectronics() + 1);
                            } else if (randType < 8) {
                                //Probability of .1 (7)
                                game.getTrashContainers().get(trashContainerId).setAluminum(game.getTrashContainers().get(trashContainerId).getAluminum() + 1);
                            } else if (randType < 10) {
                                //Probability of .2 (8,9)
                                game.getTrashContainers().get(trashContainerId).setGlass(game.getTrashContainers().get(trashContainerId).getGlass() + 1);
                            }
                        }
                    } else {
                        int randType = (int) (Math.random() * (27));
                        game.getTrash().add(new Trash(x + screen.getX() + 16, y + screen.getY() + 16, 32, 32, randType, game, screen, id));
                        trashThrown++;
                        justThrowedTrash = true;
                        justThrowedTrashHelper = 50;
                    }
                    trashMaker = 0;
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
        //Add animation
        g.drawImage(facing.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        if (justThrowedTrash || talking) {
            g.drawImage(alertAnimation.getCurrentFrame(), getX()+16, getY() - 16, 16, 16, null);
        }
    }
    
        public void renderForStore(Graphics g,int xScreen, int yScreen) {
        //Add animation
        if(talking){
          g.drawImage(conversation,iniX-xScreen+xMove, iniY-yScreen+yMove, getWidth(), getHeight(), null);
        }else{
        g.drawImage(facing.getCurrentFrame(), iniX-xScreen+xMove, iniY-yScreen+yMove, getWidth(), getHeight(), null);
        }
        if (justThrowedTrash || talking) {
            g.drawImage(alertAnimation.getCurrentFrame(),iniX-xScreen+xMove+16, iniY-yScreen+yMove-16, 16, 16, null);
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
