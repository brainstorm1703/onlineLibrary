package ua.chistikov.onlinelibrary.models;

public class Book {

    private int book_id;
    private int releaseYear;
    private String authorName;

    private String name;

    public Book(){}

    public Book(int book_id, int releaseYear, String nameAuthor, String name) {
        this.book_id = book_id;
        this.releaseYear = releaseYear;
        this.authorName = nameAuthor;
        this.name = name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String nameAuthor) {
        this.authorName = nameAuthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "releaseYear=" + releaseYear +
                ", authorName='" + authorName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
