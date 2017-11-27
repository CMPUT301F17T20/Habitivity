package main.habitivity.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * Created by Shally on 2017-11-25.
 */

public class UpdateHabitEventRequest {
    private String id;
    private String oldId;
    private Date completionDate;
    private String comment;
    private transient Bitmap photograph;
    private Location location;

    /**
     * Gets the geo location of the event
     * @return - The location of the event
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     * Sets the geolocation of the event
     */
    public void setLocation(Location location){
        this.location = location;
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

}
