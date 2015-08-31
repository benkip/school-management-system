package student;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ben
 */
import login.dbconnect;
import javax.swing.*;
import java.sql.*;
public class student {
    JLabel jlbadd=new JLabel();
    public void addstudent(){
        System.out.print("adding student");
    }
    public ResultSet viewstudents(){
        dbconnect db=new dbconnect();
        db.Connect();
        db.qry="select * from student";
        db.rst=db.queryString(db.qry);
        return db.rst;
    }
    public ResultSet getStudent(String adm){
        dbconnect db=new dbconnect();
        db.Connect();
        db.qry="select * from student where adm_no='"+adm+"'";
        db.rst=db.queryString(db.qry);
        return db.rst;
    }
    public ResultSet getClass(int id){
        dbconnect db=new dbconnect();
        db.Connect();
        db.qry="select adm_no, CONCAT(f_name,l_name,s_name) as name from student where class_id='"+id+"'";
        db.rst=db.queryString(db.qry);
        return db.rst;
    }
    
}
