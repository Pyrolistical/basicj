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
import java.util.*;
import java.util.Timer;

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

    
	public static final int black       = Colors.black;
	public static final int darkblue    = Colors.darkblue;
	public static final int darkgreen   = Colors.darkgreen;
	public static final int darkcyan    = Colors.darkcyan;
	public static final int darkred     = Colors.darkred;
	public static final int darkmagenta = Colors.darkmagenta;
	public static final int darkyellow  = Colors.darkyellow;
	public static final int gray        = Colors.gray;
	public static final int darkgray    = Colors.darkgray;
	public static final int blue        = Colors.blue;
	public static final int green       = Colors.green;
	public static final int cyan        = Colors.cyan;
	public static final int red         = Colors.red;
	public static final int magenta     = Colors.magenta;
	public static final int yellow      = Colors.yellow;
	public static final int white       = Colors.white;
    
    
    /**
     * The inital flush rate.
     * 
     * @see #autoFlush(long)
     */
    private static final long INITAL_FLUSHRATE = 100;
	
	/**
	 * The screen object that implements visual and event commands.
     * 
     * @see #screen(int, int)
	 */
	private Screen scr;
    
    /**
     * The auto flush timer.
     * 
     * @see #autoFlush(long)
     */
    private Timer autoFlusher;
	
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
        
        autoFlush(INITAL_FLUSHRATE);
		
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
	 * Sets the foreground color.
	 * The parameter is a named color.  See the public statics.
	 */
	public void color(int c) {
		scr.color(Colors.toColor(c));
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
	
	/**
	 * Clears the screen to a new background color.
	 * The parameter is a named color.  See the public statics.
	 */
	public void clear(int c) {
		scr.clear(Colors.toColor(c));
	}
	
	/**
	 * Draws a pixel.
	 * The color of the pixel is the color last set by the color command.
	 */
	public void point(int x, int y) {
		scr.point(x, y);
	}
	
	/**
	 * Draws a line.
	 * A line is drawn between the end points (x0, y0) and (x1, y1).  The 
	 * color of the line is the color last set by the color command.
	 */
	public void line(int x0, int y0, int x1, int y1) {
		scr.line(x0, y0, x1, y1);
	}
	
	/**
	 * Draws a circle.
	 * A circle is drawn with (x, y) as its center point and with radius r.
	 * The color of the circle is the color last set by the color command.
	 */
	public void circle(int x, int y, int r) {
		scr.circle(x, y, r);
	}
	
	/**
	 * Draws text.
	 * The String s is drawn at (x, y), where x, y is the top corner of the 
	 * String.  The color of the text is the color last set by the color 
	 * command.
	 */
	public void text(int x, int y, String s) {
		scr.text(x, y, s);
	}
    
    /**
     * Renders all text and draw commands immediately.
     *
     */
    public void flush() {
        scr.flush();
    }
    
    /**
     * Sets the auto flush rate.
     * Auto flushing is on by default at a rate of 100ms.  To handle flushing
     * manually (needed for animation/performance) set the rate to 0, and use
     * the flush command.
     *  
     * @param rate
     * @see #flush()
     */
    public void autoFlush(long rate) {
        if(rate > 0) {
            autoFlusher = new Timer();
            autoFlusher.scheduleAtFixedRate(new AutoFlushTask(), 0, rate);
        } else {
            if(autoFlusher != null) {
                autoFlusher.cancel();
            }
        }
    }
    
    /**
     * Simple TimerTask for auto flushing.
     * 
     * @author Ronald Chen
     */
    class AutoFlushTask extends TimerTask {
        public void run() {
            flush();
        }
    }
}
