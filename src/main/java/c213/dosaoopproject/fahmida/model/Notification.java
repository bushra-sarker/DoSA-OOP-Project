package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * An in-app notification for one user. Raised automatically when a relevant
 * status change happens (membership approved, notice posted, ...) and shown on
 * the user's dashboard via the "notification" button.
 */
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;
    private String message;
    private LocalDate date;
    private boolean read;

    public Notification(int userId, String message, LocalDate date) {
        this.userId = userId;
        this.message = message;
        this.date = date;
        this.read = false;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isRead() {
        return read;
    }

    public void markRead() {
        this.read = true;
    }
}
