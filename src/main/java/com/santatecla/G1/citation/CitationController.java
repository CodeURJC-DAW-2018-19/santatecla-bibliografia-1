package com.santatecla.G1.citation;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.book.Book;

@Controller
public class CitationController {
	
	@Autowired
	private CitationRepository repository;
	
	public Collection<Citation> citations(){
		return repository.findAll();
	}
	@RequestMapping("/newCitation")
	public String newBook(Model model) {
		
		return "CitationForm";
	}
	
	@RequestMapping("/saveCitation")
	public String saveBook(Model model, Citation citation) {
		repository.save(citation);
		model.addAttribute("text","Citation Created");
		return "Message";
	}
}
