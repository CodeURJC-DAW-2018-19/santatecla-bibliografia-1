package com.santatecla.G1.theme;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.user.UserComponent;

@RestController
@RequestMapping("/api")
public class ThemeRestController {
	interface ThemeBasicView extends Theme.NameView, Theme.BasicView {}
	interface ThemeDetailView extends Theme.BasicView, Theme.BooksView,Book.NameView, Book.BasicView, Book.AuthorView,
			Author.NameView,Author.BasicView, Book.CitationsView, Citation.BasicView {
	}
	
	@Autowired
	private UserComponent userComponent;

	@Autowired
	private ThemeService themeService;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/themes", method = RequestMethod.GET)
	public MappingJacksonValue themes(Pageable page, String name) {
		List<Theme> themes;
		if(name!=null) {
			themes = themeService.findByNameContaining(name, page);
		}
		else {
			themes = themeService.findAll(page).getContent();		
		}
		MappingJacksonValue result = new MappingJacksonValue(themes);
		if(themes!=null) {
			if(userComponent.isLoggedUser())
				result.setSerializationView(ThemeBasicView.class);
			else
				result.setSerializationView(Theme.NameView.class);
			return result;
		}
		else return null;
	}

	@JsonView(ThemeDetailView.class)
	@RequestMapping(value = "/themes", method = POST)
	public ResponseEntity<Theme> theme(@RequestBody Theme theme) {
		if (themeService.findByNameIgnoreCase(theme.getName()) == null) {
			if (theme.getBook() != null) {
				try {
					themeService.save(theme);
					Book bookAux= new Book();
					ArrayList<Book> books = new ArrayList<>();
					for (Book book : theme.getBook()) {
						books.add(bookService.findById(book.getId()));
						bookAux=bookService.findById(book.getId());
						bookAux.setTheme(theme);
						bookService.save(bookAux);
					}

					theme.setBooks(books);
				} catch (Exception e) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
			themeService.save(theme);
			return new ResponseEntity<>(theme, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(HttpStatus.IM_USED);
	}

	@JsonView(ThemeDetailView.class)
	@RequestMapping(value = "/themes/{id}", method = GET)
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
	@RequestMapping(value = "/themes/{id}", method = PATCH)
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
	@RequestMapping(value = "/themes/{id}", method = DELETE)
	public ResponseEntity<Theme> deleteTheme(@PathVariable long id) {
		Theme theme = themeService.findById(id);
		if (theme != null) {
			themeService.deleteById(id);
			return new ResponseEntity<>(theme, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
