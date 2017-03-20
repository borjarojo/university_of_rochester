
public class Brother {
	
	private String name, broName;
	private int age, height, weight;
	
	//Constructor
	public Brother(String n, String bn, int a, int h, int w){
		name = n;
		broName = bn;
		age = a;
		height = h;
		weight = w;
	}
	
	//accesser and mutators
		//Accesser and mutator for 'name'
		public void setName(String n){
			name = n;
		}
		
		public String getName(){
			return name;
		}
		
		//accesser and mutator for 'broName'
		public void setBroName(String n){
			broName = n;
		}
		
		public String getBroName(){
			return broName;
		}
		
		//accesser and mutator for 'age'
		public void setAge(int a){
			age = a;
		}
		
		public int getAge(){
			return age;
		}
		
		//accesser and mutator for 'height'
		public void setHeight(int h){
			height = h;
		}
		
		public int getHeight(){
			return height;
		}
		
		//accesser and mutator for 'weight'
		public void setWeight(int w){
			weight = w;
		}
		
		public int getWeight(){
			return weight;
		}

	//assign sprite
	
}
