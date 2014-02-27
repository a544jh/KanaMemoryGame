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
 * Methods for parsing JSON-formatted files to construct a {@link PlayerProfile}
 * object.
 *
 * @author axel
 */
public class JsonFileReader {

    /**
     * Creates a new PlayerProfile object from a JSON file
     *
     * @param profileName The name field of the profile to be loaded, used as
     * the key in the JSON mapping.
     * @param filename Name of the JSON-formatted file to read the data from.
     * @return A new PlayerProfile with the character scores loaded from the
     * file.
     */
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

    /**
     * Returns a list of profile names in a JSON file. (The keys in the JSON
     * mapping)
     *
     * @param filename JSON file to be parsed.
     * @return
     * @throws java.io.FileNotFoundException
     * @throws org.json.JSONException
     */
    public static ArrayList<String> ProfilesList(String filename) throws FileNotFoundException, JSONException {
        ArrayList<String> names = new ArrayList<>();
        JSONObject jo = readFileToJsonObject(new File(filename));
        Iterator namesIterator = jo.keys();
        while (namesIterator.hasNext()) {
            names.add((String) namesIterator.next());

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
