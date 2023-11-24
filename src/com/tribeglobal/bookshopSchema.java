package com.tribeglobal;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class bookshopSchema {
    public static void main(String[] args) {
//        String bookPrice = plus(3,"RICH DAD POOR DAD" );
//        System.out.println(bookPrice);

        Map<String, Double> bookMap = new HashMap<>();


        bookMap.put("rich dad poor dad", 39.99);
        bookMap.put("entrepreneurs mindset", 28.99);
        bookMap.put("the richest man in babylon", 69.99);
        bookMap.put("harry potter", 58.99);


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name of book: ");
        String bookInput = scanner.nextLine();


        if (bookMap.containsKey(bookInput)){
            System.out.println("Hi there " + bookInput + " is available for: " + NumberFormat.getCurrencyInstance().format(bookMap.get(bookInput)));
            System.out.println("Do you want to make purchase (Yes / No): ");
            String confirmation  = scanner.nextLine();


            if (confirmation.equals("yes")){
                System.out.print("Quantity ( in numbers ): ");
                String qty = scanner.nextLine();
                double bookQuantity = Double.parseDouble(qty);
                double bookPrice = bookMap.get(bookInput);
                double total = bookQuantity * bookPrice;
                System.out.println("you ordered " + qty + " copies of " + bookInput + " the total cost is: " + NumberFormat.getCurrencyInstance().format(total));
                System.out.println("you will now be charged: " + NumberFormat.getCurrencyInstance().format(total) + " from your account thanks for shopping with us.");
            }
            else {
                System.out.println("thanks for stopping by!!");
            }
        }
        else {
            System.out.println("sorry we are out of stock");
        }
    }
}
