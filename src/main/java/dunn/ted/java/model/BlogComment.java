package dunn.ted.java.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ted on 3/1/2017.
 */
public class BlogComment {
    private String author;
    private String dateTime;
    private String comment;

    public BlogComment(String author, String comment) {
        this.author = author;
        this.comment = comment;
        LocalDateTime thisTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM d, yyyy  h:mm a");
        this.dateTime = thisTime.format(format);

    }

    public String getAuthor() {
        return author;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getComment() {
        return comment;
    }
}
