package com.santatecla.G1.author;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class AuthorController {
	
	@Autowired
	private AuthorRepository repository;
	
	public Collection<Author> authors(){
		return repository.findAll();
	}
	
	@RequestMapping("/author/{id}")
	public String Author(Model model, @PathVariable long id) {
		Optional<Author> author = repository.findById(id);
		System.out.println(author.toString());
		if (author!=null) {
			model.addAttribute("author", author.get());
		}
		return "authorPage";
	}
	
	/*@RequestMapping("/author/{id}")
	public String updateAuthor(Model model, @PathVariable long id) {
		Author author = repository.findOne(id);
		if (author!=null) {
			model.addAttribute("author", author);
		}
		return "authorPage";
	}*/
	
	@RequestMapping("/author")
	public String author(Model model) {
		//repository.save(author);
		return "authorPage";
	}
	
	@RequestMapping("/newAuthor")
	public String newAuthor(Model model) {
		//repository.save(author);
		return "authorPageEdit";
	}
	
	/*@RequestMapping("/author/{id}")
	public String deleteAuthor(Model model, @PathVariable long id) {
		Author author = repository.findOne(id);
		if (author!=null) {
			model.addAttribute("author", author);
		}
		repository.delete(id);
		
		return "deletePage";
	}*/
	
	/*@RequestMapping("/newAuthor/uploaded")
	public String newAuthorUploaded(Model model,@RequestParam("nameAuthor") String name,@RequestParam("birthDate") String bornDate,@RequestParam("defuncDate") String deathDate, @RequestParam("bornPlace") String bornPlace, @RequestParam("urlMap") String urlMap, @RequestParam("file") MultipartFile file) {
		int imageId = com.santatecla.G1.ImageManagerController.getNewId();
		com.santatecla.G1.ImageManagerController.handleFileUpload(model, file, imageId);
		Author a = new Author(name, bornDate, deathDate, imageId);
		repository.save(a);
		model.addAttribute(a);
		return "/author/{id}";
	}*/
}
