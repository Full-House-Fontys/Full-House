package AppCommunication;

import java.io.IOException;

/**
 * Created by Kaj Suiker on 20-4-2016.
 */
public class CommunicationServerSingleton {

    private static AppCommunication networkServer;

    public static AppCommunication getNetworkServer() throws IOException {
        if (networkServer == null) {
            networkServer = new AppCommunication();
        }

        networkServer.startListeners();

        return networkServer;
    }

}

