package com.a544jh.kanamemory;

import com.a544jh.kanamemory.characters.*;
import com.a544jh.kanamemory.profile.PlayerProfile;
import com.a544jh.kanamemory.textgame.TextGame;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.EnumSet;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws UnsupportedEncodingException {
        TextGame testGame = new TextGame(new PlayerProfile("Test player"),
                EnumSet.range(KanaSyllable.A, KanaSyllable.O));
        testGame.printStats();
        testGame.play(CharacterType.HIRAGANA);
        testGame.play(CharacterType.HIRAGANA);
        testGame.printStats();
        testGame.play(CharacterType.KATAKANA);
        testGame.printStats();
    }
}
