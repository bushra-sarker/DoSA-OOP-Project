package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;


public class ClubInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int clubId;
    private String clubName;
    private String category;
    private String moderatorName;
    private int totalMembers;
    private String description;
    private String meetingSchedule;
    private String contactEmail;
    private String contactPhone;
    private String venue;

    public ClubInfo(int clubId, String clubName, String category, String moderatorName,
                    int totalMembers, String description, String meetingSchedule,
                    String contactEmail, String contactPhone, String venue) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.category = category;
        this.moderatorName = moderatorName;
        this.totalMembers = totalMembers;
        this.description = description;
        this.meetingSchedule = meetingSchedule;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.venue = venue;
    }

    public int getClubId() {
        return clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public String getCategory() {
        return category;
    }

    public String getModeratorName() {
        return moderatorName;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public String getDescription() {
        return description;
    }

    public String getMeetingSchedule() {
        return meetingSchedule;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getVenue() {
        return venue;
    }

    /** Apply edited values (called from "Update Club Info"). */
    public void update(String description, String meetingSchedule,
                       String contactEmail, String contactPhone, String venue) {
        this.description = description;
        this.meetingSchedule = meetingSchedule;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.venue = venue;
    }
}
