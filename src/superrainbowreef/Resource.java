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
            Resource.resources.put("katch", read(SSR.class.getClassLoader().getResource("katch.gif")));
            Resource.resources.put("pop", read(SSR.class.getClassLoader().getResource("pop.gif")));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-5);
        }
    }
    public static BufferedImage getResourceImage(String key){
        return Resource.resources.get(key);
    }
}
