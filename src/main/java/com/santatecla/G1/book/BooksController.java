package com.santatecla.G1.book;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BooksController {

	@Autowired
	private BookRepository repository;
	
	public Collection<Book> books(){
		return repository.findAll();
	}
	
	@RequestMapping("/book/{id}")
	public String Book(Model model, @PathVariable long id) {
		Optional<com.santatecla.G1.book.Book> book = repository.findById(id);
		if (book!=null) {
			model.addAttribute("book", book);
		}
		return "booksPageView";
	}
	
	@RequestMapping("/book")
	public String newBook(Model model, Book book) {
		repository.save(book);
		return "booksPage";
	}

}
