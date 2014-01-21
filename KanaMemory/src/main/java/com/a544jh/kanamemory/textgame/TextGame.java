/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.textgame;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaCharacter;
import com.a544jh.kanamemory.characters.KanaSyllable;
import com.a544jh.kanamemory.profile.PlayerProfile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Scanner;

/**
 *
 * @author axel
 */
public class TextGame {

    private PlayerProfile profile;
    private final EnumSet<KanaSyllable> kanaSet;// = EnumSet.range(KanaSyllable.A, KanaSyllable.O);

    public TextGame(PlayerProfile profile, EnumSet<KanaSyllable> kanaSet) {
        this.profile = profile;
        this.kanaSet = kanaSet;
    }

    public void printStats() {
        System.out.println("--Stats--");
        System.out.println("Name: " + profile.getName());

        for (CharacterType ct : EnumSet.of(CharacterType.HIRAGANA, CharacterType.KATAKANA)) {
            
            for (KanaSyllable kanaSyllable : kanaSet) {
                String kana = kanaSyllable.getCharacterString(ct);
                int score = profile.getScore(kanaSyllable, ct);
                System.out.print(kana + ":" + score + " ");
            }
            System.out.print("\n");
        }
        System.out.println("");
    }

    public void play(CharacterType gameCType) {
        ArrayList<KanaCharacter> gameCharacters = new ArrayList<>();

        //Create the KanaCharacter objects for play
        for (KanaSyllable kanaSyllable : kanaSet) {
            gameCharacters.add(new KanaCharacter(kanaSyllable, gameCType));
        }
        Collections.shuffle(gameCharacters);

        Scanner scanner = new Scanner(System.in);

        for (KanaCharacter c : gameCharacters) {
            System.out.println("--" + c.charcterString() + "--");
            System.out.print(">");
            String input = scanner.nextLine();

            if (c.characterString(CharacterType.ROMAJI).equals(input)) {
                System.out.println("Correct!");
                profile.addScore(c, 1);
            } else {
                System.out.println("Incorrect!");
                profile.addScore(c, -1);
            }
        }
    }

}
