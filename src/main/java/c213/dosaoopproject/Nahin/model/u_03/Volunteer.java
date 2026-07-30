package c213.dosaoopproject.Nahin.model.u_03;
import c213.dosaoopproject.Nahin.commonClass.User;

public class Volunteer extends User {
    private final String volID;
    private  String email;
    private String phone;

    private double totalContributionHours;
    private int totalParticipation;
    private boolean isCertified;

    public Volunteer(String fullName, int userId, String volID, String email, String phone, double totalContributionHours, int totalParticipation, boolean isCertified) {
        super(fullName, userId);
        this.volID = volID;
        this.email = email;
        this.phone = phone;
        this.totalContributionHours = totalContributionHours;
        this.totalParticipation = totalParticipation;
        this.isCertified = isCertified;
    }

    public String getVolID() {
        return volID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTotalContributionHours() {
        return totalContributionHours;
    }

    public void setTotalContributionHours(double totalContributionHours) {
        this.totalContributionHours = totalContributionHours;
    }

    public int getTotalParticipation() {
        return totalParticipation;
    }

    public void setTotalParticipation(int totalParticipation) {
        this.totalParticipation = totalParticipation;
    }

    public boolean isCertified() {
        return isCertified;
    }

    public void setCertified(boolean certified) {
        isCertified = certified;
    }

    // check if volunteer is active
    public String getStatus(){
        if(isCertified){
            return "Certified";
        } else if (totalContributionHours >80) {
            return "Active";
        }else{
            return "Inactive";
        }
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "volID='" + volID + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", totalContributionHours=" + totalContributionHours +
                ", totalParticipation=" + totalParticipation +
                ", isCertified=" + isCertified +
                ", userId=" + userId +
                ", passwordHash='" + passwordHash + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public void loadDashboard() {
        //
    }
}