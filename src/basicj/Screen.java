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
import java.awt.image.BufferedImage;

import javax.swing.*;
import basicj.util.*;

/**
 * Handles the visual and event based BasicJ commands.
 * 
 * The print, println, point, line, circle, color, screen, width, height, text, 
 * input, zoom, keypressed, and mouseclicked commands are implemented in this 
 * class.
 * 
 * The commands text, input, zoom, keypressed, and mouseclicked are not 
 * implmented yet.
 * 
 * @author Ronald Chen
 */
final class Screen extends JComponent {	
	
	/**
	 * Holds the next draw command to be drawn.
	 */
	private Command nextCommand;
	
	/**
	 * The back buffer of the screen.
	 * The final image is composed in this buffer from the print and draw
	 * buffers.  If there were no updates, the paint() method just paints this
	 * buffer.
	 */
	private BufferedImage backBuffer;
	
	/**
	 * The back buffer for print commands.
	 * This layer is where print commands and the background is rendered.
	 */
	private BufferedImage textBuffer;
	
	/**
	 * The back buffer for draw commands.
	 * This layer is where point, line, circle, and text commands are rendered.
	 * This buffer is only cleared when the clear command is called.  Even when
	 * a screen command is given, the draw buffer is copied onto the new draw 
	 * buffer.
	 */
	private BufferedImage drawBuffer;
	
	/**
	 * Flag that indicates back buffer needs updating.
	 */
	private boolean backPending;
	
	/**
	 * Flag that indicates print buffer needs updating.
	 */
	private boolean printPending;
	
	/**
	 * Flag that indicates draw buffer needs updating.
	 */
	private boolean drawPending;
	
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
	 * A transparent color.
	 * It is set to a transparent blue, just in case this is painted to an
	 * image that does not support transparency.  Thus the blue will show
	 * the error.
	 */
	private static final Color TRANSPARENT = new Color(0, 0, 255, 0);
	
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
	 * This variable should not be set directly in any method other than 
	 * color().
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
		nextCommand = null;
		resetBuffers(INITAL_WIDTH, INITAL_HEIGHT);
		formatedPrintBuffer = new LinkedList<Pair<Color, StringBuffer>>();
		
		screen(INITAL_WIDTH, INITAL_HEIGHT);
		
	}
	
	/**
	 * Creates a new buffer set.
	 * The fields backBuffer, textBuffer, and drawBuffer are initalized with
	 * new BufferedImage objects.  IE. The old buffers are lost.
	 */
	private void resetBuffers(int width, int height) {
		backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		textBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		drawBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		clearBuffers();
	}
	
	/**
	 * Clears all buffers.
	 * The back buffer is reset to bgColor. 
	 */
	private void clearBuffers() {
		Graphics g = textBuffer.getGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, textBuffer.getWidth(), textBuffer.getHeight());
		g = drawBuffer.getGraphics();
		g.setColor(TRANSPARENT);
		g.fillRect(0, 0, drawBuffer.getWidth(), drawBuffer.getHeight());
		backPending = false;
		printPending = false;
		drawPending = false;
	}

	/**
	 * Paints the visual commands.
	 */
	public void paint(Graphics g) {
		if(printPending) {
			updateTextBuffer();
		}
		if(drawPending) {
			updateDrawBuffer();
		}
		if(backPending) {
			updateBackBuffer();
		}
		g.drawImage(backBuffer, 0, 0, this);
	}
	
	/**
	 * Updates the text buffer.
	 * The buffer is cleared and the text is re-painted from the 
	 * formatedPrintBuffer.
	 */
	public void updateTextBuffer() {
		Graphics g = textBuffer.getGraphics();
		int x = 0;
		int y = 0;
		
		g.setColor(bgColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setFont(f);
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
		printPending = false;
		backPending = true;
	}
	
	/**
	 * Updates the back buffer.
	 * Here is where the textBuffer and drawBuffer is composed on the 
	 * backBuffer.
	 */
	public void updateBackBuffer() {
		Graphics g = backBuffer.getGraphics();
		g.drawImage(textBuffer, 0, 0, null);
		g.drawImage(drawBuffer, 0, 0, null);
		backPending = false;
	}
	
	/**
	 * Updates the draw buffer.
	 * The nextCommand is read and is drawn on to the draw buffer.
	 */
	public void updateDrawBuffer() {
		Graphics g = drawBuffer.getGraphics();
		
		if(nextCommand != null) {
			g.setColor(fgColor);
			Command.CommandID id = nextCommand.getCmdID();
			final int[] intParam = nextCommand.getIntParam();
			if(id == Command.POINT) {
				g.drawLine(intParam[0], intParam[1], intParam[0], intParam[1]);
			} else if(id == Command.LINE) {
				g.drawLine(intParam[0], intParam[1], intParam[2], intParam[3]);
			} else if(id == Command.CIRCLE) {
				g.drawOval(intParam[0] - intParam[2], intParam[1] - intParam[2], 2*intParam[2], 2*intParam[2]);
			}
		}
		drawPending = false;
		backPending = true;
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
				Pair<Color, StringBuffer> last = new Pair<Color, StringBuffer>(fgColor, new StringBuffer());
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
	 * Sets the next draw command to be drawn.
	 */
	private void drawCommand(Command c) {
		nextCommand = c;
		drawPending = true;
		repaint();
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
		clearBuffers();
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
		
		BufferedImage tempDrawBuffer = drawBuffer;
		resetBuffers(width, height);
		drawBuffer.getGraphics().drawImage(tempDrawBuffer, 0, 0, null);
		
		formatPrintBuffer();
		printPending = true;
		repaint();
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
		printPending = true;
		repaint();
	}
	
	/**
	 * Implements the point command.
	 */
	public void point(int x, int y) {
		drawCommand(Command.makePoint(new int[] {x, y}));
	}
	
	/**
	 * Implements the line command.
	 */
	public void line(int x0, int y0, int x1, int y1) {
		drawCommand(Command.makeLine(new int[] {x0, y0, x1, y1}));
	}
	
	/**
	 * Impements the circle command.
	 */
	public void circle(int x, int y, int r) {
		if(r == 0) {
			drawCommand(Command.makePoint(new int[] {x, y}));
		} else {
			drawCommand(Command.makeCircle(new int[] {x, y, r}));
		}
	}
	
}
