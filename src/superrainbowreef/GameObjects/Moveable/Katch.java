package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameConstants;
import superrainbowreef.GameObjects.Unmoveable.Unbreakable.Wall;
import superrainbowreef.Resource;
import superrainbowreef.SRR;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Katch extends Moveable{
    public int x,y, spawnX, spawnY;
    private BufferedImage img;
    private SRR ref;
    private Rectangle hitBox;

    private boolean RightPressed;
    private boolean LeftPressed;

    public Katch(int x, int y, BufferedImage img){
        super(x,y,img);
        this.x = x;
        this.y = y;
        this.img = Resource.getResourceImage("katch");
        this.hitBox = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
        spawnX = GameConstants.GAME_SCREEN_WIDTH / 2 - Resource.getResourceImage("katch").getWidth() / 2;
        spawnY = GameConstants.GAME_SCREEN_HEIGHT - Resource.getResourceImage("katch").getHeight() - Resource.getResourceImage("pop").getHeight();
    }

    public Rectangle getKatchHitBox(){
        return hitBox.getBounds();
    }

    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.img, x, y, null);
    }

    @Override
    public void update() {
        checkBounds();
        updateMove();
    }

    public void respawn(){
        this.x = spawnX;
        this.y = spawnY;
    }
    public void checkBounds(){
        if (x < 30) {
            x = 30;
        }
        if (x >= GameConstants.GAME_SCREEN_WIDTH - 90) {
            x = GameConstants.GAME_SCREEN_WIDTH - 90;
        }
    }

    public void updateMove(){
        if (this.LeftPressed) {
            this.moveLeft();
        }
        if (this.RightPressed) {
            this.moveRight();
        }
    }

    public void moveRight(){
        this.x += 2;
        this.hitBox.setLocation(x,y);
    }
    public void moveLeft(){
        this.x -= 2;
        this.hitBox.setLocation(x,y);
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }
}
