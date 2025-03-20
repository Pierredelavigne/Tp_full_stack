package org.example.springdatam1.services;

import org.example.springdatam1.entity.Publisher;
import org.example.springdatam1.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService implements CrudService<Publisher, Long> {
    private final PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Publisher publisher) {
        repository.save(publisher);
    }

    @Override
    public List<Publisher> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Publisher publisher) {
        if (repository.existsById(publisher.getId())) {
            repository.save(publisher);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
