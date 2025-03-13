import java.io.*;
import java.util.*;

class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " - rs." + price + " x " + quantity;
    }
}

class ShoppingCart {
    private ArrayList<Item> cart;
    private static final String FILE_NAME = "cartData.txt";

    public ShoppingCart() {
        cart = new ArrayList<>();
        loadCartFromFile();
    }

    public void addItem(String name, double price, int quantity) {
        cart.add(new Item(name, price, quantity));
        saveCartToFile();
    }

    public void removeItem(String name) {
        cart.removeIf(item -> item.getName().equalsIgnoreCase(name));
        saveCartToFile();
    }

    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your Shopping Cart:");
            for (Item item : cart) {
                System.out.println(item);
            }
        }
    }

    private void saveCartToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Item item : cart) {
                writer.println(item.getName() + "," + item.getPrice() + "," + item.getQuantity());
            }
        } catch (IOException e) {
            System.out.println("Error saving cart: " + e.getMessage());
        }
    }

    private void loadCartFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    cart.add(new Item(name, price, quantity));
                }
            }
        } catch (IOException e) {
            cart = new ArrayList<>();
        }
    }
}

public class ShoppingCartApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("\n1. Add Item\n2. Remove Item\n3. View Cart\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    cart.addItem(name, price, quantity);
                    break;
                case 2:
                    System.out.print("Enter item name to remove: ");
                    String removeName = scanner.nextLine();
                    cart.removeItem(removeName);
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}