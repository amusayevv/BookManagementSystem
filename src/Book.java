package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private LinkedList<String> genre = new LinkedList<>();
    private boolean availability;

    public Book(String title, String author, LinkedList<String> genre, boolean availability) {
        try {
            String path = "books.csv";
            Scanner myReader = new Scanner(new File(path));
            String data = "0";
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            this.id = Integer.parseInt(data.split(",")[0].trim()) + 1;
                myReader.close();
            } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public Book(String title, String author) {
        try {
            String path = "books.csv";
            Scanner myReader = new Scanner(new File(path));
            String data = "0";
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            this.id = Integer.parseInt(data.split(",")[0].trim()) + 1;
            myReader.close();
            } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Check for null and type
        Book book = (Book) o; // Cast to Book
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
