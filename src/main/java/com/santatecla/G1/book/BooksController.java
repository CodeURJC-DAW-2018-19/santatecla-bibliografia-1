package com.santatecla.G1.book;

import java.util.Collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.santatecla.G1.TabController;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.user.UserComponent;


@Controller
public class BooksController {

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private TabController tabs;
  
  @Autowired
	private BookService bookService;

	
	public Collection<Book> books(){
		return bookService.findAll();
	}
	
	@RequestMapping("/book/{id}")
	public String Book(Model model, @PathVariable long id) {
		Book book = bookService.findOne(id);
		System.out.println(book.toString());
		List <Citation> citations = book.getCitations();
		Author author = book.getAuthor();
		Theme theme = book.getTheme();
		if (book!=null) {
			model.addAttribute("book", book);
			model.addAttribute("authors", author);
			model.addAttribute("themes", theme);
			model.addAttribute("citations",citations);
			model.addAttribute("entity","book");
		}
		System.out.println("Add tab");
		tabs.userTabs(model, "/book/"+ id, book.getTitle(), true, id);
		return "booksPage";
	}
	
	@RequestMapping("/newBook")
	public String newBook(Model model) {
		
		return "booksPageEdit";
	}
	
	@GetMapping("/table-works")
	public String showMoreWorks(Model model, Pageable page) {
		Page<Book> works = bookService.findAll(page);

		model.addAttribute("books", works);
		model.addAttribute("nWorks", page.getPageNumber());
		model.addAttribute("indexWorks", works.getTotalPages());

		return "pageableBook";
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
		bookService.save(book);
		model.addAttribute("text","Book Created");
		return "Message";
	}
	
	@RequestMapping("book/{id}/updateBook")
	public String updateBook(Model model,Book newBook , @PathVariable long id) {
		Book oldBook = bookService.findOne(id);
		oldBook.update(newBook);
		bookService.save(oldBook);		
		model.addAttribute("text","Libro actualizado correctamente");
		return "Message";
	}
	
	@RequestMapping("/book/{id}/deleteBook")
	public String deleteAuthor(Model model, @PathVariable long id) {
		Book book = bookService.findOne(id);
		if (book!=null) {
			model.addAttribute("book", book);
			model.addAttribute("text","Libro eliminado de forma correcta");
		}
		bookService.deleteById(id);
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
