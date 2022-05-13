package superrainbowreef.GameObjects.Unmoveable.Breakable;

import superrainbowreef.GameObjects.Unmoveable.Unmoveable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BigLegs extends Unmoveable {
    int x,y;
    public boolean isDestroyed;
    BufferedImage img;

    public BigLegs(int x, int y, BufferedImage img) {
        super(x,y,img);
        this.x = x;
        this.y = y;
        this.img = img;
    }
    public void drawImage(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(!isDestroyed)
            g2.drawImage(this.img, x,y,null);
    }

    @Override
    public void update() {

    }
}
