/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.services;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Handles saving locally to app
 */
public class AndroidFileHandler{
    private Context context;

    public AndroidFileHandler(Context context) {
        this.context = context;
    }

    /**
     * Loading a file as a stirng
     * @param filename
     * @return the files contents as a string
     */
    public String loadFileAsString(String filename) {
        FileInputStream inputStream;

        try {
            inputStream = context.openFileInput(filename);
            String out = convertStreamToString(inputStream);
            inputStream.close();

            return out;

        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Saving a string to a file
     * @param filename name of file to be saved to
     * @param contents contents of the string to put in the file
     */
    public void saveStringToFile(String filename, String contents) {
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(contents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a stream to a string
     * @param is stream
     * @return string version of the stream
     */
    private String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}