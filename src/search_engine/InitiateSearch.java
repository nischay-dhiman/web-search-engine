package search_engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

import autocorrect.Autocorrect;
import search_result.GetResults;
import search_result.OutResults;
import user_input.UserInput;
import websites_data.UrlWordsData;

public class InitiateSearch {
	public static HashMap getDictionary() throws IOException {
		FileInputStream fileIn = new FileInputStream("crunchify.ser");
		HashMap<String, HashMap<String, Integer>> dictionary = new HashMap();
        try (ObjectInputStream in = new ObjectInputStream(fileIn)) {
        	dictionary = (HashMap<String, HashMap<String, Integer>>) in.readObject();
			return dictionary;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return dictionary;
	}

	public static void main(String[] args) throws IOException {
		HashMap<String, HashMap<String, Integer>> wordHashTable = getDictionary();
		String searchKey = UserInput.userInput(wordHashTable);
		OutResults.print_results(wordHashTable, searchKey);
	}
}
