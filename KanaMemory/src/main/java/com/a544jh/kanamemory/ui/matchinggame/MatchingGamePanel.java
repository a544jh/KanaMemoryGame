/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.ui.matchinggame;

import com.a544jh.kanamemory.characters.KanaCharacter;
import com.a544jh.kanamemory.gamelogic.MatchingGame;
import com.a544jh.kanamemory.ui.GamePanel;
import com.a544jh.kanamemory.ui.KanaCharacterButton;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author axel
 */
public class MatchingGamePanel extends javax.swing.JPanel implements GamePanel{

    private MatchingGame game;
    private List<KanaCharacterButton> kcbuttons1, kcbuttons2;
    private final String COMPONENT_NAME;
    /**
     * Creates new form MatchingGamePanel
     */
    public MatchingGamePanel() {
        COMPONENT_NAME = "MatchingGame";
        initComponents();
        kcbuttons1 = new ArrayList<>();
        kcbuttons2 = new ArrayList<>();
        makeButtonsLists();
    }

    private void makeButtonsLists() {
        KanaButtonListener listener = new KanaButtonListener();
        for (Component component : jPanel1.getComponents()) {
            if (component instanceof KanaCharacterButton) {
                KanaCharacterButton button = (KanaCharacterButton) component;
                button.addActionListener(listener);
                kcbuttons1.add(button);
            }
        }
        for (Component component : jPanel2.getComponents()) {
            if (component instanceof KanaCharacterButton) {
                KanaCharacterButton button = (KanaCharacterButton) component;
                button.addActionListener(listener);
                kcbuttons2.add(button);
            }
        }
    }

    public void setGame(MatchingGame game) {
        this.game = game;
    }

    public void startRound() {
        ArrayList[] charLists = game.getCharacters();
        
        ArrayList<KanaCharacter> chars1 = (ArrayList<KanaCharacter>) charLists[0];
        ArrayList<KanaCharacter> chars2 = (ArrayList<KanaCharacter>) charLists[1];
        
        for (int i = 0; i < game.getCharsPerRound(); i++) {
            kcbuttons1.get(i).setCharacter(chars1.get(i));
            kcbuttons1.get(i).setEnabled(true);
            kcbuttons1.get(i).setVisible(true);
        }
        
        for (int i = 0; i < game.getCharsPerRound(); i++) {
            kcbuttons2.get(i).setCharacter(chars2.get(i));
            kcbuttons2.get(i).setEnabled(true);
            kcbuttons2.get(i).setVisible(true);
        }
    }
    
    class KanaButtonListener implements ActionListener{
        KanaCharacterButton kcb1=null, kcb2=null;
        int matchedCount = 0;
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(kcb1==null){
                //First button gets selected
                kcb1 = (KanaCharacterButton)ae.getSource();
                kcb1.setEnabled(false);
            } else if (kcb2==null) {
                //Second button gets selected
                kcb2 = (KanaCharacterButton)ae.getSource();;
                if(game.matchCharacters(kcb1.getCharacter(), kcb2.getCharacter())){
                    //Match, hide both buttons
                    kcb1.setVisible(false);
                    kcb2.setVisible(false);
                    matchedCount++;
                } else {
                    //Mismatch, enable the first button again
                    kcb1.setEnabled(true);
                }
                //Reset the button pointers
                kcb1 = null;
                kcb2 = null;
                
                if (matchedCount == game.getCharsPerRound()){
                    matchedCount = 0;
                    startRound();
                }
            }
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

        jPanel1 = new javax.swing.JPanel();
        kanaCharacterButton2 = new com.a544jh.kanamemory.ui.KanaCharacterButton();
        kanaCharacterButton3 = new com.a544jh.kanamemory.ui.KanaCharacterButton();
        kanaCharacterButton4 = new com.a544jh.kanamemory.ui.KanaCharacterButton();
        kanaCharacterButton5 = new com.a544jh.kanamemory.ui.KanaCharacterButton();
        kanaCharacterButton6 = new com.a544jh.kanamemory.ui.KanaCharacterButton();
        jPanel2 = new javax.swing.JPanel();
        kanaCharacterButton7 = new com.a544jh.kanamemory.ui.KanaCharacterButton();
        kanaCharacterButton8 = new com.a544jh.kanamemory.ui.KanaCharacterButton();
        kanaCharacterButton9 = new com.a544jh.kanamemory.ui.KanaCharacterButton();
        kanaCharacterButton10 = new com.a544jh.kanamemory.ui.KanaCharacterButton();
        kanaCharacterButton11 = new com.a544jh.kanamemory.ui.KanaCharacterButton();
        backButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(500, 600));

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        kanaCharacterButton2.setText("あ");
        kanaCharacterButton2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jPanel1.add(kanaCharacterButton2);

        kanaCharacterButton3.setText("あ");
        kanaCharacterButton3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jPanel1.add(kanaCharacterButton3);

        kanaCharacterButton4.setText("あ");
        kanaCharacterButton4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jPanel1.add(kanaCharacterButton4);

        kanaCharacterButton5.setText("あ");
        kanaCharacterButton5.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jPanel1.add(kanaCharacterButton5);

        kanaCharacterButton6.setText("あ");
        kanaCharacterButton6.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jPanel1.add(kanaCharacterButton6);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        kanaCharacterButton7.setText("ka");
        kanaCharacterButton7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jPanel2.add(kanaCharacterButton7);

        kanaCharacterButton8.setText("ka");
        kanaCharacterButton8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jPanel2.add(kanaCharacterButton8);

        kanaCharacterButton9.setText("ka");
        kanaCharacterButton9.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jPanel2.add(kanaCharacterButton9);

        kanaCharacterButton10.setText("ka");
        kanaCharacterButton10.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jPanel2.add(kanaCharacterButton10);

        kanaCharacterButton11.setText("ka");
        kanaCharacterButton11.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jPanel2.add(kanaCharacterButton11);

        backButton.setText("Main Menu");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addGap(112, 112, 112)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(316, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.previous(getParent());
        getParent().remove(this);
    }//GEN-LAST:event_backButtonActionPerformed

    public String getComponentName() {
        return COMPONENT_NAME;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.a544jh.kanamemory.ui.KanaCharacterButton kanaCharacterButton10;
    private com.a544jh.kanamemory.ui.KanaCharacterButton kanaCharacterButton11;
    private com.a544jh.kanamemory.ui.KanaCharacterButton kanaCharacterButton2;
    private com.a544jh.kanamemory.ui.KanaCharacterButton kanaCharacterButton3;
    private com.a544jh.kanamemory.ui.KanaCharacterButton kanaCharacterButton4;
    private com.a544jh.kanamemory.ui.KanaCharacterButton kanaCharacterButton5;
    private com.a544jh.kanamemory.ui.KanaCharacterButton kanaCharacterButton6;
    private com.a544jh.kanamemory.ui.KanaCharacterButton kanaCharacterButton7;
    private com.a544jh.kanamemory.ui.KanaCharacterButton kanaCharacterButton8;
    private com.a544jh.kanamemory.ui.KanaCharacterButton kanaCharacterButton9;
    // End of variables declaration//GEN-END:variables
}
