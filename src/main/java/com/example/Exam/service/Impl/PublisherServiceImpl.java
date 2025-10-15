package com.example.Exam.service.Impl;

import com.example.Exam.model.Publisher;
import com.example.Exam.repository.PublisherRepository;
import com.example.Exam.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAll(){
        return publisherRepository.findAll();
    }
    @Override
    public void addPublisher(Publisher publisher){
        publisherRepository.save(publisher);
    }
    @Override
    public void updatePublisher(Long id, Publisher publisher){
        Publisher updated = publisherRepository.findById(publisher.getId()).orElse(null);
        updated.setName(publisher.getName());
        publisherRepository.save(updated);
    }
    @Override
    public void deletePublisher(Long id){
        publisherRepository.deleteById(id);
    }
    @Override
    public Publisher getById(Long id){
        return publisherRepository.getById(id);
    }
}
