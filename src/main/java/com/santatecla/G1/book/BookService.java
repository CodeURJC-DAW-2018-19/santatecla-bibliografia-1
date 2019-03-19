package com.santatecla.G1.book;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.santatecla.G1.author.Author;
import com.santatecla.G1.theme.Theme;

@Service
public class BookService {

	@Autowired
	private BookRepository booksRepository;

	public Book findOne(long id) {
		return booksRepository.findById(id);
	}
	
	public List<Book> findAll() {
		return booksRepository.findAll();
	}
	
	public List<Book> findByTitleContaining(String title) {
		return booksRepository.findByTitleContaining(title);
	}
	
	public List<Book> findByTitleContaining(String title, Pageable page) {
		return booksRepository.findByTitleContaining(title, page);
	}
	
	public void save(Book book) {
		booksRepository.save(book);
	}

	public void deleteById(long id) {
		booksRepository.deleteById(id);
	}
	
	public Book findByTitleIgnoreCase(String title) {
		return booksRepository.findByTitleIgnoreCase(title);
	}
	
	public List<Book> findByAuthor(Author author) {
		return booksRepository.findByAuthor(author);
	}
	
	public Book findByTitle(String title) {
		return booksRepository.findByTitle(title);
	}
	
	public Book findById(long id) {
		return booksRepository.findById(id);
	}
	
	public List<Book> findByTheme(Theme theme){
		return booksRepository.findByTheme(theme);
	}
	public Page<Book> findAll(Pageable page){
		return booksRepository.findAll(page);
	}
}

