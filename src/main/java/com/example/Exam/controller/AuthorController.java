package com.example.Exam.controller;

import com.example.Exam.model.Author;
import com.example.Exam.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    @GetMapping
    ResponseEntity<?> getAll(){
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<?> addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable(name= "id") Long id , @RequestBody Author author){
        authorService.updateAuthor(id, author);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(name= "id") Long id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}