package project1;

public class NewMath {
	public static void main(String[]args){
		//Arithmetic test for Rational and Complex numbers using the project examples
		
			//Rational arithmetic
			System.out.println("Testing arithmatic for Rational numbers using static methods\n");
			Rational a = new Rational(1,2);
			Rational b = new Rational(1,3);
				
				System.out.println("Static methods");
				// 1/2 + 1/3 = 5/6
				Rational c = Rational.add(a,b);
				System.out.println(a.toString() + " + " + b.toString() + " = " + c.toString());
				
				// 8/9 + 1/9 = 1
				a.setNew(8, 9); b.setNew(1, 9);
				c = Rational.add(a, b);
				System.out.println(a.toString() + " + " + b.toString() + " = " + c.toString());
				
				// 13/17 * 51/ 64 = 39/64
				a.setNew(13, 17); b.setNew(51, 64);
				c = Rational.mult(a, b);
				System.out.println(a.toString() + " * " + b.toString() + " = " + c.toString());
				
				// 1/3 - 1/3 = 0
				a.setNew(1, 3); b.setNew(1, 3);
				c = Rational.sub(a, b);
				System.out.println(a.toString() + " - " + b.toString() + " = " + c.toString());
				
				// 1/3 / (-1/4) = -4/3
				b.setNew(-1, 4);
				c = Rational.div(a, b);
				System.out.println(a.toString() + " / " + b.toString() + " = " + c.toString());
				
			//Complex arithmetic
			System.out.println("\nTesting arithmetic for Complex numbers using static methods\n");
			Complex x = new Complex(5, 9);
			Complex y = new Complex(-2, 2);
			
				// (5 + 9i) + (-2 + 2i) = 3+ 11i
				Complex z = Complex.add(x, y);
				System.out.println(x.toString() + " + " + y.toString() + " = " + z.toString());
				
				// (5 + 9i) - (-2 + 2i) = 3+ 11i
				z = Complex.sub(x, y);
				System.out.println(x.toString() + " - " + y.toString() + " = " + z.toString());
				
				// (5 + 9i) * (-2 + 2i) = 3+ 11i
				z = Complex.mult(x, y);
				System.out.println(x.toString() + " * " + y.toString() + " = " + z.toString());
				
				// (5 + 9i) / (-2 + 2i) = 3+ 11i
				z = Complex.div(x, y);
				System.out.println(x.toString() + " / " + y.toString() + " = " + z.toString());
				
			//Rational arithmetic using dynamic methods
			System.out.println("\nTesting arithmetic for Rational numbers using dynamic methods\n");
				
				//Dynamic addition
				a.setNew(1, 2); b.setNew(1, 3); 
				System.out.print(a.toString());
				System.out.println(" + " + b.toString() + " = " + (a.addition(b)).toString());
				
				//Dynamic addition
				a.setNew(8, 9); b.setNew(1, 9);
				System.out.print(a.toString());
				System.out.println(" + " + b.toString() + " = " + (a.addition(b)).toString());
				
				//Dynamic multiplication
				a.setNew(13, 17); b.setNew(51, 64);
				System.out.print(a.toString());
				System.out.println(" * " + b.toString() + " = " + (a.multiplication(b)).toString());
				
				//Dynamic subtraction
				a.setNew(1, 3); b.setNew(1, 3);
				System.out.print(a.toString());
				System.out.println(" - " + b.toString() + " = " + (a.subtraction(b)).toString());
				
				//Dynamic division
				a.setNew(1, 3); b.setNew(-1, 4);
				System.out.print(a.toString());
				System.out.println(" / " + b.toString() + " = " + (a.division(b)).toString());
				
			//Complex Arithmetic using dynamic methods
			System.out.println("\nTesting arithmetic for Complex numbers using dynamic methods\n");
			
				x.setNew(5, 9); y.setNew(-2, 2);
				
				//Dynamic addition
				System.out.println(x.toString() + " + " + y.toString() + " = " + (x.addition(y)).toString());
				
				//Dynamic subtraction
				System.out.println(x.toString() + " - " + y.toString() + " = " + (x.subtraction(y)).toString());
				
				//Dynamic multiplication
				System.out.println(x.toString() + " * " + y.toString() + " = " + (x.multiplication(y)).toString());
				
				//Dynamic division
				System.out.println(x.toString() + " / " + y.toString() + " = " + (x.division(y)).toString());
				
			//Rational equals and compareTo methods demo
			System.out.println("\nTesting Rational equals and compareTo methods. The code uses the methods.\n");
				a.setNew(1, -4); b.setNew(-13, 52);
				if(a.compareTo(b) == 1){
					System.out.println(a.toString() + " is equal to " + b.toString());
				}else{
					System.out.println(a.toString() + " is not equal to " + b.toString());
				}
				
				if(a.equals(b) == true){
					System.out.println(a.toString() + " is equal to " + b.toString());
				}else{
					System.out.println(a.toString() + " is not equal to " + b.toString());
				}
				
			//Complex radius and angle methods demo
			System.out.println("\nTesting Complex radius and angle methods. The code uses the methods.\n");
				x.setNew(5, 9);
				System.out.println(x.radius() + " units");
				System.out.println(x.angle() + " radians");
			
		/*i********************************************************************************************************/
				
		//TESTING EACH METHOD FOR DEGENERATE CASES
			System.out.println("\nTesting for Degenerate Cases");
			//Testing Rational Methods
				System.out.println("\tRational Methods");
				//Testing Rational arithmetic methods
					System.out.println("\t\tArithmetic Methods");
					//Testing Rational Static arithmetic methods
						System.out.println("\t\t\tStatic Methods");
						a.setNew(16,51); b.setNew(2,3); 
						System.out.println("\t\t\t\tX = "+a.toString()+"\n\t\t\t\tY = "+b.toString()+"\n\t\t\t\tX _ Y = Z");
						
						//add
						System.out.println("\t\t\t\t"+a.toString()+" + "+b.toString()+" = " + Rational.add(a, b));
						
						//sub
						System.out.println("\t\t\t\t"+a.toString()+" - "+b.toString()+" = " + Rational.sub(a, b));
						System.out.println("\t\t\t\t"+b.toString()+" - "+a.toString()+" = " + Rational.sub(b, a));
						
						//mult
						System.out.println("\t\t\t\t"+a.toString()+" * "+b.toString()+" = " + Rational.mult(a, b));
						
						//div
						System.out.println("\t\t\t\t"+a.toString()+" / "+b.toString()+" = " + Rational.div(a, b));
						System.out.println("\t\t\t\t"+b.toString()+" / "+a.toString()+" = " + Rational.div(b, a));
					
					//Testing Rational dynamic arithmetic methods
						System.out.println("\t\t\tDynamic Methods");
						a.setNew(16,51); b.setNew(2,3); 
						System.out.println("\t\t\t\tX = "+a.toString()+"\n\t\t\t\tY = "+b.toString()+"\n\t\t\t\tX _ Y = Z");
						
						
						//add
						System.out.println("\t\t\t\t"+a.toString()+" + "+b.toString()+" = " + a.addition(b));
						a.setNew(16,51);
						
						//sub
						System.out.println("\t\t\t\t"+a.toString()+" - "+b.toString()+" = " + a.subtraction(b));
						a.setNew(16,51);
						System.out.println("\t\t\t\t"+a.toString()+" - "+b.toString()+" = " + a.subtraction(b));
						b.setNew(2,3);
						
						//mult
						System.out.println("\t\t\t\t"+a.toString()+" * "+b.toString()+" = " + a.multiplication(b));
						a.setNew(16,51);
						
						//div
						System.out.println("\t\t\t\t"+a.toString()+" / "+b.toString()+" = " + a.division(b));
						a.setNew(16,51);
						System.out.println("\t\t\t\t"+a.toString()+" / "+b.toString()+" = " + a.division(b));
						a.setNew(16,51);
				
				//Testing equals and compareTo method
				System.out.println("\t\tTesting Rational equals and compareTo methods");
						//Equals
						System.out.println("\t\t\tequals Methods");
						a.setNew(16,51); b.setNew(2,3); 
						
						if(a.equals(b) == true){
							System.out.println("\t\t\t\t"+a.toString() + " is equal to " + b.toString());
						}else{
							System.out.println("\t\t\t\t"+a.toString() + " is not equal to " + b.toString());
						}
						
						//compareTo
						System.out.println("\t\t\tcompareTo Methods");
						
						if(a.compareTo(b) == 1){
							System.out.println("\t\t\t\t"+a.toString() + " is equal to " + b.toString());
						}else{
							System.out.println("\t\t\t\t"+a.toString() + " is not equal to " + b.toString());
						}
						
			//Testing Complex Methods
			System.out.println("\tComplex Methods");
				//Testing Complex arithmetic methods
				System.out.println("\t\tArithmetic Methods");
					//Testing Complex Static arithmetic methods
					System.out.println("\t\t\tStatic Methods");
					x.setNew(4, -7); y.setNew(2, 9);
					System.out.println("\t\t\t\tX = "+x.toString()+"\n\t\t\t\tY = "+y.toString()+"\n\t\t\t\tX _ Y = Z");
					
					//add
					System.out.println("\t\t\t\t"+x.toString()+" + "+y.toString()+" = " + Complex.add(x, y));
					
					//sub
					System.out.println("\t\t\t\t"+x.toString()+" - "+y.toString()+" = " + Complex.sub(x, y));
					x.setNew(4, -7);
					System.out.println("\t\t\t\t"+y.toString()+" - "+x.toString()+" = " + Complex.sub(y, x));
					x.setNew(4, -7);
					
					//mult
					System.out.println("\t\t\t\t"+x.toString()+" * "+y.toString()+" = " + Complex.mult(x, y));
					
					//div
					System.out.println("\t\t\t\t"+x.toString()+" / "+y.toString()+" = " + Complex.div(x, y));
					x.setNew(4, -7);
					System.out.println("\t\t\t\t"+y.toString()+" / "+x.toString()+" = " + Complex.div(y, x));
					x.setNew(4, -7);
					
					//Testing Rational dynamic arithmetic methods
					System.out.println("\t\t\tDynamic Methods");
					x.setNew(4, -7); y.setNew(2, 9); 
					System.out.println("\t\t\t\tX = "+x.toString()+"\n\t\t\t\tY = "+y.toString()+"\n\t\t\t\tX _ Y = Z");
					
					
					//add
					System.out.println("\t\t\t\t"+x.toString()+" + "+y.toString()+" = " + x.addition(y));
					x.setNew(4, -7);
					
					//sub
					System.out.println("\t\t\t\t"+x.toString()+" - "+y.toString()+" = " + x.subtraction(y));
					x.setNew(4, -7);
					System.out.println("\t\t\t\t"+y.toString()+" - "+x.toString()+" = " + y.subtraction(x));
					y.setNew(2, 9);
					
					//mult
					System.out.println("\t\t\t\t"+x.toString()+" * "+y.toString()+" = " + x.multiplication(y));
					x.setNew(4, -7);
					
					//div
					System.out.println("\t\t\t\t"+x.toString()+" / "+y.toString()+" = " + x.division(y));
					x.setNew(4, -7);
					System.out.println("\t\t\t\t"+y.toString()+" / "+x.toString()+" = " + y.division(x));
			
			//Complex radius and angle methods demo
					System.out.println("\t\tTesting Complex radius and angle methods. The code uses the methods.");
						x.setNew(5, 9);
					
						//radius
						System.out.println("\t\t\tTesting radius method");
						System.out.println("\t\t\t\t" +x.radius() + " units");
						
						//angle
						System.out.println("\t\t\tTesting angle method");
						System.out.println("\t\t\t\t"+x.angle() + " radians");
						
					
			
						
						
						
	}
}
