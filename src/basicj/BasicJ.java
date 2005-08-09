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
    
    public static final double PI = Math.PI;
    public static final double E = Math.E;
    
    /**
     * The inital flush rate.
     * 
     * @see #autoFlush(long)
     */
    private static final long INITAL_FLUSHRATE = 250;
	
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
        flush();
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
	 * Returns the current height of the screen.
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
     * @param rate units in milliseconds and must be non-negative
     * @see #flush()
     */
    public void autoFlush(long rate) {
        if(rate > 0) {
            autoFlusher = new Timer();
            autoFlusher.scheduleAtFixedRate(new AutoFlushTask(), 0, rate);
        } else if(rate == 0) {
            if(autoFlusher != null) {
                autoFlusher.cancel();
            }
        }
    }
    
    /**
     * Sets the zoom factor.
     * @param factor must be greater than 1
     */
    public void zoom(int factor) {
        scr.zoom(factor);
        pack();
        flush();
    }
    
    /**
     * Returns the absolute value.
     * If a > 0, a otherwise if a < 0, -a.
     */
    public static double abs(double a) {
        return Math.abs(a);
    }
    
    /**
     * Returns the absolute value.
     * If a > 0, a otherwise if a < 0, -a.
     */
    public static float abs(float a) {
        return Math.abs(a);
    }
    
    /**
     * Returns the absolute value.
     * If a > 0, a otherwise if a < 0, -a.
     */
    public static int abs(int a) {
        return Math.abs(a);
    }
    
    /**
     * Returns the absolute value.
     * If a > 0, a otherwise if a < 0, -a.
     */
    public static long abs(long a) {
        return Math.abs(a);
    }
    
    /**
     * Returns the arc-cosine value in degrees.
     * Prints a warning to System.err if parameter is less than -1 or greater
     * than 1.
     */
    public static double acos(double a) {
        if(a < -1 || a > 1)
            System.err.println("--WARNING--    acos(" + a + "), " + a + " must be between -1 and 1.  " + a + " is out of range.");
        return Math.toDegrees(Math.acos(a));
    }
    
    /**
     * Returns the arc-cosine value in degrees.
     * Prints a warning to System.err if parameter is less than -1 or greater
     * than 1.
     */
    public static float acos(float a) {
        return (float) acos((double) a);
    }
    
    /**
     * Returns the arc-sine value in degrees.
     * Prints a warning to System.err if parameter is less than -1 or greater
     * than 1.
     */
    public static double asin(double a) {
        if(a < -1 || a > 1)
            System.err.println("--WARNING--    asin(" + a + "), " + a + " must be between -1 and 1.  " + a + " is out of range.");
        return Math.toDegrees(Math.asin(a));
    }
    
    /**
     * Returns the arc-sine value in degrees.
     * Prints a warning to System.err if parameter is less than -1 or greater
     * than 1.
     */
    public static float asin(float a) {
        return (float) asin((double) a);
    }
    
    /**
     * Returns the arc-tangent value in degrees.
     */
    public static double atan(double a) {
        return Math.toDegrees(Math.atan(a));
    }
    
    /**
     * Returns the arc-tangent value in degrees.
     */
    public static float atan(float a) {
        return (float) Math.toDegrees(Math.atan(a));
    }
    
    /**
     * Returns the arc-tangent value in degrees.
     * This returns the same value as atan(a/b) if b == 0, if b == 0, then
     * atan(a/b) returns 90 for a > 0 and -90 for a < 0.
     */
    public static double atan2(double a, double b) {
        return Math.toDegrees(Math.atan2(a, b));
    }
    
    /**
     * Returns the arc-tangent value in degrees.
     * This returns the same value as atan(a/b) if b == 0, if b == 0, then
     * atan(a/b) returns 90 for a > 0 and -90 for a < 0.
     */
    public static float atan2(float a, float b) {
        return (float) Math.toDegrees(Math.atan2(a, b));
    }
    
    /**
     * Returns the ceiling.
     * Rounds to the nearest integer greater than or equal to a.
     */
    public static long ceil(double a) {
        return (long) Math.ceil(a);
    }
    
    /**
     * Returns the ceiling.
     * Rounds to the nearest integer greater than or equal to a.
     */
    public static int ceil(float a) {
        return (int) Math.ceil(a);
    }
    
    /**
     * Returns the ceiling.
     * Rounds to the nearest integer greater than or equal to a.
     * This is a convenient function, since users will use double and int
     * rather than float or long.
     */
    public static int cint(double a) {
        return (int) Math.ceil(a);
    }
    
    /**
     * Returns the floor.
     * Rounds to the nearest integer less than or equal to a.
     */
    public static long floor(double a) {
        return (long) Math.floor(a);
    }
    
    /**
     * Returns the floor.
     * Rounds to the nearest integer less than or equal to a.
     */
    public static int floor(float a) {
        return (int) Math.floor(a);
    }
    
    /**
     * Returns the floor.
     * Rounds to the nearest integer less than or equal to a.
     * This is a convenient function, since users will use double and int
     * rather than float or long.
     */
    public static int fint(double a) {
        return (int) Math.floor(a);
    }
    
    /**
     * Returns the cosine of a degree.
     */
    public static double cos(double a) {
        return Math.cos(Math.toRadians(a));
    }
    
    /**
     * Returns the cosine of a degree.
     */
    public static float cos(float a) {
        return (float) Math.cos(Math.toRadians(a));
    }
    
    /**
     * Returns the sine of a degree.
     */
    public static double sin(double a) {
        return Math.sin(Math.toRadians(a));
    }
    
    /**
     * Returns the sine of a degree.
     */
    public static float sin(float a) {
        return (float) Math.sin(Math.toRadians(a));
    }
    
    /**
     * Returns the tangent of a degree.
     */
    public static double tan(double a) {
        return Math.tan(Math.toRadians(a));
    }
    
    /**
     * Returns the tangent of a degree.
     */
    public static float tan(float a) {
        return (float) Math.tan(Math.toRadians(a));
    }
    
    /**
     * Returns the exponential value of base e.
     * Returns e<super>a</super>.
     */
    public static double exp(double a) {
        return Math.exp(a);
    }
    
    /**
     * Returns the exponential value of base e.
     * Returns e<super>a</super>.
     */
    public static float exp(float a) {
        return (float) Math.exp(a);
    }
    
    /**
     * Returns the exponential value of base e.
     * Returns e<super>a</super> for a >= 0.  Prints a warning if a is less
     * than 0.
     */
    public static int exp(int a) {
        if(a < 0)
            System.err.println("--WARNING--    exp(" + a + "), " + a + " must be between greater than or equal to 0.  " + a + " is out of range.");
        return (int) Math.exp((long) a);
    }
    
    /**
     * Returns the exponential value of base e.
     * Returns e<super>a</super> for a >= 0.  Prints a warning if a is less
     * than 0.
     */
    public static long exp(long a) {
        if(a < 0)
            System.err.println("--WARNING--    exp(" + a + "), " + a + " must be between greater than or equal to 0.  " + a + " is out of range.");
        return (long) Math.exp(a);
    }
    
    /**
     * Returns the logarithm value with base e.
     * Prints a warning if a is less than or equal to 0.
     */
    public static double log(double a) {
        if(!(a > 0))
            System.err.println("--WARNING--    log(" + a + "), " + a + " must be between greater than 0.  " + a + " is out of range.");
        return Math.log(a);
    }
    
    /**
     * Returns the logarithm value with base e.
     * Prints a warning if a is less than or equal to 0.
     */
    public static float log(float a) {
        if(!(a > 0))
            System.err.println("--WARNING--    log(" + a + "), " + a + " must be between greater than 0.  " + a + " is out of range.");
        return (float) Math.log(a);
    }
    
    /**
     * Returns the larger value of the two.
     */
    public static double max(double a, double b) {
        return Math.max(a, b);
    }
    
    /**
     * Returns the larger value of the two.
     */
    public static float max(float a, float b) {
        return Math.max(a, b);
    }
    
    /**
     * Returns the larger value of the two.
     */
    public static int max(int a, int b) {
        return Math.max(a, b);
    }
    
    /**
     * Returns the larger value of the two.
     */
    public static long max(long a, long b) {
        return Math.max(a, b);
    }
    
    /**
     * Returns the smaller value of the two.
     */
    public static double min(double a, double b) {
        return Math.min(a, b);
    }
    
    /**
     * Returns the smaller value of the two.
     */
    public static float min(float a, float b) {
        return Math.min(a, b);
    }
    
    /**
     * Returns the smaller value of the two.
     */
    public static int min(int a, int b) {
        return Math.min(a, b);
    }
    
    /**
     * Returns the smaller value of the two.
     */
    public static long min(long a, long b) {
        return Math.min(a, b);
    }
    
    /**
     * Returns the power of a to b.
     * Returns a<super>b</super>.
     */
    public static double pow(double a, double b) {
        return Math.pow(a, b);
    }
    
    /**
     * Returns the power of a to b.
     * Returns a<super>b</super>.
     */
    public static float pow(float a, float b) {
        return (float) Math.pow(a, b);
    }
    
    /**
     * Returns the power of a to b.
     * Returns a<super>b</super>.  Prints a warning if b is less than 0.
     */
    public static int pow(int a, int b) {
        if(b < 0)
            System.err.println("--WARNING--    pow(" + a + ", " + b + "), " + b + " must be between greater than or equal to 0.  " + b + " is out of range.");
        return (int) Math.pow(a, b);
    }
    
    /**
     * Returns the power of a to b.
     * Returns a<super>b</super>.  Prints a warning if b is less than 0.
     */
    public static long pow(long a, long b) {
        if(b < 0)
            System.err.println("--WARNING--    pow(" + a + ", " + b + "), " + b + " must be between greater than or equal to 0.  " + b + " is out of range.");
        return (long) Math.pow(a, b);
    }
    
    /**
     * Returns a random value.
     * The value returned is less than or equal to 0, but strictly less than 1.
     * @return
     */
    public static double random() {
        return Math.random();
    }
    
    /**
     * Returns a random integer.
     * The value returned is between and including a and b.  If a is greater
     * than b, then a warning is printed and the parameters are switched
     * internally.
     */
    public static int random(int a, int b) {
        if(a > b) {
            System.err.println("--WARNING--    random(" + a + ", " + b + "), " + a + " must be less than " + b + ".  Try random(" + b + ", " + a + ") instead.");
            int temp = a;
            a = b;
            b = temp;
        }
        Random r = new Random();
        int diff = b - a;
        return r.nextInt(diff + 1) + a;
    }
    
    /**
     * Returns the closest integer to a.
     */
    public static long round(double a) {
        return (long) Math.round(a);
    }
    
    /**
     * Returns the closest integer to a.
     */
    public static int round(float a) {
        return (int) Math.round(a);
    }
    
    /**
     * Returns the closest integer to a.
     * This is a convenient function, since users will use double and int
     * rather than float or long.
     */
    public static int rint(double a) {
        return (int) Math.round(a);
    }
    
    /**
     * Returns the square root.
     * Prints a warning if a is less than 0.
     */
    public static double sqrt(double a) {
        if(a < 0)
            System.err.println("--WARNING--    sqrt(" + a + "), " + a + " must be between greater than or equal to 0.  " + a + " is out of range.");
        return Math.sqrt(a);
    }
    
    /**
     * Returns the square root.
     * Prints a warning if a is less than 0.
     */
    public static float sqrt(float a) {
        if(a < 0)
            System.err.println("--WARNING--    sqrt(" + a + "), " + a + " must be between greater than or equal to 0.  " + a + " is out of range.");
        return (float) Math.sqrt(a);
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
