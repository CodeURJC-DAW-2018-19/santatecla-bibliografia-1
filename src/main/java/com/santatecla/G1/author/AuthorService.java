package com.santatecla.G1.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	public Author findByName(String name) {
		return authorRepository.findByName(name);
	}
	
	public Author findByNameIgnoreCase(String name) {
		return authorRepository.findByNameIgnoreCase(name);
	}
	
	public Author findById(long id) {
		return authorRepository.findById(id);
	}
	
	public List<Author> findAll(){
		return authorRepository.findAll();
	}
	
	public void save(Author author) {
		authorRepository.save(author);
	}
	
	public void deleteById(long id) {
		authorRepository.deleteById(id);
	}
	
	public Page<Author> findAll(Pageable page){
		return authorRepository.findAll(page);
	}
	
}
