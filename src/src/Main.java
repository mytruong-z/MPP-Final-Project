import handler.DataAccess;
import model.*;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem library = LibrarySystem.getInstance();

        System.out.println("Welcome to the Library Management System\n");

        int choice;
        do {
            System.out.println("1. Add Book");
            System.out.println("2. Add Book Copy");
            System.out.println("3. Check Out Book");
            System.out.println("4. Add New Member");
            System.out.println("5. Display Check Out Record");
            System.out.println("6. Exit\n");
            System.out.print("Please enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter book isbn: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter max: ");
                    int maxCheckoutLength = scanner.nextInt();
                    Book book = new Book(isbn, author, title, maxCheckoutLength);
                    library.addBook(book);
                    System.out.println("Book added successfully!\n");
                    break;

                case 2:
                    System.out.print("Enter book title to add copy: ");
                    String copyTitle = scanner.nextLine();
                    // Assuming some implementation to add copy
                    System.out.println("Book copy added successfully!\n");
                    break;

                case 3:
                    System.out.print("Enter book title to check out: ");
                    String checkoutTitle = scanner.nextLine();
                    System.out.print("Enter member id: ");
                    String memberName = scanner.nextLine();
                    library.checkOutBook(memberName, checkoutTitle);
                    // Assuming some implementation to check out a book
                    System.out.println("Book checked out successfully!\n");
                    break;

                case 4:
                    System.out.print("Enter member id: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter first name: ");
                    String fname = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lname = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter state: ");
                    String state = scanner.nextLine();
                    System.out.print("Enter city: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter street: ");
                    String street = scanner.nextLine();
                    System.out.print("Enter zip code: ");
                    String zip = scanner.nextLine();
                    LibraryMember newMember = new LibraryMember(memberId, fname, lname, phone, new Address(state, city, street, zip));
                    DataAccess.addMember(newMember);
                    System.out.println("New member added successfully!\n");
                    break;
                case 5:
                    System.out.println("Check Out Record:");
                    // Assuming some implementation to display check out record
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        } while (choice != 6);

        scanner.close();
    }
}