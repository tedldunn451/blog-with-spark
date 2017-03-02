package dunn.ted.java.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ted on 3/1/2017.
 */
public class BlogEntry {
    private String title;
    private LocalDateTime dateTime;
    private String body;
    private List<BlogComment> comments;

    public BlogEntry(String title, String body) {
        this.title = title;
        this.body = body;
        dateTime = LocalDateTime.now();
        comments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getBody() {
        return body;
    }

    public List<BlogComment> getComments() {
        return comments;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEntry(String body) {
        this.body = body;
    }
}
