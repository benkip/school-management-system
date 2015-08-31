/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pdf;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.testutils.*;
import com.itextpdf.awt.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.ByteBuffer;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.xmp.*;
import com.sun.pdfview.*;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ben
 */
public class itextpdf extends javax.swing.JFrame {

    /**
     * Creates new form itextpdf
     */
    private String sch="CHEPTUIYET GIRLS SECONDARY SCHOOL";
    public itextpdf() {
        initComponents();
    }
    
    public void createreportform(String []col, String [][]data,int row, String tot){
        Document document = new Document();
        
        try {
            FileOutputStream report=new FileOutputStream("report.pdf");
            PdfWriter.getInstance(document, report);

            document.open();
            Paragraph paragraph = new Paragraph();
            Paragraph paragraph2 = new Paragraph();
            Paragraph total = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            Font f=new Font(FontFamily.TIMES_ROMAN,20.0f,Font.UNDERLINE,BaseColor.BLUE);
            Chunk title=new Chunk(sch);
            title.setFont(f);
            paragraph.add(title);
            document.add(paragraph);
            document.add(new Paragraph(" "));
            Chunk chunk=new Chunk("STUDENT REPORT FORM");
            paragraph2.add(chunk);
            document.add(paragraph2);
            document.add(new Paragraph(" "));
            document.add(createTable(col,data,row));
            document.add(new Paragraph(" "));
            Chunk ch=new Chunk("TOTAL MARKS"+"  "+tot);
            total.add(ch);
            document.add(total);
            document.close(); // no need to close PDFwriter?

        } catch (DocumentException | FileNotFoundException e) {
        }
        
    }
    public File getReport(){
      File file=new File("report.pdf");
      return file;
  }
    public void createPdf(String []col, String [][]data,int row){
        Document document = new Document();

        try {
            PdfWriter.getInstance(document,
                new FileOutputStream("HelloWorld.pdf"));

            document.open();
            Paragraph paragraph = new Paragraph();
            Paragraph paragraph2 = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            Font f=new Font(FontFamily.TIMES_ROMAN,20.0f,Font.UNDERLINE,BaseColor.BLUE);
            Chunk title=new Chunk(sch);
            title.setFont(f);
            paragraph.add(title);
            document.add(paragraph);
            document.add(new Paragraph(" "));
            Chunk chunk=new Chunk("Class Performane");
            paragraph2.add(chunk);
            document.add(paragraph2);
            document.add(new Paragraph(" "));
            document.add(createTable(col,data,row));
            document.close(); // no need to close PDFwriter?

        } catch (DocumentException | FileNotFoundException e) {
        }
    }
    public  static PdfPTable createTable(String []cols, String [][]data, int row){
        int x=cols.length;
        int i=0;
        PdfPTable table = new PdfPTable(x);
        while(i<x){
        PdfPCell c1 = new PdfPCell(new Phrase(cols[i]));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        i++;
        }
        table.setHeaderRows(1);
        
        for(i=0; i<row; i++){
            for(int j=0; j<x; j++){
                table.addCell(data[i][j]);
            }
        }  
        return table;
    }
  private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
  public File getClassReport(){
      File file=new File("HelloWorld.pdf");
        return file;
  }
    public void viewPdf(File file){
 
            //load a pdf from a byte buffer
            //File file = new File("HelloWorld.pdf");            
            PDFViewer pdfv = new PDFViewer(true);
            try {
                pdfv.openFile(file) ;
            } catch (IOException ex) {
                Logger.getLogger(itextpdf.class.getName()).log(Level.SEVERE, null, ex);
            }
            pdfv.setEnabling();
            pdfv.pack();
            //pdfv.setDefaultCloseOperation();
            pdfv.setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pdfpanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pdfpanelLayout = new javax.swing.GroupLayout(pdfpanel);
        pdfpanel.setLayout(pdfpanelLayout);
        pdfpanelLayout.setHorizontalGroup(
            pdfpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );
        pdfpanelLayout.setVerticalGroup(
            pdfpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(pdfpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(pdfpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(itextpdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(itextpdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(itextpdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(itextpdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new itextpdf().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pdfpanel;
    // End of variables declaration//GEN-END:variables
}
