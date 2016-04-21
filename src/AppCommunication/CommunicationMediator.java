package AppCommunication;

import java.io.IOException;

/**
 * Created by Kaj Suiker on 20-4-2016.
 */
public class CommunicationMediator {
    AppCommunication appCommunication;

    public CommunicationMediator() {
        try {
            appCommunication = CommunicationServerSingleton.getNetworkServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while (true) {
           CommunicationRequest networkRequest = appCommunication.consumeRequest();

            if (networkRequest != null)
                handle(networkRequest);
        }
    }

    private void handle(CommunicationRequest networkRequest) {
        switch (networkRequest.getUrl()) {
            case "/login/":
                System.out.println(networkRequest.getPayload());
                break;
            default:
                System.out.println("error");
        }
    }
}
