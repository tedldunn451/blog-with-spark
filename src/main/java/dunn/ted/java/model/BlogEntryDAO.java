package dunn.ted.java.model;

import java.util.List;

/**
 * Created by Ted on 3/1/2017.
 */
public interface BlogEntryDAO {
    boolean add(BlogEntry entry);

    boolean delete(BlogEntry entry);

    List<BlogEntry> findAll();
}
