package dunn.ted.java;

import dunn.ted.java.model.BlogComment;
import dunn.ted.java.model.BlogEntry;
import dunn.ted.java.dao.BlogDAO;
import dunn.ted.java.dao.BlogDAOImplementation;
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
        BlogDAO blog = new BlogDAOImplementation();

        BlogEntry entry1 = new BlogEntry("My Thoughts on Monday ", "Mondays are no fun!");
        BlogComment comment1 = new BlogComment("Fred Jones", "FIRST!");
        entry1.addComment(comment1);
        blog.add(entry1);
        BlogEntry entry2 = new BlogEntry("My Thoughts on Wednesday", "Hump Day!");
        blog.add(entry2);
        BlogEntry entry3 = new BlogEntry("My Thoughts on Saturday", "Woo hoo! The weekend!");
        blog.add(entry3);

        before((req, res) -> {
            if (req.cookie("username") != null) {
                req.attribute("username", req.cookie("username"));
            }
        });

/*
        before("/new", (req, res) -> {
            if (req.cookie("username") != "admin") {
                //setFlashMessage(req,"Whoops, please sign in first!");
                res.redirect("login");
                halt();
            }
        });
*/

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entries", blog.findAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/detail/:slug", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entry", blog.findEntryBySlug(req.params("slug")));
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        post("/detail/:slug/comment", (req, res) -> {
            BlogEntry entry = blog.findEntryBySlug(req.params("slug"));
            String author = req.queryParams("author");
            String comment = req.queryParams("comment");
            entry.addComment(new BlogComment(author, comment));
            res.redirect("/detail/" + entry.getSlug());
            return null;
        });

        get("/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "new.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new", (req, res) -> {
            BlogEntry entry = new BlogEntry(req.queryParams("title"), req.queryParams("entry"));
            blog.add(entry);
            res.redirect("/");
            return null;
        });

/*
        get("/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "login.hbs");
        }, new HandlebarsTemplateEngine());

        post("/login", (req, res) -> {
            String password = req.queryParams("username");
            res.cookie("username", password);
            if (req.cookie("username") == password) {
                res.redirect("/new");
            } else {
                res.redirect("/");
            }
            return null;
        });
*/
    }
}


