# Phase 3 — Shared Processes (Technical Documentation)

**Status:** Done. Covers the spec's shared processes **Notification** (Process-3)
and **Search** (Process-4). Logout was already delivered in Phase 0.

`…` = `src/main/java/c213/dosaoopproject/fahmida`.

---

## 1. Notification (Process-3)

An event happens → a notification is recorded for the affected user(s) → they see
it when they click the notification button on their dashboard.

### Model — `model/Notification.java`
`userId`, `message`, `date`, `read` (Serializable).

### DataStore support — `data/DataStore.java`
```java
List<Notification> getNotifications();
void notify(int userId, String message);        // one user
void notifyRole(String role, String message);   // every user of that role
```
Both persist immediately.

### Where notifications are raised
| Trigger (controller) | Call | Who is notified |
|---|---|---|
| Approve/Reject membership (`U2G3`) | `notify(studentId, …)` | the applying student |
| Post notice (`U2G2`) | `notifyRole("Student", …)` | all students |
| Apply for club (`U1G3`) | `notifyRole("Club Advisor", …)` | advisors |
| Register for event (`U1G2`) | `notifyRole("Club Advisor", …)` | advisors |

### Display — `util/Notifications.java`
`showForCurrentUser()` filters notifications by the logged-in user's id, shows
them newest-first in a dialog (a `•` marks unread), then marks them read and
saves. Wired to `notificationOA` on both dashboards.

---

## 2. Search (Process-4)

### Helper — `util/Search.java`
`query(keyword)` returns a formatted result block. It matches the keyword
(case-insensitive, substring) against:
- **Notices** — title, body, category
- **Events** — name, venue
- **Clubs** — name, category

Returns `No results found for "…"` when nothing matches (as the spec requires).

### Wiring
Both dashboard controllers set, in `initialize()`:
```java
searchOFCRTF.setOnAction(e -> Ui.info(Search.query(searchOFCRTF.getText())));
```
`TextField.setOnAction` fires when the user presses **Enter** in the search box,
so no extra button is needed.

---

## 3. Try it

1. Log in **ADV01/adv01** → **Post Club Notice** → create one.
2. Log in **STU01/stu01** → click the **notification button** (top bar) → you see
   *"New notice: …"*.
3. In the student dashboard **search box**, type `robotics` + **Enter** → results
   list the Robotics club, its notice and workshop event.
4. Student **Apply for Club** → log in as advisor → notification shows the new
   application; **Review & Approve** → student's next notification confirms it.

---

## 4. Remaining (Phase 4 — optional polish)
- Real-time login validation / failed-attempt lockout.
- Consistent styling; replace the 1×1 placeholder icons.
- A short illustrated user guide.
