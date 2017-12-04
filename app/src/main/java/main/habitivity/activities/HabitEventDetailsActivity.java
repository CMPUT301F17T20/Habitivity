/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import main.habitivity.R;
import main.habitivity.controllers.HabitListController;
import main.habitivity.controllers.UpdateHabitController;
import main.habitivity.controllers.UpdateHabitEventRequest;
import main.habitivity.controllers.UpdateHabitRequest;
import main.habitivity.exceptions.ImageTooLargeException;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitSingletonContainer;
import main.habitivity.users.UserContainer;

public class HabitEventDetailsActivity extends BaseActivity {

    private static final int SELECTED_PICTURE = 0;
    private DatePickerDialog.OnDateSetListener addDateSetListen;
    private Button addDate;
    private TextView viewDate;
    private TextView habitEventTitle;
    private TextView locationText;
    private TextView comment;
    private HabitEvent curHabitEvent;
    private Calendar cal = Calendar.getInstance();
    private Date compDate;

    private static final int CAMERA_REQUEST = 1888;
    private ImageView userImage;
    private Bitmap bitmap;

    private UpdateHabitController updateHabitController;
    private HabitListController habitListController;

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_event_details_new);
        resolveDependencies();

        habitEventTitle = (TextView) findViewById(R.id.habitEvent);
        comment = (TextView) findViewById(R.id.addComment);
        userImage = (ImageView) findViewById(R.id.userImage);
        locationText = (TextView) findViewById(R.id.location);


        curHabitEvent = HabitSingletonContainer.getInstance().getHabitEvent();
        Location loc = curHabitEvent.getLocation();

        String commentString = curHabitEvent.getComment();
        comment.setText(commentString);
        habitEventTitle.setText(curHabitEvent.getId());
        compDate = curHabitEvent.getCompletionDate();

        if (loc != null) {
            locationText.setText(String.format(Locale.CANADA, "Latitude: %.2f  Longitude: %.2f", loc.getLatitude(), loc.getLongitude()));
        }

        addDate = (Button) findViewById(R.id.chooseDate);
        viewDate = (TextView) findViewById(R.id.dateChoice);

        viewDate.setText(curHabitEvent.getCompletionDate().toString());

        if(curHabitEvent.getPhoto() != null){
            userImage.setImageBitmap(curHabitEvent.getPhoto());
        }

        Calendar setDate = toCalendar(curHabitEvent.getCompletionDate());
        int yr = setDate.get(Calendar.YEAR);
        int mth = setDate.get(Calendar.MONTH);
        int dy = setDate.get(Calendar.DAY_OF_MONTH);

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
                        HabitEventDetailsActivity.this,
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
                int yr = cal.get(Calendar.YEAR);
                int mth = cal.get(Calendar.MONTH);
                int dy = cal.get(Calendar.DAY_OF_MONTH);
                compDate = cal.getTime();
                month = month + 1;
                String showDate = month + "/" + day + "/" + year;
                viewDate.setText(showDate);
            }
        };

        Button photoButton = (Button) this.findViewById(R.id.addPhoto);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra( android.provider.MediaStore.EXTRA_SIZE_LIMIT, "65000");
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            userImage.setImageBitmap(photo);
        }
        if (requestCode == SELECTED_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri photouri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photouri);
                setImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void pickPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECTED_PICTURE);
    }

    public void imageTooLargeDialog() {

        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage("Image Is Too Large!")
                .setNegativeButton("Return", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setTitle("Image Is Too Large!");

        builder.show();
    }

    private void setImage(Bitmap photo) {
        if(photo.getByteCount() >= 65536){
            this.imageTooLargeDialog();
        }
        else {
            userImage.setImageBitmap(photo);
        }
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        updateHabitController = app.getUpdateHabitController();
        habitListController = app.getHabitListController();
    }

    public void onEdit(View view){
        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        habitEventTitle = (TextView) findViewById(R.id.habitEvent);
        comment = (TextView) findViewById(R.id.addComment);

        for(HabitEvent event: UserContainer.getInstance().getUser().getHabitEvents()) {
            if (event.checkIfCompletionDay(compDate) && !curHabitEvent.checkIfCompletionDay(compDate) && (event.getId().equals(habitEventTitle.getText().toString()))) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage("An event for that date already exists! Pick another date")
                        .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setTitle("Duplicate Date");
                builder.show();
                return;
            }
        }

        UpdateHabitEventRequest updateHabitEventRequest = new UpdateHabitEventRequest();
        updateHabitEventRequest.setLocation(curHabitEvent.getLocation());
        updateHabitEventRequest.setPhoto(bitmap);
        updateHabitEventRequest.setOldHabitEvent(curHabitEvent);
        updateHabitEventRequest.setCompletionDate(compDate);
        updateHabitEventRequest.setId(habitEventTitle.getText().toString());
        updateHabitEventRequest.setComment(comment.getText().toString());

        try {
            updateHabitController.updateHabitEvent(updateHabitEventRequest);
        } catch (ImageTooLargeException e) {
            e.printStackTrace();
        }
        //HabitSingletonContainer.getInstance().setHabitEvent(habitEvent);
        startActivity(intent);
    }

    public void onDelete(View view){
        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        habitListController.removeHabitEvent(HabitSingletonContainer.getInstance().getHabitEvent());
        startActivity(intent);
    }
}

