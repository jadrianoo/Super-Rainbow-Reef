package superrainbowreef.GameObjects.Unmoveable.Breakable;

import superrainbowreef.GameObjects.Unmoveable.Unmoveable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CoralBlocks extends Unmoveable {
    int x,y;
    BufferedImage img;
    Rectangle hitBox;

    public CoralBlocks(int x, int y, BufferedImage img) {
        super(x,y,img);
        this.x = x;
        this.y = y;
        this.img = img;
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }
    public Rectangle getHitBox(){
        return hitBox.getBounds();
    }
    public void drawImage(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.img, x,y,null);
    }

    @Override
    public void update() {

    }
}
