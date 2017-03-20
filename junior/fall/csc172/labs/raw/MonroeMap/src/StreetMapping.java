/*
 * Borja Rojo
 * Street Mapping
 * Project 4
 */
import java.io.FileNotFoundException;
import java.util.Scanner;


public class StreetMapping {

	public static void main(String[] args) throws FileNotFoundException {

		Graph monroe = Graph.monroeFile("monroe-county.tab");
		
		Scanner input = new Scanner(System.in);
		
		String querry1 = "";
		String[] querry2;
		//If the next line is exit
		System.out.println("Check");
		while(querry1.compareTo("exit") != 0){
			String v1, v2;
			System.out.print("What path would you like to calculate? (Intersection 1, Interesection 2): ");
			querry2 = input.nextLine().split(" "); v1 = querry2[0]; v2 = querry2[1];
			System.out.println();
			
			monroe.printShortestPath(v1, v2);
			
			
			System.out.print("\nPrint another path? (exit to close, anything else to continue): ");
			querry1 = input.nextLine().toLowerCase();
		}
		
		input.close();
		
	}

}
