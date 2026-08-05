package c213.dosaoopproject.fahmida.data;

import commonClass.User;
import c213.dosaoopproject.fahmida.model.ApprovalLetter;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.model.Certificate;
import c213.dosaoopproject.fahmida.model.ClubAdvisor;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.model.ClubMembershipApplication;
import c213.dosaoopproject.fahmida.model.Complaint;
import c213.dosaoopproject.fahmida.model.CommunityServiceProgram;
import c213.dosaoopproject.fahmida.model.EventCompletionReport;
import c213.dosaoopproject.fahmida.model.EventRegistration;
import c213.dosaoopproject.fahmida.model.HistoryEntry;
import c213.dosaoopproject.fahmida.model.Notice;
import c213.dosaoopproject.fahmida.model.Student;
import c213.dosaoopproject.fahmida.model.VolunteerAssignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The single source of data for the whole app.
 *
 * <p>Holds every list in memory and persists them together to one binary file
 * ({@code dosa.dat}) using Java object serialization — this satisfies the
 * specification's "read users from a binary file" requirement without needing a
 * database. It is a singleton: {@link #get()} loads the file on first use (or
 * seeds fresh sample data if the file does not exist yet).</p>
 *
 * <p>Controllers read the lists, mutate them, then call {@link #save()}.</p>
 */
public class DataStore implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Binary data file, created in the working directory on first save. */
    private static final Path FILE = Path.of("dosa.dat");

    private static DataStore instance;

    private final List<User> users = new ArrayList<>();
    private final List<Notice> notices = new ArrayList<>();
    private final List<ArrangeClubEvent> events = new ArrayList<>();
    private final List<EventRegistration> eventRegistrations = new ArrayList<>();
    private final List<ClubMembershipApplication> membershipApplications = new ArrayList<>();
    private final List<CommunityServiceProgram> communityPrograms = new ArrayList<>();
    private final List<Complaint> complaints = new ArrayList<>();
    private final List<VolunteerAssignment> volunteerAssignments = new ArrayList<>();
    private final List<EventCompletionReport> completionReports = new ArrayList<>();
    private final List<ClubInfo> clubs = new ArrayList<>();
    private final List<Certificate> certificates = new ArrayList<>();
    private final List<ApprovalLetter> approvalLetters = new ArrayList<>();
    private final List<HistoryEntry> history = new ArrayList<>();

    private DataStore() {
    }

    // ----- singleton / persistence -------------------------------------------

    /** Returns the shared store, loading it from disk (or seeding) on first call. */
    public static DataStore get() {
        if (instance == null) {
            instance = load();
        }
        return instance;
    }

    private static DataStore load() {
        if (Files.exists(FILE)) {
            try (ObjectInputStream in =
                         new ObjectInputStream(new FileInputStream(FILE.toFile()))) {
                return (DataStore) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Could not read " + FILE + " (" + e.getMessage()
                        + ") — starting with fresh seeded data.");
            }
        }
        DataStore fresh = new DataStore();
        fresh.seed();
        fresh.save();
        return fresh;
    }

    /** Writes every list to the binary file. */
    public void save() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE.toFile()))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.err.println("Could not save " + FILE + ": " + e.getMessage());
        }
    }

    // ----- authentication -----------------------------------------------------

    /**
     * Returns the user matching the given login id and password, or {@code null}
     * if none matches.
     */
    public User authenticate(String loginId, String password) {
        for (User u : users) {
            if (u.getLoginId() != null
                    && u.getLoginId().equalsIgnoreCase(loginId)
                    && u.getPasswordHash().equals(password)) {
                return u;
            }
        }
        return null;
    }

    // ----- list accessors -----------------------------------------------------

    public List<User> getUsers() {
        return users;
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public List<ArrangeClubEvent> getEvents() {
        return events;
    }

    public List<EventRegistration> getEventRegistrations() {
        return eventRegistrations;
    }

    public List<ClubMembershipApplication> getMembershipApplications() {
        return membershipApplications;
    }

    public List<CommunityServiceProgram> getCommunityPrograms() {
        return communityPrograms;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public List<VolunteerAssignment> getVolunteerAssignments() {
        return volunteerAssignments;
    }

    public List<EventCompletionReport> getCompletionReports() {
        return completionReports;
    }

    public List<ClubInfo> getClubs() {
        return clubs;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public List<ApprovalLetter> getApprovalLetters() {
        return approvalLetters;
    }

    public List<HistoryEntry> getHistory() {
        return history;
    }

    /** Records an activity-history line and saves. */
    public void logHistory(int userId, String action) {
        history.add(new HistoryEntry(userId, action, LocalDate.now()));
        save();
    }

    // ----- sample data --------------------------------------------------------

    /** Populates the store with a small, realistic set of example records. */
    private void seed() {
        // Users (password == lower-case login id, for easy testing)
        users.add(new Student("Ayesha Rahman", "stu01", 1001, "STU01",
                "ayesha@iub.edu", "CSE"));
        users.add(new Student("Rafiq Hasan", "stu02", 1002, "STU02",
                "rafiq@iub.edu", "EEE"));
        users.add(new ClubAdvisor("Dr. Karim Uddin", "adv01", 2001, "ADV01",
                "karim@iub.edu", 1));

        // Clubs
        clubs.add(new ClubInfo(1, "Robotics Club", "Technology", "Dr. Karim Uddin", 24,
                "Builds robots and runs workshops.", "Sundays 3–5 PM",
                "robotics@iub.edu", "01700000000", "Auditorium Lab"));
        clubs.add(new ClubInfo(2, "Debate Club", "Cultural", "Ms. Nadia Islam", 30,
                "Weekly debates and public-speaking practice.", "Tuesdays 4–6 PM",
                "debate@iub.edu", "01800000000", "Room 4012"));

        // Notices (posted by a club — visible to students)
        notices.add(new Notice(1, "Robotics Club", "Welcome Session",
                "Join our first meeting of the semester!", "Club Activity",
                LocalDate.now().minusDays(3)));
        notices.add(new Notice(2, "DoSA Office", "Exam Schedule Published",
                "Mid-term schedule is now available on the portal.", "Event",
                LocalDate.now().minusDays(1)));

        // Events
        events.add(new ArrangeClubEvent(1, "Robotics Workshop",
                "Hands-on Arduino session.", LocalDate.now().plusDays(7),
                "Auditorium Lab", "Upcoming"));
        events.add(new ArrangeClubEvent(2, "Career Fair",
                "Meet employers on campus.", LocalDate.now().plusDays(14),
                "Main Ground", "Upcoming"));

        // Community-service programs
        communityPrograms.add(new CommunityServiceProgram(1, "Winter Clothing Drive",
                "Donation", "Warm clothes", 10, "2 weeks", 0.0));
    }
}
