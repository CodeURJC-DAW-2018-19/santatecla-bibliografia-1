package com.santatecla.G1.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.santatecla.G1.author.Author;
import com.santatecla.G1.theme.Theme;

public interface BookRepository extends JpaRepository<Book,Long>{

	Book findByTitle(String title);
	Book findByTitleIgnoreCase(String title);
	
	Book findById(long id);
	List <Book> findByTheme(Theme theme);
	List<Book> findByAuthor(Author writer);
	
	@Query(value="SELECT * FROM Book WHERE theme_id = ?1 ",nativeQuery = true)
	List<Book> findByTheme_id(long id);
	List<Book> findByTitleContaining(String title);
}
