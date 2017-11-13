package main.habitivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.habitivity.controllers.AddHabitController;
import main.habitivity.controllers.AddHabitRequest;


public class AddHabitActivity extends BaseActivity {

    private Button addDate;
    private TextView viewDate;
    private Date compDate;
    private Calendar cal = Calendar.getInstance();
    private EditText title;
    private Date startingDate;
    private AddHabitController addHabitController;
    private EditText reason;

    private DatePickerDialog.OnDateSetListener addDateSetListen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        resolveDependencies();

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
                startingDate = compDate;
                month = month + 1;
                String showDate = month + "/" + day + "/" + year;
                viewDate.setText(showDate);
            }
        };
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        addHabitController = app.getAddHabitController();
    }

    public void onAdd(View view) {
        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        title = (EditText) findViewById(R.id.habitInput);
        reason = (EditText) findViewById(R.id.addComment);
        //faking out the data for now
        List<Integer> testList = new ArrayList<Integer>();
        testList.add(1);
        AddHabitRequest addHabitRequest = new AddHabitRequest();
        addHabitRequest.setId(title.getText().toString());
        addHabitRequest.setStartDate(startingDate);
        addHabitRequest.setDaysOfTheWeek(testList);

        addHabitController.addHabit(addHabitRequest);
        startActivity(intent);
    }

}
