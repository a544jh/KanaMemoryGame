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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TypingGameLogicTest {
    
    PlayerProfile p;
    TypingGame tg;
    
    public TypingGameLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p = new PlayerProfile("Test");
        tg = new TypingGame(p, EnumSet.range(KanaSyllable.SA, KanaSyllable.SO), CharacterType.HIRAGANA);
    }
    
    @Test
    public void noConsecutiveCharactersTest(){
        boolean test = true;
        KanaCharacter cur, prev;
        prev = null;
        for (int i = 0; i < 100; i++) {
            try {
                cur = tg.getCurrentCharacter();
                Method method = TypingGame.class.getDeclaredMethod("nextCharacter");
                method.setAccessible(true);
                method.invoke(tg);
                if (prev != null && cur.matchesWith(prev)){
                    test = false;
                    break;
                }
                prev = cur;
            } catch (    NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(TypingGameLogicTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        assertTrue(test);
    }
    
    @Test
    public void generateHintTest() {
        for (int i = 0; i < 3; i++) {
            tg.makeMatch("ö");
        }
        assertFalse(tg.getHint().isEmpty());
    }
    
    @Test
    public void emptyHintTest() {
        assertTrue(tg.getHint().isEmpty());
    }
    
    @Test
    public void makeMatchMatchTest() {
        KanaCharacter cur = tg.getCurrentCharacter();
        boolean test = tg.makeMatch(tg.getCurrentCharacter().characterString(CharacterType.ROMAJI));
        assertEquals(1, p.getScore(cur));
        assertTrue(test);
    }
    
    @Test
    public void makeMatchMismatchTest() {
        KanaCharacter cur = tg.getCurrentCharacter();
        boolean test = tg.makeMatch("ö");
        assertEquals(-1, p.getScore(cur));
        assertFalse(test);
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
