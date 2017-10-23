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

    public String getId() {
        return id;
    }

    public void setId(String id) {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
    }

    public Image getPhoto() {
        return photograph;
    }

    public void setPhoto(Image photograph) {
    }

}
