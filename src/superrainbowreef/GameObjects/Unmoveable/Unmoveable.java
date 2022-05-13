package superrainbowreef.GameObjects.Unmoveable;

import superrainbowreef.GameObjects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Unmoveable extends GameObject {

    protected Unmoveable(int x, int y, BufferedImage img) {
        super(x, y, img);
    }

    public abstract void drawImage(Graphics g);

    public abstract void update();
}
