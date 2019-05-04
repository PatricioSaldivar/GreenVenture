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
 * @author yeyog
 */
public class RecycleCo implements Runnable {
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
    private int index = 0;                  // to manage the index of the animations
    private Animation animation;            // to store the animations
    private MainMenu game;                      //To store the game in which it was before
    private int indexHelper=10;                 // To store an index helper to know when the game need to make noise
    private double glassPrice = 0.03;           // To store the original price
    private double aluminumPrice = 0.04;        // To store the original price
    private double plasticPrice = 0.02;         // To store the original price
    private double paperPrice = 0.02;           // To store the original price
    private double electronicsPrice = 0.05;     // To store the original price
    private double organicPrice = 0.01;         // To store the original price



    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public RecycleCo(String title, int width, int height, Display display, KeyManager keyManager, MainMenu game) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        this.keyManager = keyManager;
        this.display = display;
        display.setTitle("RecycleCo");
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


    /**
     * initializing the display window of the game
     */
    private void init() {
        Assets.init();
        animation = new Animation(Assets.recycleCoReturn,300);

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
         switch(index) {
            //Return Menu Selection
                    case 0:
                       if(indexHelper != 0){
                           animation = new Animation(Assets.recycleCoReturn, 300);
                           Assets.selectSound.play();
                           indexHelper= index;
                       }
                       if(keyManager.down && !keyManager.helperDown){
                           index = 3;
                           break;
                       }
                       if(keyManager.up && !keyManager.helperUp){
                           index = 1;
                           break;
                       }
                        break;
            //Organics Menu Selection
                    case 1:
                        if(indexHelper != 1){
                           animation = new Animation(Assets.recycleCoOrganics, 300);
                           Assets.selectSound.play();
                           indexHelper= index;
                        }
                        if(keyManager.down && !keyManager.helperDown){
                           index = 0;
                           break;
                        }
                        if((keyManager.left && !keyManager.helperLeft) || (keyManager.right && !keyManager.helperRight)){
                           index = 4;
                           break;
                        }
                        if(keyManager.up && !keyManager.helperUp){
                           index = 2;
                           break;
                       }
                        
                      
                        break;
            //Electronics Menu Selection            
                    case 2:
                        if(indexHelper != 2){
                            animation = new Animation(Assets.recycleCoElectronics, 300);
                            Assets.selectSound.play();
                            indexHelper= index;
                        }
                        if(keyManager.down && !keyManager.helperDown){
                           index = 1;
                           break;
                        }
                        if((keyManager.left && !keyManager.helperLeft) || (keyManager.right && !keyManager.helperRight)){
                           index = 5;
                           break;
                        }
                        if(keyManager.up && !keyManager.helperUp){
                           index = 3;
                           break;
                        }

                        
                        break;
            //Aluminum Menu Selection
                    case 3:
                        if(indexHelper != 3){
                            animation = new Animation(Assets.recycleCoAluminum, 300);
                            Assets.selectSound.play();
                            indexHelper= index;
                        }
                        if(keyManager.down && !keyManager.helperDown){
                           index = 2;
                           break;
                        }
                        if((keyManager.left && !keyManager.helperLeft) || (keyManager.right && !keyManager.helperRight)){
                           index = 6;
                           break;
                        }
                        if(keyManager.up && !keyManager.helperUp){
                           index = 0;
                           break;
                        }
                        
                        break;
            //Paper Menu Selection
                    case 4:
                        if(indexHelper != 4){
                            animation = new Animation(Assets.recycleCoPaper, 300);
                            Assets.selectSound.play();
                            indexHelper= index;
                        }
                        if(keyManager.down && !keyManager.helperDown){
                           index = 0;
                           break;
                        }
                        if((keyManager.left && !keyManager.helperLeft) || (keyManager.right && !keyManager.helperRight)){
                           index = 1;
                           break;
                        }
                        if(keyManager.up && !keyManager.helperUp){
                           index = 5;
                           break;
                        }
                        
                        break;
            //Plastic Menu Selection
                    case 5:
                        if(indexHelper != 5){
                            animation = new Animation(Assets.recycleCoPlastic, 300);
                            Assets.selectSound.play();
                            indexHelper= index;
                        }
                        if(keyManager.down && !keyManager.helperDown){
                           index = 4;
                           break;
                        }
                        if((keyManager.left && !keyManager.helperLeft) || (keyManager.right && !keyManager.helperRight)){
                           index = 2;
                           break;
                        }
                        if(keyManager.up && !keyManager.helperUp){
                           index = 6;
                           break;
                        }
                    
                        break;
            //Glass Menu Selection
                    case 6:
                        if(indexHelper != 6){
                            animation = new Animation(Assets.recycleCoGlass, 300);
                            Assets.selectSound.play();
                            indexHelper= index;
                        }
                        if(keyManager.down && !keyManager.helperDown){
                           index = 5;
                           break;
                        }
                        if((keyManager.left && !keyManager.helperLeft) || (keyManager.right && !keyManager.helperRight)){
                           index = 3;
                           break;
                        }
                        if(keyManager.up && !keyManager.helperUp){
                           index = 4;
                           break;
                        }
                    
                        break;
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
            //Score values por painting
            g.setFont(fontx);
            g.setColor(Color.BLACK);
            g.getFont().isBold();
            g.getFont().deriveFont(36f);
            //Draws the prices and the quantity of the trash the player has in his inventory
           // g.drawString("X "+ (game.getPlayer().getGlass) ,110, 160);
           // g.drawString("X "+ (game.getPlayer().getAluminum)   ,110, 160);
           // g.drawString("X "+ (game.getPlayer().getPlastic)  ,110, 160);
           // g.drawString("X "+ (game.getPlayer().getPaper)  ,110, 160);
           // g.drawString("X "+ (game.getPlayer().getElectronics)   ,110, 160);
           // g.drawString("X "+ (game.getPlayer().getOrganic)  ,110, 160);
            
           // g.drawString("$ "+ ( ((glassPrice) * game.getPlayer().getUpgrade() ) * game.getPlayer.getGlass()  ,110, 160);
           // g.drawString("$ "+ ( ((aluminumPrice)  * game.getPlayer().getUpgrade()) * game.getPlayer.getAluminum() ,110, 160);
           // g.drawString("$ "+ ( ((plasticPrice)  * game.getPlayer().getUpgrade()) * game.getPlayer.getPlastic()   ,110, 160);
           // g.drawString("$ "+ ( ((paperPrice)  * game.getPlayer().getUpgrade()) game.getPlayer.getPaper()   ,110, 160);
           // g.drawString("$ "+ ( ((electronicsPrice)  * game.getPlayer().getUpgrade() ) game.getPlayer.getElectronics()   ,110, 160);
           // g.drawString("$ "+ ( ((organicPrice)  * game.getPlayer().getUpgrade() ) game.getPlayer.getOrganic()   ,110, 160);
            
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
