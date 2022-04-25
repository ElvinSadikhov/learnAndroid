import java.util.Scanner;

public class Armstrong_number {

	public boolean checkForArmstrong(int num) {
		int sum = 0;
		int copyOfNum = num;
		StringBuilder temp = new StringBuilder(String.format("%s", num));
		int power = temp.length();

		while (num > 0) {
			sum += Math.pow(num % 10, power);
			num = num / 10;
		}
		return sum == copyOfNum;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Type a number to check for being Armstrong number: ");
		int numberToCheck;
		while (true) {
			numberToCheck = in.nextInt();
			if (numberToCheck >= 0)
				break;
			System.out.println("Type a positive number!");
		}

		Armstrong_number obj = new Armstrong_number();
		if (obj.checkForArmstrong(numberToCheck)) {
			System.out.println("It is an Armstrong number!");
		} else
			System.out.println("IT IS NOT AN ARMSTRONG NUMBER!");

	}

}
