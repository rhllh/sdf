package vttp2022.day6b.workshop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipIOApp {
    public static void main(String[] args) {
        System.out.println(args[0]);
        try {
            InputStream is = new FileInputStream(args[0]);
            ZipInputStream zis = new ZipInputStream(is);
            while (true) {
                ZipEntry zip = zis.getNextEntry();
                if (null != zip) {
                    System.out.printf("filename: %s, size: %d\n", zip.getName(), zip.getSize());
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            //e.printStackTrace();      // not encouraged
            System.err.println(e.getMessage());
        }
    }
}
