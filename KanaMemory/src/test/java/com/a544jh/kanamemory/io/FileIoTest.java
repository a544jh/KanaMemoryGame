/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.a544jh.kanamemory.io;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaSyllable;
import com.a544jh.kanamemory.profile.PlayerProfile;
import org.json.JSONWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author axwikstr@cs
 */
public class FileIoTest {
    
    public FileIoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        PlayerProfile testProfile1 = new PlayerProfile("test1");
        PlayerProfile testProfile2 = new PlayerProfile("test2");
        
        testProfile1.addScore(KanaSyllable.TE, CharacterType.HIRAGANA, 5);
        testProfile1.addScore(KanaSyllable.SU, CharacterType.KATAKANA, 7);
        testProfile1.addScore(KanaSyllable.CHI, CharacterType.HIRAGANA, 9);
        
        testProfile2.addScore(KanaSyllable.RU, CharacterType.HIRAGANA, 14);
        testProfile2.addScore(KanaSyllable.YO, CharacterType.KATAKANA, 17);
        testProfile2.addScore(KanaSyllable.TSU, CharacterType.HIRAGANA, 19);
        testProfile2.addScore(KanaSyllable.KA, CharacterType.KATAKANA, 20);
    }
    
    @Test
    public void namesListTest(){
        JSONWriter
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
