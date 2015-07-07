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
                seed.setRestrict(rs.getString(4));
                seed.setStatus(rs.getByte(5));
                seedList.add(seed);
            }
            return seedList;
        }catch(Exception e){
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
                seed.setRestrict(rs.getString(4));
                seed.setStatus(rs.getByte(5));
                return seed;
            }
            return null;
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

    public boolean insertURL(ArrayList<URL> URLList) {
    try{
        Iterator iter = URLList.iterator();
        while(iter.hasNext()){
            URL url=new URL();
            url=(URL)iter.next();
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

        }
        return true;
    }catch(Exception e){
        e.printStackTrace();
        return false;
    }
    }



}

