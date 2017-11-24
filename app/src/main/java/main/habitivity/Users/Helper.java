package main.habitivity.Users;

import android.util.Log;

import java.util.concurrent.ExecutionException;

import main.habitivity.ElasticsearchController;

/**
 * Created by Shally on 2017-11-23.
 */
public class Helper {

    public static User getUserObject(String jestID) {
        ElasticsearchController.GetSingleUserTask getSingleUserTask
                = new ElasticsearchController.GetSingleUserTask();
        try {
            getSingleUserTask.execute(jestID).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            return getSingleUserTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the users from the async object");
            Log.i("Error", e.toString());
        }
        return null;
    }

}