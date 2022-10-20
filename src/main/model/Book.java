package model;

public class Book {
    private String title;   // title of the book
    private String author;  // author of the book
    private String isbnNum; // ISBN number
    private int seriesNum;  // book x in the series (0 if it's not part of a series)

    /*
        REQUIRES: title has a non-zero length
        EFFECTS: book title is set to title; author is set to authorName
                 if there is an ISBN number then it is set to numISBN,
                 otherwise it is set to null; if it is part of a series then the
                 book seriesNum is set to bookNum, otherwise it is 0.
     */
    public Book(String title, String authorName, String numISBN, int bookNum) {
        this.title = title;
        author = authorName;

        if (numISBN != null) {
            isbnNum = numISBN;
        } else {
            isbnNum = "N/A";
        }

        if (bookNum != 0) {
            seriesNum = bookNum;
        } else {
            seriesNum = 0;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbnNum() {
        return isbnNum;
    }

    public int getSeriesNum() {
        return seriesNum;
    }

    public String toString() {
        String bookInfo = "Title: " + getTitle() + "\nAuthor: " + getAuthor()
                + "\nISBN: " + getIsbnNum() + "\nBook " + getSeriesNum() + " in the series";
        return bookInfo;
    }
}
