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

/*
 * Created on Jan 10, 2005 by Ronald Chen
 */
package basicj;

import java.util.*;

/**
 * This immutable class encapsulates print/draw commands.  Command holds a 
 * cmdID, and integer and string parameters.  Commands will have integer, 
 * string, or both types of parameters.
 * 
 * @author Ronald Chen
 */
final class Command {

	private static final class CommandID {
		
		private String name;
		
		public CommandID(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
	}
	/**
	 * cmdID for print/println commands
	 * Parameters for print/println commands are converted to String.
	 */
	public static final CommandID PRINT = new CommandID("print");
	
	/**
	 * cmdID for the color commands
	 * Parameters for color commands are Integer.
	 */
	public static final CommandID COLOR = new CommandID("color");
	
	/**
	 * cmdID for the point command
	 * Parameters for point command are Integer.
	 */
	public static final CommandID POINT = new CommandID("point");
	
	/**
	 * cmdID for the line command
	 * Parameters for line command are Integer.
	 */
	public static final CommandID LINE = new CommandID("line");
	
	/**
	 * cmdID for the circle command
	 * Parameters for circle command are Integer.
	 */
	public static final CommandID CIRCLE = new CommandID("circle");
	
	/**
	 * cmdID for the text command
	 * Parameters for text command are Integer and String.
	 */
	public static final CommandID TEXT = new CommandID("text");
	
	/**
	 * cmdID for the screen command
	 * Parameters for screen command are Integer.
	 */
	public static final CommandID SCREEN = new CommandID("screen");
	
	/**
	 * cmdID for the zoom command
	 * Parameters for zoom command are Integer.
	 */
	public static final CommandID ZOOM = new CommandID("zoom");
	
	private final CommandID cmdID;
	private final int[] intParam;
	private final String strParam;
	
	/*
	 * The dummy default constructor.
	 * This constructs a Command object with <code>getCmdID() == Command.PRINT 
	 * && getStrParam() == ""</code> is true.  It is not intended to be called 
	 * directly.  It is only used for Command array construction.
	 *
	 */
	private Command() {
		cmdID = PRINT;
		intParam = new int[0];
		strParam = "";
	}
	
	private Command(CommandID cmdID, int[] intParam, String strParam) {
		if(intParam == null) throw new IllegalArgumentException("intParam cannot be null");
		if(strParam == null) throw new IllegalArgumentException("strParam cannot be null");
		this.cmdID = cmdID;
		this.intParam = copy(intParam);
		this.strParam = strParam;
	}
	
	private Command(CommandID cmdID, String strParam) {
		this(cmdID, new int[0], strParam);
	}
	
	private Command(CommandID cmdID, int[] intParam) {
		this(cmdID, intParam, "");
	}
	
	/**
	 * Makes a new Print Command.
	 * All top level print/println paramters can be to String, and thus that 
	 * is done and assigned to strParam.
	 * @param strParam the data converted to a string to be printed
	 * @return A Command object where <code>getCmdID() == Command.LINE && getStrParam() == strParam</code> is true
	 */
	public static Command makePrint(String strParam) {
		return new Command(Command.PRINT, strParam);
	}
	
	/**
	 * Makes a new Color Command.
	 * Color commands come in two favors.  The parameter is either one integer
	 * ranging from 0 to 15 (16 standard colors) or 3 integers ranging from 0
	 * to 255, denoting rgb.
	 * @param intParam array of paramters of either length 1 or 3
	 * @return A Command object where <code>getCmdID() == Command.COLOR && Arrays.equals(getIntParam(), intParam)</code> is true
	 */
	public static Command makeColor(int[] intParam) {
		if(intParam == null) throw new IllegalArgumentException("intParam cannot be null");
		if(intParam.length == 1) {
			if(intParam[0] < 0 || intParam[0] > 15) throw new IllegalArgumentException("valid named colors range from 0 to 15, color " + intParam[0] + " is invalid");
		} else if(intParam.length == 3) {
			if(intParam[0] < 0 || intParam[0] > 255) throw new IllegalArgumentException("valid red component range from 0 to 255, color " + intParam[0] + " is invalid");
			if(intParam[1] < 0 || intParam[1] > 255) throw new IllegalArgumentException("valid green component range from 0 to 255, color " + intParam[1] + " is invalid");
			if(intParam[2] < 0 || intParam[2] > 255) throw new IllegalArgumentException("valid blue component range from 0 to 255, color " + intParam[2] + " is invalid");
		} else {
			throw new IllegalArgumentException("intParam must be of either contain 1 or 3 paramters, and not " + intParam.length);
		}
		return new Command(Command.COLOR, intParam);
	}
	
	/**
	 * Makes a new Point Command.
	 * The two parameters define a x,y location of where a pixel should be
	 * drawn.  The screen is defined by 0,0 at the top left corner.  If x,y is
	 * less than 0,0 it is not drawn.  Same for x,y that exceede 
	 * width(),height().
	 * @param intParam the x,y of the point to be drawn
	 * @return A Command object where <code>getCmdID() == Command.POINT && Arrays.equals(getIntParam(), intParam)</code> is true
	 */
	public static Command makePoint(int[] intParam) {
		if(intParam == null) throw new IllegalArgumentException("intParam cannot be null");
		if(intParam.length != 2) throw new IllegalArgumentException("intParam must be of length 2, and not " + intParam.length);
		return new Command(Command.POINT, intParam);
	}
	
	/**
	 * Makes a new Line Command.
	 * The four parameters define two x,y locations of where the end points of 
	 * the line should be drawn.  The screen is defined by 0,0 at the top left 
	 * corner.  If the end points are not on the screen, a line may still be 
	 * drawn if some part of the line intersects the screen.
	 * @param intParam the x0,y0 and x1,y1 of the end points of the line to be drawn
	 * @return A Command object where <code>getCmdID() == Command.LINE && Arrays.equals(getIntParam(), intParam)</code> is true
	 */
	public static Command makeLine(int[] intParam) {
		if(intParam == null) throw new IllegalArgumentException("intParam cannot be null");
		if(intParam.length != 4) throw new IllegalArgumentException("intParam must be of length 4, and not " + intParam.length);
		return new Command(Command.LINE, intParam);
	}
	
	/**
	 * Makes a new Circle Command.
	 * The three parameters define a x,y point of the center and r radius of 
	 * the circle  to be drawn.  The screen is defined by 0,0 at the top left 
	 * corner.  If the center is not on the screen, if the given radius still
	 * intersects the screen, that part of the circle will be drawn.  The 
	 * radius must be greater than 0.
	 * @param intParam the x,y of the center, and r radius of the circle to be drawn
	 * @return A Command object where <code>getCmdID() == Command.CIRCLE && Arrays.equals(getIntParam(), intParam)</code> is true
	 */
	public static Command makeCircle(int[] intParam) {
		if(intParam == null) throw new IllegalArgumentException("intParam cannot be null");
		if(intParam.length != 3) throw new IllegalArgumentException("intParam must be of length 3, and not " + intParam.length);
		if(intParam[2] <= 0) throw new IllegalArgumentException("the radius can't be less than 0");
		return new Command(Command.CIRCLE, intParam);
	}
	
	/**
	 * Makes a new Text Command.
	 * The two integer parameters define a x,y location of top left corner of 
	 * the text to be drawn.  The string parameter is the text to be drawn.  
	 * The screen is defined by 0,0 at the top left corner.  If x,y is not on
	 * on the screen, then part of the text may still be drawn if it intersects
	 * the screen.
	 * @param intParam the x,y of the top left corner of the text
	 * @param strParam the text to be drawn
	 * @return A Command object where <code>getCmdID() == Command.TEXT && Arrays.equals(getIntParam(), intParam) && getStrParam.equals(strParam)</code> is true
	 */
	public static Command makeText(int[] intParam, String strParam) {
		if(intParam == null) throw new IllegalArgumentException("intParam cannot be null");
		if(intParam.length != 2) throw new IllegalArgumentException("intParam must be of length 2, and not " + intParam.length);
		return new Command(Command.TEXT, intParam, strParam);
	}
	
	/**
	 * Makes a new Screen Command.
	 * The two parameters define a x,y location of big the screen should be.
	 * Both parameters should be positive.
	 * @param intParam the x,y of the new screen size
	 * @return A Command object where <code>getCmdID() == Command.SCREEN && Arrays.equals(getIntParam(), intParam)</code> is true
	 */
	public static Command makeScreen(int[] intParam) {
		if(intParam == null) throw new IllegalArgumentException("intParam cannot be null");
		if(intParam.length != 2) throw new IllegalArgumentException("intParam must be of length 2, and not " + intParam.length);
		if(intParam[0] <= 0 || intParam[1] <= 0) throw new IllegalArgumentException("both parameters must be positive");
		return new Command(Command.SCREEN, intParam);
	}
	
	/**
	 * Makes a new Zoom Command.
	 * The parameter define zoom factor.  The zoom factor is defined as the 
	 * virtual pixel size.  IE. Zoom factor of 3 draws every pixel as a 3x3
	 * group of pixels of the same color.  The zoom factor must be positive.
	 * @param intParam the zoom factor
	 * @return A Command object where <code>getCmdID() == Command.ZOOM && Arrays.equals(getIntParam(), intParam)</code> is true
	 */
	public static Command makeZoom(int[] intParam) {
		if(intParam == null) throw new IllegalArgumentException("intParam cannot be null");
		if(intParam.length != 1) throw new IllegalArgumentException("intParam must be of length 1, and not " + intParam.length);
		if(intParam[0] <= 0) throw new IllegalArgumentException("the zoom factor must be positive and not " + intParam[0]);
		return new Command(Command.ZOOM, intParam);
	}
	
	private int[] copy(int[] intParam) {
		if(intParam == null) throw new IllegalArgumentException("intParam cannot be null");
		int[] temp = new int[intParam.length];
		System.arraycopy(intParam, 0, temp, 0, intParam.length);
		return temp;
	}
	
	/**
	 * Returns the value of intParam.
	 * 
	 * @return intParam
	 */
	public int[] getIntParam() {
		return copy(intParam);
	}
	
	/**
	 * Returns the value of strParam.
	 * 
	 * @return strParam
	 */
	public String getStrParam() {
		return strParam;
	}
	
	/**
	 * Returns true iff o equals this Command.
	 */
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Command)) return false;
		Command c = (Command) o;
		return Arrays.equals(intParam, c.intParam) && strParam.equals(c.strParam);
	}
	
	/**
	 * Returns the hashcode of this Command.
	 */
	public int hashCode() {
		return Arrays.hashCode(intParam) + 17*strParam.hashCode();
	}
	
	/**
	 * Returns the value of cmdID.
	 * Use the public static CommandID objects found in this class to find
	 * the command type cmdID.
	 * 
	 * @return cmdID
	 */
	public CommandID getCmdID() {
		return cmdID;
	}
}
