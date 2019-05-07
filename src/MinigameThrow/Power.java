/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinigameThrow;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.TexturePaint;
import videogame.Assets;
import videogame.Item;

/**
 *
 * @author PatoSaldivar
 */
public class Power extends Item {
    private int powerX=1;
    private int powerY=1;

    
    public Power(int x, int y) {
        super(x, y);
    }

    public int getPowerX() {
        return powerX;
    }

    public void setPowerX(int increase) {
        this.powerX += increase;
    }

    public int getPowerY() {
        return powerY;
    }

    public void setPowerY(int increase) {
        this.powerY += increase;
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
         Graphics2D g2 = (Graphics2D) g;
    
    if(powerX>10 && powerX<16){
    g2.setColor(Color.yellow);
    }else if(powerX>15 && powerX<21){
    g2.setColor(Color.red);
    }else{
         g2.setColor(Color.green);
    }
    g2.fillRect(200, 437, powerX*10, 75);
    
        if(powerY>10 && powerY<16){
    g2.setColor(Color.yellow);
    }else if(powerY>15 && powerY<21){
    g2.setColor(Color.red);
    }else{
         g2.setColor(Color.green);
    }
    g2.fillRect(0, 200, 75, 200);
    g2.setColor(Color.gray);
    g2.fillRect(0, 200, 75, 200-powerY*10);
    
    
     g2.setColor(Color.BLACK);
    g2.drawRect(0, 200, 75, 200);
    g2.drawRect(200, 437, 200, 75);
    
    g2.drawString("Fuerza Horizontal", 100, 100-g2.getFont().getSize());
     g2.drawString("Fuerza Vertical", 100, 200-g2.getFont().getSize());
     //g2.setStroke(new BasicStroke(3));
     TexturePaint paint = new TexturePaint(Assets.leftBox,new Rectangle(16,16,16,16));
     g2.setPaint(paint);

     Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    g2.setStroke(dashed);
      g2.drawLine(64, 512-64, powerX*5+64, -powerY*5+512-64);
      
    }

    @Override
    public Rectangle getPerimetro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
