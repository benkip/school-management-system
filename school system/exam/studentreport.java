/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exam;

/**
 *
 * @author Ben
 */
import form.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import login.dbconnect;
import pdf.itextpdf;
public class studentreport extends javax.swing.JFrame {

    /**
     * Creates new form studentreport
     */
    int sub,duration,term,exam;
    String [] Column= {"Subject","Cat One", "Cat Two", "EndTerm","Total Mark","Grade"};
    String totalscored;
    public studentreport() {
        initComponents();
        ResultSet rt;
        rt=new form().getform();
        try {
            while(rt.next()){
                jcboclass.addItem(rt.getString("class_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentreport.class.getName()).log(Level.SEVERE, null, ex);
        }
        getYear();
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
            Logger.getLogger(studentreport.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(studentreport.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public int getTermId(){
         dbconnect db=new dbconnect();
         ResultSet rt;
         db.Connect();
         int id=0;
         db.qry="select term_id from term where duration_id='"+duration+"' and term_name='"+jcboterm.getSelectedItem().toString()+"'";
         rt=db.queryString(db.qry);
        try {
            rt.first();
            id= rt.getInt("term_id");
        } catch (SQLException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
     }
     public int getClassId(){
        int id=0;
         dbconnect db=new dbconnect();
         ResultSet rt;
         db.Connect();
         db.qry="select class_id from class where class_name='"+jcboclass.getSelectedItem().toString()+"'";
         rt=db.queryString(db.qry);
         try {
           if(rt.first())
            id= rt.getInt("class_id");
        } catch (SQLException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
         return id;        
    }
    public int getExamId(String exam){
         dbconnect db=new dbconnect();
         ResultSet rt;
         db.Connect();
         int id=0;
         db.qry="select exam_id from exam where term_id='"+getTermId()+"' and exam_name='"+exam+"'";
         rt=db.queryString(db.qry);
         try {
            if(rt.first())
            id= rt.getInt("exam_id");
        } catch (SQLException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
         return id;
     }
    public int getCatOne(String adm,int subid){
         int catone=0;
         dbconnect db=new dbconnect();
         ResultSet rt;
         db.Connect();
         db.qry="select mark from marks where exam_id='"+getExamId("cat one")+"' and adm_no='"+adm+"' and sub_id='"+subid+"'";
         rt=db.queryString(db.qry);
         try {
            if(rt.first())
            catone= rt.getInt("mark");
        } catch (SQLException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
         return catone;
     }
    public int getCatTwo(String adm,int subid){
         int cattwo=0;
         dbconnect db=new dbconnect();
         ResultSet rt;
         db.Connect();
         db.qry="select mark from marks where exam_id='"+getExamId("cat two")+"' and adm_no='"+adm+"' and sub_id='"+subid+"'";
         rt=db.queryString(db.qry);
         try {
           if(rt.first())
            cattwo= rt.getInt("mark");
        } catch (SQLException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
         return cattwo;
     }
      public int getEndTerm(String adm,int subid){
         int end=0;
         dbconnect db=new dbconnect();
         ResultSet rt;
         db.Connect();
         db.qry="select mark from marks where exam_id='"+getExamId("End term")+"' and adm_no='"+adm+"' and sub_id='"+subid+"'";
         rt=db.queryString(db.qry);
         try {
           if(rt.first())
            end= rt.getInt("mark");
        } catch (SQLException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
         return end;
     }
     public String getGrade(int mark){
         String grade="";
         if(mark>79 && mark<=100){
             grade="A";
         }else if(mark>74 && mark<=79){
             grade="A-";
         } else if(mark>69 && mark<=74){
             grade="B+";
         } else if(mark>64 && mark<=69){
             grade="B";
         } else if(mark>59 && mark<=64){
             grade="B-";
         } else if(mark>54 && mark<=59){
             grade="C+";
         }else if(mark>49 && mark<=54){
             grade="C";
         }else if(mark>44 && mark<=49){
             grade="C-";
         } else if(mark>39 && mark<=44){
             grade="D+";
         }else if(mark>34 && mark<=39){
             grade="D";
         }else if(mark>29 && mark<=34){
             grade="D-";
         }else if(mark>=0 && mark<=29){
             grade="E";
         }
        return grade; 
     }
     public void getStudent(){
         dbconnect db=new dbconnect();
         db.Connect();
         db.rst=db.queryString("select adm_no,CONCAT(f_name,'  ',l_name,'  ',s_name) as name from student where class_id='"+getClassId()+"'");         
        try {
            if(db.rst.first()) {
                txtname.setText(db.rst.getString("name"));
                txtadm.setText(db.rst.getString("adm_no"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentreport.class.getName()).log(Level.SEVERE, null, ex);
        }
                
     }

     public void populate() throws SQLException {
        
        int rows,cols,counter,mark,total;
        ResultSet rst;
        String subj;
        String adm=txtadm.getText();
        //db.rst=db.queryString(qry);
        ResultSetMetaData rsmd; 
        DefaultTableModel mod=(DefaultTableModel) reportstable.getModel();
        mod.setRowCount(0);
        mod.setColumnCount(0);
        //ADDING COLUMN NAMES
        cols=Column.length;
        counter=0;
        while(counter<cols){
            mod.addColumn(Column[counter].toUpperCase());
           counter++;
        }
        dbconnect db=new dbconnect();
        db.Connect();
        //db.rst=db.queryString("select adm_no,CONCAT(f_name,'  ',l_name,'  ',s_name) as name from student where class_id='"+getClassId()+"'");
        db.rst=db.queryString("select * from subject");
        //ADDING ROWS
        int X=0;
        int subid=0;
        int scored=0;
        while(db.rst.next()){
            Vector rowX= new Vector();
            rowX.addElement(db.rst.getString("sub_name"));
            subj=db.rst.getString("sub_name");
            //rowX.addElement(db.rst.getString("name"));
            rowX.addElement(getCatOne(adm,db.rst.getInt("sub_id")));
            rowX.addElement(getCatTwo(adm,db.rst.getInt("sub_id")));
            rowX.addElement(getEndTerm(adm,db.rst.getInt("sub_id")));
            mark=(getCatOne(adm,db.rst.getInt("sub_id"))+getCatTwo(adm,db.rst.getInt("sub_id")))/2+getEndTerm(adm,db.rst.getInt("sub_id"));
            scored=scored+mark;
            rowX.addElement(mark);                
            rowX.addElement(getGrade(mark));
            //rowX.addElement(db.rst.getString("s_name"));
           // rowX.addElement(db.rst.getString("image"));
            mod.addRow(rowX);
            X++;
            //db.rst.next();
        }
        reportstable.setModel(mod);
        //reportstable.getColumnModel().getColumn(1).setMinWidth(150);
        mod.fireTableDataChanged();
        //return total marks for printing
        totalscored=String.valueOf(scored);
        txtscored.setText(String.valueOf(scored));
        //jTable1.isCellEditable(1, 1);
    }
public String [][] getData(){        
        int row=reportstable.getRowCount(), col=reportstable.getColumnCount();
        String [][]data=new String[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                data[i][j]=reportstable.getValueAt(i, j).toString();
                //System.out.print(resultstable.getValueAt(i, j).toString());
            }
            //System.out.println();
        }        
        return data;
    }
    public int getRows(){
        return reportstable.getRowCount();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnview = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jcboclass = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jcboyear = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcboterm = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportstable = new javax.swing.JTable();
        lblname = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtadm = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnprev = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnprint = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtscored = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtposition = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnview.setText("view");
        btnview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnviewMouseClicked(evt);
            }
        });

        jLabel1.setText("Form");

        jLabel2.setText("Year");

        jcboyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboyearActionPerformed(evt);
            }
        });

        jLabel3.setText("Term");

        reportstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(reportstable);

        lblname.setText("NAME");

        txtname.setEnabled(false);

        jLabel4.setText("ADMISSION NUMBER");

        txtadm.setEnabled(false);

        jLabel5.setText("FORM");

        jTextField1.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtname)
                            .addComponent(txtadm, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblname)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtadm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        btnprev.setText("PREVIOUS");
        btnprev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnprevMouseClicked(evt);
            }
        });

        btnnext.setText("NEXT");
        btnnext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnextMouseClicked(evt);
            }
        });

        btnprint.setText("Print");
        btnprint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnprintMouseClicked(evt);
            }
        });

        jLabel6.setText("TOTAL MARKS");

        txtscored.setEditable(false);

        jLabel7.setText("POSITION");

        txtposition.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnview)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboclass, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboyear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcboterm, 0, 85, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(btnprev)
                        .addGap(18, 18, 18)
                        .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnprint)))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtscored, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtposition, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnview)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcboclass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcboterm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnprev)
                    .addComponent(btnnext)
                    .addComponent(btnprint))
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtscored, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtposition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcboyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboyearActionPerformed
        // TODO add your handling code here:
        if(evt.getSource()==jcboyear){
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
            Logger.getLogger(studentreport.class.getName()).log(Level.SEVERE, null, ex);
        }
        duration=item;
        
        getTerm(item);
        
        }
    }//GEN-LAST:event_jcboyearActionPerformed

    private void btnviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnviewMouseClicked
        
        getStudent();
        try {
            // TODO add your handling code here:
            populate();
        } catch (SQLException ex) {
            Logger.getLogger(studentreport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnviewMouseClicked

    private void btnnextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnextMouseClicked
        // TODO add your handling code here:
        dbconnect db=new dbconnect();
        db.Connect();
        db.rst=db.queryString("select adm_no,CONCAT(f_name,'  ',l_name,'  ',s_name) as name from student where class_id='"+getClassId()+"'"); 
        try {
             while(db.rst.next()){
              if(db.rst.getString("adm_no").equals(txtadm.getText())){
                         if(db.rst.next()){
                         txtname.setText(db.rst.getString("name"));                           
                         txtadm.setText(db.rst.getString("adm_no"));
                         }
                         break;
                 } 
              populate();
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(studentreport.class.getName()).log(Level.SEVERE, null, ex);
             }
    }//GEN-LAST:event_btnnextMouseClicked

    private void btnprevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnprevMouseClicked
        // TODO add your handling code here:
        dbconnect db=new dbconnect();
        db.Connect();
        db.rst=db.queryString("select adm_no,CONCAT(f_name,'  ',l_name,'  ',s_name) as name from student where class_id='"+getClassId()+"'"); 
        try {
             while(db.rst.next()){
              if(db.rst.getString("adm_no").equals(txtadm.getText())){
                         if(db.rst.previous()){
                         txtname.setText(db.rst.getString("name"));                           
                         txtadm.setText(db.rst.getString("adm_no"));
                         }
                         break;
                 } 
              populate();
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(studentreport.class.getName()).log(Level.SEVERE, null, ex);
             }
    }//GEN-LAST:event_btnprevMouseClicked

    private void btnprintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnprintMouseClicked
        // TODO add your handling code here:
        
        new itextpdf().createreportform(Column,getData(),getRows(),totalscored);
        new itextpdf().viewPdf(new itextpdf().getReport());
    }//GEN-LAST:event_btnprintMouseClicked

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
            java.util.logging.Logger.getLogger(studentreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(studentreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(studentreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studentreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new studentreport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnprev;
    private javax.swing.JButton btnprint;
    private javax.swing.JButton btnview;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox jcboclass;
    private javax.swing.JComboBox jcboterm;
    private javax.swing.JComboBox jcboyear;
    private javax.swing.JLabel lblname;
    private javax.swing.JTable reportstable;
    private javax.swing.JTextField txtadm;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtposition;
    private javax.swing.JTextField txtscored;
    // End of variables declaration//GEN-END:variables
}
