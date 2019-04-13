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

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.png");
        player = ImageLoader.loadImage("/images/char.png");
        playerPortait = ImageLoader.loadImage("/images/portrait.png");
        mainMenu = ImageLoader.loadImage("/images/Menu.png");
        pause = ImageLoader.loadImage("/images/Pausa.png");
        trash = new BufferedImage[6];
        trash[0] = ImageLoader.loadImage("/images/Can.png");
        trash[1] = ImageLoader.loadImage("/images/Gbottle-1.png");
        trash[2] = ImageLoader.loadImage("/images/Litro.png");
        trash[3] = ImageLoader.loadImage("/images/Pbag.png");
        trash[4] = ImageLoader.loadImage("/images/Pbottle.png");
        trash[5] = ImageLoader.loadImage("/images/Tcan.png");
    }
    
}
