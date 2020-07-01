/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gui;

import com.project.gui.mediator.GUIImageHelper;

import java.awt.*;


public class RMOLoginGUI extends javax.swing.JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ecoreLogo;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel loginImageLogo;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameTextField;


    public RMOLoginGUI() {
        initComponents();
        ecoreLogo.setIcon(GUIImageHelper.getScaledLogoImage(ecoreLogo.getWidth(), ecoreLogo.getHeight()));
        loginImageLogo.setIcon(GUIImageHelper.getScaledLoginImage(loginImageLogo.getWidth(), loginImageLogo.getHeight()));
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RMOLoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new RMOLoginGUI().setVisible(true));
    }

    private void initComponents() {
        javax.swing.JPanel topPanel = new javax.swing.JPanel();
        ecoreLogo = new javax.swing.JLabel();
        javax.swing.JPanel loginPanel = new javax.swing.JPanel();
        loginImageLogo = new javax.swing.JLabel();
        javax.swing.JLabel signinLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        javax.swing.JButton loginButton = new javax.swing.JButton();
        javax.swing.JLabel forgotPasswordLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recycling Machine Operator");
        setMinimumSize(new java.awt.Dimension(680, 540));
        //getContentPane().setLayout(new AbsoluteLayout());

        topPanel.setBackground(new java.awt.Color(1, 87, 155));
        topPanel.setMaximumSize(new java.awt.Dimension(680, 100));
        topPanel.setMinimumSize(new java.awt.Dimension(680, 100));
        topPanel.setPreferredSize(new java.awt.Dimension(680, 100));

        ecoreLogo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ecoreLogo.setMaximumSize(new java.awt.Dimension(400, 90));
        ecoreLogo.setMinimumSize(new java.awt.Dimension(400, 90));
        ecoreLogo.setPreferredSize(new java.awt.Dimension(400, 90));
        ecoreLogo.setRequestFocusEnabled(false);

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addComponent(ecoreLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
                topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ecoreLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(topPanel);

        loginPanel.setBackground(new java.awt.Color(255, 255, 255));
        loginPanel.setMaximumSize(new java.awt.Dimension(680, 440));
        loginPanel.setMinimumSize(new java.awt.Dimension(680, 440));
        loginPanel.setPreferredSize(new java.awt.Dimension(680, 440));

        loginImageLogo.setMaximumSize(new java.awt.Dimension(240, 240));
        loginImageLogo.setMinimumSize(new java.awt.Dimension(240, 240));
        loginImageLogo.setPreferredSize(new java.awt.Dimension(240, 240));

        signinLabel.setFont(new java.awt.Font("Lucida Grande", Font.PLAIN, 18)); // NOI18N
        signinLabel.setForeground(new java.awt.Color(1, 87, 155));
        signinLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        signinLabel.setText("Member Login");
        signinLabel.setMaximumSize(new java.awt.Dimension(200, 24));
        signinLabel.setMinimumSize(new java.awt.Dimension(200, 24));
        signinLabel.setPreferredSize(new java.awt.Dimension(200, 24));

        usernameTextField.setActionCommand("");
        usernameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", Font.PLAIN, 13), new java.awt.Color(1, 87, 155))); // NOI18N
        usernameTextField.setMaximumSize(new java.awt.Dimension(300, 54));
        usernameTextField.setMinimumSize(new java.awt.Dimension(300, 54));
        usernameTextField.setPreferredSize(new java.awt.Dimension(300, 54));
        usernameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameTextFieldMouseClicked(evt);
            }
        });

        passwordField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", Font.PLAIN, 13), new java.awt.Color(1, 87, 155))); // NOI18N
        passwordField.setMaximumSize(new java.awt.Dimension(300, 54));
        passwordField.setMinimumSize(new java.awt.Dimension(300, 54));
        passwordField.setPreferredSize(new java.awt.Dimension(300, 50));

        loginButton.setBackground(new java.awt.Color(0, 137, 123));
        loginButton.setForeground(new java.awt.Color(178, 223, 219));
        loginButton.setText("Login");
        loginButton.addActionListener(evt -> loginButtonActionPerformed(evt));

        forgotPasswordLabel.setFont(new java.awt.Font("Lucida Grande", Font.ITALIC, 13)); // NOI18N
        forgotPasswordLabel.setForeground(new java.awt.Color(1, 87, 155));
        forgotPasswordLabel.setText("Forgot username/ password ?");

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(loginImageLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(forgotPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addGap(53, 53, 53)
                                                .addComponent(signinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(48, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addComponent(signinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(16, 16, 16)
                                                .addComponent(errorLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(forgotPasswordLabel))
                                        .addComponent(loginImageLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(94, Short.MAX_VALUE))
        );

        getContentPane().add(loginPanel);

        pack();
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String username = usernameTextField.getText();
        char[] password = passwordField.getPassword();

        if (username.length() < 3 || password.length < 3) {
            errorLabel.setText("Please Enter Valid Username/Password!");
            errorLabel.setForeground(new java.awt.Color(255, 0, 0));
            usernameTextField.setText("");
            passwordField.setText("");
        } else if (username.equalsIgnoreCase("admin") && String.copyValueOf(password).equals("admin")) {
            new RMOSGUI().setVisible(true);
            this.setVisible(false);
        } else {
            errorLabel.setText("Please Enter Valid Username/Password!");
            errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        }
    }

    private void usernameTextFieldMouseClicked(java.awt.event.MouseEvent evt) {
        errorLabel.setText("");
    }
}
