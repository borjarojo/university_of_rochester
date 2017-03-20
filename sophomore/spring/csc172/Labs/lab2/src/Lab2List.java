/*
 * Borja Rojo
 * 
 * Due February 1, 2015
 * 
 * CSC 172
 * 
 * Lab 3
 */
public class Lab2List implements SimpleLinkedList{

	public MyNode item;
	
	public Lab2List(){
		item = null;
	}
	
	public void insert(Object x) {
		// TODO Auto-generated method stub
		if(lookup(x) == false){						//Enter method only if item does not already exist
			MyNode newNode = new MyNode();			//Create new node
			newNode.data = x;						//add data to node
			newNode.next = item;					//Give beginning pointer to  new node
			item = newNode;							//Make first item equal the new node
//			System.out.println("Item added");
		}else{
			System.out.println("Already in the list!");
		}
		//Does not depend on size of list, only deals with first, O(1).
	}

	public void delete(Object x) {
		// TODO Auto-generated method stub
		MyNode prev = item;
		MyNode temp = item;
		
		if(lookup(x) == false){
			System.out.println("Item to delete is not in the list.");
		}else{
		
		if(item.data.equals(x)){
			item = item.next;
			return;
		}
		
		while(temp != null ){
			if(temp.data.equals(x)){
				temp = temp.next;
				prev.next = temp;
				return;
			} else if (!temp.data.equals(x)){
				prev = temp;
				temp = temp.next;
			}
		}
		return;
		}
	}

	public boolean lookup(Object x) {
		// TODO Auto-generated method stub
		MyNode temp = item;
		
		if (temp == null){	//Check to see if the list exists
//			System.out.println("No list yet!");
			return false;
		}
		
		while(temp != null){
			 if(temp.data == x){	//if the data passed in is contained in a node, return true
			 	return true;
			 }
			 temp = temp.next;		//then, if it didnt return true, set temp to the next node and continue
		}
		
		return false;				//If every single check didnt return true, it is not there, so return false
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		MyNode temp = item;				//Save node
		
		while(temp.next != null){		//Enter if it is not the end of the list
			if(temp.data != null){		//If there is data, return false, else save next node and repeat
				return false;
			}else{
				temp = temp.next;
			}
		}
		
		if(temp.data != null){			//Check Last Node
			return false;
		}
		
		return true;					//After checking all nodes to see if it is empty, return true
		
	}
	

	public void printList() {
		// TODO Auto-generated method stub
		MyNode temp = item;
		String listPrint = "";
		while(temp.next != null){									//Enter as long as it's not the last item
			listPrint = listPrint + temp.data.toString() + ", ";	//Add data as a String
			temp = temp.next;
//			System.out.println("Added to print STring");
		}
		listPrint = listPrint + temp.data.toString();				//Add last data String
		
		System.out.println(listPrint);
		
		//Runs through the entire list, so it has to do with it's size: O(n)
	}

}
