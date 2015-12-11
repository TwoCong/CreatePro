package DB;

import Model.Seed;
import Model.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * Created by Two_Cong on 15/06/29.
 */
public class Db {
    Connection conn;
    PreparedStatement pstmt;


    public Db(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CreatePro", "root", "123456");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean close(){
        try {
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 释放语意集与结果集
     * @return
     * @param  resultSet
     * @param  pstmt
     * 后创建的先释放
     */
    public void releaseSource(ResultSet resultSet,PreparedStatement pstmt) {
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            releasePstmt(pstmt);
        }
    }

    /**
     * 释放 PreparedStatement
     * @return
     * @param pstmt
     */
    public void releasePstmt(PreparedStatement pstmt){
        if (pstmt != null){
            try {
                pstmt.close();
                pstmt = null;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    /*
     *对Seed表进行DB操作
     *
     *
     */
    ///status=0 表示该seed未被爬过，加入ArrayList

    public ArrayList<Seed> getAllSeed() {
        ResultSet resultSet;
        try{
            pstmt=conn.prepareStatement("select * from seed where STATUS=0");
            resultSet=pstmt.executeQuery();
            ArrayList<Seed> seedList=new ArrayList<Seed>();
            while(resultSet.next()){
                Seed seed=new Seed();
                seed.setSeedId(resultSet.getInt(1));
                seed.setURL(resultSet.getString(2));
                seed.setSiteName(resultSet.getString(3));
                seed.setDnLimite(resultSet.getString(4));
                seed.setStatus(resultSet.getByte(5));
                seedList.add(seed);
            }
            return seedList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 将seed表中所有的 status=0 的url 转移至URL表中
     * 返回URL表 ArrayList<URL>
     */
    public  ArrayList<URL> sendSeedToURL(){
        ResultSet resultSet;
        try{
            getAllSeed();       //status=0 表示该seed未被爬过，加入ArrayList
            resultSet=pstmt.executeQuery();
            while(resultSet.next()){
                URL url=new URL();
                url.setSeedId(resultSet.getInt(1));
                url.setURL(resultSet.getString(2));
                url.setURLStatus(resultSet.getByte(5));
                /*
                 *来自seed表的条目(URL)还需要修改
                 *Docsize,lastcrawlertime,cycle,urlvalue,pagecontentvalue  属性的值
                 *之后再进行修改
                 */
                insertURL(url);             //插进URL表中
            }
            getAllURL();
            ResultSet resultSet1=pstmt.executeQuery();
            ArrayList<URL> urlList=new ArrayList<URL>();
            while(resultSet1.next()){
                URL url1=new URL();
                url1.setSeedId(resultSet1.getInt(2));
                url1.setURL(resultSet1.getString(3));
                url1.setDocsize(resultSet1.getInt(4));
                url1.setLastCrawlerTime(resultSet1.getString(5));
                url1.setCycle(resultSet1.getInt(6));
                url1.setURLValue(resultSet1.getInt(7));
                url1.setPageContentValue(resultSet1.getInt(8));
                url1.setURLStatus(resultSet1.getInt(9));
            }
            return urlList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //
    //获取Status=0的网页的URL
    //
    public ArrayList getAllSeedUrl() {
        ResultSet resultSet;
        try{
            pstmt=conn.prepareStatement("select url from seed where STATUS=0");
            resultSet=pstmt.executeQuery();
            ArrayList al=new ArrayList();
            while(resultSet.next()){
                al.add(resultSet.getString(1));
            }
            return al;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //
    //
    //

    public Seed findSeed(int seedId){
        ResultSet resultSet;
        try{
            pstmt=conn.prepareStatement("SELECT * FROM seed WHERE SeedId=?");
            pstmt.setInt(1, seedId);
            resultSet=pstmt.executeQuery();
            Seed seed=new Seed();
            if(resultSet.next()){
                seed.setSeedId(resultSet.getInt(1));
                seed.setURL(resultSet.getString(2));
                seed.setSiteName(resultSet.getString(3));
                seed.setDnLimite(resultSet.getString(4));
                seed.setStatus(resultSet.getByte(5));
                return seed;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //
    //通过seedurl找到DNLIMITE
    //
    public String getURLRestrict(String seedURL) {
        ResultSet resultSet;
        try{
            pstmt=conn.prepareStatement("select DNLIMITE from seed where URL=?");
            pstmt.setString(1, seedURL);
            resultSet=pstmt.executeQuery();
            return resultSet.getString(1);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /*
     *对URL表进行的DB操作
     */

    public boolean insertURL(URL url) {
        ResultSet resultSet;
        try{
            pstmt=conn.prepareStatement("insert into URL (SEEDID,URL,DOCSIZE,LASTCRAWLERTIME,CYCLE,URLVALUE,PAGECONTENTVALUE,URLSTATUS) values(?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, url.getSeedId());
            pstmt.setString(2, url.getURL());
            pstmt.setInt(3, url.getDocsize());
            pstmt.setString(4, url.getLastCrawlerTime());
            pstmt.setInt(5, url.getCycle());
            pstmt.setInt(6, url.getURLValue());
            pstmt.setInt(7, url.getPageContentValue());
            pstmt.setInt(8, url.getURLStatus());
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
           releasePstmt(pstmt);
        }
    }

    /**
     * 获取URL表中所有URLSTATE为O的ArrayList<URL>
     * @return
     */
    public ArrayList<URL> getAllURL(){
        ResultSet resultSet;
        try{
            pstmt=conn.prepareStatement("select * from URL WHERE URLSTATUS=0");
            resultSet=pstmt.executeQuery();
            ArrayList<URL> URLList=new ArrayList<URL>();
            while(resultSet.next()){
                URL url=new URL();
                url.setURLId(resultSet.getInt(1));
                url.setSeedId(resultSet.getInt(2));
                url.setURL(resultSet.getString(3));
                url.setDocsize(resultSet.getInt(4));
                url.setLastCrawlerTime(resultSet.getString(5));
                url.setCycle(resultSet.getInt(6));
                url.setURLValue(resultSet.getInt(7));
                url.setPageContentValue(resultSet.getInt(8));
                url.setURLStatus(resultSet.getInt(9));
                URLList.add(url);
            }
            return URLList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * set URLStatus=1
     * @param url
     */
    public void updateURLStatus(URL url){
        ResultSet resultSet;
        try{
            pstmt=conn.prepareStatement("update URL set URLSTATUS=1 WHERE URLID=?");
            pstmt.setInt(1, url.getURLId());
            pstmt.executeUpdate();
            pstmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据urlId，更新对应url的docsize
     * @param docSize
     * @param urlId
     */
    public void updateURLDocsize(int docSize,int urlId){
        ResultSet resultSet;
        try{
            pstmt=conn.prepareStatement("update URL set DOCSIZE=? WHERE URLID=?");
            pstmt.setInt(1, docSize);
            pstmt.setInt(2,urlId);
            pstmt.executeUpdate();
            pstmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    //
    //得到url表中所有的url
    //
    public ArrayList getAllUrl() {
        ResultSet resultSet;
        try{
            pstmt=conn.prepareStatement("select url from URL");
            resultSet=pstmt.executeQuery();
            ArrayList al=new ArrayList();
            while(resultSet.next()){
                al.add(resultSet.getString(1));
            }
            return al;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            releasePstmt(pstmt);
        }
    }
    /**
     * 根据surl查询出当前URL
     * @param s
     * @return URL
     */
    public URL findURL(String s){
        ResultSet resultSet;
        try {
            pstmt=conn.prepareStatement("SELECT * FROM URL WHERE  URL=?");
            pstmt.setString(1, s);
            resultSet=pstmt.executeQuery();
            URL url=new URL();
            while (resultSet.next()){
                url.setURLId(resultSet.getInt(1));
                url.setSeedId(resultSet.getInt(2));
                url.setURL(resultSet.getString(3));
                url.setDocsize(resultSet.getInt(4));
                url.setLastCrawlerTime(resultSet.getString(5));
                url.setCycle(resultSet.getInt(6));
                url.setURLValue(resultSet.getInt(7));
                url.setPageContentValue(resultSet.getInt(8));
                url.setURLStatus(resultSet.getInt(9));
                return url;
            }
            return  null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取所有docsize<500的网页的URLID
     * @return
     */
    public ArrayList getEmptyDoc() {
        ResultSet resultSet;
        ArrayList UrlIdList = new ArrayList();
        try{
            pstmt = conn.prepareStatement("SELECT URLID FROM URL WHERE DOCSIZE <500");
            resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                UrlIdList.add(resultSet.getInt(1));
            }
            return  UrlIdList;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    /**
     * 删除docsize <500的网址（一般为空网页）
     */
    public void deleteEmptyDoc(){
        ResultSet resultSet;
        try{
            pstmt = conn.prepareStatement("DELETE from URL WHERE DOCSIZE <500");
            pstmt.executeUpdate();
            pstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 得到url表中FirsturlID
     * @return
     */
    public int getFirstUrlID() {
        ResultSet resultSet;
        try{
            pstmt=conn.prepareStatement("select  URLID from URL limit 1");
            resultSet=pstmt.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }catch(Exception e){
            e.printStackTrace();
            return 1;
        }finally {
            releasePstmt(pstmt);
        }
    }

    /**
     * 通过UrlId后去对应的URL
     * @param UrlId
     * @return
     */
    public String getUrl(int UrlId){
        ResultSet resultSet;
        try{
            pstmt = conn.prepareStatement("select URL FROM URL where URLID=? ");
            pstmt.setInt(1,UrlId);
            resultSet = pstmt.executeQuery();
            if (resultSet.next())
                return  resultSet.getString(1);
            else
                return null;
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("getUrl  by URlId occurs error!");
            return null;
        }
    }


}


