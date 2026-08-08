package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;
import java.time.LocalDate;


public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    private int noticeId;
    private String clubName;
    private String title;
    private String body;
    private String category;      // e.g. "Event" or "Club Activity"
    private LocalDate datePosted;

    public Notice(int noticeId, String clubName, String title, String body,
                  String category, LocalDate datePosted) {
        this.noticeId = noticeId;
        this.clubName = clubName;
        this.title = title;
        this.body = body;
        this.category = category;
        this.datePosted = datePosted;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public String getClubName() {
        return clubName;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    /** Full notice text shown on the "Read More" details page. */
    public String getFullNotice() {
        return title + "\n\nPosted by: " + clubName + "\nDate: " + datePosted
                + "\nCategory: " + category + "\n\n" + body;
    }
}
