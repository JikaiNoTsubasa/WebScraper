package fr.triedge.web.scraper.api;

import fr.triedge.web.scraper.model.Blog;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

public class PageScraper {

    private String url = "https://www.gamekyo.com";

    public ArrayList<Blog> parse() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.82";
        Document doc = Jsoup.connect(url).userAgent(userAgent).sslSocketFactory(SSLHelper.socketFactory()).get();

        ArrayList<Blog> bls = new ArrayList<>();
        Element blogList = doc.getElementById("blog-list");
        Elements blogs = blogList.children();
        Iterator<Element> it = blogs.iterator();
        while (it.hasNext()){
            Element el = it.next();
            Elements articleInfo = el.getElementsByClass("article-information");
            Blog b = new Blog();
            boolean isValid = false;
            if (articleInfo.size() > 0){
                isValid = true;
                Element info = articleInfo.get(0);
                Elements links = info.select("a");
                Element date = info.getElementsByClass("date").get(0);
                String title = links.get(0).html();
                String articleLink = url+links.get(0).attr("href");
                String author = links.get(1).html();
                String authorLink = url+links.get(1).attr("href");
                String dateStr = date.html().replace("at ","").replace("the ","").trim();

                b.setTitle(title);
                b.setBlogLink(articleLink);
                b.setAuthor(author);
                b.setAuthorLink(authorLink);
                b.setDateString(dateStr);
            }

            Elements articleThumb = el.getElementsByClass("article-thumb");
            if (articleThumb.size() > 0){
                Element img = articleThumb.select("img").get(0);
                String imgLink = img.attr("src");
                b.setImageLink(imgLink);
            }

            if (isValid)
                bls.add(b);
        }
        return bls;
    }

    public void process() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        ArrayList<Blog> blogs = parse();
        blogs.forEach(System.out::println);
    }
}
