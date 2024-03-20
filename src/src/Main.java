import handler.DataAccess;
import model.*;

import java.time.LocalDate;
import java.util.List;
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
                case -1:
                    library.getAllMembers().forEach(System.out::println);
                    break;
                case 0:
                    library.getAllBooks().forEach(System.out::println);
                    break;
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
                    System.out.print("Please enter the book isbn: ");
                    String isbnNumber = scanner.nextLine();
                    System.out.print("Please enter the publisher: ");
                    String publisher = scanner.nextLine();
                    System.out.print("Please enter the number of copy: ");
                    String numCopies = scanner.nextLine();
                    library.addBookCopy(isbnNumber, publisher, Integer.parseInt(numCopies));
                    System.out.println("Book copy added successfully!\n");
                    break;
                case 3:
                    System.out.print("Enter book isbn to check out: ");
                    String checkoutTitle = scanner.nextLine();
                    System.out.print("Enter member id: ");
                    String memberName = scanner.nextLine();
                    Boolean result = library.checkOutBook(memberName, checkoutTitle);
                    if(result){
                        // Assuming some implementation to check out a book
                        System.out.println("Book checked out successfully!\n");
                    }
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
                    System.out.print("Enter due date: ");
                    int dueDate = scanner.nextInt();
                    // Assuming some implementation to display check out record
                    LocalDate date = LocalDate.now().plusDays(dueDate);
//                    DataAccess.getMembers()
//                            .stream()
//                            .flatMap(member -> member.getCheckoutRecord().getCheckOutRecordEntries().stream())
//                                    .filter(entry -> entry.dueDate().isBefore(date))
//                                            .toList();

                    DataAccess.getMembers().stream()
                            .filter(member -> member.getCheckoutRecord().getCheckOutRecordEntries().stream()
                                    .anyMatch(entry -> entry.dueDate().isBefore(date)))
                                    .forEach(m -> {
                                        System.out.println("Id:"+m.memberId() + " Name:" + m.firstName() + " " + m.lastName());
                                        m.getCheckoutRecord().getCheckOutRecordEntries().stream()
                                                .filter(entry -> entry.dueDate().isBefore(date))
                                                .forEach(e -> System.out.println("    Book isbn:" + e.getIsbn() + " Due Date:" + e.dueDate()));

                                    });


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