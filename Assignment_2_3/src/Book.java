public class Book {
    //Fields private instance variables for book details
    private String title;
    private String author;
    private int publicationYear;

    // Constructor to initialize book object
    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    // Getter method to access title
    public String getTitle() {
        return title;
    }

    // Getter method to access author
    public String getAuthor() {
        return author;
    }

    // Getter method to access publication year
    public int getPublicationYear() {
        return publicationYear;
    }
}
