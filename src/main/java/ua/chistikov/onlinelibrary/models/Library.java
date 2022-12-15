package ua.chistikov.onlinelibrary.models;

public class Library {

    private int person_id;

    private int book_id;

    public Library(){}

    public Library(int person_id, int book_id) {
        this.person_id = person_id;
        this.book_id = book_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "Library{" +
                "person_id=" + person_id +
                ", book_id=" + book_id +
                '}';
    }
}
