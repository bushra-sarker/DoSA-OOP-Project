package c213.dosaoopproject.Bushra.U07.util;

import c213.dosaoopproject.Bushra.U07.model.BudgetItem;
import c213.dosaoopproject.Bushra.U07.model.Event;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static final String DATA_FILE = "data/events_data.dat";

    // 1. Load all events (Generates mock data if file doesn't exist)
    public static List<Event> loadEvents() {
        List<Event> events = BinaryFileUtil.readObjects(DATA_FILE);
        if (events == null || events.isEmpty()) {
            events = createMockEvents();
            saveEvents(events);
        }
        return events;
    }

    // 2. Save all events to binary file
    public static boolean saveEvents(List<Event> events) {
        try {
            BinaryFileUtil.writeObjects(DATA_FILE, events);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Helper to fetch pending events
    public static List<Event> getPendingEvents() {
        List<Event> all = loadEvents();
        List<Event> filtered = new ArrayList<>();

        for (Event e : all) {
            if ("Pending".equalsIgnoreCase(e.getStatus())) {
                filtered.add(e);
            }
        }
        return filtered;
    }

    // 4. Update a single event record and save to file
    public static boolean updateEvent(Event updatedEvent) {
        List<Event> events = loadEvents();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventId().equalsIgnoreCase(updatedEvent.getEventId())) {
                events.set(i, updatedEvent);
                return saveEvents(events);
            }
        }
        return false;
    }

    // 5. Mock Data creation using correct Event constructor parameters
    private static List<Event> createMockEvents() {
        List<Event> list = new ArrayList<>();

        // Event 1
        Event e1 = new Event("EVT-101", "National Tech Fest 2026", "Computer Club",
                "2026-05-20", 150000.0, "High", "Pending", "Auditorium Main Hall");
        e1.getBudgetItems().add(new BudgetItem("Venue Setup & AV", "60000"));
        e1.getBudgetItems().add(new BudgetItem("Guest Honorarium", "40000"));
        e1.getBudgetItems().add(new BudgetItem("Prize Pool & Catering", "50000"));

        // Event 2
        Event e2 = new Event("EVT-102", "Inter-Dept Cultural Gala", "Cultural Club",
                "2026-06-15", 85000.0, "Medium", "Pending", "Student Center Ground");
        e2.getBudgetItems().add(new BudgetItem("Sound System & Stage", "50000"));
        e2.getBudgetItems().add(new BudgetItem("Costumes & Decor", "35000"));

        // Event 3
        Event e3 = new Event("EVT-103", "National Debate Championship", "Debating Club",
                "2026-04-10", 45000.0, "Low", "Approved", "Room 402");
        e3.getBudgetItems().add(new BudgetItem("Certificates & Trophies", "20000"));
        e3.getBudgetItems().add(new BudgetItem("Refreshments", "25000"));

        list.add(e1);
        list.add(e2);
        list.add(e3);

        return list;
    }
}