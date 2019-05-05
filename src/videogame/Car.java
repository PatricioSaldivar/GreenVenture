/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author PatoSaldivar
 */
public class Car extends Item{
    private int x;
    private int y;
    private int SMoveX;
    private int SMoveY;
    private int iniX;
    private int iniY;
    private int width;
    private int height;
    private Game game;
    Car(int x, int y, int width, int height, Game game){
        super(x,y);
        this.width = width;
        this.height = height;
        this.game = game;
        iniX = x;
        iniY =y;
                
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getSMoveX() {
        return SMoveX;
    }

    public void setSMoveX(int SMoveX) {
        this.SMoveX = SMoveX;
    }

    public int getSMoveY() {
        return SMoveY;
    }

    public void setSMoveY(int SMoveY) {
        this.SMoveY = SMoveY;
    }
    

    @Override
    public void tick() {
        //Movement in roads
     //  for(int i=0; i<game.getWalkers().size();i++){
      //     for(int j=0; j<game.getNpcs().size(); j++){
        //   if(game.getWalkers().get(i).getPerimetro().intersects(game.getNpcs().get(j).getPerimetro()) || game.getWalkers().get(i).getPerimetro().intersects(game.getPlayer().getPerimetro())){
               //PAusar movimiento de carro
         //  }
          //     }
      // }
        //Checks if theres a person on the Walker
       
      //  x = iniX + SMoveX - game.getScreen().getX();
      //  y = iniY + SMoveY - game.getScreen().getY();
    }

    @Override
    public void render(Graphics g) {
        
    }

    @Override
    public Rectangle getPerimetro() {
         return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
}
