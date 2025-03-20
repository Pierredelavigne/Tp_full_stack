package org.example.springdatam1.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;

    public Genre() {}

    public Genre(Long id, String name, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
