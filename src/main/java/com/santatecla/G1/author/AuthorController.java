package com.santatecla.G1.author;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.santatecla.G1.TabController;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.user.UserComponent;


@Controller
public class AuthorController {
	
	@Autowired
	private AuthorRepository repository;
	@Autowired
	private BookRepository booksRepository;
	@Autowired
	private TabController tabs;

	
	@Autowired
	private UserComponent userComponent;
	
	public List<Author> authors(){
		return repository.findAll();
	}
	
	@RequestMapping("/author/{id}")
	public String Author(Model model, @PathVariable long id, HttpServletRequest request) {
		Author author = repository.findById(id);
		List<Book> books = booksRepository.findByAuthor(author);
		List<Theme> themes = new ArrayList<>();
		List<Citation> citations = new ArrayList<>();
		for(Book b: books) {
			//Get the themes of the books of the author
			themes.add(b.getTheme());
			//We need an aux array because a book can return a list of citation
			citations.addAll(b.getCitations());
			System.out.println("LAS CITAS ESTÁN VACIAS? " + citations.isEmpty());
			for(Citation c : citations) {
				System.out.println(c.getText());
			}
			
		}	
		if (author!=null) { 
			model.addAttribute("author", author);
			model.addAttribute("books", books);
			model.addAttribute("themes",themes);
			model.addAttribute("citations",citations);
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
	
	
	/*@RequestMapping("/author/{id}")
	public String updateAuthor(Model model, @PathVariable long id) {
		Optional<com.santatecla.G1.author.Author> author = repository.findById(id);
		if (author!=null) {
			model.addAttribute("author", author);
		}
		return "authorPage";
	}*/
	
	@RequestMapping(value="/saveAuthor")
	public String author(Model model, Author author,@RequestParam("file")MultipartFile file) {
		if((file!=null)&&(!file.isEmpty())) {
			int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
			com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);	
			author.setImgId(imgId);
		}			
		else
			author.setImgId(-1);
		repository.save(author);
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
		Author oldAuthor = repository.findById(id);
		oldAuthor.update(newAuthor);
		repository.save(oldAuthor);
		model.addAttribute("text","Autor editado de forma correcta");
		return "Message";
	}	
	
	@RequestMapping("/author/{id}/deleteAuthor")
	public String deleteAuthor(Model model, @PathVariable long id) {
		Author author = repository.findById(id);
		if (author!=null) {
			model.addAttribute("author", author);
			model.addAttribute("text","Autor eliminado de forma correcta");
		}
		repository.deleteById(id);
		
		return "Message";
	}
	
	
	
	/*@RequestMapping("/newAuthor/uploaded")
	public String newAuthorUploaded(Model model,@RequestParam("nameAuthor") String name,@RequestParam("birthDate") String bornDate,@RequestParam("defuncDate") String deathDate, @RequestParam("bornPlace") String bornPlace, @RequestParam("urlMap") String urlMap, @RequestParam("file") MultipartFile file) {
		int imageId = com.santatecla.G1.ImageManagerController.getNewId();
		com.santatecla.G1.ImageManagerController.handleFileUpload(model, file, imageId);
		Author a = new Author(name, bornDate, deathDate, imageId);
		repository.save(a);
		model.addAttribute(a);
		return "/author/{id}";
	}*/
}
