/*
 * Created on Jan 27, 2005 by Ronald Chen
 */
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
		//clear(0, 255, 0);
		screen(300, 350);
		print("I was here first!");
		println("Hello World!");
		color(255, 0, 0);
		println("Hello World!");
		print("I was reversed!");
		color(128, 0, 0);
		print("H");print("e");print("l");print("l");
		color(0, 0, 128);
		println("H");println("e");println("l");println("l");
		color(255, 255, 0);
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
		println("this is the poem that never ends, just like something something best friends!  it's like something something, i forgot the whole song, that's kinda sad since its the song that never ends!");
		print("line after it super long text");
		/*clear();
		println("After clear");*/
		/*for(int i = 0; i < 2 << 10; i++) {
			color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
			print((char) (int) (Math.random()*64 + 64));
		}*/
	}
	
	public static void main(String args[]) {		
		new ScreenTest();
	}
}
