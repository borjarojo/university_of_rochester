import java.math.BigInteger;


public class Combinatorics1Main {
	
	public static void main(String[]args){
		
		//1.1
		BigInteger house = new BigInteger("3");
		BigInteger color = new BigInteger("4");
		BigInteger one1Answer = countAssign(color, house);
		
		System.out.println("Answer to 1.1: " + one1Answer);
		
		//1.2
		BigInteger passEight = new BigInteger("8");
		BigInteger passNine = new BigInteger("9");
		BigInteger passTen = new BigInteger("10");
		
		BigInteger passChar = new BigInteger("66");
		
		BigInteger totalEight = countAssign(passChar, passEight);
		BigInteger totalNine = countAssign(passChar, passNine);
		BigInteger totalTen = countAssign(passChar, passTen);
		
		BigInteger one2Answer = new BigInteger("0");
		
		one2Answer = one2Answer.add(totalEight);
		one2Answer = one2Answer.add(totalNine);
		one2Answer = one2Answer.add(totalTen);
		
		System.out.println("Answer to 1.2: " + one2Answer);
		
	}
	
	public static BigInteger countAssign(BigInteger k, BigInteger n){
		BigInteger total = new BigInteger("0");
		
		total = k.pow(n.intValue());
		
		return total;
	}
	
	public static BigInteger factoral(BigInteger n){
		BigInteger total = n;
		int count = n.intValue();
		
		for(int i = count; i != 0; i--){
			total = total.multiply();
		}
		
		return total;
	}
	
	
}
