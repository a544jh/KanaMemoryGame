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
import java.util.EnumSet;
import java.util.Random;

/**
 *
 * @author axel
 */
public class TypingGame {

    private PlayerProfile profile;
    private final EnumSet<KanaSyllable> kanaSet;// = EnumSet.range(KanaSyllable.A, KanaSyllable.O);
    private CharacterType cType;
    private ArrayList<KanaCharacter> characterList;
    private String hint;
    private KanaCharacter currentCharacter;
    private Random random;
    private int mismatchCount;

    public TypingGame(PlayerProfile profile, EnumSet<KanaSyllable> kanaSet,
            CharacterType cType) {
        this.profile = profile;
        this.kanaSet = kanaSet;
        this.cType = cType;
        this.random = new Random();
        characterList = new ArrayList<>();
        makeCharacters();
        nextCharacter();
    }
    
    private void makeCharacters() {
        for (KanaSyllable s : kanaSet){
            characterList.add(new KanaCharacter(s, cType));
        }
    }
    
    private void nextCharacter() {
        mismatchCount = 0;
        hint = "";
        currentCharacter = characterList.get(random.nextInt(characterList.size()));
    }
    
    private void generateHint() {
        String romajiString = currentCharacter.characterString(CharacterType.ROMAJI);
        if (romajiString.length() == 1){
            hint = "It's a vocal";
        } else {
            String dashes = "";
            //add dashes for the remaining charcters
            for (int i = 0; i < romajiString.length() - 1; i++) {
                dashes += "-";
            }
        hint = romajiString.charAt(0) + dashes;}
    }
    
    public boolean makeMatch(String s) {
        if (s.equals(currentCharacter.characterString(CharacterType.ROMAJI))){
            profile.addScore(currentCharacter, 1);
            nextCharacter();
            return true;
        } else {
            profile.addScore(currentCharacter, -1);
            mismatchCount++;
            if (mismatchCount == 3){
                generateHint();
            }
            return false;
        }
    }

    public String getHint() {
        return hint;
    }

    public KanaCharacter getCurrentCharacter() {
        return currentCharacter;
    }
    
    
}
