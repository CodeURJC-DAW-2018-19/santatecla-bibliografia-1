package com.santatecla.G1.theme;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santatecla.G1.author.Author;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.citation.Citation;

public interface ThemeRepository extends JpaRepository<Theme,Long>{
	
	Theme findById(long id);
	List<Theme> findThemesByName(String text);
	List<Citation> findCitationByName(String theme);
	Book findBookById(long id);

}
