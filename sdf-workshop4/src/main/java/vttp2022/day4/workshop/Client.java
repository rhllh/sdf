package vttp2022.day4.workshop;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * CLIENT: connects to server on host:portNo
 * gives command to server
 * receives text (read) from server
 * java -cp fortunecookie.jar fc.Client locahost:12345
 */
public class Client {
    public static void main(String[] args) throws UnknownHostException, 
    IOException {
        if (args.length != 1) {
            System.out.println("Invalid input. " + 
                        "Please enter <host name>:<port number>");
            return;
        }
        args = args[0].trim().split(":");
        String host = args[0];
        int portNo = Integer.parseInt(args[1]);

        System.out.printf("%s:%d\n", host, portNo);
        System.out.println("");

        // connect to server
        System.out.println("Connecting to server..");
        Socket sock = new Socket(host, portNo);
        System.out.println("Connected.");

        // create IO object to communicate with server
        IO io = new IO(sock);

        // input command get-cookie
        String request = "";
        String response = "";
        //boolean isSending = true;
        Console cons = System.console();

        //while (isSending) {
        request = cons.readLine("> ");
        if (request.equals("close")) {
            io.write(request);
        } else {
            io.write(request);
            response = io.read();
            System.out.printf("Your cookie is >> %s\n", response);
        }
        //}

        io.close();
        sock.close();

        System.out.println("Client ended");
        
    }
}
