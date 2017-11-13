package main.habitivity.habits;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import java.util.Date;
import java.util.UUID;

public class HabitEvent {
    private String id;
    private Date completionDate;
    private String comment;
    private Bitmap photograph;

    public HabitEvent(Date completionDate) {
        /* This path is going to need testing. I couldn't find the assumed source file online so I
        had to take a blind guess.*/
        photograph = BitmapFactory.decodeFile("main\\res\\drawable\\habit_event_default.png");
    }

    /**
    * Gets the id of the HabitEvent
    *
    * @return id - id of the HabitEvent
    */
    public String getId() {
        return this.id;
    }

    /**
    * Sets the id of the HabitEvent
    *
    * @param[in] id - id of the HabitEvent
    */
    public void setId(String id) {
        this.id = id;
    }

    /**
    * Gets the comment of the HabitEvent
    *
    * @return comment - comment of the HabitEvent
    */
    public String getComment() {
        return this.comment;
    }

    /**
    * Sets the comment of the HabitEvent
    *
    * @param[in] comment - comment of the HabitEvent
    */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
    * Gets the completion date of the HabitEvent (i.e the date the habit was completed and became a "HabitEvent")
    *
    * @return completionDate - date that HabitEvent was created
    */
    public Date getCompletionDate() {
        return this.completionDate;
    }

    /**
    * Sets the completion date of the HabitEvent (i.e the date the habit was completed and became a "HabitEvent")
    *
    * @param[in] completionDate - date that HabitEvent was created
    */
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    /**
    * Gets the photo associated with the HabitEvent
    *
    * @return photograph - photo associated with the HabitEvent
    */
    public Bitmap getPhoto() {
        return this.photograph;
    }

    /**
    * Sets the photo associated with the HabitEvent
    *
    * @param[in] photograph - photo associated with the HabitEvent
    */
    public void setPhoto(Bitmap photograph) {
        this.photograph = photograph;
    }
    
    /**
    * Sets the photo associated with the HabitEvent
    *
    * @param[in] path - a path to a photo associated with the HabitEvent
    */
    public void setPhoto(String path) {
        this.photograph = BitmapFactory.decodeFile(path);
    }

}
