/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BonfireStudios
 */
public class Game implements Runnable {

    private BufferStrategy bs;                  // to have several buffers when displaying
    private Graphics g;                         // to paint objects
    private Display display;                    // to display in the game
    String title;                               // title of the window
    private int width;                          // width of the window
    private int height;                         // height of the window
    private Thread thread;                      // thread to create the game
    private boolean running;                    // to set the game
    private Player player;                      // to use a player
    private KeyManager keyManager;              // to manage the keyboard
    private Screen screen;                      // to manage screen
    private ArrayList<NPC> npcs;                // to manage all items in the game
    private Font fontx;                         // to manage a custom font
    private LinkedList<Trash> trash;            // to manage the trash taht is in the game
    private Animation animation;                // to manage the animations of the objects
    private int pauseIndex = 5;                 // to storw the index of the pause selector
    private boolean cont = false;               // to continue the game
    private ArrayList<Solid> solids;            // to store all the solids
    private boolean loaded;                     // to load the game
    private NPCMinigame1 npcTrashClassify;      // to manage the NPC what gives you the TrashClassify Minigame
    private ArrayList<Crosswalk> crosswalks;    // to manage the walkers of the street;
    private ArrayList<roadChange> roadChanges;  // used to change direction of a car
    private Car car;                            // used to create the car
    private ArrayList<StoreDoor> storeDoors; // to manage the doors for the stores

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
        solids = new ArrayList<>();
        crosswalks = new ArrayList<>();
        roadChanges = new ArrayList<>();
        storeDoors = new ArrayList<>();
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setNpcs(ArrayList<NPC> npcs) {
        this.npcs = npcs;
    }

    public void setTrash(LinkedList<Trash> trash) {
        this.trash = trash;
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

    public NPCMinigame1 getNpcTrashClassify() {
        return npcTrashClassify;
    }

    public void setCont(boolean cont) {
        this.cont = cont;
    }

    public ArrayList<Solid> getSolids() {
        return solids;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public Screen getScreen() {
        return screen;
    }

    public ArrayList<Crosswalk> getCrosswalks() {
        return crosswalks;
    }

    public void setWalkers(ArrayList<Crosswalk> crosswalks) {
        this.crosswalks = crosswalks;
    }

    public ArrayList<roadChange> getRoadChanges() {
        return roadChanges;
    }

    public void setRoadChanges(ArrayList<roadChange> roadChanges) {
        this.roadChanges = roadChanges;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    

    /**
     * initializing the display window of the game
     */
    private void init() {
        Assets.init();
        if (!loaded) {
            player = new Player(40, 40, 64, 64, this);
        }
        screen = new Screen(0, 0, width, height, this, player, trash);
        npcs.add(new NPC(350, 350, 64, 64, 0, this, screen, 0));
        npcs.add(new NPC(350, 350, 64, 64, 0, this, screen, 1));
        npcs.add(new NPC(350, 350, 64, 64, 0, this, screen, 2));
        car = new Car(512, 320, 128, 128, screen, this);
        car.setDirection(2);
        npcTrashClassify = new NPCMinigame1(1000, 1000, 64, 64, this, screen, 10);
        animation = new Animation(Assets.pausaSave, 300);
        keyManager.setPauseMax(4);
        
        //Creates doors to enter to the stores
        storeDoors.add(new StoreDoor(0, 0, 10, 10, this, screen, 0));           // TodoxMart door
        storeDoors.add(new StoreDoor(100, 0, 10, 10, this, screen, 1));         // RecycleCo door    
        


        

        //Boundaries of Map
        solids.add(new Solid(0, -32, 4096, 32, screen));
        solids.add(new Solid(-32, 0, 32, 4096, screen));
        solids.add(new Solid(4096, 0, 32, 4096, screen));
        solids.add(new Solid(0, 4096, 4096, 32, screen));

        //Arbol parque norte izquierdo
        solids.add(new Solid(24, 280, 143, 187, screen));
        //Arbol parque norte derecho
        solids.add(new Solid(930, 0, 143, 187, screen));
        
        //Cave North
        solids.add(new Solid(787, 0, 143, 105, screen));
        solids.add(new Solid(787, 105, 23, 23, screen));
        solids.add(new Solid(887, 105, 43, 23, screen));
        
        //WaterFountain main plaza
        solids.add(new Solid(848, 932, 97, 34, screen));
        solids.add(new Solid(861, 901, 71, 79, screen));
        //Tacos Y Mas
        solids.add(new Solid(0, 1669, 441, 275, screen)); // Restaurant
        solids.add(new Solid(226, 1945, 120, 37, screen)); // Restauran's door
        solids.add(new Solid(73, 1944, 88, 37, screen)); //Vending

        //Mesas Tacos y Mas
        solids.add(new Solid(88, 2068, 5, 3, screen));
        solids.add(new Solid(215, 2068, 5, 3, screen));
        solids.add(new Solid(349, 2068, 5, 3, screen));
        solids.add(new Solid(88, 2174, 5, 3, screen));
        solids.add(new Solid(215, 2174, 5, 3, screen));
        solids.add(new Solid(349, 2174, 5, 3, screen));

        //Observatorio
        solids.add(new Solid(65, 2560, 296, 192, screen));
        solids.add(new Solid(84, 2501, 250, 271, screen));

        //Trees south park
        solids.add(new Solid(128, 2840, 25, 95, screen));
        solids.add(new Solid(106, 2858, 70, 77, screen));

        solids.add(new Solid(266, 2840, 25, 95, screen));
        solids.add(new Solid(244, 2858, 70, 77, screen));

        solids.add(new Solid(410, 2840, 25, 95, screen));
        solids.add(new Solid(388, 2858, 70, 77, screen));

        solids.add(new Solid(562, 2840, 25, 95, screen));
        solids.add(new Solid(539, 2858, 70, 77, screen));

        solids.add(new Solid(719, 2840, 25, 95, screen));
        solids.add(new Solid(696, 2858, 70, 77, screen));

        //Bridge
        solids.add(new Solid(507, 3281, 10, 398, screen));
        solids.add(new Solid(634, 3281, 10, 398, screen));

        //Left South Corener Trees
        solids.add(new Solid(0, 3504, 86, 592, screen));

        //Left Corner Bushes
        solids.add(new Solid(0, 4071, 1031, 25, screen));
        //Minibushes close to water
        solids.add(new Solid(211, 3561, 25, 5, screen));
        solids.add(new Solid(420, 3561, 86, 5, screen));
        solids.add(new Solid(673, 3561, 25, 5, screen));

        //Water
        solids.add(new Solid(0, 3404, 506, 60, screen));
        solids.add(new Solid(645, 3404, 461, 60, screen));

        solids.add(new Solid(1046, 3428, 78, 669, screen));

        //Cueva 
        //*********************************************Por el momento solo es solido*
        solids.add(new Solid(908, 3464, 138, 130, screen));
        solids.add(new Solid(908, 3594, 24, 15, screen));
        solids.add(new Solid(1002, 3594, 44, 15, screen));

        //Lake
        solids.add(new Solid(1124, 3889, 618, 206, screen));
        solids.add(new Solid(1312, 3286, 429, 603, screen));
        solids.add(new Solid(1369, 3227, 262, 58, screen));
        solids.add(new Solid(1711, 3231, 31, 54, screen));

        //Truck
        solids.add(new Solid(2852, 3469, 152, 82, screen));

        //Right South Corner Buildings 
        solids.add(new Solid(3854, 2752, 243, 1087, screen));
        solids.add(new Solid(3932, 3839, 95, 22, screen));
        

        //Right South Corner Trees
        solids.add(new Solid(3657, 2821, 120, 377, screen));

        //Soccer Court Fence
        solids.add(new Solid(2122, 2818, 562, 6, screen));
        solids.add(new Solid(2120, 2818, 2, 404, screen));
        solids.add(new Solid(2120, 3220, 447,2, screen));
        solids.add(new Solid(2665, 3220, 43, 2, screen));
        //Soccer Goals
        solids.add(new Solid(2122, 2928, 80, 132, screen));
        solids.add(new Solid(2606, 2928, 114, 134, screen));
        
        //School behind soccer court
        solids.add(new Solid(2684, 2818, 36, 110, screen));
        solids.add(new Solid(2720, 2818, 314, 370, screen));
        solids.add(new Solid(3034, 2818, 80, 350, screen));
        solids.add(new Solid(3119, 2818, 314, 369, screen));
        solids.add(new Solid(3433, 2818, 29, 225, screen));
        solids.add(new Solid(2779, 3188, 255, 34, screen));
        solids.add(new Solid(3119, 3187, 275, 35, screen));

        //Mansion Bushes
        solids.add(new Solid(2126, 1682, 40, 835, screen));
        solids.add(new Solid(2166, 1682, 303, 14, screen));
        solids.add(new Solid(2469, 1682, 39, 61, screen));
        solids.add(new Solid(2517, 1670, 153, 93, screen));
        solids.add(new Solid(2576, 1773, 36, 20, screen));
        solids.add(new Solid(2941, 1670, 153, 93, screen));
        solids.add(new Solid(3000, 1773, 36, 20, screen));
        solids.add(new Solid(3102, 1682, 39, 61, screen));
        solids.add(new Solid(3141, 1682, 303, 14, screen));
        solids.add(new Solid(3445, 1682, 34, 834, screen));

        solids.add(new Solid(2234, 1792, 10, 7, screen));
        solids.add(new Solid(2280, 1944, 10, 7, screen));
        solids.add(new Solid(2207, 2081, 10, 7, screen));
        solids.add(new Solid(2281, 2299, 10, 7, screen));
        solids.add(new Solid(2221, 2454, 10, 7, screen));

        solids.add(new Solid(3364, 1808, 10, 7, screen));
        solids.add(new Solid(3315, 1963, 10, 7, screen));
        solids.add(new Solid(3389, 2101, 10, 7, screen));
        solids.add(new Solid(3315, 2318, 10, 7, screen));
        solids.add(new Solid(3376, 2472, 10, 7, screen));

        //Mansion house
        solids.add(new Solid(2327, 1929, 353, 588, screen));
        solids.add(new Solid(2679, 1929, 256, 474, screen));
        solids.add(new Solid(2936, 1929, 348, 588, screen));
        solids.add(new Solid(2679, 2403, 69, 31, screen));
        solids.add(new Solid(2867, 2403, 70, 31, screen));
        
        //Todo Mart
        solids.add(new Solid(3550, 2040, 522, 447, screen));
        solids.add(new Solid(3550, 2487, 219, 27, screen));
        solids.add(new Solid(3850, 2487, 222, 27, screen));
        //Fences Todo Mart
        solids.add(new Solid(3550, 1861, 2, 179, screen));
        solids.add(new Solid(3550, 1809, 192, 52, screen));
        solids.add(new Solid(3858, 1809, 238, 52, screen));
        solids.add(new Solid(4048, 1861, 2, 179, screen));
        
        //Marketplace
        //Right Store
        solids.add(new Solid(3814, 517, 282, 491, screen));
        solids.add(new Solid(3814, 1008, 72, 6, screen));
        solids.add(new Solid(4027, 1008, 69, 6, screen));
        solids.add(new Solid(3846, 1014, 8, 77, screen));
        solids.add(new Solid(4071, 1014, 8, 77, screen));
        solids.add(new Solid(3819, 1091, 79, 4, screen));
        solids.add(new Solid(4025, 1091, 71, 4, screen));
        solids.add(new Solid(3768, 923, 46, 136, screen));
        
        //Left Store
        solids.add(new Solid(2118, 517, 297, 491, screen));
        solids.add(new Solid(2118, 1008, 72, 6, screen));
        solids.add(new Solid(2342, 1008, 73, 6, screen));
        solids.add(new Solid(2150, 1014, 8, 77, screen));
        solids.add(new Solid(2375, 1014, 8, 77, screen));
        solids.add(new Solid(2123, 1091, 79, 4, screen));
        solids.add(new Solid(2327, 1091, 88, 4, screen));
        solids.add(new Solid(2415, 917, 46, 136, screen));
        
        //Mall
        solids.add(new Solid(2590, 512, 1048, 384, screen));
        solids.add(new Solid(2590, 896, 177, 78, screen));
        solids.add(new Solid(2767, 896, 16, 106, screen));
        solids.add(new Solid(2783, 896, 133, 30, screen));
        solids.add(new Solid(2783, 926, 22, 29, screen));
        solids.add(new Solid(2897, 926, 19, 29, screen));
        solids.add(new Solid(2916, 896, 18, 106, screen));
        solids.add(new Solid(2934, 896, 132, 90, screen));
        solids.add(new Solid(2934, 986, 135, 16, screen));
        solids.add(new Solid(3046, 102, 23, 24, screen));
        solids.add(new Solid(3066, 896, 98, 90, screen));
        solids.add(new Solid(3164, 896, 148, 90, screen));
        solids.add(new Solid(3161, 986, 151, 16, screen));
        solids.add(new Solid(3161, 1002, 23, 24, screen));
        solids.add(new Solid(3312, 896, 133, 30, screen));
        solids.add(new Solid(3312, 926, 22, 29, screen));
        solids.add(new Solid(3426, 926, 19, 29, screen));
        solids.add(new Solid(3445, 896, 16, 103, screen));
        solids.add(new Solid(3461, 896, 177, 78, screen));
        
        //Residetial Mini Houses
        solids.add(new Solid(2123, 0, 1973, 190, screen));
        solids.add(new Solid(2123, 190, 207, 21, screen));
        solids.add(new Solid(2406, 190, 285, 21, screen));
        solids.add(new Solid(2763, 190, 210, 21, screen));
        solids.add(new Solid(3046, 190, 206, 21, screen));
        solids.add(new Solid(3325, 190, 456, 21, screen));
        solids.add(new Solid(3853, 190, 243, 21, screen));
        
        //Blue Skycraper
        solids.add(new Solid(1354, 0, 494, 57, screen));
        solids.add(new Solid(1371, 57, 460, 20, screen));
        solids.add(new Solid(1386, 77, 430, 20, screen));
        solids.add(new Solid(1408, 97, 386, 20, screen));
        solids.add(new Solid(1427, 117,348, 16, screen));
        solids.add(new Solid(1427, 133,32, 50, screen));
        solids.add(new Solid(1743, 133,32, 50, screen));
        solids.add(new Solid(1546, 133,108, 50, screen));
        solids.add(new Solid(1546, 183,12, 27, screen));
        solids.add(new Solid(1642, 183,12, 27, screen));
        
        //
        

        
        //Solids for roadChangers
        solids.add(new Solid(1167, 326, 98, 82, screen));

        //CrossWalks
        //First from left to right north (Complete square)
        crosswalks.add(new Crosswalk(1095, 310, 57, 138, this, screen));
         
        //RoadChanges
        int possibleDirections[];

        possibleDirections = new int[2];
        possibleDirections[0] = 1;
        possibleDirections[1] = 3;
        roadChanges.add(new roadChange(1142, 310, 148, 148, screen, possibleDirections));

        possibleDirections = new int[1];
        possibleDirections[0] = 2;
        roadChanges.add(new roadChange(502, 310, 148, 148, screen, possibleDirections));

        possibleDirections = new int[2];
        possibleDirections[0] = 2;
        possibleDirections[1] = 3;
        roadChanges.add(new roadChange(502, 630, 148, 148, screen, possibleDirections));

        possibleDirections = new int[2];
        possibleDirections[0] = 4;
        possibleDirections[1] = 2;
        roadChanges.add(new roadChange(502, 1462, 148, 148, screen, possibleDirections));

        possibleDirections = new int[2];
        possibleDirections[0] = 2;
        possibleDirections[1] = 3;
        roadChanges.add(new roadChange(502, 2294, 148, 148, screen, possibleDirections));

        possibleDirections = new int[1];
        possibleDirections[0] = 4;
        roadChanges.add(new roadChange(502, 2614, 148, 148, screen, possibleDirections));

        possibleDirections = new int[2];
        possibleDirections[0] = 4;
        possibleDirections[1] = 1;
        roadChanges.add(new roadChange(1142, 1462, 148, 148, screen, possibleDirections));

        possibleDirections = new int[2];
        possibleDirections[0] = 4;
        possibleDirections[1] = 1;
        roadChanges.add(new roadChange(1142, 2614, 148, 148, screen, possibleDirections));

        possibleDirections = new int[2];
        possibleDirections[0] = 3;
        possibleDirections[1] = 1;
        roadChanges.add(new roadChange(1910, 310, 148, 148, screen, possibleDirections));

        possibleDirections = new int[2];
        possibleDirections[0] = 4;
        possibleDirections[1] = 1;
        roadChanges.add(new roadChange(1910, 1462, 148, 148, screen, possibleDirections));

        possibleDirections = new int[2];
        possibleDirections[0] = 4;
        possibleDirections[1] = 1;
        roadChanges.add(new roadChange(1910, 2614, 148, 148, screen, possibleDirections));

        possibleDirections = new int[1];
        possibleDirections[0] = 1;
        roadChanges.add(new roadChange(1910, 3958, 148, 148, screen, possibleDirections));

        possibleDirections = new int[1];
        possibleDirections[0] = 1;
        roadChanges.add(new roadChange(1910, 3319, 148, 148, screen, possibleDirections));

        possibleDirections = new int[1];
        possibleDirections[0] = 3;
        roadChanges.add(new roadChange(3637, 3319, 148, 148, screen, possibleDirections));

        possibleDirections = new int[2];
        possibleDirections[0] = 3;
        possibleDirections[1] = 1;
        roadChanges.add(new roadChange(3637, 3948, 148, 148, screen, possibleDirections));

    }

    @Override
    public void run() {
        //If the game is called to resume the init wont be called 
        if (!cont) {
            init();
        } else {
            animation = new Animation(Assets.pausaSave, 300);
            keyManager.setPauseMax(4);
            //this code may be used on the return to game function for better eficiency
            keyManager.pause = false;

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
            for (int i = 0; i < crosswalks.size(); i++) {
                crosswalks.get(i).tick();
            }
            for (int i = 0; i < roadChanges.size(); i++) {
                roadChanges.get(i).tick();
            }

            for(int i = 0; i < storeDoors.size(); i++){
                storeDoors.get(i).tick();
            }
            
            car.tick();
       
            for (int i = 0; i < npcs.size(); i++) {
                npcs.get(i).tick();
                if (!npcs.get(i).isTalking()) {
                    if (npcs.get(i).getPerimetro().intersects(player.getPerimetro()) && npcs.get(i).isJustThrowedTrash() && player.isPick() && !player.isConversation()) {
                        npcs.get(i).setTalking(true);
                        player.setConversation(true);
                        screen.setFinishedConversationText(false);
                        screen.setConversationTextIndex(0);
                    }

                } else if (player.isPick() && player.isConversation()) {

                    if (!player.isTalking()) {
                        if (!screen.isFinishedConversationText()) {
                            screen.setFinishedConversationText(true);
                        } else {
                            player.setTalking(true);
                            screen.setConversationTextIndex(0);
                            screen.setFinishedConversationText(false);

                        }
                    } else {
                        if (!screen.isFinishedConversationText()) {
                            screen.setFinishedConversationText(true);
                        } else {
                            player.setConversation(false);
                            npcs.get(i).setTalking(false);
                            player.setTalking(false);
                        }
                    }

                }
            }

            npcTrashClassify.tick();
            player.tick();


            
            //If player intersects the TodoMart door
            if (player.getPerimetro().intersects(storeDoors.get(0).getPerimetro()) && keyManager.space && !keyManager.helperSpace) {
                    TodoMartRoom tmr = new TodoMartRoom("TodoxMartRoom",512,512,display,keyManager,this,player);
                    tmr.start();
                    // TodoMart tm = new TodoMart("TodoxMart", 512, 512, display, keyManager, this);
                    //tm.start();
                    running = false;
            }
            //If player intersects the RecycleCo door
            if (player.getPerimetro().intersects(storeDoors.get(1).getPerimetro()) && keyManager.space && !keyManager.helperSpace) {
                    RecycleCo rc = new RecycleCo("RecycleCo", 512, 512, display, keyManager, this);
                    rc.start();
                    running = false;
            }

            //If player intersects the npc and press space the minigame starts, but first they start a conversation
            if (player.getPerimetro().intersects(npcTrashClassify.getPerimetro())) {
                if (keyManager.space && !keyManager.helperSpace) {
                    if (!player.isConversation()) {
                        npcTrashClassify.setTalking(true);
                        player.setConversation(true);
                        screen.setFinishedConversationText(false);
                        screen.setConversationTextIndex(0);
                    } else if (player.isPick() && player.isConversation()) {
                        if (!player.isTalking()) {
                            if (!screen.isFinishedConversationText()) {
                                screen.setFinishedConversationText(true);
                            } else {
                                player.setTalking(true);
                                screen.setConversationTextIndex(0);
                                screen.setFinishedConversationText(false);
                            }
                        } else {

                            player.setConversation(false);
                            npcTrashClassify.setTalking(false);
                            player.setTalking(false);
                            if (screen.isCursorOnPlay()) {
                                MinigameTrashClassify mct = new MinigameTrashClassify("Minigame", 512, 512, display, keyManager, this);
                                mct.start();
                                running = false;
                            }

                        }

                    }
                }
                if ((!keyManager.helperUp && keyManager.up) || (!keyManager.helperDown && keyManager.down)) {
                    screen.setCursorOnPlay(!screen.isCursorOnPlay());
                }

            }

        } else {
            animation.tick();
            if (pauseIndex != keyManager.pauseSelector) {
                switch (keyManager.pauseSelector) {
                    case 0:
                        animation = new Animation(Assets.pausaSave, 300);
                        Assets.selectSound.play();

                        break;
                    case 1:
                        animation = new Animation(Assets.pausaStats, 300);
                        Assets.selectSound.play();
                        break;
                    case 2:
                        animation = new Animation(Assets.pausaMenuInstructions, 300);
                        Assets.selectSound.play();
                        break;
                    case 3:
                        animation = new Animation(Assets.pausaMainMenu, 300);
                        Assets.selectSound.play();
                        break;
                }
                pauseIndex = keyManager.pauseSelector;
            }
            if (keyManager.space && pauseIndex == 1) {
                //TodoMart tm = new TodoMart("TodoMart", 512, 512,display,keyManager,this);
                //Assets.gameStart.play();
                //tm.start();
                //running = false;

            }
            if (keyManager.space && pauseIndex == 3) {
                MainMenu m = new MainMenu("MainMenu", 512, 512, display);
                Assets.gameStart.play();
                m.start();
                running = false;
            }
            if (keyManager.space && pauseIndex == 2) {

                RecycleCo r = new RecycleCo("Store", 512, 512, display, keyManager, this);

                Assets.gameStart.play();
                r.start();
                running = false;
            }
            if (keyManager.space && pauseIndex == 0) {

                Save s = new Save(this);

                try {
                    s.tick();

                } catch (SQLException ex) {
                    Logger.getLogger(Game.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
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
            npcTrashClassify.render(g);
            //Draws Pause image when pausing game
            if (keyManager.pause) {
                g.drawImage(animation.getCurrentFrame(), 0, 0, width, height, null);
            }
            /*
            g.drawRect(24-screen.getX(),280-screen.getY(),143,187);
            
            g.drawRect(930-screen.getX(),0-screen.getY(),143,187);
             
            g.drawRect(848-screen.getX(),932-screen.getY(),97,34);
            g.drawRect(861-screen.getX(),901-screen.getY(),71,79);
            g.drawRect(0-screen.getX(),1669-screen.getY(),441,275); //Tacos y mas
            g.drawRect(226-screen.getX(),1945-screen.getY(),120,37); //Tacos y mas door
            g.drawRect(88-screen.getX(),2068-screen.getY(),5,3);
            g.drawRect(215-screen.getX(),2068-screen.getY(),5,3);
            g.drawRect(349-screen.getX(),2068-screen.getY(),5,3);
            g.drawRect(88-screen.getX(),2174-screen.getY(),5,3);
            g.drawRect(215-screen.getX(),2174-screen.getY(),5,3);
            g.drawRect(349-screen.getX(),2174-screen.getY(),5,3);//              
            g.drawRect(73-screen.getX(), 1944-screen.getY(), 88, 37); //vending
            g.drawRect(0-screen.getX(), 1944-screen.getY(), 10, 37);//left building column
            
            
             */
            
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
