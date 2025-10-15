package com.example.Exam.controller;

import com.example.Exam.model.Publisher;
import com.example.Exam.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(publisherService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(publisherService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher) {
        publisherService.addPublisher(publisher);
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublisher(@PathVariable(name = "id") Long id, @RequestBody Publisher publisher) {
        publisherService.updatePublisher(id, publisher);
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublisher(@PathVariable(name = "id") Long id) {
        publisherService.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

