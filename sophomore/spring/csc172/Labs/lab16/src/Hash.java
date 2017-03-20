
public class Hash {
	
	public static int tableSize = 13;
	public static String[] hashTable = new String[tableSize];
	
	public Hash(){
		
	}
	
	public Hash(int s){
		tableSize = s;
		hashTable = new String[s];
	}
	public static void insert(String item){
		if(getLoad() >= .50){
			rehash();
		}
		int key = hash(item);
		if(exsists(item) == false){
			resolve(key, item);
		}
	}
	
	public static boolean exsists(String item){
		for(int i = 0; i < tableSize - 1 ; i++){
			//If the space is not empty and it compares to be equal, return true
			if(hashTable[i] != null && 
					item.compareTo(hashTable[i]) == 0){
				return true;
			}
		}
		//If nothing ever compares equal, return false
		return false;
	}
	//Resolves by taking a key and the item in question
	public static void resolve(int key, String item){
		int i = key;				//Iterator
		while(hashTable[i] != null){//Go while space is filled
			if(i == tableSize - 1){	//If it's equal to the last index, wrap
				i = 0;
			}
			else{					//Else, increment by 1
				i++;
			}
		}
		hashTable[i] = item;		//When a space is found that is null, insert item
	}

	public static void print(){
		for(int i = 0; i < tableSize - 1; i++){
			if(hashTable[i] != null){
				System.out.println(i + ": " + hashTable[i]);
			}
		}
	}
	
	public static int hash(String data){
		int hashVal = 0;
		
		for(int i = 0; i < data.length(); i++){
			hashVal = hashVal * 37 + data.charAt(i);
		}
		
		hashVal %= tableSize;
		if(hashVal< 0){
			hashVal += tableSize;
		}
		
		return hashVal;
	}
	
	public static int getSize(){
		return tableSize;
	}
	
	public static int getCount(){
		int count = 0;
		for(int i = 0; i < tableSize - 1; i++){
			if(hashTable[i] != null){
				count++;
			}
		}
		return count;
	}
	
	public static double getLoad(){
		double load = ((double) getCount())/((double) getSize());
		return load;
	}
	
	public static void rehash(){
		int newSize = tableSize*2;
		Hash newTable = new Hash(newSize);
		
		for(int i = 0; i < tableSize - 1; i++){
			
			if(hashTable[i] != null){
				newTable.insert(hashTable[i]);
			}
		}
		
		hashTable = newTable.hashTable;
	}



}
