package superrainbowreef.GameObjects;

import superrainbowreef.GameObjects.Unmoveable.Unbreakable.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public abstract class GameObject extends Rectangle{
    public int x,y,height,width,score;
    Rectangle rect;
    Rectangle hitBox;
    BufferedImage img;
    public ArrayList<Wall> walls;

    protected GameObject(int x, int y, BufferedImage img){
        this.x = x;
        this.y = y;
        this.img = img;
        this.hitBox = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
    }


    public abstract void drawImage(Graphics g);

    public abstract void update();

    public void setSize(int currWidth, int currHeight){
        this.width = currWidth;
        this.height = currHeight;
    }
    public void setImage(BufferedImage img){
        this.img = img;
    }

    public void setLocation(int currX, int currY){
        this.x = currX;
        this.y = currY;
        this.rect = new Rectangle(currX, currY);
    }

    //get
    public Rectangle getRect(){
        return new Rectangle(x, y, width, height);
    }
    public Rectangle getHitBox(){
        return hitBox.getBounds();
    }
    public int getRecX(){
        return hitBox.x;
    }
    public int getRecY(){ return hitBox.y;}
    public int getRecHeight(){return hitBox.height;}
    public int getRecWidth(){
        return hitBox.width;
    }
    public ArrayList<Wall> getWalls(){
        return this.walls;
    }



}
