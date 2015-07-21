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
import DAO.DownloadFile;

/**
 * Created by Two_Cong on 15/07/06.
 *
 * 1、遍历Seed表，筛选出Status=0（未爬过的=0）的seed，加入ArrayList中
 * 2、获取seed.url网页中的所有超链接网址，对其进行条件筛选，再存入URL表中。
 */
public class GetAllURL {


    private Queue<URL>  URLs;  //单个网页中的所有超链接
    private ArrayList<URL> URLListFormAHref;  //所有A[href]的url
    public ArrayList<URL> getURLListFormAHref() {
        return URLListFormAHref;
    }

    public void setURLListFormAHref(ArrayList<URL> URLListFormAHref) {
        this.URLListFormAHref = URLListFormAHref;
    }

    public Queue<URL> getURLs() {
        return URLs;
    }

    public void setURLs(Queue<URL> URLs) {
        this.URLs = URLs;
    }



    public GetAllURL(){
        super();
        this.URLListFormAHref=new ArrayList<URL>();
        this.URLs=new LinkedList<URL>();
    }


    /**
     * judge url
     * @return
     * @param url  需要进行判断
     * @oaram dnLimite 对应seed的域名限制
     */
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
     * 遍历URL表中所有超链接
     * @return  ArrayList<URL>
     * @param  urlList 从URL表中读出的URL数组
     *     URLSTATUS =0, 没有爬过，新网页
     *               =1，爬过，
     *               =2：重建索引网页
     *               =3: 建立索引时，都将其置3
     *               =4: 已索引的网页进行了更新
     */
    public ArrayList<URL> traverse(ArrayList<URL> urlList) throws IOException {
        int iurlSeedId=0;
        int idocSize=0;
        int icycle=0;
        int iurlValue=0;
        int ipageContentValue=0;
        int iurlStatus=0;
        int iurlId=0;
        String surl="";
        String slastCrawlerTime="";
        Db db=new Db();

        Iterator iterator=urlList.iterator();
        while (iterator.hasNext()){
            URL url=(URL)iterator.next();

            iurlSeedId=url.getSeedId();
            iurlStatus=url.getURLStatus();
            idocSize=url.getDocsize();
            icycle=url.getCycle();
            iurlValue=url.getURLValue();
            ipageContentValue=url.getPageContentValue();
            slastCrawlerTime=url.getLastCrawlerTime();
            surl=url.getURL();


            this.getURLs().add(url);         //取出单个url实体，加入URLs队列中
        }
        while (!this.getURLs().isEmpty()){
            boolean isConnected=true;
            Document document=null;
            try {

                URL url=this.getURLs().poll();

                iurlSeedId=url.getSeedId();
                iurlStatus=url.getURLStatus();
                idocSize=url.getDocsize();
                icycle=url.getCycle();
                iurlValue=url.getURLValue();
                ipageContentValue=url.getPageContentValue();
                slastCrawlerTime=url.getLastCrawlerTime();
                surl=url.getURL();


                document=Jsoup.connect(surl).timeout(1000).get();
                db.updateURLStatus(url);

            }catch (Exception e){
                e.printStackTrace();
                isConnected=false;
            }
            if (isConnected){
                Elements elements=document.select("a[href]");
                for (Element e : elements){
                    String s=e.attr("abs:href");
                    if (isURL(s,db.findSeed(iurlSeedId).getDnLimite())&&!db.getAllUrl().contains(s)){
                        URL url=new URL();
                        url.setURL(s);
                        url.setSeedId(iurlSeedId);

                         //修改Docsize，lastcrawlertime，urlstatus属性

                        this.getURLListFormAHref().add(url);
                        db.insertURL(url);
                        // 根据网址查询出urlId,用urlId命名网页名称
                        // 下载网页
                        URL url1=new URL();
                        url1=db.findURL(s);
                        DownloadFile downLoadFile=new DownloadFile();

                        downLoadFile.downloadPageByGetMethod(s,url1.getURLId());
                    }
                }
            }
        }

        return this.getURLListFormAHref();
    }


}



