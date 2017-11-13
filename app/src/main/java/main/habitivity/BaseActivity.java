package main.habitivity;

import android.support.v7.app.AppCompatActivity;

/**
 * BaseActivity exposes the HabitApplication class which can be
 * used to resolve dependencies
 */
public class BaseActivity extends AppCompatActivity {
    public HabitApplication getHabitApplication() {
        return (HabitApplication) getApplication();
    }
}