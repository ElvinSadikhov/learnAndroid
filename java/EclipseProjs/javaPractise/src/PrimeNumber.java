import java.util.Scanner;

public class PrimeNumber {

	public boolean isPrime(int num) {
		if (num == 2)
			return true;
		int d = 2;
		while (d <= Math.sqrt(num)) {
			if (num % d == 0)
				return false;
			d += 1;
		}
		return true;

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Type a positive number(n>1): ");
		int num;
		while (true) {
			num = in.nextInt();
			if (num < 1)
				System.out.println("Try again!");
			else
				break;
		}
		PrimeNumber obj = new PrimeNumber();
		if (obj.isPrime(num)) {
			System.out.println("This number is prime!");
		} else
			System.out.println("THIS IS NOT A PRIME NUMBER!");
	}

}
