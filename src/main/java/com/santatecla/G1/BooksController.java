package com.santatecla.G1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BooksController {

	@RequestMapping("/books")
	public String author(Model model) {
		
		return "booksPage";
	}
}
