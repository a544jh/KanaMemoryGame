/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.a544jh.kanamemory.profile;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaCharacter;
import com.a544jh.kanamemory.characters.KanaSyllable;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author axel
 */
public class PlayerProfile {
    private String name;
    //private Map<KanaSyllable,Integer> hiraganaScores, katakanaScores;
    private Map<CharacterType, Map<KanaSyllable, Integer>> kanaScores;
    
    public PlayerProfile(String name){
        this.name = name;
        //hiraganaScores = new EnumMap<>(KanaSyllable.class);
        //katakanaScores = new EnumMap<>(KanaSyllable.class);
        kanaScores = new EnumMap<>(CharacterType.class);
        for (CharacterType cType : CharacterType.values()){
            kanaScores.put(cType, new EnumMap<KanaSyllable, Integer>(KanaSyllable.class));
        }
    }

    public String getName() {
        return name;
    }
    
    public int getScore(KanaCharacter c){
        return getScore(c.getSyllable(), c.getType());
    }

    public int getScore(KanaSyllable syllable, CharacterType type) {
        Map<KanaSyllable, Integer> scoreMap = kanaScores.get(type);
        
        if (scoreMap.containsKey(syllable)){
            return scoreMap.get(syllable);
        }
        return 0;
    }
    
    public void addScore(KanaCharacter c, int score){
        addScore(c.getSyllable(), c.getType(), score);
    }

    public void addScore(KanaSyllable syllable, CharacterType type, int score) {
        Map<KanaSyllable, Integer> scoreMap = kanaScores.get(type);
        
        int oldscore = getScore(syllable, type);
        scoreMap.put(syllable, oldscore + score);
    }

    
    
}
