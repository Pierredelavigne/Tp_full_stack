package org.example.springdatam1.ihm;

import org.example.springdatam1.entity.Author;
import org.example.springdatam1.entity.Book;
import org.example.springdatam1.entity.Genre;
import org.example.springdatam1.entity.Publisher;
import org.example.springdatam1.services.AuthorService;
import org.example.springdatam1.services.BookService;
import org.example.springdatam1.services.GenreService;
import org.example.springdatam1.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class IHM2 implements CommandLineRunner {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private PublisherService publisherService;

    @Override
    public void run(String... args) {
        menu();
    }

    private void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> menuAuthor(scanner);
                case 2 -> menuGenre(scanner);
                case 3 -> menuPublisher(scanner);
                case 4 -> menuBook(scanner);
                case 0 -> {
                System.out.println("Bye bye!");
                System.exit(0);
            }
                default -> System.out.println("Choix invalide !");
            }
        } while (choice != 0);
    }

    private void displayMenu() {
        System.out.println("\nMenu principal:");
        System.out.println("1. Gestion des Auteurs");
        System.out.println("2. Gestion des Genres");
        System.out.println("3. Gestion des Publishers");
        System.out.println("4. Gestion des Livres");
        System.out.println("0. Quitter");
        System.out.print("Choisissez une option : ");
    }

    // ----------------- Gestion des Auteurs -----------------
    private void menuAuthor(Scanner scanner) {
        System.out.println("\nGestion des Auteurs:");
        System.out.println("1. Ajouter un auteur");
        System.out.println("2. Lister les auteurs");
        System.out.println("3. Supprimer un auteur");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("Nom de l'auteur : ");
                String name = scanner.nextLine();
                authorService.save(new Author(null, name, null));
                System.out.println("Auteur ajouté !");
            }
            case 2 -> {
                List<Author> authors = authorService.findAll();
                authors.forEach(a -> System.out.println(a.getId() + " - " + a.getName()));
            }
            case 3 -> {
                System.out.print("ID de l'auteur à supprimer : ");
                Long id = scanner.nextLong();
                authorService.delete(id);
                System.out.println("Auteur supprimé !");
            }
            default -> System.out.println("Choix invalide !");
        }
    }

    // ----------------- Gestion des Genres -----------------
    private void menuGenre(Scanner scanner) {
        System.out.println("\nGestion des Genres:");
        System.out.println("1. Ajouter un genre");
        System.out.println("2. Lister les genres");
        System.out.println("3. Supprimer un genre");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("Nom du genre : ");
                String name = scanner.nextLine();
                genreService.save(new Genre(null, name, new HashSet<>()));
                System.out.println("Genre ajouté !");
            }
            case 2 -> {
                List<Genre> genres = genreService.findAll();
                genres.forEach(g -> System.out.println(g.getId() + " - " + g.getName()));
            }
            case 3 -> {
                System.out.print("ID du genre à supprimer : ");
                Long id = scanner.nextLong();
                genreService.delete(id);
                System.out.println("Genre supprimé !");
            }
            default -> System.out.println("Choix invalide !");
        }
    }

    // ----------------- Gestion des Publishers -----------------
    private void menuPublisher(Scanner scanner) {
        System.out.println("\nGestion des Publishers:");
        System.out.println("1. Ajouter un publisher");
        System.out.println("2. Lister les publishers");
        System.out.println("3. Supprimer un publisher");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("Nom du publisher : ");
                String name = scanner.nextLine();
                publisherService.save(new Publisher(null, name, null));
                System.out.println("Publisher ajouté !");
            }
            case 2 -> {
                List<Publisher> publishers = publisherService.findAll();
                publishers.forEach(p -> System.out.println(p.getId() + " - " + p.getName()));
            }
            case 3 -> {
                System.out.print("ID du publisher à supprimer : ");
                Long id = scanner.nextLong();
                publisherService.delete(id);
                System.out.println("Publisher supprimé !");
            }
            default -> System.out.println("Choix invalide !");
        }
    }

    // ----------------- Gestion des Livres -----------------
    private void menuBook(Scanner scanner) {
        System.out.println("\nGestion des Livres:");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Lister les livres");
        System.out.println("3. Supprimer un livre");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("Titre du livre : ");
                String title = scanner.nextLine();

                // Choisir un auteur
                System.out.print("ID de l'auteur : ");
                Long authorId = scanner.nextLong();
                scanner.nextLine();
                Author author = authorService.findById(authorId).orElse(null);
                if (author == null) {
                    System.out.println("Auteur introuvable !");
                    return;
                }

                // Choisir un genre
                System.out.print("ID du genre : ");
                Long genreId = scanner.nextLong();
                scanner.nextLine();
                Genre genre = genreService.findById(genreId).orElse(null);
                if (genre == null) {
                    System.out.println("Genre introuvable !");
                    return;
                }
                Set<Genre> genres = new HashSet<>();
                genres.add(genre);

                // Choisir un publisher
                System.out.print("ID du publisher : ");
                Long publisherId = scanner.nextLong();
                scanner.nextLine();
                Publisher publisher = publisherService.findById(publisherId).orElse(null);
                if (publisher == null) {
                    System.out.println("Publisher introuvable !");
                    return;
                }

                // Enregistrer le livre
                Book book = new Book(null, title, author, genres, publisher);
                bookService.save(book);
                System.out.println("Livre ajouté !");
            }
            case 2 -> {
                List<Book> books = bookService.findAll();
                books.forEach(b -> System.out.println(b.getId() + " - " + b.getTitle()));
            }
            case 3 -> {
                System.out.print("ID du livre à supprimer : ");
                Long id = scanner.nextLong();
                bookService.delete(id);
                System.out.println("Livre supprimé !");
            }
            default -> System.out.println("Choix invalide !");
        }
    }
}
