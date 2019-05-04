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

    //City Assets
    public static BufferedImage background;         // to store background image
    public static BufferedImage player;             // to store the player image
    public static BufferedImage playerPortait;      // to store the portrait picture of the player
    public static BufferedImage mainMenu;           // to store the main menu
    public static BufferedImage pause;              // to store the pause
    public static BufferedImage trash[];            // to store the trash
    public static SoundClip pickTrash;              // to store the picking trash sound
    public static SoundClip pickDenied;             // to store the picking denied sound
    public static BufferedImage playerSpriteSheet;  // to store the player sprite sheet
    public static BufferedImage playerUp[];           // to store the player sprite sheet
    public static BufferedImage playerDown[];         // to store the player sprite sheet
    public static BufferedImage playerRight[];        // to store the player sprite sheet
    public static BufferedImage playerLeft[];         // to store the player sprite sheet
    public static BufferedImage playerFacingUp;
    public static BufferedImage playerFacingDown;
    public static BufferedImage playerFacingRight;
    public static BufferedImage playerFacingLeft;

    //Money Asset
    public static BufferedImage coin[];             // to store the coin animation asset
    public static BufferedImage backpack;           // to store the backpack image

    //NPC Assets
    public static BufferedImage npcAlert[];         // to store the alert image
    public static BufferedImage npcDown[][];        // to store the NPC going down assets
    public static BufferedImage npcUp[][];          // to store the NPC going up assets
    public static BufferedImage npcRight[][];       // to store the NPC going right assets
    public static BufferedImage npcLeft[][];        // to store the NPC going left assets
    public static BufferedImage npcMinigame1;       // to store the NPC of the minigame1 image

    //Minigame 1 Assests
    public static BufferedImage inTrashCan;         // to store the image of the inorganic trash can
    public static BufferedImage orTrashCan;         // to store the image of the organic trash can
    public static BufferedImage minigameWallpaper;  // to store the image of the minigame wallpaper
    public static BufferedImage rightBox;           // to store the image of the right box glove
    public static BufferedImage leftBox;            // to store the image of the left box glove
    public static BufferedImage inTrash[];          // to store the images of the inorganic trash
    public static BufferedImage orTrash[];           // to store the images of the organic trash
    public static SoundClip gloveHit;               // to store the glove hit sound

    
    //Pause of game Assets

    public static BufferedImage pausaMainMenu[];            // to store the image of the pause when MainMenu is selected
    public static BufferedImage pausaSave[];                // to store the image of the pause when Save is selected
    public static BufferedImage pausaStats[];               // to store the image of the pause when Stats is selected
    public static BufferedImage pausaMenuInstructions[];   // to store the image of the pause when Instructions is selected

    // Main Menu Assets
    public static BufferedImage mainMenuPlay[];           // to store the image of the MainMenu when play is selected
    public static BufferedImage mainMenuLoad[];           // to store the image of the MainMenu when load is selected
    public static BufferedImage mainMenuCredits[];        // to store the image of the MainMenu when credits is selected
    public static BufferedImage mainMenuInstructions[];   // to store the image of the MainMenu when instructions is selected
    public static BufferedImage mainMenuQuit[];           // to store the image of the MainMenu when quit is selected
    public static SoundClip gameStart;                    // to store the sound of new game or continue game button
    public static SoundClip selectSound;                  // to store the sound of the menu selector
    public static BufferedImage mainMenuInstructionsImage[];

    //Minigame Pause Assets
    public static BufferedImage minigame1PauseEnd[];      // to store the image of the Minigame1 pause when end is selected
    
    //RecycleCo Assets
    public static BufferedImage recycleCoAluminum[];    // to store the images of the RecycleCo when Aluminum is selected
    public static BufferedImage recycleCoElectronics[];    // to store the images of the RecycleCo when Electronics is selected
    public static BufferedImage recycleCoGlass[];    // to store the images of the RecycleCo when Glass is selected
    public static BufferedImage recycleCoOrganics[];    // to store the images of the RecycleCo when Organics is selected
    public static BufferedImage recycleCoPaper[];    // to store the images of the RecycleCo when Papaer is selected
    public static BufferedImage recycleCoPlastic[];    // to store the images of the RecycleCo when Plastic is selected
    public static BufferedImage recycleCoReturn[];    // to store the images of the RecycleCo Return is selected

    /**
     * initializing the images of the game
     */
    public static void init() {
        //Game 1 loader
        background = ImageLoader.loadImage("/images/Background.png");
        player = ImageLoader.loadImage("/images/char.png");
        playerPortait = ImageLoader.loadImage("/images/portrait.png");

        pickTrash = new SoundClip("/global_sounds/pickTrash.wav");
        pickDenied = new SoundClip("/global_sounds/pickDenied.wav");
        playerSpriteSheet = ImageLoader.loadImage("/images/player_spritesheet.png");
        SpriteSheet playerSprites = new SpriteSheet(playerSpriteSheet);
        playerDown = new BufferedImage[2];
        playerUp = new BufferedImage[2];
        playerLeft = new BufferedImage[2];
        playerRight = new BufferedImage[2];
        for (int i = 0; i < 2; i++) {
                playerDown[i] = playerSprites.crop(i*2* 72 + 8, 32, 56, 64);
        }
                playerFacingDown = playerSprites.crop(72 + 8, 32, 56, 64);
        for (int i = 0; i < 2; i++) {
                playerLeft[i] = playerSprites.crop(i*2 * 72 + 8, 128, 56, 64);
        }
                playerFacingLeft = playerSprites.crop(72 + 8, 128, 56, 64);
           
        for (int i = 0; i < 2; i++) {
                playerRight[i] = playerSprites.crop(i*2 * 72 + 8, 224, 56, 64);
        }
                playerFacingRight = playerSprites.crop(72 + 8, 224, 56, 64);
           
        for (int i = 0; i < 2; i++) {
                playerUp[i] = playerSprites.crop(i*2 * 72 + 8, 320, 56, 64);
        }
                playerFacingUp = playerSprites.crop(72 + 8, 320, 56, 64);
            

        //Money coin image loader
        coin = new BufferedImage[6];
        coin[0] = ImageLoader.loadImage("/images/coin1.png");
        coin[1] = ImageLoader.loadImage("/images/coin2.png");
        coin[2] = ImageLoader.loadImage("/images/coin3.png");
        coin[3] = ImageLoader.loadImage("/images/coin4.png");
        coin[4] = ImageLoader.loadImage("/images/coin5.png");
        coin[5] = ImageLoader.loadImage("/images/coin6.png");
        backpack = ImageLoader.loadImage("/images/backpack.png");

        // Trash in Game 1 sprite array loader
        trash = new BufferedImage[27];
        // Glass items
        trash[0] = ImageLoader.loadImage("/inorganicItems/beer.png");
        trash[1] = ImageLoader.loadImage("/inorganicItems/cup.png");
        trash[2] = ImageLoader.loadImage("/inorganicItems/glassBottle.png");
        trash[3] = ImageLoader.loadImage("/inorganicItems/jar.png");
        // Aluminum items 
        trash[4] = ImageLoader.loadImage("/inorganicItems/bigCan.png");
        trash[5] = ImageLoader.loadImage("/inorganicItems/can.png");
        trash[6] = ImageLoader.loadImage("/inorganicItems/coke.png");
        trash[7] = ImageLoader.loadImage("/inorganicItems/spray.png");
        // Plastic items
        trash[8] = ImageLoader.loadImage("/inorganicItems/bottle.png");
        trash[9] = ImageLoader.loadImage("/inorganicItems/detergent.png");
        trash[10] = ImageLoader.loadImage("/inorganicItems/gallon.png");
        // Paper items
        trash[11] = ImageLoader.loadImage("/inorganicItems/newspaper.png");
        trash[12] = ImageLoader.loadImage("/inorganicItems/paperBag.png");
        trash[13] = ImageLoader.loadImage("/inorganicItems/starbucks.png");
        // Electronic garbage
        trash[14] = ImageLoader.loadImage("/inorganicItems/radio.png");
        trash[15] = ImageLoader.loadImage("/inorganicItems/gameboy.png");
        trash[16] = ImageLoader.loadImage("/inorganicItems/battery.png");
        trash[17] = ImageLoader.loadImage("/inorganicItems/smartphone.png");
        // Organic waste
        trash[18] = ImageLoader.loadImage("/organicItems/bone.png");
        trash[19] = ImageLoader.loadImage("/organicItems/burguer.png");
        trash[20] = ImageLoader.loadImage("/organicItems/chicken.png");
        trash[21] = ImageLoader.loadImage("/organicItems/cookie.png");
        trash[22] = ImageLoader.loadImage("/organicItems/fish.png");
        trash[23] = ImageLoader.loadImage("/organicItems/greenApple.png");
        trash[24] = ImageLoader.loadImage("/organicItems/redApple.png");
        trash[25] = ImageLoader.loadImage("/organicItems/pizza.png");
        trash[26] = ImageLoader.loadImage("/organicItems/watermelon.png");

        //NPC alert loader
        npcAlert = new BufferedImage[4];
        npcAlert[0] = ImageLoader.loadImage("/images_NPC/alert.png");
        npcAlert[1] = ImageLoader.loadImage("/images_NPC/alert2.png");
        npcAlert[2] = ImageLoader.loadImage("/images_NPC/alert3.png");
        npcAlert[3] = ImageLoader.loadImage("/images_NPC/alert4.png");
        npcMinigame1 = ImageLoader.loadImage("/images_NPC/npc.png");

        //Minigame 1 loader
        inTrashCan = ImageLoader.loadImage("/images_minigame1/binIn.png");
        orTrashCan = ImageLoader.loadImage("/images_minigame1/binOr.png");
        minigameWallpaper = ImageLoader.loadImage("/images_minigame1/minigame_wallpaper.jpg");
        rightBox = ImageLoader.loadImage("/images_minigame1/box_right.png");
        leftBox = ImageLoader.loadImage("/images_minigame1/box_left.png");
        gloveHit = new SoundClip("/sounds_minigame1/glovePunch.wav");

        //Mini-game pause
        minigame1PauseEnd = new BufferedImage[2];
        minigame1PauseEnd[0] = ImageLoader.loadImage("/images_minigame1/pauseMiniGame.png");
        minigame1PauseEnd[1] = ImageLoader.loadImage("/images_minigame1/pauseMiniGame2.png");

        //Minigame 1 inorganic trash loader
        inTrash = new BufferedImage[19];
        inTrash[0] = ImageLoader.loadImage("/inorganicItems/beer.png");
        inTrash[1] = ImageLoader.loadImage("/inorganicItems/bigCan.png");
        inTrash[2] = ImageLoader.loadImage("/inorganicItems/bottle.png");
        inTrash[3] = ImageLoader.loadImage("/inorganicItems/can.png");
        inTrash[4] = ImageLoader.loadImage("/inorganicItems/coke.png");
        inTrash[5] = ImageLoader.loadImage("/inorganicItems/cup.png");
        inTrash[6] = ImageLoader.loadImage("/inorganicItems/detergent.png");
        inTrash[7] = ImageLoader.loadImage("/inorganicItems/gallon.png");
        inTrash[8] = ImageLoader.loadImage("/inorganicItems/glassBottle.png");
        inTrash[9] = ImageLoader.loadImage("/inorganicItems/jar.png");
        inTrash[10] = ImageLoader.loadImage("/inorganicItems/litre.png");
        inTrash[11] = ImageLoader.loadImage("/inorganicItems/newspaper.png");
        inTrash[12] = ImageLoader.loadImage("/inorganicItems/paperBag.png");
        inTrash[13] = ImageLoader.loadImage("/inorganicItems/spray.png");
        inTrash[14] = ImageLoader.loadImage("/inorganicItems/starbucks.png");
        inTrash[15] = ImageLoader.loadImage("/inorganicItems/radio.png");
        inTrash[16] = ImageLoader.loadImage("/inorganicItems/gameboy.png");
        inTrash[17] = ImageLoader.loadImage("/inorganicItems/battery.png");
        inTrash[18] = ImageLoader.loadImage("/inorganicItems/smartphone.png");

        //Minigame 1 organic trash loader
        orTrash = new BufferedImage[9];
        orTrash[0] = ImageLoader.loadImage("/organicItems/bone.png");
        orTrash[1] = ImageLoader.loadImage("/organicItems/burguer.png");
        orTrash[2] = ImageLoader.loadImage("/organicItems/chicken.png");
        orTrash[3] = ImageLoader.loadImage("/organicItems/cookie.png");
        orTrash[4] = ImageLoader.loadImage("/organicItems/fish.png");
        orTrash[5] = ImageLoader.loadImage("/organicItems/greenApple.png");
        orTrash[6] = ImageLoader.loadImage("/organicItems/pizza.png");
        orTrash[7] = ImageLoader.loadImage("/organicItems/redApple.png");
        orTrash[8] = ImageLoader.loadImage("/organicItems/watermelon.png");

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

        //Main Menu loader
        selectSound = new SoundClip("/global_sounds/select.wav");
        gameStart = new SoundClip("/global_sounds/gameStart.wav");
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

        mainMenuInstructionsImage = new BufferedImage[2];
        mainMenuInstructionsImage[0] = ImageLoader.loadImage("/imagesMainMenu/instructions.png");
        mainMenuInstructionsImage[1] = ImageLoader.loadImage("/imagesMainMenu/instructions2.png");
        
        //RecycleCo Loader
        recycleCoAluminum  = new BufferedImage[2]; 
        recycleCoAluminum[0] = ImageLoader.loadImage("/imagesRecycleCo/recAluminum.png");
        recycleCoAluminum[1] = ImageLoader.loadImage("/imagesRecycleCo/recAluminum2.png");
        
        recycleCoElectronics = new BufferedImage[2];
        recycleCoElectronics[0] = ImageLoader.loadImage("/imagesRecycleCo/recElectronics.png");
        recycleCoElectronics[1] = ImageLoader.loadImage("/imagesRecycleCo/recElectronics2.png");
        
        recycleCoGlass = new BufferedImage[2];
        recycleCoGlass[0] = ImageLoader.loadImage("/imagesRecycleCo/recGlass.png");
        recycleCoGlass[1] = ImageLoader.loadImage("/imagesRecycleCo/recGlass2.png");
        
        recycleCoOrganics = new BufferedImage[2];
        recycleCoOrganics[0] = ImageLoader.loadImage("/imagesRecycleCo/recOrganics.png");
        recycleCoOrganics[1] = ImageLoader.loadImage("/imagesRecycleCo/recOrganics2.png");
        
        recycleCoPaper = new BufferedImage[2];
        recycleCoPaper[0] = ImageLoader.loadImage("/imagesRecycleCo/recPaper.png");
        recycleCoPaper[1] = ImageLoader.loadImage("/imagesRecycleCo/recPaper2.png");
        
        recycleCoPlastic = new BufferedImage[2];
        recycleCoPlastic[0] = ImageLoader.loadImage("/imagesRecycleCo/recPlastic.png");
        recycleCoPlastic[1] = ImageLoader.loadImage("/imagesRecycleCo/recPlastic2.png");
        
        recycleCoReturn = new BufferedImage[2];
        recycleCoReturn[0] = ImageLoader.loadImage("/imagesRecycleCo/recReturn.png");
        recycleCoReturn[1] = ImageLoader.loadImage("/imagesRecycleCo/recReturn2.png");

    }

}
