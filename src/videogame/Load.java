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

public class Load {

    private Player player;
    private Game game;
    private ArrayList<NPC> npcs;
    private LinkedList<Trash> trash; // to manage the trash taht is in the game
    private KeyManager keyManager = new KeyManager();
    private MainMenu mm;
    private Screen screen;
    private Car car;

    public Load(MainMenu mm) {
        this.mm = mm;
        npcs = new ArrayList<>();
        trash = new LinkedList<>();
    }

    public Screen getScreen() {
        return screen;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    public LinkedList<Trash> getTrash() {
        return trash;
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
                    + "where p.playerId =1;");

            // 4. Process the result set (Player)
            while (myRs.next()) {
                //Agregar el mapa id para saber el mapa
                player = new Player(0, 0, 64, 64, game);
                player.setX(myRs.getInt(2));
                player.setY(myRs.getInt(3));
                player.setSMoveX(myRs.getInt(4));
                player.setSMoveY(myRs.getInt(5));
                player.setSpeed(myRs.getInt(6));
                player.setCapacity(myRs.getInt(7));
                player.setInventory(myRs.getInt(8));
                player.setGlass(myRs.getInt(9));
                player.setPaper(myRs.getInt(10));
                player.setPlastic(myRs.getInt(11));
                player.setAluminum(myRs.getInt(12));
                player.setElectronics(myRs.getInt(13));
                player.setOrganic(myRs.getInt(14));
                player.setMoney(myRs.getInt(15));
                player.setTrashUpgrade(myRs.getInt(16));
                player.setSpeedUpgrade(myRs.getInt(17));
                player.setCapacityUpgrade(myRs.getInt(18));
                player.setDirection(myRs.getInt(19));
                player.setKilometers(myRs.getInt(20));
                player.setTotalTrash(myRs.getInt(21));
                player.setTotalIncome(myRs.getInt(22));
                player.setProgress(myRs.getInt(23));
                if (myRs.getInt(24) == 1) {
                    player.setConversation(true);
                } else {
                    player.setConversation(false);
                }
                if (myRs.getInt(25) == 1) {
                    player.setTalking(true);
                } else {
                    player.setTalking(false);
                }
                ArrayList<Integer> list;
                list = new ArrayList<Integer>();
                if (myRs.getInt(26) == 0) {
                    list.add(0);
                }
                if (myRs.getInt(27) == 0) {
                    list.add(1);
                }
                if (myRs.getInt(28) == 0) {
                    list.add(2);
                }
                if (myRs.getInt(29) == 0) {
                    list.add(3);
                }
                if (myRs.getInt(30) == 0) {
                    list.add(4);
                }
                if (myRs.getInt(31) == 0) {
                    list.add(5);
                }
                if (myRs.getInt(32) == 0) {
                    list.add(6);
                }
                if (myRs.getInt(33) == 0) {
                    list.add(7);
                }
                if (myRs.getInt(34) == 0) {
                    list.add(8);
                }
                if (myRs.getInt(35) == 0) {
                    list.add(9);
                }
                if (myRs.getInt(36) == 0) {
                    list.add(10);
                }
                if (myRs.getInt(37) == 0) {
                    list.add(11);
                }
                if (myRs.getInt(38) == 0) {
                    list.add(12);
                }
                if (myRs.getInt(39) == 0) {
                    list.add(13);
                }
                if (myRs.getInt(40) == 0) {
                    list.add(14);
                }
                if (myRs.getInt(41) == 0) {
                    list.add(15);
                }
            }
            game.setPlayer(player);
            screen = new Screen(0, 0, 512,512, game, player, trash);
            

            myRs = myStmt.executeQuery("select *\n"
                    + "from Trash \n");

            // 4. Adds Trash
            while (myRs.next()) {
                trash.add(new Trash(myRs.getInt(4), myRs.getInt(5), 32, 32, myRs.getInt(3), game, screen, myRs.getInt(2)));
            }

            game.setScreen(screen);
            screen.setTrash(trash);

            myRs = myStmt.executeQuery("select *\n"
                    + "from NPC \n");
            

            // 4. Adds Trash
            while (myRs.next()) {
                
                
               npcs.add(new NPC(myRs.getInt(15),myRs.getInt(16) , 64, 64, game, screen, myRs.getInt(1),myRs.getInt(17)));
               npcs.get(npcs.size()-1).setTrashMaker(myRs.getInt(2));
               npcs.get(npcs.size()-1).setTrashThrown(myRs.getInt(3));
               //Speed is 4 but dosent change
               npcs.get(npcs.size()-1).setxMove(myRs.getInt(5));
               npcs.get(npcs.size()-1).setyMove(myRs.getInt(6));
               npcs.get(npcs.size()-1).setDirection(myRs.getInt(7));
               npcs.get(npcs.size()-1).setTrashMakerHelper(myRs.getInt(8));
               npcs.get(npcs.size()-1).setJustThrowedTrashHelper(myRs.getInt(9));
               if(myRs.getInt(10)==1){
               npcs.get(npcs.size()-1).setJustThrowedTrash(true);
               }else{
                   npcs.get(npcs.size()-1).setJustThrowedTrash(false);
               }
               if(myRs.getInt(11) == 1){
               npcs.get(npcs.size()-1).setTalking(true);
               }else{
                   npcs.get(npcs.size()-1).setTalking(false);
               }
               npcs.get(npcs.size()-1).setRandomDist(myRs.getInt(12));
               npcs.get(npcs.size()-1).setDistanceTraveled(myRs.getInt(13));
               npcs.get(npcs.size()-1).setTrashContainerId(myRs.getInt(14));
            }
            game.setNpcs(npcs);
            
            
                        myRs = myStmt.executeQuery("select *\n"
                    + "from Car \n");

            // 4. Adds Trash
            while (myRs.next()) {
               car = new Car(myRs.getInt(2),myRs.getInt(3) , 128, 128, screen, game);
               car.setSMoveX(myRs.getInt(4));
               car.setSMoveY(myRs.getInt(5));
               car.setDirection(myRs.getInt(6));
               car.setAlreadyChecked(myRs.getInt(7));
            }
            game.setCar(car);
            
            
                        myRs = myStmt.executeQuery("select *\n"
                    + "from trashContainers \n");

            // 4. Adds Trash
            while (myRs.next()) {
                if(myRs.getInt(2) == 1){
                game.getTrashContainers().get(myRs.getInt(1)).setUnlocked(true);
                }else{
                    game.getTrashContainers().get(myRs.getInt(1)).setUnlocked(false);
            }
                game.getTrashContainers().get(myRs.getInt(1)).setGlass(myRs.getInt(3));
                game.getTrashContainers().get(myRs.getInt(1)).setElectronics(myRs.getInt(4));
                game.getTrashContainers().get(myRs.getInt(1)).setOrganic(myRs.getInt(5));
                game.getTrashContainers().get(myRs.getInt(1)).setPaper(myRs.getInt(6));
                game.getTrashContainers().get(myRs.getInt(1)).setPlastic(myRs.getInt(7));
                game.getTrashContainers().get(myRs.getInt(1)).setAluminum(myRs.getInt(8));
                game.getTrashContainers().get(myRs.getInt(1)).setHowManyTrash(myRs.getInt(9));
                game.getTrashContainers().get(myRs.getInt(1)).setScreen(screen);
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
