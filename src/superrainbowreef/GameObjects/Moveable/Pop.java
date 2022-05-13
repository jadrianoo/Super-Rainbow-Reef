package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameConstants;
import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.GameObjects.Unmoveable.Breakable.BigLegs;
import superrainbowreef.GameObjects.Unmoveable.Breakable.CoralBlocks;
import superrainbowreef.Resource;
import superrainbowreef.SRR;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pop extends GameObject{
    public int x,y;
    public double vx, vy, xSpeed, ySpeed;
    public int moveX, moveY;
    public Rectangle hitBox;
    private BufferedImage img;
    private SRR ref;
    private Katch katch;

    ArrayList<CoralBlocks> coralBlocks;

    public Pop(SRR ref, int x, int y, BufferedImage img){
        super(x,y,img);
        this.x = x;
        this.y = y;
        moveX = 2;
        moveY = 2;
        this.ref = ref;
        this.img = Resource.getResourceImage("pop");
        this.hitBox = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
    }
    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.img, x, y, null);
        g2d.setColor(Color.CYAN);
        g2d.drawRect(x,y,this.img.getWidth(),this.img.getHeight());
    }
    public Rectangle getPopHitBox(){
        return hitBox.getBounds();
    }

    @Override
    public void update() {
        updateMove();
        collision();
//        updateCollison();
    }

    public void updateMove(){

        if(this.getPopHitBox().intersects(SRR.katch.getKatchHitBox())){
            moveY *= -1;
        }
        for(CoralBlocks curr : this.ref.getCoralBlocks()){
            if(this.getPopHitBox().intersects(curr.getHitBox()) && !curr.isDestroyed){
                moveY *= -1;
                curr.isDestroyed = true;
            }
        }
        for(BigLegs curr : this.ref.getBigLegs()){
            if(this.getPopHitBox().intersects(curr.getHitBox()) && !curr.isDestroyed){
                moveY *= -1;
                curr.isDestroyed = true;
            }
        }
        CoralBlocks curr;



    }
    public void collision(){
        this.x += moveX;

        if(this.x > (GameConstants.GAME_SCREEN_WIDTH - 30) || this.x < 0)
            moveX *= -1;
        if(this.y > (GameConstants.GAME_SCREEN_HEIGHT - 30) || this.y < 0)
            moveY *= -1;

        this.y += moveY;
        this.hitBox.setLocation(x,y);

//        if(this.x < 0)
//            moveX = -moveX;
//        if(this.y < 0)
//            moveY = -moveY;
//        if(this.x > 600)
//            moveX = -moveX;


    }

}
