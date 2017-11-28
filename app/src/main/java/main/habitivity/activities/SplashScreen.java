package main.habitivity.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import main.habitivity.R;
import main.habitivity.controllers.AllUsersController;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

public class SplashScreen extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    private ArrayList<User> allUsers = new ArrayList<>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);
        allUsers = AllUsersController.getAllUsers();
        UserContainer.getInstance().setAllUsers(allUsers);
        LoadScreenLauncher loadScreenLauncher = new LoadScreenLauncher();
        loadScreenLauncher.start();
    }

    private class LoadScreenLauncher extends Thread {
        public void run() {
            try {
                sleep(SPLASH_DISPLAY_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(SplashScreen.this, LoginUser.class);
            startActivity(intent);
            SplashScreen.this.finish();
        }
    }
}
