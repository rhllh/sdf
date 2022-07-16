package vttp2022.day4.workshop;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws IOException
     */
    @Test
    public void testCookie() throws IOException
    {
        String txtFilePath = "./cookie_file.txt";
        File file = new File(txtFilePath);
        String cookie = IO.getCookie(file);
        assertNotNull(cookie);
    }

    @Test
    public void randomCookie() throws IOException {
        String txtFilePath = "./cookie_file.txt";
        File file = new File(txtFilePath);
        String cookie1 = IO.getCookie(file);
        String cookie2 = IO.getCookie(file);
        assertTrue(!cookie1.equals(cookie2));
    }
}
