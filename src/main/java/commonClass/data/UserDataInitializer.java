package commonClass.data;

import commonClass.model.User;
import commonClass.util.SessionManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserDataInitializer {

    public static void main(String[] args) {
        ArrayList<User> userList = new ArrayList<>();

        // Add users to ArrayList
        userList.add(new User("S01", "2521807", "Student", ""));
        userList.add(new User("CA02", "2521807", "Club Advisor", ""));
        userList.add(new User("V03", "2411850", "Volunteer", ""));
        userList.add(new User("DO04", "2411850", "DoSA Officer", ""));
        userList.add(new User("CE05", "2430898", "Club Executive", ""));
        userList.add(new User("DCSC06", "2430898", "Coordinator", ""));
        userList.add(new User("HOD07", "2411837", "Head of DoSA", "/c213/dosaoopproject/Bushra/U07/U07_HeadOfDoSADashboardView.fxml"));
        userList.add(new User("SWO08", "2411837", "Welfare Officer", "/c213/dosaoopproject/Bushra/U08/U08_StudentWelfareOfficerDashboard.fxml"));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(userList);
            System.out.println("users.dat successfully created with " + userList.size() + " accounts.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}