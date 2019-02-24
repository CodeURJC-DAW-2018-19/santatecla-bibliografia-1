package com.santatecla.G1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.author.AuthorRepository;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.theme.ThemeRepository;
import com.santatecla.G1.user.UserComponent;


@Controller
public class IndexController {
	
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository booksRepository;
	@Autowired
	private ThemeRepository themesRepository;
	@Autowired
	private UserComponent userComponent;
	
	
	
	@RequestMapping("/index")
	public String author(Model model) {
		
		model.addAttribute("authors",authorRepository.findAll());
		model.addAttribute("books",booksRepository.findAll());
		model.addAttribute("themes",themesRepository.findAll());
		
		return "indexPage";
	}
	
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
			//model.addAttribute("userName",userComponent.getLoggedUser().getName());
		}
	}
	
	@GetMapping("/loginerror")
	public String loginError() {
		return "loginerror";
	}
}
