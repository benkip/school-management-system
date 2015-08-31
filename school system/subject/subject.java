package subject;

import login.*;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ben
 */
public class subject {
    public ResultSet viewsubject() {
        String qry;
        ResultSet rst;
        qry="select * from subject";
        dbconnect db=new dbconnect();
        db.Connect();
        rst=db.queryString(qry);
        return rst;
        
    }
}
