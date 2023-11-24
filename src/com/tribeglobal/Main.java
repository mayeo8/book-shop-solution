package com.tribeglobal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice;
        String bookName;
        Scanner sc = new Scanner(System.in);
        BookInterfaceImplementation methods  = new BookInterfaceImplementation();
        System.out.println("Welcome To Your Online Bookshop");
        while (true){
            System.out.println("""
                1. Add New Books
                2. Buy Books
                3. Update Book Stock
                4. Update Book Price
                5. Quit
                """);
            choice = sc.nextInt();
            switch (choice){
                case 1 -> {
                    Books books = new Books();
                    System.out.println("Enter Name Of Book");
                    bookName = sc.next();
                    System.out.println("Enter Book Price");
                    double bookPrice = sc.nextDouble();
                    System.out.println("Enter Book Quantity");
                    int bookQuantity = sc.nextInt();
                    books.setName(bookName);
                    books.setPrice(bookPrice);
                    books.setQuantity(bookQuantity);
                    methods.addBook(books);
                }
                case 2 -> {
                    System.out.println("""
                            1. Search For Specific Book
                            2. Search Through Our Archive
                            """);
                    choice = sc.nextInt();
                    switch (choice){
                        case 1 -> {
                            System.out.println("Enter Book Name");
                            bookName = sc.next();
                            if(methods.buyBook(bookName)){
                                System.out.println("Purchase Successful");
                            }else System.out.println("Sorry This Book Is Currently Unavailable");
                        }
                        case 2 -> {
                            System.out.println("There Are The Books That Are In Stock");
                            methods.showAllBooks();
                            System.out.println("Enter Book Name");
                            bookName = sc.next();
                            if(methods.buyBook(bookName)){
                                System.out.println("Purchase Successful");
                            }else System.out.println("Sorry This Book Is Currently Unavailable");
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Enter Book Name");
                    bookName = sc.next();
                    System.out.println("Enter New Stock");
                    int stock = sc.nextInt();
                    methods.updateBookStock(bookName, stock);
                }
                case 4 -> {
                    System.out.println("Enter Book Name");
                    bookName = sc.next();
                    System.out.println("Enter New Price");
                    double price = sc.nextDouble();
                    methods.updateBookPrice(bookName, price);

                }
                case 5 -> {
                    System.out.println("Thank You For Your Time");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Input");
            }

        }
    }
}
