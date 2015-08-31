/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exam;

import form.form;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import login.dbconnect;
import login.home;
import student.*;
import pdf.*;

/**
 *
 * @author Ben
 */
public class results extends javax.swing.JFrame {

    /**
     * Creates new form results
     */
    int sub,duration,term,exam;
    public results() {
        initComponents();
        getYear();
        ResultSet rt;
        rt=new form().getform();
        try {
            while(rt.next()){
                jcboclass.addItem(rt.getString("class_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
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
    public String [] getCols(){
        dbconnect db=new dbconnect();
        db.Connect();
        String []col1 = {"Adm_no","Name"};
        List list=new ArrayList(Arrays.asList(col1));
        db.rst=db.queryString("select sub_name from subject");
        try {
            while(db.rst.next()){
                list.add(db.rst.getString("sub_name"));
            }
            db.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.add("TOTAL");
        String[] ColumnNames = new String[list.size()];
        ColumnNames = (String[]) list.toArray(ColumnNames);
        return ColumnNames;
    }
 public int getMark(){
        
        return 0;
        
    }
    public String[] getSubjectId(){        
        dbconnect db=new dbconnect();
        db.Connect();
        List list = new ArrayList();
       // List list=null;
        if(list==null){
            System.out.println("yes");
            
            }
        db.qry="select * from subject";
        db.rst=db.queryString(db.qry);
        try {
            if(!db.rst.isBeforeFirst()){
            System.out.println("yes");
            }
            while(db.rst.next()){
                list.add(db.rst.getString("sub_id"));
                //System.out.println(db.rst.getString("sub_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] subs = new String[list.size()];
        subs = (String[]) list.toArray(subs);
        return subs; 
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
    public void populate() throws SQLException {
        String [] Column= getCols();
        int rows,cols,counter,mark,total;
        String [] subs= getSubjectId();
        ResultSet rst;
        String adm;
        //db.rst=db.queryString(qry);
        ResultSetMetaData rsmd; 
        DefaultTableModel mod=(DefaultTableModel) resultstable.getModel();
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
        db.rst=db.queryString("select adm_no,CONCAT(f_name,'  ',l_name,'  ',s_name) as name from student where class_id='"+getClassId()+"'");
        //ADDING ROWS
        int X=0;
        int subid=0;
        int len=subs.length;
        while(db.rst.next()){
            Vector rowX= new Vector();
            rowX.addElement(db.rst.getString("adm_no"));
            adm=db.rst.getString("adm_no");
            rowX.addElement(db.rst.getString("name"));
            subid=0;
            total=0;
            while(subid<len){
                mark=(getCatOne(adm,Integer.parseInt(subs[subid]))+getCatTwo(adm,Integer.parseInt(subs[subid])))/2+getEndTerm(adm,Integer.parseInt(subs[subid]));
                rowX.addElement(mark);
                total=total+mark;
                subid++;                
            }
            rowX.addElement(total);
            //rowX.addElement(db.rst.getString("s_name"));
           // rowX.addElement(db.rst.getString("image"));
            mod.addRow(rowX);
            X++;
            //db.rst.next();
        }
        resultstable.setModel(mod);
        resultstable.getColumnModel().getColumn(1).setMinWidth(150);
        mod.fireTableDataChanged();
        //jTable1.isCellEditable(1, 1);
    }
    public String [][] getData(){        
        int row=resultstable.getRowCount(), col=resultstable.getColumnCount();
        String [][]data=new String[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                data[i][j]=resultstable.getValueAt(i, j).toString();
                //System.out.print(resultstable.getValueAt(i, j).toString());
            }
            //System.out.println();
        }        
        return data;
    }
    public int getRows(){
        return resultstable.getRowCount();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        resultstable = new javax.swing.JTable();
        btnview = new javax.swing.JButton();
        jcboyear = new javax.swing.JComboBox();
        jcboterm = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcboclass = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        btnrank = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RESULTS");

        resultstable.setAutoCreateRowSorter(true);
        resultstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(resultstable);

        btnview.setText("VIEW");
        btnview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnviewMouseClicked(evt);
            }
        });

        jcboyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboyearActionPerformed(evt);
            }
        });

        jcboterm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        jLabel1.setText("Term");

        jLabel2.setText("Year");

        jLabel3.setText("Form");

        jButton1.setText("pdf");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        btnrank.setText("Rank");
        btnrank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnrankMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jcboyear, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcboterm, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcboclass, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnview, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnrank)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcboterm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcboclass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnview))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnrank)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnviewMouseClicked
        // TODO add your handling code here:
        try {
        populate();
        }  catch (SQLException ex) {
            
        }
        String []data=getCols();
        //new pdfjava().createPdf(data);
       // new pdfjava().openPdf();
        
    }//GEN-LAST:event_btnviewMouseClicked

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
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
        duration=item;
        
        getTerm(item);
        
        
       }
    }//GEN-LAST:event_jcboyearActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        new itextpdf().createPdf(getCols(),getData(),getRows());
        new itextpdf().viewPdf(new itextpdf().getClassReport());
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnrankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnrankMouseClicked
        // TODO add your handling code here:
        TableRowSorter sorter = new TableRowSorter(resultstable.getModel());
        resultstable.setRowSorter(sorter);
        sorter.setSortsOnUpdates(true); 
        //mod.fireTableDataChanged();
    }//GEN-LAST:event_btnrankMouseClicked

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
            java.util.logging.Logger.getLogger(results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new results().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnrank;
    private javax.swing.JButton btnview;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcboclass;
    private javax.swing.JComboBox jcboterm;
    private javax.swing.JComboBox jcboyear;
    private javax.swing.JTable resultstable;
    // End of variables declaration//GEN-END:variables
}
