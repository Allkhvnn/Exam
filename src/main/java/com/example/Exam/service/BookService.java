package com.example.Exam.service;

import com.example.Exam.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    void addBook(Book book);
    void updateBook(Long id, Book book);
    void deleteBook(Long id);

}
