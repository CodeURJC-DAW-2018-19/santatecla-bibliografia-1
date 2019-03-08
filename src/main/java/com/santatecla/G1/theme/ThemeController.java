package com.santatecla.G1.theme;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.TabController;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.user.UserComponent;
import com.santatecla.G1.book.BookService;




@Controller
public class ThemeController {
	
	@Autowired
	private ThemeService themeService;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private TabController tabs;

	@Autowired
	private BookService bookService;

	
	
	@RequestMapping("/theme/{id}")
	public String theme(Model model, @PathVariable long id) {
		Theme theme = themeService.findById(id);
		List<Book> books = bookService.findByTheme(theme);
		List<Author> authors = new ArrayList<>();
		List<Citation> citation = new ArrayList<>();
		for(Book b: books) {
			if(b.getAuthor()!=null)
				authors.add(b.getAuthor());
			System.out.println(b.getTitle());
			List<Citation> aux = b.getCitations();
			for(Citation c: aux) {
				citation.add(c);
			}
		}
		
		if (theme!=null) {
			if (!authors.isEmpty())
				model.addAttribute("authors",authors);
			model.addAttribute("books",books);
			model.addAttribute("theme", theme);
			model.addAttribute("citations",citation);
			model.addAttribute("entity","theme");
		}
		
		tabs.userTabs(model, "/theme/"+ id, theme.getName(), true, id);
		return "themePage";
	}
	
	@GetMapping("/table-theme")
	public String showMoreThemes(Model model, Pageable page) {
		Page<Theme> themes = themeService.findAll(page);

		model.addAttribute("themes", themes);
		model.addAttribute("nTheme", page.getPageNumber());
		model.addAttribute("indexTheme", themes.getTotalPages());

		return "pageableTheme";
	}
	
	@RequestMapping("/newTheme")
	public String newTheme(Model model) {
		return "themeFormEdit";
	}
	
	@RequestMapping("/saveTheme")
	public String author(Model model, Theme theme) {
		themeService.save(theme);
		System.out.println(theme.toString());
		model.addAttribute("text","Theme Created");
		return "Message";
	}
	
	public Collection<Theme> themes(){
		return themeService.findAll();
	}
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
		}
	}
	
	@RequestMapping("/theme/{id}/updateTheme")
	public String updateAuthor(Model model, Theme newTheme, @PathVariable long id) {
		Theme oldTheme = themeService.findById(id);
		oldTheme.update(newTheme);
		themeService.save(oldTheme);		
		model.addAttribute("text","Tema editado de forma correcta");
		return "Message";
	}
	
	
	@RequestMapping("/theme/{id}/deleteTheme")
	public String deleteAuthor(Model model, @PathVariable long id) {
		Theme theme = themeService.findById(id);
		if (theme!=null) {
			model.addAttribute("theme", theme);
			model.addAttribute("text","Tema eliminado de forma correcta");
		}
		themeService.deleteById(id);
		
		return "Message";
	}
	
	
}
