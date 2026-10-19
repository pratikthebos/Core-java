//package com.pratik;
//
//public class Type_check {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}


package com.pratik;

public class Type_check{
	
	public static void main(String[] args) {
		
		Thread t=new Thread();
		
		Object o = new Object();
		
		String s = new String ("PRARIK");
		
		System.out.println(t==o);
		System.out.println(o==s);
		System.out.println(s==t);
	}
}
