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
import java.util.LinkedList;

/**
 *
 * @author BonfireStudio
 */
public class Screen {

    private int x;                       // to store the position in x
    private int y;                       // to store the positon in y
    private int x2;                      // to store the x position plus the width of the display
    private int y2;                      // to store the y position plus the height of the display
    private Game game;                   // to store the game
    private Player player;               // to store the player
    private int RectangleInfoHeight = 48;  // to store the height of the rectangle info
    private KeyManager keyManager;  // to manage the keyboard
    private LinkedList<Trash> trash;
    private int talkingNPC;
    private boolean finishedConversationText = false;
    private int conversationTextIndex = 0;
    private Animation coin;

    /**
     * to create the screen with his attributes
     *
     * @param x to set the x position
     * @param y to set the y position
     * @param x2 to set the x2 position
     * @param y2 to set the y2 position
     * @param game to set the game where the brick is created
     * @param player to set the player
     * @param trash to set the array of items
     */
    public Screen(int x, int y, int x2, int y2, Game game, Player player, LinkedList<Trash> trash) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.game = game;
        this.player = player;
        this.trash = trash;
        coin = new Animation(Assets.coin, 100);
    }

    /**
     * To get the x of the screen
     *
     * @return an <code>int</code> value with the x
     */
    public int getX() {
        return x;
    }

    /**
     * To get the y of the screen
     *
     * @return an <code>int</code> value with the y
     */
    public int getY() {
        return y;
    }

    /**
     * To get the x2 of the screen
     *
     * @return an <code>int</code> value with the x2
     */
    public int getX2() {
        return x2;
    }

    /**
     * To get the y2 of the screen
     *
     * @return an <code>int</code> value with the y2
     */
    public int getY2() {
        return y2;
    }

    /**
     * To get the game
     *
     * @return an <code>Game</code> value with the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * To get the player
     *
     * @return an <code>Player</code> value with the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set the x of the screen
     *
     * @param x <b>x</b> value with the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the y of the screen
     *
     * @param y <b>y</b> value with the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set the x2 of the screen
     *
     * @param x2 <b>x2</b> value with the x2
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * Set the y2 of the screen
     *
     * @param y2 <b>x</b> value with the y2
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }

    /**
     * Set the game
     *
     * @param game <b>Game</b> value with the game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Set the player
     *
     * @param player <b>Player</b> value with the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * To get the rectangle that covers the perimeter of the screen
     *
     * @return an <code>Game</code> value with the game
     */
    public Rectangle getPerimetro() {
        //creates rectangle 

        return new Rectangle(-32, -32, game.getWidth() + 64, game.getHeight() + 64);
    }

    public boolean isFinishedConversationText() {
        return finishedConversationText;
    }

    public void setFinishedConversationText(boolean finishedConversationText) {
        this.finishedConversationText = finishedConversationText;
    }

    public void setConversationTextIndex(int conversationTextIndex) {
        this.conversationTextIndex = conversationTextIndex;
    }

    public void conversation(NPC npc, Player player, Graphics2D g) {
        int yPaint = 416;
        String message;
        g.setColor(Color.red);
        g.fillRect(0, 384, 512, 128);
        g.setColor(Color.BLACK);
        if (!player.isTalking()) {
            g.drawImage(Assets.player, 0, 416, 64, 64, null);
            message = "Hey que te pasa porque tiras basura mejor tirala en un bote\n o a la otra te parto toda EHHH!\nNo quiero verte hacerlo de nuevo ";
            if (!finishedConversationText) {
                if (conversationTextIndex < message.length()) {
                    conversationTextIndex++;
                } else {
                    finishedConversationText = true;
                }
            } else {
                conversationTextIndex = message.length() - 1;
            }
        } else {
            message = "Oh vaya, perdon no lo volvere a hacer\n que bueno que te importa el ambiente\n ten una gratificacion ";
            if (!finishedConversationText) {
                if (conversationTextIndex < message.length()) {
                    conversationTextIndex++;
                } else {
                    finishedConversationText = true;
                }
            } else {
                conversationTextIndex = message.length() - 1;
            }
            g.drawImage(Assets.inTrashCan, 0, 416, 64, 64, null);
        }
        if (conversationTextIndex < message.length() && message.charAt(conversationTextIndex) == '\'') {
            conversationTextIndex++;
        }
        for (String line : message.substring(0, conversationTextIndex).split("\n")) {
            g.drawString(line, 128, yPaint += g.getFontMetrics().getHeight());
        }

    }

    public void render(Graphics g) {
        
        //Variable to create transparency on images and to create 2 dimensional graphics effects
        Graphics2D g2d = (Graphics2D) g;
        //Assigns values to screen parameters to follow the player
        x = player.getSMoveX();
        x2 = player.getSMoveX() + Assets.background.getWidth() / 8;
        y = player.getSMoveY();
        y2 = player.getSMoveY() + Assets.background.getHeight() / 8;

        //Draws the background depending of the screen parameter   
        g.drawImage(Assets.background, 0, 0, game.getWidth(), game.getHeight(), x, y, x2, y2, Color.black, null);

        // Renders trash * FIX CAMERA AREA*
        for (int i = 0; i < trash.size(); i++) {
            trash.get(i).tick();
            if (trash.get(i).isDead()) {
                Assets.pickTrash.play();
                game.getNpcs().get(trash.get(i).getNpcId()).setTrashThrown();
                trash.remove(i);
            } else if (getPerimetro().contains(trash.get(i).getPerimetro())) {
                trash.get(i).render(g);
            }
        }
        player.render(g);
        for (int i = 0; i < game.getNpcs().size(); i++) {
            game.getNpcs().get(i).render(g);
            if (game.getNpcs().get(i).isTalking()) {
                talkingNPC = i;
            }
        }
        if (player.isConversation()) {

            conversation(game.getNpcs().get(talkingNPC), player, g2d);
        }

        //Displays the top rectangle with information of the player
        if (!game.getKeyManager().pause) {
            g2d.setColor(new Color(249, 171, 85));
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 7 * 0.1f));
            g2d.fillRect(0, 0, game.getWidth(), RectangleInfoHeight);
            g2d.drawImage(Assets.playerPortait, 0, 0, 48, RectangleInfoHeight, null);
            g2d.setColor(Color.black);
            g2d.drawRect(0, 0, game.getWidth(), RectangleInfoHeight);
            g2d.setColor(new Color(114, 24, 23));
            g2d.setFont(game.getFontx());
            DecimalFormat dform = new DecimalFormat("0.00");
            String message = ": " + dform.format(player.getMoney());
            coin.tick();
            g.drawImage(coin.getCurrentFrame(), 88,12, 24, RectangleInfoHeight/2, null);
            g2d.drawString(message, 112, RectangleInfoHeight * 2 / 3);
            g2d.setFont(game.getFontx());
            message = "Inventario:" + player.getInventory() + "/" + player.getCapacity();
            g2d.drawString(message, 272, RectangleInfoHeight * 2 / 3);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 10 * 0.1f));
        }
    }
}
