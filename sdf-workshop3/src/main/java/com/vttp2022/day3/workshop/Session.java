package com.vttp2022.day3.workshop;

//import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class Session {          // using scanner instead of console - my method
    // command program
    // switch-case block

    private String scInput = "";
    private String[] terms = null;
    
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
    public Session(Repository repo) {
        this.repository = repo;
    }

    /* ---------- methods ---------- */
    public void start() {
        // create a cart for anonymous user
        System.out.println("Welcome to your cart!");
        currentCart = new Cart("anon");
        Scanner sc = new Scanner(System.in);

        // prompt until user decides to leave
        while (!"leave".equals(scInput)) {
            System.out.print("> ");
            scInput = sc.next();                // task keyword for switch
            scInput = scInput.toLowerCase();

            switch (scInput) {
                case LIST:
                    // lists items in currentCart of user
                    System.out.printf("Items in %s's shopping cart\n", 
                    currentCart.getUsername());
                    printList(currentCart.getCart());
                    break;
                
                case ADD:
                    int initialCartSize = currentCart.getCart().size();
                    terms = sc.nextLine().trim().split(" ");
                    for (String item : terms) {
                        currentCart.add(item);
                    }
                    int addedCount = currentCart.getCart().size() - initialCartSize;
                    System.out.printf("%d item(s) were added to %s's cart\n", addedCount,
                                        currentCart.getUsername());
                    break;
                
                case DELETE:
                    terms = sc.nextLine().trim().split(" ");
                    int indToDelete = Integer.parseInt(terms[1]);
                    String item = currentCart.delete(indToDelete);
                    System.out.printf("Removed %s from %s's cart\n", item,
                                        currentCart.getUsername());
                    break;

                case LOAD:
                    // read username.cart and assign the list of items to currentCart
                    currentCart = repository.load(currentCart.getUsername());
                    System.out.printf("Loaded %s's shopping cart. There are %s item(s) in it\n",
                                    currentCart.getUsername(), currentCart.getCart().size());
                    printList(currentCart.getContents());
                    break;

                case USERS:
                    try {
                        List<String> allUsers = repository.getShoppingCarts();
                        this.printList(allUsers);
                    } catch (Exception e) {
                        System.out.println("There are no user carts saved\n");
                    }
                    break;

                case SAVE:
                    repository.save(currentCart);
                    System.out.println("Saved successfully\n");
                    break;

                case LOGIN:
                    terms = sc.nextLine().trim().split(" ");
                    // to do: loading existing cart from file will replace currentCart - need to add 
                    // file items to list
                    // to do: currentCart = repository.load(terms[1]); - load immediately after login
                    currentCart = new Cart(terms[0]);
                    System.out.printf("%s, you are now logged in\n", terms[0]);
                    break;

                default:
                    if (scInput.equals("leave"))
                        System.out.println("Left the cart\n");
                    else 
                        System.out.printf("Unknown input: %s\n", scInput);

                    break;
            }
            
        }
        sc.close();
        
    }

    public void printList(List<String> list) {
        if (list.size() <= 0) {
            System.out.println("Your cart is empty!");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            // print list with 1-based indexing
            System.out.printf("%d. %s\n", i+1, list.get(i));
        }
    }

}
