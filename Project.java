import java.util.*;

class Product {
    private String name;
    private double price; 

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

class User {
    private String username;
    private String password;

    public User(String u, String p) {
        this.username = u;
        this.password = p;
    }

    public boolean validate(String u, String p) {
        return username.equals(u) && password.equals(p);
    }
}

abstract class Category {
    protected List<Product> products = new ArrayList<>();
    protected String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public abstract void loadProducts();
}

class Electronics extends Category {
    public Electronics() {
        categoryName = "Electronics";
        loadProducts();
    }

    public void loadProducts() {
        products.add(new Product("TV", 35000.0));
        products.add(new Product("AC", 40000.0));
        products.add(new Product("Laptop", 60000.0));
    }
}

class Cosmetics extends Category {
    public Cosmetics() {
        categoryName = "Cosmetics";
        loadProducts();
    }

    public void loadProducts() {
        products.add(new Product("Lipstick", 500.0));
        products.add(new Product("Perfume", 1500.0));
        products.add(new Product("Foundation", 800.0));
    }
}

class Groceries extends Category {
    public Groceries() {
        categoryName = "Groceries";
        loadProducts();
    }

    public void loadProducts() {
        products.add(new Product("Rice", 60.0));
        products.add(new Product("Milk", 30.0));
        products.add(new Product("Bread", 25.0));
    }
}

public class Project {
    static Scanner sc = new Scanner(System.in);
    static List<Category> categories = new ArrayList<>();
    static List<Product> cart = new ArrayList<>();
    static User user = new User("user", "pass");

    public static void main(String[] args) {
        categories.add(new Electronics());
        categories.add(new Cosmetics());
        categories.add(new Groceries());

        System.out.println(" Welcome to the Java Shopping App");

        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        if (!user.validate(uname, pass)) {
            System.out.println("Invalid login.");
            return;
        }

        int choice;

        do {
            System.out.println("\n---- Main Menu ----");
            System.out.println("1. View Products");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showCategories();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    checkout();
                    break;
                case 4:
                    System.out.println("Thank you for Shopping! Visit Again!!!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);
    }

    static void showCategories() {
        System.out.println("Available Categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }

        System.out.print("Select a category: ");
        int catChoice = sc.nextInt();
        sc.nextLine();

        if (catChoice < 1 || catChoice > categories.size()) {
            System.out.println("Invalid category choice.");
            return;
        }

        Category selected = categories.get(catChoice - 1);
        List<Product> items = selected.getProducts();

        System.out.println("Products in " + selected.getCategoryName() + ":");
        for (int i = 0; i < items.size(); i++) {
            Product p = items.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " - Rs " + p.getPrice());
        }

        System.out.print("Choose product to add to cart (0 to cancel): ");
        int prodChoice = sc.nextInt();
        sc.nextLine();

        if (prodChoice > 0 && prodChoice <= items.size()) {
            Product chosen = items.get(prodChoice - 1);
            cart.add(chosen);
            System.out.println(chosen.getName() + " added to cart.");
        } else if (prodChoice != 0) {
            System.out.println("Invalid product selection.");
        }
    }

    static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your cart contains:");
            double total = 0;
            for (Product p : cart) {
                System.out.println("- " + p.getName() + " : Rs " + p.getPrice());
                total += p.getPrice();
            }
            System.out.println("Total amount: Rs " + total);
        }
    }

    static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Add items before checkout.");
        } else {
            System.out.println("----- Checkout Summary -----");
            double total = 0;
            for (Product p : cart) {
                System.out.println("- " + p.getName() + " : Rs " + p.getPrice());
                total += p.getPrice();
            }
            System.out.println("Total items: " + cart.size());
            System.out.println("Total amount: Rs " + total);
            cart.clear();
            System.out.println("Thank you for shopping with us!");
        }
    }
}

