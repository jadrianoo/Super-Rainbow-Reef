package superrainbowreef;

import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.GameObjects.Moveable.Katch;
import superrainbowreef.GameObjects.Moveable.Pop;
import superrainbowreef.GameObjects.Unmoveable.Breakable.BigLegs;
import superrainbowreef.GameObjects.Unmoveable.Breakable.CoralBlocks;
import superrainbowreef.GameObjects.Unmoveable.Breakable.PowerUps;
import superrainbowreef.GameObjects.Unmoveable.Unbreakable.SolidBlocks;
import superrainbowreef.GameObjects.Unmoveable.Unbreakable.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class SRR extends JPanel implements Runnable {
    private BufferedImage world;
    private Launcher lf;
    public static Katch katch;
    private static Pop pop;
    static long tick = 0;
    ArrayList<GameObject> gameObjects;
    ArrayList<Wall> walls;
    ArrayList<SolidBlocks> solidBlocks;
    ArrayList<CoralBlocks> coralBlocks;
    ArrayList<PowerUps> powerUps;
    ArrayList<BigLegs> bigLegs;

    public SRR(Launcher lf) {
        this.lf = lf;
    }

    @Override
    public void run() {
        try {
            this.gameInitialize();
            while (true) {
                this.tick++;
//                this.pop.update();// update pop
                this.gameObjects.forEach(gameObject -> gameObject.update());

//                cs.coralBlockCollision(pop, coralBlocks);

                this.repaint();   // redraw game
                Thread.sleep(1000 / 144); //sleep for a few milliseconds

                /*
                 * simulate an end game event
                 * we will do this with by ending the game when drawn 2000 frames have been drawn
                 */
//                if(this.tick > 2000){
//                    this.lf.setFrame("end");
//                    return;
//                }
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    public void gameInitialize() {
        this.world = new BufferedImage(GameConstants.GAME_SCREEN_WIDTH,
                GameConstants.GAME_SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        gameObjects = new ArrayList<>();
        walls = new ArrayList<>();
        solidBlocks = new ArrayList<>();
        coralBlocks = new ArrayList<>();
        powerUps = new ArrayList<>();
        bigLegs = new ArrayList<>();

        katch = new Katch(275, 400, Resource.getResourceImage("katch"));
        pop = new Pop(this,300, 330, Resource.getResourceImage("pop"));

        this.gameObjects.add(katch);
        this.gameObjects.add(pop);

        try {
            InputStreamReader isr = new InputStreamReader(SRR.class.getClassLoader().getResourceAsStream("maps/map1"));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for (int curRow = 0; curRow < numRows; curRow++) {
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for (int curCol = 0; curCol < numCols; curCol++) {
                    switch (mapInfo[curCol]) {
                        case "9":
                            this.walls.add(new Wall(curCol * 20, curRow * 20, Resource.getResourceImage("wall")));
                            break;
                        case "1":
                            this.solidBlocks.add(new SolidBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("solidW")));
                            break;
                        case "2":
                            this.coralBlocks.add(new CoralBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("redBlock")));
                            break;
                        case "3":
                            this.coralBlocks.add(new CoralBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("blueBlock")));
                            break;
                        case "5":
                            this.coralBlocks.add(new CoralBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("yellowBlock")));
                            break;
                        case "6":
                            this.coralBlocks.add(new CoralBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("greenBlock")));
                            break;
                        case "7":
                            this.coralBlocks.add(new CoralBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("purpleBlock")));
                            break;
                        case "8":
                            this.powerUps.add(new PowerUps(curCol * 20, curRow * 20, Resource.getResourceImage("lifeBlock")));
                            break;
                        case "10":
                            this.bigLegs.add(new BigLegs(curCol * 20, curRow * 19, Resource.getResourceImage("bigLegSmall")));
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        KatchControls kc = new KatchControls(katch, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);


        this.setBackground(Color.BLACK);
        this.lf.getJf().addKeyListener(kc);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        Graphics2D buffer = world.createGraphics();
        buffer.setColor(Color.black);
        buffer.fillRect(0, 0, GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT);
        this.gameObjects.forEach(gameObject -> gameObject.drawImage(buffer));
        this.walls.forEach(Wall -> Wall.drawImage(buffer));
        this.solidBlocks.forEach(SolidBlocks -> SolidBlocks.drawImage(buffer));
        this.coralBlocks.forEach(CoralBlocks -> CoralBlocks.drawImage(buffer));
        this.powerUps.forEach(PowerUps -> PowerUps.drawImage(buffer));
        this.bigLegs.forEach(BigLegs -> BigLegs.drawImage(buffer));


        g2.drawImage(world, 0, 0, null);
    }
//    public void updateMove(){
//        if(SSR.katch.getKatchHitBox().intersects(SSR.pop.getPopHitBox())){
//            pop.moveY *= -1;
//        }
//    }
    public ArrayList<Wall> getWalls(){
        return this.walls;
    }
    public ArrayList<CoralBlocks> getCoralBlocks(){
        return this.coralBlocks;
    }
}
