package dunn.ted.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ted on 3/1/2017.
 */
public class BlogDAOImplementation implements BlogDAO {
    private List<BlogEntry> blog;

    public BlogDAOImplementation() {
        blog = new ArrayList<>();
    }

    @Override
    public boolean add(BlogEntry entry) {
        return blog.add(entry);
    }

    @Override
    public boolean delete(BlogEntry entry) {
        return blog.remove(entry);
    }

    @Override
    public List<BlogEntry> findAll() {
        return new ArrayList<>(blog);
    }

    @Override
    public BlogEntry findEntryBySlug(String slug) {
        return blog.stream()
                .filter(entry -> entry.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);

    }

}
