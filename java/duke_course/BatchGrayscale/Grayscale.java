package BatchGrayscale;


/**
 * Write a description of class Grayscale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class Grayscale {
    static ImageResource convertToGrayscale(ImageResource inImage){
        ImageResource grayVersion = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for (Pixel pixel:grayVersion.pixels()){
            Pixel orgPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            int average = (orgPixel.getBlue()+orgPixel.getRed()+orgPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return grayVersion;
    }
    static void save(){
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource newImage = convertToGrayscale(inImage);
            newImage.draw();
            String fname = "gray-"+inImage.getFileName();
            newImage.setFileName(fname);
            newImage.save();
        }
    }
}
