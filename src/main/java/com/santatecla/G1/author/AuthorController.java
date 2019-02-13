package com.santatecla.G1.author;

import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AuthorController {
	
	@Autowired
	private AuthorRepository repository;

	@PostConstruct
	public void init() {
		repository.save(new Author("Pepe",new SimpleDateFormat("1964-05-12"),new SimpleDateFormat("1964-05-12")));
		repository.save(new Author("Juan",new SimpleDateFormat("1964-05-12"),new SimpleDateFormat("1964-05-12")));
	}
	
	public Collection<Author> authors(){
		return repository.findAll();
	}
	
	@RequestMapping("/author/{id}")
	public String Author(Model model, @PathVariable long id) {
		Author author = repository.findOne(id);
		if (author!=null) {
			model.addAttribute("author", author);
		}
		return "authorPageView";
	}
	
	/*@RequestMapping("/author/{id}")
	public String updateAuthor(Model model, @PathVariable long id) {
		Author author = repository.findOne(id);
		if (author!=null) {
			model.addAttribute("author", author);
		}
		return "authorPage";
	}*/
	
	@RequestMapping("/author")
	public String newAuthor(Model model, Author author) {
		repository.save(author);
		return "authorPage";
	}
	
	/*@RequestMapping("/author/{id}")
	public String deleteAuthor(Model model, @PathVariable long id) {
		Author author = repository.findOne(id);
		if (author!=null) {
			model.addAttribute("author", author);
		}
		repository.delete(id);
		
		return "deletePage";
	}*/
}
