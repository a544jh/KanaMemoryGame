/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.characters;

/**
 * Represents one of the Japanese kana characters.
 * The character can be represented in its hiragana or katakana form, 
 * or as it would be written as in roman letters (romaji)
 */
public class KanaCharacter {

    private final KanaSyllable syllable;
    private final CharacterType type;

    /**
     * 
     * @param syllable  The syllable of the character.
     * @param type How the character should be represented.
     */
    public KanaCharacter(KanaSyllable syllable, CharacterType type) {
        this.syllable = syllable;
        this.type = type;
    }
    
    /**
     * 
     * @return The String representation of the character.
     */
    public String charcterString() {
        return characterString(this.type);
    }
    /**
     * 
     * @param cType The type of character to get the String representation as.
     * @return The String representation of the character as the specified CharacterType.
     */
    public String characterString(CharacterType cType){

        return syllable.getCharacterString(cType);
    }
    
    /**
     * Check if two characters represent the same syllable.
     * @param c The character to be matched.
     * @return <code>true</code> if the characters have the same KanaSyllable
     */
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
