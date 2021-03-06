/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author BonfireStudios
 */
public class KeyManager implements KeyListener {
    
    public boolean up;      // flag to move up the player
    public boolean helperUp;// flag to mark the up just clicked
    public boolean helperDown;// flag to mark the down just clicked
    public boolean helperRight;// flag to mark the up just clicked
    public boolean helperLeft;// flag to mark the down just clicked
    public boolean down;    // flag to move down the player
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean pause = false;   //flag to pause the game
    public boolean space;   // flag to mark the space button
    public boolean helperSpace;   // flag to mark the space button just clicked
    public boolean menu;            
    public int pauseSelector=0;   //flag to select which pause selection of the pause is being selected
    public int pauseSelectorHelper;   //flag help the pause selector
    public int pauseMax;            //To store the maximum number of possble selections on a menu
    

    private boolean keys[];  // to store all the flags for every key

    public int getPauseMax() {
        return pauseMax;
    }

    public void setPauseMax(int pauseMax) {
        this.pauseMax = pauseMax;
    }
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        if(e.getKeyCode() == KeyEvent.VK_P){
            pause = !pause;
            pauseSelector=0;
        }
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        helperSpace = space;
        helperUp = up;
        helperDown = down;
        helperRight = right;
        helperLeft = left;
        
        space = keys[KeyEvent.VK_SPACE];
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        menu = keys[KeyEvent.VK_M];
        
       
        if(!helperDown && down){
            pauseSelectorHelper++;
        }
        if(!helperUp && up){
            pauseSelectorHelper--;
        }
        pauseSelector+= pauseSelectorHelper;
        if(pauseSelector<0){
            pauseSelector=pauseMax-1;
        }
        pauseSelector = pauseSelector%pauseMax;
        pauseSelectorHelper=0;
        
    }
}
