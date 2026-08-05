# Phase 1 — Student (User-1) Technical Documentation

**Status:** Done — all 8 student goals wired to the data layer.

Every goal follows the same three-step pattern, so once you understand one you
understand them all:

```
Dashboard button ──▶ SceneManager.switchTo("SomeView")
SomeView.initialize() ──▶ read a list from DataStore.get(), fill the table/labels
Primary action ──▶ build a model object, add to the list, DataStore.save(), notify
```

`…` = `src/main/java/c213/dosaoopproject/fahmida`.

---

## 1. Goal → screen → model map

| Goal | Dashboard handler | View / Controller | Model written | Reads |
|---|---|---|---|---|
| View Notices | `viewNoticesOA` | `U1G1_ViewNotices` | — | `getNotices()` |
| ↳ Notice details | (per-row button) | `U1G1_NoticeDetails` | — | selected `Notice` |
| Register for Event | `registerEventsOA` | `U1G2_EventList` | `EventRegistration` | `getEvents()` |
| Apply for Club | `clubMembershipOA` | `U1G3_ApplyForClub` | `ClubMembershipApplication` | `getClubs()` |
| View Event Schedule | `viewScheduleOA` | `U1G4_ViewEventSchedule` | — | `getEvents()` |
| Submit Complaint | `submitComplaintsOA` | `U1G6` | `Complaint` | — |
| Community Service | `communityProgramOA` | `U1G5_CommunityService` | volunteer slot | `getCommunityPrograms()` |
| Download Approval Letter | `downloadApprovalOA` | `U1G7_DownloadApproval` | `ApprovalLetter` (+ .txt file) | own letters |
| Track History | `trackHistoryOA` | `U1G8_TrackHistory` | — | own `getHistory()` |

> Community Service, Download Approval Letter and Track History are now full
> sidebar screens (built from the `U1G4_ViewEventSchedule` template), each with
> the correct sidebar item highlighted. Cross-page sidebar navigation is wired on
> every student screen.

---

## 2. Shared helper — `util/Ui`

To avoid copy-pasting the same lines into every controller:

```java
Ui.greet(nameLabel11, userIdLabel11);   // fills header name/ID from Session
Ui.info("message");                      // simple information dialog
```

---

## 3. Feature details

### 3.1 View Notices  (`U1G1_ViewNoticesController`)
- `initialize()` maps columns with `PropertyValueFactory` to `Notice` getters
  (`title`, `clubName`, `category`, `datePosted`), then fills the table from
  `DataStore.get().getNotices()`.
- Removes the stray empty "C2" column and sets
  `CONSTRAINED_RESIZE_POLICY` so columns fit the width (no horizontal scroll).
- **Per-row "Read More"**: a programmatically added `TableColumn<Notice, Void>`
  whose cell renders a `Button`; clicking it calls
  `U1G1_NoticeDetailsController.setSelectedNotice(n)` then navigates to details.

### 3.2 Notice Details  (`U1G1_NoticeDetailsController`)
- Receives the chosen notice through a `static Notice selectedNotice` handed over
  before navigation (simple cross-controller data pass).
- Fills `detailTitle`, `detailMeta` (`Posted by … • category • date`) and
  `detailBody` from the notice.

### 3.3 Register for Event  (`U1G2_EventListController`)
- Table of `ArrangeClubEvent` from `getEvents()`.
- `RegNowOA`: takes the selected event, reads the student's department (via
  `Session` cast to `Student`), adds a `new EventRegistration(...)` with status
  `"Pending"`, logs history, and confirms.

### 3.4 Apply for Club  (`U1G3_ApplyForClubController`)
- Table of `ClubInfo` (`clubName`, `category`, `totalMembers`, `moderatorName`).
- `applyToJoinOA`: builds a `ClubMembershipApplication` (status `"Pending"`) for
  the selected club + logged-in student, saves, logs, confirms. *This is the
  record a Club Advisor will approve/reject in Phase 2.*

### 3.5 View Event Schedule  (`U1G4_ViewEventScheduleController`)
- Read-only table of `getEvents()` (activity/status/venue/date).

### 3.6 Submit Complaint  (`U1G6Controller`)
- Category `ComboBox` + title + description. On submit: validates, builds a
  `Complaint` (status `"Open"`), saves, logs, returns to the dashboard.

### 3.7 Community Service / Track History  (dialogs on `U1_DashboardController`)
- Community Service lists `getCommunityPrograms()` via `Ui.info`.
- Track History filters `getHistory()` by the current user id and lists it.

### 3.8 Download Approval Letter  (`U1_DashboardController.downloadApprovalOA`)
- Builds an `ApprovalLetter`, writes `ApprovalLetter.content()` to
  `ApprovalLetter_<loginId>.txt` with `Files.writeString`, stores the letter,
  logs history, and shows the saved path. (Plain text stands in for a PDF.)

---

## 4. Cross-user data flow (why the design matters)

Because all screens share one `DataStore`:

```
Club Advisor: Post Notice ─┐
                           ├─▶ DataStore.notices ──▶ Student: View Notices
Student: Apply for Club ───┐
                           ├─▶ DataStore.membershipApplications ──▶ Advisor: Review Membership
Student: Register Event ───┐
                           └─▶ DataStore.eventRegistrations ──▶ Advisor: View Participants
```

Phase 1 fills the student side of these pipes; Phase 2 wires the advisor side.

---

## 5. Bug fixed during this phase
`U1G1_ViewNotices.fxml` had `fx:controller` pointing at
`U1G1_NoticeDetailsController` (wrong) — a copy-paste error that made the View
Notices screen fail to load. Corrected to `U1G1_ViewNoticesController`.

---

## 6. Verifying handlers
Every FXML `onAction="#x"` must have a matching `void x(ActionEvent)` in its
controller, or the screen throws a `LoadException` when opened. A quick check:

```bash
# from the fahmida resources dir — lists any FXML whose handlers are missing
for f in *.fxml; do grep -oE 'onAction="#[A-Za-z0-9_]+"' "$f"; done
```
