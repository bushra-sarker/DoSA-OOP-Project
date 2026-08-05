package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;

/**
 * A community-service program a student can enrol in as a volunteer.
 */
public class CommunityServiceProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    private int programId;
    private String programName;
    private String type;
    private String itemsNeeded;
    private int volunteerSlotsAvailable;
    private String campaignDuration;
    private double amountRaised;

    public CommunityServiceProgram(int programId, String programName, String type,
                                   String itemsNeeded, int volunteerSlotsAvailable,
                                   String campaignDuration, double amountRaised) {
        this.programId = programId;
        this.programName = programName;
        this.type = type;
        this.itemsNeeded = itemsNeeded;
        this.volunteerSlotsAvailable = volunteerSlotsAvailable;
        this.campaignDuration = campaignDuration;
        this.amountRaised = amountRaised;
    }

    public int getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }

    public String getType() {
        return type;
    }

    public String getItemsNeeded() {
        return itemsNeeded;
    }

    public int getVolunteerSlotsAvailable() {
        return volunteerSlotsAvailable;
    }

    public String getCampaignDuration() {
        return campaignDuration;
    }

    public double getAmountRaised() {
        return amountRaised;
    }

    public String getDetails() {
        return programName + " (" + type + ") — " + campaignDuration
                + ", slots left: " + volunteerSlotsAvailable;
    }

    /** Enrol a student as a volunteer; returns false when no slots remain. */
    public boolean registerAsVolunteer(int studentId) {
        if (volunteerSlotsAvailable <= 0) {
            return false;
        }
        volunteerSlotsAvailable--;
        return true;
    }
}
