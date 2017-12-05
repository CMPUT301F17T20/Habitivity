package main.habitivity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import main.habitivity.activities.LoginUser;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginUserIntentTests {
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
    public void enterUserName() {
        // Type username
        onView(withId(R.id.userName))
                .perform(typeText(username), closeSoftKeyboard());

        // Check that the username is correct
        onView(withId(R.id.userName))
                .check(matches(withText(username)));
    }

}