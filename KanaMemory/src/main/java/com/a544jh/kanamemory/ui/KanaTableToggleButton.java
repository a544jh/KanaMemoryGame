/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.a544jh.kanamemory.ui;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaSyllable;
import javax.swing.JToggleButton;

/**
 *
 * @author axel
 */
public class KanaTableToggleButton extends JToggleButton{
    private KanaSyllable syllable;
    private CharacterType ctype;
    
    public KanaTableToggleButton() {
        
    }

    public void refreshText(){
        this.setText(syllable.getCharacterString(ctype));
    }
    
    public void refresh(){
        
    }
    
    public void setSyllable(KanaSyllable syllable) {
        this.syllable = syllable;
    }

    public void setCtype(CharacterType ctype) {
        this.ctype = ctype;
    }
    
}
