package user_input;

import java.util.Scanner;

import autocorrect.Autocorrect;

public class UserInput {
	public static String[] userInput() {

		System.out.println( "Type keywords and press enter to search: ");
				
		Scanner sc = new Scanner(System.in);
		
		String searchInput = sc.nextLine();
		
		String[] searchKeys = searchInput.split("\\s");
				
		
		for(int j = 0; j < searchKeys.length; j++) {
			searchKeys[j] = Autocorrect.autocorrectedWord(searchKeys[j]);
		}
		return searchKeys;
	}
	
}
