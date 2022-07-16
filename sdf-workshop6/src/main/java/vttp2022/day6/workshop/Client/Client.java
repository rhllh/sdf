package vttp2022.day6.workshop.Client;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;

import vttp2022.day6.workshop.Server.IO;

/**
 * Hello world!
 *
 */
public class Client {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid input. " + 
                        "Please enter <host name>:<port number>");
            return;
        }
        args = args[0].trim().split(":");

        try {
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
            String response = "";
            boolean isSending = true;
            Console cons = System.console();

            while (isSending) {
                String request = cons.readLine("> ");
                if (request.equals("get-cookie")) {
                    io.write(request);
                    response = io.read();
                    System.out.printf("Your cookie is >> %s\n\n", response);
                } else {
                    io.write(request);
                    response = io.read();
                    System.out.printf("Server >> %s\n", response);
                    if (request.equals("close"))
                        isSending = false;
                }
            }

            io.close();
            sock.close();

            System.out.println("\nClient ended");
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. " + 
            "Please enter <host name>:<port number> where host name is a String" +
            " and port number is an Integer");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}