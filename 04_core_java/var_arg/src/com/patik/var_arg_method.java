

package com.patik;

public class var_arg_method{
	
	
	public static void m1(int... x) {
		
		System.out.println("var - arg method");
	}
	
	public static void main(String[] args) {
		
		m1();
		m1(1);
		m1(2);
		m1(1,2,3,4);
		m1(1,2,3,4,5,6,3);
	}
	
	
}
