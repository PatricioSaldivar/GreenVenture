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
    public static BufferedImage trashcanOwned;
    public static BufferedImage trashcanDefault;
    public static BufferedImage dumpOwned;
    public static BufferedImage dumpDefault;

    //Money Asset
    public static BufferedImage coin[];             // to store the coin animation asset
    public static BufferedImage backpack;           // to store the backpack image
    public static SoundClip moneySound;             // to store the sound of when buying or selling things

    //NPC Assets
    public static BufferedImage npcAlert[];         // to store the alert image
    public static BufferedImage npcDialogue[];      // to store the dialogue cloud
    public static BufferedImage npcss_1;            // to store NPC spritesheets
    public static BufferedImage npcss_2;
    public static BufferedImage npcss_3;
    public static BufferedImage npcss_4;
    public static BufferedImage npcDown[][];        // to store the NPC going down assets
    public static BufferedImage npcUp[][];          // to store the NPC going up assets
    public static BufferedImage npcRight[][];       // to store the NPC going right assets
    public static BufferedImage npcLeft[][];        // to store the NPC going left assets
    public static BufferedImage npcTalk[][];
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
    public static BufferedImage leftExt;            //To store the left box extension
    public static BufferedImage rightExt;           //To store the right box extension

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
    public static BufferedImage recycleCoAluminum[];        // to store the images of the RecycleCo when Aluminum is selected
    public static BufferedImage recycleCoElectronics[];     // to store the images of the RecycleCo when Electronics is selected
    public static BufferedImage recycleCoGlass[];           // to store the images of the RecycleCo when Glass is selected
    public static BufferedImage recycleCoOrganics[];        // to store the images of the RecycleCo when Organics is selected
    public static BufferedImage recycleCoPaper[];           // to store the images of the RecycleCo when Papaer is selected
    public static BufferedImage recycleCoPlastic[];         // to store the images of the RecycleCo when Plastic is selected
    public static BufferedImage recycleCoReturn[];          // to store the images of the RecycleCo Return is selected

    //TodoXMart Assets
    public static BufferedImage todoMartBackPackUp[];       // to store the images fo the TodoMart when BackPack upgrade is selected
    public static BufferedImage todoMartBinUp[];            // to store the images fo the TodoMart when Bins upgrade is selected
    public static BufferedImage todoMartReturn[];           // to store the images fo the TodoMart when Return is selected
    public static BufferedImage todoMartSneakersUp[];       // to store the images fo the TodoMart when Sneakers upgrade is selected
    public static BufferedImage todoMartValueUp[];          // to store the images fo the TodoMart when Trash Value upgrade is selected

    public static BufferedImage todoMartRoom;               // to store the room background where the player locate before seeing the 

    //Select cursor
    public static BufferedImage selector[];

    /**
     * initializing the images of the game
     */
    public static void init() {
        //Game 1 loader
        background = ImageLoader.loadImage("/images/Background.png");
        player = ImageLoader.loadImage("/images/char.png");
        playerPortait = ImageLoader.loadImage("/images/portrait.png");
        trashcanOwned = ImageLoader.loadImage("/images/trashcanOwned.png");
        trashcanDefault = ImageLoader.loadImage("/images/trashcanDefault.png");
        dumpOwned = ImageLoader.loadImage("/images/dumpOwned.png");
        dumpDefault = ImageLoader.loadImage("/images/dumpDefault.png");

        pickTrash = new SoundClip("/global_sounds/pickTrash.wav");
        pickDenied = new SoundClip("/global_sounds/pickDenied.wav");
        playerSpriteSheet = ImageLoader.loadImage("/images/player_spritesheet.png");
        SpriteSheet playerSprites = new SpriteSheet(playerSpriteSheet);
        playerDown = new BufferedImage[2];
        playerUp = new BufferedImage[2];
        playerLeft = new BufferedImage[2];
        playerRight = new BufferedImage[2];
        for (int i = 0; i < 2; i++) {
            playerDown[i] = playerSprites.crop(i * 2 * 72 + 8, 32, 56, 64);
        }
        playerFacingDown = playerSprites.crop(72 + 8, 32, 56, 64);
        for (int i = 0; i < 2; i++) {
            playerLeft[i] = playerSprites.crop(i * 2 * 72 + 8, 128, 56, 64);
        }
        playerFacingLeft = playerSprites.crop(72 + 8, 128, 56, 64);

        for (int i = 0; i < 2; i++) {
            playerRight[i] = playerSprites.crop(i * 2 * 72 + 8, 224, 56, 64);
        }
        playerFacingRight = playerSprites.crop(72 + 8, 224, 56, 64);

        for (int i = 0; i < 2; i++) {
            playerUp[i] = playerSprites.crop(i * 2 * 72 + 8, 320, 56, 64);
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
        moneySound = new SoundClip("/global_sounds/cashSound.wav");

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
        
        //NPC dialogue loader
        npcDialogue = new BufferedImage[4];
        npcDialogue[0] = ImageLoader.loadImage("/images_NPC/dialogue.png");
        npcDialogue[1] = ImageLoader.loadImage("/images_NPC/dialogue1.png");
        npcDialogue[2] = ImageLoader.loadImage("/images_NPC/dialogue2.png");
        npcDialogue[3] = ImageLoader.loadImage("/images_NPC/dialogue3.png");
        
        npcss_1 = ImageLoader.loadImage("/NPCAssets/NPCSS_1");
        npcss_2 = ImageLoader.loadImage("/NPCAssets/NPCSS_2");
        npcss_3 = ImageLoader.loadImage("/NPCAssets/NPCSS_3");
        npcss_4 = ImageLoader.loadImage("/NPCAssets/NPCSS_4");
        
        SpriteSheet npcss1 = new SpriteSheet(npcss_1);
        SpriteSheet npcss2 = new SpriteSheet(npcss_2);
        SpriteSheet npcss3 = new SpriteSheet(npcss_3);
        SpriteSheet npcss4 = new SpriteSheet(npcss_4);
                
        npcDown = new BufferedImage[20][3];
        npcLeft = new BufferedImage[20][3];
        npcRight = new BufferedImage[20][3];
        npcUp = new BufferedImage[20][3];
        npcTalk =new BufferedImage[20][2];
        
        int xCalc = 0, yCalc = 0;

        // NPC 0 (Male 1)
        xCalc = 0*72;
        yCalc = 0*72;

        for (int i = 0; i < 2; i++) {
            npcDown[0][i] = npcss1.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[0][i] = npcss1.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[0][i] = npcss1.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[0][i] = npcss1.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[0][0] = ImageLoader.loadImage("/NPCAssets/closed_0.png");
        npcTalk[0][1] = ImageLoader.loadImage("/NPCAssets/open_0.png");
        
        // NPC 1 (Female 1)
        xCalc = 1*72;
        yCalc = 0*72;

        for (int i = 0; i < 2; i++) {
            npcDown[1][i] = npcss1.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[1][i] = npcss1.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[1][i] = npcss1.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[1][i] = npcss1.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[1][0] = ImageLoader.loadImage("/NPCAssets/closed_1.png");
        npcTalk[1][1] = ImageLoader.loadImage("/NPCAssets/open_1.png");
        
        
        // NPC 2 (China 1)
        xCalc = 3*72;
        yCalc = 0*72;

        for (int i = 0; i < 2; i++) {
            npcDown[2][i] = npcss1.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[2][i] = npcss1.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[2][i] = npcss1.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[2][i] = npcss1.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[2][0] = ImageLoader.loadImage("/NPCAssets/closed_2.png");
        npcTalk[2][1] = ImageLoader.loadImage("/NPCAssets/open_2.png");
        
        
        // NPC 3 (China 2)
        xCalc = 1*72;
        yCalc = 1*72;

        for (int i = 0; i < 2; i++) {
            npcDown[3][i] = npcss1.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[3][i] = npcss1.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[3][i] = npcss1.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[3][i] = npcss1.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[3][0] = ImageLoader.loadImage("/NPCAssets/closed_3.png");
        npcTalk[3][1] = ImageLoader.loadImage("/NPCAssets/open_3.png");
        
        
        // NPC 4 (Scientist)
        xCalc = 2*72;
        yCalc = 1*72;

        for (int i = 0; i < 2; i++) {
            npcDown[4][i] = npcss1.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[4][i] = npcss1.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[4][i] = npcss1.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[4][i] = npcss1.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[4][0] = ImageLoader.loadImage("/NPCAssets/closed_4.png");
        npcTalk[4][1] = ImageLoader.loadImage("/NPCAssets/open_4.png");
        
        
        // NPC 5 (Boxer)
        xCalc = 3*72;
        yCalc = 1*72;

        for (int i = 0; i < 2; i++) {
            npcDown[5][i] = npcss1.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[5][i] = npcss1.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[5][i] = npcss1.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[5][i] = npcss1.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[5][0] = ImageLoader.loadImage("/NPCAssets/closed_5.png");
        npcTalk[5][1] = ImageLoader.loadImage("/NPCAssets/open_5.png");
        
        
        // NPC 6 (Little Guy)
        xCalc = 0*72;
        yCalc = 0*72;

        for (int i = 0; i < 2; i++) {
            npcDown[6][i] = npcss2.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[6][i] = npcss2.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[6][i] = npcss2.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[6][i] = npcss2.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[6][0] = ImageLoader.loadImage("/NPCAssets/closed_6.png");
        npcTalk[6][1] = ImageLoader.loadImage("/NPCAssets/open_6.png");
        
        
        // NPC 7 (Little Girl)
        xCalc = 1*72;
        yCalc = 0*72;

        for (int i = 0; i < 2; i++) {
            npcDown[7][i] = npcss2.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[7][i] = npcss2.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[7][i] = npcss2.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[7][i] = npcss2.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[7][0] = ImageLoader.loadImage("/NPCAssets/closed_7.png");
        npcTalk[7][1] = ImageLoader.loadImage("/NPCAssets/open_7.png");
        
        
        // NPC 8 (Girl 1)
        xCalc = 2*72;
        yCalc = 0*72;

        for (int i = 0; i < 2; i++) {
            npcDown[8][i] = npcss2.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[8][i] = npcss2.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[8][i] = npcss2.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[8][i] = npcss2.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[8][0] = ImageLoader.loadImage("/NPCAssets/closed_8.png");
        npcTalk[8][1] = ImageLoader.loadImage("/NPCAssets/open_8.png");
        
        
        // NPC 9 (Boy 1)
        xCalc = 3*72;
        yCalc = 0*72;

        for (int i = 0; i < 2; i++) {
            npcDown[9][i] = npcss2.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[9][i] = npcss2.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[9][i] = npcss2.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[9][i] = npcss2.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[9][0] = ImageLoader.loadImage("/NPCAssets/closed_9.png");
        npcTalk[9][1] = ImageLoader.loadImage("/NPCAssets/open_9.png");
        
        
        // NPC 10 (Old)
        xCalc = 0*72;
        yCalc = 0*72;

        for (int i = 0; i < 2; i++) {
            npcDown[10][i] = npcss3.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[10][i] = npcss3.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[10][i] = npcss3.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[10][i] = npcss3.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[10][0] = ImageLoader.loadImage("/NPCAssets/closed_10.png");
        npcTalk[10][1] = ImageLoader.loadImage("/NPCAssets/open_10.png");
        
        
        // NPC 11 (Adventure)
        xCalc = 2*72;
        yCalc = 0*72;

        for (int i = 0; i < 2; i++) {
            npcDown[11][i] = npcss3.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[11][i] = npcss3.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[11][i] = npcss3.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[11][i] = npcss3.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[11][0] = ImageLoader.loadImage("/NPCAssets/closed_11.png");
        npcTalk[11][1] = ImageLoader.loadImage("/NPCAssets/open_11.png");
        
        // NPC 12 (Geek)
        xCalc = 3*72;
        yCalc = 0*72;

        for (int i = 0; i < 2; i++) {
            npcDown[12][i] = npcss3.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[12][i] = npcss3.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[12][i] = npcss3.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[12][i] = npcss3.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[12][0] = ImageLoader.loadImage("/NPCAssets/closed_12.png");
        npcTalk[12][1] = ImageLoader.loadImage("/NPCAssets/open_12.png");
        
        // NPC 13 (Girl 2)
        xCalc = 0*72;
        yCalc = 1*72;

        for (int i = 0; i < 2; i++) {
            npcDown[13][i] = npcss3.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[13][i] = npcss3.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[13][i] = npcss3.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[13][i] = npcss3.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[13][0] = ImageLoader.loadImage("/NPCAssets/closed_13.png");
        npcTalk[13][1] = ImageLoader.loadImage("/NPCAssets/open_13.png");
        
        // NPC 14 (Fat 1)
        xCalc = 1*72;
        yCalc = 1*72;

        for (int i = 0; i < 2; i++) {
            npcDown[14][i] = npcss3.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[14][i] = npcss3.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[14][i] = npcss3.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[14][i] = npcss3.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[14][0] = ImageLoader.loadImage("/NPCAssets/closed_14.png");
        npcTalk[14][1] = ImageLoader.loadImage("/NPCAssets/open_14.png");
        
        // NPC 15 (Girl 3)
        xCalc = 1*72;
        yCalc = 3*72;

        for (int i = 0; i < 2; i++) {
            npcDown[15][i] = npcss3.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[15][i] = npcss3.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[15][i] = npcss3.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[15][i] = npcss3.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[15][0] = ImageLoader.loadImage("/NPCAssets/closed_15.png");
        npcTalk[15][1] = ImageLoader.loadImage("/NPCAssets/open_15.png");
        
        // NPC 16 (Female 2)
        xCalc = 0*72;
        yCalc = 1*72;

        for (int i = 0; i < 2; i++) {
            npcDown[16][i] = npcss4.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[16][i] = npcss4.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[16][i] = npcss4.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[16][i] = npcss4.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[16][0] = ImageLoader.loadImage("/NPCAssets/closed_16.png");
        npcTalk[16][1] = ImageLoader.loadImage("/NPCAssets/open_16.png");
        
        // NPC 17 (Elvis)
        xCalc = 1*72;
        yCalc = 1*72;

        for (int i = 0; i < 2; i++) {
            npcDown[17][i] = npcss4.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[17][i] = npcss4.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[17][i] = npcss4.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[17][i] = npcss4.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[17][0] = ImageLoader.loadImage("/NPCAssets/closed_17.png");
        npcTalk[17][1] = ImageLoader.loadImage("/NPCAssets/open_17.png");
        
        // NPC 18 (Boy 2)
        xCalc = 2*72;
        yCalc = 1*72;

        for (int i = 0; i < 2; i++) {
            npcDown[18][i] = npcss4.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[18][i] = npcss4.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[18][i] = npcss4.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[18][i] = npcss4.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[18][0] = ImageLoader.loadImage("/NPCAssets/closed_18.png");
        npcTalk[18][1] = ImageLoader.loadImage("/NPCAssets/open_18.png");
        
        // NPC 19 (Girl 2)
        xCalc = 3*72;
        yCalc = 1*72;

        for (int i = 0; i < 2; i++) {
            npcDown[19][i] = npcss4.crop(i*72 +xCalc ,72*0 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcLeft[19][i] = npcss4.crop(i*72 +xCalc ,72*1 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcRight[19][i] = npcss4.crop(i*72 +xCalc ,72*2 + yCalc , 72 , 72);
        }
        for (int i = 0; i < 2; i++) {
            npcUp[19][i] = npcss4.crop(i*72 +xCalc ,72*3 + yCalc , 72 , 72);
        }
        npcTalk[19][0] = ImageLoader.loadImage("/NPCAssets/closed_19.png");
        npcTalk[19][1] = ImageLoader.loadImage("/NPCAssets/open_19.png");

        
        
        
        //Minigame 1 loader
        inTrashCan = ImageLoader.loadImage("/images_minigame1/binIn.png");
        orTrashCan = ImageLoader.loadImage("/images_minigame1/binOr.png");
        minigameWallpaper = ImageLoader.loadImage("/images_minigame1/minigame_wallpaper.jpg");
        rightBox = ImageLoader.loadImage("/images_minigame1/box_right.png");
        leftBox = ImageLoader.loadImage("/images_minigame1/box_left.png");
        gloveHit = new SoundClip("/sounds_minigame1/glovePunch.wav");
        leftExt = ImageLoader.loadImage("/images_minigame1/extensionLeft.png");
        rightExt = ImageLoader.loadImage("/images_minigame1/extensionRight.png");

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
        recycleCoAluminum = new BufferedImage[2];
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

        //TodoxMart loader
        todoMartBackPackUp = new BufferedImage[2];
        todoMartBackPackUp[0] = ImageLoader.loadImage("/imagesTodoMart/backpackUp.png");
        todoMartBackPackUp[1] = ImageLoader.loadImage("/imagesTodoMart/backpackUp2.png");

        todoMartBinUp = new BufferedImage[2];
        todoMartBinUp[0] = ImageLoader.loadImage("/imagesTodoMart/binUp.png");
        todoMartBinUp[1] = ImageLoader.loadImage("/imagesTodoMart/binUp2.png");

        todoMartReturn = new BufferedImage[2];
        todoMartReturn[0] = ImageLoader.loadImage("/imagesTodoMart/returnUp.png");
        todoMartReturn[1] = ImageLoader.loadImage("/imagesTodoMart/retrurnUp2.png");

        todoMartSneakersUp = new BufferedImage[2];
        todoMartSneakersUp[0] = ImageLoader.loadImage("/imagesTodoMart/sneakerUp.png");
        todoMartSneakersUp[1] = ImageLoader.loadImage("/imagesTodoMart/sneakerUp2.png");

        todoMartValueUp = new BufferedImage[2];
        todoMartValueUp[0] = ImageLoader.loadImage("/imagesTodoMart/valueUp.png");
        todoMartValueUp[1] = ImageLoader.loadImage("/imagesTodoMart/valueUp2.png");

        todoMartRoom = ImageLoader.loadImage("/imagesTodoMart/storeRoom.png");

        //Selector
        selector = new BufferedImage[2];
        selector[0] = ImageLoader.loadImage("/images/selector1.png");
        selector[1] = ImageLoader.loadImage("/images/selector2.png");

    }

}
