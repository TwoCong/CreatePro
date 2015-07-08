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
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Two_Cong on 15/07/06.
 *
 * 1、遍历Seed表，筛选出Status=0（未爬过的=0）的seed，加入ArrayList中
 * 2、获取seed.url网页中的所有超链接网址，对其进行条件筛选，再存入URL表中。
 */
public class GetAllURL {

    private ArrayList<URL> URLListFormSeed;  //seed表的所有url
    private Queue<Seed> seeds;//对象队列：存储每个seeds对象
    private  Queue<Seed> seedsForCon;
    public ArrayList<URL> getURLListFormSeed() {
        return URLListFormSeed;
    }

    public Queue<Seed> getSeeds() {
        return seeds;
    }

    public Queue<Seed> getSeedsForCon() {
        return seedsForCon;
    }

    public void setSeedsForCon(Queue<Seed> seedsForCon) {
        this.seedsForCon = seedsForCon;
    }

    public void setSeeds(Queue<Seed> seeds) {
        this.seeds = seeds;
    }

    public void setURLListFormSeed(ArrayList<URL> URLListFormSeed) {
        this.URLListFormSeed = URLListFormSeed;
    }

    public GetAllURL(){
        super();
        this.URLListFormSeed=new ArrayList<URL>();
        this.seedsForCon=new LinkedList<Seed>();
        this.seeds=new LinkedList<Seed>();
    }
    /**
     *将所有的URLList存储到URL表中
     *
     */

    // judge url
    private boolean isURL(String url,String dnLimite) {
        try {
            String res=dnLimite;
            String rex="^[a-zA-Z]+://[^\\s]*"+res+"[^\\s]*";
            //Pattern pattern = Pattern.compile("^[a-zA-z]+://[^\\s]*");
            Pattern pattern = Pattern.compile(rex);
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
    public ArrayList<URL> traverse(ArrayList<Seed> seedList)  {
        Db db=new Db();
        String seedURL="";
        int seedId=0;
        String seedSiteName="";
        String seedDnLimite="";  //域名限制
        Iterator iterator=seedList.iterator();
        while (iterator.hasNext()){
            Seed seed=(Seed)iterator.next();
            seedURL=seed.getURL();
            seedId=seed.getSeedId();
            seedSiteName=seed.getSiteName();
            seedDnLimite=seed.getDnLimite();   //获取到了当前seed的所有信息。
            this.getSeeds().add(seed);     // 加入到seed队列中,便于后面进行一个一个取出。

        }
        while(!this.getSeeds().isEmpty()){
            boolean flag=true;
            Document document=null;

            try {
                Seed seed=this.getSeeds().poll();
                seedURL=seed.getURL();
                seedId=seed.getSeedId();
                seedSiteName=seed.getSiteName();
                seedDnLimite=seed.getDnLimite();
                document=Jsoup.connect(seedURL).timeout(10000).get();
            }catch (Exception e){
                e.printStackTrace();
                flag=false;
            }
            if(flag){
                Elements elements=document.select("a.Normal");
                for (Element e : elements){
                    String s=e.attr("abs:href");

                    if (isURL(s,seedDnLimite)&&!db.getAllUrl().contains(s)){
                        URL url=new URL();
                        url.setURL(s);
                        url.setSeedId(seedId);

                        //
                        //之后加入Docsize以及其他属性。
                        //
                        this.getURLListFormSeed().add(url);
                        db.insertURL(url);
                    }
                }

            }

        }


    return this.getURLListFormSeed();
    }


}



