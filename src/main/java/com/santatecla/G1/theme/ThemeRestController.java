package com.santatecla.G1.theme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.user.UserComponent;
@RestController
@RequestMapping("/api")
public class ThemeRestController {
	
	@Autowired
	private ThemeService themeService;
	@Autowired
	private UserComponent userComponent;

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/theme/{id}", method = GET)
	public ResponseEntity<Theme> theme(Model model, @PathVariable long id) {
		Theme theme = themeService.findById(id);
		if(theme!=null) {
			List<Book> books = bookService.findByTheme(theme);
			List<Author> authors = new ArrayList<>();
			List<Citation> citation = new ArrayList<>();
			for(Book b: books) {
				authors.add(b.getAuthor());
				System.out.println(b.getTitle());
				List<Citation> aux = b.getCitations();
				for(Citation c: aux) {
					citation.add(c);
				}
			}
			model.addAttribute("authors",authors);
			model.addAttribute("books",books);
			model.addAttribute("theme", theme);
			model.addAttribute("citations",citation);
			return new ResponseEntity<>(theme, HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value= "/themes", method = POST)
	public Theme theme(Model model, Theme theme) {
		themeService.save(theme);
		System.out.println(theme.toString());
		model.addAttribute("text","Theme Created");
		return theme;
	}
	
	@RequestMapping(value="/getThemes", method = GET)
	public Collection<Theme> themes(){
		return themeService.findAll();
	}
	
	@RequestMapping(value="/theme/{id}", method = PATCH)
	public ResponseEntity<Theme> updateTheme(Model model, Theme newTheme, @PathVariable long id) {
		Theme oldTheme = themeService.findById(id);
		if(oldTheme!=null) {
			oldTheme.update(newTheme);
			themeService.save(oldTheme);		
			model.addAttribute("text","Tema editado de forma correcta");
			return new ResponseEntity<>(oldTheme, HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@RequestMapping(value = "/theme/{id}", method = DELETE)
	public ResponseEntity<Theme> deleteTheme(Model model, @PathVariable long id) {
		Theme theme = themeService.findById(id);
		if (theme!=null) {
			model.addAttribute("theme", theme);
			model.addAttribute("text","Tema eliminado de forma correcta");
			themeService.deleteById(id);
			return new ResponseEntity<>(theme, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//¿Como se le mete un book por el postman?
	/*@RequestMapping(value="/theme/{id}/addBook", method = PATCH)
	public ResponseEntity<Theme> addBook(Model model, @PathVariable long id, Book book){
		Theme theme=themeService.findById(id);
		if(theme!=null) {
			if(book!=null) {
				theme.addBook(book);
				themeService.save(theme);
				model.addAttribute("text", "Libro añadido al tema de forma correcta");
			}
			else model.addAttribute("text", "El libro que intentas añadir es null");
			return new ResponseEntity<>(theme, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}*/
}
