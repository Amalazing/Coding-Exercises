import java.util.Scanner;	//Needed for taking in user input

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);	//Create a scanner to taking user input
		
		String userInput = in.nextLine();	//Store user input as a String
		String palindrone = "";
		char[] checker = new char[userInput.length()];	//Create an array to store each character of the user input
		
		
		System.out.println("Thank you for your string. Time to check if " + userInput + " is a palindrome or not.");
		System.out.println("Checking...");
		System.out.println();
		
		//Loop to input the user input backwards into the palindrome variable
		for (int i = checker.length - 1; i > -1; i--) {
			checker[i] = userInput.charAt(i);	//Store each character into the checker array
			palindrone += checker[i];	//Add each character of the array to the palindrome String
		}
		
		System.out.println(userInput);
		System.out.println(palindrone);
		
		System.out.println();
		//If the user's input is equal in value to it self backwards than run, if not run the else statment
		if (userInput.equalsIgnoreCase(palindrone)) {
			System.out.println("Yes this is a palindrome");
		}else {
			System.out.println("No this is not a palnindrome");
		}

	}

}