package ObjectLabEnterpriseSoftware;

import java.awt.Desktop;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel; 

public class JobsView extends javax.swing.JFrame
{
    private static final String NAME_OF_PAGE =  "Student submissions";

    private static final int PROJECT_NAME_COLUMN_NUMBER = 0;
    private static final int FIRST_NAME_COLUMN_NUMBER = 1;
    private static final int LAST_NAME_COLUMN_NUMBER = 2;
    private static final int PRINTER_COLUMN_NUMBER = 3;
    private static final int DATE_PROJECT_STARTED_COLUMN_NUMBER = 4;

    private DefaultTableModel allFileTableModel;
    static ReportsView reports = null;
    private static FileManager inst = null;
    
    private static void updateView(String status, DefaultTableModel pendingJobsView, ArrayList<ArrayList<Object>> view)
    {
        pendingJobsView.setColumnIdentifiers(UtilController.getStatusJobsHeaders(status));
        
        /* Clears up the rows in the view's model. */
        for(int rows = pendingJobsView.getRowCount() - 1; rows >= 0; rows--)
            pendingJobsView.removeRow(rows);
        
        /* Inserts data found in (ArrayList -> listOfRows) by row into the UI model to display */
        for (ArrayList<Object> row : view) 
            pendingJobsView.addRow(row.toArray());
    }
    
    public JobsView() 
    {
        inst = new FileManager();
        reports = new ReportsView();
         /* Creates are PendingJobs UI window componet and grabs its data model for our uses */
        initComponents();
        allFileTableModel = (DefaultTableModel) PendingTable.getModel();
        
        addWindowListener
        (
            new WindowAdapter() 
            {
                @Override
                public void windowClosing(WindowEvent we) 
                {
                    /* If they close the program then close out the window properly */
                    dispose();
                    System.exit(0);
                }
            }
        );
    }

    public void PendingJobsStart() 
    {
        /* Updates table */
        updateView((String) jobStatus.getSelectedItem(), allFileTableModel, UtilController.updatePendingTableData((String) jobStatus.getSelectedItem()));
        setVisible(true);
    }
    
    /**
      * Takes the table model, selected row, and the column you are interested in and returns
      * the row number that the user selected.
      */
    public static int getSelectedRowNum(DefaultTableModel dm, int selectedRow, int column)
    {
        if (selectedRow < 0)
            return -1;
        
        for (int i = 0; i < dm.getRowCount(); i++)
            if (dm.getValueAt(i, column).equals(dm.getValueAt(selectedRow, column)))
                return i;
        
        return -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ApprovedButton = new javax.swing.JButton();
        RejectButton = new javax.swing.JButton();
        openFileInProgram = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        PendingTable = new javax.swing.JTable();
        backToMainMenu = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jobStatus = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        showClassEditorOptions = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jList1.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Art 101-001\nArt 201-002\nArt 401-004\nArt 501-005\nArt 601-006\nArt 701-007\nArt 801-009");
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(UtilController.getPageName(NAME_OF_PAGE));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Pending Jobs");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 750, 10));

        ApprovedButton.setBackground(java.awt.Color.green);
        ApprovedButton.setText("Approve");
        ApprovedButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ApprovedButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ApprovedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, 90, 20));

        RejectButton.setBackground(java.awt.Color.red);
        RejectButton.setText("Reject");
        RejectButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                RejectButtonActionPerformed(evt);
            }
        });
        getContentPane().add(RejectButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 490, 70, 20));

        openFileInProgram.setText("Review File");
        openFileInProgram.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                openFileInProgramActionPerformed(evt);
            }
        });
        getContentPane().add(openFileInProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 490, 140, 20));

        PendingTable.setAutoCreateRowSorter(true);
        PendingTable.setModel(new javax.swing.table.DefaultTableModel()
            {
                boolean[] canEdit = new boolean []
                {
                    false, false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex)
                {
                    return canEdit [columnIndex];
                }
            });
            jScrollPane4.setViewportView(PendingTable);

            getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 750, 410));

            backToMainMenu.setText("Back");
            backToMainMenu.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    backToMainMenuActionPerformed(evt);
                }
            });
            getContentPane().add(backToMainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 60, 20));

            jLabel3.setText("Job status:");
            getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 60, 20));

            jobStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pending", "rejected", "approved" }));
            jobStatus.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    jobStatusActionPerformed(evt);
                }
            });
            getContentPane().add(jobStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 70, 20));

            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ObjectLabEnterpriseSoftware/images/white_bg.jpg"))); // NOI18N
            getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 780, 530));

            jMenu1.setText("File");

            jMenuItem1.setText("Reports");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    jMenuItem1ActionPerformed(evt);
                }
            });
            jMenu1.add(jMenuItem1);

            showClassEditorOptions.setText("Class Settings");
            showClassEditorOptions.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    showClassEditorOptionsActionPerformed(evt);
                }
            });
            jMenu1.add(showClassEditorOptions);

            jMenuBar1.add(jMenu1);

            jMenu2.setText("Help");

            jMenuItem2.setText("Contents");
            jMenuItem2.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    jMenuItem2ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem2);

            jMenuBar1.add(jMenu2);

            setJMenuBar(jMenuBar1);

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void RejectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RejectButtonActionPerformed
        int userSelectedRow = PendingTable.getSelectedRow();
        String desc;
        
        if (userSelectedRow >= 0) 
        {
        desc = JOptionPane.showInputDialog(new java.awt.Frame(), "Enter in reject description: ");
        
        if(desc != null)
        {

            /* Hand off the data in the selected row found in our tablemodel to this method so we can 
             * reject the correct file -Nick 
             */
           boolean success = UtilController.rejectStudentSubmission
           ( 
                   (String) allFileTableModel.getValueAt(userSelectedRow, PROJECT_NAME_COLUMN_NUMBER), 
                    desc
           );

           if(success)
           {
               JOptionPane.showMessageDialog(new JFrame(), "Email sent succesfully!");
               updateView((String) jobStatus.getSelectedItem(), allFileTableModel, UtilController.updatePendingTableData((String) jobStatus.getSelectedItem()));
           }
           else
           {
               JOptionPane.showMessageDialog(new JFrame(), "Rejection of student submission failed!");
           }
        }
        } else
        {
            JOptionPane.showMessageDialog(new JFrame(), "Please select a submission file to reject!");
        }
    }//GEN-LAST:event_RejectButtonActionPerformed
    
    /**
      * This is probably something that should be in a general Utils class for the front end or the various "views".
      * I'm leaving this here for now because I don't want to change or add anything else that could affect other 
      * groups. -Nick
      */
    private static double getDouble(String msg, double min, double max)
    {
        String tmp;
        do
        {
            
            tmp = JOptionPane.showInputDialog(null, msg);
            
            if(tmp != null)
            {
                Scanner inputChecker = new Scanner(tmp);
                double volume;

                if(inputChecker.hasNextDouble())
                {
                    volume = inputChecker.nextDouble();
                    if(volume >= min && volume <= max)
                        return volume;
                }
                else
                {
                    if(inputChecker.hasNext())
                        inputChecker.next();
                }
            }
            else
            {
                return -1;
            }
            
        } while (true);
    }
    
    private void ApprovedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApprovedButtonActionPerformed
        int userSelectedRow = PendingTable.getSelectedRow();
        int maxVolume = 10000; /* Will need to have this in a cfg file.... */
        
        if (userSelectedRow > -1) 
        {
            int rowDataLocation = getSelectedRowNum(allFileTableModel, userSelectedRow, 0);
            double volume = getDouble("Enter volume (in cubic inches): ", 1, maxVolume);   
            
            if(volume >= 1)
            {             
                /* Hand off the data in the selected row found in our tablemodel to this method so we can 
                    approve the correct file to be printed... -Nick 
                */
                UtilController.approveStudentSubmission
                (
                    (String) allFileTableModel.getValueAt(rowDataLocation, PROJECT_NAME_COLUMN_NUMBER),
                    volume
                );
                updateView((String) jobStatus.getSelectedItem(), allFileTableModel, UtilController.updatePendingTableData((String) jobStatus.getSelectedItem()));
            }        
        }
         
    }//GEN-LAST:event_ApprovedButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        reports.ReportsPage();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        try 
        {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + inst.getPDFAdmin());
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Error");  //print the error
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
    /* This button when pressed will pull up the options menu for editing courses and choosing a "current" course. */
    private void showClassEditorOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showClassEditorOptionsActionPerformed
       new ClassOptionsView().OptionsStart();
    }//GEN-LAST:event_showClassEditorOptionsActionPerformed

    private void openFileInProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileInProgramActionPerformed
        
        int userSelectedRow = PendingTable.getSelectedRow();
        
        if (userSelectedRow > -1) 
        {
            int rowDataLocation = getSelectedRowNum(allFileTableModel, userSelectedRow, 0);
            
            /* Hand off the data in the selected row found in our tablemodel to this method so we can 
                open the correct file with the information found in the row that was clicked on. -Nick 
            */
            File fileLocation = UtilController.getFilePath
            (
                (String) allFileTableModel.getValueAt(rowDataLocation, FIRST_NAME_COLUMN_NUMBER),
                (String) allFileTableModel.getValueAt(rowDataLocation, LAST_NAME_COLUMN_NUMBER),
                (String) allFileTableModel.getValueAt(rowDataLocation, PROJECT_NAME_COLUMN_NUMBER),
                (String) allFileTableModel.getValueAt(rowDataLocation, DATE_PROJECT_STARTED_COLUMN_NUMBER)
            );
            //TODO: display popup/error if this is false
            UtilController.checkFileExists(fileLocation.getPath());
            try 
            {
                Desktop.getDesktop().open(fileLocation);
            } catch (IOException ex) 
            {
                Logger.getLogger(JobsView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else 
        {
            JOptionPane.showMessageDialog(new JFrame(), "Please select a file to Review!");
        }
    }//GEN-LAST:event_openFileInProgramActionPerformed

    private void backToMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMainMenuActionPerformed
        // TODO add your handling code here:
        dispose();
        new MainView().setVisible(true); 
    }//GEN-LAST:event_backToMainMenuActionPerformed

    private void jobStatusActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jobStatusActionPerformed
    {//GEN-HEADEREND:event_jobStatusActionPerformed
        updateView((String) jobStatus.getSelectedItem(), allFileTableModel, UtilController.updatePendingTableData((String) jobStatus.getSelectedItem()));
    }//GEN-LAST:event_jobStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ApprovedButton;
    private javax.swing.JTable PendingTable;
    private javax.swing.JButton RejectButton;
    private javax.swing.JButton backToMainMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox jobStatus;
    private javax.swing.JButton openFileInProgram;
    private javax.swing.JMenuItem showClassEditorOptions;
    // End of variables declaration//GEN-END:variables
}
