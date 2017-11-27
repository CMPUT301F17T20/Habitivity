/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.habits;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.Image;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.UUID;

/**
 * Habit Event Model Class
 */
public class HabitEvent {
    private String id;
    private Date completionDate;
    private String comment;
    private transient Bitmap photograph;
    private Location location;
    private String thumbnail;

    public HabitEvent(Date completionDate) {
        /* This path is going to need testing. I couldn't find the assumed source file online so I
        had to take a blind guess.*/
        photograph = BitmapFactory.decodeFile("main\\res\\drawable\\habit_event_default.png");
    }

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
        if (this.photograph == null && thumbnail == null) {
            return null;
            //image isn't set. //implement later
        } else {
            if (thumbnail != null) {
                byte[] decodeString = Base64.decode(thumbnail, Base64.DEFAULT);
                this.photograph = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
            }
        }
        return this.photograph;
    }

    /**
    * Sets the photo associated with the HabitEvent
    *
    * @param[in] photograph - photo associated with the HabitEvent
    */
    public void setPhoto(Bitmap photograph) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        if (photograph == null) {
            this.photograph = null;
            this.thumbnail = null;
            return;
        }
//        if (photograph.getByteCount() >= 65536) {
//            //throw an exception todo later (create an exception class)
//        }
        else {
            this.photograph = photograph;
            this.photograph.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

            byte[] bytes = byteArrayOutputStream.toByteArray();
            this.thumbnail = Base64.encodeToString(bytes, Base64.DEFAULT);
        }
    }
    
    /**
    * Sets the photo associated with the HabitEvent
    *
    * @param[in] path - a path to a photo associated with the HabitEvent
    */
    public void setPhoto(String path) {

        this.photograph = BitmapFactory.decodeFile(path);
    }


    public static Bitmap compressBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap, 127, 127, true);

    }

    public static Bitmap decompressBitmap(Bitmap scaledBitmap) {
        return Bitmap.createScaledBitmap(scaledBitmap, 256, 256, true);
    }

    @Override
    /**
     * ArrayAdapter calls to string on the object and displays it on the textview. Override to display what we want
     */
    public String toString(){
        return "Id: " + this.getId() + "|    Comment: " + this.getComment();

    }

}
