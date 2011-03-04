/*
 * Created on Feb 28, 2005 by Ronald Chen
 */
package basicj.samples;

import basicj.BasicJ;

/**
 * LineArt
 * 
 * @author Ronald Chen
 */
public class LineArt extends BasicJ {

	public LineArt() {
		int n = 80;
		double frac;
		for(int i = 0; i <= n; i++) {
			frac = (double) i/n;
			color(255, (int) (255*(1-frac)), (int) (255*(1-frac)));
			line(0, (int) (frac*height()), (int) (frac*width()), height());
		}
	}
	public static void main(String[] args) {
		new LineArt();
	}
}
