/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

/**
 *
 * @author BonfireStudios
 */
public class VideoGame {
    public static Display display; 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        display = new Display("Menu", 512, 512);
        // TODO code application logic here
        MainMenu m = new MainMenu("MainMenu", 512, 512, display);
        m.start();
      /*
        KeyManager keymanager = new KeyManager();
        MinigameTrashClassify mct = new MinigameTrashClassify("Minigame",512,512,new Display("minigame1",512,512),keymanager);
        mct.start();
        */
         
    }

}
