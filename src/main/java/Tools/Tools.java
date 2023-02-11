package Tools;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Tools {
    public static Image getSubImage(Image img, int x, int y, int w, int h) {
        PixelReader reader = img.getPixelReader();
        javafx.scene.paint.Color picColor = reader.getColor(15,15);
        WritableImage image = new WritableImage(reader, x, y, w, h);
        picColor.getOpacity();
        return image;
    }

    public static void downlaodFile(String fullPath, ImageView[][]createdMap,Image[]avalailableTiles){
        BufferedImage bufferedImage = new BufferedImage(26,14,TYPE_INT_RGB);

        for(int j = 0; j < createdMap.length; j++){
            for(int i = 0; i < createdMap[0].length; i++){
                bufferedImage.setRGB(j,i,-16056320);
            }
        }

        for(int j = 0; j < createdMap.length - 1; j++){
            for(int i = 0; i < createdMap[0].length - 1; i++){
                for(int k = 0; k < avalailableTiles.length - 1; k++){
                    if(createdMap[j][i].getImage().equals(avalailableTiles[k])){
                        int rgb = ((k&0x0ff)<<16)|((255&0x0ff)<<8)|(255&0x0ff);
                        Color color = new Color(k,0,0);
                        bufferedImage.setRGB(j,i,color.getRGB());
                        //break;
                    }
                }
            }
        }

        save(bufferedImage,fullPath);
    }

    public static int getIntFromColor(int Red, int Green, int Blue){
        Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        Blue = Blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }

    private static void save(BufferedImage bi, String path) {
        try {
            File outputfile = new File(path);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
