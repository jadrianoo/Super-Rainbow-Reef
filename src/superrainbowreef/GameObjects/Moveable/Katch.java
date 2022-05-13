package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.GameObjects.Unmoveable.Unbreakable.Wall;
import superrainbowreef.Resource;
import superrainbowreef.SSR;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Katch extends GameObject{
    public int x,y;
    private BufferedImage img;
    private SSR ref;

    public Katch(int x, int y, BufferedImage img){
        super(x,y,img);
        this.x = x;
        this.y = y;
        this.img = Resource.getResourceImage("katch");
    }

    public Rectangle getKatchHitBox(){
        return new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }
    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.img, x, y, null);
    }

    @Override
    public void update() {

    }

    public Wall getWall(){
        Rectangle katch_hitBox = new Rectangle(x, y, width, height);
        for(Wall curr : ref.getWalls()){
            if(katch_hitBox.intersects(curr.getRect()))
                return curr;
        }
        return null;
    }
}
