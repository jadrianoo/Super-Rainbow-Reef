package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameConstants;
import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.GameObjects.Unmoveable.Breakable.BigLegs;
import superrainbowreef.GameObjects.Unmoveable.Breakable.CoralBlocks;
import superrainbowreef.GameObjects.Unmoveable.Breakable.PowerUps;
import superrainbowreef.Resource;
import superrainbowreef.SRR;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pop extends GameObject{
    public int x,y,spawnY, spawnX;
    public int life = 0;
    public double vx, vy, xSpeed, ySpeed;
    public int moveX, moveY;
    public Rectangle hitBox;
    private BufferedImage img;
    private SRR ref;
    private Katch katch;


    public Pop( SRR ref,int x, int y, BufferedImage img){
        super(x,y,img);
        this.x = x;
        this.y = y;
        moveX = 2;
        moveY = 2;
        spawnY = 370;
        spawnX = 310;
        this.ref = ref;
        this.img = Resource.getResourceImage("pop");
        this.hitBox = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
    }
    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.img, x, y, null);
//        g2d.setColor(Color.CYAN);
//        g2d.drawRect(x,y,this.img.getWidth(),this.img.getHeight());
    }
    public Rectangle getPopHitBox(){
        return hitBox.getBounds();
    }

    @Override
    public void update() {
        updateMove();
        checkBounds();
    }

    public void updateMove(){

        this.x += moveX;

        if(this.getPopHitBox().intersects(SRR.katch.getKatchHitBox())){
            moveY *= -1;
        }
        for(CoralBlocks curr : this.ref.getCoralBlocks()){
            if(this.getPopHitBox().intersects(curr.getHitBox()) && !curr.isDestroyed){
                moveY *= -1;
                curr.isDestroyed = true;
            }
        }
        for(PowerUps curr : this.ref.getPowerUps()){
            if(this.getPopHitBox().intersects(curr.getHitBox()) && !curr.isDestoyed){
                life++;
                moveY *= -1;
                curr.isDestoyed = true;
                System.out.println(life);
            }
        }
        for(BigLegs curr : this.ref.getBigLegs()){
            if(this.getPopHitBox().intersects(curr.getHitBox()) && !curr.isDestroyed){
                moveY *= -1;
                curr.isDestroyed = true;
            }
        }
        this.y += moveY;
    }
    public void checkBounds(){

        if(this.x > (GameConstants.GAME_SCREEN_WIDTH - 30) || this.x < 0)
            moveX *= -1;
        if(this.y < 0)
            moveY *= -1;
        if(this.y > (GameConstants.GAME_SCREEN_HEIGHT - 30)) {
            respawn();
        }
        this.hitBox.setLocation(x,y);

//        if(this.x < 0)
//            moveX = -moveX;
//        if(this.y < 0)
//            moveY = -moveY;
//        if(this.x > 600)
//            moveX = -moveX;


    }
    public void respawn(){
        SRR.katch.respawn();
        this.x = spawnX;
        this.y = spawnY;
    }

    public int getLife(){
        return life;
    }

}
