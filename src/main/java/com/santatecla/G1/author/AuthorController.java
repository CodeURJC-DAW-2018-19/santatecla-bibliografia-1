package com.santatecla.G1.author;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.user.UserComponent;



@Controller
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private TabController tabs;
  
  @Autowired
	private BookService bookService;


	
	@Autowired
	private UserComponent userComponent;
	
	public List<Author> authors(){
		return authorService.findAll();
	}
	
	@RequestMapping("/author/{id}")
	public String Author(Model model, @PathVariable long id, HttpServletRequest request) {
		Author author = authorService.findById(id);
		List<Book> books = bookService.findByAuthor(author);
		List<Theme> themes = new ArrayList<>();
		List<Citation> citations = new ArrayList<>();
		for(Book b: books) {
			//Get the themes of the books of the author
			themes.add(b.getTheme());
			//We need an aux array because a book can return a list of citation
			citations.addAll(b.getCitations());
			for(Citation c : citations) {
				System.out.println(c.getText());
			}
			
		}	
		if (author!=null) { 
			model.addAttribute("author", author);
			model.addAttribute("books", books);
			model.addAttribute("themes",themes);
			model.addAttribute("citations",citations);
			model.addAttribute("entity","author");
		}
		
		System.out.println("Add tab");
		tabs.userTabs(model, "/author/"+ id, author.getName(), true, id);
		System.out.println("aqui");
		return "authorPage";
	}
	
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
		}
	}
	
	@GetMapping("/table-author")
	public String showMoreAuthor(Model model, Pageable page) {
		Page<Author> author = authorService.findAll(page);

		model.addAttribute("authors", author);
		model.addAttribute("nAuthor", page.getPageNumber());
		model.addAttribute("indexAuthors", author.getTotalPages());
		System.out.println("Show more author");
		System.out.println(author.getTotalPages());

		return "pageableAuthor";
	}
	
	
	/*@RequestMapping("/author/{id}")
	public String updateAuthor(Model model, @PathVariable long id) {
		Optional<com.santatecla.G1.author.Author> author = 
    
    sitory.findById(id);
		if (author!=null) {
			model.addAttribute("author", author);
		}
		return "authorPage";
	}*/
	
	@RequestMapping(value="/saveAuthor")
	public String author(Model model, Author author,@RequestParam("file")MultipartFile file, @RequestParam Long[] b) {
		System.out.println(b.toString());
		if((file!=null)&&(!file.isEmpty())) {
			int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
			com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);	
			author.setImgId(imgId);
		}			
		else
			author.setImgId(-1);
		authorService.save(author);
		model.addAttribute("text","Autor creado correctamente");
		System.out.println(author.toString());
		return "Message";
	}
	
	@RequestMapping("/newAuthor")
	public String newAuthor(Model model) {

		return "authorPageEdit"; 
	}
	
	@RequestMapping("/author/{id}/updateAuthor")
	public String updateAuthor(Model model, Author newAuthor, @PathVariable long id) {
		Author oldAuthor = authorService.findById(id);
		oldAuthor.update(newAuthor);
		authorService.save(oldAuthor);
		model.addAttribute("text","Autor editado de forma correcta");
		return "Message";
	}	
	
	@RequestMapping("/author/{id}/deleteAuthor")
	public String deleteAuthor(Model model, @PathVariable long id) {
		Author author = authorService.findById(id);
		if (author!=null) {
			model.addAttribute("author", author);
			model.addAttribute("text","Autor eliminado de forma correcta");
		}
		authorService.deleteById(id);
		
		return "Message";
	}
}
