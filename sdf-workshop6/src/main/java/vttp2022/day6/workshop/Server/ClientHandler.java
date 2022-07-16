package vttp2022.day6.workshop.Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ClientHandler implements Runnable {

    private Socket socket;
    private String filePath;

    private static List<String> cookies = new LinkedList<>();
    
    public ClientHandler(Socket socket, String filePath) {
        this.socket = socket;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        System.out.println("Starting client thread");

        try {
            String request = "";    // from client
            String response = "";   // to client
            boolean isReceiving = true;
            IO io = new IO(socket);

            // 'get-cookie' or 'close'
            while (isReceiving) {
                request = io.read().trim().toLowerCase();
                if (request.equals("close")) {  
                    System.out.println("\nClient wants to close\n");
                    response = "Closing connection";
                    io.write(response);
                    isReceiving = false;

                } else if (request.equals("get-cookie")) {
                    System.out.printf("\nClient's request: %s\n", request);
                    File file = new File(filePath);
                    response = ClientHandler.getCookie(file);
                    System.out.printf("Response: %s\n", response);
                    io.write(response);

                } else {
                    System.out.printf("\nClient's request: %s\n", request);
                    response = String.format("Unknown request (%s)\n", request);
                    System.out.printf("Response: %s\n", response);
                    io.write(response);
                }
            }
            io.close();
            socket.close();

            // client returned the thread back to the pool - expected behaviour
            System.out.println("Exiting the thread");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCookie(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr); 
        String item;
        while ((item = br.readLine()) != null) {
            cookies.add(item);
        }
        br.close();
        isr.close();

        int size = cookies.size();
        Random random = new Random();
        int randomInt = random.nextInt(size);

        return cookies.get(randomInt);
    }
    
}
