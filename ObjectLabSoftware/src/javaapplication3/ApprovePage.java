package javaapplication3;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication3.PendingJobsView.dba;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matt
 */
public class ApprovePage extends javax.swing.JFrame {

    /**
     * Creates new form ApprovePage
     */
    String ID, fileName, printer, fileLoc;
    boolean error;

    public void approveSubmission(String file, String print, String fileLocation, String id) {
        fileName = file;
        printer = print;
        fileLoc = fileLocation;
        ID = id;
        
        initComponents();
        hideErrorFields();
        
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
            {
                if ("Windows".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(ApprovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        setVisible(true);
    }

    private void hideErrorFields() {
        errorTxt1.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ObjectNameLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        volumeTextField = new javax.swing.JTextField();
        CancelButton = new javax.swing.JButton();
        SubmitButton = new javax.swing.JButton();
        errorTxt1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Approved Job");
        setBackground(new java.awt.Color(62, 63, 63));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ObjectNameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(ObjectNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 11, -1, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Volume(Cubic Inches):");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 107, 20));
        getContentPane().add(volumeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, -1));

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        SubmitButton.setText("Submit");
        SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(SubmitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));

        errorTxt1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        errorTxt1.setForeground(new java.awt.Color(255, 0, 0));
        errorTxt1.setText("Error Text");
        getContentPane().add(errorTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 119, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Approved Project Information");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 360, 10));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication3/black and white bg.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 140));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitButtonActionPerformed
        errorTxt1.setVisible(false);
        Double Volume = null;
        
        if (!volumeTextField.getText().equals("")) 
        {
            try 
            {
                Volume = Double.parseDouble(volumeTextField.getText());
            } 
            catch (Exception ex) 
            {
                error = true;
                errorTxt1.setText("*Numbers only");
                errorTxt1.setVisible(true);
            }
        } 
        else 
        {
            error = true;
            errorTxt1.setText("*Empty Field");
            errorTxt1.setVisible(true);
        }

        if (!error) 
        {
            dba.updatePendingJobVolume(ID, Volume);
            System.out.println(fileLoc);
            File newDir = new File(PendingJobsView.inst.getDrive() + "\\ObjectLabPrinters\\" + printer + "\\ToPrint");
            
            try 
            {
                FileUtils.moveFileToDirectory(new File(PendingJobsView.inst.getSubmission() + "\\" + fileName), newDir, true);
                dba.updatePendingJobFLocation(ID, fileName);
                PendingJobsView.dba.approve(ID);
            } 
            catch (SQLException | IOException ex) 
            {
                Logger.getLogger(ApprovePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            dispose();
            
        }
    }//GEN-LAST:event_SubmitButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JLabel ObjectNameLabel;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JLabel errorTxt1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField volumeTextField;
    // End of variables declaration//GEN-END:variables
}
