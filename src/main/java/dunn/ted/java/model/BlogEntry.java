package dunn.ted.java.model;

import com.github.slugify.Slugify;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ted on 3/1/2017.
 */
public class BlogEntry {

    private String title;
    private String dateTime;
    private String body;
    private List<BlogComment> comments;
    private String slug;

    public BlogEntry(String title, String body) {
        this.title = title;
        this.body = body;
        LocalDateTime thisTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM d, yyyy  h:mm a");
        this.dateTime = thisTime.format(format);
        this.comments = new ArrayList<>();
        try {
            Slugify slugify = new Slugify();
            slug = slugify.slugify(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getTitle() {
        return title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getBody() {
        return body;
    }

    public List<BlogComment> getComments() {
        return comments;
    }

    public String getSlug() { return slug; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addComment(BlogComment comment) {  this.comments.add(comment); }
}
