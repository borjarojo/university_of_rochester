package zach;

public class ZChar {
	
	//Setting variables
	private String name = "";
	private int age;
	private int height;
	private int weight;
	private String info = "";
	
	//Making a constructor without 'info', that goes into it's own set-er
	public ZChar(String a, int x, int y, int z){
		name = "a";
		age = x;
		height = y;
		weight = z;
	}
	
	//get and set name
	public String getName(){
		return name;
	}
	
	public void setName(String a){
		name = "a";
	}
	
	//get and set age
	public int getAge(){
		return age;
	}
	
	public void setAge(int a){
		age = a;
	}

}
