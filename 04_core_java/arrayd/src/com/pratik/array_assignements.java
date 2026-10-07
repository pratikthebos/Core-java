

package com.pratik;

import java.util.Arrays;

public class array_assignements{
	
	public static void main(String[] args) {
		
		int[] x = new int[5];
		
		x[0]=2;
		short s = 32_767;
		x[1]=s;
		
	    byte b = 1_27;
	    x[2]=b;
	    
	    
	    int i = 21_4748_36_47;
	    x[3]=i;
	    
	    x[4]=6;
	    
	    
	    
	    System.out.print("output is "+Arrays.toString(x));
	    
	    
	    
	}
}
