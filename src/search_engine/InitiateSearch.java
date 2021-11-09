package search_engine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

import autocorrect.Autocorrect;
import search_result.GetResults;
import user_input.UserInput;
import websites_data.UrlWordsData;

public class InitiateSearch {
	
	
	public static void main(String[] args) {

		Hashtable<String, Hashtable<String, Integer>> urlsHashTable = UrlWordsData.GetUrlWordsData();

		String[] searchKeys = UserInput.userInput(); 
		
		String[] resultSites = GetResults.searchResults(urlsHashTable, searchKeys);
		
		System.out.println( "Top 10 Search Results for - " + String.join(" ", searchKeys) + "\n");
		
		for(int i = 0 ; i < 10 ; i ++) {
			System.out.println(resultSites[i]);
		}
		
	}

}
