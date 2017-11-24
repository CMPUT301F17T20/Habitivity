/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import main.habitivity.Users.UserContainer;
import main.habitivity.controllers.AddHabitController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;
import main.habitivity.Users.User;
import main.habitivity.profiles.CurrentUser;
import main.habitivity.services.AndroidFileHandler;
import main.habitivity.services.LocalHabitService;

public class LoginUser extends BaseActivity implements Serializable, Parcelable {
    private EditText currentUserName;
    private HabitRepository repo;
    private AddHabitController addHabitController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resolveDependencies();

    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        addHabitController = app.getAddHabitController();
    }

    private void loginUser(User user){
        // after elasticsearch, go to main as that user
        System.out.println("Logging in as: " + user.getUsername());
        System.out.println("JestId: " + user.getId());
        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("jestID", user.getId());
        editor.commit();

        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        intent.putExtra("user", (Serializable) user);
        UserContainer.getInstance().setUser(user);
        if(user.getHabits() != null) {
            addHabitController.setHabits(user.getHabits());
        }
        startActivity(intent);
    }

    private void registerUser(String username){
        User user = new User(username, new ArrayList<Habit>(), new ArrayList<User>());

        ElasticsearchController.AddUsersTask addUsersTask
                = new ElasticsearchController.AddUsersTask();

        addUsersTask.execute(user);
        System.out.println("Successfully added new user");
        loginUser(user);

    }

    public void onClick(View view){
        currentUserName = (EditText) findViewById(R.id.userName);
        CurrentUser.getInstance().setCurrentUser(currentUserName.getText().toString());
        String username = currentUserName.getText().toString();

        String query = "{\n" +
                "    \"query\" : {\n" +
                "        \"term\" : { \"username\" : \"" + username + "\" }\n" +
                "    }\n" +
                "}";

        ElasticsearchController.GetUsersTask getUsersTask
                = new ElasticsearchController.GetUsersTask();
        try {
            getUsersTask.execute(query).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        try {
            ArrayList<User> users = getUsersTask.get();

            System.out.println("size: " + users.size());
            if (username.length() == 0){
                emptyUsernameDialog();
            }
            else if (users.size() == 1){
                System.out.println("\n\nvvv\nalready registered\n^^^\n\n");
                loginUser(users.get(0));
            }
            else if (users.size() > 1){
                Log.i("Error", "Username appears more than once in the database");
                loginUser(users.get(0));
            }
            else{
                registerUser(username);
            }
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the users from the async object");
            Log.i("Error", e.toString());
        }
    }

    public void emptyUsernameDialog() {

        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage("Enter a Username.")
                .setNegativeButton("Return", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setTitle("Empty Username");

        builder.show();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
