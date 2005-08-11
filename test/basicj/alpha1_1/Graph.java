/*
 * Created on Aug 10, 2005 by Ronald Chen
 */
package basicj.alpha1_1;

import basicj.BasicJ;

public class Graph extends BasicJ {

    public Graph(String title, Function f, int xy, double stepSize) {
        super(title);
        
        int xmax = xy;
        int xmin = -xy;
        int ymax = xy;
        int ymin = -xy;
        
        int dx = xmax - xmin;
        int dy = ymax - ymin;
        
        //draw grid
        color(112, 112, 112);
        int n = 2*dx;
        for(int i = 0; i < n; i++) {
            line(i*width()/n, 0, i*width()/n, height());
        }
        
        n = 2*dy;
        for(int i = 0; i < n; i++) {
            line(0, i*height()/n, width(), i*height()/n);
        }
        
        // draw axis
        color(gray);
        line(0, height()/2, width(), height()/2);
        line(width()/2, 0, width()/2, height());
        
        // draw ticks
        for(int i = 0; i < dx; i++) {
            line(i*width()/dx, height()*49/100, i*width()/dx, height()*51/100);
        }
        
        for(int i = 0; i < dy; i++) {
            line(width()*49/100, i*height()/dy, width()*51/100, i*height()/dy);
        }
        
        // draw curve
        color(white);
        thickness(2);
        double lastx = xmin;
        double lasty = f.evaluate(xmin);
        n = rint(dx/stepSize);
        for(int i = 1; i < n; i++) {
            //double newx = (double) i*dx/n;
            double newx = lastx + stepSize;
            double newy = f.evaluate(newx);
            line(rint((lastx - xmin)*width()/dx), rint((-lasty - ymin)*height()/dy), rint((newx - xmin)*width()/dx), rint((-newy - ymin)*height()/dy));
            lastx = newx;
            lasty = newy;
        }
        
    }
    
    public static void main(String args[]) {
        new Graph("Parabola", new Function() {
            public double evaluate(double x) {
                return pow(x, 2);
            }
        }, 10, 0.1);
        
        new Graph("exponential", new Function() {
            public double evaluate(double x) {
                return exp(x);
            }
        }, 10, 0.1);
        
        new Graph("Sine", new Function() {
            public double evaluate(double x) {
                return sin(x*180/PI);
            }
        }, 7, 0.1);
        
        new Graph("Tangent", new Function() {
            public double evaluate(double x) {
                return tan(x*180/PI);
            }
        }, 7, 0.1);
        
        new Graph("Cosine", new Function() {
            public double evaluate(double x) {
                return cos(x*180/PI);
            }
        }, 7, 0.1);
        
        new Graph("Square Root", new Function() {
            public double evaluate(double x) {
                return sqrt(x + 10);
            }
        }, 10, 0.1);
        
        new Graph("Logarithm", new Function() {
            public double evaluate(double x) {
                return log(x + 10.000000000000001);
            }
        }, 10, 0.1);
        
        new Graph("Absolute Value", new Function() {
            public double evaluate(double x) {
                return abs(x);
            }
        }, 10, 0.1);
        
        new Graph("Arc Sine", new Function() {
            public double evaluate(double x) {
                return asin(x)*PI/180;
            }
        }, 1, 0.1);
        
        new Graph("Arc Cosine", new Function() {
            public double evaluate(double x) {
                return acos(x)*PI/180;
            }
        }, 1, 0.1);
        
        new Graph("Arc Tangent", new Function() {
            public double evaluate(double x) {
                return atan(x)*PI/180;
            }
        }, 10, 0.1);
    }
}
