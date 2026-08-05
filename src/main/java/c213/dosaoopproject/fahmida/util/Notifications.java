package c213.dosaoopproject.fahmida.util;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.Notification;
import c213.dosaoopproject.fahmida.session.Session;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Shows the logged-in user's notifications in a dialog and marks them read.
 * Used by the "notification" button on every screen.
 */
public final class Notifications {

    private Notifications() {
    }

    public static void showForCurrentUser() {
        User user = Session.getCurrentUser();
        if (user == null) {
            return;
        }
        List<Notification> mine = DataStore.get().getNotifications().stream()
                .filter(n -> n.getUserId() == user.getUserId())
                .collect(Collectors.toList());

        if (mine.isEmpty()) {
            Ui.info("No new notifications.");
            return;
        }

        // newest first
        String text = mine.stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .map(n -> (n.isRead() ? "   " : "• ") + n.getDate() + " — " + n.getMessage())
                .collect(Collectors.joining("\n"));
        Ui.info(text);

        mine.forEach(Notification::markRead);
        DataStore.get().save();
    }
}
