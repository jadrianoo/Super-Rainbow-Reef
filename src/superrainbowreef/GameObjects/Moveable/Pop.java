package superrainbowreef.GameObjects.Moveable;

import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.Resource;
import superrainbowreef.SRR;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pop extends GameObject{
    public int x,y;
    public double vx, vy, xSpeed, ySpeed;
    public int moveX, moveY;
    public boolean isDestroyed;
    public Rectangle hitBox;
    private BufferedImage img;
    private SRR ref;
    private Katch katch;

    public Pop(SRR ref, int x, int y, BufferedImage img){
        super(x,y,img);
        this.x = x;
        this.y = y;
        moveX = -1;
        moveY = -2;
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
        if(SRR.katch.getKatchHitBox().intersects(this.getPopHitBox())){
            moveY *= -1;
        }

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
        this.hitBox.setLocation(x,y);
    }
//    public void updateCollison(){
//        GameObject collisionObj = collisionObject();
//
//        if(collisionObj instanceof Katch)
//            katchCollision();
//        else
//            checkCollison(collisionObj);
//    }
//    public void checkCollison(GameObject object){
//        if(object != null){
//            int popLeft = (int)Math.round(vx);
//            int popRight = (int)Math.round(vx) + width;
//            int popTop = (int)Math.round(vy);
//            int popBottom = (int)Math.round(vy) + height;
//
//            int objLeft = object.get_x();
//            int objRight = object.get_x() + object.get_width();
//            int objTop = object.get_y();
//            int objBottom = object.get_y() + object.get_height();
//
//            int max = Integer.MAX_VALUE;
//            int[] intersections = new int[]{max,max,max,max};
//
//            if(popRight > objLeft && popLeft < objLeft)
//                intersections[0] = popRight - objLeft;
//            if(popLeft < objRight && popRight > objRight)
//                intersections[1] = objRight - popLeft;
//            if(popTop < objBottom && popBottom > objBottom)
//                intersections[2] = objBottom - popTop;
//            if(popBottom > objTop && popTop < objTop)
//                intersections[3] = popBottom - objTop;
//
//            int min = max;
//            int min_index = -1;
//
//            for(int i = 0; i < 4; i++){
//                if(intersections[i] < min){
//                    min = intersections[i];
//                    min_index = i;
//                }
//            }
//            if(min_index == 0)
//                moveX *= -1;
//            if(min_index == 1)
//                moveX *= -1;
//            if(min_index == 2)
//                moveY *= -1;
//            if(min_index == 3)
//                moveY *= -1;
//
//        }
//    }
//
//    public GameObject getClosestObj(GameObject obj1, GameObject obj2){
//        if(obj1 != null){
//            Rectangle newPop = new Rectangle(this.getRect());
//            newPop.setLocation((int)Math.round(vx + xSpeed), (int)Math.round(vy = ySpeed));
//
//            int xColl = (int)Math.round(vx) + (width/2);
//            int yColl = (int)Math.round(vy) + (height/2);
//            int xColl_obj1 = obj1.get_x() + (obj1.get_width()/2);
//            int yColl_obj1 = obj1.get_y() + (obj1.get_height()/2);
//            int xColl_obj2 = obj2.get_x() + (obj2.get_width()/2);
//            int yColl_obj2 = obj2.get_y() + (obj2.get_height()/2);
//
//            double distance1 = Math.sqrt(Math.pow((xColl - xColl_obj1),2) + Math.pow((yColl - yColl_obj1),2));
//            double distance2 = Math.sqrt(Math.pow((xColl - xColl_obj2),2) + Math.pow((yColl - yColl_obj2),2));
//
//            if(distance1 < distance2)
//                return obj1;
//            else
//                return obj2;
//
//        }
//        return obj2;
//    }
//
//    public GameObject collisionObject(){
//        Rectangle newPop = new Rectangle(this.getRect());
//        newPop.setLocation((int)Math.round(vx + xSpeed), (int)Math.round(vy = ySpeed));
//        GameObject closestObj = null;
//
//        for(CoralBlocks curr : this.ref.getCoralBlocks()){
//            if(newPop.intersects(curr.getRect())) {
//                closestObj = getClosestObj(closestObj, curr);
//                System.out.println("has collided");
//            }
//        }
//        return closestObj;
//    }
//
//    public void katchCollision(){
//        int popCenter = (int)Math.round(vx) + (width/2);
//        int katchX = this.katch.get_x();
//        int section = 5;
//        int bounds = section - 1;
//        int katchHitBox = this.katch.get_width();
//
//        int[] katchBounds = new int[bounds];
//
//        for(int i = 0; i < bounds; i++)
//            katchBounds[i] = katchX + (katchHitBox*(i + 1));
//
//        if(popCenter < katchBounds[0]){
//            if(moveX> 0){
//                moveX *= -1;
//            }else if(moveX == 0){
//                moveX *= -1;
//            }else
//                moveX-= -1;
//        }
////        if(popCenter < katchBounds[0]){
////
////        }
//        ySpeed *= -1;
//
//    }



}
