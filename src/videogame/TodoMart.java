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
import java.awt.image.BufferStrategy;
import java.io.IOException;
import static java.lang.Math.pow;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author BonfireStudios
 */
public class TodoMart implements Runnable {

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
    private int index = 0;                  // to manage the index of the animations
    private Animation animation;            // to store the animations
    private Animation coinAnimation;         // to store the coin animations
    private Game game;                      // to store the game in which it was before
    private TodoMartRoom room;              // to store the room in which it was before
    private int indexHelper = 10;           // to store an index helper to know when the game need to make 
    private double sneakersUpPrice = 10;         // to store the price of the sneakers upgrade
    private double backPackUpPrice = 2;         // to store the price of the backpack upgrade
    private double trashValueUpPrice = 3;       // to store the price of the trash value upgrade
    private double binsUpPrice = 30;             // to store the price of the bins upgrade
    private int showTrashcan = 0;
    private boolean movingToTrashcan = false;
    private int x = 3551;
    private int y = 2179;
    private int xGoal;
    private int yGoal;
    private int rand;

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public TodoMart(String title, int width, int height, Display display, KeyManager keyManager, Game game, TodoMartRoom room) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        this.keyManager = keyManager;
        this.display = display;
        display.setTitle("TodoxMart");
        this.game = game;
        this.room = room;
        this.sneakersUpPrice = pow(2, game.getPlayer().getSpeedUpgrade()) * sneakersUpPrice;
        this.backPackUpPrice = pow(2, game.getPlayer().getCapacityUpgrade()) * backPackUpPrice;
        this.trashValueUpPrice = pow(2, game.getPlayer().getTrashUpgrade() - 1) * trashValueUpPrice;

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

    /**
     * initializing the display window of the game
     */
    private void init() {
        Assets.init();
        animation = new Animation(Assets.todoMartReturn, 300);
        coinAnimation = new Animation(Assets.coin, 100);

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
        coinAnimation.tick();
        if (!movingToTrashcan && showTrashcan <= 0) {
            switch (index) {
                //Return Menu Selection
                case 0:
                    if (indexHelper != 0) {
                        animation = new Animation(Assets.todoMartReturn, 300);
                        Assets.selectSound.play();
                        indexHelper = index;
                    }
                    if (keyManager.down && !keyManager.helperDown) {
                        index = 4;
                        break;
                    }
                    if (keyManager.up && !keyManager.helperUp) {
                        index = 1;
                        break;
                    }
                    break;
                //Bin Upgrade Menu Selection
                case 1:
                    if (indexHelper != 1) {
                        animation = new Animation(Assets.todoMartBinUp, 300);
                        Assets.selectSound.play();
                        indexHelper = index;
                    }
                    if (keyManager.down && !keyManager.helperDown) {
                        index = 0;
                        break;
                    }
                    if (keyManager.up && !keyManager.helperUp) {
                        index = 2;
                        break;
                    }

                    break;
                //Trash Value Up Upgrade Menu Selection            
                case 2:
                    if (indexHelper != 2) {
                        animation = new Animation(Assets.todoMartValueUp, 300);
                        Assets.selectSound.play();
                        indexHelper = index;
                    }
                    if (keyManager.down && !keyManager.helperDown) {
                        index = 1;
                        break;
                    }
                    if (keyManager.up && !keyManager.helperUp) {
                        index = 3;
                        break;
                    }

                    break;
                //BackPack Upgrade Menu Selection
                case 3:
                    if (indexHelper != 3) {
                        animation = new Animation(Assets.todoMartBackPackUp, 300);
                        Assets.selectSound.play();
                        indexHelper = index;
                    }
                    if (keyManager.down && !keyManager.helperDown) {
                        index = 2;
                        break;
                    }
                    if (keyManager.up && !keyManager.helperUp) {
                        index = 4;
                        break;
                    }

                    break;
                //Sneaker Upgrade Menu Selection
                case 4:
                    if (indexHelper != 4) {
                        animation = new Animation(Assets.todoMartSneakersUp, 300);
                        Assets.selectSound.play();
                        indexHelper = index;
                    }
                    if (keyManager.down && !keyManager.helperDown) {
                        index = 3;
                        break;
                    }
                    if (keyManager.up && !keyManager.helperUp) {
                        index = 0;
                        break;
                    }

                    break;
            }

            switch (index) {
                case 0:
                    if (keyManager.space && !keyManager.helperSpace) {
                        Assets.gameStart.play();
                        room.setCont(true);
                        room.start();
                        running = false;
                    }
                    break;
                case 1:
                    if ((keyManager.space && !keyManager.helperSpace) && (game.getPlayer().getMoney() >= binsUpPrice)) {
                        rand = (int) (Math.random() * game.getPlayer().getTrashContainersLeft().size());
                        Assets.moneySound.play();
                        game.getPlayer().setMoney(game.getPlayer().getMoney() - binsUpPrice);
                        this.binsUpPrice *= 2;
                        game.getTrashContainers().get(game.getPlayer().getTrashContainersLeft().get(rand)).setUnlocked(true);
                        movingToTrashcan = true;
                        xGoal = game.getTrashContainers().get(game.getPlayer().getTrashContainersLeft().get(rand)).getIniX() - 256 + game.getTrashContainers().get(game.getPlayer().getTrashContainersLeft().get(rand)).getWidth() / 2;
                        yGoal = game.getTrashContainers().get(game.getPlayer().getTrashContainersLeft().get(rand)).getIniY() - 256 + game.getTrashContainers().get(game.getPlayer().getTrashContainersLeft().get(rand)).getHeight() / 2;
                        if (xGoal < 0) {
                            xGoal = 0;
                        } else if (xGoal > 3584) {
                            xGoal = 3584;
                        }
                        if (yGoal < 0) {
                            yGoal = 0;
                        } else if (yGoal > 3584) {
                            yGoal = 3584;
                        }

                        game.getPlayer().getTrashContainersLeft().remove(rand);

                    } else if ((keyManager.space && !keyManager.helperSpace) && (game.getPlayer().getMoney() < binsUpPrice)) {
                        Assets.pickDenied.play();
                    }
                    break;
                case 2:
                    if ((keyManager.space && !keyManager.helperSpace) && (game.getPlayer().getMoney() >= trashValueUpPrice)) {
                        Assets.moneySound.play();
                        game.getPlayer().setTrashUpgrade(game.getPlayer().getTrashUpgrade() + 1); // increase the level of upgrade that the player saves
                        game.getPlayer().setMoney(game.getPlayer().getMoney() - trashValueUpPrice);
                        this.trashValueUpPrice *= 2;
                    } else if ((keyManager.space && !keyManager.helperSpace) && (game.getPlayer().getMoney() < trashValueUpPrice)) {
                        Assets.pickDenied.play();
                    }
                    break;
                case 3:
                    if ((keyManager.space && !keyManager.helperSpace) && (game.getPlayer().getMoney() >= backPackUpPrice)) {
                        Assets.moneySound.play();
                        game.getPlayer().setCapacity(game.getPlayer().getCapacity() + 10);
                        game.getPlayer().setMoney(game.getPlayer().getMoney() - backPackUpPrice);
                        game.getPlayer().setCapacityUpgrade(game.getPlayer().getCapacityUpgrade() + 1);  // increase the level of upgrade that the player saves
                        this.backPackUpPrice *= 2;
                    } else if ((keyManager.space && !keyManager.helperSpace) && (game.getPlayer().getMoney() < backPackUpPrice)) {
                        Assets.pickDenied.play();
                    }
                    break;
                case 4:
                    if ((keyManager.space && !keyManager.helperSpace) && (game.getPlayer().getMoney() >= sneakersUpPrice)) {
                        Assets.moneySound.play();
                        game.getPlayer().setSpeed(game.getPlayer().getSpeed() + 1);
                        game.getPlayer().setMoney(game.getPlayer().getMoney() - sneakersUpPrice);
                        game.getPlayer().setSpeedUpgrade(game.getPlayer().getSpeedUpgrade() + 1); // increase the level of upgrade that the player saves
                        this.sneakersUpPrice *= 2;
                    } else if ((keyManager.space && !keyManager.helperSpace) && (game.getPlayer().getMoney() < sneakersUpPrice)) {
                        Assets.pickDenied.play();
                    }
                    break;
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
            if (showTrashcan > 0) {
                showTrashcan--;
                g.drawImage(Assets.background, 0, 0, game.getWidth(), game.getHeight(), x, y, x + 512, y + 512, Color.black, null);
                for (int i = 0; i < game.getTrashContainers().size(); i++) {
                    game.getTrashContainers().get(i).renderForStore(g, x, y);
                }
                if (showTrashcan <= 0) {
                    x = 3551;
                    y = 2179;
                }
            } else if (movingToTrashcan) {
                if (x < xGoal) {
                    x += 10;
                    if (x > xGoal) {
                        x = xGoal;
                    }
                } else if (x > xGoal) {
                    x -= 10;
                    if (x < xGoal) {
                        x = xGoal;
                    }
                }
                if (y < yGoal) {
                    y += 10;
                    if (y > yGoal) {
                        y = yGoal;
                    }
                } else if (y > yGoal) {
                    y -= 10;
                    if (y < yGoal) {
                        y = yGoal;
                    }
                }

                if (x == xGoal && y == yGoal) {
                    showTrashcan = 100;
                    movingToTrashcan = false;
                }

                g.drawImage(Assets.background, 0, 0, game.getWidth(), game.getHeight(), x, y, x + 512, y + 512, Color.black, null);
                for (int i = 0; i < game.getTrashContainers().size(); i++) {
                    game.getTrashContainers().get(i).renderForStore(g, x, y);
                }
            } else {
                g.drawImage(animation.getCurrentFrame(), 0, 0, width, height, null);
                //Score values por painting
                g.setFont(fontx);
                g.setColor(Color.BLACK);
                g.getFont().isBold();
                
                //Draws messages
                g.drawImage(coinAnimation.getCurrentFrame(), 10, 470, 30, 30, null);
                DecimalFormat dform = new DecimalFormat("0.00");
                g.drawString("" + dform.format(game.getPlayer().getMoney()), 45, 492);

                g.drawString("$" + dform.format(sneakersUpPrice), 352, 170);
                g.drawString("$" + dform.format(backPackUpPrice), 352, 250);
                g.drawString("$" + dform.format(trashValueUpPrice), 352, 330);
                g.drawString("$" + dform.format(binsUpPrice), 352, 410);
                g.setFont(g.getFont().deriveFont(12f));
                g.drawString("Mejora tu velocidad un 100%", 160, 150);
                g.drawString("Convierte un bote a reciclable", 160, 390);
                g.drawString("Multiplica X2 el valor", 160, 310);
                 g.drawString("de la basura", 160, 310+ g.getFontMetrics().getHeight());
                g.drawString("Aumenta un 100% la capacidad", 160, 230);
                g.drawString("de tu mochila", 160, 230 + g.getFontMetrics().getHeight());
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
