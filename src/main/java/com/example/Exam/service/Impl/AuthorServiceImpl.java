package com.example.Exam.service.Impl;

import com.example.Exam.dto.AuthorDto;
import com.example.Exam.mapper.AuthorMapper;
import com.example.Exam.model.Author;
import com.example.Exam.repository.AuthorRepository;
import com.example.Exam.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper; // <-- добавили маппер


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
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        updated.setName(author.getName());
        authorRepository.save(updated);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public List<AuthorDto> findAllDto() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.toDtoList(authors);
    }

    public AuthorDto addAuthorDto(AuthorDto dto) {
        Author entity = authorMapper.toEntity(dto);
        Author saved = authorRepository.save(entity);
        return authorMapper.toDto(saved);
    }

    public AuthorDto updateAuthorDto(Long id, AuthorDto dto) {
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        existing.setName(dto.getName());

        Author saved = authorRepository.save(existing);
        return authorMapper.toDto(saved);
    }
}