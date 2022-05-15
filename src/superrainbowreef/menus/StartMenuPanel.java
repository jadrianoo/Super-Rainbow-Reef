package superrainbowreef.menus;

import superrainbowreef.Launcher;
import superrainbowreef.Resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartMenuPanel extends JPanel {
    private BufferedImage menuBackground;
    private JButton start;
    private JButton exit;
    private Launcher lf;

    public StartMenuPanel(Launcher lf) {
        this.lf = lf;
        try {
            menuBackground = ImageIO.read(this.getClass().getClassLoader().getResource("Title.gif"));
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
        start.setBounds(-20,330,150,50);
        start.setBorder(null);
        start.addActionListener((actionEvent -> {
            this.lf.setFrame("game");
        }));


        exit = new JButton(new ImageIcon(Resource.getResourceImage("end")));
        exit.setSize(new Dimension(200,100));
        exit.setBorder(null);
        exit.setBounds(370,330,150,50);
        exit.addActionListener((actionEvent -> {
            this.lf.closeGame();
        }));


        this.add(start);
        this.add(exit);

    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.menuBackground,0,0,null);
    }

}
