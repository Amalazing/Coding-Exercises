import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int userInput = 0;
		boolean power = true;

		while (power) {
			System.out.println("Please select an option");
			System.out.println("1 : Find prime");
			System.out.println("2 : Quit program");

			userInput = in.nextInt();

			if (userInput == 1) {

				System.out.println(
						"Nth prime number desired? (enter a number representing the nth prime you are looking for)");
				int nth = in.nextInt();
				int num = 1;
				int count = 0;
				int i = 2;

				while (count < nth) {
					num = num + 1;
					for (i = 2; i <= num; i++) {
						if (num % i == 0) {
							break;
						}
					}
					if (i == num) {
						count = count + 1;
					}
				}
				System.out.println("Value of nth prime: " + num);
				System.out.println();
				System.out.println();

			} else {
				power = false;
			}

		}

	}

}
