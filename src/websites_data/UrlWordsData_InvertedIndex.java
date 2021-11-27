package websites_data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONObject;



public class UrlWordsData_InvertedIndex {
	
	
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<HashMap.Entry<String, Integer> > list =
               new LinkedList<HashMap.Entry<String, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<HashMap.Entry<String, Integer> >() {
            public int compare(HashMap.Entry<String, Integer> o1,
            		HashMap.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (HashMap.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
	
	public  HashMap<String, Integer> sortHashMapByValues(
	        HashMap<String, Integer> passedMap) {
	    List<String> mapKeys = new ArrayList<>(passedMap.keySet());
	    List<Integer> mapValues = new ArrayList<>(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    HashMap<String, Integer> sortedMap =
	        new HashMap<>();

	    Iterator<Integer> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Integer val = valueIt.next();
	        Iterator<String> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            String key = keyIt.next();
	            Integer comp1 = passedMap.get(key);
	            Integer comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    
	    return sortedMap;
	}
	
	
	public static HashMap<String, HashMap<String, Integer>> GetUrlWordsData() throws IOException{
		
		HashMap<String, HashMap<String, Integer>> urlsHashTable = new HashMap<String, HashMap<String, Integer>>();
		
		
		List<File> files;
		try {
			files = Files.list(Paths.get("DataSource/W3C Web Pages/Text"))
					.filter(Files::isRegularFile)
					.map(Path::toFile)
					.collect(Collectors.toList());
			
			files.forEach( file -> {

				try {
					
					HashMap<String, Integer> fileHashTable = null;
					
					String fileContent = new String(Files.readAllBytes(file.toPath()));
					
					String[] fileWords = fileContent.split("\\s");
					
										
					for (int i = 0 ; i < fileWords.length; i ++) {
						
						fileWords[i] = fileWords[i].replaceAll("[^a-zA-Z]", "");
						
						if(fileWords[i].length() > 10) {
							continue;
						}
						String str = fileWords[i].toLowerCase();
						
						
						
						if(urlsHashTable.get(str) != null) {
							
							fileHashTable = urlsHashTable.get(str);
							
						}else {
							
							urlsHashTable.put(str, new HashMap<String, Integer>());
							fileHashTable = urlsHashTable.get(str);
						}
						
						if(fileHashTable.get(file.getName()) != null){
							fileHashTable.put(file.getName(), fileHashTable.get(file.getName()) + 1);
						}else {
							fileHashTable.put(file.getName(), 1);
						}
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return urlsHashTable;
	}
	
	public static void main(String args[]) throws IOException {
		
		
		HashMap<String, HashMap<String, Integer>> tempHashMap =  GetUrlWordsData();
		
		for(Map.Entry<String,HashMap<String,Integer>> en : tempHashMap.entrySet()) {
			
			HashMap<String,Integer> hm = en.getValue();
			HashMap<String,Integer> hm1 = new UrlWordsData_InvertedIndex().sortHashMapByValues(hm);
			tempHashMap.put(en.getKey(), hm1);
			System.out.println(hm1);
		}
		 
		JSONObject json = new JSONObject(tempHashMap);
		
		FileWriter file = new FileWriter("G:\\UniversityofWindsor\\Semester1\\COMP-8547_AdvancedComputingConcepts\\crunchify.txt");
        file.write(json.toString());
		
	}

}
