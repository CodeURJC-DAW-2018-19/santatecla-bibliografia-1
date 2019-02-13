package com.santatecla.G1.book;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BooksController {
	
	@Autowired
	private BookRepository repository;
	
	@PostConstruct
	public void init() {
		repository.save(new Book("Pepe","mama"));
		repository.save(new Book("Juan","mama"));
	}
	
	@RequestMapping("/books")
	public String author(Model model) {
		
		return "booksPage";
	}
}
