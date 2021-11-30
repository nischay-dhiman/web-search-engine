package crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Web Crawler
 * Using DFS to find all hyperlinks and download as HTML files
 */
public class Crawler {
    // Using this level to prevent crawling all pages which may take a lot of time
    private static final Integer MAX_LEVEL = 5;
    // Where the output HTML files store
    public static final String HTML_PATH = "src/html/";
    // The domain name to crawl
    public static final String DOMAIN = "https://windsorstar.com";

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
            File.Write(HTML_PATH + filename, document.outerHtml());

            return document;
        } catch (IOException e) {
            return null;
        }
    }

    private static void crawl(int level, String url, ArrayList<String> visited) {
        // the crawler will stop if it reaches the max level
        if (level > MAX_LEVEL) return;

        Document doc = download(url, visited);
        if (doc == null) return;

        // find all the links in a page
        Elements links = doc.select("a[href]");
        // for each link, we will access the page and find all its hyperlinks
        for (Element link : links) {
            String href = link.attr("href");
            // only crawler internal pages
            if (href.startsWith("#") || href.startsWith("?") || href.endsWith("/") || href.length() < 50) continue;
            String next_link = link.absUrl("href");
            // if the page is visited, ignore it
            if (!visited.contains(next_link) && next_link.startsWith(DOMAIN)) {
                crawl(level++, next_link, visited);
            }
        }
    }

    public static void main(String[] args) {
        crawl(1, DOMAIN, new ArrayList<String>());
    }
}
