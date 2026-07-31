package c213.dosaoopproject.Bushra.U07.util;

import c213.dosaoopproject.Bushra.U07.model.BudgetItem;
import c213.dosaoopproject.Bushra.U07.model.MajorEvent;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventDataSeeder {

    public static void main(String[] args) {
        ArrayList<MajorEvent> sampleEvents = new ArrayList<>();

        // Event 1
        ArrayList<BudgetItem> items1 = new ArrayList<>();
        items1.add(new BudgetItem("Stage & Lighting Setup", 45000.0));
        items1.add(new BudgetItem("Guest Speaker Honorarium", 35000.0));
        items1.add(new BudgetItem("Certificates & Trophies", 40000.0));

        sampleEvents.add(new MajorEvent(
                "National Tech Fest 2026", "IUB CSE Society", 120000.0,
                LocalDate.of(2026, 8, 15), "Auditorium", "Inter-University",
                "HIGH", "Pending", true, true, items1
        ));

        // Event 2
        ArrayList<BudgetItem> items2 = new ArrayList<>();
        items2.add(new BudgetItem("Venue Catering", 40000.0));
        items2.add(new BudgetItem("Printing Banner & Posters", 25000.0));

        sampleEvents.add(new MajorEvent(
                "Inter-University Debate Championship", "IUB Debate Club", 65000.0,
                LocalDate.of(2026, 8, 20), "Multipurpose Hall", "Inter-University",
                "LOW", "Pending", true, false, items2
        ));

        // Event 3
        ArrayList<BudgetItem> items3 = new ArrayList<>();
        items3.add(new BudgetItem("Sound System & Instruments", 80000.0));
        items3.add(new BudgetItem("Security Protocols & Logistics", 70000.0));

        sampleEvents.add(new MajorEvent(
                "Autumn Cultural Night", "IUB Music Club", 150000.0,
                LocalDate.of(2026, 9, 5), "Open Field", "Intra-University",
                "MEDIUM", "Pending", true, true, items3
        ));

        // Save mock data
        boolean success = EventManager.saveEvents(sampleEvents);
        if (success) {
            System.out.println("Data seeding successful! 'data/events_data.dat' created.");
        } else {
            System.err.println("Failed to seed data.");
        }
    }
}