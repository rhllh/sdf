package com.vttp2022.day3.workshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class Cart {                 // cart system
    private List<String> cart = new LinkedList<>();
    private String username = "";

    /* ---------- constructor ---------- */ 
    public Cart(String name) {
        this.username = name;
    }

    /* ---------- getters ---------- */
    public String getUsername() {
        return username;
    }

    public List<String> getCart() {
        return cart;
    }

    /* ---------- methods ---------- */
    public void add(String itemToAdd) {                         
        //for (String item : cart) {
        if (!cart.contains(itemToAdd.toLowerCase())) {          // check for duplicates
            cart.add(itemToAdd);
            System.out.printf("%s added to cart\n", itemToAdd);
        } else 
            System.out.printf("You have %s in your cart\n", itemToAdd);
        //}
    }

    public String delete(int indToDelete) {
        if (indToDelete > cart.size())
            return "Invalid index. Please enter a positive integer";
        else 
            return cart.remove(indToDelete-1);
    }

    public void load(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is);      // read file
        BufferedReader br = new BufferedReader(isr);
        String item;
        while ((item = br.readLine()) != null)                  // check for end of file
            cart.add(item);
        br.close();
        isr.close();
    }

    public void save(OutputStream os) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        for (String item : cart)
            bw.write(item + "\n");

        bw.flush();
        osw.flush();
        bw.close();
        osw.close();
    }

    public List<String> getContents() {
        return cart;
    }

}
