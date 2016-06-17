import DAO.IndexLucene;
import DAO.Searcher;

import java.io.IOException;

/**
 * Created by Administrator on 15/09/05.
 */
public class CrawlerTest {
    public static void main(String[] args) throws IOException {
        //爬取网页----一遍

//        Db db = new Db();
//        GetAllURL getAllURL = new GetAllURL();
//        db.sendSeedToURL();     //将seed表中所有的 status=0 的url 转移至URL表中
//        if (!db.getAllURL().isEmpty()) {            //URL表中存在URLSTATE为O的ArrayList<URL>
//            getAllURL.traverse(db.getAllURL());     //对ArrayList<URL>进行遍历操作
//        }
////        爬取网页-------无数
//        Db db = new Db();
//        GetAllURL getAllURL = new GetAllURL();
//        db.sendSeedToURL();
//        while (!db.getAllURL().isEmpty()){
//            getAllURL.traverse(db.getAllURL());
//        }

//        try{
//            java.net.URL url1 = new java.net.URL("sdxb.shu.edu.cn:8080");
//            url1.openConnection();
//        }catch(Exception e){
//            System.out.println("链接无效");
//        }

       // GetAllURL getAllURL = new GetAllURL();
        //System.out.println(getAllURL.isURL("sdxb.shu.edu.cn:8080","1s"));
//
        //删除没有用的网页本地文件
//        ArrayList emptyUrlId = new Db().getEmptyDoc();
//        Iterator iterator =emptyUrlId.iterator();
//        while (iterator.hasNext()){
//            String urlId = String.valueOf(iterator.next());
//
//            File html = new File("./web/Html/"+urlId+".html");
//            File txt = new File("./web/txt/"+urlId+".txt");
//            if (html.exists())
//                html.delete();
//            if (txt.exists())
//                txt.delete();
//        }
//        new Db().deleteEmptyDoc();



        //建立索引
       IndexLucene indexLucene =new IndexLucene();
        indexLucene.createIndex();
        new Searcher().readAllIndexDocs();


//        //网页高亮测试
//        System.out.print("请输入关键字: ");
//        Scanner reader = new Scanner(System.in);
//        String keyword = reader.nextLine();
//        HighLightKey highLightKey = new HighLightKey();
//
//        Searcher searcher = new Searcher();
//
//
//        ArrayList contentList = searcher.searchIndex(keyword);
//        for (int i=0;i<contentList.size();i++){
//            Object content  = contentList.get(i);
//            highLightKey.test_highlight((String)content,keyword);
//        }
//        searcher.readAllIndexDocs();
//        System .out.println(" ");





////
//       // 测试索引
//        System.out.print("请输入关键字: ");
//        Scanner reader = new Scanner(System.in);
//        String keyword = reader.nextLine();
//        ArrayList alSearch = new Searcher().searchIndex(keyword);






//        System.out.println("**********");
//        System.out.println("**********");
//        System.out.println("**********");
//        Iterator it = alSearch.iterator();
//        while (it.hasNext()){
//            String  s= it.next().toString();
//            System.out.println(s);
//        }


        /*
        *
        * 方法一，
File file = new File("?");(?中填写你的文件的路径，注意：win下需要转义，例如：D:\\test.txt,Linux下直接填写，例如:/home/user001/text.txt)
if(file.exists())
file.delete();
方法二，
利用Runtime（Runtime是java的一个取得系统相关进程的类，说白了就是调用系统的命令删除文件）
Runtime run = Runtime.getRuntime();
try {
Process p = run.exec(cmd); //cmd为String，其内容为对应系统的删除命令，例如：在win下，应该写成：String cmd = "del "+你的文件路径以及文件名;
p.waitFor();
} catch (Exception e) {
e.printStackTrace();
}
        *
        *
        * */
    }
}
