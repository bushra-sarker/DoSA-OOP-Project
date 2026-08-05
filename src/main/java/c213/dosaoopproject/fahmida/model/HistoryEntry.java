package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A single line in a user's activity history ("Track History" /
 * "View Activity History"). One entry is recorded whenever a user performs an
 * action such as registering for an event or approving an application.
 */
public class HistoryEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;
    private String action;
    private LocalDate date;

    public HistoryEntry(int userId, String action, LocalDate date) {
        this.userId = userId;
        this.action = action;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public String getAction() {
        return action;
    }

    public LocalDate getDate() {
        return date;
    }
}
