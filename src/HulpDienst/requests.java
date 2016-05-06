package HulpDienst;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 6-4-2016.
 */
public class requests implements IRequests, Serializable {
    private List<TeamRequest> Requests;

    public requests() {
        Requests = new ArrayList<>();
    }

    /**
     * gets all requests send to the servicedesk
     *
     * @return
     */
    public List<TeamRequest> GetRequests() {
        return Requests;
    }

    /**
     * adds a request to the list of requests
     * @param teamRequest
     */
    public void addRequests(TeamRequest teamRequest) {
        Requests.add(teamRequest);
    }

}
