/*
 * Created on Aug 8, 2005 by Ronald Chen
 */
package basicj.alpha1_1;

import basicj.BasicJ;
import junit.framework.TestCase;
import java.util.Random;

public class MathTest extends TestCase {

    private Random r;
    
    public void setUp() {
        r = new Random();
    }
    
    private double randDouble() {
        return Double.valueOf(Integer.toString(r.nextInt()) + "." + Integer.toString(Math.abs(r.nextInt())));
    }
    
    private double randPosDouble() {
        return Double.valueOf(Integer.toString(Math.abs(r.nextInt())) + "." + Integer.toString(Math.abs(r.nextInt())));
    }
    
    private float randFloat() {
        return Float.valueOf(Integer.toString(r.nextInt()) + "." + Integer.toString(Math.abs(r.nextInt())));
    }
    
    private float randPosFloat() {
        return Float.valueOf(Integer.toString(Math.abs(r.nextInt())) + "." + Integer.toString(Math.abs(r.nextInt())));
    }
    
    /*
     * Test method for 'basicj.BasicJ.abs(double)'
     */
    public void testAbsDouble() {
        double d = randPosDouble();
        double d2 = -d;
        assertEquals(Double.doubleToLongBits(BasicJ.abs(d)), Double.doubleToLongBits(d));
        assertEquals(Double.doubleToLongBits(BasicJ.abs(d2)), Double.doubleToLongBits(d));
    }

    /*
     * Test method for 'basicj.BasicJ.abs(float)'
     */
    public void testAbsFloat() {
        float f = randPosFloat();
        float f2 = -f;
        assertEquals(Float.floatToIntBits(BasicJ.abs(f)), Float.floatToIntBits(f));
        assertEquals(Float.floatToIntBits(BasicJ.abs(f2)), Float.floatToIntBits(f));
    }

    /*
     * Test method for 'basicj.BasicJ.abs(int)'
     */
    public void testAbsInt() {
        int i = Math.abs(r.nextInt());
        int i2 = -i;
        assertEquals(BasicJ.abs(i), i);
        assertEquals(BasicJ.abs(i2), i);
    }

    /*
     * Test method for 'basicj.BasicJ.abs(long)'
     */
    public void testAbsLong() {
        long l = Math.abs(r.nextLong());
        long l2 = -l;
        assertEquals(BasicJ.abs(l), l);
        assertEquals(BasicJ.abs(l2), l);
    }

    /*
     * Test method for 'basicj.BasicJ.acos(double)'
     */
    public void testAcosDouble() {
        double d = ((r.nextBoolean())?1:-1)*r.nextDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.acos(d)), Double.doubleToLongBits(Math.toDegrees(Math.acos(d))));
        
        // check domain
        System.err.println();
        System.err.println("Two BasicJ.acos(double) domain warnings:");
        BasicJ.acos(2.0);
        BasicJ.acos(-2.0);
    }

    /*
     * Test method for 'basicj.BasicJ.acos(float)'
     */
    public void testAcosFloat() {
        float f = ((r.nextBoolean())?1:-1)*r.nextFloat();
        assertEquals(Float.floatToIntBits(BasicJ.acos(f)), Float.floatToIntBits((float) Math.toDegrees(Math.acos(f))));
        
        // check domain
        System.err.println();
        System.err.println("Two BasicJ.acos(float) domain warnings:");
        BasicJ.acos(2.0f);
        BasicJ.acos(-2.0f);
    }

    /*
     * Test method for 'basicj.BasicJ.asin(double)'
     */
    public void testAsinDouble() {
        double d = ((r.nextBoolean())?1:-1)*r.nextDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.asin(d)), Double.doubleToLongBits(Math.toDegrees(Math.asin(d))));
        
        // check domain
        System.err.println();
        System.err.println("Two BasicJ.asin(double) domain warnings:");
        BasicJ.asin(2.0);
        BasicJ.asin(-2.0);
    }

    /*
     * Test method for 'basicj.BasicJ.asin(float)'
     */
    public void testAsinFloat() {
        float f = ((r.nextBoolean())?1:-1)*r.nextFloat();
        assertEquals(Float.floatToIntBits(BasicJ.acos(f)), Float.floatToIntBits((float) Math.toDegrees(Math.acos(f))));
        
        // check domain
        System.err.println();
        System.err.println("Two BasicJ.asin(float) domain warnings:");
        BasicJ.asin(2.0f);
        BasicJ.asin(-2.0f);
    }

    /*
     * Test method for 'basicj.BasicJ.atan(double)'
     */
    public void testAtanDouble() {
        double d = randDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.atan(d)), Double.doubleToLongBits(Math.toDegrees(Math.atan(d))));
    }

    /*
     * Test method for 'basicj.BasicJ.atan(float)'
     */
    public void testAtanFloat() {
        float f = randFloat();
        assertEquals(Float.floatToIntBits(BasicJ.atan(f)), Float.floatToIntBits((float) Math.toDegrees(Math.atan(f))));
    }

    /*
     * Test method for 'basicj.BasicJ.atan2(double, double)'
     */
    public void testAtan2DoubleDouble() {
        double d = randDouble();
        double d2 = randDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.atan2(d, d2)), Double.doubleToLongBits(Math.toDegrees(Math.atan2(d, d2))));
    }

    /*
     * Test method for 'basicj.BasicJ.atan2(float, float)'
     */
    public void testAtan2FloatFloat() {
        float f = randFloat();
        float f2 = randFloat();
        assertEquals(Float.floatToIntBits(BasicJ.atan2(f, f2)), Float.floatToIntBits((float) Math.toDegrees(Math.atan2(f, f2))));
    }

    /*
     * Test method for 'basicj.BasicJ.ceil(double)'
     */
    public void testCeilDouble() {
        double d = randDouble();
        assertEquals(BasicJ.ceil(d), (long) Math.ceil(d));
    }

    /*
     * Test method for 'basicj.BasicJ.ceil(float)'
     */
    public void testCeilFloat() {
        float f = randFloat();
        assertEquals(BasicJ.ceil(f), (int) Math.ceil(f));
    }
    
    /*
     * Test method for 'basicj.BasicJ.cint(double)'
     */
    public void testCintDouble() {
        double d = randDouble();
        assertEquals(BasicJ.cint(d), (int) Math.ceil(d));
    }

    /*
     * Test method for 'basicj.BasicJ.floor(double)'
     */
    public void testFloorDouble() {
        double d = randDouble();
        assertEquals(BasicJ.floor(d), (long) Math.floor(d));
    }

    /*
     * Test method for 'basicj.BasicJ.floor(float)'
     */
    public void testFloorFloat() {
        float f = randFloat();
        assertEquals(BasicJ.floor(f), (int) Math.floor(f));
    }
    
    /*
     * Test method for 'basicj.BasicJ.fint(double)'
     */
    public void testFintDouble() {
        double d = randDouble();
        assertEquals(BasicJ.fint(d), (int) Math.floor(d));
    }

    /*
     * Test method for 'basicj.BasicJ.cos(double)'
     */
    public void testCosDouble() {
        double d = randDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.cos(d)), Double.doubleToLongBits(Math.cos(Math.toRadians(d))));
    }

    /*
     * Test method for 'basicj.BasicJ.cos(float)'
     */
    public void testCosFloat() {
        float f = randFloat();
        assertEquals(Float.floatToIntBits(BasicJ.cos(f)), Float.floatToIntBits((float) Math.cos(Math.toRadians(f))));
    }

    /*
     * Test method for 'basicj.BasicJ.sin(double)'
     */
    public void testSinDouble() {
        double d = randDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.sin(d)), Double.doubleToLongBits(Math.sin(Math.toRadians(d))));
    }

    /*
     * Test method for 'basicj.BasicJ.sin(float)'
     */
    public void testSinFloat() {
        float f = randFloat();
        assertEquals(Float.floatToIntBits(BasicJ.sin(f)), Float.floatToIntBits((float) Math.sin(Math.toRadians(f))));
    }

    /*
     * Test method for 'basicj.BasicJ.tan(double)'
     */
    public void testTanDouble() {
        double d = randDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.tan(d)), Double.doubleToLongBits(Math.tan(Math.toRadians(d))));
    }

    /*
     * Test method for 'basicj.BasicJ.tan(float)'
     */
    public void testTanFloat() {
        float f = randFloat();
        assertEquals(Float.floatToIntBits(BasicJ.tan(f)), Float.floatToIntBits((float) Math.tan(Math.toRadians(f))));
    }

    /*
     * Test method for 'basicj.BasicJ.exp(double)'
     */
    public void testExpDouble() {
        double d = randDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.exp(d)), Double.doubleToLongBits(Math.exp(d)));
    }

    /*
     * Test method for 'basicj.BasicJ.exp(float)'
     */
    public void testExpFloat() {
        float f = randFloat();
        assertEquals(Float.floatToIntBits(BasicJ.exp(f)), Float.floatToIntBits((float) Math.exp(f)));
    }
    
    /*
     * Test method for 'basicj.BasicJ.exp(int)'
     */
    public void testExpInt() {
        int i = -1;
        while(i < 0)
            i = r.nextInt();
        assertEquals(BasicJ.exp(i), (int) Math.exp(i));
        
        // check domain
        System.err.println();
        System.err.println("One BasicJ.exp(int) warning:");
        BasicJ.exp(-1);
    }
    
    /*
     * Test method for 'basicj.BasicJ.exp(long)'
     */
    public void testExpLong() {
        long l = -1l;
        while(l < 0)
            l = r.nextLong();
        assertEquals(BasicJ.exp(l), (long) Math.exp(l));
        
        //check domain
        System.err.println();
        System.err.println("One BasicJ.exp(long) warning:");
        BasicJ.exp(-1l);
    }

    /*
     * Test method for 'basicj.BasicJ.log(double)'
     */
    public void testLogDouble() {
        double d = -1;
        while(!(d > 0))
            d = randDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.log(d)), Double.doubleToLongBits(Math.log(d)));
        
        // check domain
        System.err.println();
        System.err.println("Three BasicJ.log(double) warnings:");
        BasicJ.log(0.0);
        BasicJ.log(-0.0);
        BasicJ.log(-1.0);
    }

    /*
     * Test method for 'basicj.BasicJ.log(float)'
     */
    public void testLogFloat() {
        float f = -1f;
        while(!(f > 0))
            f = randFloat();
        assertEquals(Float.floatToIntBits(BasicJ.log(f)), Float.floatToIntBits((float) Math.log(f)));
        
        //check domain
        System.err.println();
        System.err.println("Three BasicJ.log(float) warnings:");
        BasicJ.log(0.0f);
        BasicJ.log(-0.0f);
        BasicJ.log(-1.0f);
    }

    /*
     * Test method for 'basicj.BasicJ.max(double, double)'
     */
    public void testMaxDoubleDouble() {
        double d = randDouble();
        double d2 = randDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.max(d, d2)), Double.doubleToLongBits(Math.max(d, d2)));
    }

    /*
     * Test method for 'basicj.BasicJ.max(float, float)'
     */
    public void testMaxFloatFloat() {
        float f = randFloat();
        float f2 = randFloat();
        assertEquals(Float.floatToIntBits(BasicJ.max(f, f2)), Float.floatToIntBits(Math.max(f, f2)));
    }

    /*
     * Test method for 'basicj.BasicJ.max(int, int)'
     */
    public void testMaxIntInt() {
        int i = r.nextInt();
        int i2 = r.nextInt();
        assertEquals(BasicJ.max(i, i2), Math.max(i, i2));
    }

    /*
     * Test method for 'basicj.BasicJ.max(long, long)'
     */
    public void testMaxLongLong() {
        long l = r.nextLong();
        long l2 = r.nextLong();
        assertEquals(BasicJ.max(l, l2), Math.max(l, l2));
    }

    /*
     * Test method for 'basicj.BasicJ.min(double, double)'
     */
    public void testMinDoubleDouble() {
        double d = randDouble();
        double d2 = randDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.min(d, d2)), Double.doubleToLongBits(Math.min(d, d2)));
    }

    /*
     * Test method for 'basicj.BasicJ.min(float, float)'
     */
    public void testMinFloatFloat() {
        float f = randFloat();
        float f2 = randFloat();
        assertEquals(Float.floatToIntBits(BasicJ.min(f, f2)), Float.floatToIntBits(Math.min(f, f2)));
    }

    /*
     * Test method for 'basicj.BasicJ.min(int, int)'
     */
    public void testMinIntInt() {
        int i = r.nextInt();
        int i2 = r.nextInt();
        assertEquals(BasicJ.min(i, i2), Math.min(i, i2));
    }

    /*
     * Test method for 'basicj.BasicJ.min(long, long)'
     */
    public void testMinLongLong() {
        long l = r.nextLong();
        long l2 = r.nextLong();
        assertEquals(BasicJ.min(l, l2), Math.min(l, l2));
    }

    /*
     * Test method for 'basicj.BasicJ.pow(double, double)'
     */
    public void testPowDoubleDouble() {
        double d = randDouble();
        double d2 = randDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.pow(d, d2)), Double.doubleToLongBits(Math.pow(d, d2)));
    }

    /*
     * Test method for 'basicj.BasicJ.pow(float, float)'
     */
    public void testPowFloatFloat() {
        float f = randFloat();
        float f2 = randFloat();
        assertEquals(Float.floatToIntBits(BasicJ.pow(f, f2)), Float.floatToIntBits((float) Math.pow(f, f2)));
    }

    /*
     * Test method for 'basicj.BasicJ.pow(int, int)'
     */
    public void testPowIntInt() {
        int i = r.nextInt();
        int i2 = -1;
        while(i2 < 0)
            i2 = r.nextInt();
        assertEquals(BasicJ.pow(i, i2), (int) Math.pow(i, i2));
        
        // check domain
        System.err.println();
        System.err.println("One BasicJ.pow(int, int) warning:");
        BasicJ.pow(i, -1);
    }

    /*
     * Test method for 'basicj.BasicJ.pow(long, long)'
     */
    public void testPowLongLong() {
        long l = r.nextLong();
        long l2 = -1l;
        while(l2 < 0)
            l2 = r.nextLong();
        assertEquals(BasicJ.pow(l, l2), (long) Math.pow(l, l2));
        
        // check domain
        System.err.println();
        System.err.println("One BasicJ.pow(long, long) warning:");
        BasicJ.pow(l, -1l);
    }

    /*
     * Test method for 'basicj.BasicJ.random()'
     */
    public void testRandom() {
        System.err.println();
        System.err.println("Ten runs of BasicJ.random():");
        for(int i = 0; i < 10; i++)
            System.err.println(BasicJ.random());
    }

    /*
     * Test method for 'basicj.BasicJ.random(int, int)'
     */
    public void testRandomIntInt() {
        System.err.println();
        System.err.println("Ten runs of BasicJ.random(1, 10)");
        for(int i = 0; i < 10; i++)
            System.err.println(BasicJ.random(1, 10));
        
        //check parameter ordering
        System.err.println();
        System.err.println("Two BasicJ.random(int, int) warnings:");
        BasicJ.random(10, 1);
        BasicJ.random(-1, -10);
    }

    /*
     * Test method for 'basicj.BasicJ.round(double)'
     */
    public void testRoundDouble() {
        double d = randDouble();
        assertEquals(BasicJ.round(d), (long) Math.round(d));
    }

    /*
     * Test method for 'basicj.BasicJ.round(float)'
     */
    public void testRoundFloat() {
        float f = randFloat();
        assertEquals(BasicJ.round(f), (int) Math.round(f));
    }

    /*
     * Test method for 'basicj.BasicJ.rint(double)'
     */
    public void testRint() {
        double d = randDouble();
        assertEquals(BasicJ.round(d), (int) Math.round(d));
    }

    /*
     * Test method for 'basicj.BasicJ.sqrt(double)'
     */
    public void testSqrtDouble() {
        double d = randPosDouble();
        assertEquals(Double.doubleToLongBits(BasicJ.sqrt(d)), Double.doubleToLongBits(Math.sqrt(d)));
        
        //check domain
        System.err.println();
        System.err.println("One BasicJ.sqrt(double) warning:");
        BasicJ.sqrt(-1.0);
    }

    /*
     * Test method for 'basicj.BasicJ.sqrt(float)'
     */
    public void testSqrtFloat() {
        float f = randPosFloat();
        assertEquals(Float.floatToIntBits(BasicJ.sqrt(f)), Float.floatToIntBits((float) Math.sqrt(f)));

        //check domain
        System.err.println();
        System.err.println("One BasicJ.sqrt(float) warning:");
        BasicJ.sqrt(-1.0f);
    }

}
