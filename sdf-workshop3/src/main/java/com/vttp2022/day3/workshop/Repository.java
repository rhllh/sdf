package com.vttp2022.day3.workshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Repository {
    // reading and writing to files
    // create directory

    private File repository;

    /* ---------- constructor ---------- */ 
    public Repository(String repo) {
        this.repository = new File(repo);
    }

    /* ---------- methods ---------- */
    public List<String> getShoppingCarts() {
        List<String> carts = new LinkedList<>();
        for (String n : repository.list()) {        // access the list of files in directory 
            carts.add(n.replace(".cart", ""));      // get username from username.cart, add to username list
        }
        System.out.println(carts);

        return carts;
    }

    public void save(Cart cart) {
        // get path of file to save cart to
        String cartName = cart.getUsername() + ".cart";
        String saveLocation = repository.getPath() + File.separator + cartName;     // file separator adapts to OS
        File saveFile = new File(saveLocation);         // create a file object of username.cart
        OutputStream os = null;
        try {
            if (!saveFile.exists()) {           // create the file if it doesn't exist
                Path path= Paths.get(repository.getPath());
                Files.createDirectories(path);
                saveFile.createNewFile();
            }
                
            os = new FileOutputStream(saveLocation);    // open file for writing
            cart.save(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cart load(String username) {
        String cartName = username + ".cart";
        Cart cart = new Cart(username);
        for (File cartFile : repository.listFiles()) {  // look through directory
            if (cartFile.getName().equals(cartName)) {  // find the .cart file of the user
                try {
                    InputStream is = new FileInputStream(cartFile);     // opens the file
                    cart.load(is);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return cart;
    }

}
