package dunn.ted.java.model;

import java.time.LocalDateTime;

/**
 * Created by Ted on 3/1/2017.
 */
public class BlogComment {
    private String author;
    private LocalDateTime dateTime;
    private String comment;

    public BlogComment(String author, String comment) {
        this.author = author;
        this.comment = comment;
        dateTime = LocalDateTime.now();
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getComment() {
        return comment;
    }
}
