/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author BonfireStudios
 */
public class Assets {

    //Game 1 Assets
    public static BufferedImage background;         // to store background image
    public static BufferedImage player;             // to store the player image
    public static BufferedImage playerPortait;      // to store the portrait picture of the player
    public static BufferedImage mainMenu;           // to store the main menu
    public static BufferedImage pause;              // to store the pause
    public static BufferedImage trash[];            // to store the trash
    
    //Money Asset
    public static BufferedImage coin[];             // to store the coin animation asset
    
    //NPC Assets
    public static BufferedImage npcAlert[];         // to store the alert image

    //Minigame 1 Assests
    public static BufferedImage inTrashCan;         // to store the image of the inorganic trash can
    public static BufferedImage orTrashCan;         // to store the image of the organic trash can
    public static BufferedImage minigameWallpaper;  // to store the image of the minigame wallpaper
    public static BufferedImage rightBox;           // to store the image of the right box glove
    public static BufferedImage leftBox;            // to store the image of the left box glove
    public static BufferedImage inTrash[];          // to store the images of the inorganic trash
    public static BufferedImage orTrash[];           // to store the images of the organic trash
    
    //Pause of game assets
    public static BufferedImage pausaMainMenu[];          // to store the image of the pause when MainMenu is selected
    public static BufferedImage pausaSave[];             // to store the image of the pause when Save is selected
    public static BufferedImage pausaStats[];        // to store the image of the pause when Stats is selected
    public static BufferedImage pausaMenuInstructions[];   // to store the image of the pause when Instructions is selected

    // Main Menu
    public static BufferedImage mainMenuPlay[];          // to store the image of the MainMenu when play is selected
    public static BufferedImage mainMenuLoad[];             // to store the image of the MainMenu when load is selected
    public static BufferedImage mainMenuCredits[];        // to store the image of the MainMenu when credits is selected
    public static BufferedImage mainMenuInstructions[];   // to store the image of the MainMenu when instructions is selected
    public static BufferedImage mainMenuQuit[];   // to store the image of the MainMenu when quit is selected
    

    //Minigame Pause
    public static BufferedImage minigame1PauseEnd[];   // to store the image of the Minigame1 pause when end is selected

    /**
     * initializing the images of the game
     */
    public static void init() {
        //Game 1 loader
        background = ImageLoader.loadImage("/images/Background.png");
        player = ImageLoader.loadImage("/images/char.png");
        playerPortait = ImageLoader.loadImage("/images/portrait.png");
        mainMenu = ImageLoader.loadImage("/images/MainMenu.png");
        pause = ImageLoader.loadImage("/images/Pausa.png");
        
        //Money coin image loader
        coin = new BufferedImage[6];
        coin[0] = ImageLoader.loadImage("/images/coin1.png");
        coin[1] = ImageLoader.loadImage("/images/coin2.png");
        coin[2] = ImageLoader.loadImage("/images/coin3.png");
        coin[3] = ImageLoader.loadImage("/images/coin4.png");
        coin[4] = ImageLoader.loadImage("/images/coin5.png");
        coin[5] = ImageLoader.loadImage("/images/coin6.png");
        
        // Trash in Game 1 sprite array loader
        trash = new BufferedImage[6];
        trash[0] = ImageLoader.loadImage("/images/Can.png");
        trash[1] = ImageLoader.loadImage("/images/Gbottle-1.png");
        trash[2] = ImageLoader.loadImage("/images/Litro.png");
        trash[3] = ImageLoader.loadImage("/images/Pbag.png");
        trash[4] = ImageLoader.loadImage("/images/Pbottle.png");
        trash[5] = ImageLoader.loadImage("/images/Tcan.png");
        
        //NPC loader
        npcAlert = new BufferedImage[4];
        npcAlert[0] = ImageLoader.loadImage("/images_NPC/alert.png");
        npcAlert[1] = ImageLoader.loadImage("/images_NPC/alert2.png");
        npcAlert[2] = ImageLoader.loadImage("/images_NPC/alert3.png");
        npcAlert[3] = ImageLoader.loadImage("/images_NPC/alert4.png");
        
        //Minigame 1 loader
        inTrashCan = ImageLoader.loadImage("/images_minigame1/binIn.png");
        orTrashCan = ImageLoader.loadImage("/images_minigame1/binOr.png");
        minigameWallpaper = ImageLoader.loadImage("/images_minigame1/minigame_wallpaper.jpg");
        rightBox = ImageLoader.loadImage("/images_minigame1/box_right.png");
        leftBox = ImageLoader.loadImage("/images_minigame1/box_left.png");
        gloveHit = new SoundClip("/sounds_minigame1/glovePunch.wav");

        minigame1PauseEnd = new BufferedImage[2];
        minigame1PauseEnd[0] = ImageLoader.loadImage("/imagesMinigame1Pause/pauseMiniGame.png");
        minigame1PauseEnd[1] = ImageLoader.loadImage("/imagesMinigame1Pause/pauseMiniGame2.png");

        //Minigame 1 inorganic trash loader
        inTrash = new BufferedImage[15];
        inTrash[0] = ImageLoader.loadImage("/images_minigame1/beer.png");
        inTrash[1] = ImageLoader.loadImage("/images_minigame1/bigCan.png");
        inTrash[2] = ImageLoader.loadImage("/images_minigame1/bottle.png");
        inTrash[3] = ImageLoader.loadImage("/images_minigame1/can.png");
        inTrash[4] = ImageLoader.loadImage("/images_minigame1/coke.png");
        inTrash[5] = ImageLoader.loadImage("/images_minigame1/cup.png");
        inTrash[6] = ImageLoader.loadImage("/images_minigame1/detergent.png");
        inTrash[7] = ImageLoader.loadImage("/images_minigame1/gallon.png");
        inTrash[8] = ImageLoader.loadImage("/images_minigame1/glassBottle.png");
        inTrash[9] = ImageLoader.loadImage("/images_minigame1/jar.png");
        inTrash[10] = ImageLoader.loadImage("/images_minigame1/litre.png");
        inTrash[11] = ImageLoader.loadImage("/images_minigame1/newspaper.png");
        inTrash[12] = ImageLoader.loadImage("/images_minigame1/paperBag.png");
        inTrash[13] = ImageLoader.loadImage("/images_minigame1/spray.png");
        inTrash[14] = ImageLoader.loadImage("/images_minigame1/starbucks.png");
        
        //Minigame 1 organic trash loader
        orTrash = new BufferedImage[9];
        orTrash[0] = ImageLoader.loadImage("/images_minigame1/bone.png");
        orTrash[1] = ImageLoader.loadImage("/images_minigame1/burguer.png");
        orTrash[2] = ImageLoader.loadImage("/images_minigame1/chicken.png");
        orTrash[3] = ImageLoader.loadImage("/images_minigame1/cookie.png");
        orTrash[4] = ImageLoader.loadImage("/images_minigame1/fish.png");
        orTrash[5] = ImageLoader.loadImage("/images_minigame1/greenApple.png");
        orTrash[6] = ImageLoader.loadImage("/images_minigame1/pizza.png");
        orTrash[7] = ImageLoader.loadImage("/images_minigame1/redApple.png");
        orTrash[8] = ImageLoader.loadImage("/images_minigame1/watermelon.png");
        
        
        //Pause sprites loader
        pausaMainMenu = new BufferedImage[2];
        pausaMainMenu[0] = ImageLoader.loadImage("/imagesPause/menuHover.png");
        pausaMainMenu[1] = ImageLoader.loadImage("/imagesPause/menuHover2.png");

        pausaSave = new BufferedImage[2];
        pausaSave[0] = ImageLoader.loadImage("/imagesPause/saveHover.png");
        pausaSave[1] = ImageLoader.loadImage("/imagesPause/saveHover2.png");

        pausaStats = new BufferedImage[2];
        pausaStats[0] = ImageLoader.loadImage("/imagesPause/statsHover.png");
        pausaStats[1] = ImageLoader.loadImage("/imagesPause/statsHover2.png");

        pausaMenuInstructions = new BufferedImage[2];
        pausaMenuInstructions[0] = ImageLoader.loadImage("/imagesPause/instructionsHover.png");
        pausaMenuInstructions[1] = ImageLoader.loadImage("/imagesPause/instructionsHover2.png");

        //Main Menu 
        mainMenuPlay = new BufferedImage[2];
        mainMenuPlay[0] = ImageLoader.loadImage("/imagesMainMenu/playHover.png");
        mainMenuPlay[1] = ImageLoader.loadImage("/imagesMainMenu/playHover2.png");

        mainMenuLoad = new BufferedImage[2];
        mainMenuLoad[0] = ImageLoader.loadImage("/imagesMainMenu/loadHover.png");
        mainMenuLoad[1] = ImageLoader.loadImage("/imagesMainMenu/loadHover2.png");

        mainMenuCredits = new BufferedImage[2];
        mainMenuCredits[0] = ImageLoader.loadImage("/imagesMainMenu/creditsHover.png");
        mainMenuCredits[1] = ImageLoader.loadImage("/imagesMainMenu/creditsHover2.png");

        mainMenuInstructions = new BufferedImage[2];
        mainMenuInstructions[0] = ImageLoader.loadImage("/imagesMainMenu/instructionsHover.png");
        mainMenuInstructions[1] = ImageLoader.loadImage("/imagesMainMenu/instructionsHover2.png");

        mainMenuQuit = new BufferedImage[2];
        mainMenuQuit[0] = ImageLoader.loadImage("/imagesMainMenu/exitHover.png");
        mainMenuQuit[1] = ImageLoader.loadImage("/imagesMainMenu/exitHover2.png");

    }

}
