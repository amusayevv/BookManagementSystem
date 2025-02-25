package src;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private LinkedList<String> genre = new LinkedList<>();
    private boolean availability;

    public Book(String title, String author, LinkedList<String> genre, boolean availability) {
        this.id = incrementId();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public Book(String title, String author) {
        this.id = incrementId();
        this.title = title;
        this.author = author;
        this.genre = new LinkedList<String>();
        this.availability = false;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(LinkedList<String> genre) {
        this.genre = genre;
    }

    public LinkedList<String> getGenre() {
        return this.genre;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean getAvailability() {
        return this.availability;
    }

    @Override
    public String toString() {
        String genres = '(' + String.join(",", genre) + ')';
        return id + "," + title + "," + author + "," + genres + "," + availability;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    private int incrementId () {
        String PATH = "books.csv"; 
        String data = "0";
        int newId = 0;
        try (Scanner sc = new Scanner(new File(PATH))) {
            while(sc.hasNextLine()) {
                data = sc.nextLine();
            }
        }
        catch(IOException e) {
            throw new RuntimeException();
        }
        newId = Integer.parseInt(data.split(",")[0].trim());

        return newId + 1;
    }
}
