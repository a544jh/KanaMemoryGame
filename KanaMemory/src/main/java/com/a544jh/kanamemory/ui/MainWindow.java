/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.ui;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaSyllable;
import com.a544jh.kanamemory.gamelogic.MatchingGame;
import com.a544jh.kanamemory.profile.PlayerProfile;
import java.awt.CardLayout;
import java.util.EnumSet;
import javax.swing.JFrame;

/**
 *
 * @author axel
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KanaMemory");
        setPreferredSize(new java.awt.Dimension(500, 600));
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

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
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>


        /* Create and display the form */
        final JFrame mainWindow = new MainWindow();
        
        CardLayout cardLayout = (CardLayout) mainWindow.getContentPane().getLayout();
//        mainWindow.getContentPane().add(new MainMenuPanel(), "MainMenu");
//        cardLayout.show(mainWindow.getContentPane(), "MainMenu");
        
//        MatchingGame mGame = new MatchingGame(new PlayerProfile("test"), EnumSet.range(KanaSyllable.A, KanaSyllable.O), 
//                CharacterType.HIRAGANA, CharacterType.ROMAJI, 5);
//        MatchingGamePanel mgpanel = new MatchingGamePanel();
//        mgpanel.setGame(mGame);
//        mainWindow.getContentPane().add(mgpanel);
//        mgpanel.prepareRound();
        
        mainWindow.add(new ProfileChooserPanel());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainWindow.setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
