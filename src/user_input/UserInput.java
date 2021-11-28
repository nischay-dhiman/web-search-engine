package user_input;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import autocorrect.Autocorrect;

public class UserInput {
	public static String userInput(HashMap<String, HashMap<String, Integer>> wordHashTable) throws IOException {
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("\nType keyword and press enter to search: ");
				
		Scanner sc = new Scanner(System.in);
		
		String searchInput = sc.nextLine();
		
		return searchInput;
	}
	
}
