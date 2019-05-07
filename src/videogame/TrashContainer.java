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
public class TrashContainer extends Item {

    private int width;
    private int height;
    private Screen screen;
    private int howManyTrash;
    private boolean unlocked;
    private int glass;
    private int electronics;
    private int organic;
    private int paper;
    private int plastic;
    private int aluminum;
    private int iniX;
    private int iniY;
    private int id;
    private int npcId[];
    private int npcTrash[];

    public TrashContainer(int x, int y, int width, int height, Screen screen, int id, int npcId[]) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.screen = screen;
        glass = 0;
        electronics = 0;
        organic = 0;
        paper = 0;
        plastic = 0;
        aluminum = 0;
        iniX = x;
        iniY = y;
        this.id = id;
        this.npcId = npcId;
        npcTrash = new int[npcId.length];

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

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public int getIniX() {
        return iniX;
    }

    public void setIniX(int iniX) {
        this.iniX = iniX;
    }

    public int getIniY() {
        return iniY;
    }

    public void setIniY(int iniY) {
        this.iniY = iniY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHowManyTrash() {
        return howManyTrash;
    }

    public void setHowManyTrash(int howManyTrash) {
        this.howManyTrash = howManyTrash;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public int getGlass() {
        return glass;
    }

    public void setGlass(int glass) {
        this.glass = glass;
    }

    public int getElectronics() {
        return electronics;
    }

    public void setElectronics(int electronics) {
        this.electronics = electronics;
    }

    public int getOrganic() {
        return organic;
    }

    public void setOrganic(int organic) {
        this.organic = organic;
    }

    public int getPaper() {
        return paper;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    public int getPlastic() {
        return plastic;
    }

    public void setPlastic(int plastic) {
        this.plastic = plastic;
    }

    public int getAluminum() {
        return aluminum;
    }

    public void setAluminum(int aluminum) {
        this.aluminum = aluminum;
    }

    public int[] getNpcId() {
        return npcId;
    }

    public void setNpcId(int[] npcId) {
        this.npcId = npcId;
    }

    public int[] getNpcTrash() {
        return npcTrash;
    }

    public void setNpcTrash(int[] npcTrash) {
        this.npcTrash = npcTrash;
    }

    @Override
    public void tick() {
        x = iniX - screen.getX();
        y = iniY - screen.getY();
        howManyTrash = glass + electronics + organic + paper + plastic + aluminum;
    }

    @Override
    public void render(Graphics g) {
        if (unlocked) {
            g.drawImage(Assets.trashcanOwned, x, y, width, height, null);
        } else {
            g.drawImage(Assets.trashcanDefault, x, y, width, height, null);
        }
    }

    @Override
    public Rectangle getPerimetro() {
        return new Rectangle(x, y, width, height);
    }

}
