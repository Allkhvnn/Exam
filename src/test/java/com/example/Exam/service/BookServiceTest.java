package com.example.Exam.service;

import com.example.Exam.model.Book;
import com.example.Exam.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void getAllTest() {
        Book book = new Book();
        book.setTitle("getAllTest book");
        book.setYear(2024);
        bookRepository.save(book);

        List<Book> books = bookService.getAll();

        Assertions.assertNotNull(books);
        Assertions.assertNotEquals(0, books.size());

        for (Book b : books) {
            Assertions.assertNotNull(b);
            Assertions.assertNotNull(b.getId());
            Assertions.assertNotNull(b.getTitle());
        }
    }

    @Test
    void addBookTest() {
        Book book = new Book();
        book.setTitle("addBookTest title");
        book.setYear(2020);

        bookService.addBook(book);

        List<Book> books = bookRepository.findAll();
        Assertions.assertNotNull(books);
        Assertions.assertNotEquals(0, books.size());

        boolean found = false;
        for (Book b : books) {
            if ("addBookTest title".equals(b.getTitle()) && b.getYear() == 2020) {
                found = true;
                Assertions.assertNotNull(b.getId());
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    void updateBookTest() {
        Book book = new Book();
        book.setTitle("before update");
        book.setYear(2000);
        Book saved = bookRepository.save(book);

        Book updateData = new Book();
        updateData.setId(saved.getId());
        updateData.setTitle("after update");
        updateData.setYear(2025);

        bookService.updateBook(saved.getId(), updateData);

        Book updated = bookRepository.findById(saved.getId()).orElse(null);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("after update", updated.getTitle());
        Assertions.assertEquals(2025, updated.getYear());
    }

    @Test
    void deleteBookTest() {
        Book book = new Book();
        book.setTitle("to delete");
        book.setYear(1999);
        Book saved = bookRepository.save(book);

        bookService.deleteBook(saved.getId());

        boolean exists = bookRepository.findById(saved.getId()).isPresent();
        Assertions.assertFalse(exists);
    }

    @Test
    void getRandomBookFromListTest() {
        if (bookService.getAll().isEmpty()) {
            Book book = new Book();
            book.setTitle("random base");
            book.setYear(2010);
            bookService.addBook(book);
        }

        List<Book> books = bookService.getAll();
        Assertions.assertNotNull(books);
        Assertions.assertNotEquals(0, books.size());

        Random random = new Random();
        int index = random.nextInt(books.size());

        Book randomBook = books.get(index);

        Assertions.assertNotNull(randomBook);
        Assertions.assertNotNull(randomBook.getId());
        Assertions.assertNotNull(randomBook.getTitle());
    }
}
