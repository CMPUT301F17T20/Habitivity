package main.habitivity.habits;

import android.media.Image;

import java.util.Date;
import java.util.UUID;

public class HabitEvent {
    private String id;
    private Date completionDate;
    private String comment;
    private Image photograph;

    public HabitEvent(Date completionDate) {

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
    * @param [in] id - id of the HabitEvent
    */
    public void setId(String id) {
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
    * @param [in] comment - comment of the HabitEvent
    */
    public void setComment(String comment) {
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
    * @param [in] completionDate - date that HabitEvent was created
    */
    public void setCompletionDate(Date completionDate) {
    }

    /**
    * Gets the photo associated with the HabitEvent
    *
    * @return photograph - photo associated with the HabitEvent
    */
    public Image getPhoto() {
        return this.photograph;
    }

    /**
    * Sets the photo associated with the HabitEvent
    *
    * @param [in] photograph - photo associated with the HabitEvent
    */
    public void setPhoto(Image photograph) {
    }

}
