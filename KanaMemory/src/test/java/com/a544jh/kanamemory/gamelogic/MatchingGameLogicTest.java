package com.a544jh.kanamemory.gamelogic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaCharacter;
import com.a544jh.kanamemory.characters.KanaSyllable;
import com.a544jh.kanamemory.gamelogic.MatchingGame;
import com.a544jh.kanamemory.profile.PlayerProfile;
import java.util.EnumSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author axwikstr
 */
public class MatchingGameLogicTest {

    PlayerProfile p;
    MatchingGame mg;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        p = new PlayerProfile("test");
        mg = new MatchingGame(p, EnumSet.allOf(KanaSyllable.class), CharacterType.HIRAGANA, CharacterType.ROMAJI, 5);
    }

    @Test
    public void matchAddsScore() {
        KanaCharacter c = new KanaCharacter(KanaSyllable.A, CharacterType.HIRAGANA);
        KanaCharacter c1 = new KanaCharacter(KanaSyllable.A, CharacterType.ROMAJI);
        boolean match = mg.matchCharacters(c, c1);

        assertEquals(1, p.getScore(c));
        assertTrue(match);
    }

    @Test
    public void mismatchRemovesScore() {
        KanaCharacter c = new KanaCharacter(KanaSyllable.A, CharacterType.HIRAGANA);
        KanaCharacter c1 = new KanaCharacter(KanaSyllable.KA, CharacterType.ROMAJI);
        boolean match = mg.matchCharacters(c, c1);

        assertEquals(-1, p.getScore(c));
        assertFalse(match);
    }
    
    @Test
    public void characterListLengthTest() {
        assertEquals(mg.getCharsPerRound(), mg.getCharacters()[0].size());
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
