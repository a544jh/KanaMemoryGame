/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.a544jh.kanamemory.gamelogic;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaCharacter;
import com.a544jh.kanamemory.characters.KanaSyllable;
import com.a544jh.kanamemory.profile.PlayerProfile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;

/**
 *
 * @author axel
 */
public class MatchingGame {
    private PlayerProfile profile;
    private final EnumSet<KanaSyllable> kanaSet;// = EnumSet.range(KanaSyllable.A, KanaSyllable.O);
    private CharacterType cType1, cType2;   
    private int charsPerRound;
    private ArrayList<KanaSyllable> syllableList;
    

    public MatchingGame(PlayerProfile profile, EnumSet<KanaSyllable> kanaSet, 
            CharacterType cType1, CharacterType cType2, int charsPerRound) {
        this.profile = profile;
        this.kanaSet = kanaSet;
        this.cType1 = cType1;
        this.cType2 = cType2;
        this.charsPerRound = charsPerRound;
        this.syllableList = new ArrayList<>();
        
        for (KanaSyllable kanaSyllable : kanaSet) {
            syllableList.add(kanaSyllable);
        }
    }
    
    public ArrayList[] getCharacters(){
        Collections.shuffle(syllableList);
        ArrayList[] charLists = new ArrayList[2];
        charLists[0] = new ArrayList<KanaCharacter>();
        charLists[1] = new ArrayList<KanaCharacter>();
        //Populate lists with KanaCharacters
        for (int i = 0; i < charsPerRound; i++) {
            charLists[0].add(new KanaCharacter(syllableList.get(i), cType1));
            charLists[1].add(new KanaCharacter(syllableList.get(i), cType2));
        }
        Collections.shuffle(charLists[0]);
        Collections.shuffle(charLists[1]);
        
        return charLists;
    }
    
    public boolean matchCharacters(KanaCharacter c1, KanaCharacter c2) {
        if (c1.matchesWith(c2)){
            profile.addScore(c1, 1);
            profile.addScore(c2, 1);
            return true;
        } else {
            profile.addScore(c1, -1);
            profile.addScore(c2, -1);
            return false;
        }
    }

    public int getCharsPerRound() {
        return charsPerRound;
    }
    
    
}
