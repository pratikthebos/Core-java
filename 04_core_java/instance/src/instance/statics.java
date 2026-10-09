//package instance;
//
//public class statics {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

package instance;

public class statics{
	
	static int x = 10;
	
	public static void main(String[] args) {
		
	   statics s = new statics();
	   System.out.println(s.x);
	   System.out.println(x);
	   System.out.println(statics.x);
	}
}
