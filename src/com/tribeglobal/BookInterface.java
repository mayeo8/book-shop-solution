package com.tribeglobal;

public interface BookInterface {
    public void addBook(Books books);
    public void showAllBooks();
    public void updateBookStock(String name, int stock);
    public void updateBookPrice(String name, double price);
    public boolean buyBook(String bookName);
}
