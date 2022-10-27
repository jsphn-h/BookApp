package model;

// This class creates Book objects with title and author, and possibly series number and  ISBN
public class Book {
    private String title;   // title of the book
    private String author;  // author of the book
    private int seriesNum;  // book x in the series (0 if it's not part of a series)
    private String isbn; // ISBN number

    /*
        REQUIRES: title and author has a non-zero length
        EFFECTS: book title is set to title; author is set to author
                 if the book is part of a series, then book seriesNum is set to bookNum, otherwise it is 0
                 if there is an ISBN then it is set to isbn, otherwise it is set to null
     */

    public Book(String title, String author) {
        this(title, author, 0);
    }

    public Book(String title, String author, int bookNum) {
        this(title, author, bookNum, null);
    }

    public Book(String title, String authorName, int bookNum, String isbn) {
        this.title = title;
        author = authorName;
        seriesNum = bookNum;

        if (isbn != null) {
            this.isbn = isbn;
        } else {
            this.isbn = "N/A";
        }
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getSeriesNum() {
        return seriesNum;
    }

    public String toString() {
        return "Title: " + getTitle() + "\nAuthor: " + getAuthor()
                + "\nISBN: " + getIsbn() + "\nBook " + getSeriesNum() + " in the series";
    }
}
