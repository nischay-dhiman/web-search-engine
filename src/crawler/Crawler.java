package crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Crawler {
    public static final String DOMAIN = "https://edition.cnn.com/";
    private static final Integer MAX_LEVEL = 2;

    private static Document download(String url, ArrayList<String> visited) {
        try {
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();

            if (connection.response().statusCode() != 200) {
                System.out.println("Failed to connect to: " + url);
                return null;
            }


            if (document.title().equals("")) {
                return null;
            }

            visited.add(url);
            System.out.println("Visited: " + url);

            String filename = Slugify.Make(document.title()) + ".html";
            File.Write("src/html/" + filename, document.outerHtml());

            return document;
        } catch (IOException e) {
            return null;
        }
    }

    private static void crawl(int level, String url, ArrayList<String> visited) {
        if (level > MAX_LEVEL) return;

        Document doc = download(url, visited);
        if (doc == null) return;

        for (Element link : doc.select("a[href]")) {
            String next_link = link.absUrl("href");
            if (!visited.contains(next_link) && next_link.contains(DOMAIN)) {
                crawl(level++, next_link, visited);
            }
        }
    }

    public static void main(String[] args) {
        crawl(1, DOMAIN, new ArrayList<String>());
    }
}
