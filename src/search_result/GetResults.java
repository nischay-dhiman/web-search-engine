package search_result;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class GetResults {

	public static String[] searchResults(HashMap<String, HashMap<String, Integer>> wordHashTable, String nearest_word) {
		
		String[] searchResults = new String[10];
		HashMap sites_data = wordHashTable.get(nearest_word);
		
		if(sites_data != null) {
			Set<String> result_keys = sites_data.keySet();
			List<String> list = new ArrayList<>(result_keys);
			Collections.reverse(list);
	
	
			int i = 0;
			while(i < list.size() && i < 10) {
				searchResults[i] = list.get(i);
				i += 1;
			}
		}
		else {
			searchResults = null;
		}
		
		return searchResults;
	}
}
