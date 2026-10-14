package com.patik;

public class var_arg_array{
	
	public static void main(String [] args) {
		
		int[] a= {1,20,30};
		int[] b= {1,34,54};
		
		m1(a,b);
	}
	
	public static void m1(int[]...x) {
		
		for(int[] x1:x) {
			
			System.out.println(x1[1]);
		}
	}
	
	
}