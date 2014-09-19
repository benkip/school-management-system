package login;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ben
 */
public class dbconnect {
    private String url;
    private String usr;
    private String pwd;
    public Connection con;
    public Statement stmt;
    public ResultSet rst;
    public String qry;
    public void Connect(){
        url="jdbc:mysql://localhost:3306/school";
        usr="root";
        pwd="";
        try {
            con=DriverManager.getConnection(this.url, this.usr, this.pwd);
        }
        catch(SQLException e){
            System.out.printf(e.getMessage());
        }
    }
    public ResultSet queryString(String qry){
        try{
            //System.out.print(qry);
            stmt=con.createStatement();
            rst=stmt.executeQuery(qry);
        } catch (SQLException e){
            System.out.printf(e.getMessage());
        }  
        return rst;
    }
    public void Insert(String qry) {
        try {
            stmt=con.createStatement();
            stmt.executeUpdate(qry);
        } catch (SQLException ex) {
            Logger.getLogger(dbconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
