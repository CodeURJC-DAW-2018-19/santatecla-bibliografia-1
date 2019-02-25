package com.santatecla.G1.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public Book findOne(long id) {
		return repository.findById(id);
	}
	
	public List<Book> findAll() {
		return repository.findAll();
	}

	public void save(Book book) {
		repository.save(book);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}
}

