package superrainbowreef.GameObjects.Unmoveable.Breakable;

import superrainbowreef.GameObjects.Unmoveable.Unmoveable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PowerUps extends Unmoveable {
    int x,y;
    BufferedImage img;

    public PowerUps(int x, int y, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    public void drawImage(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.img, x,y,null);
    }

    @Override
    public void update() {

    }
}
