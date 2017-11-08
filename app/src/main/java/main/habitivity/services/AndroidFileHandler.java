package main.habitivity.services;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Handle saving locally to app
 */
public class AndroidFileHandler{
    private Context context;

    public AndroidFileHandler(Context context) {
        this.context = context;
    }

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

    private String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}