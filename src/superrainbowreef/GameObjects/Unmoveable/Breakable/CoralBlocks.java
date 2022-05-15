package superrainbowreef.GameObjects.Unmoveable.Breakable;

import superrainbowreef.GameObjects.Unmoveable.Unmoveable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CoralBlocks extends Unmoveable {
    int x,y;
    public boolean isDestroyed;
    BufferedImage img;
    Rectangle hitBox;
    int score;

    public CoralBlocks(int x, int y, BufferedImage img, int pointValue) {
        super(x,y,img);
        this.x = x;
        this.y = y;
        this.img = img;
        this.hitBox = new Rectangle(x, y, this.img.getWidth()/2, this.img.getHeight()/2);
        this.score = pointValue;
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
