package com.example.Exam.repository;

import com.example.Exam.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
