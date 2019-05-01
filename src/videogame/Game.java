/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BonfireStudios
 */
public class Game implements Runnable {

    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private KeyManager keyManager;  // to manage the keyboard
    private Screen screen;          //to manage screen
    private ArrayList<NPC> npcs;   // to manage all items in the game
    private Font fontx;             //to manage a custom font
    private LinkedList<Trash> trash; // to manage the trash taht is in the game
    private Animation animation;     // to manage the animations of the objects
    private int pauseIndex = 5;          // to storw the index of the pause selector
    private boolean cont = false;       // to continue the game

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     * @param display
     * @param keyManager
     */
    public Game(String title, int width, int height, Display display, KeyManager keyManager) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        this.keyManager = keyManager;
        npcs = new ArrayList<>(0);
        trash = new LinkedList<>();
        this.display = display;
        display.setTitle("Ciudad");

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

    public Player getPlayer() {
        return player;
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

    public LinkedList<Trash> getTrash() {
        return trash;
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    public void setCont(boolean cont) {
        this.cont = cont;
    }
    

    /**
     * initializing the display window of the game
     */
    private void init() {
        Assets.init();
        player = new Player(0, 0, 64, 64, this);
        screen = new Screen(0, 0, width, height, this, player, trash);
        npcs.add(new NPC(400, 400, 64, 64, 0, this, screen, 0));
        npcs.add(new NPC(400, 400, 64, 64, 0, this, screen, 1));
        npcs.add(new NPC(800, 400, 64, 64, 0, this, screen, 2));
        animation = new Animation(Assets.pausaSave, 300);
        keyManager.setPauseMax(4);

    }

    
    
    @Override
    public void run() {
        //If the game is called to resume the init wont be called 
        if(!cont){
        init();
    }else{
        animation = new Animation(Assets.pausaSave, 300);
        keyManager.setPauseMax(4);
        //this code may be used on the return to game function for better eficiency
        keyManager.pause=false;
        
        }
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
            // avancing player with colision
            for (int i = 0; i < npcs.size(); i++) {
                npcs.get(i).tick();
                if(!npcs.get(i).isTalking()){
            if(npcs.get(i).getPerimetro().intersects(player.getPerimetro()) && npcs.get(i).isJustThrowedTrash() && player.isPick() && !player.isConversation()){
                    npcs.get(i).setTalking(true);
                    player.setConversation(true);
                }
                
                }else if(player.isPick() && player.isConversation()){
                    if(!player.isTalking()){
                    player.setTalking(true);
                    }
                    else{
                         player.setConversation(false);
                         npcs.get(i).setTalking(false);
                         player.setTalking(false);
                    }
                }
            }
           
             player.tick();
        } else {
            animation.tick();
            if (pauseIndex != keyManager.pauseSelector) {
                switch (keyManager.pauseSelector) {
                    case 0:
                        animation = new Animation(Assets.pausaSave, 300);
                        break;
                    case 1:
                        animation = new Animation(Assets.pausaStats, 300);
                        break;
                    case 2:
                        animation = new Animation(Assets.pausaMenuInstructions, 300);
                        break;
                    case 3:
                        animation = new Animation(Assets.pausaMainMenu, 300);
                        break;
                }
                pauseIndex = keyManager.pauseSelector;
            }
            if(keyManager.space && pauseIndex==3){
                MainMenu m = new MainMenu("MainMenu", 512, 512,display);
                m.start();
                running=false;
                }
            if(keyManager.space&& pauseIndex == 2){
            MinigameTrashClassify mct = new MinigameTrashClassify("Minigame",512,512,display,keyManager,this);
            mct.start();
            running=false;
            }
        }

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
            screen.render(g); //Draws the screen that follows the player    
            //Draws Pause image when pausing game
            if (keyManager.pause) {
                g.drawImage(animation.getCurrentFrame(), 0, 0, width, height, null);
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
