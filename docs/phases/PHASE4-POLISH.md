# Phase 4 — Polish (Technical Documentation)

**Status:** In progress (optional). Delivered so far: login lockout, real icons,
white search text. Remaining optional items: broader styling, a screenshot guide.

`…` = `src/main/java/c213/dosaoopproject/fahmida`.

---

## 1. Login failed-attempt lockout (spec Process-1, event-5/6)

Implements the specification's login-security detail: count wrong passwords, warn
with attempts remaining, and lock the account after 3 failures.

### Model — `commonClass/User.java`
```java
public static final int MAX_FAILED_ATTEMPTS = 3;
int  getFailedAttempts();
boolean isLocked();
void recordFailedAttempt();   // ++, locks at the limit
int  attemptsRemaining();
void resetFailedAttempts();   // on success
```
These fields are serialized with the user, so the lock state survives restarts.

### DataStore — `data/DataStore.java`
`findByLoginId(id)` returns the user regardless of password, so the login screen
can tell "no such user" from "wrong password" (needed to count attempts).

### Flow — `LoginViewController.loginOA`
1. Both fields required (validation).
2. `findByLoginId` — unknown id → generic *"Invalid ID or password"*.
3. Locked account → *"Account locked after 3 failed attempts…"*.
4. Wrong password → `recordFailedAttempt()`, save, then either the lock message
   or *"Invalid ID or password. N attempt(s) remaining before lockout."*
5. Correct password → `resetFailedAttempts()`, save, open the dashboard.

**Try it:** enter `STU01` with a wrong password three times — the message counts
down, then the account locks. (Delete `dosa.dat` to reset.)

---

## 2. Real icons

The bell and profile placeholders were 1×1 transparent PNGs. They are now small
drawn icons under `src/main/resources/images/`:
- `BellIcon.png` — 32×32 white bell (for the notification button).
- `images.png` — 64×64 profile avatar.

---

## 3. White search text

The dashboard search boxes sit on a dark blue header, so
`-fx-text-fill: white` + `-fx-prompt-text-fill: #cfe3ef` were added to the
`searchOFCRTF` style in `U1_Dashboard.fxml` and `U2_Dashboard.fxml`.

---

## 4. Still optional
- A shared CSS stylesheet instead of many inline styles.
- A short user guide with screenshots of each screen.
