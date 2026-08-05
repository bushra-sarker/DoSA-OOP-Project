package c213.dosaoopproject.fahmida.util;

import c213.dosaoopproject.fahmida.data.DataStore;

import java.util.stream.Collectors;

/**
 * Search process (spec Process-4): finds notices, events and clubs whose text
 * contains the keyword. Returns a formatted, human-readable result block.
 */
public final class Search {

    private Search() {
    }

    public static String query(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return "Type a keyword to search.";
        }
        String k = keyword.toLowerCase();
        DataStore store = DataStore.get();

        String notices = store.getNotices().stream()
                .filter(n -> contains(n.getTitle(), k) || contains(n.getBody(), k)
                        || contains(n.getCategory(), k))
                .map(n -> "  • Notice: " + n.getTitle() + " (" + n.getCategory() + ")")
                .collect(Collectors.joining("\n"));

        String events = store.getEvents().stream()
                .filter(e -> contains(e.getEventName(), k) || contains(e.getVenue(), k))
                .map(e -> "  • Event: " + e.getEventName() + " @ " + e.getVenue())
                .collect(Collectors.joining("\n"));

        String clubs = store.getClubs().stream()
                .filter(c -> contains(c.getClubName(), k) || contains(c.getCategory(), k))
                .map(c -> "  • Club: " + c.getClubName() + " (" + c.getCategory() + ")")
                .collect(Collectors.joining("\n"));

        String all = (notices + "\n" + events + "\n" + clubs).trim().replaceAll("\n{2,}", "\n");
        return all.isEmpty() ? "No results found for \"" + keyword + "\"."
                : "Results for \"" + keyword + "\":\n" + all;
    }

    private static boolean contains(String value, String lowerKeyword) {
        return value != null && value.toLowerCase().contains(lowerKeyword);
    }
}
