package c213.dosaoopproject.Bushra.U07.util;

import c213.dosaoopproject.Bushra.U07.model.EventProposal;

public class EventSelectionHolder {
    private static EventProposal selectedEvent;

    public static EventProposal getSelectedEvent() {
        return selectedEvent;
    }

    public static void setSelectedEvent(EventProposal event) {
        selectedEvent = event;
    }

    public static void clear() {
        selectedEvent = null;
    }
}