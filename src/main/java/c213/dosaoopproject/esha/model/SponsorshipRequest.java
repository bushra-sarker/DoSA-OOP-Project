package c213.dosaoopproject.esha.model;

public class SponsorshipRequest {
    private int sponsorshipId;
    private int executiveId;
    private String eventName;
    private String sponsorshipType;
    private String sponsorOrgName;
    private String expectedSupport;
    private String status; // "Pending", "Approved", "Rejected"

    public SponsorshipRequest() {}

    public SponsorshipRequest(int sponsorshipId, int executiveId, String eventName,
                              String sponsorshipType, String sponsorOrgName,
                              String expectedSupport, String status) {
        this.sponsorshipId = sponsorshipId;
        this.executiveId = executiveId;
        this.eventName = eventName;
        this.sponsorshipType = sponsorshipType;
        this.sponsorOrgName = sponsorOrgName;
        this.expectedSupport = expectedSupport;
        this.status = status;
    }

    public int getSponsorshipId() { return sponsorshipId; }
    public void setSponsorshipId(int sponsorshipId) { this.sponsorshipId = sponsorshipId; }

    public int getExecutiveId() { return executiveId; }
    public void setExecutiveId(int executiveId) { this.executiveId = executiveId; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getSponsorshipType() { return sponsorshipType; }
    public void setSponsorshipType(String sponsorshipType) { this.sponsorshipType = sponsorshipType; }

    public String getSponsorOrgName() { return sponsorOrgName; }
    public void setSponsorOrgName(String sponsorOrgName) { this.sponsorOrgName = sponsorOrgName; }

    public String getExpectedSupport() { return expectedSupport; }
    public void setExpectedSupport(String expectedSupport) { this.expectedSupport = expectedSupport; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "SponsorshipRequest{sponsorshipId=" + sponsorshipId + ", eventName='" + eventName + "', status='" + status + "'}";
    }
}
