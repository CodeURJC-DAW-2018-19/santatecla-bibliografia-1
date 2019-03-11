package com.santatecla.G1.citation;

import java.util.ArrayList;
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

import com.santatecla.G1.author.Author;
import com.santatecla.G1.author.AuthorService;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeService;
import com.santatecla.G1.user.UserComponent;

@Controller
public class CitationController {
	
	@Autowired
	private CitationService citationService;
	@Autowired
	private BookService bookService;
	@Autowired
	private ThemeService themeService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private UserComponent userComponent;
		
	public Collection<Citation> citations(){
		return citationService.findAll();
	}
	@RequestMapping("/newCitation")
	public String citation(Model model, @RequestParam long id, @RequestParam String entity) {
		System.out.println(id);
	
		System.out.println(entity);
		
		if (entity.equals("theme")) {
			Theme theme = themeService.findById(id);
			List<Book> books=bookService.findByTheme(theme);
			model.addAttribute("books",books);
		}
		else if (entity.equals("author")) {
			Author author = authorService.findById(id);
			List<Book> books=bookService.findByAuthor(author);
			model.addAttribute("books",books);	
		}
		else {
			Book book = bookService.findOne(id);
			List<Book> books = new ArrayList<>();
			books.add(book);
			model.addAttribute("books",books);
		}
		return "CitationForm";
	}
	
	@RequestMapping("/saveCitation")
	public String saveCitation(Model model, Citation citation) {
		if(bookService.findByTitle(citation.getTextAux())!=null) {
			Book book = bookService.findByTitle(citation.getTextAux());
			Citation quote = new Citation(citation.getText(),book); 
			book.addCitations(quote);
			citationService.save(quote);
			model.addAttribute("text","Citation Created");
		}
		else {
			model.addAttribute("text","Error al crear la cita, no existe ese libro");
		}	
		return "Message";
	}
	
	@GetMapping("/table-citation")
	public String showMoreAuthor(Model model, Pageable page) {
		Page<Citation> citation = citationService.findAll(page);

		model.addAttribute("citations", citation);
		model.addAttribute("nCitation", page.getPageNumber());
		model.addAttribute("indexCitation", citation.getTotalPages());


		return "pageableCitation";
	}
	
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
			model.addAttribute("userName",userComponent.getLoggedUser().getName());
		}
	}
	@RequestMapping("/theme/{id}/updateCitation")
	public String updateCitation(Model model, Citation citation, @PathVariable long id) {
		
		citation.setId(id);
		citationService.save(citation);		
		model.addAttribute("text","Cita editada de forma correcta");
		return "Message";
	}
	
	
	@RequestMapping("/theme/{id}/deleteCitation")
	public String deleteCitation(Model model, @PathVariable long id) {
		Citation citation = citationService.findById(id);
		if (citation!=null) {
			model.addAttribute("theme", citation);
			model.addAttribute("text","Cita eliminada de forma correcta");
		}
		citationService.deleteById(id);
		
		return "Message";
	}
}
