package com.santatecla.G1.author;

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
import com.santatecla.G1.user.UserComponent;

@RestController
@RequestMapping("/api")
public class AuthorRestController {
	interface AuthorDetailView extends Author.NameView, Author.BasicView, Author.BooksView, Book.BasicView, Book.ThemeView, Theme.BasicView, Book.CitationsView, Citation.BasicView {}
	interface AuthorBasicView extends Author.NameView, Author.BasicView {}
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public MappingJacksonValue authors(Pageable page, String name) {
		List<Author> authors;
		if(name!=null) {
			authors = authorService.findByNameContaining(name, page);
		}
		else {
			authors = authorService.findAll(page).getContent();		
		}
		MappingJacksonValue result = new MappingJacksonValue(authors);
		if(authors!=null) {
			if(userComponent.isLoggedUser())
				result.setSerializationView(AuthorBasicView.class);
			else
				result.setSerializationView(Author.NameView.class);
			return result;
		}
		else return null;
	}
	

	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/authors/{id}", method = GET)
	public ResponseEntity<Author> getAuthor(@PathVariable long id) {
		Author a = authorService.findById(id);
		if (a != null)
			return new ResponseEntity<>(a, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/authors2", method = POST)
	public ResponseEntity<Author> author(@RequestBody Author author) {
		if (authorService.findByNameIgnoreCase(author.getName()) == null) {
			if (author.getBooks() != null) {
				authorService.save(author);
				ArrayList<Book> books = new ArrayList<>();
				Book bookAux= new Book();
				for (Book book : author.getBooks()) {
					books.add(bookService.findById(book.getId()));
					bookAux=bookService.findById(book.getId());
					bookAux.setAuthor(author);
					bookService.save(bookAux);
				}
				author.setBooks(books);
			}
			authorService.save(author);
			return new ResponseEntity<>(author, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(HttpStatus.IM_USED);
	}

	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/authors2/{id}", method = PATCH)
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

	@RequestMapping(value = "/authors/{id}", method = DELETE)
	public ResponseEntity<Author> deleteAuthor(@PathVariable long id) {
		Author author = authorService.findById(id);
		if (author != null) {
			if (author.getBooks()!=null) {
				author.setBooks(null);
				authorService.save(author);
				authorService.deleteById(id);
				return new ResponseEntity<>(author, HttpStatus.OK);
			}
			else {
				System.out.println("sdfdfsda");
				return new ResponseEntity<>(HttpStatus.IM_USED);
			}
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	
	
	// ----------------------------- METHODS WITH UPLOAD IMAGES -------------------------------------------------
	
	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/authors", method = POST)
	public Author author(Model model, Author author, MultipartFile file) {
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
	
	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/authors/{id}", method = PATCH)
	public ResponseEntity<Author> updateAuthor(Model model, Author newAuthor, @PathVariable long id, MultipartFile file) {
		Author oldAuthor = authorService.findById(id);
		if (oldAuthor != null) {
			if ((file != null) && (!file.isEmpty())) {
				int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
				com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);
				oldAuthor.setImgId(imgId);
			} else
				oldAuthor.setImgId(-1);
			oldAuthor.update(newAuthor);
			authorService.save(oldAuthor);
			return new ResponseEntity<>(oldAuthor, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
