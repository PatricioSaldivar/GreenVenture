/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import MinigameClassify.Box;
import MinigameClassify.TrashCan;
import MinigameClassify.TrashMinigameClassify;
import MinigameThrow.Power;
import MinigameThrow.TrashThrown;
import MinigameThrow.Trashcan;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author PatoSaldivar
 */
public class MinigameThrow implements Runnable {

    /**
     *
     * @author BonfireStudios
     */
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
    private Animation animationPause;        //To animate the pause
    private Game game;                      //To store the game in which it was before
    private int score = 0;                    // To store the game score
    private boolean endGame = false;
    private int endGameHelper = 0;
    private Power power;
    private TrashThrown trashThrown;
    private Trashcan trashcan;
    private boolean launch;

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     * @param display
     * @param keyManager
     * @param game
     */
    public MinigameThrow(String title, int width, int height, Display display, KeyManager keyManager, Game game) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        this.keyManager = keyManager;
        this.display = display;
        display.setTitle("Throwing To The Trashcan");
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

    public Rectangle getPerimetro() {
        return new Rectangle(0, -64, getWidth(), getHeight());

    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        Assets.init();
        //Crear bote 
        keyManager.setPauseMax(1);
        keyManager.pause = false;
        animationPause = new Animation(Assets.minigame1PauseEnd, 300);
        power = new Power(0, 512);
        trashThrown = new TrashThrown(0, 448, 64, 64);
        trashcan = new Trashcan(400, 128, 96, 128);

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
            if (launch) {
                trashThrown.tick();
                if (trashcan.getPerimetroIn().intersects(trashThrown.getPerimetro())) {
                    score += 100;
                    trashThrown.setX(0);
                    trashThrown.setY(448);
                    launch = false;
                    trashcan.tick();
                    endGameHelper++;
                    Assets.moneySound.play();
                } else if (trashThrown.getX() > 512 || trashThrown.getY() > 448 ||trashcan.getPerimetroOff().intersects(trashThrown.getPerimetro()) ) {
                    trashThrown.setX(0);
                    trashThrown.setY(448);
                    launch = false;
                    endGameHelper++;
                    Assets.pickDenied.play();
                          
                }

            } else {
                if (keyManager.down ) {
                    if (power.getPowerY() > 0) {
                        power.setPowerY(-1);
                    }
                }
                if (keyManager.up) {
                    if (power.getPowerY() < 20) {
                        power.setPowerY(1);
                    }
                }
                if (keyManager.right) {
                    if (power.getPowerX() < 20) {
                        power.setPowerX(1);
                    }
                }
                if (keyManager.left) {
                    if (power.getPowerX() > 0) {
                        power.setPowerX(-1);
                    }
                }
                if (keyManager.space) {
                    launch = true;

                    trashThrown.setxSpeed(power.getPowerX());
                    trashThrown.setySpeed(power.getPowerY());
                    trashThrown.setMoveX(0);
                    trashThrown.setMoveY(0);
                    trashThrown.setGravity(false);
                }
            }

            //If theres no more trash, a countdown start before the game return the player to the map when he was before starting the minigame
            if (endGameHelper > 4) {
                endGame=true;
                endGameHelper++;
                //Resume the game
                if (endGameHelper > 150) {
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
                //Draws final score when there is no more trash in the game
                if (endGame) {
                    g.setFont(fontx.deriveFont(38f));
                    String message = "Puntaje Final: " + score;
                    g.drawString(message, (this.getWidth() - ((message.length() / 2) * g.getFont().getSize())) / 2, this.getHeight() / 2);
                } else {
                trashThrown.render(g);
                power.render(g);
                trashcan.render(g);

                g.setFont(fontx);
                g.setColor(Color.BLACK);
                g.getFont().isBold();
                
                    //Draws Score in display
                    g.getFont().deriveFont(36f);
                    g.drawString("Puntaje: " + score, 0, 40);
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
