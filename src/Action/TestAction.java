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
public class TestAction implements Action {
    public String execute() throws Exception{

        Db db=new Db();
        GetAllURL getAllURL=new GetAllURL();
        Iterator iterator=getAllURL.traverse(db.getAllSeed()).iterator();
        if (iterator.hasNext()){
            return "success";
        }
        else{
            return  "error";
        }

    }
}

