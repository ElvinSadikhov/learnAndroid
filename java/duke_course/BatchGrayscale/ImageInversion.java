package BatchGrayscale;


/**
 * Write a description of class ImageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class ImageInversion {
    static ImageResource convertToNegative(ImageResource inImage){
        ImageResource grayVersion = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for (Pixel pixel:grayVersion.pixels()){
            Pixel orgPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            pixel.setRed(255-orgPixel.getRed());
            pixel.setGreen(255-orgPixel.getGreen());
            pixel.setBlue(255-orgPixel.getBlue());
        }
        return grayVersion;
    }
    static void save(){
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource newImage = convertToNegative(inImage);
            newImage.draw();
            String fname = "negative-"+inImage.getFileName();
            newImage.setFileName(fname);
            newImage.save();
        }
    }
}
