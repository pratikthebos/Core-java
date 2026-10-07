

package com.pratik;

import java.util.Arrays;

public class array_object{
	
	public static void main(String[] args) {
		
//		Object[] o=new Object[6];
//		
//		o[0]="pratik";
//		o[1]=23;
//	    o[2]='f';
//	    o[3]=new Integer(10);
//	    o[4]=new String("kambale");
//	    
//		
//	   // System.out.println(o);
//	    System.out.println(Arrays.deepToString(o));
	    
		Number[] n = new Number [10];
		n[0]=new Integer(10);
		n[1]=new Double(12.5);
		n[2]=4;
		n[3]=new Long(345);
		n[4]=new Double(45.5);
		n[5]=new Float(2.4);
		n[6]= (byte)1;
		
		System.out.println(n);
		System.out.println(Arrays.toString(n));
		
	     
		
		
		
	}
}
