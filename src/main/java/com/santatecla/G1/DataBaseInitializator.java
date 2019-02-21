package com.santatecla.G1;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.santatecla.G1.author.Author;
import com.santatecla.G1.author.AuthorRepository;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.citation.CitationRepository;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeRepository;
import com.santatecla.G1.user.User;
import com.santatecla.G1.user.UserRepository;

@Component
@Order(1)
public class DataBaseInitializator {
	
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	ThemeRepository themeRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired 
	CitationRepository citationRepository;
	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		System.out.println("Start Database initialization");
		// Author initialization		
		Date deathDate = new Date(428400000);
		Date bornDate = new Date(315961200);
		Author a1 = new Author("Brandon Sanderson",bornDate,deathDate);
		
		Date deathDate2 = new Date(428400000);
		Date bornDate2 = new Date(315961200);
		Author a2 = new Author("Fernando Esteso",bornDate2,deathDate2);
		

		
	    bornDate = new Date(428400000);
		deathDate = new Date(315961200);
		authorRepository.save(new Author("Juan",bornDate,deathDate));
			
		//Citation initialization  --> We dont need to save because they deppends of the books. (Cascade.)
		Citation c1 = new Citation("El misterio de la vida no es un problema a resolver, sino una realidad a experimentar");
		Citation c2 = new Citation("La Ilusión despierta el empeño y solamente la paciencia lo termina.");
		Citation c3 = new Citation("Nunca la persona llega a tal grado de perfección como cuando llena un impreso de solicitud de trabajo.");		
		
		//Books initialization
		Book b1 = new Book("Palabras Radiantes","Brandon Sanderson");
		Book b2 = new Book("Nacidos de la bruma","Fernando Esteso");
		Book b3 = new Book("El año de los delfines","Sarah Lark");
				
		//Adding citation to books, We don't save it explicitly because they depend of the existence of a book
		b1.addCitations(c1); 
		b2.addCitations(c2);
		b2.addCitations(c3);
		
		//Create a themes that will be relacionated with a book, the theme also depends of the existence of a book(we don't save it explicitly).
		Theme th1  = new Theme("Amor");
		Theme th2  = new Theme("Vida");
		
		//Add the theme to the book
		b1.setTheme(th1);
		b2.setTheme(th2);
		b3.setTheme(th1);
		
		th1.addBook(b1);
		th1.addBook(b3);
		th2.addBook(b2);
		
		themeRepository.save(th1);
		themeRepository.save(th2);
		
		//Add the book to the author, the existence of the book depends of the existence of the author, so we don't save the book explicitly.
		a1.addBook(b1);
		a2.addBook(b2);
		
		
		authorRepository.save(a1);
		authorRepository.save(a2);
		//Save the author who has write te b1
		authorRepository.save(a1);
		//Save the book wich has no author.
		bookRepository.save(b2);
		
		//Theme initialization with no books relacionated
		themeRepository.save(new Theme("Tragedia"));
		themeRepository.save(new Theme("Economía"));
		themeRepository.save(new Theme("Ciencia"));
		themeRepository.save(new Theme("Informática"));
		themeRepository.save(new Theme("Poesía"));
		themeRepository.save(new Theme("Guerra"));	
		
		
		//Initialization of the users of the application

		userRepository.save(new User("pedro","1234","ROLE_USER"));
		userRepository.save(new User("juan","4321","ROLE_STUDENT"));
		userRepository.save(new User("admin","admin","ROLE_ADMIN","ROLE_USER","ROLE_STUDENT"));
		
		//Here code to test the DDBB
		List<Author> authors = authorRepository.findAll(); 
		System.out.println("Authors found with findAll()");
		System.out.println("----------------------------");
		for(Author a: authors) {
			System.out.println(a);
		}
		System.out.println("----------------------------");
		List<Book> books = bookRepository.findAll(); 
		System.out.println("Books found with findAll()");
		System.out.println("----------------------------");
		for(Book a: books) {
			System.out.println(a);
		}
		System.out.println("----------------------------");
		List<Theme> themes = themeRepository.findAll(); 
		System.out.println("Themes found with findAll()");
		System.out.println("----------------------------");
		for(Theme a: themes) {
			System.out.println(a);
		}
		System.out.println("----------------------------");
		List<Citation> citations = citationRepository.findAll(); 
		System.out.println("Citations found with findAll()");
		System.out.println("----------------------------");
		for(Citation a: citations) {
			System.out.println(a);
		}
		System.out.println("----------------------------");
	}
}
