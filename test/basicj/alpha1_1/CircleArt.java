/*
 * Created on Aug 8, 2005 by Ronald Chen
 */
package basicj.alpha1_1;

import basicj.BasicJ;

/**
 * CircleArt
 * 
 * @author Ronald Chen
 */
public class CircleArt extends BasicJ {
	
	public CircleArt() {
		int n = 150;
		double d = 120;
		
		double frac;
		double theta;
		
		long start, end;
		start = System.currentTimeMillis();
		for(int i = 0; i < n; i++) {
			frac = (double) i/n;
			theta = 360*frac;
			circle((int) (d*sin(theta)) + width()/2, (int) (d*cos(theta)) + height()/2, (int) d);
		}
		end = System.currentTimeMillis();
		System.out.println((double) (end - start)/1000);
	}
	
	public static void main(String[] args) {
		new CircleArt();
	}
}
