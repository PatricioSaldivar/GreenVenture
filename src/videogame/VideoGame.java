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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainMenu m = new MainMenu("MainMenu", 512, 512,new Display("Menu", 512, 512));
        m.start();
    }
    
}