/*
 * Created on Feb 28, 2005 by Ronald Chen
 */
package basicj.samples;

import basicj.BasicJ;

/**
 * PointArt
 * 
 * @author Ronald Chen
 */

public class PointArt extends BasicJ {

	private static double f(double x) {
		return 0.001*x*(x - 7.5)*(x - 5)*(x + 5)*(x + 7.5);
	}
	
	public PointArt() {
		int xmin = -10;
		int xmax = 10;
		int ymin = -10;
		int ymax = 10;
		
		int dx = xmax - xmin;
		int dy = ymax - ymin;
		
		double frac;
		int w = width();
		
		for(int i = 0; i < w; i++) {
			frac = (double) i/w;
			color(255, (int) (255*frac), (int) (255*frac));
			point(i, (int) ((1 - (f(dx*frac + xmin) - ymin)/dy)*height()));
		}
	}
	
	public static void main(String[] args) {
		new PointArt();
	}
}
