package com.santatecla.G1.theme;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.book.Book;


@Controller
public class ThemeController {
	
	@Autowired
	private ThemeRepository repository;

	
	
	@RequestMapping("/theme/{id}")
	public String theme(Model model, @PathVariable long id) {
		Optional<Theme> theme = repository.findById(id);
		System.out.println(theme.toString());
		if (theme!=null) {
			model.addAttribute("theme", theme.get());
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
	
	
}
