package com.santatecla.G1.author;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.user.UserComponent;

@RestController
@RequestMapping("/api")
public class AuthorRestController {
	interface AuthorDetailView extends Author.BasicView, Author.BooksView, Book.BasicView {}
	
	@Autowired
	private AuthorService authorService;
	@Autowired
	private BookService bookService;

	
	@Autowired
	private UserComponent userComponent;
	
	
	@JsonView(Author.BasicView.class)
	@RequestMapping(value="/author" , method = GET)
	public List<Author> authors(){
		return authorService.findAll();
	}
	
	@JsonView(Author.BasicView.class)
	@RequestMapping(value="/authorPageable" , method = RequestMethod.GET)
	public List<Author> authors(Pageable page){
		return authorService.findAll(page).getContent();
	}
	
	@JsonView(AuthorDetailView.class)
	@RequestMapping(value="/author", method = POST)
	public Author author(Model model, Author author,MultipartFile file) {
		authorService.save(author);
		if((file!=null)&&(!file.isEmpty())) {
			int imgId = com.santatecla.G1.image.ImageManagerController.getNextId();
			com.santatecla.G1.image.ImageManagerController.handleFileUpload(model, file, imgId);	
			author.setImgId(imgId);
		}			
		else
			author.setImgId(-1);
		authorService.save(author);
		model.addAttribute("text","Autor creado correctamente");
		System.out.println(author.toString());
		return author;
	}	
	
	@JsonView(AuthorDetailView.class)
	@RequestMapping(value="/author/{id}", method = GET)
	public ResponseEntity<Author> getAuthor(Model model, @PathVariable long id) {
		Author a=authorService.findById(id);
		if(a!=null)
			return new ResponseEntity<>(a, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/author/{id}", method = PATCH)
	public ResponseEntity<Author> updateAuthor(Model model, Author newAuthor, @PathVariable long id) {
		Author oldAuthor = authorService.findById(id);
		if(oldAuthor!=null) {
			oldAuthor.update(newAuthor);
			authorService.save(oldAuthor);
			model.addAttribute("text","Autor editado de forma correcta");
			return new ResponseEntity<>(oldAuthor, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@JsonView(AuthorDetailView.class)
	@RequestMapping(value = "/author/{id}", method = DELETE)
	public ResponseEntity<Author> deleteAuthor(Model model, @PathVariable long id) {
		Author author = authorService.findById(id);
		if (author!=null) {
			model.addAttribute("author", author);
			model.addAttribute("text","Autor eliminado de forma correcta");
			authorService.deleteById(id);
			return new ResponseEntity<>(author, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
