package HulpDienst;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Mark on 6-4-2016.
 */
public interface IRequests extends Remote {
    List<TeamRequest> GetRequests();

    void addRequests(TeamRequest teamRequest);
}
