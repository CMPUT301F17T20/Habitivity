/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import main.habitivity.R;
import main.habitivity.controllers.AddHabitEventController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitSingletonContainer;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;


/**TODO MOVE LOCATION HANDLING INTO ANOTHER CLASS*/
public class AddEvent extends BaseActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private static final int SELECTED_PICTURE = 0;
    private static String TAG = "JustHabitDetails";//testing
    private TextView eventTitle;
    private Bitmap bitmap = null;
    private Calendar cal = Calendar.getInstance();
    private EditText comment;
    private Habit curHabit;
    private ToggleButton addLocation;
    private String titleID;
    private Date compDate;
    private Location location = null;
    private Date startingDate = new Date();
    private Button addDate;
    private TextView viewDate;
    private static final int CAMERA_REQUEST = 1888; 
    private ImageView userImage;
    private Boolean onSched;
    // The entry point to Google Play services, used by the Places API and Fused Location Provider.
    private GoogleApiClient mGoogleApiClient;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private AddHabitEventController addHabitEventController;
    private DatePickerDialog.OnDateSetListener addDateSetListen;

    public User currentlylogged;
    private String JestId;

    public Location getCurrentLocation(){

        Boolean mLocationPermissionGranted = false;
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        if (mLocationPermissionGranted) {
            this.location = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
        }

        return this.location;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        userImage = (ImageView) findViewById(R.id.userImage);


        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        String jestID = settings.getString("jestID", "defaultvalue");

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        currentlylogged = UserContainer.getUserObject(jestID);

        // Use the addApi() method to request the Google Places API and the Fused Location Provider.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();



        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resolveDependencies();

        curHabit = HabitSingletonContainer.getInstance().getHabit();
        eventTitle = (TextView) findViewById(R.id.habitEvent);
        eventTitle.setText(curHabit.getTitle());
        addLocation = (ToggleButton) findViewById(R.id.location);

        addDate = (Button) findViewById(R.id.chooseDate);
        viewDate = (TextView) findViewById(R.id.dateChoice);

        int yr = cal.get(Calendar.YEAR);
        int mth = cal.get(Calendar.MONTH);
        int dy = cal.get(Calendar.DAY_OF_MONTH);
        mth = mth + 1;
        String showDate = mth + "/" + dy + "/" + yr;
        viewDate.setText(showDate);
        compDate = cal.getTime();

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int yr = cal.get(Calendar.YEAR);
                int mth = cal.get(Calendar.MONTH);
                int dy = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dia = new DatePickerDialog(
                        AddEvent.this,
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
        };

        addLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    location = getCurrentLocation();
                }
                else{
                    location = null;
                }
            }
        });


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
            bitmap = photo;
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
        addHabitEventController = app.getAddHabitEventsController();
    }

    /**
     * Set the habit event, but iterates over existing habit events.
     * If completion date is the same as in event the user cannot create that event
     * If completion day is today, the it was scheduled,
     * the onSchedCount for the current habit increased and the event is onSched.
     *
     * @author Nicolas Parada
     * @version 1.0
     * @since 1.0
     * @param view
     *
     */
    public void onAdd(View view){
        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        comment = (EditText) findViewById(R.id.addComment);
        titleID = eventTitle.getText().toString();
        Calendar todayCal = Calendar.getInstance();
        int compWDay = cal.get(Calendar.DAY_OF_WEEK);
        todayCal.setTime(new Date());

        for(HabitEvent event: UserContainer.getInstance().getUser().getHabitEvents()) {
            if (event.checkIfCompletionDay(compDate) && (event.getId().equals(titleID))) {
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

        onSched = false;
        boolean sameDay = cal.get(Calendar.YEAR) == todayCal.get(Calendar.YEAR) &&
                cal.get(Calendar.DAY_OF_YEAR) == todayCal.get(Calendar.DAY_OF_YEAR);
        if (curHabit.afterStartDate(compDate) && curHabit.checkDay(compWDay) && sameDay){
            onSched = true;
            Log.d(TAG, "OnSchedule"); //Checking
            curHabit.incrementOnSchedCount();
        }
        Log.d(TAG, "NOTOnSchedule" + curHabit.afterStartDate(compDate) + curHabit.checkDay(compWDay) + sameDay + todayCal.DAY_OF_WEEK + cal.DAY_OF_WEEK + compWDay + curHabit.getDaysOfTheWeekToComplete()); //Checking
        curHabit.incrementTimesCompleted();
        addHabitEventController.addHabitEvent(titleID, comment.getText().toString(), location, compDate, bitmap, onSched);

        startActivity(intent);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
