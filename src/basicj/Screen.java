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
import basicj.util.*;

/**
 * Handles the visual and event based BasicJ commands.
 * 
 * The print, println, point, line, circle, color, screen, width, height, text, 
 * input, zoom, keypressed, and mouseclicked commands are implemented in this 
 * class.
 * 
 * Thus far only color, print, println, screen, width, and height are implmented.
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
	 * Holds the input from print commands.
	 * This buffer also stores the current foreground color, for rendering 
	 * later.  The clear command empties this buffer.
	 */
	private java.util.List<Pair<Color, StringBuffer>> printBuffer;
	
	/**
	 * Holds the last print command.
	 * This Pair is always the last element in the [code]printBuffer[/code].  
	 * It is replaced with a new one if the foreground color has changed 
	 * since the last print command.
	 * @see printBuffer
	 */
	private Pair<Color, StringBuffer> lastPrint;
	
	/**
	 * Holds formated version of the printBuffer.
	 * Elements in this list are the same elements in the printBuffer, except
	 * that '\n' characters are converted into [code]hardBreak[/code]s and long
	 * lines are broken up to the screen width using [code]softBreak[/code]s.
	 * This list is cleared when the clear command is called.
	 * @see printBuffer, hardBreak, softBreak
	 */
	private java.util.List<Pair<Color, StringBuffer>> formatedPrintBuffer;
	
	/**
	 * Represents a hard new line break.
	 * This Pair is found in the [code]formatedPrintBuffer[/code].  It 
	 * separates lines of text where '\n' characters are found.
	 * @see softBreak, formatedPrintBuffer
	 */
	private static final Pair<Color, StringBuffer> hardBreak = new Pair<Color, StringBuffer>(null, null);
	
	/**
	 * Represents a soft new line break.
	 * This Pair is found in the [code]formatedPrintBuffer[/code].  It 
	 * separates long lines of text on to new lines.
	 * @see softBreak, formatedPrintBuffer
	 */
	private static final Pair<Color, StringBuffer> softBreak = null;
	
	/**
	 * The inital width of the screen.
	 */
	private static final int INITAL_WIDTH = 500;
	
	/**
	 * The inital height of the screen.
	 */
	private static final int INITAL_HEIGHT = 500;
	
	/**
	 * The inital foreground color.
	 */
	private static final Color INITAL_FOREGROUND = Colors.toColor(Colors.white);
	
	/**
	 * The inital background color.
	 */
	private static final Color INITAL_BACKGROUND = Colors.toColor(Colors.black);
	
	/**
	 * The fixed-width font used to render print and text commands.
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
	 * The new screen has a default size of INITAL_WIDTH by INITAL_HEIGHT with 
	 * the foreground color INITAL_FOREGROUND and background color 
	 * INITAL_BACKGROUND.
	 */
	public Screen() {
		super();
		setLayout(null);
		fgColor = INITAL_FOREGROUND;
		bgColor = INITAL_BACKGROUND;
		
		FontMetrics fm = getFontMetrics(f);
		charWidth = fm.charWidth('a');
		charHeight = fm.getHeight();
		charAscent = fm.getAscent();
		
		printBuffer = new LinkedList<Pair<Color, StringBuffer>>();
		lastPrint = new Pair<Color, StringBuffer>(fgColor, new StringBuffer());
		printBuffer.add(lastPrint);
		drawQueue = new LinkedList<Command>();
		formatedPrintBuffer = new LinkedList<Pair<Color, StringBuffer>>();
		
		screen(INITAL_WIDTH, INITAL_HEIGHT);
	}
	
	/**
	 * Paints the visual commands.
	 */
	public void paint(Graphics g) {
		super.paint(g);	
		
		int x = 0;
		int y = 0;
		synchronized(bgColor) {
			g.setColor(bgColor);
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setFont(f);
		g.setColor(fgColor);
		Pair<Color, StringBuffer> p;
		synchronized(formatedPrintBuffer) {
			Iterator<Pair<Color, StringBuffer>> it = formatedPrintBuffer.iterator();
			while(it.hasNext()) {
				p = it.next();
				if(p != hardBreak && p != softBreak) {
					g.setColor(p.getX());
					drawString(g, p.getY().toString(), x, y);
					x += charWidth*p.getY().length();						
				} else {
					x = 0;
					y += charHeight;						
				}
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
	 * Any Pair in the printBuffer with the character '\n' in the String is
	 * separated into two Pairs and a hardBreak between them.  If the String in
	 * the Pair is longer than the width of the screen minus the current 
	 * position of the cursor, it is separated into Pairs, so not to exceede 
	 * the screen width.  Between every new pair is a softBreak.  Otherwise, 
	 * Pair is just copied.  The result is stored in the formatedPrintBuffer.
	 * If the total number of lines in the formatedPrintBuffer exceedes the 
	 * current height of the screen, then the lines at the top are trucated 
	 * until the total number of lines is equal to the current height of the 
	 * screen.  If this height trunction occurs, printBuffer is updated 
	 * accordingly.
	 * @see formatedPrintBuffer, printBuffer, hardBreak, softBreak
	 */
	private void formatPrintBuffer() {
		final int charsPerWidth = getWidth()/charWidth;
		synchronized(formatedPrintBuffer) {
			formatedPrintBuffer.clear();
			
			Iterator<Pair<Color, StringBuffer>> it = printBuffer.iterator();
			StringTokenizer st;
			String s;
			int nLines = 0;
			Pair<Color, StringBuffer> curr;
			int cursorX = 0;
			while(it.hasNext()) {
				curr = it.next();
				st = new StringTokenizer(curr.getY().toString(), "\n", true);
				while(st.hasMoreTokens()) {
					s = st.nextToken();
					if(s.equals("\n")) {
						formatedPrintBuffer.add(hardBreak);
						cursorX = 0;
						nLines++;
						continue;
					}
					while(charWidth*(s.length() + cursorX) > getWidth()) {
						formatedPrintBuffer.add(new Pair<Color, StringBuffer>(curr.getX(), new StringBuffer(s.substring(0, charsPerWidth - cursorX))));
						s = s.substring(charsPerWidth - cursorX);
						formatedPrintBuffer.add(softBreak);
						cursorX = 0;
						nLines++;
					}
					formatedPrintBuffer.add(new Pair<Color, StringBuffer>(curr.getX(), new StringBuffer(s)));
					cursorX += s.length();
				}
			}

			if(nLines > 0 && nLines*charHeight >= getHeight()) {
				while(nLines*charHeight >= getHeight()) {
					while(formatedPrintBuffer.get(0) != hardBreak && formatedPrintBuffer.get(0) != softBreak) {
						formatedPrintBuffer.remove(0);
					}
					formatedPrintBuffer.remove(0);
					nLines--;
				}
				printBuffer.clear();
				it = formatedPrintBuffer.iterator();
				Pair<Color, StringBuffer> last = new Pair<Color, StringBuffer>(INITAL_FOREGROUND, new StringBuffer());
				printBuffer.add(last);
				while(it.hasNext()) {
					curr = it.next();
					if(curr == hardBreak) {
						last.getY().append("\n");
					} else if(curr == softBreak) {
						if(it.hasNext()) {
							curr = it.next();
							last.getY().append(curr.getY().toString());
						}
					} else {
						printBuffer.add(curr);
						last = curr;
					}
				}
				lastPrint = last;
			}
		}
	}
	
	/**
	 * Adds a draw command to drawQueue.
	 * This method is just a wrapper for draqQueue.add(...), since drawQueue
	 * needs to be synchronized with the paint method.
	 * @see drawQueue
	 */
	private void addCommand(Command c) {
		synchronized(drawQueue) {
			drawQueue.add(c);
		}
	}

	/**
	 * Implements the color command.
	 * All print and draw commands are lost.  The foreground and background
	 * colors are unchanged.
	 */
	public void clear() {
		printBuffer.clear();
		lastPrint = new Pair<Color, StringBuffer>(fgColor, new StringBuffer());
		printBuffer.add(lastPrint);
		formatedPrintBuffer.clear();
		drawQueue.clear();
		repaint();
	}
	
	/**
	 * Implements the color command, with optinal background.
	 * Sets the background color to c.
	 */
	public void clear(Color c) {
		bgColor = c;
		clear();
	}
	
	/**
	 * Implements the color command.
	 * The foreground color is set to c.
	 */
	public void color(Color c) {
		addCommand(Command.makeColor(c));
		fgColor = c;
	}
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
		if(lastPrint.getX() == fgColor) {
			lastPrint.getY().append(s);
		} else {
			lastPrint = new Pair<Color, StringBuffer>(fgColor, new StringBuffer(s));
			printBuffer.add(lastPrint);
		}
		formatPrintBuffer();
		repaint();
	}
	
}
