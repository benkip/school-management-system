package student;


import login.dbconnect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ben
 */
public class studentupdate extends javax.swing.JFrame {

    /**
     * Creates new form studentupdate
     */
    private String imgname;
    public int clid;
    public studentupdate() {
        initComponents();
       //update();
    }
    public void update(String adm){
        dbconnect db=new dbconnect();
        db.Connect();
        db.qry="select * from student where adm_no='"+adm+"'";
        db.rst=db.queryString(db.qry);
        try {
            while(db.rst.next()){
                
                txtfname.setText(db.rst.getString("f_name"));
                txtsname.setText(db.rst.getString("s_name"));
                txtlname.setText(db.rst.getString("l_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentupdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtadm = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtfname = new javax.swing.JTextField();
        txtsname = new javax.swing.JTextField();
        txtlname = new javax.swing.JTextField();
        jbtnsave = new javax.swing.JButton();
        jbtncancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jcboclass = new javax.swing.JComboBox();
        lblimg = new javax.swing.JLabel();
        btnattach = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ADMISSION NUMBER");

        txtadm.setEditable(false);

        jLabel2.setText("FIRST NAME");

        jLabel3.setText("SURNAME ");

        jLabel4.setText("LAST NAME");

        jbtnsave.setText("SAVE");
        jbtnsave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnsaveMouseClicked(evt);
            }
        });

        jbtncancel.setText("CANCEL");
        jbtncancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtncancelMouseClicked(evt);
            }
        });

        jLabel5.setText("FORM");

        jcboclass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "select class" }));

        lblimg.setBackground(new java.awt.Color(255, 255, 204));
        lblimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/img_.gif"))); // NOI18N

        btnattach.setText("Attach Image");
        btnattach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnattachMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtadm, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(txtfname)
                            .addComponent(txtsname)
                            .addComponent(txtlname)
                            .addComponent(jcboclass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jbtncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jbtnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnattach, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtadm, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtlname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcboclass, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnattach, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnsaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnsaveMouseClicked
        // TODO add your handling code here:
        String adm,fname,sname,lname;
        adm=txtadm.getText();
        fname=txtfname.getText();
        sname=txtsname.getText();
        lname=txtlname.getText();
        dbconnect db=new dbconnect();
        db.Connect();
        db.qry="update student set f_name='"+fname+"', s_name='"+sname+"', l_name='"+lname+"' , class_id="+clid+ ", image='"+imgname+"' where adm_no='"+adm+"'";
        db.Insert(db.qry);     
        this.dispose();
    }//GEN-LAST:event_jbtnsaveMouseClicked

    public static void copyFile(File source, File dest) throws IOException {
	    InputStream input = null;
	    OutputStream output = null;
	    try {
	        input = new FileInputStream(source);
	        output = new FileOutputStream(dest);
	        byte[] buf = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = input.read(buf)) > 0) {
	            output.write(buf, 0, bytesRead);
	        }
	    } finally {
	        input.close();
	        output.close();
	    }
	}
    
    private void jbtncancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtncancelMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jbtncancelMouseClicked

    private void btnattachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnattachMouseClicked
        // TODO add your handling code here:
        String path,dest,id,filename,ext,filedest;
        JFileChooser jfc;
        jfc = new JFileChooser();        
        File f = new File(System.getProperty("user.dir"));
        dest=f.getAbsolutePath().toString()+"\\src\\images\\";
        id="std_"+txtadm.getText().substring(3);
        ext="";
        jfc.setCurrentDirectory(f);
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF, and PNG Images", "jpg", "gif", "png");
        jfc.setFileFilter(filter);
        File selFile = jfc.getSelectedFile();
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) { 
            path=jfc.getSelectedFile().toString();
            File src=new File(path);
            filename=src.getName();
            if (filename.indexOf(".") > 0) {
                 ext = filename.substring(filename.lastIndexOf("."));
            }
            filedest=dest+id+ext;
            File des=new File(filedest);
            try {            
                copyFile(src,des);
            } catch (IOException ex) {
                Logger.getLogger(studentupdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ImageIcon img=new ImageIcon(filedest);
            lblimg.setIcon(img);
            imgname=id+ext;
      }
    else {
      System.out.println("No Selection ");
      }
               
    }//GEN-LAST:event_btnattachMouseClicked

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
            java.util.logging.Logger.getLogger(studentupdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(studentupdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(studentupdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studentupdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new studentupdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnattach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jbtncancel;
    private javax.swing.JButton jbtnsave;
    private javax.swing.JComboBox jcboclass;
    private javax.swing.JLabel lblimg;
    public javax.swing.JTextField txtadm;
    private javax.swing.JTextField txtfname;
    private javax.swing.JTextField txtlname;
    private javax.swing.JTextField txtsname;
    // End of variables declaration//GEN-END:variables
}
