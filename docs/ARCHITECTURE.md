# DoSA — Architecture (Fahmida's modules: Student & Club Advisor)

This document explains how the app is put together so any teammate can find their
way around. It focuses on the parts owned by **ID 2521807 — Fahmida Islam**
(User-1 **Student**, User-2 **Club Advisor**).

## Big picture

It is a **JavaFX desktop application** (not a web app) built with Maven and
Java 21. The UI is described in **FXML** files (built visually in Scene Builder)
and driven by **controller** classes. All data lives in memory in a single
`DataStore` and is saved to one **binary file** (`dosa.dat`) so it survives
between runs — no database is used, to keep the project simple.

```
Login screen ──▶ (verify credentials) ──▶ role dashboard ──▶ feature screens
     │                     │                      │
 LoginViewController   DataStore.authenticate   Session (who is logged in)
                            │
                       dosa.dat  (binary, via Java serialization)
```

## Package layout

| Package | Responsibility |
|---|---|
| `commonClass` | `User` — abstract base class for every role |
| `c213.dosaoopproject` | `HelloApplication` (entry point) |
| `c213.dosaoopproject.fahmida` | FXML **controllers** (Login, dashboards, feature screens) |
| `c213.dosaoopproject.fahmida.model` | **Data classes** from the class diagram (all `Serializable`) |
| `c213.dosaoopproject.fahmida.data` | `DataStore` — holds the lists, loads/saves the binary file, seeds sample data |
| `c213.dosaoopproject.fahmida.session` | `Session` — remembers the logged-in user |
| `c213.dosaoopproject.fahmida.utility` | `SceneManager` — switches between FXML screens |

Resources (FXML + images) live under
`src/main/resources/c213/dosaoopproject/fahmida/`.

## The OOP concepts (where the marks are)

- **Abstraction** — `User` is an abstract class; you never create a plain `User`.
- **Inheritance** — `Student` and `ClubAdvisor` **extend** `User` and reuse its
  fields (`userId`, `fullName`, `passwordHash`, `loginId`).
- **Polymorphism** — `getRole()` and `getDashboardFxml()` are overridden per role,
  so the login screen loads the right dashboard without any `if/else` on type.
- **Encapsulation** — model fields are `private`; access is through getters/setters.

## How a screen change happens

1. A controller calls `SceneManager.switchTo("SomeView")`.
2. `SceneManager` loads `/c213/dosaoopproject/fahmida/SomeView.fxml` and swaps it
   into the single application window.

## How data flows (example: posting a notice)

1. Club Advisor fills the "Post Notice" form → controller creates a `Notice` and
   adds it to `DataStore.get().getNotices()`, then calls `DataStore.get().save()`.
2. A Student opens "View Notices" → controller reads the **same**
   `DataStore.get().getNotices()` list and fills the table.
3. Because both screens share one `DataStore`, the advisor's notice appears in the
   student's list automatically.

## Persistence

- `DataStore` implements `Serializable` and writes itself to `dosa.dat` with an
  `ObjectOutputStream`.
- On start-up `DataStore.get()` reads the file if it exists, otherwise it seeds a
  few sample users/notices/events and saves.
- Delete `dosa.dat` any time to reset to the seeded sample data.

## Sample logins (seeded)

| Login ID | Password | Role |
|---|---|---|
| `STU01` | `stu01` | Student (Ayesha Rahman, CSE) |
| `STU02` | `stu02` | Student (Rafiq Hasan, EEE) |
| `ADV01` | `adv01` | Club Advisor (Dr. Karim Uddin, Robotics Club) |
