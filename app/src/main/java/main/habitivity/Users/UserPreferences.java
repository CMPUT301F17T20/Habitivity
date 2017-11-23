package main.habitivity.Users;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Shally on 2017-11-22.
 */

public class UserPreferences implements IUserPreference {
    public static final String USERNAME_KEY = "user_name";
    public static final String USER_KEY = "user";

    private static final String USER_PREFS = UserPreferences.class.getSimpleName();
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor preferencesEditor;

    public UserPreferences(Context context) {
        this.sharedPrefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE);
        this.preferencesEditor = sharedPrefs.edit();
    }

    @Override
    public String getUserName() {
        return sharedPrefs.getString(USERNAME_KEY, "");
    }


    @Override
    public User getUser() {
        String serializedUser = sharedPrefs.getString(USER_KEY, "");

        if (serializedUser != null && !serializedUser.isEmpty()) {
            return new Gson().fromJson(serializedUser, User.class);
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        String serializedUser = new Gson().toJson(user);

        preferencesEditor.putString(USERNAME_KEY, user.getUsername());
        preferencesEditor.putString(USER_KEY, serializedUser);
        preferencesEditor.commit();
    }
}