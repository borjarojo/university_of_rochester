import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab16Main {
	
	
	public static void main(String[]args) throws FileNotFoundException{
		Hash test = new Hash(13);
		
		//Initial 5 inserts
		test.insert("Jacob");
		test.insert("Adam");
		test.insert("Roy");
		test.insert("Ian");
		test.insert("Rick");
		
		//print of hash table
		System.out.println("Printing Hash Table");
		test.print();
		System.out.println();
		
		//print for size
		System.out.println("Printing Size");
		System.out.println(test.getSize());
		System.out.println();
		//Printing of unique items
		System.out.println("Printing Unique Item Count");
		System.out.println(test.getCount());
		System.out.println();
		//print of load factor
		System.out.println("Printing Load Factor");
		System.out.println(test.getLoad());
		System.out.println();
		
		//Inserts for hash table rehash
		test.insert("Murray");
		test.insert("Chris");
		test.insert("Steve");
		test.insert("Nick");
		test.insert("Peter");
		test.insert("John");
		test.insert("Joseph");
		test.insert("Greg");
		test.insert("Ben");
		test.insert("Josh");
		
		//printing new table
		System.out.println("Printing Hash Table");
		test.print();
		System.out.println();
		
		//size test for the rehash
		System.out.println("Printing Size");
		System.out.println(test.getSize());
		System.out.println();
		
		//Reading the 10 paragraphs of lorem ipsum
		Hash lipsum = new Hash(13);
		Scanner scan = new Scanner(new File("lipsum"));
		int count = 0;
		//go while there is something to scan
		while(scan.hasNext() == true){
			String s = scan.next();			//Scan
			s = s.replaceAll("[\\W]", "");	//I used a regex to filter punctuation
			count++;						//Track how many words are coming in
			lipsum.insert(s);				//insert clean word
		}
		
		//specified prints
		System.out.println("Printing Hash Table");
		lipsum.print();
		System.out.println();
		System.out.println("Total amount of unique words: " + lipsum.getCount());
		System.out.println("Total amount on words read in: " + count);
		System.out.println("Final size of my Hash Table: " + lipsum.getSize());
	}
}
