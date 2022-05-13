package superrainbowreef;

import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.GameObjects.Moveable.Katch;
import superrainbowreef.GameObjects.Moveable.Pop;
import superrainbowreef.GameObjects.Unmoveable.Breakable.CoralBlocks;

import java.util.ArrayList;

public class Collisions {
    private Katch katch;
    private Pop pop;
    private ArrayList<GameObject> gameObjects;

    public Collisions(Pop pop, ArrayList<GameObject> gameObjects){
        this.pop = pop;
        this.gameObjects = gameObjects;
    }

    public void coralBlockCollision(Pop source, ArrayList<GameObject> walls){
        source.getGetHitBox();
        for(int i = 0; i < walls.size(); i++){
            if(source.getGetHitBox().getBounds().intersects(walls.get(i).getHitBox().getBounds())){
                source.moveY *= -1;
            }
        }

    }
}
