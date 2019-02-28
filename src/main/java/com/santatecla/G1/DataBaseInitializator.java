package com.santatecla.G1;


import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
			
		String nameBrandon="Brandon Sanderson";
		String urlImageBrandon="https://www.goodreads.com/photo/author/38550.Brandon_Sanderson";
		String bornDateBrandon="1975-12-19";
		String deathDateBrandon="";
		String bornPlaceBrandon="Lincoln, Nebraska";
		String urlMapBrandon= "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d96647.87182448553!2d-96.76076790212979!3d40.80058782506782!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8796be59ca561265%3A0x633a859b1fd5deb9!2sLincoln%2C+Nebraska%2C+EE.+UU.!5e0!3m2!1ses!2ses!4v1551055373899";                  
		Author a1 = new Author(nameBrandon,urlImageBrandon,bornDateBrandon,deathDateBrandon,bornPlaceBrandon,urlMapBrandon,0);
		
		String nameEsteso ="Fernando Esteso";
		String urlImageEsteso="https://img.bekia.es/celebrities/th/2000/2576.jpg";
		String bornDateEsteso="1945-2-16";
		String deathDateEsteso="";
		String bornPlaceEsteso="Zaragoza, España";
		String urlMapEsteso= "https://www.google.es/maps/place/Zaragoza/@41.6516859,-0.9300003,13z/data=!3m1!4b1!4m5!3m4!1s0xd5914dd5e618e91:0x49df13f1158489a8!8m2!3d41.6488226!4d-0.8890853";                  
		Author a2 = new Author(nameEsteso,urlImageEsteso,bornDateEsteso,deathDateEsteso,bornPlaceEsteso,urlMapEsteso,1);
		
		String nameAlan="Alan Mathison Turing";
		String urlImageAlan="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Alan_Turing_Aged_16.jpg/440px-Alan_Turing_Aged_16.jpg";
		String bornDateAlan="1912-06-23";
		String deathDateAlan="1954-06-07";
		String bornPlaceAlan="Maida Vale, Reino Unido de Gran Bretaña e Irlanda";
		String urlMapAlan= "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d75132.22175705049!2d-0.2604624272851899!3d51.53606625037453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48761009a098e00b%3A0x261185c6bcdb02a2!2sMaida+Vale%2C+Londres%2C+Reino+Unido!5e0!3m2!1ses!2ses!4v1549226048515";                            
		Author a3 = new Author(nameAlan,urlImageAlan,bornDateAlan,deathDateAlan,bornPlaceAlan,urlMapAlan,2);
		

		

		//Books initialization
		Book b1 = new Book("Palabras Radiantes",3);
		Book b2 = new Book("Nacidos de la bruma",4);
		Book b3 = new Book("El año de los delfines",5);
		Book b4 = new Book("Refranero",-1);
		
		//Citation initialization  --> We dont need to save because they deppends of the books. (Cascade.)	
		Citation c1 = new Citation("El misterio de la vida no es un problema a resolver, sino una realidad a experimentar",b1);
		Citation c2 = new Citation("La Ilusión despierta el empeño y solamente la paciencia lo termina.",b2);
		Citation c3 = new Citation("Nunca la persona llega a tal grado de perfección como cuando llena un impreso de solicitud de trabajo.",b3);	
		Citation c4 = new Citation("El que es bueno en la familia es también un buen ciudadano.",b4);
		Citation c5 = new Citation("Haríamos muchas más cosas si creyéramos que son muchas menos las imposibles.",b3);
		Citation c6 = new Citation("La sabiduría suprema es tener sueños bastante grandes para no perderlos de vista mientras se persiguen.",b4);
		Citation c7 = new Citation("Elige la mejor manera de vivir; la costumbre te la hará agradable.",b4);
		Citation c8 = new Citation("Los libros no se han hecho para servir de adorno: sin embargo, nada hay que embellezca tanto como ellos en el interior del hogar.",b4);
		Citation c9 = new Citation("La furia con que el mundo actual busca el placer prueba que carece de él.",b4);
		Citation c10 = new Citation("Donde hay amor no hay señor, que todo lo iguala el amor.",b4);
		Citation c11= new Citation("La buena fe es el fundamento de toda sociedad, la perfidia es la peste.",b4);
		
		//Adding citation to books, We don't save it explicitly because they depend of the existence of a book
		b1.addCitations(c1); 
		b2.addCitations(c2);
		b3.addCitations(c3);
		b4.addCitations(c4);
		b4.addCitations(c5);
		b4.addCitations(c6);
		b4.addCitations(c7);
		b4.addCitations(c8);
		b4.addCitations(c9);
		b4.addCitations(c10);
		b4.addCitations(c11);
		
		//Set books of citations
		c1.setBook(b1);
		
		c2.setBook(b2);
		
		c3.setBook(b3);
		
		c4.setBook(b4);
		c5.setBook(b4);
		c6.setBook(b4);
		c7.setBook(b4);
		c8.setBook(b4);
		c9.setBook(b4);
		c10.setBook(b4);
		c11.setBook(b4);
		

		//Create a themes that will be relacionated with a book, the theme also depends of the existence of a book(we don't save it explicitly).
		Theme th1  = new Theme("Amor");
		Theme th2  = new Theme("Vida");
		Theme th3  = new Theme("Clasico");
		
		
		//Add the theme to the book
		
		c1.setTheme(th1);
		c2.setTheme(th2);
		
		c4.setTheme(th3);
		c3.setTheme(th3);
		c6.setTheme(th3);
		c7.setTheme(th3);
		c8.setTheme(th3);
		c9.setTheme(th3);
		c10.setTheme(th3);
		c11.setTheme(th3);
		
		b1.setTheme(th1);
		b2.setTheme(th2);
		b3.setTheme(th3);
		b4.setTheme(th3);
	
		th1.addBook(b1);
		th2.addBook(b2);
		th3.addBook(b3);
		th3.addBook(b4);
		
		themeRepository.save(th1);
		themeRepository.save(th2);
		themeRepository.save(th3);
		
		//Add the book to the author, the existence of the book depends of the existence of the author, so we don't save the book explicitly.
		a1.addBook(b1);
		a2.addBook(b2);
		a3.addBook(b4);
		a3.addBook(b3);
		
		b1.setAuthor(a1);
		b2.setAuthor(a2);
		b3.setAuthor(a3);
		b4.setAuthor(a3);
			
		
		
		authorRepository.save(a1);
		authorRepository.save(a2);
		authorRepository.save(a3);
		//Save the book wich has no author.
		bookRepository.save(b2);
		bookRepository.save(b1);
		bookRepository.save(b4);
		bookRepository.save(b3);
		
		//Save the author who has write the b1
		/*citationRepository.save(c1);
		citationRepository.save(c2);
		citationRepository.save(c3);
		citationRepository.save(c4);
		citationRepository.save(c5);
		citationRepository.save(c6);
		citationRepository.save(c6);
		citationRepository.save(c7);
		citationRepository.save(c8);
		citationRepository.save(c9);
		citationRepository.save(c10);
		citationRepository.save(c11);*/

		
		
		
		
		//Initialization of the users of the application

		userRepository.save(new User("juan","4321","ROLE_STUDENT"));
		userRepository.save(new User("pedro","1234","ROLE_STUDENT"));
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
