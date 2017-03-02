package dunn.ted.java;

import dunn.ted.java.model.BlogEntry;
import dunn.ted.java.model.BlogEntryDAO;
import dunn.ted.java.model.BlogEntryDAOImplementation;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by Ted on 3/1/2017.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        BlogEntryDAO blog = new BlogEntryDAOImplementation();

        BlogEntry entry1 = new BlogEntry("My Thoughts on Monday ", "Mondays are no fun!");
        blog.add(entry1);
        BlogEntry entry2 = new BlogEntry("My Thoughts on Wednesday", "Hump Day!");
        blog.add(entry2);
        BlogEntry entry3 = new BlogEntry("My Thoughts on Saturday", "Woo hoo! The weekend!");
        blog.add(entry3);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entries", blog.findAll());
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.hbs"));
        });

        get("/detail.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entry", entry1);
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "detail.hbs"));
        });
    }
}


