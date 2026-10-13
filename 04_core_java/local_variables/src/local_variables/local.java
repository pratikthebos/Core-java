

package local_variables;


public class local{
	
	public static void main(String[] args) {
		
		try {
			
			int j = Integer.parseInt("ten");
		}catch(NumberFormatException e) {
			
			j=10;
		}
		System.out.println(j);
	}
}
