package main.habitivity;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.robotium.solo.Solo;

public class ActivityTests extends ActivityInstrumentationTestCase2<HabitivityMain>{

    private Solo solo;

    public ActivityTests() {
        super(main.habitivity.HabitivityMain.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        Log.d("SETUP", "setUp()");
    }


    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    //add activity tests here

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
