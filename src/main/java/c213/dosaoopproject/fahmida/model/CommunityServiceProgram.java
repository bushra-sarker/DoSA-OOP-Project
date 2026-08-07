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
    private String campaignDuration;
    private double amountRaised;
    private String venue;

    public CommunityServiceProgram(int programId, String programName, String type,
                                   String itemsNeeded, String campaignDuration, double amountRaised,
                                   String venue) {
        this.programId = programId;
        this.programName = programName;
        this.type = type;
        this.itemsNeeded = itemsNeeded;
        this.campaignDuration = campaignDuration;
        this.amountRaised = amountRaised;
        this.venue = venue;
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

    public String getCampaignDuration() {
        return campaignDuration;
    }

    public double getAmountRaised() {
        return amountRaised;
    }

    public String getVenue() {
        return venue;
    }

    public String getDetails() {
        return programName + " (" + type + ") — " + campaignDuration;
    }

    }

