/*
 * Borja Rojo
 * brojo@u.rochester.edu
 *
 * Partner: Daniel Saltz
 */
public class HashTable {
	
	public String[] table;
	private int elementCount;
	
	//Default Table size is 13
	public HashTable(){
		table = new String[13];
		elementCount = 0;
	}
	
	//Custom size
	public HashTable(int s){
		table = new String[s];
		elementCount = 0;
	}
	
	//A "good" hash function
	public static int hash(String key, int tableSize){
		int hashVal = 0;
		
		//Raw Generation
		for(int i = 0; i < key.length(); i++){
			hashVal = 37 * hashVal + key.charAt(i);
		}
		
		//Negative check
		hashVal %= tableSize;
		if(hashVal < 0){
			hashVal += tableSize;
		}
		
		//Return
		return hashVal;
	}
	
	//Overload method to use this table's size
	public int hash(String key){
		int hashVal = 0;
		
		//Raw Generation
		for(int i = 0; i < key.length(); i++){
			hashVal = 37 * hashVal + key.charAt(i);
		}
		
		//Negative check
		hashVal %= table.length;
		if(hashVal < 0){
			hashVal += table.length;
		}
		
		//Return
		return hashVal;
	}

	//Insertion using hashing
	public void insert(String key){
		if(loadFactor() > .50){
			rehash();
		}
		//I'm going to check for a name and Insert at the same time
		//I will do this by trying to insert a name using linear probing
		//and will not insert if the same string is found, but will
		//insert if a space is found
		
		int hashCode = hash(key);
		int probe = hashCode;
		
		if(table[probe] == null){
			table[probe] = key;
			elementCount++;
			return;
		}else if(table[probe].compareTo(key) == 0){
			return;
		}
		
		probe++;
		while(probe != hashCode){
			if(table[probe] == null){
				table[probe] = key;
				elementCount++;
				return;
			}else if(table[probe].compareTo(key) == 0){
				return;
			}
			
			probe++;
			//if passes the end, go to beginning
			if(probe == table.length){
				probe = 0;
			}
		}
		
//		//Check statements to see what happened and act accordingly
//		if(probe == hashCode){						//If the entire table was traversed
//			return;									//return because there are no free spaces
//		}else if(table[probe].compareTo(key) == 0){	//else if the same key is present
//			return;									//return because it is already there;
//		}else{										//else, a blank space was found
//			table[probe] = key;						//so set that table spot to the key
//		}
	}
	
 	private void rehash() {
 		HashTable newTable = new HashTable(table.length * 2);
 		for(int i = 0; i < table.length; i++){
 			if(table[i] != null){
 				newTable.insert(table[i]);
 			}
 		}
 		
 		table = newTable.table;
	}

	public void printTable(){
		for(String word : table){
			if(word != null){
				System.out.println(word);
			}
		}
	}
 	
 	//Getter Methods
 	public int capacity(){
 		return table.length;
 	}
 	
 	public int elementCount(){
 		return elementCount;
 	}
 	
 	public double loadFactor(){
 		return ((double) elementCount) / ((double) table.length);
 	}


}
