package cart;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    public static void main(String[] args) {
        // initialise empty cart
        List<String> cart = new ArrayList<String>();
        
        // print welcome message and prompt input
        System.out.println("Welcome to your cart!");
        System.out.println("Remember to 'save' before you 'leave'.");
        Scanner sc = new Scanner(System.in);

        // initialise variables
        String input = "";
        String[] itemsToAdd;
        int itemToDelete = 0;
        
        // list                 - prints the shopping cart in an ordered list
        // add <item1> <item2>  - add item(s) to shopping cart
        // delete <itemIndex>   - delete an item from the shopping cart
        // leave                - exit the program
        while (!"leave".equals(input)) {
            System.out.print("> ");
            input = sc.next();
            input = input.toLowerCase();

            switch (input) {
                case "list":
                    // check if cart is empty, otherwise print the list
                    if (cart.isEmpty()) System.out.println("Your cart is empty!");
                    else {
                        for (int i = 0; i < cart.size(); i++) {
                            System.out.printf("%d. %s\n", i+1, cart.get(i));   
                        }
                    }
                    break;
                
                case "add":     // list of space seperated items
                    itemsToAdd = sc.nextLine().trim().split(" ");
                    for (String item : itemsToAdd) {
                        item = item.trim().toLowerCase();
                        if (item.isBlank()) {continue;} 
                        else if (!cart.contains(item)) {      // check for duplicates
                            cart.add(item);
                            System.out.printf("%s added to cart\n", item);
                        } else System.out.printf("You have %s in your cart\n", item);
                    }
                    break;
                
                case "delete":
                    // get the index of the item to remove (1-based indexing)
                    if (sc.hasNextInt()) {
                        itemToDelete = Integer.parseInt(sc.nextLine());
                        if (itemToDelete <= 0) {
                            System.out.println("Invalid index. Please enter a positive integer");
                            break;
                        }
                    } else {
                        input = sc.nextLine();  // reprompt
                        System.out.println("No index given. Please enter a positive integer");
                        break;
                    }
                    
                    // check if index is empty/out of bounds - either reprompt or delete
                    if (cart.size() == 0) System.out.println("Your cart is empty!");
                    else if (cart.size() < itemToDelete) System.out.println("Incorrect item index");
                    else {
                        System.out.printf("%s removed from cart\n", cart.get(itemToDelete-1));
                        cart.remove(itemToDelete-1);
                    }
                    break;

                default:
                    // keyword 'leave' will end the program naturally
                    // any other words will be invalid input and will prompt input again
                    if (input.equals("leave")) {System.out.println("Left the cart");}
                    else {System.out.println("Invalid input");}
                    break;
            }
        }
        sc.close();
    }
}