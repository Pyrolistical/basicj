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
package basicj.util;

import junit.framework.TestCase;

/**
 * PairTest
 * 
 * @author Ronald Chen
 */
public class PairTest extends TestCase {

	public void testEquals() {
		assertTrue(new Pair<Integer, String>(new Integer(34), "asdf").equals(new Pair<Integer, String>(new Integer(34), "asdf")));
		assertTrue(new Pair<String, String>("a", "b").equals(new Pair<Object, Object>("a", "b")));
		assertTrue(new Pair<String, String>("a", "b").equals(new Pair("a", "b")));
		assertTrue(!new Pair<Integer, String>(new Integer(34), "asdf").equals(new Pair<String, Integer>("adsf", new Integer(34))));
		assertTrue(!new Pair<String, Integer>(null, null).equals(null));
		assertTrue(!new Pair<String, Integer>(null, null).equals("asdf"));
	}
}
