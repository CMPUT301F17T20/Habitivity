/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.Calendar;
import java.util.Date;

import main.habitivity.controllers.AddHabitEventController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitSingletonContainer;


/**TODO MOVE LOCATION HANDLING INTO ANOTHER CLASS*/
public class AddEvent extends BaseActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private TextView eventTitle;
    private Calendar cal = Calendar.getInstance();
    private EditText comment;
    private Habit curHabit;
    private ToggleButton addLocation;
    private Date compDate;
    private Location location = null;
    private Date startingDate = new Date();
    private Button addDate;
    private TextView viewDate;
    // The entry point to Google Play services, used by the Places API and Fused Location Provider.
    private GoogleApiClient mGoogleApiClient;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private AddHabitEventController addHabitEventController;
    private DatePickerDialog.OnDateSetListener addDateSetListen;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                startingDate = compDate;
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

    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        addHabitEventController = app.getAddHabitEventsController();
    }

    public void onAdd(View view){
        Intent intent = new Intent(getApplicationContext(), HabitHistoryActivity.class);
        comment = (EditText) findViewById(R.id.addComment);

        addHabitEventController.addHabitEvent(eventTitle.getText().toString(), comment.getText().toString(), location, compDate);

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
