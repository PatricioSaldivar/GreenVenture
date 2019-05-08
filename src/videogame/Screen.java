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
import static java.lang.Math.abs;
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
    private boolean cursorOnPlay = false;
    private Animation selector;
    private boolean trashContainerMessage;
    private int trashContainerId;
    private Animation playerTalking;
    private Animation npcTalking;
    private boolean assignAnimationNpc=false;
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
        selector = new Animation(Assets.selector,300);
        playerTalking = new Animation(Assets.playerTalk,250);
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

    public LinkedList<Trash> getTrash() {
        return trash;
    }

    public void setTrash(LinkedList<Trash> trash) {
        this.trash = trash;
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

    public boolean isCursorOnPlay() {
        return cursorOnPlay;
    }

    public void setCursorOnPlay(boolean cursorOnPlay) {
        this.cursorOnPlay = cursorOnPlay;
    }

    public boolean isTrashContainerMessage() {
        return trashContainerMessage;
    }

    public void setTrashContainerMessage(boolean trashContainerMessage) {
        this.trashContainerMessage = trashContainerMessage;
    }

    public int getTrashContainerId() {
        return trashContainerId;
    }

    public void setTrashContainerId(int trashContainerId) {
        this.trashContainerId = trashContainerId;
    }

    public boolean isAssignAnimationNpc() {
        return assignAnimationNpc;
    }

    public void setAssignAnimationNpc(boolean assignAnimationNpc) {
        this.assignAnimationNpc = assignAnimationNpc;
    }
    

    public void trashContainerMessageText(int id, Graphics2D g){
        int yPaint=400;
        String message;
       g.drawImage(Assets.playerHud,0,384,512,128,null);
        g.setColor(Color.BLACK);
        message ="Vaya en este basurero hay:\n"
                + game.getTrashContainers().get(id).getElectronics()+ "de basura electronicos\n"
                + game.getTrashContainers().get(id).getAluminum()+"de basura alumino\n"
                + game.getTrashContainers().get(id).getGlass()+"de basura vidiro\n"
                + game.getTrashContainers().get(id).getPlastic()+"de basura plastico\n"
                + game.getTrashContainers().get(id).getPaper()+"de basura paper\n"
                + game.getTrashContainers().get(id).getOrganic()+"de basura organica\n"
                + "Tomare lo que pueda caber en mi mochila! ";
            for (String line : message.split("\n")) {
            g.drawString(line, 128, yPaint += g.getFontMetrics().getHeight());
        }
        
    }
    
    public void conversation(NPC npc, Player player, Graphics2D g) {
        int yPaint = 416;
        String message;
        g.drawImage(Assets.playerHud,0,384,512,128,null);
        g.setColor(Color.BLACK);
        if (!player.isTalking()) {
            playerTalking.tick();
            g.drawImage(playerTalking.getCurrentFrame(), 0, 416, 64, 64, null);
            message = "¡Hey! No tires la basura al suelo, mejor tírala en un bote,\nno cuesta mucho, y además, ayudas a mantener la ciudad limpia.";
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
            message = "Oh vaya, una disculpa, no lo volveré a hacer.\nMe da gusto ver que hay gente que busca generar un cambio.";
            if (!finishedConversationText) {
                if (conversationTextIndex < message.length()) {
                    conversationTextIndex++;
                } else {
                    finishedConversationText = true;
                }
            } else {
                conversationTextIndex = message.length() - 1;
            }
            npcTalking.tick();
            g.drawImage(npcTalking.getCurrentFrame(), 0, 416, 64, 64, null);
        }
        if (conversationTextIndex < message.length() && message.charAt(conversationTextIndex) == '\'') {
            conversationTextIndex++;
        }
        for (String line : message.substring(0, conversationTextIndex).split("\n")) {
            g.drawString(line, 128, yPaint += g.getFontMetrics().getHeight());
        }

    }
    
    
        public void conversationMinigame(NPCMinigame1 npc, Player player, Graphics2D g) {
        int yPaint = 416;
        String message;
        g.drawImage(Assets.playerHud,0,384,512,128,null);
        g.setColor(Color.BLACK);
        if (!player.isTalking()) {
            playerTalking.tick();
            g.drawImage(playerTalking.getCurrentFrame(), 0, 416, 64, 64, null);
            message = "Hey me podrias ayudar organizando mi basura?\nPodrias usar mi robot de golpes! ";
            if (!finishedConversationText) {
                if (conversationTextIndex < message.length()) {
                    conversationTextIndex++;
                } else {
                    finishedConversationText = true;
                }
            } else {
                conversationTextIndex = message.length() - 1;
            }
            for (String line : message.substring(0, conversationTextIndex).split("\n")) {
            g.drawString(line, 128, yPaint += g.getFontMetrics().getHeight());
        }
        } else {
            message = "Claro! Donde esta ese robot?\nPor el momento no puedo, lo lamento ";
                conversationTextIndex = message.length() - 1;
                npcTalking.tick();
            g.drawImage(npcTalking.getCurrentFrame(), 0, 416, 64, 64, null);
            selector.tick();
            if(cursorOnPlay){
                 g.drawImage(selector.getCurrentFrame(),120,416+(g.getFontMetrics().getHeight()*1/2),8,8,null);
            }else{
                g.drawImage(selector.getCurrentFrame(),120,416+ g.getFontMetrics().getHeight()*5/2,8,8,null);
            }
            int i=1;
            for (String line : message.substring(0, conversationTextIndex).split("\n")) {
            g.drawString(line, 128, yPaint + g.getFontMetrics().getHeight()*i );
            i+=2;
            }
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

        for(int i=0; i< game.getStoreDoors().size();i++){
            game.getStoreDoors().get(i).render(g);
        }
        //Renders TrashContainers
        for(int i=0; i<game.getTrashContainers().size(); i++){
            game.getTrashContainers().get(i).tick();
            game.getTrashContainers().get(i).render(g);
        }
        
        
        // Renders trash * FIX CAMERA AREA*
        for (int i = 0; i < trash.size(); i++) {
            trash.get(i).tick();
            if (trash.get(i).isDead()) {
                game.getNpcs().get(trash.get(i).getNpcId()).setTrashThrown( game.getNpcs().get(trash.get(i).getNpcId()).getTrashThrown()-1);
                trash.remove(i);
                i--; 
            } else if (getPerimetro().contains(trash.get(i).getPerimetro())) {
                trash.get(i).render(g);
            }
        }
        
        
        for (int i = 0; i < game.getNpcs().size(); i++) {
            game.getNpcs().get(i).render(g);
            if (game.getNpcs().get(i).isTalking()) {
                talkingNPC = i;
                int dX;
                int dY;
                dX = game.getPlayer().getX()- game.getNpcs().get(i).getX();
                dY = game.getPlayer().getY()- game.getNpcs().get(i).getY();
                
                
                    if(dY>32){
                        //Facing Right
                        game.getNpcs().get(i).setConversation(Assets.npcFacingDown[i]);
                    }else if(dY<-32)
                        //Facing Left
                        game.getNpcs().get(i).setConversation(Assets.npcFacingUp[i]);
                if(dX>32){
                    //Facing Up
                    game.getNpcs().get(i).setConversation(Assets.npcFacingRight[i]);
                    
                }else if(dX<-32){
                    //Facing Down
                    game.getNpcs().get(i).setConversation(Assets.npcFacingLeft[i]);
                }       
            }
        }
       
        game.getCar().render(g);
        
        if (player.isConversation()) {
           if(trashContainerMessage){
              trashContainerMessageText(trashContainerId, g2d);
               
           }else if(game.getNpcTrashClassify().isTalking()){
                int dX;
                int dY;
                dX = game.getPlayer().getX()- game.getNpcTrashClassify().getX();
                dY = game.getPlayer().getY()- game.getNpcTrashClassify().getY();  
  
                    if(dY>32){
                        //Facing Down
                        game.getNpcTrashClassify().setConversation(Assets.npcFacingDown[game.getNpcTrashClassify().getId()]);
                    }else if(dY<-32){
                        //Facing Up
                        game.getNpcTrashClassify().setConversation(Assets.npcFacingUp[game.getNpcTrashClassify().getId()]);
                    }
                 if(dX>32){
                    //Facing Right
                    game.getNpcTrashClassify().setConversation(Assets.npcFacingRight[game.getNpcTrashClassify().getId()]);
                    
                }else if (dX<-32){
                    //Facing Left
                    game.getNpcTrashClassify().setConversation(Assets.npcFacingLeft[game.getNpcTrashClassify().getId()]);
                }
                
                if(!assignAnimationNpc){
               npcTalking = new Animation(Assets.npcTalk[game.getNpcTrashClassify().getId()],250);
               assignAnimationNpc=true;
               }
                conversationMinigame(game.getNpcTrashClassify(),player,g2d);
            }else{
               if(!assignAnimationNpc){
               npcTalking = new Animation(Assets.npcTalk[game.getNpcs().get(talkingNPC).getId()],250);
               assignAnimationNpc=true;
               }
            conversation(game.getNpcs().get(talkingNPC), player, g2d);
           }
        }
         game.getNpcTrashClassify().render(g);
        player.render(g);

        //Displays the top rectangle with information of the player
        if (!game.getKeyManager().pause) {       
            g.drawImage(Assets.playerHud,0, 0, game.getWidth(), RectangleInfoHeight,null); 
            g2d.drawImage(Assets.playerTalk[0], 12, 6, 36, 36, null);
            g2d.setColor(new Color(114, 24, 23));
            g2d.setFont(game.getFontx());
            DecimalFormat dform = new DecimalFormat("0.00");
            String message = ": " + dform.format(player.getMoney());
            coin.tick();
            g.drawImage(coin.getCurrentFrame(), 76,12, 24, RectangleInfoHeight/2, null);
            g2d.drawString(message, 100, RectangleInfoHeight * 2 / 3);
            g2d.setFont(game.getFontx());
             g.drawImage(Assets.backpack, 344,12, 24, RectangleInfoHeight/2, null);
            message = player.getInventory() + "/" + player.getCapacity();
            g2d.drawString(message, 376, RectangleInfoHeight * 2 / 3);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 10 * 0.1f));
        }
    }
}
