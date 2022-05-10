package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Katch extends GameObject {
    public int x,y;
    private BufferedImage img;

    public Katch(int x, int y, BufferedImage img){
        super();
        this.x = x;
        this.y = y;
        this.img = Resource.getResourceImage("katch");
    }
    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.img, x, y, null);
    }

    @Override
    public void update() {

    }
}
