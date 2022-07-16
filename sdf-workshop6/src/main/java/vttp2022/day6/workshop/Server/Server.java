package vttp2022.day6.workshop.Server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("Please enter <port number>" +
            " <file name> as arguments.");
            return;
        }

        int portNumber = Integer.parseInt(args[0]);
        String filePath = args[1];
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // start the server
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        ServerSocket server = new ServerSocket(portNumber);
        System.out.printf("Waiting for client to connect on port %s\n..", portNumber);
 
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("Client has connected");

                // instantiate client handler object
                System.out.println("\nAssigning new thread for client\n");
                ClientHandler t = new ClientHandler(socket, filePath);
                threadPool.submit(t);
                //t.run();
                System.out.println("\nSubmitted to thread\n");
            }
        } catch (Exception e) {
            server.close();
            e.printStackTrace();
        }
        
        // server closed with ctrl+c
        //server.close();
    }

}
