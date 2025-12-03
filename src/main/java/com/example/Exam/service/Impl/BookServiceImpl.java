package com.example.Exam.service.Impl;

import com.example.Exam.dto.BookDto;
import com.example.Exam.mapper.BookMapper;
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
    private final BookMapper bookMapper;


    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, Book book) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id = " + id));

        existing.setTitle(book.getTitle());
        existing.setYear(book.getYear());

        bookRepository.save(existing);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


    public List<BookDto> getAllDto() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDtoList(books);
    }

    public BookDto addBookDto(BookDto bookDto) {
        Book entity = bookMapper.toEntity(bookDto);
        Book saved = bookRepository.save(entity);
        return bookMapper.toDto(saved);
    }

    public BookDto updateBookDto(Long id, BookDto bookDto) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id = " + id));

        existing.setTitle(bookDto.getTitle());
        existing.setYear(bookDto.getYear());

        Book saved = bookRepository.save(existing);
        return bookMapper.toDto(saved);
    }
}

