package com.santatecla.G1.citation;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.author.Author;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.user.UserComponent;

@Controller
public class CitationController {
	
	@Autowired
	private CitationService citationService;
	@Autowired
	private BookService bookService;
	@Autowired
	private UserComponent userComponent;
		
	public Collection<Citation> citations(){
		return citationService.findAll();
	}
	@RequestMapping("/newCitation")
	public String citation(Model model) {
		return "CitationForm";
	}
	
	@RequestMapping("/saveCitation")
	public String saveCitation(Model model, Citation citation) {
		Book book = bookService.findByTitle(citation.getTextAux());
		Citation quote = new Citation(citation.getText(),book); 
		book.addCitations(quote);
		citationService.save(quote);
		model.addAttribute("text","Citation Created");
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
	public String updateAuthor(Model model, Citation citation, @PathVariable long id) {
		
		citation.setId(id);
		citationService.save(citation);		
		model.addAttribute("text","Cita editada de forma correcta");
		return "Message";
	}
	
	
	@RequestMapping("/theme/{id}/deleteCitation")
	public String deleteAuthor(Model model, @PathVariable long id) {
		Citation citation = citationService.findById(id);
		if (citation!=null) {
			model.addAttribute("theme", citation);
			model.addAttribute("text","Cita eliminada de forma correcta");
		}
		citationService.deleteById(id);
		
		return "Message";
	}
}
