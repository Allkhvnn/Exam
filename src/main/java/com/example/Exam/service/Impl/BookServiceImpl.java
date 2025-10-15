package com.example.Exam.service.Impl;

import com.example.Exam.model.Author;
import com.example.Exam.model.Book;
import com.example.Exam.repository.BookRepository;
import com.example.Exam.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @Override
    public void addBook(Book book){
        bookRepository.save(book);
    }
    @Override
    public void updateBook(Long id,Book book) {
        Book updated = bookRepository.findById(book.getId()).orElse(null);
        updated.setTitle(book.getTitle());
        updated.setYear(book.getYear());
        bookRepository.save(updated);
    }

    @Override
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }


}
