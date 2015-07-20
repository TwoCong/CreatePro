package Action;

import javax.swing.*;
import DAO.*;
import DB.Db;
import Model.*;

import com.opensymphony.xwork2.Action;

import java.util.Iterator;

/**
 * Created by Two_Cong on 15/07/07.
 */
public class TestAction implements Action{
    public String execute() throws Exception{

        Db db=new Db();
        GetAllURL getAllURL=new GetAllURL();
        db.sendSeedToURL();

        if (!db.getAllURL().isEmpty()){
           getAllURL.traverse(db.getAllURL());
            return  "success";
        }
        return "error";

//        while (!db.getAllURL().isEmpty()){
//            getAllURL.traverse(db.getAllURL());
//        }
//        return SUCCESS;
    }
}

