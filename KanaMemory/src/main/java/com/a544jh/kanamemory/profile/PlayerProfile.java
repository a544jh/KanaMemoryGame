/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.profile;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaCharacter;
import com.a544jh.kanamemory.characters.KanaSyllable;
import java.awt.Color;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * The representation of a player's profile, containing its name and a score for
 * each Japanese kana character. The profile has a mapping of each possible
 * KanaCharacter, excluding the romaji forms.
 */
public class PlayerProfile {

    private String name;
    /**
     * The maximum score for each character.
     */
    public final int MAX_CHARACTER_SCORE = 20;
    public final int MAX_TOTAL_SCORE;
    private Map<CharacterType, Map<KanaSyllable, Integer>> kanaScores;

    public PlayerProfile(String name) {
        this.name = name;
        kanaScores = new EnumMap<>(CharacterType.class);
        for (CharacterType cType : EnumSet.of(CharacterType.HIRAGANA, CharacterType.KATAKANA)) {
            kanaScores.put(cType, new EnumMap<KanaSyllable, Integer>(KanaSyllable.class));
        }
        MAX_TOTAL_SCORE = 2 * KanaSyllable.values().length * MAX_CHARACTER_SCORE;
    }

    public Map<CharacterType, Map<KanaSyllable, Integer>> getScoresMap() {
        return kanaScores;
    }

    public String getName() {
        return name;
    }

    public int getScore(KanaCharacter c) {
        return getScore(c.getSyllable(), c.getType());
    }

    public int getScore(KanaSyllable syllable, CharacterType type) {
        //Not keeping score of Romaji characters
        if (type == CharacterType.ROMAJI) {
            return 0;
        }

        Map<KanaSyllable, Integer> scoreMap = kanaScores.get(type);

        if (scoreMap.containsKey(syllable)) {
            return scoreMap.get(syllable);
        }
        return 0;
    }

    public void addScore(KanaCharacter c, int score) {
        addScore(c.getSyllable(), c.getType(), score);
    }

    public void addScore(KanaSyllable syllable, CharacterType type, int score) {
        //Not keeping score of Romaji characters
        if (type == CharacterType.ROMAJI) {
            return;
        }

        Map<KanaSyllable, Integer> scoreMap = kanaScores.get(type);

        int oldscore = getScore(syllable, type);
        if (oldscore + score > MAX_CHARACTER_SCORE) {
            scoreMap.put(syllable, MAX_CHARACTER_SCORE);
        } else {
            scoreMap.put(syllable, oldscore + score);
        }
    }

    public int getTotalScoreSum() {
        int sum = 0;
        for (CharacterType c : kanaScores.keySet()) {
            for (Integer i : kanaScores.get(c).values()) {
                sum += i;
            }
        }
        return sum;
    }

    public double getCompletionPercentage() {
        return ((double) getTotalScoreSum() / MAX_TOTAL_SCORE) * 100;
    }

    /**
     * Get a color for a character based on its score. The score being closer to
     * MAX_CHARACTER_SCORE results in a greener color, while being closer to
     * -MAX_CHARACTER_SCORE returns a redder color.
     *
     * @param c
     * @return
     */
    public Color getCharacterColor(KanaCharacter c) {
        return getCharacterColor(c.getSyllable(), c.getType());
    }

    /**
     * Get a color for a character based on its score. The score being closer to
     * MAX_CHARACTER_SCORE results in a greener color, while being closer to
     * -MAX_CHARACTER_SCORE returns a redder color.
     *
     * @param syllable
     * @param type
     * @return
     */
    public Color getCharacterColor(KanaSyllable syllable, CharacterType type) {
        int score = getScore(syllable, type);
        //Green
        if (score >= 0) {
            double cofficient = -255.0 / MAX_CHARACTER_SCORE;
            int val = (int) (score * cofficient + 255);
            return new Color(val, 255, val);
            //Red
        } else if (score <= 0) {
            if (score < -MAX_CHARACTER_SCORE) {
                score = -MAX_CHARACTER_SCORE;
            }
            double cofficient = 255.0 / MAX_CHARACTER_SCORE;
            int val = (int) (score * cofficient + 255);
            return new Color(255, val, val);
        } else {
            return new Color(255, 255, 255);
        }
    }
}
