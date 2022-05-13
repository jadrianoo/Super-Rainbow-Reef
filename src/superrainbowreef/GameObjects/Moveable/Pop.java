package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.GameObjects.Unmoveable.Breakable.CoralBlocks;
import superrainbowreef.GameObjects.Unmoveable.Unbreakable.Wall;
import superrainbowreef.Resource;
import superrainbowreef.SSR;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pop extends GameObject{
    public int x,y;
    public double vx, vy, xSpeed, ySpeed;
    public int moveX, moveY;
    public boolean isDestroyed;
    public Rectangle hitBox;
    private BufferedImage img;
    private SSR ref;

    public Pop(SSR ref, int x, int y, BufferedImage img){
        super(x,y,img);
        this.x = x;
        this.y = y;
        this.xSpeed = 0;
        this.ySpeed = 0;
        moveX = -1;
        moveY = -2;
        this.ref = ref;
        this.img = Resource.getResourceImage("pop");
        this.hitBox = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
    }
    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.img, x, y, null);
    }
    public Rectangle getGetHitBox(){
        return hitBox.getBounds();
    }

    @Override
    public void update() {
//        updateMove();
        updateCollison();
        collision();
    }

    public void updateMove(){
        this.x += moveX;
        if(this.x > (getWidth()-25) && this.x < 0)
            moveX *= -1;

    }
    public void collision(){
        this.x += moveX;
        this.y += moveY;
        if(this.x < 0)
            moveX = -moveX;
        if(this.y < 0)
            moveY = -moveY;
        if(this.x > 615)
            moveX = -moveX;
    }
    public void updateCollison(){
        GameObject collisionObj = collisionObject();
        checkCollison(collisionObj);
    }
    public void checkCollison(GameObject object){
        if(object != null){
            int popLeft = (int)Math.round(vx);
            int popRight = (int)Math.round(vx) + width;
            int popTop = (int)Math.round(vy);
            int popBottom = (int)Math.round(vy) + height;

            int objLeft = object.get_x();
            int objRight = object.get_x() + object.get_width();
            int objTop = object.get_y();
            int objBottom = object.get_y() + object.get_height();

            int max = Integer.MAX_VALUE;
            int[] intersections = new int[]{max,max,max,max};

            if(popRight > objLeft && popLeft < objLeft)
                intersections[0] = popRight - objLeft;
            if(popLeft < objRight && popRight > objRight)
                intersections[1] = objRight - popLeft;
            if(popTop < objBottom && popBottom > objBottom)
                intersections[2] = objBottom - popTop;
            if(popBottom > objTop && popTop < objTop)
                intersections[3] = popBottom - objTop;

            int min = max;
            int min_index = -1;

            for(int i = 0; i < 4; i++){
                if(intersections[i] < min){
                    min = intersections[i];
                    min_index = i;
                }
            }
            if(min_index == 0)
                xSpeed *= -1;
            if(min_index == 1)
                xSpeed *= -1;
            if(min_index == 2)
                ySpeed *= -1;
            if(min_index == 3)
                ySpeed *= -1;

        }
    }

    public GameObject getClosestObj(GameObject obj1, GameObject obj2){
        if(obj1 != null){
            Rectangle newPop = new Rectangle(this.getRect());
            newPop.setLocation((int)Math.round(vx + xSpeed), (int)Math.round(vy = ySpeed));

            int xColl = (int)Math.round(vx) + (width/2);
            int yColl = (int)Math.round(vy) + (height/2);
            int xColl_obj1 = obj1.get_x() + (obj1.get_width()/2);
            int yColl_obj1 = obj1.get_y() + (obj1.get_height()/2);
            int xColl_obj2 = obj2.get_x() + (obj2.get_width()/2);
            int yColl_obj2 = obj2.get_y() + (obj2.get_height()/2);

            double distance1 = Math.sqrt(Math.pow((xColl - xColl_obj1),2) + Math.pow((yColl - yColl_obj1),2));
            double distance2 = Math.sqrt(Math.pow((xColl - xColl_obj2),2) + Math.pow((yColl - yColl_obj2),2));

            if(distance1 < distance2)
                return obj1;
            else
                return obj2;

        }
        return obj2;
    }

    public GameObject collisionObject(){
        Rectangle newPop = new Rectangle(this.getRect());
        newPop.setLocation((int)Math.round(vx + xSpeed), (int)Math.round(vy = ySpeed));
        GameObject closestObj = null;

        for(CoralBlocks curr : this.ref.getCoralBlocks()){
            if(newPop.intersects(curr.getRect())) {
                closestObj = getClosestObj(closestObj, curr);
                System.out.println("has collided");
            }
        }
        return closestObj;
    }



}
