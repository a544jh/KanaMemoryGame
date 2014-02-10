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
 *Game logic class for the matching game.
 * @author axel
 */
public class MatchingGame {
    private PlayerProfile profile;
    private final EnumSet<KanaSyllable> kanaSet;// = EnumSet.range(KanaSyllable.A, KanaSyllable.O);
    private CharacterType cType1, cType2;   
    private int charsPerRound;
    private ArrayList<KanaSyllable> syllableList;
    
    /**
     * Creates a new MatchingGame instance.
     * @param profile The profile to change the scores of.
     * @param kanaSet Set of KanaSyllables to be used in the game.
     * @param cType1 CharacterType to match "from"
     * @param cType2 CharacterType to match "to"
     * @param charsPerRound Number of characters per round.
     */
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
    
    /**
     * Get the KanaCharacters to be used in the game.
     * @return An ArrayList for each CharacterType with randomly selected syllables.
     * The length of the lists is determined by the charsPerRound attribute.
     */
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
    /**
     * Makes the match of two KanaCharacters and takes care of the scoring.
     * @param c1
     * @param c2
     * @return true if the characters match.
     */
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
