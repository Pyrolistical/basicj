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

import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * Handles the visual and event based BasicJ commands.
 * 
 * The print, println, point, line, circle, color, screen, width, height, text, 
 * input, zoom, keypressed, and mouseclicked commands are implemented in this 
 * class.
 * 
 * Thus far only print, println, screen, width, and height are implmented.
 * 
 * @author Ronald Chen
 */
final class Screen extends JComponent {

	/**
	 * Holds point, line, text, circle, and color commands.
	 * This is a FIFO queue, but it not emptied until the clear command is 
	 * called.
	 */
	private java.util.List<Command> drawQueue;
	
	/**
	 * Holds the input from print(ln) commands.
	 * The clear command empties this buffer.
	 */
	private StringBuffer printBuffer;
	
	/**
	 * The printBuffer formated into lines, given by new line breaks and the 
	 * current screen width.  IE. Each String in this list has length less than
	 * or equal to the current screen width.
	 */
	private java.util.List<String> formatedPrintBuffer;
	
	/**
	 * The inital width of the screen.
	 */
	private static final int INITAL_WIDTH = 500;
	
	/**
	 * The inital height of the screen.
	 */
	private static final int INITAL_HEIGHT = 500;
	
	/**
	 * The fixed-width font used to render print(ln) and text commands.
	 */
	private static final Font f = new Font("Courier New", Font.PLAIN, 12);
	
	/**
	 * The character width of f.
	 */
	private final int charWidth;
	
	/**
	 * The character height of f.
	 */
	private final int charHeight;
	
	/**
	 * The character ascent of f.
	 */
	private final int charAscent;
	
	/**
	 * The current foreground color.
	 */
	private Color fgColor;
	
	/**
	 * The current background color.
	 */
	private Color bgColor; 
	
	/**
	 * Creates a new screen.
	 * The new screen has a default size of 500 by 500 with the foreground
	 * color white and background color black.
	 */
	public Screen() {
		super();
		setLayout(null);
		fgColor = Color.WHITE;
		bgColor = Color.BLACK;
		
		FontMetrics fm = getFontMetrics(f);
		charWidth = fm.charWidth('a');
		charHeight = fm.getHeight();
		charAscent = fm.getAscent();
		
		printBuffer = new StringBuffer();
		drawQueue = new LinkedList<Command>();
		formatedPrintBuffer = new LinkedList<String>();
		
		screen(INITAL_WIDTH, INITAL_HEIGHT);
	}
	
	/**
	 * Paints the visual commands.
	 */
	public void paint(Graphics g) {
		super.paint(g);	
		
		int x = 0;
		int y = 0;
		g.setColor(bgColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setFont(f);
		g.setColor(fgColor);
		synchronized(formatedPrintBuffer) {
			Iterator it = formatedPrintBuffer.iterator();
			while(it.hasNext()) {
				drawString(g, (String) it.next(), 0, y);
				y += charHeight;
			}
		}
		synchronized(drawQueue) {
			/*Iterator it = printQueue.iterator();
			Command c;
			while(it.hasNext()) {
				c = (Command) it.next();
				Command.CommandID id = c.getCmdID();
				if(id == Command.PRINT) {
					String s = c.getStrParam();
					int chunkPoint;
					while(x + charWidth*s.length() > getWidth()) {
						chunkPoint = (getWidth() - x)/charWidth;
						drawString(g, s.substring(0, chunkPoint), x, y);
						s = s.substring(chunkPoint);
						x = 0;
						y += charHeight;
					}
					drawString(g, s, x, y);
					x += charWidth*s.length();
				} else if(id == Command.NEWLINE) {
					x = 0;
					y += charHeight;
				}
			}*/
		}
	}
	
	/**
	 * Draws the String s, given x, y, based at the top left corner of the
	 * String.  IE. It differs from g.drawString, which draws the String
	 * based at the left-baseline. 
	 */
	private void drawString(Graphics g, String s, int x, int y) {
		g.drawString(s, x, y + charAscent);
	}
	
	/**
	 * Formats printBuffer into formatedPrintBuffer.
	 * The printBuffer is tokenized by the '\n' character and stored in
	 * formatedPrintBuffer.  If a token is longer than the current width of 
	 * the screen, then it is sliced into lines with total widths less than the
	 * current width.  If the total number of lines in the formatedPrintBuffer
	 * exceedes the current height of the screen, then the lines at the top
	 * are trucated until the total number of lines is equal to the current
	 * height of the screen.
	 */
	private void formatPrintBuffer() {
		synchronized(formatedPrintBuffer) {
			formatedPrintBuffer.clear();
			StringTokenizer st = new StringTokenizer(printBuffer.toString(), "\n");
			final int charsPerWidth = getWidth()/charWidth;
			String s;
			while(st.hasMoreTokens()) {
				s = st.nextToken();
				while(charWidth*s.length() > getWidth()) {
					formatedPrintBuffer.add(s.substring(0, charsPerWidth));
					s = s.substring(charsPerWidth);
				}
				formatedPrintBuffer.add(s);
			}
			if(formatedPrintBuffer.size()*charHeight > getHeight()) {
				while(formatedPrintBuffer.size()*charHeight > getHeight()) {
					formatedPrintBuffer.remove(0);
				}
				printBuffer.delete(0, printBuffer.length());
				Iterator it = formatedPrintBuffer.iterator();
				while(it.hasNext()) {
					printBuffer.append((String) it.next());
				}
			}
		}
	}
	/**
	 * Adds a draw command to drawQueue.
	 * This method is just a wrapper for draqQueue.add(...), since drawQueue
	 * needs to be synchronized with the paint method.
	 * @param c
	 */
	private void addCommand(Command c) {
		synchronized(drawQueue) {
			drawQueue.add(c);
		}
	}
	
	/*public void update(Graphics g) {
		paint(g);
	}*/
	
	/**
	 * Implements the width command.
	 * @return the current width of the screen
	 */
	public int width() {
		return getWidth();
	}
	
	/**
	 * Implements the height command.
	 * @return the current height of the screen
	 */
	public int height() {
		return getHeight();
	}
	
	/**
	 * Implements the screen command.
	 * @param width the new width
	 * @param height the new height
	 */
	public void screen(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		formatPrintBuffer();
	}
	
	/**
	 * Implements the print command.
	 * All other types can be casted to type String.
	 */
	public void print(String s) {
		printBuffer.append(s);
		formatPrintBuffer();
		repaint();
	}
	
	/**
	 * Implements the print command.
	 * All other types can be casted to type String.
	 */
	public void println(String s) {
		printBuffer.append(s);
		printBuffer.append("\n");
		formatPrintBuffer();
		repaint();
	}
	
	
}
