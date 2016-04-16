/*
 * Problem Description:
 * Clarence is a data entry clerk who works at an internet service provider. 
 * His job is to manually enter the IP addresses of all of the ISP's customers into the database. 
 * He does this using a keypad which has the following layout:
 * 1	2	3
 * 4	5	6
 * 7	8	9
 * .	0	
 * The distance between the centre of horizontally or vertically adjacent keys is exactly one centimetre. 
 * For instance, the distance between the centres of 3 and 9 would be two centimetres. 
 * The distance between the centres of 3 and 5 would be sqrt 2cm. 
 * The Pythagoras theorem is sufficient to calculate the distance between any two keys.
 * Clarence, as you might expect from one who works in an ISP, uses a very slow and inefficient system of typing. 
 * He uses a single finger and searches for the key, and then moves his finger to the key, 
 * then presses it, and repeats for all of the digits in the number. 
 * You might know of this style as the "eagle search system" since the finger 
 * searches above the keyboard for the correct key before plunging down for the keypress, like an eagle plunging down for a kill.
 * For example, here is how Clarence would type out the number 7851:
 * 	He starts his finger at 7 and pushes the key.
 * 	He moves his finger to the right 1cm to 8 and pushes the key.
 * 	He moves his finger upwards 1cm to 5 and pushes the key.
 * 	He moves his finger diagonally upwards and left sqrt 2cm to 1 and pushes the key.
 * 	Therefore the total distance that Clarence moved his finger to type in 7851 is 1 + 1 + sqrt 2 which is about 3.41cm.
 * Your task is to write a program that calculates the distance Clarence must move his finger to type in arbitrary IP addresses.
 * 
 * Input:
 * Input is a string that will be in the form ().().().()
 * where each () is an integer in the range 0 - 999. 
 * This represents the IP address that Clarence must type in. An example input might be: 219.45.143.143
 * I would also like to point out that inputs such as 0.42.42.42 or 999.999.999.999 are still valid inputs, 
 * despite the fact that they are invalid IP addresses. So you don't need to include any IP address verification code in your program.
 */

import java.util.*;
import java.lang.Math.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class SlowTypist_Challenge259 {
	
	public static Integer[] xCoord = new Integer[]{1,2,3,1,2,3,1,2,3,1,2}; 
	public static Integer[] yCoord = new Integer[]{1,1,1,2,2,2,3,3,3,4,4}; 
	public static String[] key = new String[]{"1","2","3","4","5","6","7","8","9",".","0"};
	
	
	public static void main(String[] args) throws IOException{
		/*
		 * Read
		 */
		//Variables
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader read = new BufferedReader(input);
		String ipAddress = "";
		char[] ipArray = new char[]{};
		double distance = 0;
		HashMap<String,Integer[]> map = new HashMap<String,Integer[]>();
		
		
		
		//Get ip address
		System.out.println("Input an ip address: ");
		ipAddress = read.readLine();
		
		/*
		 * Think
		 */
		//Create list to simulate the number pad
		//Cycle through all possible key values
		for(int i = 0; i<key.length; i++){
			//Create list from x and y coordinates
			Integer[] list = new Integer[]{xCoord[i],yCoord[i]};
			//Put in map with the numpad number as the key
			map.put(key[i], list);
		}
		//Convert string to character array
		ipArray = ipAddress.toCharArray();
		
		//Go through all values in ip address
		for(int x = 0; x<ipArray.length-1; x ++){
			//Get the distance and add to distance
			distance = distance + calculateDistance(map.get(Character.toString(ipArray[x])),map.get(Character.toString(ipArray[x+1])));
		}
		
		/*
		 * Do
		 */
		//Return the total distance
		System.out.printf("Clarence's total distance is: %.2f cm", distance);
	}
	
	/*
	 * calculateDistance: Calculates the distance between two points using pythagorean's theorem
	 * Input: Integer List value1, Integer List value2
	 * Return: double distance
	 */
	public static double calculateDistance(Integer[] value1, Integer[] value2){
		double distance;
		int xValue1 = value1[0];
		int xValue2 = value2[0];
		int yValue1 = value1[1];
		int yValue2 = value2[1];
		distance = Math.sqrt(Math.pow(Math.abs(xValue1-xValue2),2) + Math.pow(Math.abs(yValue1-yValue2),2));
		return distance;
	}
}
