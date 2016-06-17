package DAO;

import DB.Db;
import Model.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 15/07/06.
 * <p/>
 * 1、遍历Seed表，筛选出Status=0（未爬过的=0）的seed，加入ArrayList中
 * 2、获取seed.url网页中的所有超链接网址，对其进行条件筛选，再存入URL表中。
 */
public class GetAllURL {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //获取当前日期，以“年份-月份-日期 时:分:秒”来显示

    private Queue<URL> URLs;  //单个网页中的所有超链接
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

    public GetAllURL() {
        super();
        this.URLListFormAHref = new ArrayList<URL>();
        this.URLs = new LinkedList<URL>();
    }

    /**
     * judge url
     *
     * @param url 需要进行判断
     * @return
     * @oaram dnLimite 对应seed的域名限制
     */
    public boolean isURL(String url, String dnLimite) {
        try {
            String res = dnLimite;
            String rex = "^[a-zA-Z]+://[^\\s]*" + res + "[^\\s]*";
            Pattern pattern = Pattern.compile(rex);
            Matcher matcher = pattern.matcher(url);

            if (matcher.matches()) {
                try{
                    java.net.URL url1 = new java.net.URL(url);
                    url1.openConnection();
                }catch(Exception e){
                    System.out.println("链接无效");
                    return false;
                }
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
     *
     * @param urlList 从URL表中读出的URL数组
     *                URLSTATUS =0, 没有爬过，新网页
     *                =1，爬过，
     *                =2：重建索引网页
     *                =3: 建立索引时，都将其置3
     *                =4: 已索引的网页进行了更新
     * @return ArrayList<URL>
     */
    public ArrayList<URL> traverse(ArrayList<URL> urlList) throws IOException {
        int iurlSeedId = 0;
        int idocSize = 0;
        int icycle = 0;
        int iurlValue = 0;
        int ipageContentValue = 0;
        int iurlStatus = 0;
        int iurlId = 0;
        String surl = "";
        String slastCrawlerTime = "";
        Db db = new Db();
        DownloadFile downLoadFile = new DownloadFile();
        FileOutTxt fileOutTxt = new FileOutTxt();
        FileOperate fileOperate = new FileOperate();
        ParseDocument parseDocument = new ParseDocument();

        Iterator iterator = urlList.iterator();
        while (iterator.hasNext()) {
                URL url = (URL) iterator.next();
                iurlSeedId = url.getSeedId();
                iurlStatus = url.getURLStatus();
                idocSize = url.getDocsize();
                icycle = url.getCycle();
                iurlValue = url.getURLValue();
                ipageContentValue = url.getPageContentValue();
                slastCrawlerTime = url.getLastCrawlerTime();
                surl = url.getURL();
                this.getURLs().add(url);         //取出单个url实体，加入URLs队列中


        }
        while (!this.getURLs().isEmpty()) {
            boolean isConnected = true;
            Document document = null;
            try {
                URL url = this.getURLs().poll();
                iurlSeedId = url.getSeedId();
                iurlStatus = url.getURLStatus();
                idocSize = url.getDocsize();
                icycle = url.getCycle();
                iurlValue = url.getURLValue();
                ipageContentValue = url.getPageContentValue();
                slastCrawlerTime = url.getLastCrawlerTime();
                surl = url.getURL();

                downLoadFile.downloadPageByGetMethod(surl, url.getURLId());
                fileOutTxt.fileOutTxt(parseDocument.docParseShu(url.getURLId()), url.getURLId());

                document = Jsoup.connect(surl).timeout(100000).get();
                db.updateURLStatus(url);

            } catch (Exception e) {
                e.printStackTrace();
                isConnected = false;
            }
            if (isConnected) {
                Elements elements = document.select("a[href]");
                for (Element e : elements) {
                    String s = e.attr("abs:href");
                    if (isURL(s, db.findSeed(iurlSeedId).getDnLimite()) && !db.getAllUrl().contains(s)) {
                        URL url1 = new URL();
                        url1.setURL(s);
                        url1.setSeedId(iurlSeedId);

                        //修改urlstatus属性

                        url1.setLastCrawlerTime(df.format(new Date()));   //获取当前时间lastcrawlertime，1000-01-01 00:00:00——9999-12-31 23:59:59”


                        this.getURLListFormAHref().add(url1);
                        db.insertURL(url1);
                        // 根据网址s查询数据库得出urlId,用urlId命名网页名称
                        // 下载网页
                        URL url2 = new URL();
                        url2 = db.findURL(s);

                        downLoadFile.downloadPageByGetMethod(s, url2.getURLId());       //获取制定的url的urlId,下载网页，命名方式为urlId.html
                        System.out.println("Downloadfile success!");
                        //获取相应html文件的Docsize,并更新URL表
                        idocSize = fileOperate.getDocsize(url2.getURLId() + ".html");
                        db.updateURLDocsize(idocSize, url2.getURLId());


                        fileOutTxt.fileOutTxt(parseDocument.docParseShu(url2.getURLId()), url2.getURLId());
                        System.out.println("file out to Txt success!");
                        System.out.println("");

                    }
                }
            } else {
                //////
            }
        }

        return this.getURLListFormAHref();
    }


}



