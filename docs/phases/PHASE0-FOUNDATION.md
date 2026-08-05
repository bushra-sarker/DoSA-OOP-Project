# Phase 0 — Foundation (Technical Documentation)

**Status:** Done. Everything below is required by every later phase.

This phase builds the plumbing: the class hierarchy, the data layer, the session,
the screen-navigation helper, and the login flow that ties them together.

---

## 1. Components at a glance

| Component | Type | File | Responsibility |
|---|---|---|---|
| `User` | abstract class | `commonClass/User.java` | Base of every role; shared identity fields + role hooks |
| `Student` | class | `…/model/Student.java` | User-1 role |
| `ClubAdvisor` | class | `…/model/ClubAdvisor.java` | User-2 role |
| `DataStore` | singleton | `…/data/DataStore.java` | In-memory lists + binary-file persistence + seed data |
| `Session` | singleton | `…/session/Session.java` | Holds the logged-in user |
| `SceneManager` | utility | `…/util/SceneManager.java` | Swaps FXML screens in the one window |
| `LoginViewController` | controller | `…/LoginViewController.java` | Validate + verify credentials, route by role |
| `HelloApplication` | Application | `…/HelloApplication.java` | Entry point; shows the login screen |

`…` = `src/main/java/c213/dosaoopproject/fahmida`.

---

## 2. The class hierarchy (OOP core)

```
              commonClass.User  (abstract, Serializable)
              - userId : int
              - loginId : String
              - passwordHash : String
              - fullName : String
              + loadDashboard() : void        (abstract)
              + getRole() : String            (default "User")
              + getDashboardFxml() : String   (default null)
                        ▲
        ┌───────────────┴───────────────┐
   model.Student                    model.ClubAdvisor
   - email, department              - email, clubId
   getRole()="Student"              getRole()="Club Advisor"
   getDashboardFxml()="U1_Dashboard" getDashboardFxml()="U2_Dashboard"
```

- **Abstraction** — `User` cannot be instantiated.
- **Inheritance** — subclasses reuse `userId`, `fullName`, `passwordHash`, `loginId`.
- **Polymorphism** — `getRole()` / `getDashboardFxml()` are overridden per role, so
  the login code needs **no `if/else` on user type** to pick the dashboard.
- **Encapsulation** — fields are `private`/`protected`, exposed through getters.

> `HeadOfDoSA` (a teammate's class) also extends `User`; the base class keeps
> `getRole()`/`getDashboardFxml()` as concrete defaults so that class is unaffected.

---

## 3. Data layer — `DataStore`

A **singleton** that owns one `ArrayList` per record type and persists them all
together to a single binary file.

```java
DataStore store = DataStore.get();      // loads dosa.dat, or seeds on first run
store.getNotices().add(notice);         // mutate a list
store.save();                           // ObjectOutputStream -> dosa.dat
```

- **File:** `dosa.dat` in the working directory (git-ignored).
- **Format:** Java serialization (`ObjectOutputStream`/`ObjectInputStream`). Every
  model class `implements Serializable` with a `serialVersionUID`.
- **First run:** if `dosa.dat` is absent, `seed()` inserts sample users, clubs,
  notices, events and a community program, then saves.
- **Reset:** delete `dosa.dat` to regenerate the seeded data.
- **Auth:** `authenticate(loginId, password)` linear-searches `users`.
- **History:** `logHistory(userId, action)` appends a `HistoryEntry` and saves.

Why binary-file + singleton (not a database): the spec asks to "read users from a
binary file", and a singleton gives every screen the *same* shared data with no
wiring — a notice an advisor posts is instantly visible to a student.

---

## 4. Session — who is logged in

`Session` is a static holder:

```java
Session.setCurrentUser(user);   // at login
Session.getCurrentUser();       // any screen, to greet or attribute an action
Session.clear();                // at logout
```

---

## 5. Navigation — `SceneManager`

The whole app uses **one window** whose root node is swapped.

```java
SceneManager.setStage(stage);        // once, in HelloApplication.start()
SceneManager.switchTo("U1_Dashboard"); // loads /…/fahmida/U1_Dashboard.fxml
```

`switchTo(name)` builds the path `/c213/dosaoopproject/fahmida/<name>.fxml`, loads
it with `FXMLLoader`, and calls `scene.setRoot(...)` (or creates the scene the
first time).

---

## 6. Login flow (shared "Login process" from the spec)

```
HelloApplication.start()
   └─ SceneManager.switchTo("LoginView")
        └─ user types ID + password, clicks Login
             └─ LoginViewController.loginOA():
                  1. VL  both fields non-empty?           else show error
                  2. VR  DataStore.authenticate(id, pw)   else "Invalid ID or password"
                  3. OK  Session.setCurrentUser(user)
                         SceneManager.switchTo(user.getDashboardFxml())  ← polymorphic
```

Seeded logins: `STU01/stu01`, `STU02/stu02` (students), `ADV01/adv01` (advisor).

---

## 7. `module-info.java` (why the `opens` lines matter)

```java
opens c213.dosaoopproject.fahmida       to javafx.fxml;   // controllers via reflection
opens c213.dosaoopproject.fahmida.model to javafx.base;    // TableView PropertyValueFactory
```

- FXML controllers are constructed reflectively by `javafx.fxml`.
- `PropertyValueFactory` reflects on model getters (e.g. `Notice.getTitle()`), so
  the `model` package must be open to `javafx.base` or table cells render empty.

---

## 8. How to run

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
./mvnw clean javafx:run
```
