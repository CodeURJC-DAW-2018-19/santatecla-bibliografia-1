package com.santatecla.G1.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Long>{

	List<Book> findByTitle(String title);
	
	@Query(value="SELECT * FROM Book WHERE theme_id = ?1 ",nativeQuery = true)
	List<Book> findByTheme_id(long id);
}
