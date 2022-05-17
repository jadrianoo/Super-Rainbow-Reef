package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameObjects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Movable extends GameObject {
    double speed;

    public Movable(int x, int y, BufferedImage img){
        super(x,y,img);
    }

    @Override
    public void drawImage(Graphics g) {

    }

    @Override
    public void update() {

    }
}
