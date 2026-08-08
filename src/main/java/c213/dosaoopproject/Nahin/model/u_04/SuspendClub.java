package c213.dosaoopproject.Nahin.model.u_04;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SuspendClub implements Serializable {

    private int clubID;
    private String clubName;
    private LocalDate lastActivityDate;
    private boolean suspended;

    public SuspendClub() {
    }

    public SuspendClub(int clubID, String clubName, LocalDate lastActivityDate, boolean suspended) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.lastActivityDate = lastActivityDate;
        this.suspended = suspended;
    }

    public int getClubID() {
        return clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public LocalDate getLastActivityDate() {
        return lastActivityDate;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setLastActivityDate(LocalDate lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public long getInactiveDays() {
        return ChronoUnit.DAYS.between(lastActivityDate, LocalDate.now());
    }

    public boolean isInactive() {
        return getInactiveDays() >= 180;
    }

    public String getStatus() {
        if (suspended) {
            return "Suspended";
        }
        if (isInactive()) {
            return "Inactive";
        }
        return "Active";
    }

    public void suspendClub() {
        suspended = true;
    }

    @Override
    public String toString() {
        return "SuspendClub{" +
                "clubID=" + clubID +
                ", clubName='" + clubName + '\'' +
                ", lastActivityDate=" + lastActivityDate +
                ", suspended=" + suspended +
                '}';
    }
}