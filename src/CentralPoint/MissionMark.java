package CentralPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mark on 9-3-2016.
 */
public class MissionMark {

    //all services that go towards mission.
    private int MissionId;
    private String name;
    private String description;
    private Date StartTime;
    private Date LastUpdate;
    private Date EndTime;
    private Double LocationX;
    private Double LocationY;

    /**
     * constructor
     * @param MissionId
     * @param StartTime
     * @param LocationX
     * @param LocationY
     */
    public MissionMark(int MissionId, Date StartTime, Double LocationX, Double LocationY)
    {
        this.MissionId = MissionId;
        this.StartTime = StartTime;
        this.LastUpdate = StartTime;
        this.LocationX = LocationX;
        this.LocationY = LocationY;
    }

    /**
     * returns missionid of this mission
     * @return MissionId
     */
    public int getMissionId()
    {
        return MissionId;
    }

    /**
     * returns startTime of this mission
     * @return StartTime
     */
    public Date getStartTime()
    {
        return StartTime;
    }

    /**
     * returns time since last update of this mission
     * @return LastUpdate
     */
    public Date getLastUpdate()
    {
        return LastUpdate;
    }

    /**
     * returns the endingtime of this mission
     * @return EndTime
     */
    public Date getEndTime()
    {
        return EndTime;
    }

    /**
     * returns the x position of this mission
     * @return LocationX
     */
    public double getLocationX()
    {
        return LocationX;
    }

    /**
     * returns the y posistion of this mission
     * @return LocationY
     */
    public double getLocationY()
    {
        return LocationY;
    }

    /**
     * sets a new lastupdated time for this mission
     * @param LastUpdate
     */
    public void setLastUpdate(Date LastUpdate)
    {
        this.LastUpdate = LastUpdate;
    }

    /**
     * sets the ending time for this mission
     * @param EndTime
     */
    public void setEndTime(Date EndTime)
    {
        this.EndTime = EndTime;
    }

    /**
     * returns a string with the mission id and startingtime
     * @return String
     */
    public String toString()
    {
        return "id: " + MissionId +" start time: " + StartTime;
    }

}
