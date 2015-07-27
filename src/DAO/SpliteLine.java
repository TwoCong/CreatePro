package DAO;

/**
 * Created by Two_Cong on 15/07/27.
 */
public interface SpliteLine  {
    //各个标签分隔符，后面有需要的可以自行增加
    String titleSpliteLine = "***";  //title
    String contentSpliteLine = "@@@"; //内容标签
    String pSpliteLine = "###";     //p标签
    String hnSpliteLine = "%%%";    //h1 h2 h3 h4 h5标签
    String dateSpliteLine = "$$$";     //date时间
}
