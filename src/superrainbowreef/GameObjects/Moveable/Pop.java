package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pop extends GameObject{
    public int x,y;
    public int moveX, moveY;
    private BufferedImage img;

    public Pop(int x, int y, BufferedImage img){
        super();
        this.x = x;
        this.y = y;
        moveX = 3;
        moveY = 3;
        this.img = Resource.getResourceImage("pop");
    }
    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.img, x, y, null);
    }

    @Override
    public void update() {
        updateMove();
    }

    public void updateMove(){
        this.x += moveX;
        if(this.x > (getWidth()-25) && this.x < 0)
            moveX *= -1;
    }


}
