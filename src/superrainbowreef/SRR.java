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

    static SoundPlayer sound = new SoundPlayer();
    Thread thread;

    public SRR(Launcher lf) {
        this.lf = lf;
    }

    @Override
    public void run() {
        try {
            this.gameInitialize();
            this.resetGame();
            // Play background music
            playSound(0);
            while (true) {
                this.tick++;
                this.gameObjects.forEach(gameObject -> gameObject.update());
                this.coralBlocks.forEach(coralBlocks -> coralBlocks.update());
                this.repaint();   // redraw game
                Thread.sleep(1000 / 288); //sleep for a few milliseconds

                /*
                 * simulate an end game event
                 * we will do this with by ending the game when drawn 2000 frames have been drawn
                 */
                if(this.pop.getLife() <= 0 || this.pop.getBigLegs() <= 0){
                    this.lf.setFrame("end");
                    stopSound();
                    return;
                }
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    public void resetGame(){
        this.tick = 0;
    }

    public void gameInitialize() {
        gameObjects = new ArrayList<>();
        walls = new ArrayList<>();
        solidBlocks = new ArrayList<>();
        coralBlocks = new ArrayList<>();
        powerUps = new ArrayList<>();
        bigLegs = new ArrayList<>();

        this.world = new BufferedImage(GameConstants.GAME_SCREEN_WIDTH,
                GameConstants.GAME_SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        katch = new Katch(GameConstants.GAME_SCREEN_WIDTH / 2 - Resource.getResourceImage("katch").getWidth() / 2,
                GameConstants.GAME_SCREEN_HEIGHT - Resource.getResourceImage("katch").getHeight() - Resource.getResourceImage("pop").getHeight(),
                Resource.getResourceImage("katch"));

        pop = new Pop(this,310, 370, Resource.getResourceImage("pop"));

        this.gameObjects.add(katch);
        this.gameObjects.add(pop);

        try {


            InputStreamReader isr = new InputStreamReader(SRR.class.getClassLoader().getResourceAsStream("maps/map1"));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            // Add blocks
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
                            this.coralBlocks.add(new CoralBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("redBlock"), 10));
                            break;
                        case "3":
                            this.coralBlocks.add(new CoralBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("blueBlock"), 10));
                            break;
                        case "5":
                            this.coralBlocks.add(new CoralBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("yellowBlock"), 10));
                            break;
                        case "6":
                            this.coralBlocks.add(new CoralBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("greenBlock"),10));
                            break;
                        case "7":
                            this.coralBlocks.add(new CoralBlocks(curCol * 20, curRow * 20, Resource.getResourceImage("purpleBlock"),10));
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

        this.lf.getJf().addKeyListener(kc);
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        Graphics2D buffer = world.createGraphics();
        BufferedImage backgroundImg = Resource.getResourceImage("background");
        buffer.setColor(Color.black);
        buffer.fillRect(0, 0, GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT);
        buffer.drawImage(backgroundImg,0,0,null);

        // Map Objects
        this.gameObjects.forEach(gameObject -> gameObject.drawImage(buffer));
        this.walls.forEach(Wall -> Wall.drawImage(buffer));
        this.solidBlocks.forEach(SolidBlocks -> SolidBlocks.drawImage(buffer));
        this.coralBlocks.forEach(CoralBlocks -> CoralBlocks.drawImage(buffer));
        this.powerUps.forEach(PowerUps -> PowerUps.drawImage(buffer));
        this.bigLegs.forEach(BigLegs -> BigLegs.drawImage(buffer));

        // Draw life
        buffer.setColor(Color.yellow);
        buffer.setFont(new Font("Oswald", Font.BOLD, 20));
        buffer.drawString("Lives: " + pop.getLife(), 50, 390);

        // Draw Score
        buffer.setColor(Color.yellow);
        buffer.setFont(new Font("Oswald", Font.BOLD, 20));
        buffer.drawString("Score: " + pop.getScore(), 50, 360);

        // World
        g2.drawImage(world, 0, 0, null);

    }

    // Get Map Objects
    public ArrayList<Wall> getWalls(){
        return this.walls;
    }
    public ArrayList<SolidBlocks> getSolidBlocks() {return this.solidBlocks;}
    public ArrayList<CoralBlocks> getCoralBlocks(){
        return this.coralBlocks;
    }
    public ArrayList<PowerUps> getPowerUps(){ return this.powerUps;}
    public ArrayList<BigLegs> getBigLegs(){return this.bigLegs;}

    // Play Sounds
    public static void playSound(int x){
        sound.setFile(x);
        sound.playClip();
        sound.loopClip();
    }
    public void stopSound(){
        sound.stopClip();
    }

    public static void playEffects(int x){
        sound.setFile(x);
        sound.playClip();
    }

}
