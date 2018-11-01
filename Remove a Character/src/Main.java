import java.util.Scanner; //For user input

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);	//Create a scanner to take in user input
		
		System.out.println("Please enter a string");
		String userInput = in.nextLine();	//Take in user input and store in a String variable
		
		System.out.println("Please enter a character that you would like to remove from this string");
		char charToRemove = in.next().charAt(0);	//Take in a single character that the user enters. If the user inputs more than one character I only use the first
		String removeChar = String.valueOf(charToRemove);	//Set a string equal to the indicated character, in order to call the .equals method later
		
		System.out.println("Removing the character");
		System.out.println();
		
		//Loop through the user input in order to find all of the characters that need to be removed
		for (int i = 0; i < userInput.length(); i++) {
			String temp = String.valueOf(userInput.charAt(i));	//Temp variable for comparing an index in the user input to the character that they wanted to remove
			// If we find a character that should be removed, remove it
			if (temp.equals(removeChar)) {	
				userInput = userInput.substring(0, i) + userInput.substring(i + 1);	//Remove a character by concatenating the sub-string before and the sub-string after the indicated character
			}
		}
		System.out.println(userInput);
	}

}