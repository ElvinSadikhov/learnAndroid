import java.util.Scanner;

public class Palindrome {
	public String reverse(String original) {
		String reversed = "";
		for (int i = original.length() - 1; i >= 0; i--) {
			reversed += original.charAt(i);
		}
		return reversed;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Type a value: ");
		String originalValue = in.nextLine();
		Palindrome obj = new Palindrome();
		if (obj.reverse(originalValue).equals(originalValue)) {
			System.out.println("This value is a Palindrome!");
		} else {
			System.out.println("THIS IS NOT A PALIDROME!");
		}
	}
}
