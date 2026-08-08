package c213.dosaoopproject.Nahin.model.u_03;
import c213.dosaoopproject.commonClass.model.User;
import c213.dosaoopproject.commonClass.util.SessionManager;

import java.util.ArrayList;

public class Volunteer extends User {
    private final String email;
    private String phone;
    private double totalContributionHours;
    private int totalParticipation;
    private boolean isCertified;

    private ArrayList<TeamJoin> teamRequests;
    private ArrayList<ClubEventRegister> clubEventRegister;
    private ArrayList<CampaignRegister> campaignRegistration;
    private ArrayList<ReportConcerns> reportIssues;


    public Volunteer(String userId, String password, String fxmlPath, String email, String phone, double totalContributionHours, int totalParticipation, boolean isCertified) {
        super(userId, password, fxmlPath);
        this.email = email;
        this.phone = phone;
        this.totalContributionHours = totalContributionHours;
        this.totalParticipation = totalParticipation;
        this.isCertified = isCertified;

        teamRequests = new ArrayList<>();
        clubEventRegister = new ArrayList<>();
        campaignRegistration = new ArrayList<>();
        reportIssues = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    //can update profile (phone number)
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTotalContributionHours() {
        return totalContributionHours;
    }

    public int getTotalParticipation() {
        return totalParticipation;
    }

    public boolean isCertified() {return isCertified;}


    public ArrayList<TeamJoin> getTeamRequests(){
        return teamRequests;
    }
    public ArrayList<ClubEventRegister> getClubEventRegister(){
        return clubEventRegister;
    }
    public ArrayList<CampaignRegister> getCampaignRegistration(){
        return campaignRegistration;
    }
    public ArrayList<ReportConcerns> getReportIssues(){
        return reportIssues;
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
}