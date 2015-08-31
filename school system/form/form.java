package form;
import java.sql.*;
import login.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ben
 */
public class form {
    public ResultSet getform() {
        ResultSet rst;
        dbconnect db= new dbconnect();
        db.Connect();
        db.qry="select * from class";
        rst=db.queryString(db.qry);
        return rst;       
        
    }
}
