/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.uni_sofia.fmi.oopjava.windows;

import bg.uni_sofia.fmi.oopjava.client.Client;
import bg.uni_sofia.fmi.oopjava.exceptions.InvalidCardNumberException;
import bg.uni_sofia.fmi.oopjava.exceptions.NoPermissionException;
import bg.uni_sofia.fmi.oopjava.exceptions.TooManyTriesException;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author John
 */
public class ClientWindow extends javax.swing.JFrame {

    Client client;

    /**
     * Creates new form ClientWindow
     * @param client
     */
    public ClientWindow(Client client) {
        initComponents();
        setLocationRelativeTo(null);
        this.client = client;
        initEncryptDecryptButtons();
        initAddRemoveButtons();
    }

    private void initEncryptDecryptButtons() {
        JButton[] buttons = encryptDecryptCardPanel1.getButtons();
        buttons[0].addActionListener(event -> {
            try {
                if (!validateCardNumber(encryptDecryptCardPanel1.getCardNumberText())) {
                    JOptionPane.showMessageDialog(this, "Invalid card number! (Must have 16 digits)", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                encryptDecryptCardPanel1.setEncryptedNumberText(client.encryptCard(encryptDecryptCardPanel1.getCardNumberText()));
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(this, "Connection to server error!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (TooManyTriesException | InvalidCardNumberException | NoPermissionException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        buttons[1].addActionListener(event -> {
            try {
                if (!validateEncryptedNumber(encryptDecryptCardPanel1.getEncryptedCardNumberText())) {
                    JOptionPane.showMessageDialog(this, "Invalid encrypted number! (Must have 16 digits)", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                encryptDecryptCardPanel1.setCardNumberText(client.decryptCard(encryptDecryptCardPanel1.getEncryptedCardNumberText()));
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(this, "Connection to server error!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NoPermissionException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void initAddRemoveButtons() {
        JButton[] buttons = addRemoveUserPanel1.getButtons();
        buttons[0].addActionListener(event -> {
            try {
                if (!validateUsername(addRemoveUserPanel1.getUsernameText()) || !validatePassword(addRemoveUserPanel1.getPasswordText())) {
                    JOptionPane.showMessageDialog(this, "Invalid username or password", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                client.addUser(addRemoveUserPanel1.getUsernameText(), addRemoveUserPanel1.getPasswordText(), addRemoveUserPanel1.getPermissions());
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(this, "Connection to server error!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NoPermissionException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttons[1].addActionListener(event -> {
            try {
                if (!validateUsername(addRemoveUserPanel1.getUsernameText()) || !validatePassword(addRemoveUserPanel1.getPasswordText())) {
                    JOptionPane.showMessageDialog(this, "Invalid username or password", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                client.removeUser(addRemoveUserPanel1.getUsernameText());
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(this, "Connection to server error!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NoPermissionException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    
    private boolean validateUsername(String text) {
        return text.matches("([a-zA-Z1-9]+[_]*)+");
    }

    private boolean validatePassword(String password) {
        return password.matches("([a-zA-Z1-9]+[_]*)+");
    }

    private boolean validateCardNumber(String number) {
        number = number.replaceAll(" ", "");
        return number.matches("\\d{16}");
    }
    
    private boolean validateEncryptedNumber(String number) {
        number = number.replaceAll(" ", "");
        return number.matches("\\d{16}");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        encryptDecryptCardPanel1 = new bg.uni_sofia.fmi.oopjava.windows.EncryptDecryptCardPanel();
        addRemoveUserPanel1 = new bg.uni_sofia.fmi.oopjava.windows.AddRemoveUserPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(encryptDecryptCardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addRemoveUserPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addRemoveUserPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(encryptDecryptCardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private bg.uni_sofia.fmi.oopjava.windows.AddRemoveUserPanel addRemoveUserPanel1;
    private bg.uni_sofia.fmi.oopjava.windows.EncryptDecryptCardPanel encryptDecryptCardPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
