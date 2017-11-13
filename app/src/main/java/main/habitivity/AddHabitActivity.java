package main.habitivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class AddHabitActivity extends AppCompatActivity {

    private Button addDate;
    private TextView viewDate;
    private Date compDate;
    private Calendar cal = Calendar.getInstance();

    private DatePickerDialog.OnDateSetListener addDateSetListen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addDate = (Button) findViewById(R.id.chooseDate);
        viewDate = (TextView) findViewById(R.id.dateChoice);

        int yr = cal.get(Calendar.YEAR);
        int mth = cal.get(Calendar.MONTH);
        int dy = cal.get(Calendar.DAY_OF_MONTH);
        mth = mth + 1;
        String showDate = mth + "/" + dy + "/" + yr;
        viewDate.setText(showDate);

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int yr = cal.get(Calendar.YEAR);
                int mth = cal.get(Calendar.MONTH);
                int dy = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dia = new DatePickerDialog(
                        AddHabitActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        addDateSetListen,
                        yr, mth, dy);
                dia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dia.show();
            }
        });

        addDateSetListen = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                cal.set(year, month, day);
                compDate = cal.getTime();
                month = month + 1;
                String showDate = month + "/" + day + "/" + year;
                viewDate.setText(showDate);
            }
        }

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
