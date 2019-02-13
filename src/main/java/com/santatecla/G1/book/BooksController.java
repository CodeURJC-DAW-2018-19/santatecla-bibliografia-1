package com.santatecla.G1.book;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	public Collection<Book> books(){
		return repository.findAll();
	}
	
	@RequestMapping("/book/{id}")
	public String Author(Model model, @PathVariable long id) {
		Book book = repository.findOne(id);
		if (book!=null) {
			model.addAttribute("book", book);
		}
		return "bookPage";
	}
	
	@RequestMapping("/book")
	public String newAuthor(Model model, Book book) {
		repository.save(book);
		return "bookPage";
	}

}
