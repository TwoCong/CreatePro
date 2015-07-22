package DAO;

import java.io.File;
import DB.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

import Model.*;
import java.util.*;

/**
 * Created by Two_Cong on 15/07/21.
 */
public class ParseDocument {

    public String resolveHtml() {
        Db db = new Db();
//        int firstURLID = db.getFirstUrlID();
//    ArrayList<URL> URLList = db.getAllUrl();
//    while(!URLList.getURLs().isEmpty()){
//        int t= URLList.get(1).getURLId();
//
//    }

        String fileName = 959 + ".html";
        String address2 = "/Users/Two_Cong/Desktop/11.html";

      //  String address = "/Users/Two_Cong/IdeaProjects/CreatePro/web/Html/959.html";
       // String address2 = "/Html/959.html";
        File input = new File(address2);
        String test ="abc";
        String test1="cde";
        try {
            Document doc = Jsoup.parse(input, "UTF-8","");
            Element content = doc.getElementById("content");
            Elements links = content.getElementsByTag("title");
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return test1;
    }


}
