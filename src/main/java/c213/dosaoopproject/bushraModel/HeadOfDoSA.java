package c213.dosaoopproject.bushraModel;

import c213.dosaoopproject.commonClass.User;

public class HeadOfDoSA extends User {
    private int headId;

    public HeadOfDoSA(String role, String fullName, String passwordHash, int userId, int headId) {
        super(passwordHash, userId);
        this.headId = headId;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    @Override
    public void loadDashboard(){
        // Load Head of DoSA dashboard
    }

    // Major event Management part
    public boolean approveMajorEvent(int eventId){
        return false;
    }

    public boolean rejectEvent(int eventId, String reason){
        return false;
    }

    // Crisis Management part
    public boolean manageCrisis(int crisisId){
        return false;
    }

    // Budget Management part
    public boolean publishFinalBudget(String semester){
        return false;
    }

    // Partnership & MOU part
    public boolean signMOU(int requestId){
        return false;
    }

    // Student Discipline part
    public boolean resolveDisciplinaryCase(int caseId){
        return false;
    }

    // Exchange Program part
    public boolean approveExchangeNomination(int nominationId){
        return false;
    }

    // Transcript Certificate part
    public boolean certifyTranscript(int transcriptId){
        return false;
    }


}
