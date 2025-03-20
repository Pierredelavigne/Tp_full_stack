package org.example.springdatam1.services;

import org.example.springdatam1.entity.Book;
import org.example.springdatam1.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements CrudService<Book, Long> {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Book book) {
        if (repository.existsById(book.getId())) {
            repository.save(book);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
