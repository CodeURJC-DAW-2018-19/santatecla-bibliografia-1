package com.santatecla.G1;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.santatecla.G1.author.Author;
import com.santatecla.G1.author.AuthorRepository;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.citation.CitationRepository;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeRepository;

@Component
@Order(1)
public class DataBaseInitializator implements CommandLineRunner {
	
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	ThemeRepository themeRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired 
	CitationRepository citationRepository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Start Database initialization");
		// Author initialization		
		Date deathDate = new Date(428400000);
		Date bornDate = new Date(315961200);
		authorRepository.save(new Author("Pepe",bornDate,deathDate));
		
	    bornDate = new Date(428400000);
		deathDate = new Date(315961200);
		authorRepository.save(new Author("Juan",bornDate,deathDate));
		
		
		
		//Citation initialization 
		Citation c1 = new Citation("El misterio de la vida no es un problema a resolver, sino una realidad a experimentar");
		Citation c2 = new Citation("La Ilusión despierta el empeño y solamente la paciencia lo termina.");
		Citation c3 = new Citation("Nunca la persona llega a tal grado de perfección como cuando llena un impreso de solicitud de trabajo.");
		
		citationRepository.save(c1);
		citationRepository.save(c2);
		citationRepository.save(c3);		
		
		//Books initialization
		Book b1 = new Book("Palabras Radiantes","Brandon Sanderson");
		Book b2 = new Book("Nacidos de la bruma","Brandon Sanderson");
		
		/*b1.citations.add(c1);
		b1.citations.add(c2);
		b2.citations.add(c3);*/
		
		bookRepository.save(b1);
		bookRepository.save(b2);
		
		
		
		
		
		
		//Theme initialization 
		themeRepository.save(new Theme("Amor"));
		themeRepository.save(new Theme("Vida"));
		themeRepository.save(new Theme("Tragedia"));
		themeRepository.save(new Theme("Economía"));
		themeRepository.save(new Theme("Ciencia"));
		themeRepository.save(new Theme("Informática"));
		themeRepository.save(new Theme("Poesía"));
		themeRepository.save(new Theme("Guerra"));	
		
		//Here code to test the DDBB
		List<Author> authors = authorRepository.findAll(); 
		System.out.println("Authors found with findAll()");
		System.out.println("----------------------------");
		for(Author a: authors) {
			System.out.println(a);
		}
		List<Book> books = bookRepository.findAll(); 
		System.out.println("Books found with findAll()");
		System.out.println("----------------------------");
		for(Book a: books) {
			System.out.println(a);
		}
		List<Theme> themes = themeRepository.findAll(); 
		System.out.println("Themes found with findAll()");
		System.out.println("----------------------------");
		for(Theme a: themes) {
			System.out.println(a);
		}
		List<Citation> citations = citationRepository.findAll(); 
		System.out.println("Citations found with findAll()");
		System.out.println("----------------------------");
		for(Citation a: citations) {
			System.out.println(a);
		}
		
		
	}
}
