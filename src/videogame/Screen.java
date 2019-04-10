/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author PatoSaldivar
 */
public class Screen {
    private int x;
    private int y;
    private int x2;
    private int y2;
    private Game game;
    private Player player;
    private ArrayList <Item> list;
    private int yRectangleInfo=48;
      public Screen(int x, int y, int x2, int y2, Game game, ArrayList<Item> list, Player player) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.game = game;
        this.list = list;
        this.player = player;
        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList getList() {
        return list;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }
    
        public Rectangle getPerimetro() {

        return new Rectangle(getX(), getY(), getX2() , getY2());
    }
      
        
       public void render(Graphics g) {
           Graphics2D g2d = (Graphics2D) g;
           x=player.getSMoveX();
           x2=player.getSMoveX()+Assets.background.getWidth()/4;
           y=player.getSMoveY();
           y2=player.getSMoveY()+ Assets.background.getHeight()/4;
           
        g.drawImage(Assets.background,0,0,game.getWidth(),game.getHeight(),x, y, x2, y2, Color.black, null);
        for(int i=0; i<list.size(); i++){
            if(getPerimetro().contains(list.get(i).getPerimetro())){
                list.get(i).render(g);
            }
        }
          player.render(g);
         
           //Displays the top rectangle with information of the player
            g2d.setColor(new Color(249, 171, 85));
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 5 * 0.1f));
            g2d.fillRect(0, 0, game.getWidth(), yRectangleInfo);
            g2d.drawImage(Assets.playerPortait, 0, 0 ,48, yRectangleInfo, null);
            g2d.setColor(Color.black);
            g2d.drawRect(0, 0, game.getWidth(),yRectangleInfo);
            g2d.setColor(new Color(114, 24, 23));
            g2d.setFont(game.getFontx());
            DecimalFormat dform = new DecimalFormat("0.00");
            String message = "Money:" + dform.format(player.getMoney());
            g2d.drawString(message, 64, yRectangleInfo*2/3);
            g2d.setFont(game.getFontx());
            message = "Inventory:"+player.getInventory()+"/" + player.getCapacity();
            g2d.drawString(message, 272, yRectangleInfo*2/3);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 10 * 0.1f));
    }
}
