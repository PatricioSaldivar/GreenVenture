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
    private Game g;

    public Save(Game g) {
        this.g = g;
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
            int mon = (int)(g.getPlayer().getMoney());
            // 3. Execute SQL query
            myStmt.executeLargeUpdate("UPDATE Player SET  mapId="+1+",  x="+g.getPlayer().getX()+",  y="+g.getPlayer().getY()+",  SMoveX="+g.getPlayer().getSMoveX()+", SMoveY="+g.getPlayer().getSMoveY()+", speed="+g.getPlayer().getSpeed()+", capacity="+g.getPlayer().getCapacity()+", inventory="+g.getPlayer().getInventory()+", glass="+g.getPlayer().getGlass()+", paper="+g.getPlayer().getPaper()+", plastic="+g.getPlayer().getPlastic()+", aluminum="+g.getPlayer().getAluminum()+", electronics="+g.getPlayer().getElectronics()+", organic="+g.getPlayer().getOrganic()+",  money="+mon+ " WHERE playerId=1;");
               

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
