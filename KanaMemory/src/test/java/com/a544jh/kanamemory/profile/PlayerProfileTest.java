/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.profile;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaCharacter;
import com.a544jh.kanamemory.characters.KanaSyllable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author axel
 */
public class PlayerProfileTest {

    private PlayerProfile p;

    public PlayerProfileTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        p = new PlayerProfile("test");
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void constructorTest() {
        assertEquals("test", p.getName());
    }

    @Test
    public void getScoreReturnsZero() {
        assertEquals(0, p.getScore(KanaSyllable.TA, CharacterType.KATAKANA));
    }

    @Test
    public void addScoreTest() {
        KanaCharacter c = new KanaCharacter(KanaSyllable.A, CharacterType.HIRAGANA);

        p.addScore(c, 10);
        p.addScore(KanaSyllable.A, CharacterType.HIRAGANA, -5);

        assertEquals(5, p.getScore(c));
    }

    @Test
    public void addScoreDifferentTypesTest() {
        KanaCharacter h = new KanaCharacter(KanaSyllable.HA, CharacterType.HIRAGANA);
        KanaCharacter k = new KanaCharacter(KanaSyllable.HA, CharacterType.KATAKANA);

        p.addScore(h, 2);
        p.addScore(k, 3);

        assertEquals(2, p.getScore(h));
        assertEquals(3, p.getScore(k));
        assertTrue(p.getScore(h) != p.getScore(KanaSyllable.HA, CharacterType.KATAKANA));
    }

    
    @Test
    public void addScoreRomajiTest(){
        p.addScore(KanaSyllable.A, CharacterType.ROMAJI, 10);
        
        assertEquals(0, p.getScore(KanaSyllable.A, CharacterType.ROMAJI));
    }
    
    @Test
    public void scoreDoesNotExceedMaxTest() {
        KanaCharacter c = new KanaCharacter(KanaSyllable.A, CharacterType.HIRAGANA);

        p.addScore(c, 100);

        assertEquals(p.MAX_CHARACTER_SCORE, p.getScore(c));
    }
    
    @Test
    public void getTotalScoreSumTest(){
        KanaCharacter h = new KanaCharacter(KanaSyllable.HA, CharacterType.HIRAGANA);
        KanaCharacter k = new KanaCharacter(KanaSyllable.HA, CharacterType.KATAKANA);

        p.addScore(h, 2);
        p.addScore(k, 3);

        assertEquals(5, p.getTotalScoreSum());
    }
    
    @Test
    public void getCompletionPrecentageTest() {
        for (KanaSyllable ks : KanaSyllable.values()){
            p.addScore(ks, CharacterType.HIRAGANA, p.MAX_CHARACTER_SCORE);
            p.addScore(ks, CharacterType.KATAKANA, p.MAX_CHARACTER_SCORE);
        }
        
        assertEquals(100, p.getCompletionPrecentage(),0.001);
    }
}
