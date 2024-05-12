package bean;

/**
 * @Author: Monei Bakang Mothuti
 * @Date: Sunday 12 May 2024
 * @Time: 8:22 PM
 */
public class Book {
    private String isbn, title, author, genre;
    private double price;
    private int quantity;

    public Book(String isbn, String title, String author, String genre, double price, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
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
        return
                isbn + '\'' +
                title + '\'' +
                author + '\'' +
                 genre + '\'' +
                price + '\'' +
                 quantity  + '\'';
    }
}