package search_result;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.PriorityQueue;

public class GetResults {

	public static String[] searchResults(Hashtable<String, Hashtable<String, Integer>> urlsHashTable, String[] searchKeys) {
		
		String[] searchResults = new String[10];
		
		PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(Map.Entry.comparingByValue(Comparator.reverseOrder())); 
		
		urlsHashTable.forEach((String str, Hashtable<String, Integer> ht) -> {
			int score = 0;
			for(int j = 0; j < searchKeys.length; j++) {
				
				String searchKey = searchKeys[j].toLowerCase();
				
				Integer word_freq = ht.get(searchKey);
				if(word_freq != null){
					score += (int) ht.get(searchKey);
				}
			}
			
			maxHeap.offer(new AbstractMap.SimpleEntry<>(str, score));
		});


		for(int i = 0 ; i < 10 ; i ++) {
			searchResults[i] = maxHeap.poll().getKey();
		}
		
		return searchResults;
	}
}
