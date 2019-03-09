package com.santatecla.G1.citation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.Book.AuthorView;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.theme.Theme;

@RestController
@RequestMapping("/api")
public class CitationRestController {
	interface CitationDetailView
			extends Citation.BasicView, Citation.BookView, Citation.ThemeView, Book.BasicView, Theme.BasicView, Book.AuthorView, Author.BasicView {
	}

	@Autowired
	private CitationService citationService;

	@Autowired
	private BookService bookService;

	@JsonView(Citation.BasicView.class)
	@RequestMapping(value = "/citation", method = GET)
	public ResponseEntity<Collection<Citation>> citations() {
		return new ResponseEntity<>(citationService.findAll(), HttpStatus.OK);
	}

	@JsonView(Citation.BasicView.class)
	@RequestMapping(value = "/citation-pageable", method = RequestMethod.GET)
	public ResponseEntity<List<Citation>> citationPageable(Pageable page) {
		return new ResponseEntity<>(citationService.findAll(page).getContent(), HttpStatus.OK);
	}

	@JsonView(Citation.BasicView.class)
	@RequestMapping(value = "/citation-name-pageable", method = RequestMethod.GET)
	public ResponseEntity<List<String>> citationPageableGuest(Pageable page) {
		List<Citation> citation = citationService.findAll(page).getContent();
		List<String> citationName = new ArrayList<>();
		for (Citation c : citation) {
			citationName.add(c.getText());
		}
		return new ResponseEntity<>(citationName, HttpStatus.OK);
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
			System.out.println(citation.toString());
			Book book = citation.getBook();
			Theme theme = citation.getTheme();
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

	// ----------------------------- METHODS WITH UPLOAD IMAGES
	// -------------------------------------------------

	@JsonView(CitationDetailView.class)
	@RequestMapping(value = "/citation", method = POST)
	public ResponseEntity<Citation> saveCitation(Model model, @RequestBody Citation citation,
			@RequestParam Long bookId) {
		citationService.save(citation);
		if (bookId != null) {
			Book book = bookService.findOne(bookId);
			if (book != null) {
				book.addCitations(citation);
				citation.setBook(book);
				bookService.save(book);
			} else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		citationService.save(citation);
		model.addAttribute("text", "Citation Created");
		return new ResponseEntity<>(citation, HttpStatus.OK);
	}

	@JsonView(CitationDetailView.class)
	@RequestMapping(value = "/citation/{id}", method = PATCH)
	public ResponseEntity<Citation> updateCitation(Model model, @RequestBody Citation newCitation,
			@PathVariable long id, Long bookId) {
		Citation oldCitation = citationService.findById(id);
		if (oldCitation != null) {
			oldCitation.update(newCitation);
			if (bookId != null) {
				Book book = bookService.findOne(bookId);
				if (book != null) {
					book.addCitations(oldCitation);
					oldCitation.setBook(book);
					bookService.save(book);
				} else
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			citationService.save(oldCitation);
			model.addAttribute("text", "Cita editada de forma correcta");
			return new ResponseEntity<>(oldCitation, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
