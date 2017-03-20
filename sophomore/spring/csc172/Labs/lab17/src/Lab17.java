/*
 * Borja Rojo
 * CSC 172
 * Lab17
 * Daniel Saltz
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Lab17 {
	
	public static HashMap<Integer, String> mapIS = new HashMap<Integer, String>();
	public static HashMap<String, Integer> mapSI = new HashMap<String, Integer>();
	
	public static void main(String[]args) throws FileNotFoundException{
		Scanner scan = new Scanner(new File("zipcodes.csv"));
		
		/*
		 * Scan order goes:
		 * zipcode, City, ST
		 */
		
		while(scan.hasNext() == true){
			String[] line = scan.nextLine().split(","); //returns a string array with each item from the line
			
			Integer key = Integer.valueOf(line[0]); 	//converts zip string to an Integer
			String val = line[1] + ", " + line[2];		//puts the next two items as the value
			
			mapIS.put(key, val);
		}
		scan.close();//Close the scanener
		
		System.out.print("Hashtable size is ");
		System.out.println(mapIS.size() + "\n");
		
		/*
		 * As discussed in lab, the zip codes are not all correct, as
		 * the ones that start with zero are missing their first zero
		 * in the zipcodes.csv file. Therefore, I will accept any
		 * amount of numbers between 1 and 5
		 */
		
		Scanner user = new Scanner(System.in);
		user.useDelimiter("\\n");
		System.out.println("---City lookup---\n");
		while(true){
			//Query
			System.out.print("Enter a zipcode to look up a city: ");
			String zipString = user.next();
			
			//While there is at least one or more non-digits
			//or there is not between 1 and 5 digits
			while(zipString.matches("[^0-9]+|[0-9]{6,}")){	//regex says this will be true if there are any non numbers, or if there are 6 numbers or more
				System.out.print("Incorrect input. Please enter a proper zipcode: ");
				zipString = user.next();
			}
			Integer zip = Integer.valueOf(zipString);	//Valid int, make zip equal it
			
			//If the key returns null, say it does not correspond
			//else, it does correspond so print the city 
			if(!mapIS.containsKey(zip)){
				System.out.print("That zipcode does not correspond to any city. ");
			}else{
				System.out.println(zip + " is the zipcode for " + mapIS.get(zip) + ". ");
			}
			System.out.print("Would you like to lookup another city? If not, enter quit: ");
			String exit = user.next();
			System.out.println();
			
			if(exit.toLowerCase().compareTo("quit") == 0){
				break;
			}
			
		}
		
		//-------------------------------------------//
		
		//Iterate through the hashmap
		for(Integer zip: mapIS.keySet()){
			if(mapSI.containsKey(mapIS.get(zip))){	//if new map contains A city as a key already
				mapSI.put(mapIS.get(zip), mapSI.get(mapIS.get(zip)) + 1); //put , at that city, the value that city key goes to plus one
			}else{									//else, it doesn't have the key, so make a new entry
				mapSI.put(mapIS.get(zip), 1);
			}
		}
		

		System.out.println("---Frequency lookup---\n");
		while(true){
			//Query
			System.out.print("Enter a city to look up how may zipcodes it has: ");
			String cityString = user.next();
			
			//While there is at least one or more non-digits
			//or there is not between 1 and 5 digits
			while(!mapSI.containsKey(cityString)){
				System.out.print("This city does not exsist. Please enter an exsisting city (City, ST): ");
				cityString = user.next();
			}
			
			//If the key returns null, say it does not correspond
			//else, it does correspond so print the city 
			System.out.println(cityString + " has " + mapSI.get(cityString) + " zipcodes. ");
			System.out.print("Would you like to lookup more? If not, enter quit: ");
			String exit = user.next();
			System.out.println();
			
			if(exit.toLowerCase().compareTo("quit") == 0){
				break;
			}	
		}
		
		
	}
}
