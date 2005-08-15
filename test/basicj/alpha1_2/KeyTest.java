package basicj.alpha1_2;

import basicj.BasicJ;

public class KeyTest extends BasicJ {
    public KeyTest() {
        autoFlush(0);
        while(true) {
            print((char) keypressed());
            flush();
        }
    }
    
    public static void main(String args[]) {
        new KeyTest();
    }
}
