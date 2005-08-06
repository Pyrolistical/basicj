/*
 * Created on Aug 6, 2005 by Ronald Chen
 */
package basicj.alpha1_0;

import basicj.BasicJ;

/**
 * CircleArt
 * 
 * @author Ronald Chen
 */
public class ZoomCircleArt extends BasicJ {
	
	public ZoomCircleArt() {
        zoom(2);
		int n = 150;
		double d = 120;
		
		double frac;
		double theta;
		
		long start, end;
		start = System.currentTimeMillis();
		for(int i = 0; i < n; i++) {
			frac = (double) i/n;
			theta = frac*(2*Math.PI);
			circle((int) (d*Math.sin(theta)) + width()/2, (int) (d*Math.cos(theta)) + height()/2, (int) d);
		}
		end = System.currentTimeMillis();
		System.out.println((double) (end - start)/1000);
	}
	
	public static void main(String[] args) {
		new ZoomCircleArt();
	}
}
