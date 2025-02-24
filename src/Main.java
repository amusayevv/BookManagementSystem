package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = "books.csv";

        System.out.println("1. Add a new book");
        System.out.println("2. Show all books");
        System.out.println("3. Search a book");
        System.out.println("4. Delete a book");
        // System.out.println("5. Exit");

        Scanner sc = new Scanner(System.in);
        int userInput = sc.nextInt();
        sc.nextLine();

        if (userInput == 5) System.exit(0);

        if(userInput == 1) {
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
            System.out.println(addedBook.toString());
            try(FileWriter writer = new FileWriter(path, true)) {
                writer.write(addedBook.toString());
                writer.write(System.lineSeparator());
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    
        if(userInput == 2) {
            try {
                Scanner myReader = new Scanner(new File(path));
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
                } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        
        if(userInput == 3) {
            System.out.println("Input book title to search");
            String searchTitle = sc.nextLine().toLowerCase().trim().replace(" ", "");

            try {
                Scanner myReader = new Scanner(new File(path));
                boolean found = false;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String dataTitle = data.split(",")[1].toLowerCase().trim().replace(" ", "");
                    if(dataTitle.equals(searchTitle)) {
                        System.out.println(data);
                        found = true;
                    }
                }
                if(!found) System.out.println("Book not found");
                myReader.close();
                } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    
        if(userInput == 4) {
            System.out.println("Please enter a book ID to remove");
            String idToRemove = sc.nextLine();
            LinkedList<String> allBooks = new LinkedList<>();
            
            try {
                Scanner myReader = new Scanner(new File(path));
                boolean found = false;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String dataId = data.split(",")[0].trim();
                    
                    if(!dataId.equals(idToRemove)) {
                        allBooks.add(data);
                        found = true;
                    }
                }

                try (FileWriter writer = new FileWriter(path, false)) {
                } catch (IOException e) {
                    System.out.println("Error clearing file: " + e.getMessage());
                }

                int NewId = 1;
                for (String book : allBooks) {
                    
                    int firstCommaIndex = book.indexOf(',');
                    String result = book.substring(firstCommaIndex + 1);                
                    try(FileWriter writer = new FileWriter(path, true)) {
                        writer.write(String.valueOf(NewId++) + ',' + result);
                        writer.write(System.lineSeparator());
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(!found) System.out.println("Book not found");
                else System.out.println("Book deleted successfully");
                myReader.close();
                } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
        }
    }
}