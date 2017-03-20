package csc172lab1;

import java.util.Comparator;

public class CaseInsensitiveCompare implements Comparator<Character>{
		public int compare(Character lhs, Character rhs){
			return lhs.compareToIgnoreCase(rhs);
		}
	}
