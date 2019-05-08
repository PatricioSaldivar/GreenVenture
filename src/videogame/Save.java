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
    private ArrayList<TrashContainer> trashContainers;
    private Car car;

    public Save(Game g, LinkedList<Trash> trash, ArrayList<NPC> npcs, ArrayList<TrashContainer> trashContainers, Car car) {
        this.g = g;
        this.trash = trash;
        this.npcs = npcs;
        this.trashContainers = trashContainers;
        this.car = car;
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
            double mon =  (g.getPlayer().getMoney());
            double totalIncome = (g.getPlayer().getTotalIncome());
            // 3. Execute SQL query
            int conversation = 0;
            if (g.getPlayer().isConversation()) {
                conversation = 1;
            }
            int talking = 0;
            if (g.getPlayer().isTalking()) {
                talking = 1;
            }
            int Trashcan0 = 0;
            if (g.getTrashContainers().get(0).isUnlocked()) {
                Trashcan0 = 1;
            }
            int Trashcan1 = 0;
            if (g.getTrashContainers().get(1).isUnlocked()) {
                Trashcan1 = 1;
            }
            int Trashcan2 = 0;
            if (g.getTrashContainers().get(2).isUnlocked()) {
                Trashcan2 = 1;
            }
            int Trashcan3 = 0;
            if (g.getTrashContainers().get(3).isUnlocked()) {
                Trashcan3 = 1;
            }
            int Trashcan4 = 0;
            if (g.getTrashContainers().get(4).isUnlocked()) {
                Trashcan4 = 1;
            }
            int Trashcan5 = 0;
            if (g.getTrashContainers().get(5).isUnlocked()) {
                Trashcan5 = 1;
            }
            int Trashcan6 = 0;
            if (g.getTrashContainers().get(6).isUnlocked()) {
                Trashcan6 = 1;
            }
            int Trashcan7 = 0;
            if (g.getTrashContainers().get(7).isUnlocked()) {
                Trashcan7 = 1;
            }
            int Trashcan8 = 0;
            if (g.getTrashContainers().get(8).isUnlocked()) {
                Trashcan8 = 1;
            }
            int Trashcan9 = 0;
            if (g.getTrashContainers().get(9).isUnlocked()) {
                Trashcan9 = 1;
            }
            int Trashcan10 = 0;
            if (g.getTrashContainers().get(10).isUnlocked()) {
                Trashcan10 = 1;
            }
            int Trashcan11 = 0;
            if (g.getTrashContainers().get(11).isUnlocked()) {
                Trashcan0 = 1;
            }
            int Trashcan12 = 0;
            if (g.getTrashContainers().get(12).isUnlocked()) {
                Trashcan12 = 1;
            }
            int Trashcan13 = 0;
            if (g.getTrashContainers().get(13).isUnlocked()) {
                Trashcan13 = 1;
            }
            int Trashcan14 = 0;
            if (g.getTrashContainers().get(14).isUnlocked()) {
                Trashcan14 = 1;
            }
            int Trashcan15 = 0;
            if (g.getTrashContainers().get(15).isUnlocked()) {
                Trashcan15 = 1;
            }

            myStmt.executeLargeUpdate("UPDATE Player SET  x=" + g.getPlayer().getX() + ",  y=" + g.getPlayer().getY() + ",  SMoveX=" + g.getPlayer().getSMoveX() + ", SMoveY=" + g.getPlayer().getSMoveY() + ", speed=" + g.getPlayer().getSpeed() + ", capacity=" + g.getPlayer().getCapacity() + ", inventory=" + g.getPlayer().getInventory() + ", glass=" + g.getPlayer().getGlass() + ", paper=" + g.getPlayer().getPaper() + ", plastic=" + g.getPlayer().getPlastic() + ", aluminum=" + g.getPlayer().getAluminum() + ", electronics=" + g.getPlayer().getElectronics()
                    + ", organic=" + g.getPlayer().getOrganic() + ",  money=" + mon + ",  trashUpgrade=" + g.getPlayer().getTrashUpgrade() + ",  speedUpgrade=" + g.getPlayer().getSpeedUpgrade() + ",  capacityUpgrade=" + g.getPlayer().getCapacityUpgrade() + ",  direction=" + g.getPlayer().getDirection() + ",  kilometers=" + g.getPlayer().getKilometers() + ",  totalTrash=" + g.getPlayer().getTotalTrash() + ",  totalIncome=" + totalIncome + " ,  progress=" + g.getPlayer().getProgress()
                    + ",  conversation=" + conversation + ",  talking=" + talking + ",  Trashcan0=" + Trashcan0 + ",  Trashcan1=" + Trashcan1 + ",  Trashcan2=" + Trashcan2 + ",  Trashcan3=" + Trashcan3 + ",  Trashcan4=" + Trashcan4 + ",  Trashcan5=" + Trashcan5 + ",  Trashcan6=" + Trashcan6 + ",  Trashcan7=" + Trashcan7 + ",  Trashcan8=" + Trashcan8 + ",  Trashcan9=" + Trashcan9 + ",  Trashcan10=" + Trashcan10 + ",  Trashcan11=" + Trashcan11 + ",  Trashcan12=" + Trashcan12 + ",  Trashcan13=" + Trashcan13 + ",  Trashcan14=" + Trashcan14 + ",  Trashcan15=" + Trashcan15
                    + " WHERE playerId=1;");
            myStmt.executeLargeUpdate("DELETE from Trash;");
            
            for (int i = 0; i < trash.size(); i++) {
                myStmt.executeLargeUpdate("INSERT INTO Trash(trashId,npcId,type,iniX,iniY) Values(" + i + "," + trash.get(i).getNpcId() + "," + trash.get(i).getType() + "," + trash.get(i).getIniX() + "," + trash.get(i).getIniY() + ");");
            }
            
            myStmt.executeLargeUpdate("DELETE from trashContainers;");
            for (int i = 0; i < trashContainers.size(); i++) {
                int unlocked = 0;
                if(trashContainers.get(i).isUnlocked()){
                    unlocked=1;
                }
                myStmt.executeLargeUpdate("INSERT INTO trashContainers(trashContainerId,unlocked,glass,electronics,organics,paper,plastic,aluminum,howManyTrash) Values(" + i + "," + unlocked + "," + trashContainers.get(i).getGlass() + "," + trashContainers.get(i).getElectronics() + "," + trashContainers.get(i).getOrganic() +"," + trashContainers.get(i).getPaper() +"," + trashContainers.get(i).getPlastic() +"," + trashContainers.get(i).getAluminum() + "," + trashContainers.get(i).getHowManyTrash() +");");
            }
            
           
            
            for (int i = 0; i < npcs.size(); i++) {
                talking = 0;
                int justThrow =0;
                if(npcs.get(i).isTalking()){
                    talking=1;
                }
                if(npcs.get(i).isJustThrowedTrash()){
                    justThrow=1;
                }
                
               myStmt.executeLargeUpdate("UPDATE NPC SET trashMaker =" + npcs.get(i).getTrashMaker()+", trashThrown =" +npcs.get(i).getTrashThrown() + ", speed ="+npcs.get(i).getSpeed() + ",xMove =" +npcs.get(i).getxMove() + ", yMove =" + npcs.get(i).getyMove()+ ",direction =" +npcs.get(i).getDirection() +", trashMakerHelper = "+ npcs.get(i).getTrashMakerHelper()+", justThrowedTrashHelper =" +npcs.get(i).getJustThrowedTrashHelper() +", justThrowedTrash ="+ justThrow+ ", talking =" +talking +", randomDist ="+npcs.get(i).getRandomDist() + ", distanceTraveled ="+ npcs.get(i).getDistanceTraveled()+ ", trashToTrashContainer = "+ npcs.get(i).getTrashToTrashContainer()+ ", iniX ="+ npcs.get(i).getIniX()+ ", iniY="+npcs.get(i).getIniY() +",idTrashContainer ="+npcs.get(i).getTrashContainerId()
                       +" WHERE npcId="+i+";");
            }
            
            myStmt.executeLargeUpdate("UPDATE Car SET iniX ="+car.getIniX() +", iniY="+car.getIniY() +", xMove="+ car.getxMove()+", yMove="+car.getyMove() +",direction="+car.getDirection() +", alreadyChecked="+car.getAlreadyChecked()
                    + " WHERE CarID=0");
            
            
            
            
            
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
