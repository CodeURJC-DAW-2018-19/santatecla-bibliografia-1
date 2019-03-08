package com.santatecla.G1.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.author.AuthorService;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeService;

@RestController
@RequestMapping("/api")
public class BooksRestController {
	interface BookDetailView extends Book.BasicView, Book.AuthorView, Book.CitationsView, Book.ThemeView, Author.BasicView, Citation.BasicView, Theme.BasicView {}
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ThemeService themeService;
	
	
	@JsonView(Book.BasicView.class)
	@RequestMapping(value="book", method = GET)
	public Collection<Book> getBooks(){
		return bookService.findAll();
	}
	
	@JsonView(Book.BasicView.class)
	@RequestMapping(value="/bookPageable" , method = RequestMethod.GET)
	public List<Book> authors(Pageable page){
		return bookService.findAll(page).getContent();
	}
	
	@JsonView(BookDetailView.class)
	@RequestMapping(value = "/book", method=POST)
	public Book saveBook(Model model, Book book, MultipartFile file, Long authorId, Long themeId) {
		bookService.save(book);
		if(authorId!=null) {
			Author author = authorService.findById(authorId);
			if(author!=null) {
				book.setAuthor(author);
				author.addBook(book);
				authorService.save(author);
			}
		}
		if(themeId!=null) {
			Theme theme = themeService.findById(themeId);
			if(theme!=null) {
				book.setTheme(theme);
				theme.addBook(book);
				themeService.save(theme);
			}
		}
		if((file!=null)&&(!file.isEmpty())) {
			int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
			book.setImgId(imgId);
			com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);
		}
		else
			book.setImgId(-2);
		bookService.save(book);
		model.addAttribute("text","Book Created");
		return book;
	}	
		
	@JsonView(BookDetailView.class)
	@RequestMapping(value = "/book/{id}", method = GET)
	public ResponseEntity<Book> getBook(Model model, @PathVariable long id) {
		Book book = bookService.findOne(id);
		if (book!=null) {
			System.out.println(book.toString());
			List <Citation> citations = book.getCitations();
			Author author = book.getAuthor();
			Theme theme = book.getTheme();
			model.addAttribute("book", book);
			model.addAttribute("authors", author);
			model.addAttribute("themes", theme);
			model.addAttribute("citations",citations);
			return new ResponseEntity<>(book,HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@JsonView(BookDetailView.class)
	@RequestMapping(value = "book/{id}", method = PATCH)
	public ResponseEntity<Book> updateBook(Model model,Book newBook , @PathVariable long id, MultipartFile file, Long authorId, Long themeId) {
		Book oldBook = bookService.findOne(id);
		if(oldBook!=null) {
			oldBook.update(newBook);
			if(authorId!=null) {
				Author author = authorService.findById(authorId);
				if(author!=null) {
					oldBook.setAuthor(author);
					author.addBook(oldBook);
					authorService.save(author);
				}
			}
			if(themeId!=null) {
			Theme theme = themeService.findById(themeId);
				if(theme!=null) {
					oldBook.setTheme(theme);
					theme.addBook(oldBook);
					themeService.save(theme);
				}				
			}
			if((file!=null)&&(!file.isEmpty())) {
				int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
				oldBook.setImgId(imgId);
				com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);				
			}
			bookService.save(oldBook);		
			model.addAttribute("text","Libro actualizado correctamente");
			return new ResponseEntity<>(oldBook, HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@JsonView(BookDetailView.class)
	@RequestMapping(value = "/book/{id}", method = DELETE)
	public ResponseEntity<Book> deleteAuthor(Model model, @PathVariable long id) {
		Book book = bookService.findOne(id);
		if (book!=null) {
			model.addAttribute("book", book);
			model.addAttribute("text","Libro eliminado de forma correcta");
			bookService.deleteById(id);
			return new ResponseEntity<>(book,HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
