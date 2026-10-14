

package com.patik;

public class arg_method{
	
	public static void m1(int... x) {
		
		System.out.println("int... ");
	}
	
	public static void m1(int[] x) {
		
		Sytem.out.println("int[] x");
	}
	
	
	public static void main(String[] args) {
		
		m1(12,3,4,5,6,3);
		
		
		
		
	}
}
