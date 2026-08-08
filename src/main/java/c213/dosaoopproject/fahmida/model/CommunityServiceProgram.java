package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;


public class CommunityServiceProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    private int programId;
    private String programName;
    private String type;;
    private String campaignDuration;;
    private String venue;

    public CommunityServiceProgram(int programId, String programName, String type,
                                   String itemsNeeded, String campaignDuration, double amountRaised,
                                   String venue) {
        this.programId = programId;
        this.programName = programName;
        this.type = type;
        this.campaignDuration = campaignDuration;
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


    public String getCampaignDuration() {
        return campaignDuration;
    }
    

    public String getVenue() {
        return venue;
    }

    public String getDetails() {
        return programName + " (" + type + ") — " + campaignDuration;
    }

    }

