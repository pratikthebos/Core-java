

package com.pratik;

public class anonymous{
	
	public static void main(String[] args) {
		
		sum(new int[] {10,10,10,10,10});
		
	}
	
	public static void sum(int[] x) {
		
		
		int total = 0;
		for(int x1:x) {
			
			total+=x1;
			
		}
		
		System.out.println("sum = "+total);
	}
		
		
		
	
}


	
	
