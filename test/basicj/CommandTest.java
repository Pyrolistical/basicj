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
 * Created on Jan 22, 2005 by Ronald Chen
 */
package basicj;

import java.util.*;
import junit.framework.TestCase;

/**
 * CommandTest
 * 
 * @author Ronald Chen
 */
public class CommandTest extends TestCase {

	public void testPrintConstructor() {
		Command c = Command.makePrint("Hello");
		assertTrue(c.getCmdID() == Command.PRINT && c.getStrParam().equals("Hello"));
		
		c = Command.makePrint("");
		assertTrue(c.getStrParam().equals(""));
	}
	
	public void testPointConstructor() {
		Command c = Command.makePoint(new int[] {35, 93});
		assertTrue(c.getCmdID() == Command.POINT && Arrays.equals(c.getIntParam(), new int[] {35, 93}));
		
		try {
			c = Command.makePoint(null);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makePoint(new int[3]);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
	}
	
	public void testLineConstructor() {
		Command c = Command.makeLine(new int[] {34, 26, 92, 18});
		assertTrue(c.getCmdID() == Command.LINE && Arrays.equals(c.getIntParam(), new int[] {34, 26, 92, 18}));
		
		try {
			c = Command.makeLine(null);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeLine(new int[5]);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
	}
	
	public void testCircleConstructor() {
		Command c = Command.makeCircle(new int[] {62, 26, 2});
		assertTrue(c.getCmdID() == Command.CIRCLE && Arrays.equals(c.getIntParam(), new int[] {62, 26, 2}));
		
		try {
			c = Command.makeCircle(null);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeCircle(new int[4]);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeCircle(new int[] {35, 26, -6});
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
	}
	
	public void testTextConstructor() {
		Command c = Command.makeText(new int[] {62, 26}, "Hello");
		assertTrue(c.getCmdID() == Command.TEXT && Arrays.equals(c.getIntParam(), new int[] {62, 26}) && c.getStrParam().equals("Hello"));
		
		try {
			c = Command.makeText(null, "");
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeText(new int[3], "");
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeText(new int[0], null);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeText(new int[3], null);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
	}
	
	public void testScreenConstructor() {
		Command c = Command.makeScreen(new int[] {62, 26});
		assertTrue(c.getCmdID() == Command.SCREEN && Arrays.equals(c.getIntParam(), new int[] {62, 26}));
		
		try {
			c = Command.makeScreen(null);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeScreen(new int[3]);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeScreen(new int[] {-1, 4});
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
	}
	
	public void testZoomConstructor() {
		Command c = Command.makeZoom(new int[] {2});
		assertTrue(c.getCmdID() == Command.ZOOM && Arrays.equals(c.getIntParam(), new int[] {2}));
		
		try {
			c = Command.makeZoom(null);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeZoom(new int[2]);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeZoom(new int[] {-1});
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
	}
	
	public void testColorConstructor() {
		Command c = Command.makeColor(new int[] {62, 26, 222});
		assertTrue(c.getCmdID() == Command.COLOR && Arrays.equals(c.getIntParam(), new int[] {62, 26, 222}));
		
		try {
			c = Command.makeScreen(null);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeScreen(new int[4]);
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeScreen(new int[] {-1, 9, 3});
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
		
		try {
			c = Command.makeScreen(new int[] {801, 9, 3});
			assertTrue(false);
		} catch(IllegalArgumentException e) {
		}
	}
}
