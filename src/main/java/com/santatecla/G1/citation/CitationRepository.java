package com.santatecla.G1.citation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CitationRepository extends JpaRepository<Citation,Long>{
	
	List<Citation> findByText(String text);

}
