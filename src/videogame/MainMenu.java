/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author yeyog
 */
public class MainMenu implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the gamer
    private KeyManager keyManager;  // to manage the keyboard
    private Animation animation;    // to animate the main menu
    private int index=0;            // to establish the index of the option to be 
    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public MainMenu(String title, int width, int height, Display display) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        this.display = display;
        display.setTitle("Menu");
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

    public Display getDisplay() {
        return display;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        Assets.init();
        display.getJframe().addKeyListener(keyManager);
        animation = new Animation(Assets.mainMenuPlay, 300);
        keyManager.setPauseMax(5);

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
        animation.tick();
            if (index != keyManager.pauseSelector) {
                switch (keyManager.pauseSelector) {
                    case 0:
                        animation = new Animation(Assets.mainMenuPlay, 300);
                        Assets.selectSound.play();
                        break;
                    case 1:
                        animation = new Animation(Assets.mainMenuLoad, 300);
                        Assets.selectSound.play();
                        break;
                    case 2:
                        animation = new Animation(Assets.mainMenuInstructions, 300);
                        Assets.selectSound.play();
                        break;
                    case 3:
                        animation = new Animation(Assets.mainMenuCredits, 300);
                        Assets.selectSound.play();
                        break;
                    case 4:
                        animation = new Animation(Assets.mainMenuQuit, 300);
                        Assets.selectSound.play();
                        break;
                }
                index = keyManager.pauseSelector;

    }
           if(index==0 && keyManager.space==true){
            Game g = new Game("Juego", 512,512, display,keyManager);
            Assets.gameStart.play();
            g.start();
            running=false;
        }
            if(index==1 && keyManager.space==true){
            Load l = new Load(this);
            try {
                l.tick();
            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            Game g = l.getGame();
            g.setPlayer(l.getPlayer());
            g.setLoaded(true);
            Assets.gameStart.play();
            g.start();
            running=false;
        }
           if(index==4 && keyManager.space==true){
               //Closes the game
               display.getJframe().dispose();
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
           g.drawImage(animation.getCurrentFrame(), 0, 0, width, height,null);
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
    
