package wrappers;

public class Test {
	public static void main (String[] args) {
		String myString = "12345";
		
		Integer wrapA = 1;
		Integer wrapB = 2;
		
		System.out.println("a: "+ wrapA + " b: " + wrapB);
		
		Integer tmp = wrapA;
		wrapA = wrapB;
		wrapB = tmp;
		
		System.out.println("a: "+ wrapA + " b: " + wrapB);
	}
}
