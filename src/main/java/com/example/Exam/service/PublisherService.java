package com.example.Exam.service;

import com.example.Exam.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> getAll();
    Publisher getById(Long id);
    void addPublisher(Publisher publisher);
    void updatePublisher(Long id, Publisher publisher);
    void deletePublisher(Long id);
}
