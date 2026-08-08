package c213.dosaoopproject.Nahin.model.u_03;

import java.io.Serializable;
import java.time.LocalDate;

public class VolFeedback implements Serializable {

    private final int feedbackId;
    private final String activityName;
    private final String rating;
    private final String comments;
    private LocalDate date;

    public VolFeedback(int feedbackId, String activityName, String rating, String comments, LocalDate date) {
        this.feedbackId = feedbackId;
        this.activityName = activityName;
        this.rating = rating;
        this.comments = comments;
        this.date = date;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Custom method
    public boolean validateFeedback() {
        return activityName != null && comments != null;
    }

    @Override
    public String toString() {
        return "VolFeedback{" +
                "feedbackId=" + feedbackId +
                ", activityName='" + activityName + '\'' +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                ", date=" + date +
                '}';
    }
}