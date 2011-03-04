/*----------------------------------------------------------------------------
 This file is part of BasicJ.

 BasicJ is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 BasicJ is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with BasicJ; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 ----------------------------------------------------------------------------*/
package basicj.util;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

/**
 * Encodes Images to BMP files.
 * 
 * @author Ronald Chen
 */
public class BMPEncoder {
  
    private final static int FILE_HEADER_SIZE = 14;
    private final static int INFO_HEADER_SIZE = 40;
    
    /*
     * Class is uninitializable
     */
    private BMPEncoder() {
    }
    
    /**
     * Encodes Image to a Bitmap file given by filename.
     */
    public static void encode(String filename, Image img) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
        
            int width = img.getWidth(null);
            int height = img.getHeight(null);

            // convert image to bitmap[]
            int bitmap[] = new int[width*height];
            PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, bitmap, 0, width);
            try {
                pg.grabPixels();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            
            // calc image/file size for headers
            int pad = (4*(int) Math.ceil((double) 3*width/4) - 3*width)*height;
            int imageSize = 3*width*height + pad;
            int fileSize = FILE_HEADER_SIZE + INFO_HEADER_SIZE + imageSize;
            
            //write file header
            fos.write(new byte[] {'B', 'M'});                           // type
            fos.write(toDword(fileSize));                               // total file size
            fos.write(toWord(0));                                       // reserved 1
            fos.write(toWord(0));                                       // reserved 2
            fos.write(toDword(FILE_HEADER_SIZE + INFO_HEADER_SIZE));    // offset
            
            //write info header
            fos.write(toDword(INFO_HEADER_SIZE));                       // info header size
            fos.write(toDword(width));                                  // width of image
            fos.write(toDword(height));                                 // height of image
            fos.write(toWord(1));                                       // number of planes
            fos.write(toWord(24));                                      // bit count
            fos.write(toDword(0));                                      // compression
            fos.write(toDword(imageSize));                              // image size
            fos.write(toDword(0));                                      // x pixels per meter
            fos.write(toDword(0));                                      // y pixels per meter
            fos.write(toDword(0));                                      // colors used
            fos.write(toDword(0));                                      // important colors

            // write bitmap[] to fos
            for(int row = height - 1; row >= 0; row--) {
                for(int col = 0; col < width; col++) {
                    int encdRGB = bitmap[width*row + col];
                    fos.write(encdRGB & 0xFF);
                    fos.write((encdRGB >> 8) & 0xFF);
                    fos.write((encdRGB >> 16) & 0xFF);
                    if(col == width - 1) {
                        for(int i = 0; i < pad; i++) {
                            fos.write(0x0);
                        }
                    }
                }
            }
            
            fos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Converts an integer to a word.
     */
    private static byte[] toWord (int val) {
        byte word[] = new byte[2];
        word[0] = (byte) (val & 0xFF);
        word[1] = (byte) ((val >> 8) & 0xFF);
        return word;
    }

    /**
     * Converts an integer to a double word.
     */
    private static byte[] toDword (int val) {
        byte dword[] = new byte[4];
        dword[0] = (byte) (val & 0xFF);
        dword[1] = (byte) ((val >> 8) & 0xFF);
        dword[2] = (byte) ((val >> 16) & 0xFF);
        dword[3] = (byte) ((val >> 24) & 0xFF);
        return dword;
    }
    
}
