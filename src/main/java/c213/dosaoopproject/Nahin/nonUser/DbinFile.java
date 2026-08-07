package c213.dosaoopproject.Nahin.nonUser;

import c213.dosaoopproject.Nahin.model.u_04.NewClubRegister;

import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.writeFile;

public class DbinFile {
    public static void generateCampaign() {
        ArrayList<CampaignData> campaignData = new ArrayList<>();
        campaignData.add(new CampaignData("Blood Donation Camp", LocalDate.of(2026, 8, 20), "Mirpur", "open"));
        campaignData.add(new CampaignData("Tree Plantation Program", LocalDate.of(2026, 7, 10), "Uttara", "closed"));
        campaignData.add(new CampaignData("Food Distribution Drive", LocalDate.of(2026, 8, 25), "Rajshahi", "open"));
        campaignData.add(new CampaignData("Winter Cloth Donation Campaign", null, "Sylhet", "Upcoming"));
        campaignData.add(new CampaignData("Plastic Free Environment Campaign", LocalDate.of(2026, 9, 12), "Narayanganj", "open"));

        writeFile("campaigns.bin", campaignData);
    }
    public static void generateNewClubRegistration(){
        ArrayList<NewClubRegister> list = new ArrayList<>();

        list.add(new NewClubRegister(1001,"Technology","IUB Robotics & AI Society","Tanvir Ahmed","Promote hands-on robotics & AI projects among students", LocalDate.of(2026,8,1),"018362844638"));
        list.add(new NewClubRegister(1002,"Creative Arts","IUB Photography Circle","Nusrat Jahan","Organize photo walks, exhibition & workshops", LocalDate.of(2026,8,3),"01898462008"));
        list.add(new NewClubRegister(1003,"Recreational","IUB Chess Federation","Rafiul Islam","Host Chess tournaments & improve strategic thinking", LocalDate.of(2026,7,30),"01833456782"));
        list.add(new NewClubRegister(1004,"Business","IUB Entrepreneurship Hub","Sadia Rahman","Support students startup through mentorship & pitch thinking", LocalDate.of(2026,8,10),"01728491107"));
        list.add(new NewClubRegister(1005,"Environment","IUB Wildlife & Environment Club","Farhan Kabir","Raise awareness on conservation and sustainability", LocalDate.of(2026,8,10),"01345627834"));

        writeFile("NewClubApplications.bin",list);
    }
}
