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

    public List<TeamRequest> GetRequests() {
        return Requests;
    }

    public void addRequests(TeamRequest teamRequest) {
        Requests.add(teamRequest);
    }

}
