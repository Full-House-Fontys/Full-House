package AppCommunication;

import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kaj Suiker on 13-4-2016.
 */
public class AppCommunication {
    private static final int socketServerPORT = 8080;
    private Queue<CommunicationMessage> messageQueue;
    private ExecutorService executorService = Executors.newFixedThreadPool(3);
    private ServerSocket socket;

    /**
     * Constructor, calling this will also start the message listener.
     * for the socket communication with the field-app.
     */
    public AppCommunication() {
        try {
            startListeners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the thread listeners.
     * @throws IOException when the thread get interrupted.
     */
    public void startListeners() throws IOException {
        final int THREADPOOLSIZE = 1;

        //stopListeners();

        // Initialize all variables
        executorService = Executors.newFixedThreadPool(THREADPOOLSIZE);
        messageQueue = new ConcurrentLinkedQueue<>();
        socket = new ServerSocket(socketServerPORT);

        // Create runnable
        Runnable worker = () -> constantCheck();

        // Execute
        executorService.execute(worker);

    }

    /**
     * Clears all local variables and stops listening.
     */
    public void stopListeners() {
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        messageQueue.clear();
    }

    /**
     * The listener will constantly check this method.
     * This will check non-stop for app communication.
     */
    private void constantCheck() {
        try {
            while (true) {
                System.out.println(socket.getLocalSocketAddress());
                Socket clientSocket = this.socket.accept();

                String full = recv(clientSocket);

                // Read it into an object
                CommunicationMessage communicationMessage = new CommunicationMessage(full, clientSocket.getInetAddress().toString().substring(1), clientSocket.getLocalSocketAddress().toString().substring(1).substring(0,clientSocket.getLocalSocketAddress().toString().indexOf(":")-1));

                System.out.println(full);
                synchronized (this) {
                    messageQueue.add(communicationMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the inputStream from given socket.
     * This will be returned as a String.
     * @param clientSocket : The socket of the client.
     * @return the inputStream from the given socket.
     * @throws IOException clientSocket can throw an IOException
     */
    @NotNull
    private String recv(Socket clientSocket) throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        String full = "";

        // Read the data into a string
        while ((line = bufferedReader.readLine()) != null) {
            full += line.trim();
        }
        return full;
    }

    /**
     * Gets the latest network message as a request type.
     * @return The latest network request if there is one, else null.
     */
    public CommunicationRequest consumeRequest() {
        CommunicationMessage networkMessage = consumeMessage();
        if (networkMessage != null)
            return new CommunicationRequest(networkMessage);
        else
            return null;
    }

    /**
     * Returns a message item.
     * @return NetworkMessage that was first in the queue. Null if there was none.
     */
    public CommunicationMessage consumeMessage() {
        synchronized (this) {
            return messageQueue.poll();
        }
    }

}

