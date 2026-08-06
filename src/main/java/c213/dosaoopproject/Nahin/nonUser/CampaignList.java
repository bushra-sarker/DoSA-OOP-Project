package c213.dosaoopproject.Nahin.nonUser;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class CampaignList {
    public static void createDummyCampaign(){

        ArrayList<CampaignData> campaignData = new ArrayList<>();

        campaignData.add(new CampaignData("Blood Donation Camp", LocalDate.of(2026,8,20),
                "Mirpur", "open"));
        campaignData.add(new CampaignData("Tree Plantation Program", LocalDate.of(2026,7,10),
                "Uttara", "closed"));
        campaignData.add(new CampaignData("Food Distribution Drive", LocalDate.of(2026,8,25),
                "Rajshahi", "open"));
        campaignData.add(new CampaignData("Winter Cloth Donation Campaign", null,
                "Sylhet", "Upcoming"));
        campaignData.add(new CampaignData("Plastic Free Environment Campaign", LocalDate.of(2026,9,12),
                "Narayanganj", "open"));

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("campaigns.bin"))){
            out.writeObject(campaignData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}