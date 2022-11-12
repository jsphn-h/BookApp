package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

// This class creates Book objects with title, author, series number and  genre
public class Book implements Writeable {
    private String title;   // title of the book
    private String author;  // author of the book
    private int seriesNum;  // book x in the series (0 if it's not part of a series)
    private Genre genre;
    private Lists list;

    /*
        REQUIRES: title and author has a non-zero length, genre
        EFFECTS: book title is set to title; author is set to author
                 if the book is part of a series, then book seriesNum is set to seriesNum, otherwise it is 0
     */

    public Book(String title, String author, int seriesNum, Genre genre, Lists list) {
        this.title = title;
        this.author = author;
        this.seriesNum = seriesNum;
        this.genre = genre;
        this.list = list;
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

    public Lists getList() {
        return list;
    }

    //EFFECTS: returns a string representation of the book information
    public String toString() {
        return "Title: " + getTitle() + "\nAuthor: " + getAuthor()
                + "\nGenre: " + getGenre() + "\nBook " + getSeriesNum() + " in the series";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("num", seriesNum);
        json.put("genre", genre);
        json.put("lists", list);
        return json;
    }

}
