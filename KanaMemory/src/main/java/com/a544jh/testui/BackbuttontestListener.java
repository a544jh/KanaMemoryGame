/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.a544jh.testui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author axel
 */
public class BackbuttontestListener implements ActionListener{

    private Component component;

    public BackbuttontestListener(Component component) {
        this.component = component;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        CardLayout cl = (CardLayout)  component.getParent().getLayout();
        cl.show(component.getParent(), "card9");
        component.getParent().remove(component);
    }
    
}
