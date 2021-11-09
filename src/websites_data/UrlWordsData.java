package websites_data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class UrlWordsData {
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
					
					Hashtable<String, Integer> wordHashTable = new Hashtable<String, Integer>();
					
					for (int i = 0 ; i < fileWords.length; i ++) {
						
						fileWords[i] = fileWords[i].replaceAll("[^a-zA-Z]", "");
						
						if(fileWords[i].length() > 10) {
							continue;
						}
						String str = fileWords[i].toLowerCase();
						
						Integer wrdCnt = wordHashTable.get(str);
						if(wrdCnt != null){
							wordHashTable.put(str, wrdCnt + 1);
						}else {
							wordHashTable.put(str, 1);
						}
					}
					
					urlsHashTable.put(file.getName(), wordHashTable);
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
