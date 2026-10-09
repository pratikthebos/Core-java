

package instance;

public class static_variables{
	
	String name;
	int rollno;
	static String cname="pratik";
	
	public static void main(String[] args) {
		
		
	static_variables a = new static_variables();
	
	a.cname="cdac";
	
	System.out.println(a.cname);
	  
    static_variables s = new static_variables();
    
  
	s.name="pratik";
	s.rollno=4;
	s.cname="shankar";
	
	System.out.println(s.cname);
		  
		
	}
}
