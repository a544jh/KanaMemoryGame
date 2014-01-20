/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.characters;

/**
 *
 * @author Axel
 */
public class KanaCharacter {

    private final KanaSyllables syllable;
    private final CharacterType type;

    public KanaCharacter(KanaSyllables syllable, CharacterType type) {
        this.syllable = syllable;
        this.type = type;
    }

    public String charcterString() {
        switch (type) {
            case HIRAGANA:
                return syllable.getHiragana();
            case KATAKANA:
                return syllable.getKatakana();
            default:
                return syllable.getRomaji();
        }
    }
    
    public boolean matchesWith(KanaCharacter c){
        return this.syllable.equals(c.syllable);
    }
}
