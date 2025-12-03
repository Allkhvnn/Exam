package com.example.Exam.service.Impl;

import com.example.Exam.dto.PublisherDto;
import com.example.Exam.mapper.PublisherMapper;
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
    private final PublisherMapper publisherMapper; // <-- новый маппер


    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public void addPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisher(Long id, Publisher publisher) {
        Publisher existing = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));

        existing.setName(publisher.getName());
        publisherRepository.save(existing);
    }

    @Override
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public Publisher getById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));
    }


    public List<PublisherDto> getAllDto() {
        return publisherMapper.toDtoList(publisherRepository.findAll());
    }

    public PublisherDto addPublisherDto(PublisherDto dto) {
        Publisher entity = publisherMapper.toEntity(dto);
        Publisher saved = publisherRepository.save(entity);
        return publisherMapper.toDto(saved);
    }

    public PublisherDto updatePublisherDto(Long id, PublisherDto dto) {
        Publisher existing = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));

        existing.setName(dto.getName());

        return publisherMapper.toDto(publisherRepository.save(existing));
    }

    public PublisherDto getByIdDto(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));

        return publisherMapper.toDto(publisher);
    }
}
