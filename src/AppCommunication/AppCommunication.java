package AppCommunication;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kaj Suiker on 13-4-2016.
 */
public class AppCommunication {

    ServerSocket serverSocket;
    String message = "";
    static final int socketServerPORT = 8080;

    private Queue<CommunicationMessage> messageQueue;
    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    private ServerSocket socket;
    int count = 0;

    public static void main(String[] args) {
        new AppCommunication();

        Thread comCom = new Thread(new Runnable() {
            @Override
            public void run() {
                new CommunicationMediator();
            }
        });

       comCom.start();
    }

    public AppCommunication() {
        try {
            startListeners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    private class SocketServerThread extends Thread {
    */

/*
        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(socketServerPORT);

                while (true) {
                    Socket socket = serverSocket.accept();
                    count++;
                    message += "#" + count + " from "
                            + socket.getInetAddress() + ":"
                            + socket.getPort() + "\n";

                    System.out.println(message);

                    SocketServerReplyThread socketServerReplyThread = new SocketServerReplyThread(
                            socket, count);
                    socketServerReplyThread.run();

                    Thread reciever = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            constantCheck();
                        }
                    });

                    reciever.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/

    /**
     * Starts the thread listeners
     */
    public void startListeners() throws IOException {
        final int THREADPOOLSIZE = 4;

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
     * Clears all local variables and stops listening
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

    private void constantCheck() {
        try {
            while (true) {
                Socket clientSocket = this.socket.accept();

                String full = recv(clientSocket);

                // Read it into an object
                CommunicationMessage communicationMessage = new CommunicationMessage(full);// clientSocket.getInetAddress().toString(), clientSocket.getLocalSocketAddress().toString());

                System.out.println(full);
                synchronized (this) {
                    messageQueue.add(communicationMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
     * Gets the latest network message as a request type
     * @return The latest network request if there is one
     */
    public CommunicationRequest consumeRequest() {
        CommunicationMessage networkMessage = consumeMessage();
        if (networkMessage != null)
            return new CommunicationRequest(networkMessage);
        else
            return null;
    }

    /**
     * Returns a message item
     * @return NetworkMessage that was first in the queue. Null if there was none
     */
    public CommunicationMessage consumeMessage() {
        synchronized (this) {
            return messageQueue.poll();
        }
    }

}

