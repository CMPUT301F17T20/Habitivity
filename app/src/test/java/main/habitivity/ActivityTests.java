package main.habitivity;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.robotium.solo.Solo;

import main.habitivity.activities.HabitivityMain;
import main.habitivity.activities.LoginUser;

public class ActivityTests extends ActivityInstrumentationTestCase2<LoginUser>{

    private Solo solo;

    public ActivityTests() {
        super(LoginUser.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        Log.d("SETUP", "setUp()");
    }


    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testLogin() {
        solo.assertCurrentActivity("wrong activity", LoginUser.class);
        solo.enterText((EditText) solo.getView(R.id.userName), "Test User");

        solo.clickOnButton("OKAY");

        assertTrue(solo.waitForText("Test User"));

        solo.clickOnActionBarHomeButton();
        solo.clickOnActionBarItem(1);
        solo.goBack();

        solo.clickOnActionBarItem(2);
        solo.goBack();

        solo.clickOnActionBarItem(3);
        solo.goBack();
        solo.goBack();

        solo.clickOnActionBarHomeButton();
        solo.clickOnActionBarItem(0);

        solo.enterText((EditText) solo.getView(R.id.habitInput), "Test Habit");
        solo.enterText((EditText) solo.getView(R.id.addComment), "For Testing Purposes Only");
        solo.clickOnButton("SUN");
        solo.clickOnButton("MON");
        solo.clickOnButton("ADD HABIT");
        solo.goBack();

        assertTrue(solo.waitForText("Test User"));
        solo.assertCurrentActivity("wrong activity", HabitivityMain.class);
    }

    //add activity tests here

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
