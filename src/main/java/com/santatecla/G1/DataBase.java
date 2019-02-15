package com.santatecla.G1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

import com.santatecla.G1.author.Author;
import com.santatecla.G1.author.AuthorRepository;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.citation.CitationRepository;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeRepository;

@Controller
@Order(2)
public class DataBase implements CommandLineRunner {
	
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ThemeRepository themeRepository;
	@Autowired
	private CitationRepository citationRepository;

	@Override
	public void run(String... args) throws Exception {

		
		//Code or logic for DDBB
	}

}
