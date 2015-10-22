import DAO.GetAllURL;
import DB.Db;

import java.io.IOException;

/**
 * Created by Two_Cong on 15/09/05.
 */
public class CrawlerTest {
    public static void main(String[] args) throws IOException {
        Db db = new Db();
        GetAllURL getAllURL = new GetAllURL();
        db.sendSeedToURL();     //将seed表中所有的 status=0 的url 转移至URL表中

        if (!db.getAllURL().isEmpty()) {            //URL表中存在URLSTATE为O的ArrayList<URL>
            getAllURL.traverse(db.getAllURL());     //对ArrayList<URL>进行遍历操作
        }



//        IndexLucene indexLucene =new IndexLucene();
//        indexLucene.createIndex();
//        System.out.print("请输入关键字: ");
//        Scanner reader = new Scanner(System.in);
//        String keyword = reader.nextLine();
//        Searcher searcher = new Searcher();
//        //searcher.readAllIndexDocs();
//        searcher.searchIndex(keyword);
//
//        System .out.println(" ");



//        HighLightKey highLightKey = new HighLightKey();
//        highLightKey.test_highlight();

    }
}
