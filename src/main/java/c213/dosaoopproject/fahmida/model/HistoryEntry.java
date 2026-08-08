package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;
import java.time.LocalDate;


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
