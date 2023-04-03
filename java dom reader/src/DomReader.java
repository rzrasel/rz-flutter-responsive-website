import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomReader {
    public static void main(String[] args) {
        //System.out.println("DEBUG_LOG_PRINT: Test me");
        (new Thread() {
            @Override
            public void run() {
                //super.run();
                try {
                    new DomReader().domRead();
                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void domRead() throws IOException, JSONException {
        String ytVideoUrl = "https://www.youtube.com/watch?v=6-t5GoZ8Boo";
        String ytVideoId = getVideoId(ytVideoUrl);
        String ytApiUrl = getVideoApiUrl(ytVideoId);
        System.out.println("DEBUG_LOG_PRINT: ytVideoId: " + ytVideoId);
        System.out.println("DEBUG_LOG_PRINT: ytApiUrl: " + ytApiUrl);
        Document document = Jsoup.connect(ytApiUrl).get();
        String jsonString = document.text();
        //System.out.println("DEBUG_LOG_PRINT: jsonString: " + jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        System.out.println("DEBUG_LOG_PRINT: title: " + jsonObject.get(JSONKey.TITLE.tag));
        System.out.println("DEBUG_LOG_PRINT: author name: " + jsonObject.get(JSONKey.AUTHOR_NAME.tag));
        System.out.println("DEBUG_LOG_PRINT: thumbnail url: " + jsonObject.get(JSONKey.THUMBNAIL_URL.tag));
        /*System.out.println("DEBUG_LOG_PRINT: raw data: " + document.text());
        Elements player = document.select("div#player");
        System.out.println("DEBUG_LOG_PRINT: element size: " + player.size());
        for (Element item : player) {
            System.out.println("DEBUG_LOG_PRINT: channel name: " + item.html());
        }*/
    }

    private String getVideoApiUrl(String youTubeVideoId) {
        return "https://noembed.com/embed?url="
                + "https://www.youtube.com/watch?v="
                + youTubeVideoId;
    }

    private String getVideoId(String youTubeVideoUrl) {
        String pattern = "https?://(?:m.)?(?:www\\.)?youtu(?:\\.be/|(?:be-nocookie|be)\\.com/(?:watch|\\w+\\?(?:feature=\\w+.\\w+&)?v=|v/|e/|embed/|live/|shorts/|user/(?:[\\w#]+/)+))([^&#?\\n]+)";
        //String pattern = 'http(?:s)?:\/\/(?:m.)?(?:www\.)?youtu(?:\.be\/|(?:be-nocookie|be)\.com\/(?:watch|[\w]+\?(?:feature=[\w]+.[\w]+\&)?v=|v\/|e\/|embed\/|live\/|shorts\/|user\/(?:[\w#]+\/)+))([^&#?\n]+)';
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeVideoUrl);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

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

enum JSONKey {
    AUTHOR_NAME("author_name"),
    TITLE("title"),
    THUMBNAIL_URL("thumbnail_url"),
    TYPE("type");
    final String tag;

    JSONKey(String tag) {
        this.tag = tag;
    }
}

/*

Reading HTML file to DOM tree using Java
https://stackoverflow.com/questions/457684/reading-html-file-to-dom-tree-using-java
https://www.baeldung.com/java-with-jsoup
https://stackoverflow.com/questions/2000218/what-is-inline-thread

https://stackoverflow.com/questions/46157695/jsoup-retrieve-youtube-title


soft word wrap intellij

In the Project tool window ( Alt+1 ), right-click the node in which you want to create a new class and select New | Java Class. Alternatively, select the node, press Alt+Insert , and select Java Class

Crt-l + Shift + "A" OR Help -> Find Action · Enter: "wrap" into text box · Toggle: View | Active Editor Soft-Wrap "ON".

hit Ctrl+Shift+F

YouTube Video title with API v3 without API key?
https://stackoverflow.com/questions/30084140/youtube-video-title-with-api-v3-without-api-key

How to get the video id from a YouTube URL with regex in Java
https://stackoverflow.com/questions/24048308/how-to-get-the-video-id-from-a-youtube-url-with-regex-in-java
http(?:s)?:\/\/(?:m.)?(?:www\.)?youtu(?:\.be\/|(?:be-nocookie|be)\.com\/(?:watch|[\w]+\?(?:feature=[\w]+.[\w]+\&)?v=|v\/|e\/|embed\/|live\/|shorts\/|user\/(?:[\w#]+\/)+))([^&#?\n]+)
*/