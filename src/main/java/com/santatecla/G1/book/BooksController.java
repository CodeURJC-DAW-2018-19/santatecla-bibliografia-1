package com.santatecla.G1.book;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.santatecla.G1.author.Author;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.user.UserComponent;

@Controller
public class BooksController {

	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private BookService repository;
	@Autowired
	private BookRepository bookRepository;
	
	public Collection<Book> books(){
		return repository.findAll();
	}
	
	@RequestMapping("/book/{id}")
	public String Book(Model model, @PathVariable long id) {
		Book book = bookRepository.findById(id);
		System.out.println(book.toString());
		List <Citation> citations = book.getCitations();
		Author author = book.getAuthor();
		Theme theme = book.getTheme();
		if (book!=null) {
			model.addAttribute("book", book);
			model.addAttribute("authors", author);
			model.addAttribute("themes", theme);
			model.addAttribute("citations",citations);
		}
		return "booksPage";
	}
	
	@RequestMapping("/newBook")
	public String newBook(Model model) {
		
		return "booksPageEdit";
	}
	
	@RequestMapping("/saveBook")
	public String saveBook(Model model, Book book, @RequestParam("file")MultipartFile file) {
		if((file!=null)&&(!file.isEmpty())) {
			int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
			book.setImgId(imgId);
			com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);
			book.setImgId(imgId);
		}
		else
			book.setImgId(-2);
		repository.save(book);
		model.addAttribute("text","Book Created");
		return "Message";
	}
	
	@RequestMapping("book/{id}/updateBook")
	public String updateBook(Model model, Book book, @PathVariable long id) {
		
		book.setId(id);
		repository.save(book);		
		model.addAttribute("text","Libro actualizado correctamente");
		return "Message";
	}
	@RequestMapping("/book/{id}/deleteBook")
	public String deleteAuthor(Model model, @PathVariable long id) {
		Book book = bookRepository.findById(id);
		if (book!=null) {
			model.addAttribute("book", book);
			model.addAttribute("text","Libro eliminado de forma correcta");
		}
		repository.deleteById(id);
		return "Message";
	}
	
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
			model.addAttribute("userName",userComponent.getLoggedUser().getName());
		}
	}
	

}
