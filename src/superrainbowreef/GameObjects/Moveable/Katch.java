package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameObjects.Unmoveable.Unbreakable.Wall;
import superrainbowreef.Resource;
import superrainbowreef.SRR;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Katch extends Moveable{
    public int x,y;
    private BufferedImage img;
    private SRR ref;
    private Rectangle hitBox;

    private boolean RightPressed;
    private boolean LeftPressed;

    public Katch(int x, int y, BufferedImage img){
        super(x,y,img);
        this.x = x;
        this.y = y;
        this.img = Resource.getResourceImage("katch");
        this.hitBox = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
    }

    public Rectangle getKatchHitBox(){
        return hitBox.getBounds();
    }
    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.img, x, y, null);
        g2d.setColor(Color.CYAN);
        g2d.drawRect(x,y,this.img.getWidth(),this.img.getHeight());
    }

    @Override
    public void update() {
        updateMove();
    }

    public Wall getWall(){
        Rectangle katch_hitBox = new Rectangle(x, y, width, height);
        for(Wall curr : ref.getWalls()){
            if(katch_hitBox.intersects(curr.getRect()))
                return curr;
        }
        return null;
    }

    public void updateMove(){
        if (this.LeftPressed) {
            this.moveLeft();
        }
        if (this.RightPressed) {
            this.moveRight();
        }
    }

    public void moveRight(){
        this.x += 4;
        this.hitBox.setLocation(x,y);
    }
    public void moveLeft(){
        this.x -= 4;
        this.hitBox.setLocation(x,y);
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }
}
