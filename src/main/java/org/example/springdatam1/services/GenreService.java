package org.example.springdatam1.services;

import org.example.springdatam1.entity.Genre;
import org.example.springdatam1.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService implements CrudService<Genre, Long> {
    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Genre genre) {
        repository.save(genre);
    }

    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Genre genre) {
        if (repository.existsById(genre.getId())) {
            repository.save(genre);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
