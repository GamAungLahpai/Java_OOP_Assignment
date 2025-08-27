public class LibraryMain {
    public static void main(String[] args) {
        // Create Book instance with different titles, authors and years
        Book book1 = new Book("Introduction to Java Programming", "John Smith", 2020);
        Book book2 = new Book("Data Structures and Algorithms", "Jane Doe", 2018);
        Book book3 = new Book("The Art of Fiction", "Alice Johnson", 2019);

        // Create a Library instance
        Library library = new Library();

        // Add books to the library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        library.displayBooks();

        System.out.println();

        // Search for books by a specific author
        library.findBooksByAuthor("Jane Doe");


        //Task_3
        System.out.println();
        System.out.println("Task_3");

        // Check book availability
        System.out.println("Checking book availability:");
        System.out.println("Is 'Data Structures and Algorithms' available? " +
                library.isBookAvailable("Data Structures and Algorithms"));
        System.out.println("Is 'Nonexistent Book' available? " +
                library.isBookAvailable("Nonexistent Book"));
    }
}