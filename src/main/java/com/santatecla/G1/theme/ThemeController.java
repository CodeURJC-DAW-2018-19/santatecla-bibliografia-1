package com.santatecla.G1.theme;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ThemeController {
	
	@Autowired
	private ThemeRepository repository;

	
	
	@RequestMapping("/theme")
	public String author(Model model) {
		
		return "themePage";
	}
	
	public Collection<Theme> themes(){
		return repository.findAll();
	}
	
	
}
