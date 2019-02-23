package com.santatecla.G1.theme;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.book.Book;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.user.UserComponent;


@Controller
public class ThemeController {
	
	@Autowired
	private ThemeRepository repository;
	@Autowired
	private UserComponent userComponent;
	
	
	@RequestMapping("/theme/{id}")
	public String theme(Model model, @PathVariable long id) {
		Theme theme = repository.findById(id);
		List<Citation> citations = repository.findCitationByName(theme.getName());
		System.out.println(theme.toString());
		if (theme!=null) {
			model.addAttribute("theme", theme);
			model.addAttribute("citations",citations);
		}
		return "themePageEdit";
	}
	
	@RequestMapping("/newTheme")
	public String newTheme(Model model) {
		return "themePage";
	}
	
	public Collection<Theme> themes(){
		return repository.findAll();
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
	
	
}
