package superrainbowreef.menus;

import superrainbowreef.GameConstants;
import superrainbowreef.Launcher;
import superrainbowreef.Resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelpMenuPanel extends JPanel {
    private BufferedImage menuBackground;
    private JButton start;
    private JButton exit;
    private Launcher lf;

    public HelpMenuPanel(Launcher lf) {
        this.lf = lf;
        try {
            menuBackground = ImageIO.read(this.getClass().getClassLoader().getResource("Background1.bmp"));
        } catch (IOException e) {
            System.out.println("Error cant read menu background");
            e.printStackTrace();
            System.exit(-3);
        }
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        start = new JButton(new ImageIcon(Resource.getResourceImage("start")));
//        start.setFont(new Font("Courier New", Font.BOLD ,24));
//        start.setBounds(0,330,150,50);
        start.setBounds(230,400,150,50);
        start.setBorder(null);
        start.addActionListener((actionEvent -> {
            this.lf.setFrame("game");
        }));


        this.add(start);


    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.menuBackground,0,0,null);
        g2.setColor(Color.MAGENTA);
        g2.setFont(new Font("Oswald", Font.BOLD, 20));
        g2.drawString("Controls: ", 50, 100);
        g2.drawString("Left Arrow Key< : Move Left ", 50, 120);
        g2.drawString("Right Arrow Key > : Move Right ", 50, 140);
        g2.drawString("Objective/Rules: ", 50, 200);
        g2.drawString("Destroy all Big Legs!" , 50, 220);
        g2.drawString("If lives reaches 0 game is over" , 50, 240);
    }

}

