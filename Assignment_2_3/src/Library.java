import java.lang.reflect.Method;
import java.util.ArrayList;

public class Library {
    // ArrayList to store Book objects (association relation)
    private ArrayList<Book> books = new ArrayList<>();

    // Method to add a book to the Library collection
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to display all books in the Library
    public void displayBooks() {
        System.out.println("Library Catalog:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". Title: \"" + book.getTitle() +
                    "\", Author: \"" + book.getAuthor() +
                    "\", Year: " + book.getPublicationYear());
        }
    }

    // Method to find and display books by a specific author
    public void findBooksByAuthor(String author) {
        System.out.println("Books by Author \"" + author + "\":");
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                System.out.println("Title: \"" + book.getTitle() +
                        "\", Year: " + book.getPublicationYear());
            }
        }
    }

    // Method to borrow a book (remove from library)
    public void borrowBook(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(title)) {
                books.remove(i);
                break;
            }
        }
    }

    // Method to return a book (add back to Library)
    public void returnBook(Book book) {
        books.add(book);
    }

    // Task 3
    // Method to check if a book is available in the library
    public boolean isBookAvailable(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}
