package fr.triedge.web.scraper.ctrl;

import fr.triedge.web.scraper.api.PageScraper;
import fr.triedge.web.scraper.api.Storage;
import fr.triedge.web.scraper.model.Blog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@RestController
public class ScraperController {

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("home.html");
        PageScraper p = new PageScraper();
        ArrayList<Blog> blogs = null;

        try {
            blogs = Storage.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (blogs == null || blogs.size() == 0){
            try {
                blogs = refresh();
            } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
                throw new RuntimeException(e);
            }
        }

        model.addObject("blogs", blogs);
        return model;
    }

    @GetMapping("refresh")
    public ModelAndView refreshPage(){
        try {
            refresh();
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("redirect:/");
    }

    public ArrayList<Blog> refresh() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        PageScraper p = new PageScraper();
        ArrayList<Blog> blogs = p.parse();
        Storage.store(blogs);
        return blogs;
    }
}
