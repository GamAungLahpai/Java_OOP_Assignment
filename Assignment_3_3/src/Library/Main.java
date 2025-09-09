package Library;

import Library.model.Book;
import Library.model.LibraryMember;
import Library.system.Library;

public class Main {
    public static void main(String[] args) {
        // Create instances of Library, Book, LibraryMember
        Library library = new Library();

        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4");
        Book book3 = new Book("1984", "George Orwell", "978-0-452-28423-4");

        LibraryMember member1 = new LibraryMember("Alice Johnson", 1001);
        LibraryMember member2 = new LibraryMember("Bob Smith", 1002);

        // Add books and members to the library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addMember(member1);
        library.addMember(member2);

        // Perform borrowing operations
        library.borrowBook(member1, book1);
        library.borrowBook(member2, book2);

        // Demonstrate reservation functionality
        System.out.println("\n=== Book Reservation Operations ===");

        // Reserve books
        library.reserveBook(member1, book3);
        library.reserveBook(member2, book1);

        // Try to reserve an already reserved book
        library.reserveBook(member1, book3);

        // Display reserved books for each member
        library.displayReservedBooks(member1);
        library.displayReservedBooks(member2);

        // Cancel reservation
        System.out.println("\n=== Cancel Reservation ===");
        library.cancelReservation(member1, book3);

        // Display reserved books after cancellation
        library.displayReservedBooks(member1);

        // Return books
        System.out.println("\n=== Return Books ===");
        library.returnBook(member1, book1);
        library.returnBook(member2, book2);
    }
}