package project1;

import java.util.Scanner;

public class Rational {
	/*This class describes a rational number.
	 * this class will be dealing with doubleegers
	 * because of the nature of the number that
	 * is being dealt with.
	 */
	private double top;
	private double bot;

	/*
	 * Constructor: Because a Rational number represents
	 * a ratio or a fraction, I have each part stored as
	 * top and bot for top and bottom respectively. It
	 * seemed natural to define the number in the constructor
	 * as first top, then bottom.
	 */
	public Rational(double a, double b){
		top = a;
		bot = b;

		//If the bottom is 0, ask for a new value every time
		while(bot == 0){
			System.out.println("Zero cannot be on bottom...Please enter a new number...");
			Scanner keyboard = new Scanner(System.in);
			bot = keyboard.nextDouble();
		}
	}

	//accessors
	public double getTop(){
		return top;
	}

	public double getBot(){
		return bot;
	}

	//mutators, all of which simplify automatically
	public void setTop(double a){
		top = a;
		Simplify();
	}

	public void setBot(double b){
		bot = b;
		Simplify();
	}

	public void setNew(double a, double b){
		top = a; bot = b;
		Simplify();
	}

	//This method returns a string representing the Rational number
	public String toString(){
		String c = "";
		
		//if the bottom is negative, put the negative on top and re-enter the method
		if(bot < 0){
			Rational a = new Rational(-top, -bot);
			c = a.toString();
		}

		//Set c to the top if the bottom is 1
		if(bot == 1){
			c = "" + top;
		}

		// set c to 0 if the top is 0
		if(top == 0){
			c = "0";
		}

		//Last check, and format
		if((bot != 1) && (top != 0) && (bot >= 0)){
			c = (int) top + "/" + (int) bot;
		}

		//If the top and bottom happen to be equal, change whatever c was and make it 1
		if(top == bot){
			c = "" + 1;
		}
		
		if(top == -(bot)){
			c = "" + -1;
		}

		return c;
		
	}

	public int compareTo(Rational a){
		int compare = 0;
		//Check to see if both the top and bottom are equal, or if the negatives of the top and bottom are equal
		if(((top == a.getTop()) && (bot == a.getBot())) || ((-top == a.getTop()) && (-bot == a.getBot()))){
			compare = 1;
		}else{
			compare = -1;
		}
		return compare;
	}

	public boolean equals(Rational a){
		int compare = 0;
		//Check to see if both the top and bottom are equal, or if the negatives of the top and bottom are equal
		if(((top == a.getTop()) && (bot == a.getBot())) || ((-top == a.getTop()) && (-bot == a.getBot()))){
			compare = 1;
		}else{
			compare = -1;
		}
		
		if(compare > 0){
			return true;
		}else{
			return false;
		}
	}

	/*
	 * I used to have a for loop in each method to simplify that looked like:
	 * 
	 * //for loop for simplifying. checks smallest common denominator systematically
				for(int x = 2; x <= (added.getBot()); x++){
					//if it works, use mutators to change 'added' Rational numbers, and then break
					if(((added.getTop() % x) == 0) && ((added.getBot() % x) == 0)){
						added.setTop(added.getTop() / x); added.setBot(added.getBot() / x);
						break;
					}
				}

		So i Made the method below
	 */

	public void Simplify(){

		for(int x = (int) bot; x > 1; x--){
			if(((top % x) == 0) && ((bot % x) == 0)){
				top /= x; bot /= x;
			}
		}

	}

	//THE FOLLOWING METHODS ARE STATIC

	/*
	 * This will add rational numbers. First, I'm going to expand the 
	 * rational numbers so that they have a common denominator. Then,
	 * to simplify it, I will use a for-loop for as many iterations as
	 * half the greatest common denominator and apply modulo opperations
	 * to check if there is a smaller common denominator. If there is,
	 * I can use that information to simplify the rational number
	 */

	//Rational a + Rational b == Rational.sub(Rational a, Rational b)
	public static Rational add(Rational a, Rational b){
		
		//New rational number that is the versions of a & b with GCD implemented
		Rational newA = new Rational((a.getTop() * b.getBot()), (a.getBot() * b.getBot()));
		Rational newB = new Rational((b.getTop() * a.getBot()), (b.getBot() * a.getBot()));
		//Number representing the added versions of the parameters with GCD implemented
		Rational added = new Rational((newA.getTop() + newB.getTop()), newB.getBot());
		
		added.Simplify();
		
		//The now simplified 'added' variable is returned
		return added;
	}

	/*
	 * This uses the same approach as the add method, the only 
	 * difference is that instead of Rational a + Rational b, its
	 * Rational a - Rational b.
	 */

	//Rational a - Rational b == Rational.sub(Rational a, Rational b)
	public static Rational sub(Rational a, Rational b){
		//New rational number that is the versions of a & b with GCD implemented
		Rational newA = new Rational((a.getTop() * b.getBot()), (a.getBot() * b.getBot()));
		Rational newB = new Rational((b.getTop() * a.getBot()), (b.getBot() * a.getBot()));

		//Number representing the added versions of the parameters with GCD implemented
		Rational subtracted = new Rational((newA.getTop() - newB.getTop()), newB.getBot());

		subtracted.Simplify();

		//The now simplified 'added' variable is returned
		return subtracted;
	}

	/*
	 * This method evaluates the product of two Rational numbers. Because
	 * evaluating product of rational numbers is equal to muliplying the
	 * tops and dividing by the product of the bottoms, that's what this
	 * method will do. They will also be simplified using the modulo concept
	 * in my 'add' and 'sub' methods
	 */

	//Rational a * Rational b == Rational.mult(Rational a, Rational b)
	public static Rational mult(Rational a, Rational b){
		//multiplied version of a and b product
		Rational multiplied = new Rational((a.getTop() * b.getTop()), (a.getBot() * b.getBot()));
		multiplied.Simplify();

		//return simplified multiplied
		return multiplied;
	}

	/*
	 * This method evaluates the quotient of two Rational numbers.
	 * Because multiplying by a reciprocal is the same as division,
	 * this method will use this classes 'mult' method
	 */

	//Rational a / Rational b == Rational.div(Rational a, Rational b)
	public static Rational div(Rational a, Rational b){
		//Rational c is the reciprocal of Rational b
		Rational c = new Rational(b.getBot(), b.getTop());

		Rational divided = mult(a, c);

		divided.Simplify();

		//return simplified divided
		return divided;
	}

	//The following methods are dynamic

	/*
	 * This method adds one Rational number (parameter) to 
	 * the Rational number that this method is of
	 */
	public Rational addition(Rational a){
		//Implement GCD into the tops and subtract
		Rational added = new Rational(((top * a.getBot()) + (a.getTop() * bot)), (bot * a.getBot()));
		added.Simplify();
		return added;			
	}

	/*
	 * This method subtracts one Rational number (parameter) from
	 * the Rational number that this method is of
	 */
	public Rational subtraction(Rational a){
		//Implement GCD into the tops and subtract
		Rational subtracted = new Rational(((top * a.getBot()) - (a.getTop() * bot)), (bot * a.getBot()));
		subtracted.Simplify();
		return subtracted;
	}

	/*
	 * This method multiplies a Rational number (parameter) with
	 * the Rational number that this method is of
	 */
	public Rational multiplication(Rational a){
		Rational b = new Rational((top * a.getTop()), (bot * a.getBot()));	
		b.Simplify();
		return b;
	}

	/*
	 * This method divides a Rational number (parameter) by
	 * the Rational number that this method is of
	 */
	public Rational division(Rational a){
		Rational b = new Rational((top * a.getBot()), (bot * a.getTop()));
		b.Simplify();
		return b;
	}



















}
