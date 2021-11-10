package websites_data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;



public class UrlWordsData_InvertedIndex {
	
	
	public static Hashtable<String, Hashtable<String, Integer>> GetUrlWordsData(){
		
		Hashtable<String, Hashtable<String, Integer>> urlsHashTable = new Hashtable<String, Hashtable<String, Integer>>();
		
		
		List<File> files;
		try {
			files = Files.list(Paths.get("DataSource/W3C Web Pages/Text"))
					.filter(Files::isRegularFile)
					.map(Path::toFile)
					.collect(Collectors.toList());
			
			files.forEach( file -> {

				try {
					String fileContent = new String(Files.readAllBytes(file.toPath()));
					
					String[] fileWords = fileContent.split("\\s");
					
										
					for (int i = 0 ; i < fileWords.length; i ++) {
						
						fileWords[i] = fileWords[i].replaceAll("[^a-zA-Z]", "");
						
						if(fileWords[i].length() > 10) {
							continue;
						}
						String str = fileWords[i].toLowerCase();
						
						Hashtable<String, Integer> fileHashTable = null;
						
						if(urlsHashTable.get(str) != null) {
							
							fileHashTable = urlsHashTable.get(str);
							
						}else {
							
							urlsHashTable.put(str, new Hashtable<String, Integer>());
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

}
