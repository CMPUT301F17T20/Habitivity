/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import main.habitivity.R;
import main.habitivity.controllers.AddHabitEventController;
import main.habitivity.controllers.AllUsersController;
import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.habits.HabitEvent;
import main.habitivity.users.UserContainer;
import main.habitivity.controllers.AddHabitController;
import main.habitivity.habits.Habit;
import main.habitivity.users.User;
import main.habitivity.profiles.CurrentUser;


@SuppressLint("ParcelCreator")
public class LoginUser extends BaseActivity implements Serializable, Parcelable {
    private EditText currentUserName;
    private AddHabitController addHabitController;
    private AddHabitEventController addHabitEventController;
    private ArrayList<User> allUsers = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resolveDependencies();
        allUsers = UserContainer.getInstance().getAllUsers();

    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        addHabitController = app.getAddHabitController();
        addHabitEventController = app.getAddHabitEventsController();
    }

    private void loginUser(User user){
        // after elasticsearch, go to main as that user

        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        UserContainer.getInstance().setUser(user);

        String currentUserName =  UserContainer.getInstance().getUser().getUserName();
        for(User users: allUsers){
            if(users.getUserName().equals(currentUserName)){
                allUsers.remove(users);
                break;
            }
        }
         UserContainer.getInstance().setAllUsersExcludingUser(allUsers);

        if(user.getHabits().size() != 0) {
            //for all habits add missed days while offline to habits;
            user.updateOfflineDays();
            addHabitController.setHabits(user.getHabits());
        }
        if(user.getHabitEvents().size() != 0){
            addHabitEventController.setHabitEvents(user.getHabitEvents());

        }
        startActivity(intent);
        finish();
    }

    private void registerUser(String username){
        User user = new User(username, new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());

        ElasticsearchController.AddUsersTask addUsersTask
                = new ElasticsearchController.AddUsersTask();

        addUsersTask.execute(user);
        System.out.println("Successfully added new user");
        loginUser(user);

    }

    public void onClick(View view){
        currentUserName = (EditText) findViewById(R.id.userName);
        if(currentUserName == null){
            emptyUsernameDialog();
        }
        CurrentUser.getInstance().setCurrentUser(currentUserName.getText().toString());
        String username = currentUserName.getText().toString();

        UserContainer.getInstance().setAllUsersExcludingUser(allUsers);
        Boolean found = true;

        for(User user: UserContainer.getInstance().getAllUsers()){
            if(user.getUserName().equals(username)){
                loginUser(user);
                found = false;
                break;
            }
        }
        if(found) {
            registerUser(username);
        }

        String query = "{\n" +
                "    \"query\" : {\n" +
                "        \"term\" : { \"username\" : \"" + username + "\" }\n" +
                "    }\n" +
                "}";

        // ElasticsearchController.GetAllUsersWithUserNameTask getAllUsersWithUserNameTask
        //         = new ElasticsearchController.GetAllUsersWithUserNameTask();


        // try {
        //     ArrayList<User> users = getAllUsersWithUserNameTask.execute(query).get();;

        //     System.out.println("size: " + users.size());
        //     if (username.length() == 0){
        //         emptyUsernameDialog();
        //     }
        //     //we found the user login them in
        //     else if (users.size() == 1){
        //         loginUser(users.get(0));
        //     }
        //     else if (users.size() > 1){
        //         Log.i("Error", "Username appears more than once in the server");
        //         loginUser(users.get(0));
        //     }
        //     else{
        //         registerUser(username);
        //     }
        // }
        // catch (Exception e) {
        //     Log.i("Error", "Failed to get the users from the async object");
        //     Log.i("Error", e.toString());
        // }
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