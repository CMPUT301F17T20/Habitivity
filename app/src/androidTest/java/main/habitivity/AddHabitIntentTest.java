package main.habitivity;

import android.provider.ContactsContract;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import main.habitivity.activities.HabitivityMain;
import main.habitivity.activities.LoginUser;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AddHabitIntentTest {
    String username;
    @Rule
    public ActivityTestRule<LoginUser> mActivityRule = new ActivityTestRule<>(
            LoginUser.class);
    private LoginUser loginActivity;

    @Before
    public void initUsername() {
        username = "intent";
        loginActivity = mActivityRule.getActivity();
    }


    @Test
    public void addHabitActivity() {
        //Navigate to MainActivity
        onView(withId(R.id.userName))
                .perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.button3)).perform(click());

        onView(withId(R.id.add_habit)).check(matches(withText("Add Habit")));
        onView(withId(R.id.add_habit)).perform(click());

        onView(withId(R.id.delete2)).check(matches(withText("Add Habit")));
        onView(withId(R.id.colorButton)).check(matches(withText("Habit Type")));
        onView(withId(R.id.chooseDate)).check(matches(withText("Select Date")));

    }

}