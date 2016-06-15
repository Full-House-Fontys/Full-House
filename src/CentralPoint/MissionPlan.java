package CentralPoint;

/**
 * Created by Qun on 15-6-2016.
 */
public class MissionPlan {
    private int missionID;
    private String allSteps;

    public MissionPlan(int missionID, String allSteps) {
        this.missionID = missionID;
        this.allSteps = allSteps;
    }

    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    public String getAllSteps() {
        return allSteps;
    }

    public void setAllSteps(String allSteps) {
        this.allSteps = allSteps;
    }
}
