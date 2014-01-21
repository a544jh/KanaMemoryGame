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
public class KanaSyllableTest {
    
    public KanaSyllableTest() {
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
    public void testA(){
        KanaSyllable a = KanaSyllable.A;
        String s = a.getCharacterString(CharacterType.HIRAGANA)+
                a.getCharacterString(CharacterType.KATAKANA)+
                a.getCharacterString(CharacterType.ROMAJI);
        
        assertEquals("あアa", s);
    }
}
