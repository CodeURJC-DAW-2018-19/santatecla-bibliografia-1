package com.santatecla.G1.theme;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThemeController {

	@RequestMapping("/theme")
	public String author(Model model) {
		
		return "themePage";
	}
	
}
