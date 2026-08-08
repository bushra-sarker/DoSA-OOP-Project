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
import c213.dosaoopproject.fahmida.model.Notification;
import c213.dosaoopproject.fahmida.model.Student;
import c213.dosaoopproject.fahmida.model.VolunteerAssignment;
import c213.dosaoopproject.fahmida.utility.FileManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;


public class DataStore {

    private static final  String USERS_FILE = "Users.bin";
    private static final String NOTICES_FILE = "Announcements.bin";
    private static final String EVENTS_FILE = "AvailableEvents.bin";
    private static final String EVENT_REGISTRATIONS_FILE = "EventRegistrations.bin";
    private static final String MEMBERSHIP_APPLICATIONS_FILE = "MembershipApplications.bin";
    private static final String COMMUNITY_PROGRAMS_FILE = "campaigns.bin";
    private static final String COMPLAINTS_FILE = "Complaints.bin";
    private static final String VOLUNTEER_ASSIGNMENTS_FILE = "VolunteerAssignments.bin";
    private static final String COMPLETION_REPORTS_FILE = "CompletionReports.bin";
    private static final String CLUBS_FILE = "Clubs.bin";
    private static final String CERTIFICATES_FILE = "Certificates.bin";
    private static final String APPROVAL_LETTERS_FILE = "ApprovalLetters.bin";
    private static final String HISTORY_FILE = "History.bin";
    private static final String NOTIFICATIONS_FILE = "Notifications.bin";

    private static DataStore instance;

    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Notice> notices = new ArrayList<>();
    private final ArrayList<ArrangeClubEvent> events = new ArrayList<>();
    private final ArrayList<EventRegistration> eventRegistrations = new ArrayList<>();
    private final ArrayList<ClubMembershipApplication> membershipApplications = new ArrayList<>();
    private final ArrayList<CommunityServiceProgram> communityPrograms = new ArrayList<>();
    private final ArrayList<Complaint> complaints = new ArrayList<>();
    private final ArrayList<VolunteerAssignment> volunteerAssignments = new ArrayList<>();
    private final ArrayList<EventCompletionReport> completionReports = new ArrayList<>();
    private final ArrayList<ClubInfo> clubs = new ArrayList<>();
    private final ArrayList<Certificate> certificates = new ArrayList<>();
    private final ArrayList<ApprovalLetter> approvalLetters = new ArrayList<>();
    private final ArrayList<HistoryEntry> history = new ArrayList<>();
    private final ArrayList<Notification> notifications = new ArrayList<>();

    private DataStore() {
    }

    public static DataStore get() {
        if (instance == null) {
            instance = load();
        }
        return instance;
    }

    private static DataStore load() {
        if (!Files.exists(Path.of(USERS_FILE))) {
            DataStore fresh = new DataStore();
            fresh.seed();
            fresh.save();
            return fresh;
        }
        DataStore ds = new DataStore();
        ds.users.addAll(loadOrEmpty(USERS_FILE));
        ds.notices.addAll(loadOrEmpty(NOTICES_FILE));
        ds.events.addAll(loadOrEmpty(EVENTS_FILE));
        ds.eventRegistrations.addAll(loadOrEmpty(EVENT_REGISTRATIONS_FILE));
        ds.membershipApplications.addAll(loadOrEmpty(MEMBERSHIP_APPLICATIONS_FILE));
        ds.communityPrograms.addAll(loadOrEmpty(COMMUNITY_PROGRAMS_FILE));
        ds.complaints.addAll(loadOrEmpty(COMPLAINTS_FILE));
        ds.volunteerAssignments.addAll(loadOrEmpty(VOLUNTEER_ASSIGNMENTS_FILE));
        ds.completionReports.addAll(loadOrEmpty(COMPLETION_REPORTS_FILE));
        ds.clubs.addAll(loadOrEmpty(CLUBS_FILE));
        ds.certificates.addAll(loadOrEmpty(CERTIFICATES_FILE));
        ds.approvalLetters.addAll(loadOrEmpty(APPROVAL_LETTERS_FILE));
        ds.history.addAll(loadOrEmpty(HISTORY_FILE));
        ds.notifications.addAll(loadOrEmpty(NOTIFICATIONS_FILE));
        return ds;
    }

    private static <T> ArrayList<T> loadOrEmpty(String fileName) {
        ArrayList<T> loaded = FileManager.readFile(fileName);
        return loaded != null ? loaded : new ArrayList<>();
    }

    /** Writes every list to its own binary file. */
    public void save() {
        FileManager.writeFile(USERS_FILE, users);
        FileManager.writeFile(NOTICES_FILE, notices);
        FileManager.writeFile(EVENTS_FILE, events);
        FileManager.writeFile(EVENT_REGISTRATIONS_FILE, eventRegistrations);
        FileManager.writeFile(MEMBERSHIP_APPLICATIONS_FILE, membershipApplications);
        FileManager.writeFile(COMMUNITY_PROGRAMS_FILE, communityPrograms);
        FileManager.writeFile(COMPLAINTS_FILE, complaints);
        FileManager.writeFile(VOLUNTEER_ASSIGNMENTS_FILE, volunteerAssignments);
        FileManager.writeFile(COMPLETION_REPORTS_FILE, completionReports);
        FileManager.writeFile(CLUBS_FILE, clubs);
        FileManager.writeFile(CERTIFICATES_FILE, certificates);
        FileManager.writeFile(APPROVAL_LETTERS_FILE, approvalLetters);
        FileManager.writeFile(HISTORY_FILE, history);
        FileManager.writeFile(NOTIFICATIONS_FILE, notifications);
    }

    // ----- authentication -----------------------------------------------------

    /**
     * Returns the user matching the given login id and password, or {@code null}
     * if none matches.
     */
    public User authenticate(String loginId, String password) {
        User u = findByLoginId(loginId);
        return (u != null && u.getPasswordHash().equals(password)) ? u : null;
    }

    /** Finds a user by login id regardless of password (for lockout handling). */
    public User findByLoginId(String loginId) {
        for (User u : users) {
            if (u.getLoginId() != null && u.getLoginId().equalsIgnoreCase(loginId)) {
                return u;
            }
        }
        return null;
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Notice> getNotices() {
        return notices;
    }

    public ArrayList<ArrangeClubEvent> getEvents() {
        return events;
    }

    public ArrayList<EventRegistration> getEventRegistrations() {
        return eventRegistrations;
    }

    public ArrayList<ClubMembershipApplication> getMembershipApplications() {
        return membershipApplications;
    }

    public ArrayList<CommunityServiceProgram> getCommunityPrograms() {
        return communityPrograms;
    }

    public ArrayList<Complaint> getComplaints() {
        return complaints;
    }

    public ArrayList<VolunteerAssignment> getVolunteerAssignments() {
        return volunteerAssignments;
    }

    public ArrayList<EventCompletionReport> getCompletionReports() {
        return completionReports;
    }

    public ArrayList<ClubInfo> getClubs() {
        return clubs;
    }

    public ArrayList<Certificate> getCertificates() {
        return certificates;
    }

    public ArrayList<ApprovalLetter> getApprovalLetters() {
        return approvalLetters;
    }

    public ArrayList<HistoryEntry> getHistory() {
        return history;
    }

    public void logHistory(int userId, String action) {
        history.add(new HistoryEntry(userId, action, LocalDate.now()));
        save();
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    /** Raises a notification for one user (Notification process) and saves. */
    public void notify(int userId, String message) {
        notifications.add(new Notification(userId, message, LocalDate.now()));
        save();
    }

    /** Raises the same notification for every user matching the role name. */
    public void notifyRole(String role, String message) {
        for (User u : users) {
            if (role.equalsIgnoreCase(u.getRole())) {
                notifications.add(new Notification(u.getUserId(), message, LocalDate.now()));
            }
        }
        save();
    }

    // ----- sample data --------------------------------------------------------

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
        clubs.add(new ClubInfo(2, "Jukti Club", "Programming", "Dr. Karim Uddin", 30,
                "Weekly Coding practice.", "Tuesdays 4–6 PM",
                "jukti@iub.edu", "01800000000", "Room 4012"));
        clubs.add(new ClubInfo(3, "Art Club", "Cultural", "Ms. Nadia Islam", 25,
                "Creative Design & Art Exibitions.", "Sundays 3–5 PM",
                "Arts@iub.edu", "01900000000", "Activity Area"));

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
        events.add(new ArrangeClubEvent(2, "Annual Drama Production",
                "Padmabati Drama produced by students.", LocalDate.now().plusDays(7),
                "IUB Theatre ", "Upcoming"));
        events.add(new ArrangeClubEvent(3, "Career Fair",
                "Meet employers on campus.", LocalDate.now().plusDays(14),
                "Main Ground", "Upcoming"));

        // Community-service programs
        communityPrograms.add(new CommunityServiceProgram(1, "Winter Cloth Distribution",
                "Donation", "Jackets, sweaters, blankets", "2 weeks", 0.0,
                "Student Activity Area"));
        communityPrograms.add(new CommunityServiceProgram(2, "Fundraising for Disaster Victims",
                "Fundraising", "-", "1 month", 50000.0, "AUDITORIUM"));
    }
}
