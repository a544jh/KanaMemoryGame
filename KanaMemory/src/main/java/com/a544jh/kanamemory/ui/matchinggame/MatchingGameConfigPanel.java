/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.ui.matchinggame;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaSyllable;
import com.a544jh.kanamemory.gamelogic.MatchingGame;
import com.a544jh.kanamemory.profile.PlayerProfile;
import com.a544jh.kanamemory.ui.KanaTablePanel;
import java.awt.CardLayout;
import java.util.EnumSet;

/**
 *
 * @author axel
 */
public class MatchingGameConfigPanel extends javax.swing.JPanel {

    PlayerProfile profile;
    KanaTablePanel.SelectionChangeListener scListener = new KanaTablePanel.SelectionChangeListener() {

        @Override
        public void selectionChanged() {
            if (kanaTablePanel1.getSelectedSyllables().size() < 5) {
                beginButton.setEnabled(false);
            } else {
                beginButton.setEnabled(true);
            }
        }
    };

    /**
     * Creates new form MatchingGameConfigPanel
     */
    public MatchingGameConfigPanel() {
        initComponents();
    }

    public void setProfile(PlayerProfile profile) {
        this.profile = profile;
    }

    public void refresh() {
        kanaTablePanel1.setCtype(CharacterType.HIRAGANA);
        kanaTablePanel1.refreshColors(profile);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        backButton = new javax.swing.JButton();
        kanaTablePanel1 = new com.a544jh.kanamemory.ui.KanaTablePanel();
        jLabel1 = new javax.swing.JLabel();
        hiraganaRadioButton = new javax.swing.JRadioButton();
        katakanaRadioButton = new javax.swing.JRadioButton();
        kanaRadioButton = new javax.swing.JRadioButton();
        beginButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(500, 600));

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        kanaTablePanel1.addSelectionChangeListener(scListener);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Match");

        buttonGroup1.add(hiraganaRadioButton);
        hiraganaRadioButton.setSelected(true);
        hiraganaRadioButton.setText("Hiragana - Rōmaji");
        hiraganaRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hiraganaRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(katakanaRadioButton);
        katakanaRadioButton.setText("Katakana - Rōmaji");
        katakanaRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                katakanaRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(kanaRadioButton);
        kanaRadioButton.setText("Hiragana - Katakana");

        beginButton.setText("Begin!");
        beginButton.setEnabled(false);
        beginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Select at least 5 syllables");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kanaTablePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(beginButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kanaRadioButton)
                    .addComponent(katakanaRadioButton)
                    .addComponent(hiraganaRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton)
                    .addComponent(jLabel1))
                .addGap(49, 49, 49)
                .addComponent(hiraganaRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(katakanaRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kanaRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kanaTablePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beginButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.previous(getParent());
        getParent().remove(this);
    }//GEN-LAST:event_backButtonActionPerformed

    private void hiraganaRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hiraganaRadioButtonActionPerformed
        // TODO add your handling code here:
        kanaTablePanel1.setCtype(CharacterType.HIRAGANA);
        kanaTablePanel1.refreshColors(profile);
    }//GEN-LAST:event_hiraganaRadioButtonActionPerformed

    private void beginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginButtonActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) getParent().getLayout();
        CharacterType ctype1, ctype2;
        if (hiraganaRadioButton.isSelected()) {
            ctype1 = CharacterType.HIRAGANA;
            ctype2 = CharacterType.ROMAJI;
        } else if (katakanaRadioButton.isSelected()) {
            ctype1 = CharacterType.KATAKANA;
            ctype2 = CharacterType.ROMAJI;
        } else {
            ctype1 = CharacterType.HIRAGANA;
            ctype2 = CharacterType.KATAKANA;
        }
        MatchingGame mgame = new MatchingGame(profile, kanaTablePanel1.getSelectedSyllables(), ctype1, ctype2, 5);
        MatchingGamePanel mgpanel = new MatchingGamePanel();
        mgpanel.setGame(mgame);
        mgpanel.startRound();
        getParent().add(mgpanel, "MatchingGame");
        cl.show(getParent(), "MatchingGame");
        getParent().remove(this);
    }//GEN-LAST:event_beginButtonActionPerformed

    private void katakanaRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_katakanaRadioButtonActionPerformed
        // TODO add your handling code here:
        kanaTablePanel1.setCtype(CharacterType.KATAKANA);
        kanaTablePanel1.refreshColors(profile);
    }//GEN-LAST:event_katakanaRadioButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton beginButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton hiraganaRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton kanaRadioButton;
    private com.a544jh.kanamemory.ui.KanaTablePanel kanaTablePanel1;
    private javax.swing.JRadioButton katakanaRadioButton;
    // End of variables declaration//GEN-END:variables
}
