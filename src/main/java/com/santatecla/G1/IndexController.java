package com.santatecla.G1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.author.Author;
import com.santatecla.G1.author.AuthorRepository;
import com.santatecla.G1.author.AuthorService;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.image.ImageManagerController;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeRepository;
import com.santatecla.G1.theme.ThemeService;
import com.santatecla.G1.user.UserComponent;


@Controller
public class IndexController {
	
	@Autowired
	private AuthorService authorService;
	@Autowired
	private BookService booksService;
	@Autowired
	private ThemeService themesService;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private ImageManagerController imageController;
	@Autowired
	private TabController tabs;
	
	
	
	@RequestMapping("/")
	public String author(Model model) {
		
		model.addAttribute("authors",authorService.findAll(new PageRequest(0, 10)));
		model.addAttribute("books",booksService.findAll(new PageRequest(0, 10)));
		model.addAttribute("themes",themesService.findAll(new PageRequest(0, 10)));
		model.addAttribute("images",imageController.getImages().values());
		
		tabs.modelTabs(model);	
		try {
			 tabs.updateActiveTabs(true);
		}catch (Exception e) {
			
		}
		return "indexPage";
	}
	
	@RequestMapping("/findByAuthor")
	public String findAuthor(Model model,Author author) {
		
		model.addAttribute("authors",authorService.findByName(author.getName()));
		model.addAttribute("books",booksService.findAll());
		model.addAttribute("themes",themesService.findAll());
		model.addAttribute("images",imageController.getImages().values());
		
		return "indexPage";
	}
	@RequestMapping("/findByBook")
	public String findBook(Model model,Book book) {
		
		model.addAttribute("authors",authorService.findAll());
		model.addAttribute("books",booksService.findByTitle(book.getTitle()));
		model.addAttribute("themes",themesService.findAll());
		model.addAttribute("images",imageController.getImages().values());
		
		return "indexPage";
	}
	
	@RequestMapping("/findByTheme")
	public String findTheme(Model model,Theme theme) {
		
		model.addAttribute("authors",authorService.findAll());
		model.addAttribute("books",booksService.findAll());
		model.addAttribute("themes",themesService.findThemesByName(theme.getName()));
		model.addAttribute("images",imageController.getImages().values());
		
		return "indexPage";
	}
	
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
		}
	}
	
	@GetMapping("/loginerror")
	public String loginError() {
		return "loginerror";
	}
	
	@RequestMapping("/chart")
	public String springMVC(Model modelMap) {
		List<Theme> themes=themesService.findAll();
		List<Integer> numBs=new ArrayList<>();
		for(int i=0; i<themes.size();i++) {
			List<Book> books=booksService.findByTheme(themes.get(i));
			numBs.add(books.size());
		} 
		modelMap.addAttribute("themes", themes);
		modelMap.addAttribute("numBooks",numBs);
		return "chart";
	}
}
