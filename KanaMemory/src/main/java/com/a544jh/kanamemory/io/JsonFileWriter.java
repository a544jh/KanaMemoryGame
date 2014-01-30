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
 *
 * @author axel
 */
public class JsonFileWriter {

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
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jo.toString(4));
            }
        } catch (JSONException | IOException ex) {
            Logger.getLogger(JsonFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
