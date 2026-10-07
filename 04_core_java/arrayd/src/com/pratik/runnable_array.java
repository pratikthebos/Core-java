

package com.pratik;

import java.util.Arrays;

public class runnable_array{
	
	public static void main(String[] args) {
		
		Runnable[] s= new Runnable[10];
		s[0]= new Thread();
		System.out.println(s);
		System.out.println(Arrays.deepToString(s));
		
//		
//		int [] x= new int[0];
//		
//		System.out.println("\n"+x);
//		System.out.println(Arrays.toString(x));
//		
//		
//		short[] b=new short[] {1,2,3,4,4};
//		
//		System.out.println(Arrays.toString(b));
//		
		
//		int[][] x=new int[2][];
//		x[0]=new int[2];
//		x[1]=new int[3];
//		
//		System.out.println(Arrays.deepToString(x));
		
		int[][][] a=new int[3][][];
	    a[0] = new int[3][];
	    a[0][0]=new int[1];
	    a[0][1]=new int[2];
	    a[0][2]=new int[3];
	    
	    a[1] = new int[2][2];
	    a[2]=new int[2][2];
	    
	    System.out.println(Arrays.deepToString(a));
	   	
		
	}
	
	
	
	
	
	
}
