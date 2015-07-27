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
public class ParseDocument implements SpliteLine{

    public String resolveHtml() {
        Db db = new Db();
        String fileName = 959 + ".html";
        String address2 = "/Users/Two_Cong/Desktop/11.html";
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
    /**
     * 解析网页，存入相应patterns数据
     * @param urlId
     * @return
     */
    public String htmlParse(int urlId) throws IOException{
        Document document = null;
        String fileName = urlId + ".html";
        document = Jsoup.parse(fileName,"gb2312");
        Elements title = document.select("title");

        return null;

    }
    /**
     * 对本地网页进行解析
     * @param urlId
     * @return
     * @throws IOException
     */
    public static ArrayList<String> docParseShu(int urlId) throws IOException {
        String path = "/Users/Two_Cong/IdeaProjects/CreatePro/web/Html/";
        String fileName = urlId+".html";
        File input = new File(path+fileName);

        ArrayList<String> strArray = new ArrayList<String>();
        try{
            Document document = Jsoup.parse(input, "utf-8");
            Elements title = document.select("head>title");
            /*
            请在这里写解析的代码
            解析之后请调用 FileOutTxt，进行存储本地TXT文件，
             */
            String titleCon = title.text()+titleSpliteLine;
            strArray.add(0,titleCon);

            return strArray;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


}
