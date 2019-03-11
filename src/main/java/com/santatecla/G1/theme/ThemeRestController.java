package com.santatecla.G1.theme;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.citation.Citation;

@RestController
@RequestMapping("/api")
public class ThemeRestController {
	interface ThemeDetailView extends Theme.BasicView, Theme.BooksView, Book.BasicView, Book.AuthorView, Author.BasicView, Book.CitationsView, Citation.BasicView{
	}

	@Autowired
	private ThemeService themeService;

	@Autowired
	private BookService bookService;

	@JsonView(Theme.BasicView.class)
	@RequestMapping(value = "/theme", method = GET)
	public ResponseEntity<Collection<Theme>> themes() {
		return new ResponseEntity<>(themeService.findAll(), HttpStatus.OK);
	}
	
	@JsonView(ThemeDetailView.class)
	@RequestMapping(value = "/theme-search/{name}", method = GET)
	public ResponseEntity<List<Theme>> searchTheme(@PathVariable String name) {
		return new ResponseEntity<>(themeService.findByNameContaining(name), HttpStatus.OK);
	}
	
	@JsonView(Theme.BasicView.class)
	@RequestMapping(value = "/theme-pageable", method = RequestMethod.GET)
	public ResponseEntity<List<Theme>> themePageable(Pageable page) {
		return new ResponseEntity<>(themeService.findAll(page).getContent(), HttpStatus.OK);
	}
	
	@JsonView(ThemeDetailView.class)
	@RequestMapping(value = "/theme2", method = POST)
	public ResponseEntity<Theme> theme(@RequestBody Theme theme) {
		if (themeService.findByNameIgnoreCase(theme.getName()) == null) {
			try {
				ArrayList<Book> books = new ArrayList<>();
				for (Book book : theme.getBook()) {

					books.add(bookService.findById(book.getId()));
					book.setTheme(theme);
					bookService.save(book);
				}

				theme.setBooks(books);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			themeService.save(theme);
			return new ResponseEntity<>(theme, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(HttpStatus.IM_USED);
	}

	@JsonView(Theme.BasicView.class)
	@RequestMapping(value = "/theme-name-pageable", method = RequestMethod.GET)
	public ResponseEntity<List<String>> themePageableGuest(Pageable page) {
		List<Theme> theme = themeService.findAll(page).getContent();
		List<String> themeName = new ArrayList<>();
		for (Theme t : theme) {
			themeName.add(t.getName());
		}
		return new ResponseEntity<>(themeName, HttpStatus.OK);
	}

	@JsonView(ThemeDetailView.class)
	@RequestMapping(value = "/theme/{id}", method = GET)
	public ResponseEntity<Theme> theme(@PathVariable long id) {
		Theme theme = themeService.findById(id);
		if (theme != null) {
			List<Book> books = bookService.findByTheme(theme);
			List<Author> authors = new ArrayList<>();
			List<Citation> citation = new ArrayList<>();
			for (Book b : books) {
				authors.add(b.getAuthor());
				System.out.println(b.getTitle());
				List<Citation> aux = b.getCitations();
				for (Citation c : aux) {
					citation.add(c);
				}
			}
			return new ResponseEntity<>(theme, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@JsonView(ThemeDetailView.class)
	@RequestMapping(value = "/theme2/{id}", method = PATCH)
	public ResponseEntity<Theme> updateTheme(@RequestBody Theme newTheme, @PathVariable long id) {
		Theme oldTheme = themeService.findById(id);
		if (oldTheme != null) {
			oldTheme.update(newTheme);
			themeService.save(oldTheme);
			return new ResponseEntity<>(oldTheme, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@JsonView(ThemeDetailView.class)
	@RequestMapping(value = "/theme/{id}", method = DELETE)
	public ResponseEntity<Theme> deleteTheme(@PathVariable long id) {
		Theme theme = themeService.findById(id);
		if (theme != null) {
			themeService.deleteById(id);
			return new ResponseEntity<>(theme, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// ----------------------------- METHODS WITH UPLOAD IMAGES
	// -------------------------------------------------

	@JsonView(ThemeDetailView.class)
	@RequestMapping(value = "/theme", method = POST)
	public Theme theme(Model model, @RequestBody Theme theme) {
		themeService.save(theme);
		System.out.println(theme.toString());
		model.addAttribute("text", "Theme Created");
		return theme;
	}

}
