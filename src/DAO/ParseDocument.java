package DAO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Two_Cong on 15/07/21.
 */
public class ParseDocument implements SpliteLine{
    /**
     * 对本地网页进行解析，对网页中所有信息进行抽取
     * 目前抽取内容: title ,content(所有文本)，
     * @param urlId
     * @return
     * @throws IOException
     */
    public static ArrayList<String> docParseShu(int urlId) throws IOException {
 //       String path = "/Users/Two_Cong/IdeaProjects/CreatePro/web/Html/";
        String path = "/Users/Two_Cong/Desktop/CreatePro/web/Html/";
        String fileName = urlId+".html";
        ArrayList<String> strArray = new ArrayList<String>();
        File input = new File(path+fileName);
        try{
            Document document = Jsoup.parse(input, "utf-8");
            Elements title = document.select("head>title");
//            Elements title = document.select("span.head");
            String titleCon = title.text()+titleSpliteLine;
            String contentCon = document.text();    //将网页中的所有文本提取出来
            if ( contentCon.isEmpty() ){             //若提取出的文本为空，则将其对应的链接剔除掉，包括数据库、html,txt文本

                System.out.println("本文件存储的txt为空");

            }
            contentCon = contentCon+contentSpliteLine;
            strArray.add(0,titleCon);
            strArray.add(1,contentCon);
/*
            //遍历网页所有链接，提取出来
            int i = 2;
            Elements links = document.select("a[href]");
            for (Element link:links) {
                String linkCon = link.attr("abs:href")+linkSplteLine;
                strArray.add(i,linkCon);
                i++;
            }
*/
            return strArray;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


}
