package com.example.Exam.controller;

import com.example.Exam.model.Book;
import com.example.Exam.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Book book) {
        bookService.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name= "id") Long id,@RequestBody Book book){
        bookService.updateBook(id,book);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name= "id") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

