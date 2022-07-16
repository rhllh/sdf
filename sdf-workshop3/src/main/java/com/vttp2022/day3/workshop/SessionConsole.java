package com.vttp2022.day3.workshop;

import java.io.Console;
import java.util.List;

public class SessionConsole {       // using console instead of scanner - solution
    // command program
    // switch-case block
    
    public static final String LIST = "list";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String LOAD = "load";
    public static final String USERS = "users";
    public static final String SAVE = "save";
    public static final String LEAVE = "leave";
    public static final String LOGIN = "login";

    private Repository repository;
    private Cart currentCart;
    
    /* ---------- constructor ---------- */ 
    public SessionConsole(Repository repo) {
        this.repository = repo;
    }

    /* ---------- methods ---------- */
    public void start() {
        boolean stop = false;
        System.out.println("Welcome to your cart!");
        currentCart = new Cart("anon");
        Console cons = System.console();
        while (!stop) {
            String input = cons.readLine("> ");
            String[] terms = input.split(" ");
            switch (terms[0]) {

                case LIST:
                    System.out.printf("Items in %s's shopping cart\n", 
                                        currentCart.getUsername());
                    printList(currentCart.getCart());
                    break;

                case ADD:
                    int initialCartSize = currentCart.getCart().size();
                    for (int i = 1; i < terms.length; i++) {
                        //System.out.println(terms[i]);
                        currentCart.add(terms[i]);
                    }
                    int addedCount = currentCart.getCart().size() - initialCartSize;
                    System.out.printf("%d item(s) were added to %s's cart\n", addedCount,
                                        currentCart.getUsername());
                    break;

                case DELETE:
                    int indToDelete = Integer.parseInt(terms[1]);
                    String item = currentCart.delete(indToDelete);
                    System.out.printf("Removed %s from %s's cart\n", item,
                                        currentCart.getUsername());
                    break;

                case LOAD:      
                    // open the user's .db file and get the cart and update currentCart
                    //currentCart = repository - to do
                    currentCart = repository.load(currentCart.getUsername());
                    System.out.printf("Loaded %s's shopping cart. There are %s items in it\n",
                                    currentCart.getUsername(), currentCart.getCart().size());
                    printList(currentCart.getContents());
                    break;

                case USERS:
                    // to do
                    try {
                        List<String> allUsers = repository.getShoppingCarts();
                        this.printList(allUsers);
                    } catch (Exception e) {
                        System.out.println("There are no user carts saved");
                    }
                    
                    break;

                case SAVE:
                    // write currentCart to user's .cart file
                    repository.save(currentCart);
                    System.out.println("Saved successfully");
                    break;

                case LOGIN:
                    // update currentCart
                    currentCart = new Cart(terms[1]);
                    // currentCart = repository.load(terms[1]); - load immediately after login
                    System.out.printf("%s, you are now logged in\n", terms[1]);
                    break;

                case LEAVE:
                    System.out.println("See you!");
                    stop = true;
                    break;
                
                default:
                    System.err.printf("Unknown input: %s\n", terms[0]);
                    break;
            }
        }
        
    }

    public void printList(List<String> list) {
        if (list.size() <= 0) {
            System.out.println("Your cart is empty!");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i+1, list.get(i));
        }
    }

}
