# DoSA-OOP-Project

A **JavaFX desktop application** simulating the operations of the **Division of
Student Affairs (DoSA)** at IUB — a CSE213 OOP course project (Group 64).

This repository contains the modules owned by **ID 2521807 — Fahmida Islam**:
- **User-1: Student**
- **User-2: Club Advisor**

## Tech stack
- Java 21, JavaFX 21 (FXML + controllers)
- Maven build (`javafx-maven-plugin`)
- Data stored in a single **binary file** (`dosa.dat`) via Java serialization —
  no database, to keep it simple.

## Requirements
- **JDK 21** (e.g. Temurin 21). Check with `java -version`.

## Run it
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)   # macOS
./mvnw clean javafx:run
```
Or from **Android Studio / IntelliJ**: open the Maven tool window →
`Plugins → javafx → javafx:run`.

The app opens the **Login** screen. Sample logins (seeded automatically):

| Login ID | Password | Role |
|---|---|---|
| `STU01` | `stu01` | Student |
| `STU02` | `stu02` | Student |
| `ADV01` | `adv01` | Club Advisor |

After login you land on the role-specific dashboard. **Logout** (top of the
dashboard) returns you to the login screen.

> Delete `dosa.dat` to reset all data back to the seeded samples.

## Preview a screen (design)
The `.fxml` files under `src/main/resources/c213/dosaoopproject/fahmida/` open in
**Scene Builder**:
```bash
open -a "SceneBuilder" src/main/resources/c213/dosaoopproject/fahmida/U1_Dashboard.fxml
```

## Project docs
- [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md) — how the code is organised and
  the OOP design.
- [`docs/ROADMAP.md`](docs/ROADMAP.md) — the phased build plan and progress.
- **Per-phase technical docs:**
  [Phase 0 — Foundation](docs/phases/PHASE0-FOUNDATION.md) ·
  [Phase 1 — Student](docs/phases/PHASE1-STUDENT.md) ·
  [Phase 2 — Club Advisor](docs/phases/PHASE2-CLUBADVISOR.md) ·
  [Phase 3 — Shared](docs/phases/PHASE3-SHARED.md) ·
  [Phase 4 — Polish](docs/phases/PHASE4-POLISH.md)

## Status
- **Phase 0 (Foundation): done** — login, role routing, session, navigation, data
  layer with sample data, working logout.
- **Phase 1 (Student): done** — all 8 goals wired to the data layer (View Notices
  with per-row "Read More" → full details, Register for Event, Apply for Club,
  View Schedule, Submit Complaint, Community Service, Download Approval Letter,
  Track History).
- **Phase 2 (Club Advisor): done** — all 8 goals wired (Update Club Info, Post
  Notice, Review & Approve Membership, Create Event, View Participants, Assign
  Volunteer, Submit Completion Report, View Activity History).
- **Phase 3 (shared: Notification, Search): done** — in-app notifications raised
  on status changes; keyword search over notices/events/clubs.
- **Phase 4 (polish): partly done** — login failed-attempt lockout, real bell/
  profile icons, white search text. (Broader styling + a screenshot guide remain
  optional.)
