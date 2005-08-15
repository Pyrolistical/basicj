package basicj.alpha1_2;

import basicj.BasicJ;

public class WhatIsYourName extends BasicJ {

    public WhatIsYourName() {
        String name = "";
        while(true) {
            name = input("What is your name: ");
            if(name.equals("")) {
                println("Please enter your name!");
            } else {
                break;
            }
        }
        println("Hello " + name + "!");
    }
    
    public static void main(String args[]) {
        new WhatIsYourName();
    }
}
