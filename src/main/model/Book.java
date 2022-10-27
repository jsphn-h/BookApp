package model;

// This class creates Book objects with title and author, and possibly series number and  ISBN
public class Book {
    private String title;   // title of the book
    private String author;  // author of the book
    private int seriesNum;  // book x in the series (0 if it's not part of a series)
    private String isbn; // ISBN number

    private Genre genre;

    /*
        REQUIRES: title and author has a non-zero length, genre
        EFFECTS: book title is set to title; author is set to author
                 if the book is part of a series, then book seriesNum is set to bookNum, otherwise it is 0
                 if there is an ISBN then it is set to isbn, otherwise it is set to null
     */

    public Book(String title, String author, Genre genre) {
        this(title, author, genre, 0);
    }

    public Book(String title, String authorName, Genre genre, int bookNum) {
        this.title = title;
        author = authorName;
        this.genre = genre;
        seriesNum = bookNum;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getSeriesNum() {
        return seriesNum;
    }

    //EFFECTS: returns a string representation of the book information
    public String toString() {
        return "Title: " + getTitle() + "\nAuthor: " + getAuthor()
                + "\nGenre: " + getGenre() + "\nBook " + getSeriesNum() + " in the series";
    }
}
