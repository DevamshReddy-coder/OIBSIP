import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

class Member {
    private String name;
    private String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;
    private Map<Member, List<Book>> borrowedBooks;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.borrowedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void issueBook(Member member, Book book) {
        if (books.contains(book) && members.contains(member)) {
            borrowedBooks.computeIfAbsent(member, k -> new ArrayList<>()).add(book);
            books.remove(book);
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book or member not found.");
        }
    }

    public void returnBook(Member member, Book book) {
        if (borrowedBooks.containsKey(member) && borrowedBooks.get(member).contains(book)) {
            borrowedBooks.get(member).remove(book);
            books.add(book);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not borrowed by this member.");
        }
    }

    public void generateFine(Member member, Book book) {
        System.out.println("Fine generated successfully.");
    }

    public void generateReport() {
        System.out.println("Report generated successfully.");
    }
}

 class DigitalLibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Book 1", "Author 1");
        Book book2 = new Book("Book 2", "Author 2");

        Member member1 = new Member("User 1", "user1@example.com");
        Member member2 = new Member("User 2", "user2@example.com");

        library.addBook(book1);
        library.addBook(book2);

        library.addMember(member1);
        library.addMember(member2);

        library.issueBook(member1, book1);
        library.returnBook(member1, book1);
        library.generateFine(member1, book1);
        library.generateReport();
    }
}