package DAO;

import DB.Db;
import Model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Two_Cong on 15/07/06.
 *
 * 1、遍历Seed表，筛选出Status=0（未爬过的=0）的seed，加入ArrayList中
 * 2、获取seed.url网页中的所有超链接网址，对其进行条件筛选，再存入URL表中。
 */
public class GetAllURL {

    private ArrayList<URL> URLListFormSeed;
    public ArrayList<URL> getURLListFormSeed() {
        return URLListFormSeed;
    }

    public void setURLListFormSeed(ArrayList<URL> URLListFormSeed) {
        this.URLListFormSeed = URLListFormSeed;
    }

    public GetAllURL(){
        super();
        this.URLListFormSeed=new ArrayList<URL>();

    }


//    ArrayList allSeedURL=db.getAllSeedUrl();
//    boolean result=insertAllURL(allSeedURL);


    /**
     *将所有的URLList存储到URL表中
     *
     */
    public boolean insertAllURL(ArrayList allSeedURL){
        Db db=new Db();
        try{
            Iterator iter = allSeedURL.iterator();
            ArrayList<URL> URLList=new ArrayList<URL>();
            while (iter.hasNext()) {
                String seedURL = iter.next().toString();

                URLList=traverse(seedURL);

                db.insertURL(URLList);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // judge url
    private boolean isURL(String url) {
        try {

            Pattern pattern = Pattern.compile("^[a-zA-z]+://[^\\s]*");
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *遍历seedURL中的所有超链接。
     * 返回   URLList
     *
     */
    public ArrayList<URL> traverse(String seedURL)  {

            boolean flag=true;
            Document document=null;
            try {
                 document = Jsoup.connect(seedURL).timeout(10000).get();
            } catch (Exception e) {
                e.printStackTrace();
                flag=false;
            }

            if(flag){
                Elements elements=document.select("a.linkfont1");
                for (Element e : elements){
                    String s=e.attr("abs:href");

                    if (isURL(s)&&!getURLListFormSeed().contains(s)){
                        URL url=new URL();
                        url.setURL(s);
                        this.getURLListFormSeed().add(url);
                    }
                }

            }


        return this.getURLListFormSeed();
    }
//    public ArrayList<URL> traverse(String seedURL){
//        return null;
//    }
}



