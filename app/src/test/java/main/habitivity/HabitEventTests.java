package main.habitivity;

import android.media.Image;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Date;

import main.habitivity.habits.HabitEvent;

/**
 * Created by sbergen on 10/23/17.
 */

public class HabitEventTests {

    private String id;
    private Date completionDateInitial;
    private Date completionDateReplacement;
    private String comment;
    private Image photograph;

    private HabitEvent habitEvent;

    @Before
    public void set_up(){
        id = "1234";
        completionDateInitial = new Date();
        completionDateReplacement = new Date();
        comment = "This is a habit event";
        photograph = mock(Image.class);
        habitEvent = new HabitEvent(completionDateInitial);

        habitEvent.setId(id);
        habitEvent.setCompletionDate(completionDateReplacement);
        habitEvent.setComment(comment);
    }

    @Test
    public void test(){
        assertEquals(habitEvent.getId(), id);
        assertEquals(habitEvent.getCompletionDate(), completionDateReplacement);
        assertEquals(habitEvent.getComment(), comment);
        assertEquals(habitEvent.getPhoto(), photograph);
    }
}
