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
		citationRepository.save(new Citation("El misterio de la vida no es un problema a resolver, sino una realidad a experimentar"));
		citationRepository.save(new Citation("La Ilusión despierta el empeño y solamente la paciencia lo termina."));
		citationRepository.save(new Citation("Nunca la persona llega a tal grado de perfección como cuando llena un impreso de solicitud de trabajo."));
		
		//Theme initialization 
		themeRepository.save(new Theme("Amor"));
		themeRepository.save(new Theme("Vida"));
		themeRepository.save(new Theme("Tragedia"));
		themeRepository.save(new Theme("Economía"));
		themeRepository.save(new Theme("Ciencia"));
		themeRepository.save(new Theme("Informática"));
		themeRepository.save(new Theme("Poesía"));
		themeRepository.save(new Theme("Guerra"));
		
		
		//Books initialization
		bookRepository.save(new Book("Palabras Radiantes","Brandon Sanderson"));
		bookRepository.save(new Book("Nacidos de la bruma","Brandon Sanderson"));
		
		
		//Here code to test the DDBB
		List<Author> authors = authorRepository.findAll(); 
		System.out.println("Authors found with findAll()");
		System.out.println("----------------------------");
		for(Author a: authors) {
			System.out.println(a);
		}
		List<Book> books = bookRepository.findAll(); 
		System.out.println("Authors found with findAll()");
		System.out.println("----------------------------");
		for(Book a: books) {
			System.out.println(a);
		}
		List<Theme> themes = themeRepository.findAll(); 
		System.out.println("Authors found with findAll()");
		System.out.println("----------------------------");
		for(Theme a: themes) {
			System.out.println(a);
		}
		List<Citation> citations = citationRepository.findAll(); 
		System.out.println("Authors found with findAll()");
		System.out.println("----------------------------");
		for(Citation a: citations) {
			System.out.println(a);
		}
		
		
	}
}
