package com.vttp2022.day3.workshop;

//import java.nio.file.Files;
//import java.nio.file.Paths;

/**
 * Cool shopping cart
 */
public class App {
    private static String defaultDb = "db";

    public static void main( String[] args )
    {
        // get directory name to create later
        // start the session

        if (args.length > 0) {
            if (args[0] != null) {
                App.defaultDb = args[0];
            } 
            Repository repo = new Repository(defaultDb);
            Session session = new Session(repo);
            session.start();
        }
    }
}
