
import java.util.Scanner;
public class TestKisi {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter person's name: ");
		String name = in.nextLine();
		Kisi insan = new Kisi(name);
		System.out.print("Enter age: ");
		int age = in.nextInt();
		try {
			insan.setYas(age);
			System.out.println(insan);
		} 
		catch (OlanaksizBilgi e) {
			e.printStackTrace();
		}
		in.close();
	}

}
