package DAO;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;


/**
 * Created by Two_Cong on 15/07/16.
 */
public class DownLoadFile {
    /*
     *根据URL和网页的类型生成所需要保存的网页的文件名，去除URL中的非文件名字符
     */
    public String getFileNameByUrl(String url,String contentType){
        //去除http://
        url=url.substring(7);
        //text/html类型
        if(contentType.indexOf("html")!=-1){
            url=url.replaceAll("[\\?/:*|<>\"]","_")+".html";
            return url;
        }
        //application/pdf类型
        else {
            url=url.replaceAll("[\\?/:*|<>\"]","_")+"."+contentType.substring(contentType.lastIndexOf("/")+1);
            return url;
        }
    }

    /*
     * 保存网页字节数组到本地文件，filePath为要保存的文件的相对地址
     */
    public void saveToLocal(byte[] data,String filePath){
        try {
            DataOutputStream outputStream=new DataOutputStream(new FileOutputStream(new File(filePath)));
            for (int i=0;i<data.length; i++){
                outputStream.write(data[i]);
            }
            outputStream.flush();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
     * 下载url指向的网页
     */


     public String downloadFile(String url){
         String filePath=null;
         //1、生成HttpClient对象，并设置参数
         HttpClient httpClient=new HttpClient();
         //设置HTTP连接超时5s
         httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
         //2、生成GetMethod对象并设置参数

         GetMethod getMethod=new GetMethod();
         //设置get请求超时5s
         getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,5000);
         //设置请求重试处理
         getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
         //3、执行HTTP GET请求
         try {
             int statusCode = httpClient.executeMethod(getMethod);
             //判断访问的状态码
             if (statusCode != HttpStatus.SC_OK){
                 filePath=null;
             }

             //4、处理HTTP响应内容
             byte[] responseBody = getMethod.getResponseBody();     //读取为字节数组
             //根据网页url生成保存时的文件名
             filePath="Two_Cong:Users\\Two_Cong\\IdeaProjects\\CreatePro\\web\\Html\\"+getFileNameByUrl(url,getMethod.getResponseHeader("Content-Type").getValue());
             saveToLocal(responseBody,filePath);
         }catch (HttpException e){
             //可能是协议不对或者返回内容有问题
             e.printStackTrace();
         }catch (IOException e){
             //网络异常
             e.printStackTrace();
         }finally {
             //释放连接
             getMethod.releaseConnection();
         }
         return filePath;


     }
}
