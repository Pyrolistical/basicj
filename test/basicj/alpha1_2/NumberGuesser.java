package basicj.alpha1_2;

import basicj.BasicJ;

public class NumberGuesser extends BasicJ {
    public NumberGuesser() {
        int theNumber = random(1, 100);
        int guess, i;
        String input = "";
        println("Guess the number I am thinking of that is between 1 and 100.");
        while(true) {
            input = input("Guess: ");
            if(!isInt(input))
                println("Please enter a number and try again.");
            else
                break;
        }
        guess = toInt(input);
        for(i = 1; i < 7; i++) {
            if(guess == theNumber) {
                break;
            } else {
                if(guess > theNumber) {
                    println("Too high.");
                } else {
                    println("Too low.");
                }
                input = "";
                while(true) {
                    input = input("Guess again: ");
                    if(!isInt(input))
                        println("Please enter a number and try again.");
                    else
                        break;
                }
                guess = toInt(input);
            }
        }
        if(guess == theNumber) {
            println("You got it!");
        } else {
            println("Sorry, the number was " + theNumber + ".");
        }
    }
    
    public static void main(String args[]) {
        new NumberGuesser();
    }
}
