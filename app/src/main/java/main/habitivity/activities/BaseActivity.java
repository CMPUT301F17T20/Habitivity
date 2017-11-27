/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.activities;

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