/*
 * Created on Feb 28, 2005 by Ronald Chen
 */
package basicj.samples;

import basicj.BasicJ;

/**
 * AsciiArt
 * 
 * @author Ronald Chen
 */
public class AsciiArt extends BasicJ {

	public AsciiArt() {
		screen(350, 450);
		println("                             ____________");
		println("                            |            |");
		println("                            |            |");
		println("                            |            |");
		println("                            |            |");
		println("                            |            |");
		println("                            |            |");
		println(" _________                  |            |");
		println("|+++++++++\\                 |            |");
		println("|++++++++++|                |            |");
		println("|++|    |++|                |            |");
		println("|++|____|++|   ___     ____ | _____    ____");
		println("|+++++++++<   /+++\\   /++++|||+++++|  /++++|");
		println("|++|    |++| |+|_|+| |+|__  |  |+|   |+| |");
		println("|++|____|++| |+++++|  \\+++\\ |  |+|   |+| |");
		println("|++++++++++| |+| |+|  ___|+|| _|+|_  |+|___");
		println("|+++++++++/  |+| |+| |++++/ ||+++++|  \\++++|");
		println("      ____________          |            |");
		println("     |            |         |            |");
		println("     |            |         |            |");
		println("     |            |         |            |");
		println("     |            |         |            |");
		println("     |            \\_________/            |");
		println("      \\                                 /");
		println("       \\                               /");
		println("        \\                             /");
		println("         \\                           /");
		println("          \\                         /");
		println("           \\_______________________/");
	}
	
	public static void main(String args[]) {
		new AsciiArt();
	}
}
