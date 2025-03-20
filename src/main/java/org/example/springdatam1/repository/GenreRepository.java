package org.example.springdatam1.repositories;

import org.example.springdatam1.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
