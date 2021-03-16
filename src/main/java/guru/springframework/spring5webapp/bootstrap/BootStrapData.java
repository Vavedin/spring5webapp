package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Eisenhardt" );
        Book xmen = new Book("X-Men", "616");
        eric.getBooks().add(xmen);
        xmen.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(xmen);

        Publisher ambiorix = new Publisher("Ambiorix", "Prinsenweg 13", "Tongeren", "Limburg", 3700);

        publisherRepository.save(ambiorix);

        xmen.setPublisher(ambiorix);
        ambiorix.getBooks().add(xmen);

        authorRepository.save(eric);
        bookRepository.save(xmen);
        publisherRepository.save(ambiorix);

        Author charles = new Author("Charles", "Xavier");
        Book mutans = new Book("mutans", "677");
        charles.getBooks().add(mutans);
        mutans.getAuthors().add(charles);

        mutans.setPublisher(ambiorix);
        ambiorix.getBooks().add(mutans);

        authorRepository.save(charles);
        bookRepository.save(mutans);
        publisherRepository.save(ambiorix);


        System.out.println(ambiorix.toString());



        System.out.println("Started in bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Nr of books from Ambriorix: " + ambiorix.getBooks().size());



    }
}
