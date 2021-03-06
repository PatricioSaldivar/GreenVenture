/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import MinigameClassify.Box;
import MinigameClassify.TrashCan;
import MinigameClassify.TrashMinigameClassify;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author BonfireStudios
 */
public class MinigameTrashClassify implements Runnable {

    private BufferStrategy bs;              // to have several buffers when displaying
    private Graphics g;                     // to paint objects
    private Display display;                // to display in the game
    String title;                           // title of the window
    private int width;                      // width of the window
    private int height;                     // height of the window
    private Thread thread;                  // thread to create the game
    private boolean running;                // to set the game
    private KeyManager keyManager;          // to manage the keyboard
    private Font fontx;                     // to manage a custom font
    private TrashCan inTrashCan;            // to create inorganic trash can
    private TrashCan orTrashCan;            // to create organic trash can
    private Box boxGuantlets;            // to create box guantlets
    // private TrashMinigameClassify trash;   
    private LinkedList<TrashMinigameClassify> trash; // to create trash in the minigame
    private Animation animationPause;        //To animate the pause
    private Game game;                      //To store the game in which it was before
    private int score=0;                    // To store the game score
    private boolean endGame = false;
    private int endGameHelper = 0;

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public MinigameTrashClassify(String title, int width, int height, Display display, KeyManager keyManager, Game game) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        this.keyManager = keyManager;
        this.display = display;
        display.setTitle("Ciudad");
        this.game = game;

        //Adds font from fonts package
        try {
            fontx = Font.createFont(Font.TRUETYPE_FONT, Font.class.getResourceAsStream("/fonts/FreePixel.ttf")).deriveFont(24f);
        } catch (FontFormatException | IOException ex) {
        }

    }

    public Graphics getG() {
        return g;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the custom font
     *
     * @return an <code>.ttf</code> value with the height
     */
    public Font getFontx() {
        return fontx;
    }

    public LinkedList<TrashMinigameClassify> getTrash() {
        return trash;
    }

    public void setTrash(LinkedList<TrashMinigameClassify> trash) {
        this.trash = trash;
    }

    public TrashCan getInTrashCan() {
        return inTrashCan;
    }

    public void setInTrashCan(TrashCan inTrashCan) {
        this.inTrashCan = inTrashCan;
    }

    public TrashCan getOrTrashCan() {
        return orTrashCan;
    }

    public void setOrTrashCan(TrashCan orTrashCan) {
        this.orTrashCan = orTrashCan;
    }

    public Rectangle getPerimetro() {
        return new Rectangle(0, -64, getWidth(), getHeight());

    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        Assets.init();
        Assets.miniMusic.play();
        //creates organinc an inorganic trash cans
        inTrashCan = new TrashCan(64, 384, 128, 128, false, this);
        orTrashCan = new TrashCan(getWidth() - 192, 384, 128, 128, true, this);
        //creating Trash list
        trash = new LinkedList<TrashMinigameClassify>();
        //creats right and left guantlet
        boxGuantlets = new Box(0, (getHeight() / 2) - 32, 64, 64, this);
        //Cycle to create trash in game
        int iJump = -64;
        for (int i = 0; i < 20; i++) {
            //random cycle to create organic or inorganic trash every new game (there is a great change that all garabge can be organic or inogranic)
            //IF true is organic, else is inorganic
            if( ((int)(Math.random() * 2) + 1) == 1){
                trash.add(new TrashMinigameClassify(getWidth()/2 - 32,iJump, 64, 64, 3, true, this));
            } else {
            trash.add(new TrashMinigameClassify(getWidth() / 2 - 32, iJump, 64, 64, 3, false, this));
            }
            iJump -= 152;
        }
        keyManager.setPauseMax(1);
        keyManager.pause = false;
        animationPause = new Animation(Assets.minigame1PauseEnd, 300);

    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    private void tick() {
        keyManager.tick();
        if (!keyManager.pause) {
            // avancing minigame
            boxGuantlets.tick();
            for (int i = 0; i < trash.size(); i++) {
                if (trash.get(i).getY() > (512 - 64)) {
                    trash.remove(i);
                } else {
                    if (boxGuantlets.getPerimetro().intersects(trash.get(i).getPerimetro()) && !boxGuantlets.isBoxReturn()) {
                      if(!trash.get(i).isMovingRight() && !trash.get(i).isMoved() ) {
                          if(trash.get(i).getImageIndex() < 4){
                              Assets.glassHit.play();
                          }
                          if(trash.get(i).getImageIndex() > 3 && trash.get(i).getImageIndex() < 8){
                              Assets.alHit.play();
                          }
                          if(trash.get(i).getImageIndex() > 7 && trash.get(i).getImageIndex() < 11){
                              Assets.palsticHit.play();
                          }
                          if(trash.get(i).getImageIndex() > 10 && trash.get(i).getImageIndex() < 14){
                              Assets.paperHit.play();
                          }
                          if(trash.get(i).getImageIndex() > 13 && trash.get(i).getImageIndex() < 18){
                              Assets.elecHit.play();
                          }
                          if(trash.get(i).getImageIndex() > 18){
                              Assets.orgHit.play();
                          }
                        
                    }
                        trash.get(i).setMovingRight(true);
                    } else if (boxGuantlets.getPerimetroLeft().intersects(trash.get(i).getPerimetro()) && !boxGuantlets.isBoxReturn()) {
                        if(!trash.get(i).isMovingLeft() && !trash.get(i).isMoved()) {
                         if(trash.get(i).getImageIndex() < 4){
                              Assets.glassHit.play();
                          }
                          if(trash.get(i).getImageIndex() > 3 && trash.get(i).getImageIndex() < 8){
                              Assets.alHit.play();
                          }
                          if(trash.get(i).getImageIndex() > 7 && trash.get(i).getImageIndex() < 11){
                              Assets.palsticHit.play();
                          }
                          if(trash.get(i).getImageIndex() > 10 && trash.get(i).getImageIndex() < 14){
                              Assets.paperHit.play();
                          }
                          if(trash.get(i).getImageIndex() > 13 && trash.get(i).getImageIndex() < 18){
                              Assets.elecHit.play();
                          }
                          if(trash.get(i).getImageIndex() > 18){
                              Assets.orgHit.play();
                          }
                    }
                        trash.get(i).setMovingLeft(true);
                       
                    }
                    trash.get(i).tick();
                    if(trash.get(i).getPerimetro().intersects(inTrashCan.getPerimetro()) && !trash.get(i).isTrashType()){
                        score+=100;
                        trash.remove(i);
                    }else if(trash.get(i).getPerimetro().intersects(orTrashCan.getPerimetro()) && trash.get(i).isTrashType()){
                        score+=100;
                        trash.remove(i);
                    }
                }
            }
            //If theres no more trash, a countdown start before the game return the player to the map when he was before starting the minigame
            if(endGame){
            endGameHelper++;
                //Resume the game
                if(endGameHelper>150){
                Assets.miniMusic.stop();
                game.setCont(true);
                game.start();
                running = false;
                }
            }
        } else {
            animationPause.tick();
            if (keyManager.space && !keyManager.helperSpace) {
                //Resume the game
                game.setCont(true);
                game.start();
                running = false;
            }

        }
        endGame = trash.isEmpty();
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.clearRect(0, 0, width, height);
            //Draws Pause image when pausing game
            if (keyManager.pause) {
                g.drawImage(animationPause.getCurrentFrame(), 0, 0, width, height, null);
            } else {
                g.drawImage(Assets.minigameWallpaper, 0, 0, 512, 512, null);
                for (int i = 0; i < trash.size(); i++) {
                    trash.get(i).render(g);
                }
                boxGuantlets.render(g);
                inTrashCan.render(g);
                orTrashCan.render(g);
                  
                //Score values por painting
                g.setFont(fontx);
                g.setColor(Color.BLACK);
                g.getFont().isBold();
                //Draws final score when there is no more trash in the game
                if(endGame){
                   g.setFont(fontx.deriveFont(38f));
                    String message = "Puntaje Final: " + score;
                    g.drawString( message, (this.getWidth() -(((message.length()/2)) * g.getFont().getSize()))/2, this.getHeight()/2);
                } else {
                    //Draws Score in display
                    g.getFont().deriveFont(36f);
                    g.drawString("Puntaje: "+ score,12, 40);
                }
                
            }
            bs.show();
            g.dispose();
        }

    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
