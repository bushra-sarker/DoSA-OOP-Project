# DoSA — Build Roadmap (Fahmida: Student & Club Advisor)

Simple, phase-by-phase plan. Each goal is small: read a list from `DataStore`,
show/edit it in a screen, save. Tick items off as you go.

## Phase 0 — Foundation ✅ (done)
- [x] `User` abstract base (Serializable, role hooks)
- [x] Model classes for every green class in the diagram
- [x] `DataStore` — binary-file persistence + seeded sample data
- [x] `Session` — remembers the logged-in user
- [x] `SceneManager` — screen navigation
- [x] `LoginView` + controller (validates, verifies, routes by role)
- [x] Dashboards show the logged-in name/ID and have a working **Logout**

## Phase 1 — Student (User-1)
Wire each dashboard button to a screen backed by `DataStore`.

- [x] **View Notices** — table from `getNotices()`, a **per-row "Read More"** button
      opens the Notice Details page (title, meta, full body) ✅
- [x] **Register for Event** — creates an `EventRegistration` (Pending) ✅
- [x] **Apply for Club Membership** — creates a `ClubMembershipApplication` (Pending) ✅
- [x] **View Event Schedule** — lists `getEvents()` ✅
- [x] **Submit Complaint** — creates a `Complaint`, saves ✅
- [x] **Community Service** — shows programs (dialog) ✅
- [x] **Download Approval Letter** — writes `ApprovalLetter.content()` to a `.txt` ✅
- [x] **Track History** — shows this student's `getHistory()` (dialog) ✅

> Also fixed a pre-existing bug: `U1G1_ViewNotices.fxml` pointed at the wrong
> controller (`U1G1_NoticeDetailsController`), which broke the View Notices screen.

## Phase 2 — Club Advisor (User-2)
- [ ] **Update Club Info** — edit the `ClubInfo`, save
- [ ] **Post Notice** — create a `Notice` (appears in students' View Notices!)
- [ ] **Review Membership** — approve/reject `ClubMembershipApplication`
- [ ] **Create Event** — create an `ArrangeClubEvent`
- [ ] **View Registered Participants** — list registrations for an event
- [ ] **Assign Volunteer** — create a `VolunteerAssignment`
- [ ] **Submit Completion Report** — create an `EventCompletionReport`
- [ ] **View Activity History** — list `getHistory()` for this advisor

## Phase 3 — Shared processes
- [ ] **Logout** ✅ (done in Phase 0)
- [ ] **Notification** — an in-app list; add an entry on each status change
- [ ] **Search** — filter Events/Notices/Clubs by keyword

## Phase 4 — Polish (optional)
- [ ] Real-time login validation / failed-attempt lockout
- [ ] Consistent styling; replace the placeholder icons
- [ ] Short user guide / screenshots

## The repeating pattern for every "Phase 1/2" goal
1. In the dashboard controller, the button handler calls
   `SceneManager.switchTo("TheView")`.
2. `TheView`'s controller `initialize()` reads the relevant list from
   `DataStore.get()` and fills the table/labels.
3. On submit, build the model object, add it to the list, call
   `DataStore.get().save()`, then navigate back.
