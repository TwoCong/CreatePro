package DAO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Two_Cong on 15/07/27.
 */
public class FileOutTxt {
    /**
     * 将字符串写入TXT文件中
     * @param input 字符数组
     * @param outputName 输出文件名
     * @throws IOException
     */
    public  static void fileOutTxt(ArrayList<String> input,String outputName) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/Two_Cong/IdeaProjects/CreatePro/web/txt"+outputName+".txt",true);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        try {
            for (int i = 0;i < input.size();i++){
                bufferedWriter.write(input.get(i).toString());
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            bufferedWriter.close();
            outputStreamWriter.close();
            fileOutputStream.close();
        }
    }
}
