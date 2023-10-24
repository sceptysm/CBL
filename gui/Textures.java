package gui;

import java.util.HashMap;
import java.util.Objects;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Textures {

    private static HashMap<String, BufferedImage> spriteHashMap;

    static void loadTextures() {
        spriteHashMap = new HashMap<>();

        File textureFolderPath = new File("resources/textures");

        for (File file : Objects.requireNonNull(textureFolderPath.listFiles())) {
            try {
                System.out.println("Loading texture: " + file.getName());
                spriteHashMap.put(file.getName().replaceAll(".png", ""), ImageIO.read(file));
            } catch (IOException exception) {
                System.out.println("Exception reading the texture: " + file.getName());
            }
        }

        System.out.println("Loaded all textures.");

    }

    static BufferedImage getSprite(String spriteKey) {
        return spriteHashMap.get(spriteKey);
    }

    
}
