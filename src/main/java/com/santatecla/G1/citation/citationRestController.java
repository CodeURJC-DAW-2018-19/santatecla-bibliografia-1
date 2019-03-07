package com.santatecla.G1.citation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.user.UserComponent;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/api")
public class citationRestController {
	interface CitationDetailView extends Citation.BasicView, Citation.BookView, Citation.ThemeView, Book.BasicView, Theme.BasicView {}
	
	@Autowired
	private CitationService citationService;
	@Autowired
	private BookService bookService;
	@Autowired
	private UserComponent userComponent;

	
	
	@JsonView(Citation.BasicView.class)
	@RequestMapping(value="/citation", method = GET)
	public Collection<Citation> citations(){
		return citationService.findAll();
	}
	
	@JsonView(Citation.BasicView.class)
	@RequestMapping(value="/citation", method = POST)
	public Citation saveCitation(Model model, Citation citation) {
		Book book = bookService.findByTitle(citation.getTextAux());
		Citation quote = new Citation(citation.getText(),book); 
		book.addCitations(quote);
		citationService.save(quote);
		model.addAttribute("text","Citation Created");
		return quote;
	}
	
	
	@JsonView(CitationDetailView.class)
	@RequestMapping(value="/citation/{id}", method=PATCH)
	public ResponseEntity<Citation> updateCitation(Model model, Citation newCitation, @PathVariable long id) {
		Citation oldCitation=citationService.findById(id);
		if(oldCitation!=null) {
			oldCitation.update(newCitation);
			citationService.save(oldCitation);		
			model.addAttribute("text","Cita editada de forma correcta");
			return new ResponseEntity<>(oldCitation, HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@JsonView(CitationDetailView.class)
	@RequestMapping(value="/citation/{id}", method = DELETE)
	public ResponseEntity<Citation> deleteCitation(Model model, @PathVariable long id) {
		Citation citation = citationService.findById(id);
		if (citation!=null) {
			model.addAttribute("Citation", citation);
			model.addAttribute("text","Cita eliminada de forma correcta");
			citationService.deleteById(id);	
			return new ResponseEntity<>(citation, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
