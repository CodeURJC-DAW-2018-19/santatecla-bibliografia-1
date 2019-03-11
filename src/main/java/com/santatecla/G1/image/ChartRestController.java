package com.santatecla.G1.image;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.TabController;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeService;
import com.santatecla.G1.user.UserComponent;

@RestController
@RequestMapping("/api")
public class ChartRestController {
	interface ChartBasicView extends Chart.BasicView, Theme.NameView{}
	
	@Autowired
	private ThemeService themeService;
	@Autowired
	private BookService bookService;

	
	@JsonView(ChartBasicView.class)
	@RequestMapping(value = "/chart", method = GET)
	public ResponseEntity<Chart> chart() {
		Chart c= new Chart();
		List<Integer> numBs= new ArrayList<>();
		c.setThemes(themeService.findAll());
		for(Theme theme: c.getThemes()) {
			List<Book> books=bookService.findByTheme(theme);
			numBs.add(books.size());
		}
		c.setNumBooks(numBs);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
}
