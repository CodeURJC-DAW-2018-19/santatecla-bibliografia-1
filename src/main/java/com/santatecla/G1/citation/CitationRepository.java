package com.santatecla.G1.citation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santatecla.G1.book.Book;


public interface CitationRepository extends JpaRepository<Citation,Long>{
	
	List<Citation> findByText(String text);
	List<Citation> findCitationByBook(Book book);
	Citation findById(long id);

}
