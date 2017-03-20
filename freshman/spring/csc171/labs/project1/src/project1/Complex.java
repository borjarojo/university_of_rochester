package project1;

public class Complex {
	/*This class describes a complex number
	 *It holds the real and imaginary part and treats
	 *them appropriately when arithmetical opperations
	 *are done on the Complex class
	 */
	private double real;
	private double imag;

	/*Constructor; The first numbered entered is the real part,
	 *where the second number is the imaginary part. This form
	 *makes more sense because it models how complex numbers 
	 *are writen standardly
	 */
	public Complex(double r, double i){
		real = r;
		imag = i;
	}

	//Accessors for each variable
	public double getReal(){
		return real;
	}

	public double getImag(){
		return imag;
	}

	//Mutators; USED IN DIVISION METHOD
	public void setReal(double a){
		real = a;
	}

	public void setImag(double a){
		imag = a;
	}

	public void setNew(double a, double b){
		real = a;
		imag = b;
	}

	public String toString(){
		String a = "";
		String b = "";
		String c = "";

		//If there are no decimals, take away the zeros. If there are, leave them.
		if(real == (int) real){
			a = "" + (int) real;
		}else{
			a = "" + real;
		}

		if(imag == (int) imag){
			b = (int) imag + "i";
		}else{
			b = imag + "i";
		}


		//check for zeros
		if((real == 0) || (imag == 0)){
			if(real == 0){
				c = b;
			}
			if(imag == 0){
				c = a;
			}
			if((real == 0) && (imag ==0)){
				c = "" + 0;
			}
		}else{
			if(imag < 0){
				c = "(" + a + b + ")";
			}else{
				c = "(" + a + "+" + b + ")";
			}
		}

		return c;
	}
	
	//Returns a double of radius
	public double radius(){
		double a = Math.sqrt(Math.pow(real, 2) + Math.pow(imag, 2));
		return a;
	}

	//Returns the Complex angle
	public double angle(){
		double a = Math.tan(imag/real);
		return a;
	}
	
	//Static Methods

	/*
	 * This method adds the two Complex numbers together and
	 * returns a Complex number. It adds together the real parts
	 * together seperately from adding the imaginary parts of each
	 * inputed Complex number, and uses each in their correspoding
	 * parts inside of the constructor to make a new Complex number.
	 * The new Complex number is then returned.
	 */

	//Complex a + Complex b == Complex.add(Complex a, Complex b)
	public static Complex add(Complex a, Complex b){
		double newReal;
		double newImag;

		newReal = a.getReal() + b.getReal();
		newImag = a.getImag() + b.getImag();

		Complex newComplex = new Complex(newReal, newImag);

		return newComplex;
	}

	/*
	 * Like the addition method, the subtraction method opperated
	 * on each component of the complex number seperately, creates
	 * a new Complex number out of those results, and returns it.
	 */

	//Complex a - Complex b == Complex.sub(Complex a, Complex b)
	public static Complex sub(Complex a, Complex b){
		double newReal;
		double newImag;

		newReal = a.getReal() - b.getReal();
		newImag = a.getImag() + b.getImag();

		Complex newComplex = new Complex(newReal, newImag);

		return newComplex;
	}

	/*The multiplication function uses the same 'Divide and
	 * Conquer' idea from the addition and subraction methods.
	 * First, the real sections are multiplied together and saved.
	 * Then, the two parts of real-imaginary multiplication are
	 * elavuated. Then the imaginary part is concidered. They are
	 * then each combined with their corresponding real or 
	 * imaginary partner, put into a constructor, and the method
	 * returns a new Complex number.
	 */

	//Complex a * Complex b == Complex.mult(Complex a, Complex b)
	public static Complex mult(Complex a, Complex b){
		double real1;
		double imag1;
		double imag2;
		double real2;

		//FOIL systematically for the coefficients
		real1 = a.getReal() * b.getReal();
		imag1 = a.getReal() * b.getImag();
		imag2 = a.getImag() * b.getReal();
		real2 = -(a.getImag() * b.getImag()); //The negative sign is there for i^2 == -1

		//put together the coefficients
		real1 = real1 + real2;
		imag1 = imag1 + imag2;

		Complex newComplex = new Complex(real1, imag1);

		return newComplex;
	}

	/*
	 * This method is used to divide complex numbers. This uses
	 * the logic behind using the divisors conjugate in order to
	 * make the divisor real. This allows for the new top of the 
	 * division to be a complex number who is divided by a real
	 * number as opposed to an imaginary.
	 * 
	 * THE BOTTOM COMES BACK AS A COMPLEX NUMBER!!!
	 * The imag == 0 in the new bottom, but there is a real part 
	 * still
	 */

	//Complex a / Complex b == Complex.mult(Complex a, Complex b)
	public static Complex div(Complex a, Complex b){
		Complex conj = new Complex(b.getReal(), -(b.getImag()));

		Complex newTop = mult(a, conj);
		Complex newBot = mult(b, conj); //newBot has real = non-zero and imag = 0

		newTop.setReal(newTop.getReal() / newBot.getReal()); //divide real of top by real of bottom
		newTop.setImag(newTop.getImag() / newBot.getReal()); //divide imag of top by real of bottom

		return newTop; //return newTop because it has all the parts correctly altered
	}

	//Dynamic Methods

	//Addition by adding each part
	public Complex addition(Complex a){
		Complex c = new Complex((real + a.getReal()), (imag + a.getImag()));
		return c;
	}

	//Subtraction by subtracting each part
	public Complex subtraction(Complex a){
		Complex c = new Complex((real - a.getReal()), (imag - a.getImag()));
		return c;
	}

	//Multiplication; FOIL method and then each part is added respectively
	public Complex multiplication(Complex a){
		double x = real * a.getReal();
		double y = real * a.getImag();
		double z = imag * a.getReal();
		double w = -(imag * a.getImag());

		Complex c = new Complex((x + w), (y + z));
		return c;
	}

	//Division; Conjugate method
	public Complex division(Complex a){
		Complex aConj = new Complex(a.getReal(), -(a.getImag()));
		Complex num = new Complex(real, imag);

		Complex x = mult(num, aConj);
		Complex y = mult(a, aConj);

		Complex c = new Complex((x.getReal() / y.getReal()), (x.getImag() / y.getReal()));
		return c;
	}




}
