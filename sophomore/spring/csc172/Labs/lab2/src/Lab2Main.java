/*
 * Borja Rojo
 * 
 * Due February 1, 2015
 * 
 * CSC 172
 * 
 * Lab 3
 */
public class Lab2Main {
	
	public static void main(String[]args){
		
		Lab2List test = new Lab2List();					//Initializing list
		
		test.insert("1");				//Adding Three Items
		test.insert("2");
		test.insert("3");
		
		test.printList();								//Printing list
		
		if(test.lookup("1") == true){	//lookup() test
			System.out.println("It's there");
		}else{
			System.out.println("It's not there");
		}
		
		test.insert("1");				//insert() with lookup test
		
		if(test.isEmpty() == true){						//isEmpty() test 2
			System.out.println("It's Empty!");
		}else{
			System.out.println("It's got stuff");
		}
		
		test.insert("5");					//inserting more things to test delete()
		test.insert("4");				
		
		test.delete("4");	 				//Testing to delete first item
		test.printList(); 								//Printing the list to verify
		test.insert("4");  				//adding back in
		test.printList();
		
		test.delete("3");					//Middle
		test.printList();
		test.insert("3");
		
		test.delete("1");	 			//last
		test.printList();
		test.insert("1");
		
		test.printList();								//Last print of list. the order should be Some, Thrice, MOAH, Another, More
	}
}
