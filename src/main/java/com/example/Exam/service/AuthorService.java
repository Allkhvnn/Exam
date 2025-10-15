package com.example.Exam.service;

import com.example.Exam.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    void addAuthor(Author author);
    void updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
}
