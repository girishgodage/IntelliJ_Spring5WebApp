package giri.springframework.spring5webapp.bootstrap;

import giri.springframework.spring5webapp.model.Author;
import giri.springframework.spring5webapp.model.Book;
import giri.springframework.spring5webapp.model.Publisher;
import giri.springframework.spring5webapp.repositories.AuthorRepository;
import giri.springframework.spring5webapp.repositories.BookRepository;
import giri.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;


    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData(){

        Publisher publisher = new Publisher();
        publisher.setName("foo");
        publisher.setAddress("12th Street, LA");
        publisherRepository.save(publisher);

        //Girish
        Author girish = new Author("Girish","Godage");
        Book ddd = new Book("domain Driven Design", "1234", publisher);
        girish.getBooks().add(ddd);
        ddd.getAuthors().add(girish);

        authorRepository.save(girish);
        bookRepository.save(ddd);

        //om
        Author om = new Author("Om","Godage");
        Book jpa = new Book("Java Persient API ", "2345", publisher);
        om.getBooks().add(jpa);
        jpa.getAuthors().add(om);

        authorRepository.save(om);
        bookRepository.save(jpa);

    }


}
