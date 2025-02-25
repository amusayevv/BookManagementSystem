package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
public class Book {
    private int id;
    private String title;
    private String author;
    private LinkedList<String> genre = new LinkedList<>();
    private boolean availability;

    public Book(String title, String author, LinkedList<String> genre, boolean availability) {
        this.id = incrementID();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public Book(String title, String author) {
        this.id = incrementID();
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

    public int incrementID(){
        String data;
        int newId;
        try(BufferedReader myReader = new BufferedReader(new FileReader("books.csv"))){
            while ((data = myReader.readLine())!=null) {
            }
            if(data == null) data = "0";
            newId = Integer.parseInt(data.split(",")[0].trim()) + 1;
        }
        catch (Exception e) {
        throw new RuntimeException ("file not found");
        }

        return newId;
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
}
