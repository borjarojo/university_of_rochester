/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */
import java.math.BigInteger;

public class MainTest {
	
	//Returns x^y
	public static BigInteger cAssign(int x, int y){
		BigInteger k = new BigInteger(new Integer(x).toString());
		
		return k.pow(y);
	}
	
	//return x!
	public static BigInteger cPerm(int x){
		BigInteger n = new BigInteger(new Integer(1).toString());
		
		for(int i = (x); i > 0; i--){
			n = n.multiply(new BigInteger(new Integer(i).toString()));
		}
		
		return n;
	}
	
	//returns (x!)/(x-y)! ; n > m
	public static BigInteger orderSelect(int x, int y){
		return cPerm(x).divide(cPerm(x-y));
	}
	
	//returns n!/((n-m)!*m!)
	public static BigInteger unorderSelect(int x, int y){
		return cPerm(x).divide(cPerm(x-y)).divide(cPerm(y));
	}
	
	public static void fullTest(){
		oneTest();
		twoTest();
		threeTest();
		fourTest();
	}
	
	public static void oneTest(){
		System.out.println("-Counting Assignments-\n");
		
		System.out.print("1.1 How many ways can we paint three houses in any of four colors?: ");
		System.out.println(cAssign(4, 3).toString());
		
		System.out.print("1.2 Suppose a computer password consists of eight to ten letters and/or digits. How many "
				+ "different passwords are there?\nRemember that an upper-case letter is "
				+ "different from a lower- case one.: ");
		System.out.println(cAssign(62, 8).add(cAssign(62,9)).add(cAssign(62,10)).toString());
	}
	
	public static void twoTest(){
		System.out.println("\n-Counting Permutations-\n");
		
		System.out.print("2.1 If we have 9 players for a baseball team, how many possible batting orders are there?: ");
		System.out.println(cPerm(9).toString());
	}
	
	public static void threeTest(){
		System.out.println("\n-Ordered Selections-\n");
		
		System.out.print("3.1 In a class of 200 students, we wish to elect a President, Vice President, Secretary and Treasurer. "
				+ "In haw many ways can these four officers be selected?: ");
		System.out.println(orderSelect(200, 4));
		
		System.out.print("3.2 How many ways are there to form a sequence of m letters out of the 26 letters, "
				+ "if no letter is allowed to appera more than once for (a) m=3, (b) m=5.: ");
		System.out.println("a) " + orderSelect(26,3).toString() + " b) " + orderSelect(26, 5).toString());
	}
	
	public static void fourTest(){
		System.out.println("\n-Unordered Selections-\n");
		
		System.out.print("4.1 In poker, each player is dealt five cards from a 52 card deck."
				+ "How many different possible hands are there?: ");
		System.out.println(unorderSelect(52, 5).toString());
	}
	
	public static void main(String[]args){
		fullTest();
	}
}
