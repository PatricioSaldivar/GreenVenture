/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
//
/**
 *
 * @author BonfireStudios
 */
public class Tutorial implements Runnable {

    private BufferStrategy bs;             // to have several buffers when displaying
    private Graphics g;                     // to paint objects
    private Display display;                // to display in the game
    String title;                           // title of the window
    private int width;                      // width of the window
    private int height;                     // height of the window
    private Thread thread;                  // thread to create the game
    private boolean running;                // to set the game
    private KeyManager keyManager;          // to manage the keyboard
    private Font fontx;                     // to manage a custom font
    private Game game;                      // to store the previous game
    private PlayerInStore playablePlayer;   // to store the tutorial player
    private ArrayList<Solid> solids;         // to store the solids
    private int indexInstruction = 0;         // to store the index of the instruction that will be shown
    private int iContS = 0;                   // count to show letter per letter in the messages
    private int index = 0;                      // to store the index for the switch of messages
    private boolean showMessage = true;               // to store if the next message will be shown
    private boolean up = false;                         // to store if the player have move up
    private boolean down = false;                       // to store if the player have move down
    private boolean right = false;                      // to store if the player have move right
    private boolean left = false;                       // to store if the player have move left
    private LinkedList<Trash> trash;                    // to store the trash
    private boolean trashHasBeenCreated = false;        // to check if trash has been created
    private boolean npcHasBeenCreated = false;          // to check if npc has been created
    private NPC npc;                                    // creates npc    
    private boolean npcTalk = false;                    // checks if player wants to talk with npc
    private boolean eraseNPC = false;                   // checks if player already talked to npc so next instruction shows up
    private int nextSlide = 0;                  //checks when message need to appear after case 3


    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Tutorial(String title, int width, int height, Display display, KeyManager keyManager, Game game) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        this.keyManager = keyManager;
        this.display = display;
        display.setTitle("ReycleCoRoom");
        this.game = game;
        this.solids = new ArrayList<>();
        trash = new LinkedList<>();

        //Adds font from fonts package
        try {
            fontx = Font.createFont(Font.TRUETYPE_FONT, Font.class.getResourceAsStream("/fonts/pixelfont.ttf")).deriveFont(24f);
        } catch (FontFormatException | IOException ex) {
        }

    }

    public Graphics getG() {
        return g;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the custom font
     *
     * @return an <code>.ttf</code> value with the height +
     */
    public Font getFontx() {
        return fontx;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        Assets.init();
        Assets.songGame.play();
        solids.add(new Solid(0,0,512,233));
        solids.add(new Solid(0,422,32,990));
        solids.add(new Solid(0,0,512,233));
        //Solids so npc cant get out of the map
        solids.add(new Solid(0,-10,512,10));
        solids.add(new Solid(0,512,512,10));
        solids.add(new Solid(-10,-10,10,532));
        solids.add(new Solid(512,-10,10,532));
        playablePlayer = new PlayerInStore(width / 2, 448, 64, 64, game, solids);
    }

    @Override
    public void run() {
        //If the game is called to resume the init wont be called 
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public void npcMessage(int contM) {
        int iPosy = 20;
        String message;
        g.setColor(Color.BLACK);
        g.drawImage(Assets.textBox, 0, 0, 512, 128, null);
        g.drawImage(Assets.playerFacingDown, 27, 36, 44, 54, null);
        message = "¡Hey! No tires la basura al suelo,\nmejor tírala en un bote,no cuesta mucho,\ny además, ayudas a mantener la\nciudad limpia. ";
        if (iContS < message.length() - 1) {
            iContS++;
            if (keyManager.space && !keyManager.helperSpace) {
                iContS = message.length() - 1;
            }
        } else {
            if (keyManager.space && !keyManager.helperSpace) {
                iContS = 0;
                npcTalk = false;
                eraseNPC = true;
            }
        }
        if (message.charAt(iContS) == '\'') {
            iContS++;
        }
        for (String line : message.substring(0, iContS).split("\n")) {
            g.drawString(line, 100, iPosy += g.getFontMetrics().getHeight());
        }

    }

    public void tutorialMessage(int contM) {
        int iPosy = 20;
        g.setFont(g.getFont().deriveFont(16f));
        String message = " ";
        g.setColor(Color.BLACK);
        g.drawImage(Assets.textBox, 0, 0, 512, 128, null);
        switch (contM) {
            case 0:
                message = "Utiliza las flechas del teclado para moverte\npor el mapa. ";
                g.drawImage(Assets.arrows, 27, 36, 44, 54, null);
                break;
            case 1:
                message = "Recoge toda la basura veas con la tecla ESPACIO,\ntienes un límite de basura que puedes\nalmacenar dependiendo de la capacidad\nde tu mochila. ";
                g.drawImage(Assets.backpack, 27, 36, 44, 54, null);
                break;
            case 2:
                message = "Esa persona acaba de tirar basura.\nAcércte a él y presiona la tecla ESPACIO\npara decirle que la tire en el basurero.\nLa próxima vez que veas a alguien con este\nsímbolo de exclamación en su cabeza\ndile que no tire la basura en el suelo ";
                g.drawImage(Assets.npcDown[0][0], 27, 36, 44, 54, null);
                break;
            case 3:
                message = "Este es un bote de basura, si le llamas la\natención a las personas, mejorarán sus hábitos\ny comenzarán a tirar basura en el bote. ";
                g.drawImage(Assets.trashcanDefault, 27, 36, 44, 54, null);
                break;
            case 4:
                message = "Puedes sustituir los botes de basura\npor reciclabes al comprarlos en la tienda.\n En los botes reciclables (verdes) puedes\nrecoger la basura que esta en los botes verdes ";
                g.drawImage(Assets.trashcanOwned, 27, 36, 44, 54, null);
                break;
            case 5:
                message = "Ya que tienes basura puedes ir a\nvenderla en la tienda de reciclaje\nllamada 'RecycleCo'. ";
                g.drawImage(Assets.trash[0], 27, 36, 44, 54, null);
                break;
            case 6:
                message = "Una ves que hayas vendido tu basura\nrecibiras dinero, con el puedes comprar\nmejoras para tu jugador. ";
                g.drawImage(Assets.coin[0], 27, 36, 44, 54, null);
                break;
            case 7:
                message = "'TodoxMart' es la tienda donde comprar\nlas mejoras. ";
                g.drawImage(Assets.trash[14], 27, 36, 44, 54, null);
                break;
        }

        if (iContS < message.length() - 1) {
            iContS++;
            if (keyManager.space && !keyManager.helperSpace) {
                iContS = message.length() - 1;
            }
        } else {
            if (keyManager.space && !keyManager.helperSpace) {
                Assets.selectSound.play();
                indexInstruction++;
                iContS = 0;
                showMessage = false;
                nextSlide++;
            }
        }
        if (message.charAt(iContS) == '\'') {
            iContS++;
        }
        for (String line : message.substring(0, iContS).split("\n")) {
            g.drawString(line, 100, iPosy += g.getFontMetrics().getHeight());
        }

    }

    private void tick() {
        keyManager.tick();
        playablePlayer.tick();
        if (index == 2) {
            npc.setJustThrowedTrash(true);
        }

        switch (index) {
            case 0:
                if (up && down && right && left) {
                    index++;
                    showMessage = true;
                }

                break;
            case 1:
                if (trash.isEmpty()) {
                    index++;
                    showMessage = true;
                }
                break;
            case 2:
                if (eraseNPC) {
                    index++;
                    showMessage = true;
                }
                break;

            case 3:
                if (nextSlide > index) {
                    index++;
                    showMessage = true;
                }
                break;
            case 4:
                if (nextSlide > index) {
                    index++;
                    showMessage = true;
                }
                break;
            case 5:
                if (nextSlide > index) {
                    index++;
                    showMessage = true;
                }
                break;
            case 6:
                if (nextSlide > index) {
                    index++;
                    showMessage = true;
                }
                break;
            case 7:
                if (nextSlide > index) {
                    index++;
                    showMessage = true;
                }
                break;

        }

        //Flags that need to be activated so the next message comes to the screen
        if (!showMessage) {
            //Checks if player move up , down, right and left
            if (keyManager.up && !keyManager.helperUp) {
                up = true;
            }
            if (keyManager.down && !keyManager.helperDown) {
                down = true;
            }
            if (keyManager.right && !keyManager.helperRight) {
                right = true;
            }
            if (keyManager.left && !keyManager.helperLeft) {
                left = true;
            }

            //Checks if the player grabs the trash and erase it
            for (int i = 0; i < trash.size(); i++) {
                if ((playablePlayer.getPerimetro().intersects(trash.get(i).getPerimetro()))) {
                    if (keyManager.space && !keyManager.helperSpace) {
                        trash.remove(i);
                    }
                }
            }

            //Shows dialogue if player touches and press space in npc
            if (index == 2) {
                if (playablePlayer.getPerimetro().intersects(npc.getPerimetro())) {
                    if (keyManager.space && !keyManager.helperSpace) {
                        npcTalk = true;
                    }
                }
            }

        }

        //Creates trash
        if (index == 1 && !trashHasBeenCreated) {
            trashHasBeenCreated = true;
            trash.add(new Trash((width / 2), (height / 2), 32, 32, 0, game, game.getScreen(), 100));
            trash.add(new Trash((width / 2) + 40, (height / 2), 32, 32, 4, game, game.getScreen(), 99));
            trash.add(new Trash((width / 2) + 80, (height / 2), 32, 32, 6, game, game.getScreen(), 98));
            trash.add(new Trash((width / 2) + 160, (height / 2), 32, 32, 10, game, game.getScreen(), 97));
        }
        //Creates NPC and tick's it
        if (index == 2 && !npcHasBeenCreated) {
            npcHasBeenCreated = true;
            npc = new NPC(90, 250, 64, 64, game, game.getScreen(), 0, 0);
        }
        
        //Ones the player finish tutorial, the game starts
        if(index > 7){
            Assets.gameStart.play();
            Assets.songGame.stop();
            game.setCont(true);
            game.start();
            running = false;
        }

    }

    public Rectangle getPerimetroStore() {
        return new Rectangle(0, 120, 210, 215);
    }

    public Rectangle getPerimetroWorld() {
        return new Rectangle(172, 450, 75, 62);
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.clearRect(0, 0, width, height);
            g.drawImage(Assets.tutorialBackground, 0, 0, width, height, null);
            for (int i = 0; i < trash.size(); i++) {
                trash.get(i).render(g);
            }
            playablePlayer.render(g);
            //Score values por painting
            g.setFont(fontx);
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(16f));
            //Shows message every time the before 
            if (indexInstruction < 8 && showMessage) {
                this.tutorialMessage(indexInstruction);
            }
            //Render NPC only when his dialogue is open
            if (index == 2) {
                npc.render(g);
                //Creates npc dialogue
                if (npcTalk) {
                    npcMessage(iContS);
                }
            }
            bs.show();
            g.dispose();
        }

    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
