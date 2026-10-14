package com.patik;

public class sum_method{
	
	public static void main(String[] args) {
		
		sum();
		sum(10,20);
		sum(12,34,56);
		sum(12,34,23,54);
		}
	
	 public static void sum(int ...x) {
		
		int total=0;
		for(int x1:x) {
			
			total = total+x1;
			}
		System.out.println("The sum :"+total);
	}
	
}
