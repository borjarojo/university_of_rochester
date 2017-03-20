/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */
import java.math.BigInteger;


public class MainTest {
	
	public static BigInteger bigIntFact(BigInteger n){
		if(n.equals(new BigInteger("1"))){
			return new BigInteger("1");
		}else{
			return n.multiply(bigIntFact(n.subtract(new BigInteger("1"))));
		}
	}
	
	//Ordering with Identical Items
	//j starts at -1 because of arrays
	public static BigInteger orderIdItem(BigInteger n, BigInteger k, BigInteger[] i, BigInteger j){
		if(j.equals(k.subtract(new BigInteger("1")))){	//Base case
			return bigIntFact(i[j.intValue()]);			//When at the last group of items, return it's factorial
		}else if(j.equals(new BigInteger("-1"))){		//start
			return bigIntFact(n).divide(orderIdItem(n,k,i,j.add(new BigInteger("1"))));	//n factorial divided by *begin recursion
		}else{	//recursive step
			return bigIntFact(i[j.intValue()]).multiply(orderIdItem(n,k,i,j.add(new BigInteger("1"))));	//return next group's factorial multiplied for *recursion
		}
		//Should start with n factorial divided by what becomes a chain of multiplications of the group's factorial's, ending when the last group is reached
	}
	
	//Distributing Indistinguishable Objects into Bins
	/*This formula doesn't really require recursion, as 
	 * i have a recursive method for factorials. I'm not sure
	 * if that counts exactly.
	 * (n + m -1)! / (n! * ((n + m -1) - n)!) : n + m -1 choose n
	*/
	public static BigInteger distIndObjToBin(BigInteger n, BigInteger m){
		return bigIntFact(n.add(m).subtract(new BigInteger("1"))).divide(bigIntFact(n).multiply(bigIntFact(m.subtract(new BigInteger("1")))));
	}
	
	//Distributing Distinguishable Objects into Bins
	//j should start at -1 because arrays start at 0
	public static BigInteger distDisObjToBin(BigInteger n, BigInteger m, BigInteger k, BigInteger[] i, BigInteger j){
		if(j.equals(k.subtract(new BigInteger("1")))){	//Base Case
			return bigIntFact(i[j.intValue()]);			//when at the last group of items, return it's factorial
		}else if(j.equals(new BigInteger("-1"))){			//Start
			//return the top divided by the bottom, which is the (m-1)! multiplied by the recursive step
			return bigIntFact(n.add(m).subtract(new BigInteger("1"))).divide(bigIntFact(m.subtract(new BigInteger("1"))).multiply(distDisObjToBin(n, m, k, i, j.add(new BigInteger("1")))));
		}else{
			return bigIntFact(i[j.intValue()]).multiply(distDisObjToBin(n, m, k, i, j.add(new BigInteger("1"))));
		}
	}
	
	public static void main(String[]args){
		//Ordering with Identical Items
		System.out.println("Ordering With Identical Items");
		
		//1a) error; n = 5, k = 3, i = {1,3,1}
		BigInteger n = new BigInteger("5");
		BigInteger k = new BigInteger("3");
		BigInteger[] i = {new BigInteger("1"), new BigInteger("3"), new BigInteger("1")};
		BigInteger j = new BigInteger("-1");
		
		System.out.println("1a) error: " + orderIdItem(n, k, i, j));
		
		//1b) street; n = 6, k = 4, i = {1,2,1,2}
		n = new BigInteger("6");
		k = new BigInteger("4");
		BigInteger[] i2 = {new BigInteger("1"), new BigInteger("2"), new BigInteger("1"), new BigInteger("2")};
		j = new BigInteger("-1");
		
		System.out.println("1b) street: " + orderIdItem(n, k, i2, j));
		
		//1c)allele; n = 6, k = 3, i = {1,3,2}
		n = new BigInteger("6");
		k = new BigInteger("3");
		BigInteger[] i6 = {new BigInteger("1"), new BigInteger("3"), new BigInteger("2")};
		j = new BigInteger("-1");
		
		System.out.println("1b) allele: " + orderIdItem(n, k, i6, j));
		
		//1d) mississippi; n = 11, k = 4, i = {1,4,4,2}
		n = new BigInteger("11");
		k = new BigInteger("4");
		BigInteger[] i3 = {new BigInteger("1"), new BigInteger("4"), new BigInteger("4"), new BigInteger("2")};
		j = new BigInteger("-1");
		
		System.out.println("1d) mississippi: " + orderIdItem(n, k, i3, j) + "\n");
		
		//Distributing Indistinguishable Objects into Bins
		System.out.println("Distributing Indistinguishable Objects into Bins");
		
		//2a)six apples to four children; n = 6, m = 4
		n = new BigInteger("6");
		BigInteger m = new BigInteger("4");
		
		System.out.println("2a) six apples to four children: " + distIndObjToBin(n, m));
		
		//2b)four apples to six children; n = 4, m = 6
		n = new BigInteger("4");
		m = new BigInteger("6");
		
		System.out.println("2a) four apples to six children: " + distIndObjToBin(n, m) + "\n");
		
		//Distributing Distinguishable Objects into Bins
		System.out.println("Distributing Distinguishable Objects into Bins");
		
		//3a)six apples and three pears to five children; n = 9, m = 5, k = 2, i = {6, 3}
		n = new BigInteger("9");
		m = new BigInteger("5");
		k = new BigInteger("2");
		BigInteger[] i4 = {new BigInteger("6"), new BigInteger("3")};
		j = new BigInteger("-1");
		
		System.out.println("3a) six apples and three pears to five children: " + distDisObjToBin(n, m, k, i4, j));
		
		//3b)2 apples, 5 pears, and six bananas to three children: n = 13, m = 3, k = 3, i = {2, 5, 6}
		n = new BigInteger("13");
		m = new BigInteger("3");
		k = new BigInteger("3");
		BigInteger[] i5 = {new BigInteger("2"), new BigInteger("5"), new BigInteger("6")};
		j = new BigInteger("-1");
		
		System.out.println("3b) two apples, five pears, and six bananas to 3 children: " + distDisObjToBin(n, m, k, i5, j));
	}
}
