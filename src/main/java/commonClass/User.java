package commonClass;

public abstract class User {
    protected int userId;
    protected String passwordHash;
    protected String fullName;

    public User(String fullName, int userId) {
        this.fullName = fullName;
        this.passwordHash = passwordHash;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFullName() {
        return fullName;
    }


    public abstract void loadDashboard();

    public final boolean login(){
        // placeholder
        return true;
    }
    public final void logout(){
        // placeholder
    }
}
