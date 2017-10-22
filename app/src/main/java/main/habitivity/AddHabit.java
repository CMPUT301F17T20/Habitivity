package main.habitivity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddHabit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         *
         *
         * ToDo: implementation of data transfer between the client and server.
         *
         *
         */

        /**
         * ToDo: Add actual implementation of data inputs.
         * Below was only a placeholder.
         */

        /*
        ContentValues values = new ContentValues();
        values.put(HabitsProvider.NAME,
                ((EditText)findViewById(R.id.habit_name)).getText().toString());

        values.put(HabitsProvider.INFOCOLUMNPLACEHOLDER2,
                ((EditText)findViewById(R.id.habit_info2)).getText().toString());

        values.put(HabitsProvider.INFOCOLUMNPLACEHOLDER3,
                ((EditText)findViewById(R.id.habit_info3)).getText().toString());

        values.put(HabitsProvider.INFOCOLUMNPLACEHOLDER4,
                ((EditText)findViewById(R.id.habit_info4)).getText().toString());

        Uri uri = getContentResolver().insert(
                HabitsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();*/
    }

}
