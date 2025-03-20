package org.example.springdatam1.repositories;

import org.example.springdatam1.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
