package basicj.alpha1_0;

import basicj.BasicJ;

public class Gradient extends BasicJ {

    public Gradient() {
        screen(256, 256);
        
        for(int y = 0; y < 256; y++) {
            for(int x = 0; x < 256; x++) {
                color(y, y, x);
                point(x, y);
            }
        }
    }
    
    public static void main(String[] args) {
        new Gradient();
    }

}
