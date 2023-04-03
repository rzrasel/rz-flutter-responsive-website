import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DomReader {
    public static void main(String[] args) {
        //System.out.println("DEBUG_LOG_PRINT: Test me");
        (new Thread() {
            @Override
            public void run() {
                //super.run();
                try {
                    new DomReader().domRead();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void domRead() throws IOException {}
    private void domReadTest01() throws IOException {
        String pageUrl = "https://www.youtube.com/watch?v=6-t5GoZ8Boo";
        //Document document = Jsoup.parse(pageUrl);
        // TODO:
        Document document = Jsoup.connect(pageUrl).get();
        // Print Html data
        //System.out.println("DEBUG_LOG_PRINT: document: " + document.html());
        // Print page title
        System.out.println("DEBUG_LOG_PRINT: document: " + document.title());
        //document.select(".ytd-channel-name").forEach(System.out::println);
        //String title = document.getElementById("eow-title").text();
        Elements elements = document.getElementsByClass("style-scope ytd-channel-name");
        System.out.println("DEBUG_LOG_PRINT: element size: " + elements.size());
        for (Element item : elements) {
            System.out.println("DEBUG_LOG_PRINT: channel name: " + item.text());
        }
        elements = document.select("style-scope ytd-channel-name");
        System.out.println("DEBUG_LOG_PRINT: element size: " + elements.size());
        for (Element item : elements) {
            System.out.println("DEBUG_LOG_PRINT: channel name: " + item.text());
        }
        // FIXME:
    }
}
/*

Reading HTML file to DOM tree using Java
https://stackoverflow.com/questions/457684/reading-html-file-to-dom-tree-using-java
https://www.baeldung.com/java-with-jsoup
https://stackoverflow.com/questions/2000218/what-is-inline-thread

https://stackoverflow.com/questions/46157695/jsoup-retrieve-youtube-title

*/