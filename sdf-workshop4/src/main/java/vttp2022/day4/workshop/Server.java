package vttp2022.day4.workshop;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SERVER: waits for client to connect
 * receives command from client
 * calls IO methods to read/write from file
 * java -cp fortunecookie.jar fc.Server 12345 cookie_file.txt
 */
public class Server {
    public static void main( String[] args ) throws IOException {
        if (args.length != 2) {
            System.out.println("Invalid input. " + 
                        "Please enter <port number> <file name>");
            return;
        }
        int portNo = Integer.parseInt(args[0]);
        String fileName = args[1];

        System.out.printf("%d %s\n", portNo, fileName);
        System.out.println("");

        // create server
        ServerSocket socket = new ServerSocket(portNo);
        System.out.println("Waiting for client to connect..");
        Socket sock = socket.accept();
        System.out.println("Client connected.");

        // receive command from client
        IO io = new IO(sock);
        String request = "";
        String response = "";
        //boolean isReceiving = true;

        // check command
        //while (isReceiving) {
        request = io.read();
        if (request.equals("close")) {
            System.out.println("Client wants to close.");
            //isReceiving = false;
            //break;
        } else if (request.equals("get-cookie")) {
            System.out.printf("Client's request: %s\n", request);
            String txtFilePath = "./" + fileName;
            File file = new File(txtFilePath);
            response = IO.getCookie(file);
            io.write(response);
        } else {
            System.out.printf("Client's request: %s\n", request);
            response = String.format("Unknown request: %s", request);
            io.write(response);
        }
        //}
        
        io.close();
        sock.close();
        socket.close();
    }
}