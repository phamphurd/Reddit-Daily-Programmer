/*
 * Problem Description:
 * create a program that will ask the users name, age, and reddit username. have it tell them the information back, in the format:
 * your name is (blank), you are (blank) years old, and your username is (blank)
 * https://www.reddit.com/r/dailyprogrammer/comments/pih8x/easy_challenge_1/
 */

import java.io.IOException;
import java.util.*;

public class SimpleIO_Challenge1 {

	public static void main(String[] args) {
		//Variables
		Scanner scan = new Scanner(System.in);
		String name;
		int age;
		String username;
		String hold;
		
		/*
		 * Read
		 */
		//Get inputs
		System.out.println("Enter name: ");
		name = scan.nextLine();
		while(true){
			System.out.println("Enter age: ");
			hold = scan.nextLine();
			if(hold.matches("^[0-9]+$")){
				age = Integer.parseInt(hold);
				break;
			}else{
				System.out.println("Wrong data type.");
				continue;
			}
		}
		System.out.println("Enter Readdit Username: ");
		username = scan.nextLine();
		
		/*
		 * Do
		 */
		//Output Results
		System.out.printf("Your name is %s, you are %d years old, and your username is %s.", name, age, username);
		scan.close();
	}

}
