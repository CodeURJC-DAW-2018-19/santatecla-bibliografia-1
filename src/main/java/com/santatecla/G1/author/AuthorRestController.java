package com.santatecla.G1.author;

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
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.theme.Theme;

@RestController
@RequestMapping("/api")
public class AuthorRestController {
	interface AuthorDetailView extends Author.BasicView, Author.BooksView, Book.BasicView, Book.ThemeView, Theme.BasicView, Book.CitationsView, Citation.BasicView {
	}

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	@JsonView(Author.BasicView.class)
	@RequestMapping(value = "/author", method = GET)
	public ResponseEntity<List<Author>> authors() {
		return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
	}

	@JsonView(Author.BasicView.class)
	@RequestMapping(value = "/author-pageable", method = RequestMethod.GET)
	public ResponseEntity<List<Author>> authorsPageable(Pageable page) {
		return new ResponseEntity<>(authorService.findAll(page).getContent(), HttpStatus.OK);
	}

	@RequestMapping(value = "/author2", method = POST)
	public ResponseEntity<Author> author(@RequestBody Author author) {
		if (authorService.findByNameIgnoreCase(author.getName()) == null) {
			if (author.getBooks() != null) {
				ArrayList<Book> books = new ArrayList<>();
				for (Book book : author.getBooks()) {
					books.add(bookService.findById(book.getId()));
				}
				author.setBooks(books);
			}
			authorService.save(author);
			return new ResponseEntity<>(author, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(HttpStatus.IM_USED);
	}

	@JsonView(Author.BasicView.class)
	@RequestMapping(value = "/author-name-pageable", method = RequestMethod.GET)
	public ResponseEntity<List<String>> authorPageableGuest(Pageable page) {
		List<Author> author = authorService.findAll(page).getContent();
		List<String> authorName = new ArrayList<>();
		for (Author a : author) {
			authorName.add(a.getName());
		}
		return new ResponseEntity<>(authorName, HttpStatus.OK);
	}

	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/author/{id}", method = GET)
	public ResponseEntity<Author> getAuthor(@PathVariable long id) {
		Author a = authorService.findById(id);
		if (a != null)
			return new ResponseEntity<>(a, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/author/{id}", method = PATCH)
	public ResponseEntity<Author> updateAuthor(@RequestBody Author newAuthor, @PathVariable long id) {
		Author oldAuthor = authorService.findById(id);
		if (oldAuthor != null) {
			oldAuthor.update(newAuthor);
			authorService.save(oldAuthor);
			return new ResponseEntity<>(oldAuthor, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/author/{id}", method = DELETE)
	public ResponseEntity<Author> deleteAuthor(@PathVariable long id) {
		Author author = authorService.findById(id);
		if (author != null) {
			authorService.deleteById(id);
			return new ResponseEntity<>(author, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	
	
	// ----------------------------- METHODS WITH UPLOAD IMAGES -------------------------------------------------
	
	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/author", method = POST)
	public Author author(Model model, @RequestBody Author author, MultipartFile file) {
		if ((file != null) && (!file.isEmpty())) {
			int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
			com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);
			author.setImgId(imgId);
		} else
			author.setImgId(-1);
		authorService.save(author);
		model.addAttribute("text", "Autor creado correctamente");
		System.out.println(author.toString());
		return author;
	}
}
