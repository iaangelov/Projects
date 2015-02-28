/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.uni_sofia.fmi.oopjava.windows;

import bg.uni_sofia.fmi.oopjava.server.Server;
import bg.uni_sofia.fmi.oopjava.server.ServerRemote;
import bg.uni_sofia.fmi.oopjava.server.ServerServiceRegister;
import bg.uni_sofia.fmi.oopjava.utils.CardWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author John
 */
public class ServerWindow extends javax.swing.JFrame {

    private ServerRemote server;
    private final String SERVER_RUNNING_MESSAGE = "Server is running";
    private final String SERVER_NOT_RUNNING_MESSAGE = "Server is not running";
    private final String SERVICE_NAME = "Server";

    /**
     * Creates new form ServerWindow
     */
    public ServerWindow() {
        initComponents();
        setLocationRelativeTo(null);
        try {
            server = new Server();
            ServerServiceRegister.createRegistry(server, SERVICE_NAME);
        } catch (RemoteException ex) {
            ex.printStackTrace();
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

        btnStartServer = new javax.swing.JButton();
        btnStopServer = new javax.swing.JButton();
        lblServerState = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        comboBoxSortPreference = new javax.swing.JComboBox();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btnStartServer.setText("Start Server");
        btnStartServer.setEnabled(false);
        btnStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartServerActionPerformed(evt);
            }
        });

        btnStopServer.setText("Stop Server");
        btnStopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopServerActionPerformed(evt);
            }
        });

        lblServerState.setText("Server is running");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        comboBoxSortPreference.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sorted by number", "Sorted by encrypted number" }));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStartServer)
                        .addGap(45, 45, 45)
                        .addComponent(btnStopServer))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(lblServerState)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxSortPreference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblServerState)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnStartServer)
                            .addComponent(btnStopServer))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxSortPreference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave))
                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartServerActionPerformed
        try {
            ServerServiceRegister.registerServerObject(server, SERVICE_NAME);
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Couldn't register server object!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        btnStartServer.setEnabled(false);
        btnStopServer.setEnabled(true);
        lblServerState.setText(SERVER_RUNNING_MESSAGE);
    }//GEN-LAST:event_btnStartServerActionPerformed

    private void btnStopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopServerActionPerformed
        try {
            ServerServiceRegister.removeFromRegistry(server);
        } catch (NoSuchObjectException ex) {
            JOptionPane.showMessageDialog(null, "Couldn't stop server!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        btnStartServer.setEnabled(true);
        btnStopServer.setEnabled(false);
        lblServerState.setText(SERVER_NOT_RUNNING_MESSAGE);
    }//GEN-LAST:event_btnStopServerActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File fileName = fileChooser.getSelectedFile();
        try {
            switch(comboBoxSortPreference.getSelectedIndex()){
                case 0: CardWriter.writeCards(server.getNumbers(), fileName); break;
                case 1: CardWriter.writeCardsAlt(server.getNumbers(), fileName); break;
            }
            
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Couldn't save!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File not found", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(ServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnStartServer;
    private javax.swing.JButton btnStopServer;
    private javax.swing.JComboBox comboBoxSortPreference;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblServerState;
    // End of variables declaration//GEN-END:variables
    
}
