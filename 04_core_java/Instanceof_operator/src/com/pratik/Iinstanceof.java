//package com.pratik;
//
//public class Iinstanceof {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

package com.pratik;
public class  Iinstanceof{
	
	public static void main(String[] args) {
		
	  Thread t = new Thread();
		
		System.out.println(t instanceof Thread);
		
		System.out.println(t instanceof Object);
		
		//System.out.println(t instanceof String);
		System.out.println(null instanceof Object);
	}
	
	
}
