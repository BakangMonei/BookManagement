package bean;

/**
 * @Author: Monei Bakang Mothuti
 * @Date: Sunday 12 May 2024
 * @Time: 8:24 PM
 */
import java.util.HashSet;

public class Inventory {
    private HashSet<Book> books;

    public Inventory() {
        books = new HashSet<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public boolean containsBook(Book book) {
        return books.contains(book);
    }

    public int size() {
        return books.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory{\n");
        for (Book book : books) {
            sb.append(book.toString()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}