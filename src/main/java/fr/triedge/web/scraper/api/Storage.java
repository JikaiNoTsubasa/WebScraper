package fr.triedge.web.scraper.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.triedge.web.scraper.model.Blog;
import org.springframework.boot.json.GsonJsonParser;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private static final String FILE_PATH = "blogs.store";

    public static void store(ArrayList<Blog> blogs) throws IOException {
        File file = new File(FILE_PATH);
        FileWriter fw = new FileWriter(file);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        fw.write(gson.toJson(blogs));
        fw.flush();
        fw.close();
    }

    public static ArrayList<Blog> load() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists())
            return null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder tmp = new StringBuilder();
        while ((line=br.readLine())!= null){
            tmp.append(line).append("\n");
        }
        br.close();
        return gson.fromJson(tmp.toString(), new TypeToken<ArrayList<Blog>>(){}.getType());
    }
}
