package main.habitivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.Image;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Date;

import main.habitivity.exceptions.ImageTooLargeException;
import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.Clock;


public class HabitEventTests {

    @Before
    public void set_up(){
    }

    @Test
    public void test_habitId(){
        HabitEvent habitEvent = new HabitEvent(new Date());
        habitEvent.setId("test id");
        assertEquals(habitEvent.getId(), "test id");
    }

    @Test
    public void test_habitEventComment(){
        HabitEvent habitEvent = new HabitEvent(new Date());
        habitEvent.setComment("comment");
        assertEquals(habitEvent.getComment(), "comment");
    }

    @Test
    public void test_completionDate(){
        Clock time = new Clock();
        HabitEvent habitEvent = new HabitEvent(time.getCurrentDate());
        habitEvent.setCompletionDate(time.getCurrentDate());
        assertEquals(habitEvent.getCompletionDate(), time.getCurrentDate());
    }

    @Test
    public void test_location(){
        Location loc = new Location("dummy");
        loc.setLatitude(20.3);
        loc.setLongitude(52.6);
        HabitEvent habitEvent = new HabitEvent(new Date());
        habitEvent.setLocation(loc);
        assertEquals(habitEvent.getLocation(), loc);
    }

    @Test
    public void test_Photo(){
        Bitmap dummyPhoto = Bitmap.createBitmap(100, 200, Bitmap.Config.ARGB_8888);
        HabitEvent habitEvent = new HabitEvent(new Date());
        try {
            habitEvent.setPhoto(dummyPhoto);
        } catch (ImageTooLargeException e) {
            e.printStackTrace();
        }
        assertEquals(habitEvent.getPhoto(), dummyPhoto);
    }

}
