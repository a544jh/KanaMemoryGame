/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.a544jh.kanamemory.io;

import com.a544jh.kanamemory.characters.CharacterType;
import com.a544jh.kanamemory.characters.KanaSyllable;
import com.a544jh.kanamemory.profile.PlayerProfile;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    private PlayerProfile testProfile1;
    private PlayerProfile testProfile2;
    
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
        testProfile1 = new PlayerProfile("test1");
        testProfile2 = new PlayerProfile("test2");
        
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
        JsonFileWriter.saveProfile(testProfile1, "profiles_test.tmp");
        JsonFileWriter.saveProfile(testProfile2, "profiles_test.tmp");
        
        ArrayList<String> names = new ArrayList<>(Arrays.asList("test1","test2"));
        assertEquals(names, JsonFileReader.ProfilesList("profiles_test.tmp"));
    }
    
    @Test
    public void profileSaveAndLoadTest() {
        JsonFileWriter.saveProfile(testProfile1, "profiles_test.tmp");
        JsonFileWriter.saveProfile(testProfile2, "profiles_test.tmp");
        PlayerProfile loaded1 = JsonFileReader.loadProfile("test1", "profiles_test.tmp");
        PlayerProfile loaded2 = JsonFileReader.loadProfile("test2", "profiles_test.tmp");
        
        assertEquals(testProfile1.getName(), loaded1.getName());
        assertEquals(testProfile1.getScoresMap(), loaded1.getScoresMap());
        
        assertEquals(testProfile2.getName(), loaded2.getName());
        assertEquals(testProfile2.getScoresMap(), loaded2.getScoresMap());
    }
    
    @After
    public void tearDown() {
        File tmpfile = new File("profiles_test.tmp");
        tmpfile.deleteOnExit();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
