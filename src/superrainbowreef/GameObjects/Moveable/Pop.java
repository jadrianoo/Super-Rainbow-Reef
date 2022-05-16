package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameConstants;
import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.GameObjects.Unmoveable.Breakable.BigLegs;
import superrainbowreef.GameObjects.Unmoveable.Breakable.CoralBlocks;
import superrainbowreef.GameObjects.Unmoveable.Breakable.PowerUps;
import superrainbowreef.GameObjects.Unmoveable.Unbreakable.SolidBlocks;
import superrainbowreef.GameObjects.Unmoveable.Unbreakable.Wall;
import superrainbowreef.Resource;
import superrainbowreef.SRR;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Pop extends GameObject{

    public int x,y,spawnY, spawnX;
    public int score = 0;
    public int life = 1;
    public int bigLegs = 4;
    public int moveX, moveY;

    boolean isMovingUp;

    public Rectangle hitBox;
    private BufferedImage img;
    private SRR ref;

    public Pop( SRR ref,int x, int y, BufferedImage img){
        super(x,y,img);
        this.x = x;
        this.y = y;
        moveX = 1;
        moveY = 1;
        spawnY = 370;
        spawnX = 310;
        this.ref = ref;
        this.img = Resource.getResourceImage("pop");
        this.hitBox = new Rectangle(x, y, this.img.getWidth() ,this.img.getHeight());
    }
    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.img, x, y, null);
    }
    public Rectangle getPopHitBox(){
        return hitBox.getBounds();
    }

    @Override
    public void update() {
        checkBounds();
        updateMove();

    }

    public void updateMove(){

        // Check coral blocks
        for(CoralBlocks curr : this.ref.getCoralBlocks()){
            // Check sides of box
            if(this.getPopHitBox().intersects(curr.getHitBox()) && !curr.isDestroyed){
                if(this.x > curr.getRecX() && this.x < curr.getRecX() + curr.getRecWidth()
                        && this.y > curr.getRecY() && this.y < curr.getRecY() + curr.getRecHeight() && !curr.isDestroyed){
                    score += 10;
                    moveY = -moveY;
                    curr.isDestroyed = true;
                    updateGameObject(curr);
                }
            }
        }
        // check solid blocks
        for(SolidBlocks curr : this.ref.getSolidBlocks()){
            // check sides of solid blocks
            if(this.getPopHitBox().intersects(curr.getHitBox())){
                if(this.x > curr.getRecX() && this.x < curr.getRecX() + curr.getRecWidth()
                        && this.y > curr.getRecY() && this.y < curr.getRecY() + curr.getRecHeight()){
                    if(isMovingUp){
                        this.y += moveY;
                        moveY = -moveY;
                    }if(!isMovingUp){
                        this.y -= moveY;
                        moveY = -moveY;
                    }
                }else {
                    this.x -= moveX;
                    moveX = -moveX;
                }
            }
        }
        // Check power ups
        for(PowerUps curr : this.ref.getPowerUps()){
            if(this.getPopHitBox().intersects(curr.getHitBox()) && !curr.isDestoyed){
                life++;
                moveY = -moveY;
                curr.isDestoyed = true;
                updateGameObject(curr);
            }
        }
        // check big legs
        for(BigLegs curr : this.ref.getBigLegs()){
            if(this.getPopHitBox().intersects(curr.getHitBox()) && !curr.isDestroyed){
                bigLegs--;
                moveY = -moveY;
                curr.isDestroyed = true;
                updateGameObject(curr);
            }
        }
    }
    private void updateGameObject(GameObject gameobject) {
        try {
            if (gameobject instanceof CoralBlocks) {
                SRR.playEffects(1);
            }
            if (gameobject instanceof PowerUps) {
                SRR.playEffects(1);
            }
            if (gameobject instanceof BigLegs) {
                SRR.playEffects(2);
            }

        } catch (ConcurrentModificationException e) {
            // indicate that concurrent modification was attempted
        }
    }

    public void respawn(){
        SRR.katch.respawn();
        this.x = spawnX;
        this.y = spawnY;
    }

    public void checkBounds(){

        this.x += moveX;

        if(this.x > (GameConstants.GAME_SCREEN_WIDTH - 30) || this.x < 0)
            moveX = -moveX;
        if(this.y < 0 || this.getPopHitBox().intersects(SRR.katch.getKatchHitBox())) {
            moveY = -moveY;
            isMovingUp = true;
        }
        if(this.y > (GameConstants.GAME_SCREEN_HEIGHT - 30)) {
            life--;
            respawn();
        }
        isMovingUp = false;

        this.y += moveY;

        this.hitBox.setLocation(x,y);
    }

    // Getters
    public int getLife(){
        return life;
    }
    public int getScore(){ return score;}
    public int getBigLegs(){ return bigLegs;}

}
