package DAO;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.Scanner;


/**
 * Created by Two_Cong on 15/07/16.
 */

//本类用于将指定url对应的网页下载至本地一个文件。
public  class DownloadFile {

    /**
     * 文件名格式：urlId_url.html
     * @param urlId
     * @return fileName
     */
    private static String setFileName(int urlId) {
        String fileName = urlId + ".html";
        return fileName;
    }

    /**
     * 将输入流中的内容输出到path指定的路径，fileName指定的文件名
     * @param path
     * @param fileName
     * @param is
     */
    private static void saveToFile(String path, String fileName, InputStream is) {
        Scanner sc = new Scanner(is);
        Writer os = null;
        try {
            os = new PrintWriter(path + fileName);
            while (sc.hasNext()) {

                os.write(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
            if (os != null) {
                try{
                    os.flush();
                    os.close();
                }catch(IOException e){
                    e.printStackTrace();
                    System.out.println("输出流关闭失败！");
                }
            }
        }
    }

    /**
     *
     * @param url
     * @param urlId
     * @throws IOException
     */
    public static void downloadPageByGetMethod(String url,int urlId) throws IOException {
        // 1、通过HttpGet获取到response对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 注意，必需要加上http://的前缀，否则会报：Target host is null异常。
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);

        InputStream is = null;
        if (response.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_OK) {
            try {
                // 2、获取response的entity。
                HttpEntity entity = response.getEntity();
                // 3、获取到InputStream对象，并对内容进行处理
                is = entity.getContent();
                String fileName = setFileName(urlId);
 //               saveToFile("/Users/Two_Cong/IdeaProjects/CreatePro/web/Html/", fileName, is);
                saveToFile("/Users/Two_Cong/Desktop/CreatePro/web/Html/", fileName, is);

                System.out.println("Html: "+fileName);

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    is.close();
                }
                if (response != null) {
                    response.close();
                }
            }
        }
    }


}

