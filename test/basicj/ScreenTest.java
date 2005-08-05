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
package basicj;

import java.awt.*;
import javax.swing.*;
/**
 * ScreenTest
 * 
 * @author Ronald Chen
 */
public class ScreenTest extends BasicJ {

	public ScreenTest() {
		super("Screen Test");
		int w = width();
		int h = height();
        
        /* 0: text test
         * 1: random text
         * 2: random points
         * 3: random lines
         * 4: random circles
         * 5: random characters
         */
		int test = 4;
		switch(test) {
			case 0:  // text test
				//clear(0, 255, 0);
				screen(300, 350);
				print("I was here first!");
				println("Hello World!");
				color(red);
				println("Hello World!");
				print("I was reversed!");
				color(128, 0, 0);
				print("H");print("e");print("l");print("l");
				color(0, 0, 128);
				println("H");println("e");println("l");println("l");
				color(yellow);
				print("new line in the mid\ndle");
				print("new line at the end\n");
				print("\nbegins with new line");
				print("begin single new line");
				print("\n");
				print("end single new line");
				print("double new\n\nline");
				println();
				color(0, 0, 235);
				print("new line in the mid\ndle");
				color(0, 0, 215);
				print("new line at the end\n");
				color(0, 0, 195);
				print("\nbegins with new line");
				color(0, 0, 175);
				print("begin single new line");
				color(0, 0, 155);
				print("\n");
				color(0, 0, 135);
				print("end single new line");
				color(0, 0, 115);
				print("double new\n\nline");
				color(192, 192, 192);
				print("line before super long text");
				clear(red);
				println("this is the poem that never ends, just like something something best friends!  it's like something something, i forgot the whole song, that's kinda sad since its the song that never ends!");
				print("line after it super long text");
				//screen(800, 250);
				//clear();
				//println("After clear");
				break;
			case 1:  // random text
				for(int i = 0; i < 2 << 10; i++) {
					color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
					print((char) (int) (Math.random()*64 + 64));
				}
				break;
			case 2:  // random points
				screen(250, 250);
				w = width();
				h = height();
				int x, y;
				int i = 1000;
				while(i-- > 0) {
					color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
					x = (int) (Math.random()*w);
					y = (int) (Math.random()*h);
					point(x, y);
				}
				screen(500, 500);
				w = width();
				h = height();
				while(true) {
					color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
					x = (int) (Math.random()*w);
					y = (int) (Math.random()*h);
					point(x, y);
				}
				//break;
			case 3:  // random lines
				while(true) {
					color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
					line((int) (Math.random()*w), (int) (Math.random()*h), (int) (Math.random()*w), (int) (Math.random()*h));
				}
				//break;
			case 4:  // random circles
				int r = ((w > h)?h:w);
				while(true) {
					color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
					circle((int) (Math.random()*w), (int) (Math.random()*h), (int) (Math.random()*r));
				}
				//break;
			case 5:  // random characters
				while(true) {
					color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
					text((int) (Math.random()*w), (int) (Math.random()*h), String.valueOf((char) (int) (Math.random()*64 + 64)));
				}
			default:
				print("Choose a valid test number");
		}
	}
	
	public static void main(String args[]) {		
		new ScreenTest();
	}
}
