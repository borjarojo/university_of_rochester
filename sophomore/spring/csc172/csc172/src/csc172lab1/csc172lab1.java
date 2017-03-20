package csc172lab1;
//Author: Borja Rojo

public class csc172lab1 {
  
  public static void main(String [] args){
    Integer [] intArry = {1, 2, 3, 4, 5};
    Double [] doubArry = {1.1, 2.2, 3.3, 4.4, 5.5};
    Character [] charArry = {'H', 'E', 'L', 'L', 'O'};
    String [] strArry = {"once", "upon", "a", "time"};

    printarry(intArry);
    printarry(doubArry);
    printarry(charArry);
    printarry(strArry);

    System.out.println("max Integer is: "  + getMax(intArry));
    System.out.println("max Double is: " + getMax(doubArry));
    System.out.println("max Character is: " + getMax(charArry));
    System.out.println("max String is: " + getMax(strArry));
  }

/*
  public static void printarry(Object [] o){
    for(Object thing : o){
      System.out.println(thing);
    }
  }

  public static void printarry(Integer [] arry){
    for(Integer item : arry){
      System.out.println(item);
    }
  }

  public static void printarry(Double [] arry){
    for(Double item : arry){
      System.out.println(item);
    }
  }

  public static void printarry(Character [] arry){
    for(Character item : arry){
      System.out.println(item);
    }
  }

  public static void printarry(String [] arry){
    for(String item : arry){
      System.out.println(item);
    }
  }
*/


  public static <ArrayType> void printarry(ArrayType [] arry){
    for(ArrayType item : arry){
      System.out.println(item);
    }
  }

/*  
  public static Comparable getMax(Comparable [] a){
    Comparable maxIndex = a[0];

    for(int i = 0; i < a.length; i++){
      if(a[i].compareTo(maxIndex) > 0){
        maxIndex = a[i];
      }
    }

    return maxIndex;
  }
*/

  public static <ArrayType extends Comparable<ArrayType>> Comparable getMax(ArrayType [] a){
    ArrayType max = a[0];
    for(int i = 0; i < a.length; i++){
      if(a[i].compareTo(max) > 0){
        max = a[i];  
      }
    }
    return max;
  }
}
