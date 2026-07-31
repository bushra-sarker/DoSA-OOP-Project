package c213.dosaoopproject.Nahin;

import c213.dosaoopproject.Nahin.model.u_03.CampaignData;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class CapmpaignList {
    public static void createDummyCampaign(){

        System.out.println("Dummy list created");


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
