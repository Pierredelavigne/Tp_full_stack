package org.example.springdatam1.services;

import org.example.springdatam1.entity.Author;
import org.example.springdatam1.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements CrudService<Author, Long> {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Author author) {
        repository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Author author) {
        if (repository.existsById(author.getId())) {
            repository.save(author);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
