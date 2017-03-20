package lab6q4;

public class lab6q4 {
	public static void main(String[]args){
		
		//This program prints the lyrics of the song "The Twelve Days of Christmas"
		
		for(int x = 1; x != 12; x++){
			
			System.out.print(" On the ");
			
			switch (x)
			{
			case 1 :
				System.out.print("first day of Chistmas my true love sent to me");
				break;
			case 2 :
				System.out.print("second day of Chistmas my true love sent to me");
				break;
			case 3 :
				System.out.print("third day of Chistmas my true love sent to me");
				break;
			case 4 :
				System.out.print("fourth day of Chistmas my true love sent to me");
				break;
			case 5 :
				System.out.print("fifth day of Chistmas my true love sent to me");
				break;
			case 6 :
				System.out.print("sixth day of Chistmas my true love sent to me");
				break;
			case 7 :
				System.out.print("seventh day of Chistmas my true love sent to me");
				break;
			case 8 :
				System.out.print("eighth day of Chistmas my true love sent to me");
				break;
			case 9 :
				System.out.print("nineth day of Chistmas my true love sent to me");
				break;
			case 10 :
				System.out.print("tenth day of Chistmas my true love sent to me");
				break;
			case 11 :
				System.out.print("eleventh day of Chistmas my true love sent to me");
				break;
			case 12 :
				System.out.print("twelth day of Chistmas my true love sent to me");
				break;
			}
			
			switch (x)
			{
			case 12 :
				System.out.println(" a Partrige in a Pear Tree");
			case 11 :
				System.out.println(" Two Turtle Doves");
			case 10 :
				System.out.println(" Three French Hens");
			case 9 :
				System.out.println(" Four Calling Birds");
			case 8 :
				System.out.println(" FIVE GOLDEN RINGS!!!");
			case 7 :
				System.out.println(" Six Geese-a-Laying");
			case 6 :
				System.out.println(" Seven Swans-a-Swimming");
			case 5 :
				System.out.println(" Eight Maids-a-Milking");
			case 4 :
				System.out.println(" Nine Ladies Dancing");
			case 3 :
				System.out.println(" Ten Lords-a-Leaping");
			case 2 :
				System.out.println(" Eleven Pipers Piping");
			case 1 :
				System.out.println(" Twelve Drummers Drumming");
			}
			
			System.out.println();
		}
	}
}
