package superrainbowreef.GameObjects;

import superrainbowreef.GameObjects.Unmoveable.Unbreakable.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public abstract class GameObject extends Rectangle{
    public int x,y,height,width;
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

    public void setX(int currX){
        this.x = currX;
        this.rect.setLocation(currX, this.y);
    }
    public void setY(int currY){
        this.y = currY;
        this.rect.setLocation(this.x, currY);
    }
    public void setWidth(int currWidth){
        this.width = currWidth;
        rect.setSize(currWidth,this.height);
    }
    public void setHeight(int currHeight){
        this.height = currHeight;
        rect.setSize(this.width, currHeight);
    }
    public void setSize(int currWidth, int currHeight){
        this.width = currWidth;
        this.height = currHeight;
    }
    public void setImage(BufferedImage img){
        this.img = img;
    }
    public void setImage(BufferedImage img, ImageObserver observer){
        this.img = img;
        this.x = 0;
        this.y = 0;
        try{
            this.width = img.getWidth(observer);
            this.height = img.getHeight(observer);
        }catch(NullPointerException e){
            this.width = 0;
            this.height = 0;
        }
    }
    public void setLocation(int currX, int currY){
        this.x = currX;
        this.y = currY;
        this.rect = new Rectangle(currX, currY);
    }

    //get
    public int get_x(){
        return this.x;
    }
    public int get_y(){
        return this.y;
    }
//    public Point getLocation(){
//        return new Point(this.x, this.y);
//    }
    public int get_width(){
        return this.width;
    }
    public int get_height(){
        return this.height;
    }
    public Image getImg(){
        return this.img;
    }
    public Rectangle getRect(){
        return new Rectangle(x, y, width, height);
    }
    public Rectangle getHitBox(){
        return hitBox.getBounds();
    }
    public ArrayList<Wall> getWalls(){
        return this.walls;
    }



}
