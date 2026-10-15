package com.pratik;

public class args{
	
	public static void main(String... args) {
		
		String[] argh = {"pratik","kambale","vijay"};
		
		args=argh;
		
	   for(String s:args) {
		   
		   System.out.println(s);
	   }
	}
}
