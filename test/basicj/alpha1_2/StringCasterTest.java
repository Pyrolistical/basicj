package basicj.alpha1_2;

import static basicj.BasicJ.*;
import junit.framework.TestCase;

public class StringCasterTest extends TestCase {

    /*
     * Test method for 'basicj.BasicJ.isInt(String)'
     */
    public void testIsInt() {
        assertTrue(isInt("1234"));
        assertTrue(isInt("-1234"));
        assertTrue(!isInt("1234.0"));
        assertTrue(!isInt(" 1234"));
        assertTrue(!isInt("asdf"));
    }

    /*
     * Test method for 'basicj.BasicJ.isLong(String)'
     */
    public void testIsLong() {
        assertTrue(isLong("1234"));
        assertTrue(isLong("-1234"));
        assertTrue(isLong("3456789012"));
        assertTrue(isLong("-3456789012"));
        assertTrue(!isLong("1234.0"));
        assertTrue(!isLong(" 1234"));
        assertTrue(!isLong("asdf"));
    }

    /*
     * Test method for 'basicj.BasicJ.isDouble(String)'
     */
    public void testIsDouble() {
        assertTrue(isDouble("1234"));
        assertTrue(isDouble("-1234"));
        assertTrue(isDouble("3456789012"));
        assertTrue(isDouble("-3456789012"));
        assertTrue(isDouble("1234.0"));
        assertTrue(isDouble("-1234.0"));
        assertTrue(isDouble(" 1234"));
        assertTrue(isDouble("1234 "));
        assertTrue(isDouble(".1234"));
        assertTrue(isDouble("1234."));
        assertTrue(!isDouble("asdf"));
    }

    /*
     * Test method for 'basicj.BasicJ.isFloat(String)'
     */
    public void testIsFloat() {
        assertTrue(isFloat("1234"));
        assertTrue(isFloat("-1234"));
        assertTrue(isFloat("3456789012"));
        assertTrue(isFloat("-3456789012"));
        assertTrue(isFloat("1234.0"));
        assertTrue(isFloat("-1234.0"));
        assertTrue(isFloat(" 1234"));
        assertTrue(isFloat("1234 "));
        assertTrue(isFloat(".1234"));
        assertTrue(isFloat("1234."));
        assertTrue(!isFloat("asdf"));
    }

    /*
     * Test method for 'basicj.BasicJ.isChar(String)'
     */
    public void testIsChar() {
        assertTrue(isChar("a"));
        assertTrue(isChar("\n"));
        assertTrue(!isChar("ab"));
        assertTrue(!isChar(""));
    }

    /*
     * Test method for 'basicj.BasicJ.toInt(String)'
     */
    public void testToInt() {
        assertEquals(toInt("1234"), 1234);
        
        //test error
        System.err.println();
        System.err.println("toInt() error message:");
        toInt("asdf");
    }

    /*
     * Test method for 'basicj.BasicJ.toLong(String)'
     */
    public void testToLong() {
        assertEquals(toLong("1234"), 1234l);
        
        //test error
        System.err.println();
        System.err.println("toLong() error message:");
        toLong("asdf");
    }

    /*
     * Test method for 'basicj.BasicJ.toDouble(String)'
     */
    public void testToDouble() {
        assertEquals(toDouble("1234.5"), 1234.5);
        
        //test error
        System.err.println();
        System.err.println("toDouble() error message:");
        toDouble("asdf");
    }

    /*
     * Test method for 'basicj.BasicJ.toFloat(String)'
     */
    public void testToFloat() {
        assertEquals(toFloat("1234.5"), 1234.5f);
        
        //test error
        System.err.println();
        System.err.println("toFloat() error message:");
        toFloat("asdf");
    }

    /*
     * Test method for 'basicj.BasicJ.toChar(String)'
     */
    public void testToChar() {
        assertEquals(toChar("a"), 'a');
        
        //test error
        System.err.println();
        System.err.println("toChar() error message:");
        toChar("asdf");
    }

}
