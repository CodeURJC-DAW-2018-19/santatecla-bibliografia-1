package com.santatecla.G1.citation;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CitationController {
	
	@Autowired
	private CitationRepository repository;
	
	public Collection<Citation> citations(){
		return repository.findAll();
	}
}
