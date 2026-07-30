package commonClass.data;

import c213.dosaoopproject.Bushra.U07.model.EventProposal;
import java.time.LocalDate;
import java.util.ArrayList;

public class EventDataInitializer {

    public static void initializeEventData() {
        ArrayList<EventProposal> dummyEvents = new ArrayList<>();

        // Matches: (eventId, eventName, clubName, requestedBudget, eventDate, riskLevel, status, interUniversityScope, proposalPdfAttached, budgetSheetAttached)
        dummyEvents.add(new EventProposal(
                "EVT-001", "Tech Carnival 2026", "Computer Club", 75000.0,
                LocalDate.now().plusDays(14), "Medium", "Pending", true, true, true
        ));

        dummyEvents.add(new EventProposal(
                "EVT-002", "Annual Cultural Night", "Cultural Club", 45000.0,
                LocalDate.now().plusDays(30), "Low", "Pending", false, true, false
        ));

        dummyEvents.add(new EventProposal(
                "EVT-003", "Inter-University Hackathon", "Robotics Club", 120000.0,
                LocalDate.now().plusDays(45), "High", "Pending", true, true, true
        ));

        dummyEvents.add(new EventProposal(
                "EVT-004", "Spring Sports Meet", "Sports Club", 30000.0,
                LocalDate.now().plusDays(10), "Low", "Approved", false, true, true
        ));

        // Fixed: Called saveList instead of writeList
        BinaryFileUtil.saveList("events_data.dat", dummyEvents);
        System.out.println("SUCCESS: Initialized dummy event data into events_data.dat");
    }
}