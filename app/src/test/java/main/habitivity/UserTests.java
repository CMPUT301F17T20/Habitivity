package main.habitivity;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import main.habitivity.activities.HabitivityMain;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.users.User;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


public class UserTests {
    @Before
    public void set_up() {
    }

    @Test
    public void test_UserName(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        assertEquals(user.getUserName(), "newuser");
    }

    @Test
    public void test_listOfHabitEvents(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        assertEquals(user.getHabitEvents().size(), 0);
    }

    @Test
    public void test_listOfHabits(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        assertEquals(user.getHabits().size(), 0);
    }


    @Test
    public void test_listOfFollowers(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        assertEquals(user.getFollowers().size(), 0);
    }

    @Test
    public void test_listOfFollowing(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        assertEquals(user.getFollowing().size(), 0);
    }

    @Test
    public void test_listOfPotentialFriends(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        user.findAllPotentialFriends();
        //note this test assumes that another user is already created in the elastic serach index
        assertTrue(user.getPotentialFriends().size() !=0);
    }

    @Test
    public void test_testAddHabit(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        Habit habit = new Habit();
        user.addHabit(habit);
        assertEquals(user.getHabits().get(0), habit);
    }

    @Test
    public void test_testUpdateHabit(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        Habit habit = new Habit();
        user.addHabit(habit);
        assertEquals(user.getHabits().get(0), habit);

        habit.setTitle("new title");
        user.updateHabit(user.getHabits().get(0), habit);
        assertEquals(user.getHabits().get(0), habit);

    }

    @Test
    public void test_testDeleteHabit(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        Habit habit = new Habit();
        user.addHabit(habit);
        assertEquals(user.getHabits().get(0), habit);

        user.removeHabit(user.getHabits().get(0));
        assertEquals(user.getHabits().size(), 0);

    }

    @Test
    public void test_testAddHabitEvent(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        HabitEvent habitEvent = new HabitEvent(new Date());
        user.addHabitEvent(habitEvent);
        assertEquals(user.getHabitEvents().get(0), habitEvent);
    }

    @Test
    public void test_testUpdateHabitEvent(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        HabitEvent habitEvent = new HabitEvent(new Date());
        user.addHabitEvent(habitEvent);
        assertEquals(user.getHabitEvents().get(0), habitEvent);

        habitEvent.setComment("new comment");
        user.updateHabitEvent(user.getHabitEvents().get(0), habitEvent);
        assertEquals(user.getHabitEvents().get(0), habitEvent);
    }


    @Test
    public void test_testDeleteHabitEvent(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        HabitEvent habitEvent = new HabitEvent(new Date());
        user.addHabitEvent(habitEvent);
        assertEquals(user.getHabitEvents().get(0), habitEvent);

        user.removeHabitEvent(habitEvent);
        assertEquals(user.getHabitEvents().size(), 0);
    }

    @Test
    public void test_testAddFollower(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        HabitEvent habitEvent = new HabitEvent(new Date());
        user.addFollower("test follower");
        assertEquals(user.getFollowers().get(0), "test follower");
    }

    @Test
    public void test_testAddFollowing(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        HabitEvent habitEvent = new HabitEvent(new Date());
        user.addFollowing("test following");
        assertEquals(user.getFollowing().size(), 1);
    }

    @Test
    public void test_testFollowRequest(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        HabitEvent habitEvent = new HabitEvent(new Date());
        user.addFollowerRequest("testRequest");
        assertEquals(user.getFollowerRequests().get(0), "testRequest");
    }

    @Test
    public void test_testRemoveFollowerRequest(){
        User user = new User("newuser", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        HabitEvent habitEvent = new HabitEvent(new Date());
        user.addFollowerRequest("testRequest");
        assertEquals(user.getFollowers().get(0), "test follower");

        user.removeFollowerRequest(0);
        assertEquals(user.getFollowers().size(), 0);
    }

}
