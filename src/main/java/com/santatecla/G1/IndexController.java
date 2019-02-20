package com.santatecla.G1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.author.AuthorRepository;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.theme.ThemeRepository;

@Controller
public class IndexController {
	
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository booksRepository;
	@Autowired
	private ThemeRepository themesRepository;
	
	@RequestMapping("/index")
	public String author(Model model) {
		
		model.addAttribute("authors",authorRepository.findAll());
		model.addAttribute("books",booksRepository.findAll());
		model.addAttribute("themes",themesRepository.findAll());
		
		return "indexPage";
	}

}
