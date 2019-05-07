/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.io.IOException;

/**
 *
 * @author BonfireStudios
 */
public class RecycleCoRoom implements Runnable {
    
    private BufferStrategy bs;             // to have several buffers when displaying
    private Graphics g;                     // to paint objects
    private Display display;                // to display in the game
    String title;                           // title of the window
    private int width;                      // width of the window
    private int height;                     // height of the window
    private Thread thread;                  // thread to create the game
    private boolean running;                // to set the game
    private KeyManager keyManager;          // to manage the keyboard
    private Font fontx;                     // to manage a custom font
    private Player player;                  // to store the player(attributes)
    private PlayerInStore playablePlayer;   // to store the usable player (this will only works in the store and dont save anything) 
    private Game game;                      // to store the game in which it was before
    private boolean cont = false;           // to continue the game

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public RecycleCoRoom(String title, int width, int height, Display display, KeyManager keyManager, Game game, Player player) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        this.keyManager = keyManager;
        this.display = display;
        display.setTitle("ReycleCoRoom");
        this.game = game;
        this.player = player;
        this.playablePlayer = new PlayerInStore(172,386,64,64,this.game);
        
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
    
    public boolean isCont() {
        return cont;
    }

    public void setCont(boolean cont) {
        this.cont = cont;
    }
    
    

    /**
     * initializing the display window of the game
     */
    private void init() {
        Assets.init();

    }

    @Override
    public void run() {
          //If the game is called to resume the init wont be called 
        if (!cont) {
            init();
        } else {
            keyManager.space = true;
            keyManager.helperSpace = true;
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
        playablePlayer.tick();
        if(playablePlayer.getPerimetro().intersects(this.getPerimetroStore())  && (keyManager.space && !keyManager.helperSpace)){
            RecycleCo rc = new RecycleCo("RecycleCo", 512, 512,display,keyManager,game,this);
            Assets.gameStart.play();
            rc.start();
            running = false;
        }
        if(playablePlayer.getPerimetro().intersects(this.getPerimetroWorld())  && (keyManager.space && !keyManager.helperSpace)){
            Assets.gameStart.play();
            game.setCont(true);
            game.start();
            running = false;
        }
    
             
    }
    
    public Rectangle getPerimetroStore() {
        return new Rectangle(0,120,210,215);
    }
    
    public Rectangle getPerimetroWorld() {
        return new Rectangle(172,450,75,62);
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
            g.drawImage(Assets.todoMartRoom, 0, 0, width, height, null);
            playablePlayer.render(g);
            //Score values por painting
            g.setFont(fontx);
            g.setColor(Color.BLACK);
            g.getFont().isBold();
            g.getFont().deriveFont(16f);
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