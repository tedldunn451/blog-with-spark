package dunn.ted.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ted on 3/1/2017.
 */
public class BlogEntryDAOImplementation implements BlogEntryDAO {
    private List<BlogEntry> blog;

    public BlogEntryDAOImplementation() {
        blog = new ArrayList<>();
    }

    @Override
    public boolean add(BlogEntry entry) {
        return blog.add(entry);
    }

    @Override
    public List<BlogEntry> findAll() {
        return new ArrayList<>(blog);
    }
}
