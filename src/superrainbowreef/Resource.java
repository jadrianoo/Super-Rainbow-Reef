package superrainbowreef;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static javax.imageio.ImageIO.read;

public class Resource {
    private static Map<String, BufferedImage> resources;

    static{
        Resource.resources = new HashMap<>();
        try {
            Resource.resources.put("start", read(SRR.class.getClassLoader().getResource("Button_start.gif")));
            Resource.resources.put("end", read(SRR.class.getClassLoader().getResource("Button_quit.gif")));
            Resource.resources.put("background", read(SRR.class.getClassLoader().getResource("Background1.bmp")));
            Resource.resources.put("katch", read(SRR.class.getClassLoader().getResource("katch.gif")));
            Resource.resources.put("pop", read(SRR.class.getClassLoader().getResource("pop.gif")));
            Resource.resources.put("wall", read(SRR.class.getClassLoader().getResource("Wall.gif")));
            Resource.resources.put("solidW", read(SRR.class.getClassLoader().getResource("Block_solid.gif")));
            Resource.resources.put("redBlock", read(SRR.class.getClassLoader().getResource("Block3.gif")));
            Resource.resources.put("blueBlock", read(SRR.class.getClassLoader().getResource("Block6.gif")));
            Resource.resources.put("yellowBlock", read(SRR.class.getClassLoader().getResource("Block2.gif")));
            Resource.resources.put("greenBlock", read(SRR.class.getClassLoader().getResource("Block4.gif")));
            Resource.resources.put("purpleBlock", read(SRR.class.getClassLoader().getResource("Block1.gif")));
            Resource.resources.put("lifeBlock", read(SRR.class.getClassLoader().getResource("Block_life.gif")));
            Resource.resources.put("bigLegSmall", read(SRR.class.getClassLoader().getResource("Bigleg_small.gif")));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-5);
        }
    }
    public static BufferedImage getResourceImage(String key){
        return Resource.resources.get(key);
    }
}
