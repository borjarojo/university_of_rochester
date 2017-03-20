
import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class Lab14 extends JPanel{
	
	//All components
	
	//Labels
	private JLabel fieldColumn = new JLabel();
	private JLabel firstName = new JLabel("First Name:");
	private JLabel lastName = new JLabel("Last Name:");
	private JLabel addressLabel = new JLabel("Address:");
	private JLabel cityName = new JLabel("City:");
	private JLabel stateIn = new JLabel("State");
	private JLabel zipCode = new JLabel("Zip-Code");
	private JLabel emailAdress = new JLabel("Email:");
	
	//TextBoxes
	private JLabel textBoxes = new JLabel();
	private JTextField firstText = new JTextField(25);
	private JTextField lastText = new JTextField(25);
	private JTextField addressText = new JTextField(25);
	private JTextField cityText = new JTextField(25);
	private JTextField stateText = new JTextField(25);
	private JTextField zipText = new JTextField(25);
	private JTextField emailText = new JTextField(25);
	
	//ExampleLabel
	private JLabel exampleLabel = new JLabel("Examples");
	private JLabel firstExample = new JLabel("Brian, Charles, Xxxxx");
	private JLabel lastExample = new JLabel("Richard, O'Niel, Black-Smith");
	private JLabel addressExample = new JLabel("400 Maplewood Road, ## Xxxxxx Xxxx");
	private JLabel cityExample = new JLabel("New York, Paris, Mexico City");
	private JLabel stateExample = new JLabel("XX");
	private JLabel zipExmaple = new JLabel("##### or #####-####");
	private JLabel emailExample = new JLabel("john_appleseed@aol.com, [No dots(.) at the beginning or end of the username]");
	
	
	
	
	public Lab14(){
		setLayout(new GridLayout(8,3));
		//1st row
			//Labels
			
			
			
	}

	/*
		A method that querries the user. What is querried
		is entered as a String parameter in the method (it's
		used just a label)
	 */
	
	
	public static String querry(String querry){

		String info;
		Scanner input = new Scanner(System.in);

		System.out.printf("Please enter your " + querry +": ");

		info = input.nextLine();

		return info;
	}
	

	//Checks the first name parameter
	public static boolean checkFirstName(String first){

		//RegEx: One uppercase letter followed by any number of lowercase letters
		String regex = "[A-Z][a-z]*";

		//Check:	if correct, return true, 
		//			else, print error message and return false
		if(first.matches(regex)){
			return true;
		}else{
			System.out.println("Error:");

			//Check for uppercase
			if(!first.startsWith("[A-Z]")){
				System.out.println("Please enter a name with an uppercase letter at the start");
			}

			//Check for any non-wordcharacters
			if(first.matches("[^a-zA-Z]")){
				System.out.println("First names cannot have special characters");
			}

			return false;
		}
	}

	//Checks the last name parameter
	public static boolean checkLastName(String last){

		//RegEx: One uppercase letter followed by lowercase letters.
		//May have hyphens or apostrophees
		
		//Uppercase letter followed by any number of lowercase letters, or
		//Two words of One uppercase followed by one lowercase together with a hyphen, or
		//One upper, then apostrophe, then one upper folloed by as many lowers needed.
		String regex = "[A-Z][a-z]*|[A-Z][a-z]*-[A-Z][a-z]*|[A-Z]'[A-Z][a-z]*";

		if(last.matches(regex)){
			return true;
		}else{
			System.out.println("Error:");

			//Check to see strange characters except for hyphen and 
			if(last.matches("[^a-zA-Z]")){
				System.out.println("Last names cannot have special characters");
			}else{
				//Only other option is an error in format, being that
				//as many letters can be entered as wanted
				System.out.println("Please enter in correct format:\nXxxxxxxxx or X'Xxxxxxx or Xxxx-Xxxx");

			}

			return false;
		}
	}

	//Checks street address
	public static boolean checkStreetAddress(String address){

		//RegEx; A number followed by at least one or 
		//more words starting with uppercase (though it
		//should definitely be two because of the nature
		//in street names)

		String regex = "[0-9]* [A-Z][a-z]* [A-Z][a-z]*";

		//check the input string using the Last name RegEx parameter
		if(address.matches(regex)){
			return true;
		}else{
			System.out.println("Error:");

			//checks if there are any non-word, non-digit characters
			if(address.matches("[^ a-zA-Z0-9]")){
				System.out.println("Street addresses cannot have special characters");
			}else{
				System.out.println("Please enter in correct format: ## Xxxxxxx Xxxxx");
			}

			return false;
		}
	}

	//Check city name
	public static boolean checkCityName(String city){

		//RegEx: Any number of words of any length
		//Must have uppercase beginnings and spaced by a white space

		city += " ";					//adds a space to the end of the string so it works with the RegEx

		String regex = "([A-Z][a-z]* )*";	//The reason this works is because it
													//will allow either one name or many 
													//names wih spaces after them to work

		if(city.matches(regex)){
			return true;
		}else{
			System.out.println("Error:");

			//Check for special characters
			if(city.matches("[^a-zA-Z]")){
				System.out.println("Cities cannot have special characters");
			}else{
				System.out.println("Please enter in correct format: Xxxxxxx Xxxxxxxx Xxxxx");
			}

			return false;
		}
	}

	//Check state initials
	public static boolean checkStateLetters(String state){

		//RegEx: Only one set of two uppercase letters

		String regex = "[A-Z]{2}";	//Any uppercase exactly twice
									//Will not use database incase of fake states,
									//though normally one would be used to check

		if(state.matches(regex)){
			return true;
		}else{
			//Simple enough to explain that any error should be correctable with little info
			System.out.println("Error:\nPlease enter in correct format: XX");

			return false;
		}
	}

	//Check zip
	public static boolean checkZipCode(String zip){

		//RegEx; Either 5 digits or 5 digits connected to 4 digits with a hyphen
		//#####-####

		String regex = "[0-9]{5}|[0-9]{5}-[0-9]{4}";

		if(zip.matches(regex)){
			return true;
		}else{
			System.out.println("Error:");

			//Check if there are any letters
			if(zip.matches("[^0-9-]")){
				System.out.println("Zip-codes cannot have letters");
			}

			//Check for format: if may have numbers an hyphens, but be out of whack
			//i.e. if there are only letters and hyphens, then there must be a format issue
			if(!zip.matches("[^0-9-]")){
				System.out.println("Please enter in correct format:\n##### for regular zip\n#####-#### for zip+4");

			}

			return false;
		}
	}

	public static boolean checkEmail(String email){

		//RegEx:The username can only have letters, numbers, undercores and dots.  
		//		The domain can have only letters and dots

		String regex = "[a-zA-Z0-9_.]*@[a-zA-Z.]*";

		//Check for username using the indexOf method to find where the @ is
		//and use that location in juction with codePointBefore method to check
		//if there is a dot before the @ sign
		if(email.matches(regex) && !email.startsWith(".") && (email.codePointBefore(email.indexOf("@")) != 46)){
			return true;
		}else{
			System.out.println("Error:");

			//Check for special characters
			if(email.matches("[^a-zA-Z0-9_.@]")){
				System.out.println("Emails cannot have special characters other then dots (.), underscores (_), and 'at' signs (@)");
			}

			//
			if(email.startsWith(".")||(email.codePointBefore(email.indexOf("@")) != 46)){
				System.out.println("Email usernames cannot start or end in dots (.)");
			}

			return false;
		}
	}

	public static void main(String[]args){

		//Variables to be saved
			String first = "";	//Uppercase followed by lowers: Xxxxx
			String last = "";	//Uppercase followed by lowers: Xxxxx
			String address = "";	//Two numbers followed by words that start with an uppercase:
							//## Xxxxxx Xxxxx
			String city = "";	//Words that start with an uppercase: Xxxxxx Xxxx Xxxxxx
			String state = "";	//Two uppercase letters, and only one
							//Only XX with pass, not XXXXXXXX
			String zip = "";		//Only two formats: ##### or #####-#### as zip+4
			String email = "";

		//Querry and check section

			System.out.println("This program demonstrates RegEx checking using the\nString method 'matches()'\n");

			//Check ask and check first name
			do{
				System.out.println("---------------------------------------------------------------------------------------------------");
				first = querry("first name");
			}while(!checkFirstName(first));

			//Last name
			do{
				System.out.println("---------------------------------------------------------------------------------------------------");
				last = querry("last name");
			}while(!checkLastName(last));

			//Address
			do{
				System.out.println("---------------------------------------------------------------------------------------------------");
				address = querry("address");
			}while(!checkStreetAddress(address));

			//City
			do{
				System.out.println("---------------------------------------------------------------------------------------------------");
				city = querry("city");
			}while(!checkCityName(city));

			//State
			do{
				System.out.println("---------------------------------------------------------------------------------------------------");
				state = querry("state (NY)");
			}while(!checkStateLetters(state));

			//Zip
			do{
				System.out.println("---------------------------------------------------------------------------------------------------");
				zip = querry("zip code (regular or zip+4)");
			}while(!checkZipCode(zip));

			//Email
			do{
				System.out.println("---------------------------------------------------------------------------------------------------");
				email = querry("email");
			}while(!checkEmail(email));
			
			//Display final information
			System.out.println("---------------------------------------------------------------------------------------------------");
			System.out.println("Final information\n");
			System.out.printf("First Name: %s\n", first);
			System.out.printf("Last Name: %s\n", last);
			System.out.printf("Address: %s\n", address);
			System.out.printf("City: %s\n", city);
			System.out.printf("State: %s\n", state);
			System.out.printf("Zip-Code: %s\n", zip);
			System.out.printf("Email: %s\n", email);



	}
}
