
package com.patik;

public class test{
	
	
	public static void m1(int... x) {
		
		System.out.println("var-arg method");
	}
	
	public static void m1(int x) {
		
		System.out.println("normal method");
	}
	
	public static void main(String[] args) {
		
		m1();
		m1(1,2);
		m1(10);
	}
}