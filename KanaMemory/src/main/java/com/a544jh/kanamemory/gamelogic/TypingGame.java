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
 * Logic and state class for the TypingGame.
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
    private KanaCharacter previousCharacter;
    private Random random;
    private int mismatchCount;

    /**
     * Creates a new TypingGame instance.
     *
     * @param profile The profile to change the scores of.
     * @param kanaSet Set of KanaSyllables to be used in the game.
     * @param cType Type of characters to be showed
     */
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
        for (KanaSyllable s : kanaSet) {
            characterList.add(new KanaCharacter(s, cType));
        }
    }

    private void nextCharacter() {
        mismatchCount = 0;
        hint = "";
        // the same character wont be chosen two times in a row
        currentCharacter = characterList.get(random.nextInt(characterList.size()));
        if (previousCharacter != null) {
            characterList.add(previousCharacter);
        }
        characterList.remove(currentCharacter);
        previousCharacter = currentCharacter;
    }

    private void generateHint() {
        String romajiString = currentCharacter.characterString(CharacterType.ROMAJI);
        if (romajiString.length() == 1) {
            hint = "Hint: It's a vocal";
        } else {
            String dashes = "";
            //add dashes for the remaining charcters
            for (int i = 0; i < romajiString.length() - 1; i++) {
                dashes += "-";
            }
            hint = "Hint: " + romajiString.charAt(0) + dashes;
        }
    }

    /**
     * Matches the current character's romaji form with a String. Also takes
     * care of the scoring.
     *
     * @param s The input String.
     * @return true if the current character's romji String is equal to the
     * input String.
     */
    public boolean makeMatch(String s) {
        if (s.equals(currentCharacter.characterString(CharacterType.ROMAJI))) {
            profile.addScore(currentCharacter, 1);
            nextCharacter();
            return true;
        } else {
            profile.addScore(currentCharacter, -1);
            mismatchCount++;
            if (mismatchCount == 3) {
                generateHint();
            }
            return false;
        }
    }

    /**
     * Get the hint String. The string contains a hint of the current character
     * to be guessed if a wrong guess has been made at lest 3 times.
     *
     * @return The hint String.
     */
    public String getHint() {
        return hint;
    }

    public KanaCharacter getCurrentCharacter() {
        return currentCharacter;
    }

}
