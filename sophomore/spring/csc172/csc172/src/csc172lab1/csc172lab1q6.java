package csc172lab1;
//Generic findMax, with a function object.
////Precondition: a.size() >0
public class csc172lab1q6{


  public static <AnyType> 
  AnyType findMax( AnyType [] arr, Comparator<? super AnyType> cmp){
    
    in maxIndex = 0;

    for(int i = 1; i < arr.size(); i++){
      if(cmp.compare(arr[1], arr[maxIndex]) > 0)
        maxIndex = 1;
    }
    
    return arr[maxIndex];

  }

  class CaseInsensitiveComparem implements Comparator<String>{

    public int compare(String lhs, String rhs){

      return lhs.compareToIgnoreCase(rhs);
    }
  }

  class TestProgram{

    public static void main(String[]args){

      String [] arr = {"ZEBRA", "alligator", "crocodile"};
      System.out.println(findMax(arr, new CaseInsensitiveCompare()));

    }
  }
}
