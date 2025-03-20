package org.example.springdatam1.entity;

import jakarta.persistence.*;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "publisher", cascade = CascadeType.ALL)
    private Book book;

    public Publisher() {}

    public Publisher(Long id, String name, Book book) {
        this.id = id;
        this.name = name;
        this.book = book;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
