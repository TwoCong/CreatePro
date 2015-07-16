package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import Model.*;
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



    /*
     *对Seed表进行DB操作
     *
     *
     */
    ///status=0 表示该seed未被爬过，加入ArrayList

    public ArrayList<Seed> getAllSeed() {
        try{
            pstmt=conn.prepareStatement("select * from seed where STATUS=0");
            ResultSet rs=pstmt.executeQuery();
            ArrayList<Seed> seedList=new ArrayList<Seed>();
            while(rs.next()){
                Seed seed=new Seed();
                seed.setSeedId(rs.getInt(1));
                seed.setURL(rs.getString(2));
                seed.setSiteName(rs.getString(3));
                seed.setDnLimite(rs.getString(4));
                seed.setStatus(rs.getByte(5));
                seedList.add(seed);
            }
            return seedList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*
    *将seed表中所有的url转移至URL表中
    * 返回URL表 ArrayList<URL>
    */
    public  ArrayList<URL> sendSeedToURL(){
        try{
            getAllSeed();
            ResultSet resultSet=pstmt.executeQuery();
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
        try{
            pstmt=conn.prepareStatement("select url from seed where STATUS=0");
            ResultSet rs=pstmt.executeQuery();
            ArrayList al=new ArrayList();
            while(rs.next()){
                al.add(rs.getString(1));
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
        try{
            pstmt=conn.prepareStatement("SELECT * FROM seed WHERE SeedId=?");
            pstmt.setInt(1, seedId);
            ResultSet rs=pstmt.executeQuery();
            Seed seed=new Seed();
            if(rs.next()){
                seed.setSeedId(rs.getInt(1));
                seed.setURL(rs.getString(2));
                seed.setSiteName(rs.getString(3));
                seed.setDnLimite(rs.getString(4));
                seed.setStatus(rs.getByte(5));
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
        try{
            pstmt=conn.prepareStatement("select DNLIMITE from seed where URL=?");
            pstmt.setString(1, seedURL);
            ResultSet rs=pstmt.executeQuery();
            return rs.getString(1);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*
     *对URL表进行的DB操作
     *
     *
     *
     */

    public boolean insertURL(URL url) {
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
            pstmt.close();


        return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<URL> getAllURL(){
        try{
            pstmt=conn.prepareStatement("select * from URL WHERE URLSTATUS=0");
            ResultSet rs=pstmt.executeQuery();
            ArrayList<URL> URLList=new ArrayList<URL>();
            while(rs.next()){
                URL url=new URL();
                url.setURLId(rs.getInt(1));
                url.setSeedId(rs.getInt(2));
                url.setURL(rs.getString(3));
                url.setDocsize(rs.getInt(4));
                url.setLastCrawlerTime(rs.getString(5));
                url.setCycle(rs.getInt(6));
                url.setURLValue(rs.getInt(7));
                url.setPageContentValue(rs.getInt(8));
                url.setURLStatus(rs.getInt(9));

                URLList.add(url);
            }
            return URLList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*
     * set URLStatus=1
     */
    public void updateURLStatus(URL url){
        try{
            pstmt=conn.prepareStatement("update URL set URLSTATUS=1 WHERE URLID=?");
            pstmt.setInt(1, url.getURLId());
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
        try{
            pstmt=conn.prepareStatement("select url from URL");
            ResultSet rs=pstmt.executeQuery();
            ArrayList al=new ArrayList();
            while(rs.next()){
                al.add(rs.getString(1));
            }
            return al;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}

