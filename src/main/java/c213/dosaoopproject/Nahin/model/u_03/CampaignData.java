package c213.dosaoopproject.Nahin.model.u_03;
import java.io.Serializable;
import java.time.LocalDate;

public class CampaignData implements Serializable {
    private String campaignName;
    private LocalDate date;
    private String location;
    private String status;

    public CampaignData(String campaignName, LocalDate date, String location, String status) {
        this.campaignName = campaignName;
        this.date = date;
        this.location = location;
        this.status = status;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CampaignData{" +
                "campaignName='" + campaignName + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
