package c213.dosaoopproject.esha.model;

import java.time.LocalDate;

public class Notice {
    private int noticeId;
    private String clubName;
    private String title;
    private String body;
    private LocalDate datePosted;

    public Notice() {}

    public Notice(int noticeId, String clubName, String title, String body, LocalDate datePosted) {
        this.noticeId = noticeId;
        this.clubName = clubName;
        this.title = title;
        this.body = body;
        this.datePosted = datePosted;
    }

    public int getNoticeId() { return noticeId; }
    public void setNoticeId(int noticeId) { this.noticeId = noticeId; }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public LocalDate getDatePosted() { return datePosted; }
    public void setDatePosted(LocalDate datePosted) { this.datePosted = datePosted; }

    @Override
    public String toString() {
        return "Notice{noticeId=" + noticeId + ", title='" + title + "'}";
    }
}
