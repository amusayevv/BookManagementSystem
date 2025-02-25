package src;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String PATH = "books.csv";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {

            System.out.println("1. Add a new book");
            System.out.println("2. Show all books");
            System.out.println("3. Search a book");
            System.out.println("4. Delete a book");
            System.out.println("5. Exit");
            int userInput;

            try {
                userInput = sc.nextInt();
                sc.nextLine();

            }
            catch(NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 5.");
                continue;
            }
            
            switch(userInput) {        
                case 1 -> addBook();
                // case 2 -> showAllBooks();
                // case 3 -> searchBook();
                // case 4 -> deleteBook();
                case 5 -> {System.out.println("Exiting the program..."); return;}
            }
    
        }
    }

    public static void addBook() {
        System.out.println("Please enter book title");
        String bookTitle = sc.nextLine();
        System.out.println("Please enter book author");
        String bookAuthor = sc.nextLine();
        System.out.println("Please enter book genre. You can enter multiple genres separated by comma");
        String bookGenreString = sc.nextLine();
        List<String> bookGenrelist = Arrays.asList(bookGenreString.split(", "));
        System.out.println("Please enter \"true\" if the book is available, and \"false\" if not");
        String bookAvailableString = sc.nextLine();
        boolean bookAvailable = bookAvailableString.equalsIgnoreCase("true");

        Book addedBook = new Book(bookTitle, bookAuthor, new LinkedList<>(bookGenrelist), bookAvailable);

        try(FileWriter writer = new FileWriter(PATH, true)) {
            writer.write(addedBook.toString());
            writer.write(System.lineSeparator());
        } 
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteBook() {
        
    }
}