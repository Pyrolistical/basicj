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
 * The main BasicJ class.
 * All BasicJ programs extend this class to access BasicJ commands.  The 
 * template for a BasicJ program is:
 * <pre><code>
 * import basicj;
 * 
 * public class &lt;Program Name&gt; extends BasicJ {
 * 
 *   public &lt;Program Name&gt;() {
 *     &lt;BasicJ commands&gt;
 *     ...
 *     &lt;BasicJ commands&gt;
 *   }
 *   
 *   public static void main(String args[]) {
 *     new &lt;Program Name&gt;();
 *   }
 * }
 * </code></pre>
 * 
 * where <code>&lt;BasicJ commands&gt;</code> are public methods of this class.
 *  
 * @author Ronald Chen
 */
public class BasicJ extends JFrame {
	
	/**
	 * The screen object that implements visual and event commands.
	 */
	private Screen scr;
	
	/**
	 * Creates a new BasicJ program and sets the title.
	 * This constructor is usually called by <code>super(title)</code> by the 
	 * extending class.
	 * @param title the title on the JFrame
	 */
	public BasicJ(String title) {
		super(((title.equals(""))?"":title + " - ") + "BasicJ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		scr = new Screen();
		add(scr);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Creates a new BasicJ program.
	 * The extending class doesn't need to call <code>super()</code>, since it
	 * is called automatically.
	 */
	public BasicJ() {
		this("");
	}

	/**
	 * Prints nothing.
	 */
	public void print() {
	}
	
	/**
	 * Prints a string.
	 * @param s the string to be printed
	 */
	public void print(String s) {
		scr.print(s);
	}
	
	/**
	 * Prints an integer.
	 * @param i the integer to be printed
	 */
	public void print(int i) {
		print(String.valueOf(i));
	}
	
	/**
	 * Prints a long integer.
	 * @param l the long integer to be printed
	 */
	public void print(long l) {
		print(String.valueOf(l));
	}
	
	/**
	 * Prints a float.
	 * @param f the float to be printed
	 */
	public void print(float f) {
		print(String.valueOf(f));
	}
	
	/**
	 * Prints a double.
	 * @param d the double to be printed
	 */
	public void print(double d) {
		print(String.valueOf(d));
	}
	
	/**
	 * Prints a character.
	 * @param c the character to be printed
	 */
	public void print(char c) {
		print(String.valueOf(c));
	}
	
	/**
	 * Prints a boolean.
	 * @param b the boolean to be printed
	 */
	public void print(boolean b) {
		print(String.valueOf(b));
	}
	
	/**
	 * Prints a new line.
	 */
	public void println() {
		println("");
	}
	
	/**
	 * Prints a string with a new line.
	 * @param s the string to be printed
	 */
	public void println(String s) {
		scr.print(s + "\n");
	}
	
	/**
	 * Prints an integer with a new line.
	 * @param i the integer to be printed
	 */
	public void println(int i) {
		println(String.valueOf(i));
	}
	
	/**
	 * Prints a long integer with a new line.
	 * @param l the long integer to be printed
	 */
	public void println(long l) {
		println(String.valueOf(l));
	}
	
	/**
	 * Prints a float with a new line.
	 * @param f the float to be printed
	 */
	public void println(float f) {
		println(String.valueOf(f));
	}
	
	/**
	 * Prints a double with a new line.
	 * @param d the double to be printed
	 */
	public void println(double d) {
		println(String.valueOf(d));
	}
	
	/**
	 * Prints a character with a new line.
	 * @param c the character to be printed
	 */
	public void println(char c) {
		println(String.valueOf(c));
	}
	
	/**
	 * Prints a boolean with a new line.
	 * @param b the boolean to be printed
	 */
	public void println(boolean b) {
		println(String.valueOf(b));
	}
	
	/**
	 * Sets the screen size.
	 * The screen size is defined by the inner component, not the size of the 
	 * JFrame.  The default screen size is (500, 500).
	 * @param width the width must be greater than 0
	 * @param height the height must be greater than 0
	 */
	public void screen(int width, int height) {
		scr.screen(width, height);
		pack();
	}
	
	/**
	 * Returns the current width of the screen.
	 * The width returned is the same width defined by the 
	 * <code>screen</code> command.
	 */
	public int width() {
		return scr.width();
	}
	
	/**
	 * Returns the current height of the screen
	 * The width returned is the same height defined by the
	 * <code>screen</code> command.
	 */
	public int height() {
		return scr.height();
	}
	
	/**
	 * Sets the foreground color.
	 * The paramters are the RGB components.  Each paramters has a valid range
	 * of 0 to 255.
	 */
	public void color(int r, int g, int b) {
		scr.color(new Color(r, g, b));
	}
	
	/**
	 * Clears the screen.
	 */
	public void clear() {
		scr.clear();
	}
	
	/**
	 * Clears the screen to a new background color.
	 * The paramters are the RGB components.  Each paramters has a valid range
	 * of 0 to 255.
	 */
	public void clear(int r, int g, int b) {
		scr.clear(new Color(r, g, b));
	}
}