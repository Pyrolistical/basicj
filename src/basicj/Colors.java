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

/**
 * Holds the static names and methods of colors.
 * 
 * @author Ronald Chen
 */
class Colors {

	public static final int black       = 0;
	public static final int darkblue    = 1;
	public static final int darkgreen   = 2;
	public static final int darkcyan    = 3;
	public static final int darkred     = 4;
	public static final int darkmagenta = 5;
	public static final int darkyellow  = 6;
	public static final int gray        = 7;
	public static final int darkgray    = 8;
	public static final int blue        = 9;
	public static final int green       = 10;
	public static final int cyan        = 11;
	public static final int red         = 12;
	public static final int magenta     = 13;
	public static final int yellow      = 14;
	public static final int white       = 15;
	
	private static final Color cBlack       = new Color(  0,   0,   0);
	private static final Color cDarkBlue    = new Color(  0,   0, 128);
	private static final Color cDarkGreen   = new Color(  0, 128,   0);
	private static final Color cDarkCyan    = new Color(  0, 128, 128);
	private static final Color cDarkRed     = new Color(128,   0,   0);
	private static final Color cDarkMagenta = new Color(128,   0, 128);
	private static final Color cDarkYellow  = new Color(128, 128,   0);
	private static final Color cGray        = new Color(192, 192, 192);
	private static final Color cDarkGray    = new Color(128, 128, 128);
	private static final Color cBlue        = new Color(  0,   0, 255);
	private static final Color cGreen       = new Color(  0, 255,   0);
	private static final Color cCyan        = new Color(  0, 255, 255);
	private static final Color cRed         = new Color(255,   0,   0);
	private static final Color cMagenta     = new Color(255,   0, 255);
	private static final Color cYellow      = new Color(255, 255,   0);
	private static final Color cWhite       = new Color(255, 255, 255);
	
	/**
	 * Converts a named static color to an actual Color.
	 */
	public static Color toColor(int color) {
		switch(color) {
			case black:
				return cBlack;
			case darkblue:
				return cDarkBlue;
			case darkgreen:
				return cDarkGreen;
			case darkcyan:
				return cDarkCyan;
			case darkred:
				return cDarkRed;
			case darkmagenta:
				return cDarkMagenta;
			case darkyellow:
				return cDarkYellow;
			case gray:
				return cGray;
			case darkgray:
				return cDarkGray;
			case blue:
				return cBlue;
			case green:
				return cGreen;
			case cyan:
				return cCyan;
			case red:
				return cRed;
			case magenta:
				return cMagenta;
			case yellow:
				return cYellow;
			case white:
				return cWhite;
			default:
				return cBlack;
		}
	}
	
	
}
