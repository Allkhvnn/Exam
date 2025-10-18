package com.example.Exam.service.Impl;

import com.example.Exam.service.AuthorService;
import com.example.Exam.model.Author;
import com.example.Exam.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Long id, Author author) {
        Author updated = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        updated.setName(author.getName());
        authorRepository.save(updated);
    }
    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}