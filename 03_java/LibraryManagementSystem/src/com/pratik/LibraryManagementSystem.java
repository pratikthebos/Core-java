package com.pratik;

import java.util.*;

public class LibraryManagementSystem {

    static class Book {

        private int bookId;
        private String title;
        private String author;
        private boolean issued;

        public Book(int bookId,
                    String title,
                    String author) {

            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.issued = false;
        }

        public int getBookId() {
            return bookId;
        }

        public boolean isIssued() {
            return issued;
        }

        public void issueBook() {
            issued = true;
        }

        public void returnBook() {
            issued = false;
        }

        @Override
        public String toString() {

            return "Book ID: " + bookId +
                    ", Title: " + title +
                    ", Author: " + author +
                    ", Status: " +
                    (issued ? "Issued" : "Available");
        }
    }

    static class Library {

        private Map<Integer, Book> books;

        public Library() {
            books = new HashMap<>();
        }

        public void addBook(Book book) {

            books.put(
                    book.getBookId(),
                    book
            );

            System.out.println(
                    "Book Added Successfully");
        }

        public void issueBook(int bookId) {

            Book book = books.get(bookId);

            if (book == null) {

                System.out.println(
                        "Book Not Found");
                return;
            }

            if (book.isIssued()) {

                System.out.println(
                        "Book Already Issued");
                return;
            }

            book.issueBook();

            System.out.println(
                    "Book Issued Successfully");
        }

        public void returnBook(int bookId) {

            Book book = books.get(bookId);

            if (book == null) {

                System.out.println(
                        "Book Not Found");
                return;
            }

            book.returnBook();

            System.out.println(
                    "Book Returned Successfully");
        }

        public void searchBook(int bookId) {

            Book book = books.get(bookId);

            if (book == null) {

                System.out.println(
                        "Book Not Found");
                return;
            }

            System.out.println(book);
        }

        public void displayBooks() {

            System.out.println(
                    "\nLibrary Books:");

            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }

    public static void main(String[] args) {

        Library library = new Library();

        library.addBook(
                new Book(
                        101,
                        "Clean Code",
                        "Robert Martin"));

        library.addBook(
                new Book(
                        102,
                        "Effective Java",
                        "Joshua Bloch"));

        library.addBook(
                new Book(
                        103,
                        "Design Patterns",
                        "GoF"));

        library.displayBooks();

        library.issueBook(102);

        library.searchBook(102);

        library.returnBook(102);

        library.displayBooks();
    }
}
