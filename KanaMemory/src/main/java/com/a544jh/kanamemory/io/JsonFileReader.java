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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author axel
 */
public class JsonFileReader {

    public static PlayerProfile loadProfile(String profileName, String filename) {
        PlayerProfile profile = new PlayerProfile(profileName);
        File file = new File(filename);
        try {
            //Get the profile's kanaScores from the file by using the name as key
            JSONObject kanaScores = readFileToJsonObject(file).getJSONObject(profileName);
            //Fill the profile's EnumMap with values
            populateKanaScores(profile, kanaScores);

        } catch (FileNotFoundException | JSONException ex) {
            Logger.getLogger(JsonFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profile;
    }

    private static void populateKanaScores(PlayerProfile profile, JSONObject kanaScores) throws JSONException {
        Iterator typesIterator = kanaScores.keys();

        while (typesIterator.hasNext()) {
            String charType = (String) typesIterator.next();
            JSONObject syllables = (JSONObject) kanaScores.get(charType);

            Iterator syllablesIterator = syllables.keys();
            while (syllablesIterator.hasNext()) {
                String syllable = (String) syllablesIterator.next();
                profile.addScore(KanaSyllable.valueOf(syllable), CharacterType.valueOf(charType), syllables.getInt(syllable));
            }
        }
    }

    public static ArrayList<String> readProfilesList(String filename) {
        ArrayList<String> names = new ArrayList<>();
        try {
            JSONObject jo = readFileToJsonObject(new File(filename));
            Iterator namesIterator = jo.keys();
            while (namesIterator.hasNext()) {
                names.add((String) namesIterator.next());
            }
        } catch (FileNotFoundException | JSONException ex) {
            Logger.getLogger(JsonFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return names;
    }

    protected static JSONObject readFileToJsonObject(File file) throws FileNotFoundException, JSONException {
        JSONObject jo;
        StringBuilder sb = new StringBuilder((int) file.length());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
        }
        jo = new JSONObject(sb.toString());

        return jo;
    }

}
