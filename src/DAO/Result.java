package DAO;

import java.util.ArrayList;

/**
 * Created by Two_Cong on 15/11/27.
 */
public class Result {
    public ArrayList showCode(String key){

        HighLightKey highLightKey = new HighLightKey();
        Searcher searcher = new Searcher();
        ArrayList codeList= new ArrayList();
        ArrayList contentList = searcher.searchIndex(key);
        for (int i=0;i<contentList.size();i++){
            Object content  = contentList.get(i);
            //codeList.add(highLightKey.test_highlight((String)content,key));
        }
        return codeList;
    }
}
