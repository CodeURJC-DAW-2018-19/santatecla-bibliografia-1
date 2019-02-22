package com.santatecla.G1;

import java.text.SimpleDateFormat;
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
		//Date deathDate = new Date(428400000);
		//Date bornDate = new Date(315961200);
		Author a1 = new Author("Brandon Sanderson","1927-10-11","1927-10-11",0);
		
		//Date deathDate2 = new Date(428400000);
		//Date bornDate2 = new Date(315961200);
		Author a2 = new Author("Fernando Esteso","1927-10-11","1927-10-11",1);
		String nameAlan="Alan Mathison Turing";
		String urlImageAlan="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Alan_Turing_Aged_16.jpg/440px-Alan_Turing_Aged_16.jpg";
		String bornDateAlan="1912-06-23";
		String deathDateAlan="1954-06-07";
		String bornPlaceAlan="Maida Vale, Reino Unido de Gran BretaÃ±a e Irlanda";
		String urlMapAlan= "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d75132.22175705049!2d-0.2604624272851899!3d51.53606625037453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48761009a098e00b%3A0x261185c6bcdb02a2!2sMaida+Vale%2C+Londres%2C+Reino+Unido!5e0!3m2!1ses!2ses!4v1549226048515";                            
		authorRepository.save(new Author(nameAlan,urlImageAlan,bornDateAlan,deathDateAlan,bornPlaceAlan,urlMapAlan,2));

	    //bornDate = new Date(428400000);
		//deathDate = new Date(315961200);
		authorRepository.save(new Author("Juan","1927-10-11","1927-10-11",-1));
			
		//Citation initialization  --> We dont need to save because they deppends of the books. (Cascade.)
		Citation c1 = new Citation("El misterio de la vida no es un problema a resolver, sino una realidad a experimentar");
		Citation c2 = new Citation("La Ilusión despierta el empeño y solamente la paciencia lo termina.");
		Citation c3 = new Citation("Nunca la persona llega a tal grado de perfección como cuando llena un impreso de solicitud de trabajo.");		
		
		//Books initialization
		Book b1 = new Book("Palabras Radiantes","Brandon Sanderson",3);
		Book b2 = new Book("Nacidos de la bruma","Fernando Esteso",4);
		Book b3 = new Book("El año de los delfines","Sarah Lark",5);
				
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
		bookRepository.save(b1);
		bookRepository.save(b3);
		
		
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
