package vttp2022.day6.workshop.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IO {
    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;
    
    public IO(Socket sock) throws IOException {
        is = sock.getInputStream();
        dis = new DataInputStream(is);
        os = sock.getOutputStream();
        dos = new DataOutputStream(os);
        //System.out.println("File opened successfully");
    }

    public DataInputStream getDIS() {
        return dis;
    }

    public DataOutputStream getDOS() {
        return dos;
    }

    public String read() throws IOException {
        return dis.readUTF();
    }

    public void write(String msg) throws IOException {
        dos.writeUTF(msg);
        dos.flush();
        //return msg;
    }

    public void close() {
        try {
            dos.close();
            os.close();
            dis.close();
            is.close();
            //System.out.println("File closed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
