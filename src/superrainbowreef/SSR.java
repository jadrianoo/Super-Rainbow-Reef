package superrainbowreef;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SSR extends JPanel implements Runnable {
    private BufferedImage world;
    private Launcher lf;

    public SSR(Launcher lf){this.lf = lf;}

    @Override
    public void run() {

    }

    public void gameInitialize(){
        this.world = new BufferedImage(GameConstants.GAME_SCREEN_WIDTH,
                GameConstants.GAME_SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        this.setBackground(Color.BLACK);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        Graphics2D buffer = world.createGraphics();
        buffer.setColor(Color.black);
        buffer.fillRect(0,0, GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT);
        g2.drawImage(world, 0,0,null);
    }
}
