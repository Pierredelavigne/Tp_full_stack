package org.example.springdatam1.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(
        name = "book_genre",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book() {}

    public Book(Long id, String title, Author author, Set<Genre> genres, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genres = genres;
        this.publisher = publisher;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
