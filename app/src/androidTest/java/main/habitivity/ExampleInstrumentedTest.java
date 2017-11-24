package main.habitivity;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import main.habitivity.habits.HabitRepository;
import main.habitivity.services.AndroidFileHandler;
import main.habitivity.services.LocalHabitService;
import main.habitivity.Users.User;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends Application {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("main.habitivity", appContext.getPackageName());
    }

    @Test
    public void testAdd(){
        AndroidFileHandler fileHandler = new AndroidFileHandler(this);
        LocalHabitService habitService = new LocalHabitService(fileHandler);
        HabitRepository habitRepository = new HabitRepository(habitService);
        User user = new User("fake name", habitRepository, new ArrayList<User>());
        ElasticsearchController.AddUsersTask addUsersTask
                = new ElasticsearchController.AddUsersTask();
        try {
            addUsersTask.execute(user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
