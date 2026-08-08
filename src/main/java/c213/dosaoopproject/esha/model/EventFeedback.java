package c213.dosaoopproject.esha.model;

public class EventFeedback {
    private int feedbackId;
    private String userId;
    private String activityName;
    private String rating;
    private String date;
    private String comments;
    private boolean flagged;

    public EventFeedback() {}

    public EventFeedback(int feedbackId, String userId, String activityName,
                         String rating, String date, String comments, boolean flagged) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.activityName = activityName;
        this.rating = rating;
        this.date = date;
        this.comments = comments;
        this.flagged = flagged;
    }

    public int getFeedbackId() { return feedbackId; }
    public void setFeedbackId(int feedbackId) { this.feedbackId = feedbackId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public boolean isFlagged() { return flagged; }
    public void setFlagged(boolean flagged) { this.flagged = flagged; }

    public String getFlaggedDisplay() {
        return flagged ? "Yes" : "No";
    }

    @Override
    public String toString() {
        return "EventFeedback{feedbackId=" + feedbackId + ", activityName='" + activityName + "', rating='" + rating + "', flagged=" + flagged + "}";
    }
}
