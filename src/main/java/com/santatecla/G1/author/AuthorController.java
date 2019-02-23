package com.santatecla.G1.author;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.user.UserComponent;


@Controller
public class AuthorController {
	
	@Autowired
	private AuthorRepository repository;
	@Autowired
	private BookRepository booksRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	public List<Author> authors(){
		return repository.findAll();
	}
	
	@RequestMapping("/author/{id}")
	public String Author(Model model, @PathVariable long id, HttpServletRequest request) {
		Author author = repository.findById(id);
		List<Book> books = booksRepository.findByNameEdit(author.getName());
		if (author!=null) {
			model.addAttribute("author", author);
			model.addAttribute("books", books);
		}

		
		return "authorPageEdit";
	}
	
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
			//model.addAttribute("userName",userComponent.getLoggedUser().getName());
		}
	}
	
	
	/*@RequestMapping("/author/{id}")
	public String updateAuthor(Model model, @PathVariable long id) {
		Optional<com.santatecla.G1.author.Author> author = repository.findById(id);
		if (author!=null) {
			model.addAttribute("author", author);
		}
		return "authorPage";
	}*/
	
	@RequestMapping("/author")
	public String author(Model model) {
		//repository.save(author);
		return "authorPage";
	}
	
	@RequestMapping("/newAuthor")
	public String newAuthor(Model model) {
		//repository.save(author);
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
