package com.a544jh.kanamemory;

import com.a544jh.kanamemory.characters.*;
import com.a544jh.kanamemory.io.JsonFileReader;
import com.a544jh.kanamemory.io.JsonFileWriter;
import com.a544jh.kanamemory.profile.PlayerProfile;
import com.a544jh.kanamemory.textgame.TextGame;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.EnumSet;

/**
 * This is just testing stuff.
 * Run the UI by running the MainWindow file!
 * 
 */
public class App {

    public static void main(String[] args) throws UnsupportedEncodingException {

//        TextGame testGame = new TextGame(new PlayerProfile("Test player"),
//                EnumSet.range(KanaSyllable.A, KanaSyllable.MO));
//        testGame.printStats();
//        testGame.play(CharacterType.HIRAGANA);
//        testGame.printStats();
//        testGame.play(CharacterType.KATAKANA);
//        testGame.printStats();

        ArrayList<String> names = JsonFileReader.ProfilesList("profiles");
        for (String string : names) {
            System.out.println(string);
        }
        
        PlayerProfile test1 = new PlayerProfile("test1");
        PlayerProfile test2 = new PlayerProfile("test2");

        test1.addScore(KanaSyllable.A, CharacterType.HIRAGANA, 10);
        test1.addScore(KanaSyllable.KA, CharacterType.HIRAGANA, 12);
        test1.addScore(KanaSyllable.GO, CharacterType.KATAKANA, 15);
        test1.addScore(KanaSyllable.SA, CharacterType.HIRAGANA, 27);
        
        test2.addScore(KanaSyllable.YA, CharacterType.KATAKANA, 16);
        test2.addScore(KanaSyllable.YU, CharacterType.HIRAGANA, 17);
        test2.addScore(KanaSyllable.YO, CharacterType.KATAKANA, 18);
        
        JsonFileWriter.saveProfile(test1, "profiles");
        JsonFileWriter.saveProfile(test2, "profiles");
        
        PlayerProfile loadTest = JsonFileReader.loadProfile("test1", "profiles");
        TextGame testGame = new TextGame(loadTest, EnumSet.range(KanaSyllable.A, KanaSyllable.MO));
        testGame.printStats();
    }
}
