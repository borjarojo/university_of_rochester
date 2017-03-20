import java.io.*;
import java.util.*;

public class MainTest {


	// this method reads the input file and inserts each line as an index of a String ArrayList
	public static ArrayList<String> readInput(String fileName) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));

		// ArrayList that will take in each line of the file
		ArrayList<String> inputFile = new ArrayList<String>();

		String line = reader.readLine();
		while (line != null)
		{
			inputFile.add(line);
			line = reader.readLine();
		} 
		reader.close();
		return inputFile;
	}

/*
	// this method recieves the String ArrayList from readInput(),
	// and assigns each character as an index of a Character ArrayList
	// In the process, this method removes all whitespace in between characters
	public static ArrayList<String> modifyInput(ArrayList<String> input)
	{
		ArrayList<String> outline = new ArrayList<String>();

		for (int i = 0; i < input.size(); i++)
		{
			String inline = input.get(i);
			for (int j = 0; j < inline.length(); j++)
			{
				String temp = "";
				while (j < inline.length() && !Character.isWhitespace(inline.charAt(j)))
				{
					temp += Character.toString(inline.charAt(j++));
				}
				temp = temp.trim();
				if (!(temp.equals("") || temp.contains(" ")))
				{
					while (temp.startsWith("("))
					{
						outline.add("(");
						temp = temp.substring(1, temp.length());
					}
					while (temp.startsWith("!"))
					{
						outline.add("!");
						temp = temp.substring(1,temp.length());
					}
					int count = outline.size();
					while (temp.endsWith(")"))
					{
						outline.add(")");
						temp = temp.substring(0,temp.length() - 1);
					}
					outline.add(count, temp);
				}
			}
		}
		return outline;
	}
*/

	public static ArrayList<String> modifyInput(ArrayList<String> input)
	{
		ArrayList<String> outline = new ArrayList<String>();

		for (int i = 0; i < input.size(); i++)
		{
			String temp = "";
			String inline = input.get(i);
			for (int j = 0; j < inline.length(); j++)
			{
				outline.add(inline[j]);
				/*
				while (j < inline.length() && !Character.isWhitespace(inline.charAt(j)))
				{
					temp += Character.toString(inline.charAt(j++));
					//outline.add(Character.toString(inline.charAt(j++)));
				} // temp contains the string with no spaces

				// give a space between each character
				for (int x = 0; x < temp.length(); x++)
				{
					if (Character.toString(inline.charAt(x)).equals(Pattern.quote(".")))
						System.out.println("...........");

					outline.add(Character.toString(inline.charAt(x))+" ");
				}
			}*/
		}
		return outline;
	}

	public static void main(String[] args) throws IOException{

		File input = new File(args[0]);
		File output = new File(args[1]);

		ArrayList<String> readFile = readInput(args[0]);
		// prints every line of the input file
		//for (int c = 0; c < readFile.size(); c++)
		//	System.out.println(readFile.get(c));
		//System.out.println(readFile.size());


		ArrayList<String> chars = modifyInput(readFile);

		// go through each line, break down each character into an ArrayList of individual strings
		for (int c = 0; c < chars.size(); c++)
		{
			System.out.println(chars.get(c));
		}


		// create an output file with FileWriter
		FileWriter fw = new FileWriter(new File(args[1]));
		// wrap the FileWriter in the BufferedReader
		BufferedWriter bw = new BufferedWriter(fw); 


/*
		try
		{
			for (int line = 0; line < readFile.size(); line++)
			{
				//for (int ch = 0; ch < chars.size(); ch++)
				//{
					Stack stacks = new Stack();
					Queue queues = new Queue();

					postfix(chars, queues, stacks);
					eval(queues, stacks);

					while(!chars.isEmpty())
					{
						chars.remove(0);
					}
					bw.write(stacks.pop());
					bw.newLine();
				//}
				bw.close();
			}
		} 
		catch (IOException e) 
		{
			System.out.println("File ERROR");
		}*/
	}

	public static void postfix(ArrayList<String> str1, Queue queues, Stack stacks){
		while(!str1.isEmpty()){
			if(str1.get(0).compareTo("+") == 0){
				if(stacks.peek() == null){
					stacks.push(str1.get(0));
					str1.remove(0);
				} else if((stacks.peek().equals("*")) || (stacks.peek().equals("/"))){
					while((!stacks.isEmpty()) && (stacks.peek().compareTo("(") != 0)){
						queues.insert(stacks.pop());
					}
					stacks.push(str1.get(0));
					str1.remove(0);
				} else{
					stacks.push(str1.get(0));
					str1.remove(0);
				}
			} else if(str1.get(0).compareTo("-") == 0){
				if(stacks.peek() == null){
					stacks.push(str1.get(0));
					str1.remove(0);
				} else if((stacks.peek().equals("*")) || (stacks.peek().equals("/"))){
					while((!stacks.isEmpty()) && (stacks.peek().compareTo("(") != 0)){
						queues.insert(stacks.pop());
					}
					stacks.push(str1.get(0));
					str1.remove(0);
				} else{
					stacks.push(str1.get(0));
					str1.remove(0);
				}
			} else if(str1.get(0).compareTo("*") == 0){
				stacks.push(str1.get(0));
				str1.remove(0);
			} else if(str1.get(0).compareTo("/") == 0){
				stacks.push(str1.get(0));
				str1.remove(0);
			} else if(str1.get(0).compareTo("<") == 0){
				stacks.push(str1.get(0));
				str1.remove(0);
			} else if(str1.get(0).compareTo(">") == 0){
				stacks.push(str1.get(0));
				str1.remove(0);
			} else if(str1.get(0).compareTo("==") == 0){
				stacks.push(str1.get(0));
				str1.remove(0);
			} else if(str1.get(0).compareTo("&&") == 0){
				if(stacks.peek() == null){
					stacks.push(str1.get(0));
					str1.remove(0);
				} else if((stacks.peek().equals(">")) || (stacks.peek().equals("<")) || (stacks.peek().equals("=="))){
					while((!stacks.isEmpty()) && (stacks.peek().compareTo("(") != 0)){
						queues.insert(stacks.pop());
					}
					stacks.push(str1.get(0));
					str1.remove(0);
				} else{
					stacks.push(str1.get(0));
					str1.remove(0);
				}
			} else if(str1.get(0).compareTo("||") == 0){
				if(stacks.peek() == null){
					stacks.push(str1.get(0));
					str1.remove(0);
				} else if((stacks.peek().equals(">")) || (stacks.peek().equals("<")) || (stacks.peek().equals("==")) || (stacks.peek().equals("&&"))){
					while((!stacks.isEmpty()) && (stacks.peek().compareTo("(") != 0)){
						queues.insert(stacks.pop());
					}
					stacks.push(str1.get(0));
					str1.remove(0);
				} else{
					stacks.push(str1.get(0));
					str1.remove(0);
				}
			} else if(str1.get(0).compareTo("!") == 0){
				stacks.push(str1.get(0));
				str1.remove(0);
			} else if(str1.get(0).compareTo("(") == 0){
				stacks.push(str1.get(0));
				str1.remove(0);
			} else if(str1.get(0).compareTo(")") == 0){
				int v = stacks.lastIndexOf("(");
				while(stacks.size() != v){
					queues.insert(stacks.pop());
				}
				stacks.pop();
				str1.remove(0);
			} else{
				queues.insert(str1.get(0));
				str1.remove(0);
			}
		}
		while(!stacks.isEmpty()){
			queues.insert(stacks.pop());
		}
	}

	public static void eval(Queue queues, Stack stacks){
		while(queues.size() != 0){
			String ss = queues.dequeue();
			if (ss.equals("+")){
				float x = Float.parseFloat(stacks.pop());
				float y = Float.parseFloat(stacks.pop());
				float z = x + y;
				String v = Float.toString(z);
				stacks.push(v);
			} else if (ss.equals("-")){
				float x = Float.parseFloat(stacks.pop());
				float y = Float.parseFloat(stacks.pop());
				float z = y - x;
				String v = Float.toString(z);
				stacks.push(v);
			} else if (ss.equals("*")){
				float x = Float.parseFloat(stacks.pop());
				float y = Float.parseFloat(stacks.pop());
				float z = x * y;
				String v = Float.toString(z);
				stacks.push(v);
			} else if (ss.equals("/")){
				float x = Float.parseFloat(stacks.pop());
				float y = Float.parseFloat(stacks.pop());
				float z = y / x;
				String v = Float.toString(z);
				stacks.push(v);
			} else if (ss.equals("<")){
				float x = Float.parseFloat(stacks.pop());
				float y = Float.parseFloat(stacks.pop());
				float z;
				if(y < x){
					z = 1;
				} else{
					z = 0;
				}
				String v = Integer.toString((int) z);
				stacks.push(v);
			} else if (ss.equals(">")){
				float x = Float.parseFloat(stacks.pop());
				float y = Float.parseFloat(stacks.pop());
				float z;
				if(y > x){
					z = 1;
				} else{
					z = 0;
				}
				String v = Integer.toString((int) z);
				stacks.push(v);
			} else if (ss.equals("==")){
				float x = Float.parseFloat(stacks.pop());
				float y = Float.parseFloat(stacks.pop());
				float z;
				if(y == x){
					z = 1;
				} else{
					z = 0;
				}
				String v = Integer.toString((int) z);
				stacks.push(v);
			} else if (ss.equals("&&")){
				float x = Float.parseFloat(stacks.pop());
				float y = Float.parseFloat(stacks.pop());
				float z;
				if(y == 1 && x == 1){
					z = 1;
				} else{
					z = 0;
				}
				String v = Integer.toString((int) z);
				stacks.push(v);
			} else if (ss.equals("||")){
				float x = Float.parseFloat(stacks.pop());
				float y = Float.parseFloat(stacks.pop());
				float z;
				if(y ==1 || x == 1){
					z = 1;
				} else{
					z = 0;
				}
				String v = Integer.toString((int) z);
				stacks.push(v);
			} else if (ss.equals("!")){
				float x = Float.parseFloat(stacks.pop());
				float z;
				if(x == 1){
					z = 0;
				} else{
					z = 1;
				}
				String v = Integer.toString((int) z);
				stacks.push(v);
			} else {
				stacks.push(ss);
			}
		}
	}
}