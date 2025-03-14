import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class ShopCart {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Shopping Cart Application");
        int cont = 1;
        while (cont == 1) {
            System.out.println("1.View available products:");
            System.out.println("2.Add product:");
            System.out.println("3.Remove product:");
            System.out.println("4.View cart:");
            System.out.println("5.Checkout:");
            System.out.println("Enter Your Choice:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    availableProducts();
                    break;

                case 2:
                    System.out.println("Enter product name:");
                    Scanner s1 = new Scanner(System.in);
                    String productName = s1.nextLine();
                    System.out.println("Enter product quantity:");
                    int productQuantity = s1.nextInt();
                    addProduct(productName, productQuantity);
                    break;

                case 3:
                    Scanner s2 = new Scanner(System.in);
                    System.out.println("Enter product name:");
                    productName = s2.nextLine();
                    System.out.println("Enter product quantity:");
                    productQuantity = s2.nextInt();
                    removeProduct(productName, productQuantity);
                    break;

                case 4:
                    viewCart();
                    break;

                case 5:
                    checkout();
                    break;

                default:
                    break;
            }

            System.out.println("Do you want to continue:");
            cont = sc.nextInt();
        }

    }

    static void availableProducts() {
        try {
            FileReader fr = new FileReader("product.txt");
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            while (str != null) {
                System.out.println(str);
                str = br.readLine();
            }
            br.close();
        } catch (Exception e) {
        }

    }

    static void addProduct(String productName, int productQuantity) {
        try {

            FileWriter fw = new FileWriter("userCart.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(productName + "-" + productQuantity);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    static void removeProduct(String productName, int productQuantity) {
        try {
            List<String> arr = new ArrayList<>();
            FileReader fr = new FileReader("userCart.txt");
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            while (str != null) {
                arr.add(str);
                str = br.readLine();
            }
            String quan = String.valueOf(productQuantity);
            String toRemove = productName + "-" + quan;
            arr.remove(toRemove);

            FileWriter fw = new FileWriter("userCart.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            int i = 0;
            while (i < arr.size()) {
                bw.write(arr.get(i));
                i++;
                bw.newLine();
            }
            bw.close();
            br.close();
        } catch (Exception e) {
        }
    }

    static void viewCart() {
        try {
            FileReader fr = new FileReader("userCart.txt");
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            while (str != null) {
                System.out.println(str);
                str = br.readLine();
            }
            br.close();
        } catch (Exception e) {
        }

    }

    static void checkout() {
        try {
            FileReader fr = new FileReader("userCart.txt");
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            TreeMap<String, Integer> map = new TreeMap<>();
            while (str != null) {
                String[] item = str.split("-");
                map.put(item[0], Integer.parseInt(item[1]));
                str = br.readLine();
            }
            br.close();

            FileReader ffr = new FileReader("product.txt");
            BufferedReader bbr = new BufferedReader(ffr);
            String strr = bbr.readLine();
            HashMap<String, Integer> mapp = new HashMap<>();
            while (strr != null) {
                String[] item = strr.split("-");
                mapp.put(item[0], Integer.parseInt(item[1]));
                strr = bbr.readLine();
            }
            bbr.close();
            int sum = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String cartKey = entry.getKey();
                int cartValue = entry.getValue();
                for (Map.Entry<String, Integer> ent : mapp.entrySet()) {
                    String prodKey = ent.getKey();
                    int prodValue = ent.getValue();
                    if (prodKey.equals(cartKey)) {
                        sum = sum + (cartValue * prodValue);
                    }
                }
            }
            System.out.println("Bill : " + sum);
        } catch (Exception e) {
        }

    }

}