/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.ui;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaSyllable;
import com.a544jh.kanamemory.profile.PlayerProfile;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author axel
 */
public class KanaTablePanel extends javax.swing.JPanel {

    private CharacterType ctype;
    private List<JPanel> ktbpanels;
    private List<SelectionChangeListener> scListeners;

    /**
     * Creates new form KanaTablePanel
     */
    public KanaTablePanel() {
        initComponents();
        ktbpanels = new ArrayList<>();
        makePanelsList();
        mapSyllablesToButtons();
        scListeners = new ArrayList<>();
    }

    private void makePanelsList() {
        ktbpanels.add(vocPanel);
        ktbpanels.add(kPanel);
        ktbpanels.add(sPanel);
        ktbpanels.add(tPanel);
        ktbpanels.add(nPanel);
        ktbpanels.add(hPanel);
        ktbpanels.add(mPanel);
        ktbpanels.add(yPanel);
        ktbpanels.add(rPanel);
        ktbpanels.add(wPanel);
        ktbpanels.add(nnPanel);
        ktbpanels.add(gPanel);
        ktbpanels.add(zPanel);
        ktbpanels.add(dPanel);
        ktbpanels.add(bPanel);
        ktbpanels.add(pPanel);
    }

    public void setCtype(CharacterType ctype) {
        this.ctype = ctype;
        for (JPanel jPanel : ktbpanels) {
            for (Component c : jPanel.getComponents()) {
                if (c instanceof KanaTableToggleButton) {
                    KanaTableToggleButton ktb = (KanaTableToggleButton) c;
                    ktb.setCtype(ctype);
                    ktb.refreshText();
                }
            }
        }
    }

    private void mapSyllablesToButtons() {
        initPanel(vocPanel, EnumSet.range(KanaSyllable.A, KanaSyllable.O));
        initPanel(kPanel, EnumSet.range(KanaSyllable.KA, KanaSyllable.KO));
        initPanel(sPanel, EnumSet.range(KanaSyllable.SA, KanaSyllable.SO));
        initPanel(tPanel, EnumSet.range(KanaSyllable.TA, KanaSyllable.TO));
        initPanel(nPanel, EnumSet.range(KanaSyllable.NA, KanaSyllable.NO));
        initPanel(hPanel, EnumSet.range(KanaSyllable.HA, KanaSyllable.HO));
        initPanel(mPanel, EnumSet.range(KanaSyllable.MA, KanaSyllable.MO));
        initPanel(yPanel, EnumSet.range(KanaSyllable.YA, KanaSyllable.YO));
        initPanel(rPanel, EnumSet.range(KanaSyllable.RA, KanaSyllable.RO));
        initPanel(wPanel, EnumSet.range(KanaSyllable.WA, KanaSyllable.WO));
        initPanel(nnPanel, EnumSet.of(KanaSyllable.N));
        initPanel(gPanel, EnumSet.range(KanaSyllable.GA, KanaSyllable.GO));
        initPanel(zPanel, EnumSet.range(KanaSyllable.ZA, KanaSyllable.ZO));
        initPanel(dPanel, EnumSet.range(KanaSyllable.DA, KanaSyllable.DO));
        initPanel(bPanel, EnumSet.range(KanaSyllable.BA, KanaSyllable.BO));
        initPanel(pPanel, EnumSet.range(KanaSyllable.PA, KanaSyllable.PO));
    }

    private void initPanel(JPanel panel, EnumSet<KanaSyllable> sset) {
        ChangeListener notifier = new SelectionChangeNotifier();
        Iterator<KanaSyllable> it = sset.iterator();
        for (Component c : panel.getComponents()) {
            if (c instanceof JLabel) {
                c.addMouseListener(new LabelListener());
            }
            if (c instanceof KanaTableToggleButton) {
                KanaTableToggleButton ktb = (KanaTableToggleButton) c;
                ktb.setSyllable(it.next());
                ktb.addChangeListener(notifier);
            }
        }
    }

    public void refreshColors(PlayerProfile profile) {
        for (JPanel panel : ktbpanels) {
            for (Component c : panel.getComponents()) {
                if (c instanceof KanaTableToggleButton) {
                    KanaTableToggleButton ktb = (KanaTableToggleButton) c;
                    ktb.refreshColor(profile);
                }
            }
        }
    }

    public void selectColumn(JPanel panel) {
        boolean whole = wholeColumnSelected(panel);
        for (Component c : panel.getComponents()) {
            if (c instanceof KanaTableToggleButton) {
                KanaTableToggleButton ktb = (KanaTableToggleButton) c;
                if (whole) {
                    ktb.setSelected(false);
                } else {
                    ktb.setSelected(true);
                }
            }
        }
    }

    public boolean wholeColumnSelected(JPanel panel) {
        for (Component c : panel.getComponents()) {
            if (c instanceof KanaTableToggleButton) {
                KanaTableToggleButton ktb = (KanaTableToggleButton) c;
                if (!ktb.isSelected()) {
                    return false;
                }
            }
        }
        return true;
    }

    public EnumSet<KanaSyllable> getSelectedSyllables() {
        EnumSet<KanaSyllable> selected = EnumSet.noneOf(KanaSyllable.class);

        for (JPanel panel : ktbpanels) {
            for (Component c : panel.getComponents()) {
                if (c instanceof KanaTableToggleButton) {
                    KanaTableToggleButton ktb = (KanaTableToggleButton) c;
                    if (ktb.isSelected()) {
                        selected.add(ktb.getSyllable());
                    }
                }
            }
        }
        return selected;
    }

    class LabelListener implements MouseListener {

        String labeltext;
        Border labelborder;

        @Override
        public void mouseClicked(MouseEvent me) {
            JLabel label = (JLabel) me.getSource();
            selectColumn((JPanel) label.getParent());
        }

        @Override
        public void mousePressed(MouseEvent me) {

        }

        @Override
        public void mouseReleased(MouseEvent me) {

        }

        @Override
        public void mouseEntered(MouseEvent me) {
            JLabel label = (JLabel) me.getSource();
            labelborder = label.getBorder();
            label.setBorder(new EtchedBorder());
            labeltext = label.getText();
            label.setText("▼");
        }

        @Override
        public void mouseExited(MouseEvent me) {
            JLabel label = (JLabel) me.getSource();
            label.setBorder(labelborder);
            label.setText(labeltext);
        }

    }
    
    class SelectionChangeNotifier implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ce) {
            for (SelectionChangeListener l : scListeners){
                l.selectionChanged();
            }
        }
        
    }
    
    public interface SelectionChangeListener {
        public void selectionChanged();
    }
    
    public void addSelectionChangeListener(SelectionChangeListener l){
        scListeners.add(l);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        nnPanel = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        kttb56 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        wPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        kttb54 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb55 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        kttb57 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb58 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        rPanel = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        kttb47 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb49 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb51 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb52 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb53 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        yPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        kttb46 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        kttb48 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        kttb50 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        mPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        kttb41 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb42 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb43 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb44 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb45 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        hPanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        kttb36 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb37 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb38 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb39 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb40 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        nPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        kttb31 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb32 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb33 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb34 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb35 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        tPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        kttb26 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb27 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb28 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb29 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb30 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        sPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        kttb21 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb22 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb23 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb24 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb25 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        kttb6 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb7 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb8 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb9 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb10 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        vocPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        kttb1 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb2 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb3 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb4 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb5 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        labelsPanel = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        pPanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        kttb74 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb75 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb76 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb77 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb78 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        bPanel = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        kttb69 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb70 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb71 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb72 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb73 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        dPanel = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        kttb64 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb65 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb66 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb67 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb68 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        zPanel = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        kttb59 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb60 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb61 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb62 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb63 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        gPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        kttb11 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb12 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb13 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb14 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        kttb15 = new com.a544jh.kanamemory.ui.KanaTableToggleButton();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        labelsPanel1 = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setLayout(new java.awt.GridLayout(2, 0));

        jPanel13.setLayout(new java.awt.GridLayout(1, 0, 1, 0));

        nnPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel23.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("n");
        nnPanel.add(jLabel23);

        kttb56.setBackground(new java.awt.Color(255, 255, 255));
        kttb56.setText("あ");
        kttb56.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb56.setMargin(new java.awt.Insets(2, 2, 2, 2));
        nnPanel.add(kttb56);
        nnPanel.add(filler6);
        nnPanel.add(filler7);
        nnPanel.add(filler8);
        nnPanel.add(filler9);

        jPanel13.add(nnPanel);

        wPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel22.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("w-");
        wPanel.add(jLabel22);

        kttb54.setBackground(new java.awt.Color(255, 255, 255));
        kttb54.setText("あ");
        kttb54.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb54.setMargin(new java.awt.Insets(2, 2, 2, 2));
        wPanel.add(kttb54);

        kttb55.setBackground(new java.awt.Color(255, 255, 255));
        kttb55.setText("あ");
        kttb55.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb55.setMargin(new java.awt.Insets(2, 2, 2, 2));
        wPanel.add(kttb55);
        wPanel.add(filler5);

        kttb57.setBackground(new java.awt.Color(255, 255, 255));
        kttb57.setText("あ");
        kttb57.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb57.setMargin(new java.awt.Insets(2, 2, 2, 2));
        wPanel.add(kttb57);

        kttb58.setBackground(new java.awt.Color(255, 255, 255));
        kttb58.setText("あ");
        kttb58.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb58.setMargin(new java.awt.Insets(2, 2, 2, 2));
        wPanel.add(kttb58);

        jPanel13.add(wPanel);

        rPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel21.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("r-");
        rPanel.add(jLabel21);

        kttb47.setBackground(new java.awt.Color(255, 255, 255));
        kttb47.setText("あ");
        kttb47.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb47.setMargin(new java.awt.Insets(2, 2, 2, 2));
        rPanel.add(kttb47);

        kttb49.setBackground(new java.awt.Color(255, 255, 255));
        kttb49.setText("あ");
        kttb49.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb49.setMargin(new java.awt.Insets(2, 2, 2, 2));
        rPanel.add(kttb49);

        kttb51.setBackground(new java.awt.Color(255, 255, 255));
        kttb51.setText("あ");
        kttb51.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb51.setMargin(new java.awt.Insets(2, 2, 2, 2));
        rPanel.add(kttb51);

        kttb52.setBackground(new java.awt.Color(255, 255, 255));
        kttb52.setText("あ");
        kttb52.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb52.setMargin(new java.awt.Insets(2, 2, 2, 2));
        rPanel.add(kttb52);

        kttb53.setBackground(new java.awt.Color(255, 255, 255));
        kttb53.setText("あ");
        kttb53.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb53.setMargin(new java.awt.Insets(2, 2, 2, 2));
        rPanel.add(kttb53);

        jPanel13.add(rPanel);

        yPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel20.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("y-");
        yPanel.add(jLabel20);

        kttb46.setBackground(new java.awt.Color(255, 255, 255));
        kttb46.setText("あ");
        kttb46.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb46.setMargin(new java.awt.Insets(2, 2, 2, 2));
        yPanel.add(kttb46);
        yPanel.add(filler3);

        kttb48.setBackground(new java.awt.Color(255, 255, 255));
        kttb48.setText("あ");
        kttb48.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb48.setMargin(new java.awt.Insets(2, 2, 2, 2));
        yPanel.add(kttb48);
        yPanel.add(filler4);

        kttb50.setBackground(new java.awt.Color(255, 255, 255));
        kttb50.setText("あ");
        kttb50.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb50.setMargin(new java.awt.Insets(2, 2, 2, 2));
        yPanel.add(kttb50);

        jPanel13.add(yPanel);

        mPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel19.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("m-");
        mPanel.add(jLabel19);

        kttb41.setBackground(new java.awt.Color(255, 255, 255));
        kttb41.setText("あ");
        kttb41.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb41.setMargin(new java.awt.Insets(2, 2, 2, 2));
        mPanel.add(kttb41);

        kttb42.setBackground(new java.awt.Color(255, 255, 255));
        kttb42.setText("あ");
        kttb42.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb42.setMargin(new java.awt.Insets(2, 2, 2, 2));
        mPanel.add(kttb42);

        kttb43.setBackground(new java.awt.Color(255, 255, 255));
        kttb43.setText("あ");
        kttb43.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb43.setMargin(new java.awt.Insets(2, 2, 2, 2));
        mPanel.add(kttb43);

        kttb44.setBackground(new java.awt.Color(255, 255, 255));
        kttb44.setText("あ");
        kttb44.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb44.setMargin(new java.awt.Insets(2, 2, 2, 2));
        mPanel.add(kttb44);

        kttb45.setBackground(new java.awt.Color(255, 255, 255));
        kttb45.setText("あ");
        kttb45.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb45.setMargin(new java.awt.Insets(2, 2, 2, 2));
        mPanel.add(kttb45);

        jPanel13.add(mPanel);

        hPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel18.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("h-");
        hPanel.add(jLabel18);

        kttb36.setBackground(new java.awt.Color(255, 255, 255));
        kttb36.setText("あ");
        kttb36.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb36.setMargin(new java.awt.Insets(2, 2, 2, 2));
        hPanel.add(kttb36);

        kttb37.setBackground(new java.awt.Color(255, 255, 255));
        kttb37.setText("あ");
        kttb37.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb37.setMargin(new java.awt.Insets(2, 2, 2, 2));
        hPanel.add(kttb37);

        kttb38.setBackground(new java.awt.Color(255, 255, 255));
        kttb38.setText("あ");
        kttb38.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb38.setMargin(new java.awt.Insets(2, 2, 2, 2));
        hPanel.add(kttb38);

        kttb39.setBackground(new java.awt.Color(255, 255, 255));
        kttb39.setText("あ");
        kttb39.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb39.setMargin(new java.awt.Insets(2, 2, 2, 2));
        hPanel.add(kttb39);

        kttb40.setBackground(new java.awt.Color(255, 255, 255));
        kttb40.setText("あ");
        kttb40.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb40.setMargin(new java.awt.Insets(2, 2, 2, 2));
        hPanel.add(kttb40);

        jPanel13.add(hPanel);

        nPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel17.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("n-");
        nPanel.add(jLabel17);

        kttb31.setBackground(new java.awt.Color(255, 255, 255));
        kttb31.setText("あ");
        kttb31.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb31.setMargin(new java.awt.Insets(2, 2, 2, 2));
        nPanel.add(kttb31);

        kttb32.setBackground(new java.awt.Color(255, 255, 255));
        kttb32.setText("あ");
        kttb32.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb32.setMargin(new java.awt.Insets(2, 2, 2, 2));
        nPanel.add(kttb32);

        kttb33.setBackground(new java.awt.Color(255, 255, 255));
        kttb33.setText("あ");
        kttb33.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb33.setMargin(new java.awt.Insets(2, 2, 2, 2));
        nPanel.add(kttb33);

        kttb34.setBackground(new java.awt.Color(255, 255, 255));
        kttb34.setText("あ");
        kttb34.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb34.setMargin(new java.awt.Insets(2, 2, 2, 2));
        nPanel.add(kttb34);

        kttb35.setBackground(new java.awt.Color(255, 255, 255));
        kttb35.setText("あ");
        kttb35.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb35.setMargin(new java.awt.Insets(2, 2, 2, 2));
        nPanel.add(kttb35);

        jPanel13.add(nPanel);

        tPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel16.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("t-");
        tPanel.add(jLabel16);

        kttb26.setBackground(new java.awt.Color(255, 255, 255));
        kttb26.setText("あ");
        kttb26.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb26.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tPanel.add(kttb26);

        kttb27.setBackground(new java.awt.Color(255, 255, 255));
        kttb27.setText("あ");
        kttb27.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb27.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tPanel.add(kttb27);

        kttb28.setBackground(new java.awt.Color(255, 255, 255));
        kttb28.setText("あ");
        kttb28.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb28.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tPanel.add(kttb28);

        kttb29.setBackground(new java.awt.Color(255, 255, 255));
        kttb29.setText("あ");
        kttb29.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb29.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tPanel.add(kttb29);

        kttb30.setBackground(new java.awt.Color(255, 255, 255));
        kttb30.setText("あ");
        kttb30.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb30.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tPanel.add(kttb30);

        jPanel13.add(tPanel);

        sPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel15.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("s-");
        sPanel.add(jLabel15);

        kttb21.setBackground(new java.awt.Color(255, 255, 255));
        kttb21.setText("あ");
        kttb21.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb21.setMargin(new java.awt.Insets(2, 2, 2, 2));
        sPanel.add(kttb21);

        kttb22.setBackground(new java.awt.Color(255, 255, 255));
        kttb22.setText("あ");
        kttb22.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb22.setMargin(new java.awt.Insets(2, 2, 2, 2));
        sPanel.add(kttb22);

        kttb23.setBackground(new java.awt.Color(255, 255, 255));
        kttb23.setText("あ");
        kttb23.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb23.setMargin(new java.awt.Insets(2, 2, 2, 2));
        sPanel.add(kttb23);

        kttb24.setBackground(new java.awt.Color(255, 255, 255));
        kttb24.setText("あ");
        kttb24.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb24.setMargin(new java.awt.Insets(2, 2, 2, 2));
        sPanel.add(kttb24);

        kttb25.setBackground(new java.awt.Color(255, 255, 255));
        kttb25.setText("あ");
        kttb25.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb25.setMargin(new java.awt.Insets(2, 2, 2, 2));
        sPanel.add(kttb25);

        jPanel13.add(sPanel);

        kPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel8.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("k-");
        kPanel.add(jLabel8);

        kttb6.setBackground(new java.awt.Color(255, 255, 255));
        kttb6.setText("あ");
        kttb6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb6.setMargin(new java.awt.Insets(2, 2, 2, 2));
        kPanel.add(kttb6);

        kttb7.setBackground(new java.awt.Color(255, 255, 255));
        kttb7.setText("あ");
        kttb7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        kPanel.add(kttb7);

        kttb8.setBackground(new java.awt.Color(255, 255, 255));
        kttb8.setText("あ");
        kttb8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb8.setMargin(new java.awt.Insets(2, 2, 2, 2));
        kPanel.add(kttb8);

        kttb9.setBackground(new java.awt.Color(255, 255, 255));
        kttb9.setText("あ");
        kttb9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb9.setMargin(new java.awt.Insets(2, 2, 2, 2));
        kPanel.add(kttb9);

        kttb10.setBackground(new java.awt.Color(255, 255, 255));
        kttb10.setText("あ");
        kttb10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb10.setMargin(new java.awt.Insets(2, 2, 2, 2));
        kPanel.add(kttb10);

        jPanel13.add(kPanel);

        vocPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel7.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("-");
        vocPanel.add(jLabel7);

        kttb1.setBackground(new java.awt.Color(255, 255, 255));
        kttb1.setText("あ");
        kttb1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        vocPanel.add(kttb1);

        kttb2.setBackground(new java.awt.Color(255, 255, 255));
        kttb2.setText("あ");
        kttb2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        vocPanel.add(kttb2);

        kttb3.setBackground(new java.awt.Color(255, 255, 255));
        kttb3.setText("あ");
        kttb3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        vocPanel.add(kttb3);

        kttb4.setBackground(new java.awt.Color(255, 255, 255));
        kttb4.setText("あ");
        kttb4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        vocPanel.add(kttb4);

        kttb5.setBackground(new java.awt.Color(255, 255, 255));
        kttb5.setText("あ");
        kttb5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        vocPanel.add(kttb5);

        jPanel13.add(vocPanel);

        labelsPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));
        labelsPanel.add(filler1);

        jLabel1.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("a");
        labelsPanel.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("i");
        labelsPanel.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("u");
        labelsPanel.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("e");
        labelsPanel.add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("o");
        labelsPanel.add(jLabel5);

        jPanel13.add(labelsPanel);

        add(jPanel13);

        jPanel14.setLayout(new java.awt.GridLayout(1, 0, 1, 0));
        jPanel14.add(filler12);
        jPanel14.add(filler13);
        jPanel14.add(filler14);
        jPanel14.add(filler15);

        pPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel27.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("p-");
        pPanel.add(jLabel27);

        kttb74.setBackground(new java.awt.Color(255, 255, 255));
        kttb74.setText("あ");
        kttb74.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb74.setMargin(new java.awt.Insets(2, 2, 2, 2));
        pPanel.add(kttb74);

        kttb75.setBackground(new java.awt.Color(255, 255, 255));
        kttb75.setText("あ");
        kttb75.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb75.setMargin(new java.awt.Insets(2, 2, 2, 2));
        pPanel.add(kttb75);

        kttb76.setBackground(new java.awt.Color(255, 255, 255));
        kttb76.setText("あ");
        kttb76.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb76.setMargin(new java.awt.Insets(2, 2, 2, 2));
        pPanel.add(kttb76);

        kttb77.setBackground(new java.awt.Color(255, 255, 255));
        kttb77.setText("あ");
        kttb77.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb77.setMargin(new java.awt.Insets(2, 2, 2, 2));
        pPanel.add(kttb77);

        kttb78.setBackground(new java.awt.Color(255, 255, 255));
        kttb78.setText("あ");
        kttb78.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb78.setMargin(new java.awt.Insets(2, 2, 2, 2));
        pPanel.add(kttb78);

        jPanel14.add(pPanel);

        bPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel26.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("b-");
        bPanel.add(jLabel26);

        kttb69.setBackground(new java.awt.Color(255, 255, 255));
        kttb69.setText("あ");
        kttb69.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb69.setMargin(new java.awt.Insets(2, 2, 2, 2));
        bPanel.add(kttb69);

        kttb70.setBackground(new java.awt.Color(255, 255, 255));
        kttb70.setText("あ");
        kttb70.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb70.setMargin(new java.awt.Insets(2, 2, 2, 2));
        bPanel.add(kttb70);

        kttb71.setBackground(new java.awt.Color(255, 255, 255));
        kttb71.setText("あ");
        kttb71.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb71.setMargin(new java.awt.Insets(2, 2, 2, 2));
        bPanel.add(kttb71);

        kttb72.setBackground(new java.awt.Color(255, 255, 255));
        kttb72.setText("あ");
        kttb72.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb72.setMargin(new java.awt.Insets(2, 2, 2, 2));
        bPanel.add(kttb72);

        kttb73.setBackground(new java.awt.Color(255, 255, 255));
        kttb73.setText("あ");
        kttb73.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb73.setMargin(new java.awt.Insets(2, 2, 2, 2));
        bPanel.add(kttb73);

        jPanel14.add(bPanel);
        jPanel14.add(filler10);

        dPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel25.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("d-");
        dPanel.add(jLabel25);

        kttb64.setBackground(new java.awt.Color(255, 255, 255));
        kttb64.setText("あ");
        kttb64.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb64.setMargin(new java.awt.Insets(2, 2, 2, 2));
        dPanel.add(kttb64);

        kttb65.setBackground(new java.awt.Color(255, 255, 255));
        kttb65.setText("あ");
        kttb65.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb65.setMargin(new java.awt.Insets(2, 2, 2, 2));
        dPanel.add(kttb65);

        kttb66.setBackground(new java.awt.Color(255, 255, 255));
        kttb66.setText("あ");
        kttb66.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb66.setMargin(new java.awt.Insets(2, 2, 2, 2));
        dPanel.add(kttb66);

        kttb67.setBackground(new java.awt.Color(255, 255, 255));
        kttb67.setText("あ");
        kttb67.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb67.setMargin(new java.awt.Insets(2, 2, 2, 2));
        dPanel.add(kttb67);

        kttb68.setBackground(new java.awt.Color(255, 255, 255));
        kttb68.setText("あ");
        kttb68.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb68.setMargin(new java.awt.Insets(2, 2, 2, 2));
        dPanel.add(kttb68);

        jPanel14.add(dPanel);

        zPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel24.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("z-");
        zPanel.add(jLabel24);

        kttb59.setBackground(new java.awt.Color(255, 255, 255));
        kttb59.setText("あ");
        kttb59.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb59.setMargin(new java.awt.Insets(2, 2, 2, 2));
        zPanel.add(kttb59);

        kttb60.setBackground(new java.awt.Color(255, 255, 255));
        kttb60.setText("あ");
        kttb60.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb60.setMargin(new java.awt.Insets(2, 2, 2, 2));
        zPanel.add(kttb60);

        kttb61.setBackground(new java.awt.Color(255, 255, 255));
        kttb61.setText("あ");
        kttb61.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb61.setMargin(new java.awt.Insets(2, 2, 2, 2));
        zPanel.add(kttb61);

        kttb62.setBackground(new java.awt.Color(255, 255, 255));
        kttb62.setText("あ");
        kttb62.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb62.setMargin(new java.awt.Insets(2, 2, 2, 2));
        zPanel.add(kttb62);

        kttb63.setBackground(new java.awt.Color(255, 255, 255));
        kttb63.setText("あ");
        kttb63.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb63.setMargin(new java.awt.Insets(2, 2, 2, 2));
        zPanel.add(kttb63);

        jPanel14.add(zPanel);

        gPanel.setLayout(new java.awt.GridLayout(6, 0, 0, 1));

        jLabel9.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("g-");
        gPanel.add(jLabel9);

        kttb11.setBackground(new java.awt.Color(255, 255, 255));
        kttb11.setText("あ");
        kttb11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb11.setMargin(new java.awt.Insets(2, 2, 2, 2));
        gPanel.add(kttb11);

        kttb12.setBackground(new java.awt.Color(255, 255, 255));
        kttb12.setText("あ");
        kttb12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb12.setMargin(new java.awt.Insets(2, 2, 2, 2));
        gPanel.add(kttb12);

        kttb13.setBackground(new java.awt.Color(255, 255, 255));
        kttb13.setText("あ");
        kttb13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb13.setMargin(new java.awt.Insets(2, 2, 2, 2));
        gPanel.add(kttb13);

        kttb14.setBackground(new java.awt.Color(255, 255, 255));
        kttb14.setText("あ");
        kttb14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb14.setMargin(new java.awt.Insets(2, 2, 2, 2));
        gPanel.add(kttb14);

        kttb15.setBackground(new java.awt.Color(255, 255, 255));
        kttb15.setText("あ");
        kttb15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        kttb15.setMargin(new java.awt.Insets(2, 2, 2, 2));
        gPanel.add(kttb15);

        jPanel14.add(gPanel);
        jPanel14.add(filler11);

        labelsPanel1.setLayout(new java.awt.GridLayout(6, 0, 0, 1));
        labelsPanel1.add(filler2);

        jLabel6.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("a");
        labelsPanel1.add(jLabel6);

        jLabel11.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("i");
        labelsPanel1.add(jLabel11);

        jLabel12.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("u");
        labelsPanel1.add(jLabel12);

        jLabel13.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("e");
        labelsPanel1.add(jLabel13);

        jLabel14.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("o");
        labelsPanel1.add(jLabel14);

        jPanel14.add(labelsPanel1);

        add(jPanel14);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bPanel;
    private javax.swing.JPanel dPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JPanel gPanel;
    private javax.swing.JPanel hPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel kPanel;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb1;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb10;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb11;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb12;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb13;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb14;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb15;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb2;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb21;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb22;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb23;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb24;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb25;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb26;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb27;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb28;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb29;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb3;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb30;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb31;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb32;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb33;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb34;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb35;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb36;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb37;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb38;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb39;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb4;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb40;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb41;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb42;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb43;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb44;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb45;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb46;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb47;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb48;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb49;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb5;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb50;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb51;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb52;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb53;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb54;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb55;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb56;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb57;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb58;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb59;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb6;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb60;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb61;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb62;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb63;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb64;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb65;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb66;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb67;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb68;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb69;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb7;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb70;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb71;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb72;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb73;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb74;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb75;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb76;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb77;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb78;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb8;
    private com.a544jh.kanamemory.ui.KanaTableToggleButton kttb9;
    private javax.swing.JPanel labelsPanel;
    private javax.swing.JPanel labelsPanel1;
    private javax.swing.JPanel mPanel;
    private javax.swing.JPanel nPanel;
    private javax.swing.JPanel nnPanel;
    private javax.swing.JPanel pPanel;
    private javax.swing.JPanel rPanel;
    private javax.swing.JPanel sPanel;
    private javax.swing.JPanel tPanel;
    private javax.swing.JPanel vocPanel;
    private javax.swing.JPanel wPanel;
    private javax.swing.JPanel yPanel;
    private javax.swing.JPanel zPanel;
    // End of variables declaration//GEN-END:variables
}
