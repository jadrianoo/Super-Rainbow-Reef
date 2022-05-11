package superrainbowreef.GameObjects.Unmoveable;

import superrainbowreef.GameObjects.GameObject;

import java.awt.*;

public abstract class Unmoveable extends GameObject {
    public abstract void drawImage(Graphics g);

    public abstract void update();
}
