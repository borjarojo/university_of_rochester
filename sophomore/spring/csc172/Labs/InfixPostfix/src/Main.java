/*
 * This is Borja Rojo's Infix to Postfix evaluation project
 * 
 * Class: CSC 172
 * Professor: Ted Pawlicki
 * Lab TA: Cesar De Hoyos
 */

import java.io.*;
import java.util.Formatter;


public class Main {

	public static void main(String[]args) throws IOException{
		//Initiate
		InToPost test = new InToPost(args[0]);	//declare
		
		test.fileConv = test.fileConv(test.file);				//convert to queue
		test.postFix = test.totalConv(test.fileConv);			//convert to postfix
		test.postFixEval = test.postFixEvalStore(test.postFix);	//evaluate
		
		int i;
		for(i = 0; i < test.postFixEval.length; i++){
			if(test.postFixEval[i] != null){
				System.out.printf("%.2f\n", test.postFixEval[i]);
			}
		}
		writeFile(test.postFixEval, args[1]);
		
		
		
	}
	
	public static void writeFile(Float[] toWrite, String fileName) throws IOException{
		File file = new File(fileName);
		
		if(!file.exists()){	//create file if it does not exsist
			file.createNewFile();
		}
		String formatted = "%.2f\n";
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		int i = 0;
		while(toWrite[i] != null){
			bw.write(String.format(formatted, toWrite[i]));
			i++;
		}
		
		bw.close();
		System.out.println("Written");
		
	}
}
