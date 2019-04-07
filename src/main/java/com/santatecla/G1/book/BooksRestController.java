package com.santatecla.G1.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.author.AuthorService;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.citation.CitationService;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeService;
import com.santatecla.G1.user.UserComponent;

@RestController
@RequestMapping("/api")
public class BooksRestController {
	interface BookBasicView extends Book.NameView, Book.BasicView {} 
	interface BookDetailView extends Book.NameView, Book.BasicView, Book.AuthorView, Book.CitationsView, Book.ThemeView,
			Author.NameView,Author.BasicView, Citation.BasicView, Theme.BasicView {}

	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private CitationService citationService;

	@Autowired
	private ThemeService themeService;

	//Get the book/s with pagination or not, to logged or not logged users.
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public MappingJacksonValue books(Pageable page, String title) {
		List<Book> books;
		if(title!=null) {
			books = bookService.findByTitleContaining(title, page);
		}
		else {
			books = bookService.findAll(page).getContent();		
		}
		MappingJacksonValue result = new MappingJacksonValue(books);
		if(books!=null) {
			if(userComponent.isLoggedUser())
				result.setSerializationView(BookBasicView.class);
			else
				result.setSerializationView(Book.NameView.class);
			return result;
		}
		else return null;
	}
	
	//Create a book without a img
	@JsonView(BookDetailView.class)
	@RequestMapping(value = "/books", method = POST)
	public ResponseEntity<Book> book(@RequestBody Book book) {
		//If the book doesn't exists, we create it
		if (bookService.findByTitleIgnoreCase(book.getTitle()) == null) {
			//Trying to associate an author to the book
			try {
				bookService.save(book);
				Author author = new Author();
				if (book.getAuthor() != null) {
					author = authorService.findById(book.getAuthor().getId());
					author.addBook(book);
					book.setAuthor(author);
					authorService.save(author);					
				}
				bookService.save(book);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			//Trying  to associate a theme to the book
			try {
				bookService.save(book);
				Theme theme = new Theme();
				if (book.getTheme() != null) {
					theme = themeService.findById(book.getTheme().getId());
					theme.addBook(book);
					book.setTheme(theme);
					themeService.save(theme);
				}
				bookService.save(book);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			//Trying to associte the citations to the boook.
			try {
				ArrayList<Citation> citations = new ArrayList<>();
				if (book.getCitations() != null) {
					for (Citation citation : book.getCitations()) {
						citations.add(citationService.findById(citation.getId()));
					}
				}
				bookService.save(book);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			bookService.save(book);
			return new ResponseEntity<>(book, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(HttpStatus.IM_USED);

	}
	// Get the book by the id
	@JsonView(BookDetailView.class)
	@RequestMapping(value = "/books/{id}", method = GET)
	public ResponseEntity<Book> getBook(@PathVariable long id) {
		Book book = bookService.findOne(id);
		if (book != null) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	// Delete the book by the id
	@JsonView(BookDetailView.class)
	@RequestMapping(value = "/books/{id}", method = DELETE)
	public ResponseEntity<Book> deleteAuthor(@PathVariable long id) {
		Book book = bookService.findOne(id);
		if (book != null) {
			bookService.deleteById(id);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//Update the book by id.
	@JsonView(BookDetailView.class)
	@RequestMapping(value = "books/{id}", method = PATCH)
	public ResponseEntity<Book> updateBook2(@RequestBody Book newBook, @PathVariable long id) {
		Book oldBook = bookService.findOne(id);
		if (oldBook != null) {
			oldBook.update(newBook);
			Author author = new Author();
			if (oldBook.getAuthor() != null) {
				author = authorService.findById(oldBook.getAuthor().getId());
				if (author != null) {
					oldBook.setAuthor(author);
					author.addBook(oldBook);
					authorService.save(author);
				}
			}
			Theme theme = new Theme();
			if (oldBook.getTheme() != null) {
				theme = themeService.findById(oldBook.getTheme().getId());
				if (theme != null) {
					oldBook.setTheme(theme);
					theme.addBook(oldBook);
					themeService.save(theme);
				}
			}
			bookService.save(oldBook);
			return new ResponseEntity<>(oldBook, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// ----------------------------- METHODS WITH UPLOAD IMAGES
	// -------------------------------------------------
	
	@JsonView(BookDetailView.class)
	@RequestMapping(value = "books/{id}/image", method = PATCH)
	public ResponseEntity<Book> updateAuthorImage(Model model, @PathVariable long id, @RequestParam(value="file")MultipartFile file) {
		Book oldBook = bookService.findById(id);
		if (oldBook != null) {
			if ((file != null) && (!file.isEmpty())) {
				int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
				com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);
				oldBook.setImgId(imgId);
			}
			bookService.save(oldBook);
			return new ResponseEntity<>(oldBook, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	

}
