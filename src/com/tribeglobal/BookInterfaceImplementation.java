package com.tribeglobal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BookInterfaceImplementation implements BookInterface{
    Connection connection;
    Scanner sc = new Scanner(System.in);
    static int bookQuantity;
    boolean quantityCheck;
    @Override
    public void addBook(Books book) {
        connection = DBConnection.createDBConnection();
        String query = "insert into bookshop values(?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getName());
            statement.setDouble(2, book.getPrice());
            statement.setInt(3, book.quantity);
            int rows = statement.executeUpdate();
            if (rows>0){
                System.out.println("Book Added Successfully");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void showAllBooks() {
        connection = DBConnection.createDBConnection();
        String booksQuery = "select * from bookshop";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(booksQuery);
            while (result.next()){
                String books = "";
                for (int i = 1; i < 4; i++) {
                    books += result.getString(i) + " | ";
                }
                System.out.println(books);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateBookStock(String name, int stock) {
        connection = DBConnection.createDBConnection();
        String updateBooks = "update bookshop set quantity=? where name=?";
        try {
            PreparedStatement statement = connection.prepareStatement(updateBooks);
            statement.setInt(1, stock);
            statement.setString(2, name);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Update Successful");
            }else System.out.println("Book Not Available In The Archive");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateBookPrice(String name, double price) {
        connection = DBConnection.createDBConnection();
        String updateBooks = "update bookshop set price=? where name=?";
        try {
            PreparedStatement statement = connection.prepareStatement(updateBooks);
            statement.setDouble(1, price);
            statement.setString(2, name);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Update Successful");
            }System.out.println("Book Not Available In The Archive");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean buyBook(String bookName) {
        connection = DBConnection.createDBConnection();
        String booksQuery = "select name, price, quantity from bookshop where name=?";
        String updateBooks = "update bookshop set quantity=? where name=?";

        try {
            PreparedStatement statement = connection.prepareStatement(booksQuery);
            statement.setString(1, bookName);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                if (result.getString("name").equalsIgnoreCase(bookName)){
                    String books = "";
                    for (int i = 1; i < 3; i++) {
                        books += result.getString(i) + " | ";
                    }
                    System.out.println(books);
                    System.out.println("Would You Like To Buy It");
                    System.out.println("""
                            1. Yes
                            2. No
                            """);
                    int choice = sc.nextInt();
                    switch (choice){
                        case 1 ->{
                            System.out.println("Enter Quantity");
                            int quantity = sc.nextInt();
                            if (result.getInt("quantity") > quantity) {
                            bookQuantity = result.getInt("quantity") - quantity;
                                PreparedStatement quantityStatement = connection.prepareStatement(updateBooks);
                                quantityStatement.setInt(1, bookQuantity);
                                quantityStatement.setString(2, bookName);
                                quantityStatement.executeUpdate();
                            quantityCheck = true;
                            }else System.out.println("Not Enough Quantity In Stock");
                        }
                        case 2 -> System.out.println("Keep Searching Then!");
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return quantityCheck;
    }
}
