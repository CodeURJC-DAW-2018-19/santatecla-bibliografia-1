package com.santatecla.G1.citation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.user.UserComponent;

@RestController
@RequestMapping("/api")
public class CitationRestController {
	interface CitationDetailView
			extends Citation.BasicView, Citation.BookView, Citation.ThemeView, Book.BasicView, Theme.BasicView, Book.AuthorView, Author.BasicView {
	}

	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private CitationService citationService;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/citations", method = RequestMethod.GET)
	public MappingJacksonValue authors(Pageable page, String text) {
		List<Citation> citations;
		if(!userComponent.isLoggedUser())
			return null;
		if(text!=null) {
			citations = citationService.findByTextContaining(text, page);
		}
		else {
			citations = citationService.findAll(page).getContent();		
		}
		MappingJacksonValue result = new MappingJacksonValue(citations);
		if(citations!=null) {
			result.setSerializationView(Citation.BasicView.class);
			return result;
		}
		else return null;
	}

	@JsonView(CitationDetailView.class)
	@RequestMapping(value = "/citation2", method = POST)
	public ResponseEntity<Citation> citation(@RequestBody Citation citation) {
		if (citationService.findByTextIgnoreCase(citation.getText()) == null) {
			Book book = new Book();
			if (citation.getBook() != null) {
				book = bookService.findById(citation.getBook().getId());

				citation.setBook(book);
			}
			citationService.save(citation);
			return new ResponseEntity<>(citation, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(HttpStatus.IM_USED);
	}

	@JsonView(CitationDetailView.class)
	@RequestMapping(value = "/citation/{id}", method = GET)
	public ResponseEntity<Citation> citation(@PathVariable long id) {
		Citation citation = citationService.findById(id);
		if (citation != null) {
			return new ResponseEntity<>(citation, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@JsonView(CitationDetailView.class)
	@RequestMapping(value = "/citation/{id}", method = DELETE)
	public ResponseEntity<Citation> deleteCitation(@PathVariable long id) {
		Citation citation = citationService.findById(id);
		if (citation != null) {
			citationService.deleteById(id);
			return new ResponseEntity<>(citation, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@JsonView(CitationDetailView.class)
	@RequestMapping(value = "/citation2/{id}", method = PATCH)
	public ResponseEntity<Citation> updateCitation2(@RequestBody Citation newCitation, @PathVariable long id) {
		Citation oldCitation = citationService.findById(id);
		if (oldCitation != null) {
			oldCitation.update(newCitation);
			Book book = new Book();
			if (oldCitation.getBook() != null) {
				book = bookService.findById(oldCitation.getBook().getId());
				if (book != null) {
					book.addCitations(oldCitation);
					oldCitation.setBook(book);
					bookService.save(book);
				} else
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			citationService.save(oldCitation);
			return new ResponseEntity<>(oldCitation, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
