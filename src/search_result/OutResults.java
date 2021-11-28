package search_result;

import java.io.IOException;
import java.util.HashMap;

import autocorrect.Autocorrect;

public class OutResults {

	public static void print_results(HashMap<String, HashMap<String, Integer>> wordHashTable, String searchKey) throws IOException {
		// TODO Auto-generated method stub

		String[] resultSites = GetResults.searchResults(wordHashTable, searchKey);
		
		if(resultSites == null) {
			System.out.println("\n");
			System.out.println("No results for " + searchKey);
			String autocorrectedWord = Autocorrect.autocorrectedWord(wordHashTable, searchKey);
			resultSites = GetResults.searchResults(wordHashTable, autocorrectedWord);
			System.out.println("Showing results for " + autocorrectedWord);
		}
		
		System.out.println("\n");
		
		System.out.println( "Top Search Results -");
		
		System.out.println("");
		for(int i = 0 ; (i < resultSites.length && i < 10)  ; i ++) {
			if(resultSites[i] != null) {
				System.out.println(resultSites[i]);
			}
		}
	}
}
