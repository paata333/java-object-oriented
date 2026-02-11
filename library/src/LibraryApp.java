import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String author;
    private boolean isBorrowed;
    private String borrowedBy;
    private LocalDateTime borrowedDate;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
        this.borrowedBy = null;
        this.borrowedDate = null;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return isBorrowed; }
    public String getBorrowedBy() { return borrowedBy; }
    public LocalDateTime getBorrowedDate() { return borrowedDate; }

    public void setBorrowed(boolean borrowed) { this.isBorrowed = borrowed; }
    public void setBorrowedBy(String borrowedBy) { this.borrowedBy = borrowedBy; }
    public void setBorrowedDate(LocalDateTime borrowedDate) { this.borrowedDate = borrowedDate; }

    @Override
    public String toString() {
        String status = isBorrowed ? "Borrowed by " + borrowedBy + " on " +
                borrowedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "Available";
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Status: " + status;
    }
}

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private List<Integer> borrowedBooks;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public List<Integer> getBorrowedBooks() { return borrowedBooks; }

    public void addBorrowedBook(int bookId) {
        borrowedBooks.add(bookId);
    }

    public void returnBook(int bookId) {
        borrowedBooks.remove(Integer.valueOf(bookId));
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Books Borrowed: " + borrowedBooks.size();
    }
}

class LibraryState implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Integer, Book> books;
    private Map<Integer, Student> students;
    private int nextBookId;
    private int nextStudentId;

    public LibraryState(Map<Integer, Book> books, Map<Integer, Student> students,
                        int nextBookId, int nextStudentId) {
        this.books = books;
        this.students = students;
        this.nextBookId = nextBookId;
        this.nextStudentId = nextStudentId;
    }

    public Map<Integer, Book> getBooks() { return books; }
    public Map<Integer, Student> getStudents() { return students; }
    public int getNextBookId() { return nextBookId; }
    public int getNextStudentId() { return nextStudentId; }
}

class LibraryManager {
    private Map<Integer, Book> books;
    private Map<Integer, Student> students;
    private int nextBookId;
    private int nextStudentId;

    public LibraryManager() {
        this.books = new HashMap<>();
        this.students = new HashMap<>();
        this.nextBookId = 1;
        this.nextStudentId = 1;
    }

    public int addBook(String title, String author) {
        int bookId = nextBookId;
        books.put(bookId, new Book(bookId, title, author));
        nextBookId++;
        return bookId;
    }

    public String removeBook(int bookId) {
        if (!books.containsKey(bookId)) {
            return "Book not found";
        }

        Book book = books.get(bookId);
        if (book.isBorrowed()) {
            return "Cannot remove book because it is currently borrowed";
        }

        books.remove(bookId);
        return "Book removed successfully";
    }

    public int addStudent(String name) {
        int studentId = nextStudentId;
        students.put(studentId, new Student(studentId, name));
        nextStudentId++;
        return studentId;
    }

    public String borrowBook(int bookId, int studentId) {
        if (!books.containsKey(bookId)) {
            return "Book not found";
        }

        if (!students.containsKey(studentId)) {
            return "Student not found";
        }

        Book book = books.get(bookId);
        Student student = students.get(studentId);

        if (book.isBorrowed()) {
            return "Book is already borrowed";
        }

        book.setBorrowed(true);
        book.setBorrowedBy(student.getName());
        book.setBorrowedDate(LocalDateTime.now());
        student.addBorrowedBook(bookId);

        return "Book '" + book.getTitle() + "' borrowed by " + student.getName();
    }

    public String returnBook(int bookId) {
        if (!books.containsKey(bookId)) {
            return "Book not found";
        }

        Book book = books.get(bookId);

        if (!book.isBorrowed()) {
            return "This book is not currently borrowed";
        }

        // Find which student has borrowed this book
        for (Student student : students.values()) {
            if (student.getBorrowedBooks().contains(bookId)) {
                student.returnBook(bookId);
                break;
            }
        }

        book.setBorrowed(false);
        book.setBorrowedBy(null);
        book.setBorrowedDate(null);

        return "Book '" + book.getTitle() + "' returned successfully";
    }

    public String saveState(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            filename = "library_data.ser";
        }

        LibraryState state = new LibraryState(books, students, nextBookId, nextStudentId);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(state);
            return "Library state saved to " + filename;
        } catch (IOException e) {
            return "Error saving library state: " + e.getMessage();
        }
    }

    public String loadState(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            filename = "library_data.ser";
        }

        File file = new File(filename);
        if (!file.exists()) {
            return "File " + filename + " not found";
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            LibraryState state = (LibraryState) ois.readObject();

            this.books = state.getBooks();
            this.students = state.getStudents();
            this.nextBookId = state.getNextBookId();
            this.nextStudentId = state.getNextStudentId();

            return "Library state loaded from " + filename;
        } catch (IOException | ClassNotFoundException e) {
            return "Error loading library state: " + e.getMessage();
        }
    }

    public String listBooks() {
        if (books.isEmpty()) {
            return "No books in the library";
        }

        StringBuilder sb = new StringBuilder();
        for (Book book : books.values()) {
            sb.append(book.toString()).append("\n");
        }
        return sb.toString();
    }

    public String listStudents() {
        if (students.isEmpty()) {
            return "No students registered";
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : students.values()) {
            sb.append(student.toString()).append("\n");
        }
        return sb.toString();
    }

    public String listBorrowedBooks() {
        List<Book> borrowedBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isBorrowed()) {
                borrowedBooks.add(book);
            }
        }

        if (borrowedBooks.isEmpty()) {
            return "No books are currently borrowed";
        }

        StringBuilder sb = new StringBuilder();
        for (Book book : borrowedBooks) {
            sb.append(book.toString()).append("\n");
        }
        return sb.toString();
    }
}

public class LibraryApp {
    private LibraryManager manager;
    private Scanner scanner;
    private boolean running;

    public LibraryApp() {
        this.manager = new LibraryManager();
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public void displayMenu() {
        System.out.println("\n===== Library Management System =====");
        System.out.println("1. Add a new book");
        System.out.println("2. Remove a book");
        System.out.println("3. Register a new student");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. List all books");
        System.out.println("7. List all students");
        System.out.println("8. List borrowed books");
        System.out.println("9. Save library state");
        System.out.println("10. Load library state");
        System.out.println("0. Exit");
        System.out.println("====================================");
    }

    public void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        int bookId = manager.addBook(title, author);
        System.out.println("Book added successfully with ID: " + bookId);
    }

    public void removeBook() {
        try {
            System.out.print("Enter book ID to remove: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            String message = manager.removeBook(bookId);
            System.out.println(message);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    public void registerStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        int studentId = manager.addStudent(name);
        System.out.println("Student registered successfully with ID: " + studentId);
    }

    public void borrowBook() {
        try {
            System.out.print("Enter book ID: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            String message = manager.borrowBook(bookId, studentId);
            System.out.println(message);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers for IDs.");
        }
    }

    public void returnBook() {
        try {
            System.out.print("Enter book ID to return: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            String message = manager.returnBook(bookId);
            System.out.println(message);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    public void listBooks() {
        System.out.println("\n=== Books in Library ===");
        System.out.println(manager.listBooks());
    }

    public void listStudents() {
        System.out.println("\n=== Registered Students ===");
        System.out.println(manager.listStudents());
    }

    public void listBorrowedBooks() {
        System.out.println("\n=== Borrowed Books ===");
        System.out.println(manager.listBorrowedBooks());
    }

    public void saveState() {
        System.out.print("Enter filename to save (default: library_data.ser): ");
        String filename = scanner.nextLine().trim();
        String message = manager.saveState(filename);
        System.out.println(message);
    }

    public void loadState() {
        System.out.print("Enter filename to load (default: library_data.ser): ");
        String filename = scanner.nextLine().trim();
        String message = manager.loadState(filename);
        System.out.println(message);
    }

    public void run() {
        System.out.println("Welcome to Library Management System!");

        while (running) {
            displayMenu();
            System.out.print("Enter your choice (0-10): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    removeBook();
                    break;
                case "3":
                    registerStudent();
                    break;
                case "4":
                    borrowBook();
                    break;
                case "5":
                    returnBook();
                    break;
                case "6":
                    listBooks();
                    break;
                case "7":
                    listStudents();
                    break;
                case "8":
                    listBorrowedBooks();
                    break;
                case "9":
                    saveState();
                    break;
                case "10":
                    loadState();
                    break;
                case "0":
                    System.out.println("Thank you for using Library Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        LibraryApp app = new LibraryApp();
        app.run();
    }
}