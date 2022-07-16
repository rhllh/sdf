package vttp2022.day4.workshop;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IO {
    private InputStream is = null;
    private DataInputStream dis = null;
    private OutputStream os = null;
    private DataOutputStream dos = null;

    private static List<String> cookies = new LinkedList<>();
    
    public IO(Socket sock) throws IOException {
        // constructor
        // opens the file
        is = sock.getInputStream();
        dis = new DataInputStream(is);
        os = sock.getOutputStream();
        dos = new DataOutputStream(os);
        System.out.println("opened file successfully");
    }

    // methods
    public String read() throws IOException {
        return dis.readUTF();
    }

    public String write(String msg) throws IOException {
        dos.writeUTF(msg);
        dos.flush();
        return msg;
    }

    public static String getCookie(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);      // read file
        BufferedReader br = new BufferedReader(isr);
        String item;
        while ((item = br.readLine()) != null)                  // check for end of file
            cookies.add(item);
        br.close();
        isr.close();

        int size = cookies.size();
        Random rand = new Random();
        int randomInt = rand.nextInt(size);
        //System.out.println(cookies.get(randomInt));

        return cookies.get(randomInt);
    }

    public void close() throws IOException {
        dis.close();
        is.close();
        dos.close();
        os.close();
        System.out.println("closed file successfully");
    }
}
