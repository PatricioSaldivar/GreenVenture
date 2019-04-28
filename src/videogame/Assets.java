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

    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage playerPortait; // to store the portrait picture of the player
    public static BufferedImage mainMenu;       // to store the main menu
    public static BufferedImage pause;       // to store the pause
    public static BufferedImage trash[]; // to store the trash

    //Miinigame 1 Assests
    public static BufferedImage inTrashCan;         // to store the image of the inorganic trash can
    public static BufferedImage orTrashCan;         // to store the image of the organic trash can
    public static BufferedImage minigameWallpaper;  // to store the image of the minigame wallpaper

    //Pause of game assets
    public static BufferedImage pausaMainMenu[];          // to store the image of the pause when MainMenu is selected
    public static BufferedImage pausaSave[];             // to store the image of the pause when Save is selected
    public static BufferedImage pausaStats[];        // to store the image of the pause when Stats is selected
    public static BufferedImage pausaMenuInstructions[];   // to store the image of the pause when Instructions is selected

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.png");
        player = ImageLoader.loadImage("/images/char.png");
        playerPortait = ImageLoader.loadImage("/images/portrait.png");
        mainMenu = ImageLoader.loadImage("/images/MainMenu.png");
        pause = ImageLoader.loadImage("/images/Pausa.png");
        // Trash sprite array
        trash = new BufferedImage[6];
        trash[0] = ImageLoader.loadImage("/images/Can.png");
        trash[1] = ImageLoader.loadImage("/images/Gbottle-1.png");
        trash[2] = ImageLoader.loadImage("/images/Litro.png");
        trash[3] = ImageLoader.loadImage("/images/Pbag.png");
        trash[4] = ImageLoader.loadImage("/images/Pbottle.png");
        trash[5] = ImageLoader.loadImage("/images/Tcan.png");

        //Minigame 1 loader
        inTrashCan = ImageLoader.loadImage("/images_minigame1/inorganicTrashCan.png");
        orTrashCan = ImageLoader.loadImage("/images_minigame1/organicTrashCan.png");
        minigameWallpaper = ImageLoader.loadImage("/images_minigame1/minigame_wallpaper.jpg");

        //Pause sprites loader
        pausaMainMenu = new BufferedImage[2];
        pausaMainMenu[0] = ImageLoader.loadImage("/images_pause/menuHover.png");
        pausaMainMenu[1] = ImageLoader.loadImage("/images_pause/menuHover2.png");

        pausaSave = new BufferedImage[2];
        pausaSave[0] = ImageLoader.loadImage("/images_pause/saveHover.png");
        pausaSave[1] = ImageLoader.loadImage("/images_pause/saveHover2.png");

        pausaStats = new BufferedImage[2];
        pausaStats[0] = ImageLoader.loadImage("/images_pause/statsHover.png");
        pausaStats[1] = ImageLoader.loadImage("/images_pause/statsHover2.png");

        pausaMenuInstructions = new BufferedImage[2];
        pausaMenuInstructions[0] = ImageLoader.loadImage("/images_pause/instructionsHover.png");
        pausaMenuInstructions[1] = ImageLoader.loadImage("/images_pause/instructionsHover2.png");
    }

}
