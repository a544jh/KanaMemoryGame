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

    private final KanaSyllable syllable;
    private final CharacterType type;

    public KanaCharacter(KanaSyllable syllable, CharacterType type) {
        this.syllable = syllable;
        this.type = type;
    }

    public String charcterString() {
        return characterString(this.type);
    }
    
    public String characterString(CharacterType cType){

        return syllable.getCharacterString(cType);
    }
    
    public boolean matchesWith(KanaCharacter c){
        return this.syllable.equals(c.syllable);
    }

    public KanaSyllable getSyllable() {
        return syllable;
    }

    public CharacterType getType() {
        return type;
    }
}
