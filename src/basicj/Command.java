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

/**
 * This immutable class encapsulates print/draw commands.  Command holds a 
 * cmdID, and integer and string parameters.  Commands will have integer, 
 * string, or both types of parameters.
 * 
 * @author Ronald Chen
 */
final class Command {	

	static final class CommandID {
		
		private String name;
		
		public CommandID(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
	}
	
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
	
	private final CommandID cmdID;
	private final int[] intParam;
	private final String strParam;
	
	
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
	
	private Command(CommandID cmdID) {
		this(cmdID, new int[0], "");
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
