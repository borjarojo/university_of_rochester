/*
 * This is Borja Rojo's Infix to Postfix evaluation project
 * 
 * Class: CSC 172
 * Professor: Ted Pawlicki
 * Lab TA: Yiheng Zhou
 */

import java.io.*;

public class InToPost {
	/*
	 * In order to successfully read a file and convert it to Postfix, many
	 * considerations must me made. The first is the format at which text is 
	 * written. This is of utmost importance as it will determine the methods
	 * in which the the file is read. Another important consideration, naturally,
	 * is the content of the file. The approach I will use is meant to be as
	 * general as possible.
	 * 
	 * This conversion must be able to consider many anamolies that may give
	 * rise to issues later. Spaces must be watched for, as well as decimal 
	 * points of Floats.
	 * 
	 * There is also the issue of priority, where order of operations must be
	 * considered in order to correctly evaluate an Infix statement
	 * 
	 * The strategy will be to work with floats as the primary numbers and convert
	 * ints to floats when necissary.
	 */
	
	
	String[] file = new String[50];
	Queue[] fileConv = new Queue[50];
	Queue[]	postFix	= new Queue[50];
	Float[] postFixEval = new Float[50];
	

	
	public InToPost(String fileName) throws IOException{
		readFile(fileName);
	}
	
	public void readFile(String s) throws IOException{
		
		try{
			BufferedReader reader = 
					new BufferedReader(new FileReader(s));
			
			int i = 0;
			while((file[i] = reader.readLine()) != null){
				i++;
			}
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * This method converts the String Array containing
	 * all of the statements into a new array with the
	 * with the converted statements.
	 */
	public Queue[] fileConv(String[] oldFile){
		Queue[] newFile = new Queue[oldFile.length];
		int i;
		
		for(i = 0; i < oldFile.length; i++){
			if(oldFile[i] != null){
				newFile[i] = stringConv(oldFile[i]);
			}
		}
		
		return newFile;
	}
	
	/*
	 * This method converts an infix string into a postfix
	 * set of tokens and returns that as a queue
	 */
	public Queue stringConv(String line){
		Queue statement = new Queue();
		Queue token = new Queue();
		Stack operators = new Stack();
		
		int i = 0;
		for(i = 0; i < line.length(); i++){
			statement.enqueue(line.substring(i, i+1));
		}
		
		
		//Create a token T to put into the token queue
		while(statement.peek() != null){
			String t = new String();
			
			if(tokenEval((String) statement.peek()) == 0){
				statement.dequeue();
			}
			else if(tokenEval((String) statement.peek()) == 1 || tokenEval((String) statement.peek()) == 2){
				t = t + ((String) statement.dequeue());
				
				while(tokenEval((String) statement.peek()) == 1){
					t = t + ((String) statement.dequeue());
				}
				token.enqueue(t);
			}else if(tokenEval((String) statement.peek()) == 3){
				token.enqueue(statement.dequeue());
			}
		}
		
		return token;
	}
	
	/*
	 * this method evaluates a string to see if it may
	 * be a space, a number, or a symbol
	 */
	public int tokenEval(String s){
		if(s == null){
			return 9;
		}
		if(s.compareTo(" ") == 0){
			return 0;
		}
		else if(s.compareTo("0") == 0 ||
				s.compareTo("1") == 0 ||
				s.compareTo("2") == 0 ||
				s.compareTo("3") == 0 ||
				s.compareTo("4") == 0 ||
				s.compareTo("5") == 0 ||
				s.compareTo("6") == 0 ||
				s.compareTo("7") == 0||
				s.compareTo("8") == 0 ||
				s.compareTo("9") == 0||
				s.compareTo(".") == 0){
			return 1;
		}
		else if(s.compareTo("-") == 0){
			return 2;
		}
		else{
			return 3;
		}
	}
	
	//Postfix conversion
	
	public int priority(String token){
		/*
		 * Different tokens have different priority over others.
		 * 
		 * It is important to be able to evaluate what priority 
		 * operators have in order to be able to know what methods
		 * need to be used on the stack
		 * 
		 * Priority for Order of Operations
		 * 
		 * Numbers go straight to the queue
		 * 
		 * 1. ()
		 * 2. Trig functions
		 * 3. Multiplication, division, and modulo
		 * 4. Addition and subtraction
		 * 5. Less than, greater than comparisons
		 * 6. Equals/ not Equals
		 * 7. Logical AND
		 * 8. Logical OR
		 */
		
		if(token.compareTo("(") == 0){
			return 10;
		}
		else if((token.compareTo("sin") == 0) ||
				(token.compareTo("cos") == 0) ||
				(token.compareTo("tan") == 0)){
			return 2;
		}
		else if((token.compareTo("*") == 0) ||
				(token.compareTo("/") == 0) ||
				(token.compareTo("%") == 0)){
			return 3;
		}
		else if((token.compareTo("+") == 0) ||
				(token.compareTo("-") == 0)){
			return 4;
		}
		else if((token.compareTo("<") == 0) ||
				(token.compareTo(">") == 0) ||
				(token.compareTo("<=") == 0) ||
				(token.compareTo(">=") == 0)){
			return 5;
		}
		else if((token.compareTo("=") == 0) ||
				(token.compareTo("!") == 0)){
			return 6;
		}
		else if((token.compareTo("&") == 0)){
			return 7;
		}
		else if((token.compareTo("|") == 0)){
			return 8;
		}
		else if(token.compareTo(")") == 0){
			return 9;
		}
		else if(token.compareTo("a") == 0){
			return 12;
		}
		
		//If it's a number
		return 0;
	}

	public Queue postFixConv(Queue statement){
		Queue postConv = new Queue();
		Stack operator = new Stack();
		
		operator.push("a");
		
		while(statement.peek() != null){
			if(priority((String) statement.peek()) == 0){		//if a number
				postConv.enqueue(statement.dequeue());
			}
			else if(priority((String) statement.peek()) == 10){	// if an (
				operator.push((String) statement.dequeue());
	
			}
			else if(priority((String) statement.peek()) == 9){	// if an )
				//While not (, pop stack and queue to postFix, then pop the (
				statement.dequeue();
				while(priority((String) operator.peek()) != 10){
					postConv.enqueue(operator.pop());
				}
				operator.pop();
			}
			else{	//if anything else
				
				//if statement is lesser than operator, pop stack in postfix
				//until statement is not lesser. then push statement
				if(priority((String)statement.peek()) > 
						priority((String) operator.peek())){
					
					while(priority((String) statement.peek()) > 
						priority((String) operator.peek())){
						postConv.enqueue(operator.pop());
					}
					operator.push((String) statement.dequeue());
				}
				else{
					operator.push((String) statement.dequeue()); 
				}
			}
		}
		
		while(((String) operator.peek()).compareTo("a") != 0){
			postConv.enqueue(operator.pop());
		}
		
		
		
		return postConv;
	}

	public Queue[] totalConv(Queue[] statement){
		Queue[] pfc = new Queue[statement.length];
		
		int i;
		for(i = 0; i < statement.length; i++){
			if(statement[i] != null){
				pfc[i] = postFixConv(statement[i]);
			}
		}
		
		return pfc;
	}

	//postfix evaluation
	public Object postFixEval(Queue pfState){
		Stack stack = new Stack();
		
		while(pfState.peek() != null){
			if(priority((String) pfState.peek()) > 0){
				//Pop the two first numbers, operate the first pop
				//on the second pop, push the result
				
				//Multiplication
				if(((String) pfState.peek()).compareTo("*") == 0){
					float first = (Float) stack.pop();
					float second = (Float) stack.pop();
					
					float eval = second * first;
					
					stack.push(eval);
					pfState.dequeue();
				}
				else if(((String) pfState.peek()).compareTo("/") == 0){
					float first = (Float) stack.pop();
					float second = (Float) stack.pop();
					
					float eval = second / first;
					
					stack.push(eval);
					pfState.dequeue();
				}
				else if(((String) pfState.peek()).compareTo("-") == 0){
					float first = (Float) stack.pop();
					float second = (Float) stack.pop();
					
					float eval = second - first;
					
					stack.push(eval);
					pfState.dequeue();
				}
				else if(((String) pfState.peek()).compareTo("+") == 0){
					float first = (Float) stack.pop();
					float second = (Float) stack.pop();
					
					float eval = second + first;
					
					stack.push(eval);
					pfState.dequeue();
				}
				
				//boolean
				else if(((String) pfState.peek()).compareTo(">") == 0){
					float first = (Float) stack.pop();
					float second = (Float) stack.pop();
					
					float eval;
					
					if(second > first){
						eval = 1;
					}
					else{
						eval = 0;
					}
					
					stack.push(eval);
					pfState.dequeue();
				}
				
				else if(((String) pfState.peek()).compareTo("<") == 0){
					float first = (Float) stack.pop();
					float second = (Float) stack.pop();
					
					float eval;
					
					if(second < first){
						eval = 1;
					}
					else{
						eval = 0;
					}
					
					stack.push(eval);
					pfState.dequeue();
				}
				
				else if(((String) pfState.peek()).compareTo("=") == 0){
					float first = (Float) stack.pop();
					float second = (Float) stack.pop();
					
					float eval;
					
					if(second == first){
						eval = 1;
					}
					else{
						eval = 0;
					}
					
					stack.push(eval);
					pfState.dequeue();
				}
				
				else if(((String) pfState.peek()).compareTo("!") == 0){
					float first = (Float) stack.pop();
					
					if(first == 1){
						first = 0;
					}
					else{
						first = 1;
					}
					
					
					stack.push(first);
					pfState.dequeue();
				}
				
				else if(((String) pfState.peek()).compareTo("&") == 0){
					float first = (Float) stack.pop();
					float second = (Float) stack.pop();
					float eval;
					
					if((first == 1) && (second == 1)){
						eval = 1;
					}
					else{
						eval = 0;
					}
					
					
					stack.push(eval);
					pfState.dequeue();
				}
				
				else if(((String) pfState.peek()).compareTo("|") == 0){
					float first = (Float) stack.pop();
					float second = (Float) stack.pop();
					float eval;
					
					if((first == 1) || (second == 1)){
						eval = 1;
					}
					else{
						eval = 0;
					}
					
					
					stack.push(eval);
					pfState.dequeue();
				}
				
				
				
			}
			else{
				stack.push(Float.parseFloat((String) pfState.dequeue()));
			}
		}
		
		return (Float) stack.peek();
		
		
	}
	
	public Float[] postFixEvalStore(Queue[] pfSet){
		Float[] toReturn = new Float[pfSet.length];
		
		int i;
		for(i = 0; i < pfSet.length; i++){
			if(pfSet[i] != null){
				toReturn[i] = (Float) postFixEval(pfSet[i]);
			}
		}
		
		return toReturn;
		
	}
}
