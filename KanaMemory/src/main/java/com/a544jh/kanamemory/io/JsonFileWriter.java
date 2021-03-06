/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.io;

import com.a544jh.kanamemory.profile.PlayerProfile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Methods for writing the data of {@link PlayerProfile} objects to a
 * JSON-formatted file.
 *
 * @author axel
 */
public class JsonFileWriter {

    /**
     * Saves the PlayerProfile to a JSON-formatted file. If the file exists, the
     * profile is added to the existing JSON-mapping, if a profile with the same
     * name already exists in th file, it will be overwritten. If the files does
     * not exist, a new JSON-formatted file will be created.
     *
     * @param profile The PlayerProfile to be saved.
     * @param filename The file to save the data to. Can be a JSON-formatted
     * file created by this method, or an nonexistent file.
     */
    public static void saveProfile(PlayerProfile profile, String filename) {
        File file = new File(filename);
        JSONObject jo = null;

        //Create a new file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(JsonFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Create new empty JSONObject
            jo = new JSONObject();
        } else {
            //If the file exists
            try {
                //Read the JSONObject from the file
                jo = JsonFileReader.readFileToJsonObject(file);
            } catch (FileNotFoundException | JSONException ex) {
                Logger.getLogger(JsonFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            //Put the profile's scores to be saved in the JSONOBject with the name as key
            jo.put(profile.getName(), profile.getScoresMap());
            //Write the actual file
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jo.toString(4));
            }
        } catch (JSONException | IOException ex) {
            Logger.getLogger(JsonFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Removes a PlayerProfile from a JSON-formatted file.
     * This method removes a key-value pair from the JSON-mapping using the PlayerProfile's
     * name attribute as the key.
     * @param profilename The name attribute of the profile to be removed (i.e. the key)
     * @param filename Name of the JSON-formatted file.
     */
    public static void deleteProfile(String profilename, String filename) {
        File file = new File(filename);
        JSONObject jo = null;
        try {
            //Read the JSONObject from the file
            jo = JsonFileReader.readFileToJsonObject(file);
        } catch (FileNotFoundException | JSONException ex) {
            Logger.getLogger(JsonFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Remove the profile from the JSON-mapping
        jo.remove(profilename);
        //Write the actual file
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jo.toString(4));
        } catch (IOException | JSONException ex) {
            Logger.getLogger(JsonFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
