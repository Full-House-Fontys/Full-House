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
    private List<String> services;
    private Date StartTime;
    private Date LastUpdate;
    private Date EndTime;
    private Double LocationX;
    private Double LocationY;
    public MissionMark(int MissionId, Date StartTime, Double LocationX, Double LocationY)
    {
        this.MissionId = MissionId;
        this.StartTime = StartTime;
        this.LastUpdate = StartTime;
        this.LocationX = LocationX;
        this.LocationY = LocationY;
        services = new ArrayList<>();
    }

    public int getMissionId()
    {
        return MissionId;
    }
    public Date getStartTime()
    {
        return StartTime;
    }
    public Date getLastUpdate()
    {
        return LastUpdate;
    }
    public Date getEndTime()
    {
        return EndTime;
    }
    public double getLocationX()
    {
        return LocationX;
    }
    public double getLocationY()
    {
        return LocationY;
    }
    public List<String> getServices()
    {
        return services;
    }
    public void setLastUpdate(Date LastUpdate)
    {
        this.LastUpdate = LastUpdate;
    }

    public void setEndTime(Date EndTime)
    {
        this.EndTime = EndTime;
    }

    public void addservices(String service)
    {
        services.add(service);
    }

    public String toString()
    {
        return "id: " + MissionId +" start time: " + StartTime;
    }

}
