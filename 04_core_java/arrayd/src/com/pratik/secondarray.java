

package com.pratik;
import java.util.Arrays;

public class secondarray{
	
	public static void main(String[] args) {
		
		System.out.print(Arrays.toString(new int[] {6,5,4,3,2,2}));
		
	 //new int[] {3,4,5,5,3};
		
	 //new int[][] {{1,2,3,4,5},{7,65,4,3,4}};
		
		System.out.print("\n\n"+Arrays.deepToString(new int[][] {{4,3,2,5,3,4},{1,2,3,4,5,5}}));
	 
		int [] x=new int[] {1,2,3,4,5,5,5};
		System.out.println("\n\nanswer = "+x);
		System.out.println("arryas.tostring"+Arrays.toString(x));
	}
}
