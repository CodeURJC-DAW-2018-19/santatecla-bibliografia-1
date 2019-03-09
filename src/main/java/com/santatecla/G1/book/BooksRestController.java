package com.santatecla.G1.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.author.AuthorService;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.citation.CitationService;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeService;

@RestController
@RequestMapping("/api")
public class BooksRestController {
	interface BookDetailView extends Book.BasicView, Book.AuthorView, Book.CitationsView, Book.ThemeView,
			Author.BasicView, Citation.BasicView, Theme.BasicView {
	}

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private CitationService citationService;

	@Autowired
	private ThemeService themeService;

	@JsonView(Book.BasicView.class)
	@RequestMapping(value = "book", method = GET)
	public ResponseEntity<Collection<Book>> getBooks() {
		return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
	}

	@JsonView(Book.BasicView.class)
	@RequestMapping(value = "/book-pageable", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> booksPageable(Pageable page) {
		return new ResponseEntity<>(bookService.findAll(page).getContent(), HttpStatus.OK);
	}

	@JsonView(Book.BasicView.class)
	@RequestMapping(value = "/book-name-pageable", method = RequestMethod.GET)
	public ResponseEntity<List<String>> booksPageableGuest(Pageable page) {
		List<Book> books = bookService.findAll(page).getContent();
		List<String> booksName = new ArrayList<>();
		for (Book b : books) {
			booksName.add(b.getTitle());
		}
		return new ResponseEntity<>(booksName, HttpStatus.OK);
	}

	@RequestMapping(value = "/book2", method = POST)
	public ResponseEntity<Book> book(@RequestBody Book book) {
		if (bookService.findByTitleIgnoreCase(book.getTitle()) == null) {
			try {
				Author author = new Author();
				if (book.getAuthor() != null) {
					author = authorService.findById(book.getAuthor().getId());
					book.setAuthor(author);
				}
				bookService.save(book);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			try {
				Theme theme = new Theme();
				if (book.getTheme() != null) {
					theme = themeService.findById(book.getTheme().getId());
					book.setTheme(theme);
				}
				bookService.save(book);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			try {
				ArrayList<Citation> citations = new ArrayList<>();
				if (book.getCitations() != null) {
					for (Citation citation : book.getCitations()) {
						citations.add(citationService.findById(book.getId()));
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

	@JsonView(BookDetailView.class)
	@RequestMapping(value = "/book/{id}", method = GET)
	public ResponseEntity<Book> getBook(@PathVariable long id) {
		Book book = bookService.findOne(id);
		if (book != null) {
			System.out.println(book.toString());
			List<Citation> citations = book.getCitations();
			Author author = book.getAuthor();
			Theme theme = book.getTheme();
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@JsonView(BookDetailView.class)
	@RequestMapping(value = "/book/{id}", method = DELETE)
	public ResponseEntity<Book> deleteAuthor(@PathVariable long id) {
		Book book = bookService.findOne(id);
		if (book != null) {
			bookService.deleteById(id);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@JsonView(BookDetailView.class)
	@RequestMapping(value = "book2/{id}", method = PATCH)
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
	@RequestMapping(value = "book/{id}", method = PATCH)
	public ResponseEntity<Book> updateBook(Model model, @RequestBody Book newBook, @PathVariable long id,
			MultipartFile file, Long authorId, Long themeId) {
		Book oldBook = bookService.findOne(id);
		if (oldBook != null) {
			oldBook.update(newBook);
			if (authorId != null) {
				Author author = authorService.findById(authorId);
				if (author != null) {
					oldBook.setAuthor(author);
					author.addBook(oldBook);
					authorService.save(author);
				}
			}
			if (themeId != null) {
				Theme theme = themeService.findById(themeId);
				if (theme != null) {
					oldBook.setTheme(theme);
					theme.addBook(oldBook);
					themeService.save(theme);
				}
			}
			if ((file != null) && (!file.isEmpty())) {
				int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
				oldBook.setImgId(imgId);
				com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);
			}
			bookService.save(oldBook);
			model.addAttribute("text", "Libro actualizado correctamente");
			return new ResponseEntity<>(oldBook, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@JsonView(BookDetailView.class)
	@RequestMapping(value = "/book", method = POST)
	public Book saveBook(Model model, @RequestBody Book book, MultipartFile file, Long authorId, Long themeId) {
		bookService.save(book);
		if (authorId != null) {
			Author author = authorService.findById(authorId);
			if (author != null) {
				book.setAuthor(author);
				author.addBook(book);
				authorService.save(author);
			}
		}
		if (themeId != null) {
			Theme theme = themeService.findById(themeId);
			if (theme != null) {
				book.setTheme(theme);
				theme.addBook(book);
				themeService.save(theme);
			}
		}
		if ((file != null) && (!file.isEmpty())) {
			int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
			book.setImgId(imgId);
			com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);
		} else
			book.setImgId(-2);
		bookService.save(book);
		model.addAttribute("text", "Book Created");
		return book;
	}

}
