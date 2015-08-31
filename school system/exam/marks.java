/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exam;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import student.*;
import login.dbconnect;

/**
 *
 * @author ben
 */
public class marks extends javax.swing.JFrame {

    /**
     * Creates new form marks
     */
    int click=0;
    int sub,duration,term,exam;
    String action="";
    public marks() {
        initComponents();
        /*try {
            populate("select * from student");
        } catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        getExam();
        getYear();
    }
    
     public void populate(String qry) throws SQLException {
        
        dbconnect db=new dbconnect();
        db.Connect();
        int rows,cols,counter;
        db.rst=db.queryString(qry);
        ResultSetMetaData rsmd = db.rst.getMetaData();
        DefaultTableModel mod=(DefaultTableModel) markstable.getModel();
        mod.setRowCount(0);
        mod.setColumnCount(0);
        //ADDING COLUMN NAMES
        Vector column=new Vector();
        cols=rsmd.getColumnCount();
        counter=1;
        while(counter<=cols){
            mod.addColumn(rsmd.getColumnName(counter).toUpperCase());
           counter++;
        }
        //ADDING ROWS
        int X=0;
        while(db.rst.next()){
            Vector rowX= new Vector();
            rowX.addElement(db.rst.getString("student.adm_no"));
            rowX.addElement(db.rst.getString("f_name"));
            if(action.equals("update") )
            rowX.addElement(db.rst.getString("mark"));
            //rowX.addElement(db.rst.getString("s_name"));
           // rowX.addElement(db.rst.getString("image"));
            mod.addRow(rowX);
            X++;
            //db.rst.next();
        }
        markstable.setModel(mod);
        mod.fireTableDataChanged();
        //jTable1.isCellEditable(1, 1);
    }
     public void getExam() {
         dbconnect db=new dbconnect();
         ResultSet rt;
         db.Connect();
         db.qry="select * from exam";
         rt=db.queryString(db.qry);
        try {
            while(rt.next()){
                jcboexam.addItem(rt.getString("exam_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public int getExamid(){
            dbconnect db=new dbconnect();
            int id=0;
            db.Connect();
            ResultSet rt;
            db.qry="select exam_id from exam where exam_name='"+jcboexam.getSelectedItem().toString()+"' and term_id='"+getTermid()+"'";
            rt=db.queryString(db.qry);
            try {
            rt.first();
            id=rt.getInt("exam_id");
            }catch (SQLException ex) {
                Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
     }
     public void getYear() {
         dbconnect db=new dbconnect();
         ResultSet rt;
         db.Connect();
         db.qry="select * from duration";
         rt=db.queryString(db.qry);
        try {
            while(rt.next()){
                jcboyear.addItem(rt.getString("year"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public void getTerm(int yr) {
         dbconnect db=new dbconnect();
         ResultSet rt;
         db.Connect();
         db.qry="select term_name from duration,term where duration.duration_id=term.duration_id and duration.duration_id='"+yr+"'";
         rt=db.queryString(db.qry);
         jcboterm.removeAllItems();
        try {
            while(rt.next()){
                jcboterm.addItem(rt.getString("term_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public int getTermid(){
        //Get term_id
        dbconnect db=new dbconnect();
        db.Connect();
        ResultSet rt;
        int id=0;
        db.qry="select term_id from term where term_name='"+jcboterm.getSelectedItem().toString()+"' and duration_id='"+getDurationid()+"'";
        rt=db.queryString(db.qry);
        try {
        rt.first();
        id=rt.getInt("term_id");
        }catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
  public int getDurationid(){
        ResultSet rt;
        int x,y,count,item;
        item=0;
        dbconnect db=new dbconnect();
        db.Connect();
        db.qry="select * from duration";
        rt=db.queryString(db.qry);
        count=jcboyear.getItemCount();
        try {
            rt.first();
            for(y=0; y<count; y++){
                if(y==jcboyear.getSelectedIndex()){
                    item= rt.getInt("duration_id");
                }
                rt.next();
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
  }
 public int getSubid(){
     int x,item=0,y,count;
     ResultSet rt;
        x=jcboclass.getSelectedIndex();
        dbconnect db=new dbconnect();
        db.Connect();
        db.qry="select * from subject";
        rt=db.queryString(db.qry);
        count=jcbosubject.getItemCount();
        try {
        rt.first();
        for(y=0; y<count; y++){
                if(y==jcbosubject.getSelectedIndex()){
                    item= rt.getInt("sub_id");
                }
                rt.next();
            }  
        }       
        catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        } 
     return item;
 }
 
 public int getClassid(){
      int x,id=0,y,count;
        ResultSet rt;
        x=jcboclass.getSelectedIndex();
        dbconnect db=new dbconnect();
        db.Connect();
        db.qry="select * from class";
        rt=db.queryString(db.qry);
        count=jcboclass.getItemCount();
        try {
        rt.first();
        for(y=0; y<count; y++){
                if(y==jcboclass.getSelectedIndex()){
                    id= rt.getInt("class_id");
                }
                rt.next();
            }  
        }
        catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
 }
 public void getmarks(String qry){
     int x,count,y,item,id,counter,rows;
     //String qry="";
        item=0;
        id=0;
        counter=0;
        rows=0;
        ResultSet rt;
        sub=getSubid();
        dbconnect db=new dbconnect();
        db.Connect();
        id=getClassid();   
        try {
            // TODO add your handling code here:
            //populate("select student.adm_no as adm_no,f_name,mark from student,marks where marks.adm_no=student.adm_no and class_id='"+id+"'");
            populate(qry);
        } catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        } 
 }
     public void insertmark(){
         int mark;
         String adm;
         ResultSet rt;
         adm=txtadmno.getText();
        dbconnect db=new dbconnect();
        db.Connect();
        mark=Integer.parseInt(txtmark.getText());
        db.qry="insert into marks values ('"+adm+"','"+getSubid()+"','"+getExamid()+"','"+mark+"')";
        db.Insert(db.qry);
        //action="";
     }

      public void updatemark(){
         int mark;
         String adm;
         ResultSet rt;
         adm=txtadmno.getText();
         //Get term_id
        dbconnect db=new dbconnect();
        db.Connect();
        mark=Integer.parseInt(txtmark.getText());
        db.qry="update marks set mark='"+mark+"' where adm_no='"+adm+"' and sub_id='"+getSubid()+"' and exam_id='"+getExamid()+"'";
        db.Insert(db.qry);
        //action="";
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jcboclass = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbosubject = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jcboyear = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jcboterm = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jcboexam = new javax.swing.JComboBox();
        btnsubmit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        markstable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtadmno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtfname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtsname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtlname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtmark = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnprev = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        lbltitle = new javax.swing.JLabel();
        btnupdate = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btnview = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setText("FORM");

        jLabel2.setText("SUBJECT");

        jLabel10.setText("YEAR");

        jcboyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboyearActionPerformed(evt);
            }
        });

        jLabel11.setText("TERM");

        jLabel9.setText("EXAM TYPE");

        jcboexam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcboexamMouseClicked(evt);
            }
        });
        jcboexam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboexamActionPerformed(evt);
            }
        });

        btnsubmit.setText("SUBMIT");
        btnsubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsubmitMouseClicked(evt);
            }
        });
        btnsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcboclass, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbosubject, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboyear, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboterm, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboexam, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnsubmit)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcboclass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jcbosubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jcboterm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jcboexam, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsubmit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        markstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        markstable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                markstableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(markstable);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ENTER MARKS");

        jLabel4.setText("ADMISSION NUMBER");

        jLabel5.setText("FIRST NAME");

        jLabel6.setText("SURNAME");

        jLabel7.setText("LAST NAME");

        jLabel8.setText("MARK SCORED");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton1.setText("CANCEL");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton2.setText("SAVE");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        btnprev.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnprev.setText("Previous");
        btnprev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnprevMouseClicked(evt);
            }
        });

        btnnext.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnnext.setText("Next");
        btnnext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnextMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtadmno, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtfname, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtsname, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtlname, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtmark, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(btnprev))
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtadmno, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtfname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtlname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmark, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnext, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(btnprev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        lbltitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnupdate.setText("UPDATE");
        btnupdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnupdateMouseClicked(evt);
            }
        });

        btnrefresh.setText("REFRESH");
        btnrefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnrefreshMouseClicked(evt);
            }
        });

        btnadd.setText("ADD");
        btnadd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnaddMouseClicked(evt);
            }
        });

        btnview.setText("VIEW");
        btnview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnviewMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbltitle, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnview, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnadd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnupdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnrefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(22, 22, 22)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbltitle)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnupdate)
                                .addComponent(btnrefresh)
                                .addComponent(btnadd)
                                .addComponent(btnview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnviewMouseClicked
        String qry;
        qry="select adm_no, f_name from student where  class_id='"+getClassid()+"' and adm_no NOT IN(select marks.adm_no as adm_no from student, marks where marks.adm_no=student.adm_no and sub_id='"+getSubid()+"' and exam_id='"+exam+"')";
        getmarks(qry);       
    }//GEN-LAST:event_btnviewMouseClicked

    private void jcboyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboyearActionPerformed
        // TODO add your handling code here:
        if(evt.getSource()==jcboyear){
        int item;
        item=getDurationid();        
        getTerm(item);       
        
       }
    }//GEN-LAST:event_jcboyearActionPerformed

    private void jcboexamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcboexamMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jcboexamMouseClicked

    private void jcboexamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboexamActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jcboexamActionPerformed

    private void markstableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_markstableMouseClicked
        // TODO add your handling code here:
        int col,row;
        String adm;
        ResultSet rt;
        row=markstable.getSelectedRow();
        col=markstable.getColumnCount();
        adm=(String) markstable.getValueAt(row, 0);
        rt=new student().getStudent(adm);
        try {
            while(rt.next()){
                txtadmno.setText(rt.getString("adm_no"));
                txtfname.setText(rt.getString("f_name"));
                txtsname.setText(rt.getString("s_name"));
                txtlname.setText(rt.getString("l_name"));
            }
        }catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(col>2){
            txtmark.setText((String) markstable.getValueAt(row, 2));
        } else {
            txtmark.setText("0");
        }
        
    }//GEN-LAST:event_markstableMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        String qry="";
        if(action.equals("add")){
            insertmark();
            qry="select adm_no, f_name from student where  class_id='"+getClassid()+"' and adm_no NOT IN(select marks.adm_no as adm_no from student, marks where marks.adm_no=student.adm_no and sub_id='"+getSubid()+"' and exam_id='"+getExamid()+"')";
        } else if(action.equals("update")){
            updatemark();
            qry="select student.adm_no as adm_no,f_name,mark from student,marks where marks.adm_no=student.adm_no and class_id='"+getClassid()+"' and sub_id='"+getSubid()+"' and exam_id='"+getExamid()+"'";
            getmarks(qry);
        } else {
            JOptionPane.showMessageDialog(this, "Please select update or add to proceed");
        }
        getmarks(qry);
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void btnrefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnrefreshMouseClicked
        // TODO add your handling code here:
        String qry;
        qry="select student.adm_no as adm_no,f_name,mark from student,marks where marks.adm_no=student.adm_no and class_id='"+getClassid()+"' and sub_id='"+getSubid()+"' and exam_id='"+getExamid()+"'";
         getmarks(qry);
        
    }//GEN-LAST:event_btnrefreshMouseClicked

    private void btnaddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaddMouseClicked
        // TODO add your handling code here:
        String qry;
        action="add";
        qry="select adm_no, f_name from student where  class_id='"+getClassid()+"' and adm_no NOT IN(select marks.adm_no as adm_no from student, marks where marks.adm_no=student.adm_no and sub_id='"+getSubid()+"' and exam_id='"+getExamid()+"')";
        getmarks(qry);
    }//GEN-LAST:event_btnaddMouseClicked

    private void btnupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdateMouseClicked
        // TODO add your handling code here:
        String qry;
        qry="select student.adm_no as adm_no,f_name,mark from student,marks where marks.adm_no=student.adm_no and class_id='"+getClassid()+"' and sub_id='"+getSubid()+"' and exam_id='"+getExamid()+"'";
        action="update";
        getmarks(qry);
    }//GEN-LAST:event_btnupdateMouseClicked

    private void btnsubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsubmitMouseClicked
        // TODO add your handling code here:
        lbltitle.setText(jcboclass.getSelectedItem().toString()+" "+ jcbosubject.getSelectedItem().toString()+" "+ jcboexam.getSelectedItem().toString());
        
    }//GEN-LAST:event_btnsubmitMouseClicked

    private void btnsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsubmitActionPerformed

    private void btnnextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnextMouseClicked
        // TODO add your handling code here:
        int col,row,rows;
        String adm;
        ResultSet rt;
        row=markstable.getSelectedRow();
        rows=markstable.getRowCount();
        //System.out.println(rows);
        col=markstable.getColumnCount();
        markstable.setColumnSelectionAllowed(false);
        markstable.setRowSelectionAllowed(true);
        
        if( markstable.getSelectedRow()!=markstable.getRowCount() ){
            row=row+1;
            //btnnext.setEnabled(false);
        } else {
            row=markstable.getRowCount()-1;
        }
        //System.out.println(row);
        adm=(String) markstable.getValueAt(row, 0);
        rt=new student().getStudent(adm);
        try {
            while(rt.next()){
                txtadmno.setText(rt.getString("adm_no"));
                txtfname.setText(rt.getString("f_name"));
                txtsname.setText(rt.getString("s_name"));
                txtlname.setText(rt.getString("l_name"));
            }
        }catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(col>2){
            txtmark.setText((String) markstable.getValueAt(row, 2));
        } else {
            txtmark.setText("0");
        }
        markstable.removeRowSelectionInterval(row-1, 0);
        markstable.revalidate();
        markstable.addRowSelectionInterval(row, row);
    }//GEN-LAST:event_btnnextMouseClicked

    private void btnprevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnprevMouseClicked
        // TODO add your handling code here:
        int col,row,rows;
        String adm;
        ResultSet rt;
        row=markstable.getSelectedRow();
        rows=markstable.getRowCount();
        col=markstable.getColumnCount();
        markstable.setColumnSelectionAllowed(false);
        markstable.setRowSelectionAllowed(true);
        
        if(row!=0){
            row=row-1;
        } else {
            row=0;
        }
        adm=(String) markstable.getValueAt(row, 0);
        rt=new student().getStudent(adm);
        try {
            while(rt.next()){
                txtadmno.setText(rt.getString("adm_no"));
                txtfname.setText(rt.getString("f_name"));
                txtsname.setText(rt.getString("s_name"));
                txtlname.setText(rt.getString("l_name"));
            }
        }catch (SQLException ex) {
            Logger.getLogger(marks.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(col>2){
            txtmark.setText((String) markstable.getValueAt(row, 2));
        } else {
            txtmark.setText("0");
        }
        markstable.removeRowSelectionInterval(row+1, 0);
        markstable.revalidate();
        markstable.addRowSelectionInterval(row, row);
    }//GEN-LAST:event_btnprevMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new marks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnprev;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsubmit;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton btnview;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JComboBox jcboclass;
    private javax.swing.JComboBox jcboexam;
    public javax.swing.JComboBox jcbosubject;
    private javax.swing.JComboBox jcboterm;
    private javax.swing.JComboBox jcboyear;
    private javax.swing.JLabel lbltitle;
    private javax.swing.JTable markstable;
    private javax.swing.JTextField txtadmno;
    private javax.swing.JTextField txtfname;
    private javax.swing.JTextField txtlname;
    private javax.swing.JTextField txtmark;
    private javax.swing.JTextField txtsname;
    // End of variables declaration//GEN-END:variables
}
