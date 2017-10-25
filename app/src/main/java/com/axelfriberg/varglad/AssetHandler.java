package com.axelfriberg.varglad;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class AssetHandler {
    private final AssetManager mAssetManager;

    public AssetHandler(AssetManager assetManager) {
        mAssetManager = assetManager;
    }

    public String readSongFile(String spexTitle, String songTitle) {
        spexTitle = spexTitle.replaceAll("\\s", "_").toLowerCase();
        songTitle = songTitle.replaceAll("\\s", "_");

        try {
            InputStream is =
                    mAssetManager.open(String.format("spex/%s/%s.txt", spexTitle, songTitle));
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = reader.readLine();
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String[] getArrayOfSongTitles(String spexTitle) {
        spexTitle = spexTitle.toLowerCase().replaceAll("\\s", "_");
        try {
            String[] listOfSongNames = mAssetManager.list("spex/" + spexTitle);
            for (int i = 0; i < listOfSongNames.length; i++) {
                listOfSongNames[i] = listOfSongNames[i].replaceAll("_", " ").replaceAll(".txt", "");
            }
            return listOfSongNames;
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}
