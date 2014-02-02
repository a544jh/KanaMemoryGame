/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.a544jh.kanamemory.ui;

import com.a544jh.kanamemory.characters.KanaCharacter;
import javax.swing.JButton;

/**
 *
 * @author axel
 */

public class KanaCharacterButton extends JButton{
    private KanaCharacter character;
    
    public KanaCharacterButton(){
    }

    public KanaCharacter getCharacter() {
        return character;
    }

    public void setCharacter(KanaCharacter character) {
        this.character = character;
        setText(character.charcterString());
    }
    
    
}
