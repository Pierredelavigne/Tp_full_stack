package org.example.springdatam1.repositories;

import org.example.springdatam1.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
