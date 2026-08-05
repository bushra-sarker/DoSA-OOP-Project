# Phase 2 — Club Advisor (User-2) Technical Documentation

**Status:** Done — all 8 advisor goals wired to the data layer.

The Club Advisor is the "producer" side of the app: the notices, events and
approvals created here flow into the student screens from Phase 1, because both
roles share one `DataStore`.

`…` = `src/main/java/c213/dosaoopproject/fahmida`.

---

## 1. A note on the advisor FXML

The advisor dashboard (`U2_Dashboard.fxml`) was cloned from the student one, so
its buttons **reuse the student handler names** but carry advisor labels. The
mapping (in `U2_DashboardController`):

| Button label | Handler | Action |
|---|---|---|
| Update Club Information | `viewNoticesOA` | → `U2G1_updateClubinfo` |
| Post Club Notices | `registerEventsOA` | → `U2G2_PostClubNotice` |
| Review & Approve Membership | `clubMembershipOA` | → `U2G3_ReviewandapproveClubMembership` |
| Arrange Club Events | `viewScheduleOA` | create an `ArrangeClubEvent` (prompt) |
| Assign Student Volunteers | `submitComplaintsOA` | create a `VolunteerAssignment` |
| View Registered Participants | `downloadApprovalOA` | list `EventRegistration`s |
| Submit Completion Report | `submitCompletionReportOA` | create an `EventCompletionReport` |
| View Club Activity History | `trackHistoryOA` | list this advisor's history |

> Two buttons originally both called `trackHistoryOA` (Completion Report **and**
> Activity History). The FXML was edited to give the report button its own
> handler `submitCompletionReportOA`.

---

## 2. Goals with their own screen (FXML)

### 2.1 Update Club Info — `U2G1_updateClubinfoController`
- `currentClub()` finds the `ClubInfo` whose id equals the advisor's `clubId`
  (`Session` → `ClubAdvisor.getClubId()`).
- `initialize()` loads description / schedule / venue / phone / email into the
  form fields.
- `saveChangesOA` calls `ClubInfo.update(...)`, `DataStore.save()`, logs history.

### 2.2 Post Club Notice — `U2G2_PostClubNoticeController`
- Table lists existing `Notice`s (title / body / date).
- `createNoticeButtonOA` prompts for title + body (via `Ui.prompt`), builds a
  `Notice` tagged with the advisor's club name, adds it, saves, refreshes.
- **Cross-user effect:** the new notice appears in every student's *View Notices*.

### 2.3 Review & Approve Membership — `U2G3_…Controller`
- The FXML had no content, so a `TableView` + Approve/Reject buttons were added.
- Table lists `ClubMembershipApplication`s (student / club / reason / status).
- `approveOA` / `rejectOA` call `app.approve()` / `app.reject()`, save, refresh.
- **Cross-user effect:** acts on the applications students submit in Phase 1.

---

## 3. Goals implemented as dialogs (no dedicated FXML)

These goals had no screen in the provided FXML set, so they use lightweight
dialogs from the shared `util/Ui` helper (`prompt`, `choose`, `info`). All still
read/write the real `DataStore`.

| Goal | Flow | Writes |
|---|---|---|
| Arrange Club Event | prompt event name → add event (date +7d, venue "TBA") | `ArrangeClubEvent` |
| Assign Volunteer | choose event → prompt responsibility | `VolunteerAssignment` |
| Submit Completion Report | choose event → prompt outcome | `EventCompletionReport` |
| View Participants | list `EventRegistration`s | — |
| View Activity History | list this advisor's `HistoryEntry`s | — |
| Community Service | list `CommunityServiceProgram`s | — |

`Ui.prompt(title, header)` → `Optional<String>` (TextInputDialog).
`Ui.choose(title, header, options)` → `Optional<String>` (ChoiceDialog).

---

## 4. End-to-end demo (proves the shared data model)

1. Log in as **Student** `STU01/stu01` → **Apply for Club** (Robotics) →
   application saved as *Pending*.
2. Log out, log in as **Club Advisor** `ADV01/adv01` →
   **Review & Approve Membership** → select the row → **Approve**.
3. Advisor → **Post Club Notice** → create one.
4. Log back in as the student → **View Notices** → the advisor's notice is there.

Every step reads/writes the same `dosa.dat`, so state persists across logins and
runs.

---

## 5. Simplifications (intentional, college-project scope)
- Dialog-based goals reference events by their position in the list; fine for the
  small seeded data set.
- `VolunteerAssignment` stores a placeholder volunteer id (0) — the form only
  captures the responsibility.
- Completion report attendance defaults to 0 (only the outcome summary is asked).
