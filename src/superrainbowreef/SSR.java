package superrainbowreef;

import superrainbowreef.GameObjects.GameObject;
import superrainbowreef.GameObjects.Moveable.Katch;
import superrainbowreef.GameObjects.Moveable.Pop;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class SSR extends JPanel implements Runnable {
    private BufferedImage world;
    private Launcher lf;
    private Katch katch;
    private Pop pop;
    static long tick = 0;
    ArrayList<GameObject> gameObjects;

    public SSR(Launcher lf){this.lf = lf;}

    @Override
    public void run() {
        try {
            this.gameInitialize();
            while (true) {
                this.tick++;
                this.pop.update();// update tank
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

    public void gameInitialize(){
        this.world = new BufferedImage(GameConstants.GAME_SCREEN_WIDTH,
                GameConstants.GAME_SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_RGB);


        BufferedImage katchimg = null;
        BufferedImage popimg = null;
        try{
            katchimg = read(Objects.requireNonNull(SSR.class.getClassLoader().getResource("katch.gif")));
            popimg = read(Objects.requireNonNull(SSR.class.getClassLoader().getResource("pop.gif")));
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();

        }

//        Katch katch = new Katch(175, 180, Resource.getResourceImage("katch"));

//        this.gameObjects.add(katch);
        katch = new Katch(375,400, katchimg);
        pop = new Pop(400, 330, popimg);

        this.setBackground(Color.BLACK);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
        Graphics2D buffer = world.createGraphics();
        buffer.setColor(Color.black);
        buffer.fillRect(0,0, GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT);

//        this.gameObjects.forEach(gameObject -> gameObject.drawImage(g));
        this.katch.drawImage(buffer);
        this.pop.drawImage(buffer);

        g2.drawImage(world, 0,0,null);
    }
}
