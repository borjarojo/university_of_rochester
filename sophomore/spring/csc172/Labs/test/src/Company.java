
public class Company {

	public static void printSalary(Job j){
		System.out.println(j.getSalary());
	}
	
	public static void main(String[] args){
		Chef joe = new Chef(new Integer(10000));
		
		printSalary(joe);
	}
}
