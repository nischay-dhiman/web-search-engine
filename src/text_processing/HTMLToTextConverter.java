package text_processing;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HTMLToTextConverter {
    public static final String HTML_PATH = "src/html/";
    public static final String TEXT_PATH = "src/text/";

    /**
     * List all files in a directory
     * Reference: https://www.baeldung.com/java-list-directory-files
     * @param dir directory to list files
     * @return list of files inside the directory
     */
    public static ArrayList<String> ListFilesInDirectory(String dir) throws IOException {
        ArrayList<String> fileList = new ArrayList<>();
        DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir));
        for (Path path : stream) {
            if (!Files.isDirectory(path)) {
                fileList.add(path.getFileName().toString());
            }
        }

        return fileList;
    }

    /**
     * Convert HTML files into text files
     * @param filename html filename
     * @throws IOException error
     */
    public static void HTMLtoTextConverter(String filename) throws IOException {
        // Read html file content
        In htmlFile = new In(HTML_PATH + filename);
        String htmlString = htmlFile.readAll();

        // parse & clean html to text using JSOUP
        String parsedText = Jsoup.parse(htmlString).text();
        String cleanText = Jsoup.clean(parsedText, Safelist.simpleText());

        // Write text into txt files
        String outputFilePath = TEXT_PATH + filename.replace(".htm", ".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
        bw.write(cleanText);
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        // list all html filenames
        ArrayList<String> files = ListFilesInDirectory(HTML_PATH);

        // loop over each file and convert it
        for (String file: files) {
            HTMLtoTextConverter(file);
            System.out.println("Converted " + file);
        }
    }
}
