

package instance;
public class instance_variable{
	
	int x = 10;
	
	public static void main(String[] args) {
		 
		//System.out.println(x);
		
      instance_variable i = new instance_variable();
      
      System.out.println(i.x);
      
         i.print();
         new instance_variable().print();
         
      
      
		
		
	}
	
	public void print() {
		
		System.out.println(x);
	}
	
}
