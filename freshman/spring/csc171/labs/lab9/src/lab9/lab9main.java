
/* 
 * CSC 171
 * 
 * Version 1.0
 * 
 * DON'T COPY - It's the law
 * 
 * Assignment: Lab 9
 * 
 * File Name: lab9.java
 * 
 * Author: Borja Rojo
 * 
 * Date: 2/18/14
 * 
 * Course Number: CSC 171, 76456
 * 
 * Lab Session: Tues/Thurs 2:00-3:15
 * 
 * TA Name: David Sekora
 * 
 * Last Revised: March 2, 2014
 * 
 * Lab Partner: Haotion Li
 */

public class lab9main {
	
	private static String name = "Borja Rojo ";
	private static String address = "40 Woodwalk Lane";
	
	public static void info(){
		System.out.println(name + address);
	}
	
	public static void ruseCruise(String a){
		System.out.printf("Dear %s, You may have already won a million dollars", a);
	}
	
	public static int square(int a){
		a *= a;
		return a;
	}
	
	public static double square(double a){
		a *= a;
		return a;
	}
	
	public static int cube(int a){
		a *= square(a);
		return a;
	}
	
	public static double cube(double a){
		a *= square(a);
		return a;
	}
	
	public static int factorial(int a){
		int c = 1;
		for(int x = a; x != 0; x--){
			c *= x;
		}
		return c;
	}
	
	public static long factorial(long a){
		long c = 1;
		for(long x = a; x != 0; x--){
			c *= x;
		}
		return c;
	}
	
	public static int max(int a, int b){
		if(a > b){
			return a;
		}else{
			return b;
		}
	}
	
	public static int max(int a, int b, int c){
		return max(max(a,b),c);
	}
	
	public static int max(int a, int b, int c, int d){
		return max(max(a,b),max(c,d));
	}
	
	public static void main(String[]args){
		info(); info(); info();
		
		for(int x = 0; x <= 10; x++){
			square(x);
		}
		
		for(int x = 0; x <= 10; x++){
			cube(x);
		}
		
	}

}
