package dunn.ted.java.dao;

import dunn.ted.java.model.BlogEntry;

import java.util.List;

/**
 * Created by Ted on 3/1/2017.
 */
public interface BlogDAO {
    boolean add(BlogEntry entry);

    boolean delete(BlogEntry entry);

    List<BlogEntry> findAll();

    BlogEntry findEntryBySlug(String slug);

}
