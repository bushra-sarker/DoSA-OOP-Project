package c213.dosaoopproject.Bushra.U07.util;

import c213.dosaoopproject.Bushra.U07.model.MajorEvent;

import java.io.*;
import java.util.ArrayList;

public class EventManager {
    private static final String FILE_PATH = "data/events_data.dat";

    // Ensure directory exists
    static {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    public static ArrayList<MajorEvent> loadEvents() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<MajorEvent>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static boolean saveEvents(ArrayList<MajorEvent> events) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(events);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void updateEvent(MajorEvent updatedEvent) {
        ArrayList<MajorEvent> list = loadEvents();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEventName().equals(updatedEvent.getEventName())) {
                list.set(i, updatedEvent);
                break;
            }
        }
        saveEvents(list);
    }
}