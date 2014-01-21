/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.a544jh.kanamemory.characters;

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
public class KanaCharacterTest {
    
    public KanaCharacterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
    public void sameTypeMatchTest(){
        KanaCharacter c1 = new KanaCharacter(KanaSyllable.A, CharacterType.HIRAGANA);
        KanaCharacter c2 = new KanaCharacter(KanaSyllable.A, CharacterType.HIRAGANA);
        
        assertTrue(c1.matchesWith(c2));
    }
    
    @Test
    public void differentTypeMatchTest(){
        KanaCharacter c1 = new KanaCharacter(KanaSyllable.A, CharacterType.HIRAGANA);
        KanaCharacter c2 = new KanaCharacter(KanaSyllable.A, CharacterType.KATAKANA);
        
        assertTrue(c1.matchesWith(c2));
    }
    
    @Test
    public void differentSyllableMatchTest(){
        KanaCharacter c1 = new KanaCharacter(KanaSyllable.A, CharacterType.HIRAGANA);
        KanaCharacter c2 = new KanaCharacter(KanaSyllable.KA, CharacterType.HIRAGANA);
        
        assertFalse(c1.matchesWith(c2));
    }
    
    @Test
    public void characterStringTest(){
        KanaCharacter c1 = new KanaCharacter(KanaSyllable.KA, CharacterType.HIRAGANA);
        String s = c1.charcterString();
        
        assertEquals("か", s);
    }
    
    @Test
    public void romajiStringTest(){
        KanaCharacter c1 = new KanaCharacter(KanaSyllable.SHI, CharacterType.KATAKANA);
        String s = c1.characterString(CharacterType.ROMAJI);
        
        assertEquals("shi", s);
    }
}
