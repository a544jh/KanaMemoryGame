/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.ui;

import com.a544jh.kanamemory.ui.matchinggame.MatchingGamePanel;
import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaSyllable;
import com.a544jh.kanamemory.gamelogic.MatchingGame;
import com.a544jh.kanamemory.io.JsonFileWriter;
import com.a544jh.kanamemory.profile.PlayerProfile;
import com.a544jh.kanamemory.ui.matchinggame.MatchingGameConfigPanel;
import com.a544jh.kanamemory.ui.typinggame.TypingGameConfigPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.EnumSet;

/**
 *
 * @author axel
 */
public class MainMenuPanel extends javax.swing.JPanel {
    
    private PlayerProfile profile;

    /**
     * Creates new form MainMenuPanel
     */
    public MainMenuPanel() {
        initComponents();
    }
    
    public void setProfile(PlayerProfile profile) {
        this.profile = profile;
    }
    
    public void refresh() {
        currentProfileLabel.setText("こんにちは、" + profile.getName() + "!");
        jProgressBar1.setMaximum(profile.MAX_TOTAL_SCORE);
        jProgressBar1.setValue(profile.getTotalScoreSum());
        totalProgressLabel.setText(String.format("%.2f", profile.getCompletionPercentage()) + "% "
                + profile.getTotalScoreSum() + "/" + profile.MAX_TOTAL_SCORE);
        hiraganaPanel.refreshColors(profile);
        katakanaPanel.refreshColors(profile);
    }
    
    public void switchToConfigPanel(GameConfigPanel gcp) {
        CardLayout cl = (CardLayout) getParent().getLayout();
        gcp.setProfile(profile);
        gcp.refresh();
        getParent().add((Component) gcp, gcp.getComponentName());
        cl.show(getParent(), gcp.getComponentName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kanaMemoryLabel = new javax.swing.JLabel();
        changeProfileButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        matchingGameButton = new javax.swing.JButton();
        typingGameButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        hiraganaPanel = new com.a544jh.kanamemory.ui.KanaTablePanel();
        katakanaPanel = new com.a544jh.kanamemory.ui.KanaTablePanel();
        currentProfileLabel = new javax.swing.JLabel();
        totalProgressLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(500, 600));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        kanaMemoryLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        kanaMemoryLabel.setText("KanaMemory");

        changeProfileButton.setText("Change Profile");
        changeProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeProfileButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        matchingGameButton.setText("Match");
        matchingGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchingGameButtonActionPerformed(evt);
            }
        });

        typingGameButton.setText("Type");
        typingGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typingGameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(matchingGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(typingGameButton)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {matchingGameButton, typingGameButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matchingGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typingGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {matchingGameButton, typingGameButton});

        jScrollPane1.setViewportView(jPanel1);

        jLabel1.setText("Total progress:");

        hiraganaPanel.setCtype(CharacterType.HIRAGANA);
        jTabbedPane1.addTab("Hiragana", hiraganaPanel);

        katakanaPanel.setCtype(CharacterType.KATAKANA);
        jTabbedPane1.addTab("Katakana", katakanaPanel);

        currentProfileLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        currentProfileLabel.setText("こんにちは、----- !");
        currentProfileLabel.setMaximumSize(new java.awt.Dimension(143, 17));

        totalProgressLabel.setText("146/146");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel2.setForeground(javax.swing.UIManager.getDefaults().getColor("Label.disabledForeground"));
        jLabel2.setText("v1.0 ♡2014 a544jh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalProgressLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(kanaMemoryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentProfileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeProfileButton))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kanaMemoryLabel)
                    .addComponent(changeProfileButton)
                    .addComponent(currentProfileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalProgressLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void changeProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeProfileButtonActionPerformed
        CardLayout cl = (CardLayout) getParent().getLayout();
        getParent().add(new ProfileChooserPanel(), "ProfileChooser");
        cl.show(getParent(), "ProfileChooser");
        getParent().remove(this);
    }//GEN-LAST:event_changeProfileButtonActionPerformed

    private void matchingGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchingGameButtonActionPerformed
        switchToConfigPanel(new MatchingGameConfigPanel());
    }//GEN-LAST:event_matchingGameButtonActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        JsonFileWriter.saveProfile(profile, "profiles");
        refresh();
    }//GEN-LAST:event_formComponentShown

    private void typingGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typingGameButtonActionPerformed
        switchToConfigPanel(new TypingGameConfigPanel());
    }//GEN-LAST:event_typingGameButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeProfileButton;
    private javax.swing.JLabel currentProfileLabel;
    private com.a544jh.kanamemory.ui.KanaTablePanel hiraganaPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel kanaMemoryLabel;
    private com.a544jh.kanamemory.ui.KanaTablePanel katakanaPanel;
    private javax.swing.JButton matchingGameButton;
    private javax.swing.JLabel totalProgressLabel;
    private javax.swing.JButton typingGameButton;
    // End of variables declaration//GEN-END:variables
}
