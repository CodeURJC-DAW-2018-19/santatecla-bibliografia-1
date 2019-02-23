package com.santatecla.G1.theme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookRepository;

import antlr.collections.List;


@Controller
public class ThemeController {
	
	@Autowired
	private ThemeRepository repository;
	
	@Autowired
	private BookRepository repositoryB;

	
	
	@RequestMapping("/theme/{id}")
	public String theme(Model model, @PathVariable long id) {
		Optional<Theme> theme = repository.findById(id);
		System.out.println(theme.toString());
		Collection<Book> books= repositoryB.findByTheme_id(id);
		for(Book b:books) {
			System.out.println(b.toString());
		}
		if (theme!=null) {
			model.addAttribute("theme", theme.get());
		}
		return "themePage";
	}
	
	@RequestMapping("/newTheme")
	public String newTheme(Model model) {
		return "themePageEdit";
	}
	
	public Collection<Theme> themes(){
		return repository.findAll();
	}
	
	
}
