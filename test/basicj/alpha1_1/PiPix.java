package basicj.alpha1_1;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import basicj.BasicJ;

public class PiPix extends BasicJ {

    public PiPix() {
        final int charWidth = 7;
        final int charHeight = 14;
        
        int desiredWidth = 1600;
        int desiredHeight = 1200;
        
        int width = fint((double) desiredWidth/charWidth);
        int height = fint((double) desiredHeight/charHeight);
        
        autoFlush(0);
        screen(width*charWidth, height*charHeight);
        byte pi[] = new byte[width*height];
        
        if(pi.length > 24000) {
            println("Sorry, but we only have 24,000 digits of Pi on file.");
            flush();
            return;
        }
        try {
            FileInputStream fis = new FileInputStream("test/basicj/alpha1_1/pi.txt");
            fis.read(pi);
            fis.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
            
        Image img = new ImageIcon("test/basicj/alpha1_1/pi.png").getImage();
        int imgWidth = img.getWidth(null);
        int imgHeight = img.getHeight(null);
        if(imgWidth != imgHeight) {
            println("Sorry, was expecting pi.png to be a square image.");
            flush();
            return;
        }
                
        // convert image to bitmap[]
        int bitmap[] = new int[imgWidth*imgHeight];
        PixelGrabber pg = new PixelGrabber(img, 0, 0, imgWidth, imgHeight, bitmap, 0, imgWidth);
        try {
            pg.grabPixels();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        // only use blue component, image should have been in grayscale
        for(int i = 0, n = bitmap.length; i < n; i++) {
            bitmap[i] = bitmap[i] & 0xFF;
            /*color(bitmap[i], bitmap[i], bitmap[i]);
            point(i % imgWidth, i/imgHeight);*/
        }
        
        int bgColor = 84;
        int fgColor = 255;
        boolean isWide = width > height;
        double scale = 1;
        double translate = 0;
        if(isWide) {
            scale = (double) imgHeight/(14*height);
            translate = (double) (7*width - 14*height)/2;
        } else {
            /*scale = (double) imgWidth/(7*width);
            translate = (double) (14*height - 7*width)/2;*/
            println("Sorry, only wide views are supported at the moment.");
            flush();
            return;
        }
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(isWide) {
                    if(7*(x + 1) < translate || 7*(x - 1) > translate + 14*height) {
                        color(bgColor, bgColor, bgColor);
                    } else {
                        double average = 0;
                        int avgCount = 0;
                        for(int j = rint(14*y*scale), jn = rint(14*(y + 1)*scale); j < jn; j++) {
                            for(int i = rint((7*x - translate)*scale), in = rint((7*(x + 1) - translate)*scale); i < in; i++) {
                                if(i > 0 && i < imgWidth && j > 0 && j < imgHeight) {
                                    average += bitmap[i + j*imgWidth];
                                    avgCount++;
                                }
                            }
                        }
                        int c = rint((fgColor - bgColor)*(average/avgCount)/255) + bgColor;
                        color(c, c, c);
                    }
                }
                print((char) pi[y*width + x]);
            }
        }
        flush();
        save("test/basicj/alpha1_1/Pi.bmp");
            
    }
    
    public static void main(String[] args) {
        new PiPix();
    }

}
