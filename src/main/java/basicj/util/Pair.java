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

/**
 * A Pair of Objects.
 * This class is immutable.
 * 
 * @author Ronald Chen
 */
public final class Pair<T1, T2> {

	/**
	 * Object 1 or x.
	 */
	private final T1 x;
	
	/**
	 * Object 2 or y.
	 */
	private final T2 y;
	
	/**
	 * Creates a new Pair of null Objects.
	 *
	 */
	public Pair() {
		this(null, null);
	}
	
	/**
	 * Creates a new Pair of Objects.
	 */
	public Pair(T1 x, T2 y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the value of x
	 * 
	 * @return x
	 */
	public T1 getX() {
		return x;
	}
	/**
	 * Returns the value of y
	 * 
	 * @return y
	 */
	public T2 getY() {
		return y;
	}
	
	/**
	 * Prints the Pair.
	 * Uses the respective toString() methods for each Object.
	 */
	public String toString() {
		return "[" + x.toString() + ", " + y.toString() + "]";
	}
	
	/**
	 * Returns true iff this equals o.
	 * Uses the respective equals() methods for each Object.
	 */
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Pair)) return false;
		Pair p = (Pair) o;
		return x.equals(p.x) && y.equals(p.y);
	}
	
	/**
	 * Returns the hashCode.
	 * Uses the respective hashCode() methods for each Object.
	 */
	public int hashCode() {
		return x.hashCode() + 17*y.hashCode();
	}
}
