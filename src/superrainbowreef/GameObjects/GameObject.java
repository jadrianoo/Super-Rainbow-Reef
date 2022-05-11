package superrainbowreef.GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class GameObject extends Rectangle{
    int x,y;
    Rectangle rect;
    BufferedImage img;

    public abstract void drawImage(Graphics g);

    public abstract void update();


}
