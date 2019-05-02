/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

/**
 *
 * @author PatoSaldivar
 */

import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PatoSaldivar
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class Save {

    private Player player;
    private Game game;
    private ArrayList<NPC> npcs;
    private LinkedList<Trash> trash; // to manage the trash taht is in the game
    private KeyManager keyManager = new KeyManager();
    private MainMenu mm;
    
public Save(MainMenu mm){
    this.mm = mm;
}
    public void tick() throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "kZvbgxZ6xB";
        String pass = "hKgYJViiNZ";

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/kZvbgxZ6xB", user, pass);

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select *\n"
                    + "from Player p\n"
                    + "where p.playerId = (select playerId\n"
                    + "  from Videogame)");

            // 4. Process the result set (Player)
            while (myRs.next()) {
                //Agregar el mapa id para saber el mapa
                game = new Game("Juego", 512,512, mm.getDisplay(),mm.getKeyManager());
                player = new Player (0,0,64,64,game);
                
                player.setX(myRs.getInt(3));
                player.setY(myRs.getInt(4));
                player.setSMoveX(myRs.getInt(5));
                player.setSMoveY(myRs.getInt(6));
                player.setSpeed(myRs.getInt(7));
                player.setCapacity(myRs.getInt(8));
                player.setInventory(myRs.getInt(9));
                player.setGlass(myRs.getInt(10));
                player.setPaper(myRs.getInt(11));
                player.setPlastic(myRs.getInt(12));
                player.setAluminum(myRs.getInt(13));
                player.setElectronics(myRs.getInt(14));
                player.setOrganic(myRs.getInt(15));
                player.setMoney(myRs.getInt(16));
            }


        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }
    

}