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
    private ArrayList<TrashContainer> trashContainers; //to manage all the trashContainers

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
        trashContainers = new ArrayList<>();
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

    public ArrayList<TrashContainer> getTrashContainers() {
        return trashContainers;
    }

    public void setTrashContainers(ArrayList<TrashContainer> trashContainers) {
        this.trashContainers = trashContainers;
    }

    public ArrayList<StoreDoor> getStoreDoors() {
        return storeDoors;
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
        npcs.add(new NPC(350, 350, 64, 64, this, screen, 0, 0));
        npcs.add(new NPC(350, 350, 64, 64, this, screen, 1, 0));
        npcs.add(new NPC(350, 350, 64, 64, this, screen, 2, 0));
        car = new Car(512, 320, 128, 128, screen, this);
        car.setDirection(2);
        npcTrashClassify = new NPCMinigame1(1000, 1000, 64, 64, this, screen, 10);
        animation = new Animation(Assets.pausaSave, 300);
        keyManager.setPauseMax(4);

        //Creates doors to enter stores
        //storeDoors.add(new StoreDoor(3768, 2487, 77, 67, this, screen, 0));           // TodoxMartRoom door
        //storeDoors.add(new StoreDoor(100, 0, 10, 10, this, screen, 1));         // RecycleCoRoom door    
        storeDoors.add(new StoreDoor(3768, 2487, 77, 67, this, screen, 0));     //TodoxMartRoom door
        storeDoors.add(new StoreDoor(193, 1339, 77, 67, this, screen, 1));         // RecycleCoRoom door    
        

        //Map boundaries
        solids.add(new Solid(0, -32, 4096, 32));
        solids.add(new Solid(-32, 0, 32, 4096));
        solids.add(new Solid(4096, 0, 32, 4096));
        solids.add(new Solid(0, 4096, 4096, 32));

        //North park
        //Left tree
        solids.add(new Solid(24, 280, 143, 187));
        //Right Tree
        solids.add(new Solid(930, 0, 143, 187));
        //Lights
        solids.add(new Solid(60, 7, 22, 43));
        solids.add(new Solid(272, 263, 22, 43));
        solids.add(new Solid(272, 477, 22, 43));
        //Trash Can
        solids.add(new Solid(277, 386, 12, 4));
        //Cave
        solids.add(new Solid(787, 0, 143, 105));
        solids.add(new Solid(787, 105, 23, 23));
        solids.add(new Solid(887, 105, 43, 23));

        //Main plaza
        //Fountain
        solids.add(new Solid(848, 932, 97, 34));
        solids.add(new Solid(861, 901, 71, 79));
        //Lights
        solids.add(new Solid(718, 519, 22, 41));
        solids.add(new Solid(718, 1233, 22, 41));
        solids.add(new Solid(1053, 519, 22, 41));
        solids.add(new Solid(1053, 1233, 22, 41));

        //Tacos Y Mas
        solids.add(new Solid(0, 1669, 443, 275)); // Restaurant
        solids.add(new Solid(228, 1944, 118, 37)); //Door
        solids.add(new Solid(0, 1944, 162, 37)); //Vending & Trash Can
        //Tables Tacos y Mas
        solids.add(new Solid(88, 2068, 5, 3));
        solids.add(new Solid(215, 2068, 5, 3));
        solids.add(new Solid(349, 2068, 5, 3));
        solids.add(new Solid(88, 2174, 5, 3));
        solids.add(new Solid(215, 2174, 5, 3));
        solids.add(new Solid(349, 2174, 5, 3));
        //Lights
        solids.add(new Solid(3, 2143, 18, 43));
        solids.add(new Solid(414, 2144, 22, 43));

        //Observatory
        solids.add(new Solid(65, 2560, 296, 192));
        solids.add(new Solid(84, 2501, 250, 271));

        //South West park
        solids.add(new Solid(128, 2840, 25, 95));
        solids.add(new Solid(106, 2858, 70, 77));

        solids.add(new Solid(266, 2840, 25, 95));
        solids.add(new Solid(244, 2858, 70, 77));

        solids.add(new Solid(410, 2840, 25, 95));
        solids.add(new Solid(388, 2858, 70, 77));

        solids.add(new Solid(562, 2840, 25, 95));
        solids.add(new Solid(539, 2858, 70, 77));

        solids.add(new Solid(719, 2840, 25, 95));
        solids.add(new Solid(696, 2858, 70, 77));

        //Bridge
        solids.add(new Solid(507, 3281, 10, 398));
        solids.add(new Solid(634, 3281, 10, 398));

        //South West park trees
        solids.add(new Solid(0, 3504, 86, 592));
        //Left Corner Bushes
        solids.add(new Solid(0, 4071, 1031, 25));
        //Minibushes close to water
        solids.add(new Solid(213, 3561, 23, 5));
        solids.add(new Solid(420, 3561, 86, 5));
        solids.add(new Solid(644, 3561, 54, 5));
        //Trash Can
        solids.add(new Solid(86, 3561, 59, 5));

        //Water
        solids.add(new Solid(0, 3404, 506, 60));
        solids.add(new Solid(645, 3404, 461, 60));

        solids.add(new Solid(1046, 3428, 78, 669));

        //South cave
        //*********************************************Por el momento solo es solido*
        solids.add(new Solid(908, 3464, 138, 130));
        solids.add(new Solid(908, 3594, 24, 15));
        solids.add(new Solid(1002, 3594, 44, 15));

        //Lake
        solids.add(new Solid(1124, 3889, 618, 206));
        solids.add(new Solid(1312, 3286, 429, 603));
        solids.add(new Solid(1369, 3227, 262, 58));
        solids.add(new Solid(1711, 3231, 31, 54));

        //Truck
        solids.add(new Solid(2852, 3469, 152, 82));

        //South East
        //Residential buildings
        solids.add(new Solid(3854, 2752, 243, 1087));
        solids.add(new Solid(3932, 3839, 95, 22));
        //Trees
        solids.add(new Solid(3632, 2848, 128, 347));
        solids.add(new Solid(3653, 2836, 86, 12));
        solids.add(new Solid(3675, 2817, 42, 19));

        //School
        solids.add(new Solid(2684, 2818, 36, 110));
        solids.add(new Solid(2720, 2818, 314, 370));
        solids.add(new Solid(3034, 2818, 80, 350));
        solids.add(new Solid(3119, 2818, 314, 369));
        solids.add(new Solid(3433, 2818, 29, 225));
        solids.add(new Solid(2779, 3188, 255, 34));
        solids.add(new Solid(3119, 3187, 275, 35));
        //Trash Can
        solids.add(new Solid(3184, 3222, 16, 21));
        //Soccer Court Fence
        solids.add(new Solid(2122, 2818, 562, 6));
        solids.add(new Solid(2120, 2818, 2, 404));
        solids.add(new Solid(2120, 3220, 447, 2));
        solids.add(new Solid(2665, 3220, 43, 2));
        //Soccer Goals
        solids.add(new Solid(2122, 2928, 80, 132));
        solids.add(new Solid(2606, 2928, 114, 134));

        //Purple Mansion
        //Bushes
        solids.add(new Solid(2126, 1670, 40, 847));
        solids.add(new Solid(2166, 1670, 303, 26));
        solids.add(new Solid(2469, 1670, 48, 73));
        solids.add(new Solid(2517, 1670, 153, 93));
        solids.add(new Solid(2576, 1773, 36, 20));
        solids.add(new Solid(2941, 1696, 153, 67));
        solids.add(new Solid(3000, 1773, 36, 20));
        solids.add(new Solid(3094, 1696, 47, 47));
        solids.add(new Solid(2941, 1670, 504, 26));
        solids.add(new Solid(3445, 1670, 34, 846));
        //Trash Can
        solids.add(new Solid(3402, 2512, 43, 4));

        solids.add(new Solid(2234, 1792, 10, 7));
        solids.add(new Solid(2280, 1944, 10, 7));
        solids.add(new Solid(2207, 2081, 10, 7));
        solids.add(new Solid(2281, 2299, 10, 7));
        solids.add(new Solid(2221, 2454, 10, 7));

        solids.add(new Solid(3364, 1808, 10, 7));
        solids.add(new Solid(3315, 1963, 10, 7));
        solids.add(new Solid(3389, 2101, 10, 7));
        solids.add(new Solid(3315, 2318, 10, 7));
        solids.add(new Solid(3377, 2472, 68, 7));
        //Mansion building
        solids.add(new Solid(2327, 1929, 353, 588));
        solids.add(new Solid(2679, 1929, 256, 474));
        solids.add(new Solid(2936, 1929, 348, 588));
        solids.add(new Solid(2679, 2403, 69, 31));
        solids.add(new Solid(2867, 2403, 70, 31));

        //Todo Mart
        solids.add(new Solid(3550, 2033, 522, 454));
        solids.add(new Solid(3550, 2487, 219, 27));
        solids.add(new Solid(3850, 2487, 222, 27));
        //Fences Todo Mart & Dumpsters
        solids.add(new Solid(3550, 1861, 2, 172));
        solids.add(new Solid(3550, 1809, 192, 52));
        solids.add(new Solid(3858, 1809, 238, 52));
        solids.add(new Solid(4048, 1861, 2, 172));

        //Marketplace
        //Right Store
        solids.add(new Solid(3814, 517, 282, 491));
        solids.add(new Solid(3814, 1008, 72, 6));
        solids.add(new Solid(4027, 1008, 69, 6));
        solids.add(new Solid(3846, 1014, 8, 77));
        solids.add(new Solid(4071, 1014, 8, 77));
        solids.add(new Solid(3819, 1091, 79, 4));
        solids.add(new Solid(4025, 1091, 71, 4));
        solids.add(new Solid(3768, 923, 46, 136));
        //Trash Can
        solids.add(new Solid(3780, 1078, 12, 4));

        //Left Store
        solids.add(new Solid(2118, 517, 297, 491));
        solids.add(new Solid(2118, 1008, 72, 6));
        solids.add(new Solid(2342, 1008, 73, 6));
        solids.add(new Solid(2150, 1014, 8, 77));
        solids.add(new Solid(2375, 1014, 8, 77));
        solids.add(new Solid(2123, 1091, 79, 4));
        solids.add(new Solid(2327, 1091, 88, 4));
        solids.add(new Solid(2415, 917, 46, 136));
        //Trash Can
        solids.add(new Solid(2437, 1078, 12, 4));

        //Mall
        solids.add(new Solid(2590, 512, 1048, 384));
        solids.add(new Solid(2590, 896, 177, 78));
        solids.add(new Solid(2767, 896, 16, 106));
        solids.add(new Solid(2783, 896, 133, 30));
        solids.add(new Solid(2783, 926, 22, 29));
        solids.add(new Solid(2897, 926, 19, 29));
        solids.add(new Solid(2916, 896, 18, 106));
        solids.add(new Solid(2934, 896, 132, 90));
        solids.add(new Solid(2934, 986, 135, 16));
        solids.add(new Solid(3046, 102, 23, 24));
        solids.add(new Solid(3066, 896, 98, 90));
        solids.add(new Solid(3164, 896, 148, 90));
        solids.add(new Solid(3161, 986, 151, 16));
        solids.add(new Solid(3161, 1002, 23, 24));
        solids.add(new Solid(3312, 896, 133, 30));
        solids.add(new Solid(3312, 926, 22, 29));
        solids.add(new Solid(3426, 926, 19, 29));
        solids.add(new Solid(3445, 896, 16, 103));
        solids.add(new Solid(3461, 896, 177, 78));

        //North East residential
        solids.add(new Solid(2123, 0, 1973, 190));
        solids.add(new Solid(2123, 190, 208, 21));
        solids.add(new Solid(2401, 190, 295, 21));
        solids.add(new Solid(2766, 190, 211, 21));
        solids.add(new Solid(3047, 190, 213, 21));
        solids.add(new Solid(3330, 190, 460, 21));
        solids.add(new Solid(3861, 190, 235, 21));
        //Trash Cans
        solids.add(new Solid(2114, 214, 12, 4));
        solids.add(new Solid(2850, 214, 12, 4));
        solids.add(new Solid(3160, 214, 12, 4));
        solids.add(new Solid(4066, 214, 12, 4));

        //Blue Skycraper
        solids.add(new Solid(1354, 0, 494, 57));
        solids.add(new Solid(1371, 57, 460, 20));
        solids.add(new Solid(1386, 77, 430, 20));
        solids.add(new Solid(1408, 97, 386, 20));
        solids.add(new Solid(1427, 117, 348, 16));
        solids.add(new Solid(1427, 133, 32, 50));
        solids.add(new Solid(1743, 133, 32, 50));
        solids.add(new Solid(1546, 133, 108, 50));
        solids.add(new Solid(1546, 183, 12, 27));
        solids.add(new Solid(1642, 183, 12, 27));

        //Transport station
        solids.add(new Solid(1346, 734, 508, 451));
        solids.add(new Solid(1346, 1185, 220, 19));
        solids.add(new Solid(1646, 1185, 208, 19));
        //Trasn Can
        solids.add(new Solid(1590, 1335, 19, 7));
        //Front lights
        solids.add(new Solid(1371, 1300, 31, 31));
        solids.add(new Solid(1389, 1274, 18, 7));
        solids.add(new Solid(1796, 1273, 10, 8));
        solids.add(new Solid(1816, 1288, 8, 41));
        //Fence 
        solids.add(new Solid(1346, 657, 4, 77));
        solids.add(new Solid(1346, 517, 4, 44));
        solids.add(new Solid(1350, 517, 500, 2));
        solids.add(new Solid(1850, 517, 4, 44));
        solids.add(new Solid(1850, 667, 4, 67));
        //Bikes
        solids.add(new Solid(1395, 550, 23, 5));
        solids.add(new Solid(1467, 563, 23, 5));
        solids.add(new Solid(1533, 571, 34, 5));
        solids.add(new Solid(1563, 619, 34, 2));
        solids.add(new Solid(1670, 596, 41, 2));
        solids.add(new Solid(1692, 567, 41, 29));
        solids.add(new Solid(1754, 609, 17, 2));
        //Trash Can
        solids.add(new Solid(1626, 532, 12, 4));

        //RecycleCo.
        solids.add(new Solid(153, 841, 154, 15));
        solids.add(new Solid(117, 856, 224, 15));
        solids.add(new Solid(80, 871, 298, 19));
        solids.add(new Solid(5, 890, 407, 449));
        solids.add(new Solid(5, 1339, 190, 20));
        solids.add(new Solid(269, 1339, 143, 20));

        //City Hall
        solids.add(new Solid(711, 1670, 370, 694));
        //Lights
        solids.add(new Solid(718, 2465, 22, 43));
        solids.add(new Solid(1053, 2465, 22, 43));

        //Gym
        solids.add(new Solid(1353, 1671, 494, 311));
        solids.add(new Solid(1528, 1982, 35, 82));
        solids.add(new Solid(1563, 1982, 160, 40));
        solids.add(new Solid(1723, 1982, 35, 87));
        solids.add(new Solid(1376, 2051, 39, 75));
        solids.add(new Solid(1376, 2406, 39, 75));
        solids.add(new Solid(1786, 2051, 39, 75));
        solids.add(new Solid(1786, 2406, 39, 75));
        solids.add(new Solid(1369, 2126, 52, 18));
        solids.add(new Solid(1779, 2126, 52, 18));
        solids.add(new Solid(1369, 2481, 52, 18));
        solids.add(new Solid(1779, 2481, 52, 18));
        //Trash Can
        solids.add(new Solid(1484, 1982, 44, 37));

        //Solids Street
        solids.add(new Solid(527, 326, 98, 210));
        solids.add(new Solid(527, 646, 98, 82));
        solids.add(new Solid(527, 841, 98, 533));
        solids.add(new Solid(527, 1478, 98, 82));
        solids.add(new Solid(527, 1674, 98, 856));
        solids.add(new Solid(527, 2630, 98, 82));
        solids.add(new Solid(0, 646, 433, 82));
        solids.add(new Solid(0, 1478, 433, 82));
        solids.add(new Solid(0, 2312, 433, 82));
        solids.add(new Solid(720, 326, 360, 82));
        solids.add(new Solid(720, 1478, 360, 82));
        solids.add(new Solid(720, 2630, 360, 82));
        solids.add(new Solid(1167, 0, 98, 216));
        solids.add(new Solid(1167, 326, 98, 82));
        solids.add(new Solid(1167, 518, 98, 856));
        solids.add(new Solid(1167, 1478, 98, 82));
        solids.add(new Solid(1167, 1674, 98, 856));
        solids.add(new Solid(1167, 2630, 98, 82));
        solids.add(new Solid(1360, 326, 488, 82));
        solids.add(new Solid(1360, 1478, 488, 82));
        solids.add(new Solid(1360, 2630, 488, 82));
        solids.add(new Solid(1935, 0, 98, 216));
        solids.add(new Solid(1935, 326, 98, 82));
        solids.add(new Solid(1935, 518, 98, 856));
        solids.add(new Solid(1935, 1478, 98, 82));
        solids.add(new Solid(1935, 1674, 98, 856));
        solids.add(new Solid(1935, 2630, 98, 82));
        solids.add(new Solid(1935, 2824, 98, 1052));
        solids.add(new Solid(1935, 3977, 98, 82));
        solids.add(new Solid(2128, 326, 1968, 82));
        solids.add(new Solid(2128, 1478, 1968, 82));
        solids.add(new Solid(2128, 2630, 1968, 82));
        solids.add(new Solid(2125, 3336, 1638, 82));
        solids.add(new Solid(2125, 3977, 1970, 82));
        solids.add(new Solid(3665, 3418, 98, 452));

        //CrossWalks
        crosswalks.add(new Crosswalk(448, 646, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(448, 1478, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(448, 2312, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(527, 576, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(527, 769, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(527, 1407, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(527, 1601, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(527, 2560, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(641, 326, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(641, 1478, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(641, 2630, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(1088, 326, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(1088, 1478, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(1088, 2630, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(1167, 256, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1167, 448, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1167, 1407, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1167, 1601, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1167, 2560, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1281, 326, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(1281, 1478, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(1281, 2630, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(1856, 326, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(1856, 1478, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(1856, 2630, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(1935, 256, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1935, 448, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1935, 1407, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1935, 1601, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1935, 2560, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1935, 2752, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(1935, 3905, 98, 64, this, screen));
        crosswalks.add(new Crosswalk(2048, 3977, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(2048, 3336, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(2048, 2630, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(2048, 1478, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(2048, 326, 64, 82, this, screen));
        crosswalks.add(new Crosswalk(3665, 3903, 98, 64, this, screen));

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

        int possibleNPCs[];
        possibleNPCs = new int[3];
        possibleNPCs[0] = 0;
        possibleNPCs[1] = 1;
        possibleNPCs[2] = 2;

        trashContainers.add(new TrashContainer(266, 385, 34, 54, screen, 0, false));
        trashContainers.add(new TrashContainer(1615, 530, 34, 54, screen, 0, false));
        trashContainers.add(new TrashContainer(2103, 212, 34, 54, screen, 0, false));
        trashContainers.add(new TrashContainer(2839, 212, 34, 54, screen, 0, false));
        trashContainers.add(new TrashContainer(4055, 212, 34, 54, screen, 0, false));
        trashContainers.add(new TrashContainer(1583, 1331, 34, 54, screen, 0, false));

        trashContainers.add(new TrashContainer(2426, 1074, 34, 54, screen, 0, false));
        trashContainers.add(new TrashContainer(3769, 1074, 34, 54, screen, 0, false));
        trashContainers.add(new TrashContainer(23, 1975, 34, 54, screen, 0, false));
        trashContainers.add(new TrashContainer(1476, 2012, 34, 54, screen, 0, false));
        trashContainers.add(new TrashContainer(3391, 2507, 34, 54, screen, 0, false));
        trashContainers.add(new TrashContainer(125, 3555, 34, 54, screen, 0, false));

        trashContainers.add(new TrashContainer(3175, 3233, 34, 54, screen, 0, false));

        trashContainers.add(new TrashContainer(3556, 1821, 96, 86, screen, 0, true));
        trashContainers.add(new TrashContainer(3654, 1821, 96, 86, screen, 0, true));
        trashContainers.add(new TrashContainer(3851, 1821, 96, 86, screen, 0, true));
        trashContainers.add(new TrashContainer(3951, 1821, 96, 86, screen, 0, true));

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

            //Movement of car
            car.tick();
            //Checks if the player started a conversation with any NPC
            for (int i = 0; i < npcs.size(); i++) {
                npcs.get(i).tick();
                if (!npcs.get(i).isTalking()) {
                    if (npcs.get(i).getPerimetro().intersects(player.getPerimetro()) && npcs.get(i).isJustThrowedTrash() && player.isPick() && !player.isConversation()) {
                        npcs.get(i).setTrashToTrashContainer(npcs.get(i).getTrashToTrashContainer() + 1);
                        npcs.get(i).setTalking(true);
                        player.setConversation(true);
                        screen.setFinishedConversationText(false);
                        screen.setConversationTextIndex(0);
                        player.setPick(false);
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
                    player.setPick(false);

                }
            }

            npcTrashClassify.tick();
            player.tick();
            for (int i = 0; i < storeDoors.size(); i++) {
                storeDoors.get(i).tick();
            }
            
            //If player intersects TodoMart door, it enters to new room
            if (player.getPerimetro().intersects(storeDoors.get(0).getPerimetro())) {
                TodoMartRoom tmr = new TodoMartRoom("Todo x Mart", 512, 512, display, keyManager, this, player);
                tmr.start();
                running = false;
            }
            //If player intersect RecycleCo door, it enters to new room
            if (player.getPerimetro().intersects(storeDoors.get(1).getPerimetro())) {
                RecycleCoRoom rcr = new RecycleCoRoom("Recycle Co. Room", 512, 512, display, keyManager, this, player);
                rcr.start();
                running = false;
            }

            //If player intersects the npc and press space the minigame starts, but first they start a conversation
            if (player.getPerimetro().intersects(npcTrashClassify.getPerimetro())) {
                if (player.isPick()) {
                    if (!player.isConversation()) {
                        player.setPick(false);
                        npcTrashClassify.setTalking(true);
                        player.setConversation(true);
                        screen.setFinishedConversationText(false);
                        screen.setConversationTextIndex(0);
                    } else if (player.isPick() && player.isConversation()) {
                        player.setPick(false);
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

            for (int i = 0; i < trashContainers.size(); i++) {
                if (player.isPick() && trashContainers.get(i).isUnlocked() && player.getPerimetro().intersects(trashContainers.get(i).getPerimetro())) {
                    if (!player.isConversation()) {
                        player.setPick(false);
                        player.setConversation(true);
                        screen.setTrashContainerMessage(true);
                    } else {
                        player.setConversation(false);
                        screen.setTrashContainerMessage(false);
                        //Creats value of delta
                        int delta = player.getCapacity() - player.getInventory();
                        if (delta > 0) {
                            //Adds all the electronics of the trashcan that the player could get in his backpack
                            if (trashContainers.get(i).getHowManyTrash() > 0) {
                                if (trashContainers.get(i).getElectronics() >= delta) {
                                    player.setInventory(player.getInventory() + delta);
                                    player.setElectronics(player.getElectronics() + delta);
                                    trashContainers.get(i).setElectronics(trashContainers.get(i).getElectronics() - delta);

                                } else {
                                    player.setInventory(player.getInventory() + trashContainers.get(i).getElectronics());
                                    player.setElectronics(player.getElectronics() + trashContainers.get(i).getElectronics());
                                    trashContainers.get(i).setElectronics(0);
                                }
                            }
                            //Adds all the aluminum of the trashcan that the player could get in his backpack
                            //Resets value of delta
                            delta = player.getCapacity() - player.getInventory();
                            if (delta > 0) {
                                if (trashContainers.get(i).getHowManyTrash() > 0) {
                                    if (trashContainers.get(i).getAluminum() >= delta) {
                                        player.setInventory(player.getInventory() + delta);
                                        player.setAluminum(player.getAluminum() + delta);
                                        trashContainers.get(i).setAluminum(trashContainers.get(i).getAluminum() - delta);
                                    } else {
                                        player.setInventory(player.getInventory() + trashContainers.get(i).getAluminum());
                                        player.setAluminum(player.getAluminum() + trashContainers.get(i).getAluminum());
                                        trashContainers.get(i).setAluminum(0);
                                    }
                                }
                            }
                            //Adds all the Glass of the trashcan that the player could get in his backpack
                            //Resets value of delta
                            delta = player.getCapacity() - player.getInventory();
                            if (delta > 0) {
                                if (trashContainers.get(i).getHowManyTrash() > 0) {
                                    if (trashContainers.get(i).getGlass() >= delta) {
                                        player.setInventory(player.getInventory() + delta);
                                        player.setGlass(player.getGlass() + delta);
                                        trashContainers.get(i).setGlass(trashContainers.get(i).getGlass() - delta);
                                    } else {
                                        player.setInventory(player.getInventory() + trashContainers.get(i).getGlass());
                                        player.setGlass(player.getGlass() + trashContainers.get(i).getGlass());
                                        trashContainers.get(i).setGlass(0);
                                    }
                                }
                            }
                            //Adds all the Plastic of the trashcan that the player could get in his backpack
                            //Resets value of delta
                            delta = player.getCapacity() - player.getInventory();
                            if (delta > 0) {
                                if (trashContainers.get(i).getHowManyTrash() > 0) {
                                    if (trashContainers.get(i).getPlastic() >= delta) {
                                        player.setInventory(player.getInventory() + delta);
                                        player.setPlastic(player.getPlastic() + delta);
                                        trashContainers.get(i).setPlastic(trashContainers.get(i).getPlastic() - delta);
                                    } else {
                                        player.setInventory(player.getInventory() + trashContainers.get(i).getPlastic());
                                        player.setPlastic(player.getPlastic() + trashContainers.get(i).getPlastic());
                                        trashContainers.get(i).setPlastic(0);
                                    }
                                }
                            }
                            //Adds all the Paper of the trashcan that the player could get in his backpack
                            //Resets value of delta
                            delta = player.getCapacity() - player.getInventory();
                            if (delta > 0) {
                                if (trashContainers.get(i).getHowManyTrash() > 0) {
                                    if (trashContainers.get(i).getPaper() >= delta) {
                                        player.setInventory(player.getInventory() + delta);
                                        player.setPaper(player.getPaper() + delta);
                                        trashContainers.get(i).setPaper(trashContainers.get(i).getPaper() - delta);
                                    } else {
                                        player.setInventory(player.getInventory() + trashContainers.get(i).getPaper());
                                        player.setPaper(player.getPaper() + trashContainers.get(i).getPaper());
                                        trashContainers.get(i).setPaper(0);
                                    }
                                }
                            }
                            //Adds all the Organic of the trashcan that the player could get in his backpack
                            //Resets value of delta
                            delta = player.getCapacity() - player.getInventory();
                            if (delta > 0) {
                                if (trashContainers.get(i).getHowManyTrash() > 0) {
                                    if (trashContainers.get(i).getOrganic() >= delta) {
                                        player.setInventory(player.getInventory() + delta);
                                        player.setOrganic(player.getOrganic() + delta);
                                        trashContainers.get(i).setOrganic(trashContainers.get(i).getOrganic() - delta);
                                    } else {
                                        player.setInventory(player.getInventory() + trashContainers.get(i).getOrganic());
                                        player.setOrganic(player.getOrganic() + trashContainers.get(i).getOrganic());
                                        trashContainers.get(i).setOrganic(0);
                                    }
                                }
                            }
                        }

                    }
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

                MinigameThrow MT = new MinigameThrow("Trash Throw", width, height, display, keyManager, this);

                Assets.gameStart.play();
                MT.start();
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
            //g.fillArc(0,0,200,200,20,20);
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
