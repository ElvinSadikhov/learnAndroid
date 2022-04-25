import java.util.Scanner;

public class Fibonachi_sequence_rec {
	public int findFibonachi(int n) {
		int fst = 0;
		int sec = 1;
		int temp;
		
		if (n == 0)
			return -1;
		if (n == 1)
			return fst;

		for (int i = 0; i < n - 2; i++) {
			temp = fst;
			fst = sec;
			sec = temp + sec;
		}
		return sec;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Type an n(integer): ");
		int num = in.nextInt();

		Fibonachi_sequence_rec fib = new Fibonachi_sequence_rec();
		int res = fib.findFibonachi(num);
		if (res < 0)
			System.out.println("You had to add a positive number!");
		else
			System.out.println(String.format("%d'th Fibonachi number is %d!", num, res));
	}
}
