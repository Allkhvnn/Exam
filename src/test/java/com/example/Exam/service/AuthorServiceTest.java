package com.example.Exam.service;

import com.example.Exam.model.Author;
import com.example.Exam.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAllTest() {
        Author author = new Author();
        author.setName("findAllTest author");
        authorRepository.save(author);

        List<Author> authors = authorService.findAll();

        Assertions.assertNotNull(authors);
        Assertions.assertNotEquals(0, authors.size());

        for (Author a : authors) {
            Assertions.assertNotNull(a);
            Assertions.assertNotNull(a.getId());
            Assertions.assertNotNull(a.getName());
        }
    }

    @Test
    void addAuthorTest() {
        Author author = new Author();
        author.setName("addAuthorTest name");

        authorService.addAuthor(author);

        List<Author> authors = authorRepository.findAll();
        boolean found = false;
        for (Author a : authors) {
            if ("addAuthorTest name".equals(a.getName())) {
                found = true;
                Assertions.assertNotNull(a.getId());
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    void updateAuthorTest() {
        Author author = new Author();
        author.setName("before update");
        Author saved = authorRepository.save(author);

        Author updateData = new Author();
        updateData.setName("after update");

        authorService.updateAuthor(saved.getId(), updateData);

        Author updated = authorRepository.findById(saved.getId()).orElse(null);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("after update", updated.getName());
    }

    @Test
    void deleteAuthorTest() {
        Author author = new Author();
        author.setName("to delete");
        Author saved = authorRepository.save(author);

        authorService.deleteAuthor(saved.getId());

        boolean exists = authorRepository.findById(saved.getId()).isPresent();
        Assertions.assertFalse(exists);
    }

    @Test
    void getRandomAuthorFromListTest() {
        if (authorService.findAll().isEmpty()) {
            Author author = new Author();
            author.setName("random base author");
            authorService.addAuthor(author);
        }

        List<Author> authors = authorService.findAll();

        Assertions.assertNotNull(authors);
        Assertions.assertNotEquals(0, authors.size());

        Random random = new Random();
        int index = random.nextInt(authors.size());

        Author randomAuthor = authors.get(index);

        Assertions.assertNotNull(randomAuthor);
        Assertions.assertNotNull(randomAuthor.getId());
        Assertions.assertNotNull(randomAuthor.getName());
    }
}
