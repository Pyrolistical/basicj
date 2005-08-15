package basicj.alpha1_2;

import basicj.BasicJ;

public class BouncingCircle extends BasicJ {

    public BouncingCircle() {
        autoFlush(0);
        int radius = 32;
        int x = random(radius, width() - 1 - radius);
        int y = random(radius, height() - 1 - radius);
        int xd = 2*random(0, 1) - 1;
        int yd = 2*random(0, 1) - 1;
        int distance = 1;
        int time = 15;
        
        while(true) {
            color(black);
            circle(x, y, radius);
            if(x + xd*distance - radius < 0 || x + xd*distance + radius > width() - 1) {
                xd *= -1;
            }
            if(y + yd*distance - radius < 0 || y + yd*distance + radius > height() - 1) {
                yd *= -1;
            }
            color(white);
            x += xd*distance;
            y += yd*distance;
            circle(x, y, radius);
            flush();
            pause(time);
        }
    }
    
    public static void main(String[] args) {
        new BouncingCircle();
    }

}
