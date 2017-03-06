package dunn.ted.java;

import dunn.ted.java.model.BlogEntry;
import dunn.ted.java.model.BlogDAO;
import dunn.ted.java.model.BlogDAOImplementation;
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
        blog.add(entry1);
        BlogEntry entry2 = new BlogEntry("My Thoughts on Wednesday", "Hump Day!");
        blog.add(entry2);
        BlogEntry entry3 = new BlogEntry("My Thoughts on Saturday", "Woo hoo! The weekend!");
        blog.add(entry3);

        before((req, res) -> {
            if (req.cookie("password") != null) {
                req.attribute("password", req.cookie("password"));
            }
        });

/*
        before("/new", (req, res) -> {
            if (req.cookie("password") != "admin") {
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

        get("/detail", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entry", entry1);
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

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
            String password = req.queryParams("password");
            res.cookie("password", password);
            if (req.cookie("password") == password) {
                res.redirect("/new");
            } else {
                res.redirect("/");
            }
            return null;
        });
*/
    }
}


