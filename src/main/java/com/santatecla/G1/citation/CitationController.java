package com.santatecla.G1.citation;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.user.UserComponent;

@Controller
public class CitationController {
	
	@Autowired
	private CitationRepository repository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserComponent userComponent;
		
	public Collection<Citation> citations(){
		return repository.findAll();
	}
	@RequestMapping("/newCitation")
	public String citation(Model model) {
		return "CitationForm";
	}
	
	@RequestMapping("/saveCitation")
	public String saveCitation(Model model, Citation citation) {
		//System.out.println("Entro a guardar la cita con el texto "+ text +" del libro "+bookTitle);
		//Book book = bookRepository.findByTitle(bookTitle);
		//Citation citation = new Citation(text, book);
		repository.save(citation);
		model.addAttribute("text","Citation Created");
		return "Message";
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
}
