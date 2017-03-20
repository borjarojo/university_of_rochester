import java.io.*;
import java.util.Scanner;



public class tttFile 
{
	//declaring static file
	static File f = new File ("score.txt");

	//function that reads the score from the file and returns the score as an array of ints
	public static int readScoreOne()
	{
		try
		{

			FileReader fw = new FileReader(f);
			BufferedReader br = new BufferedReader(fw);
			Scanner scan = new Scanner(br);

			String score1 = scan.nextLine();
			String score2 = scan.nextLine();
			
			int scoreOne = Integer.parseInt(score1);

			return scoreOne;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return (Integer) null;
	}
	
	public static int readScoreTwo()
	{
		try
		{

			FileReader fw = new FileReader(f);
			BufferedReader br = new BufferedReader(fw);
			Scanner scan = new Scanner(br);

			String score1 = scan.nextLine();
			String score2 = scan.nextLine();
			
			int scoreTwo = Integer.parseInt(score2);

			return scoreTwo;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return (Integer) null;
	}

	//writes the scores to the file
	public static void writeScore(int [] scores)
	{
		try
		{
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(Integer.toString(scores[0]));
			bw.newLine();
			bw.write(Integer.toString(scores[1]));
			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}