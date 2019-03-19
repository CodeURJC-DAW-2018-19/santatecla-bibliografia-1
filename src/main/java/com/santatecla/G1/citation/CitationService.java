package com.santatecla.G1.citation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CitationService {
	@Autowired
	private CitationRepository citationRepository;

	
	public List<Citation> findAll(){
		return citationRepository.findAll();
	}
	
	public void save (Citation citation) {
		citationRepository.save(citation);
	}
	public Citation findById(long id) {
		return citationRepository.findById(id);
	}
	
	public List<Citation> findByTextContaining(String text) {
		return citationRepository.findByTextContaining(text);
	}
	
	public List<Citation> findByTextContaining(String text, Pageable page) {
		return citationRepository.findByTextContaining(text, page);
	}
	
	public void deleteById(long id) {
		citationRepository.deleteById(id);
	}
	
	public Page<Citation> findAll(Pageable page){
		return citationRepository.findAll(page);
	}
	
	public Citation findByTextIgnoreCase(String text) {
		return citationRepository.findByTextIgnoreCase(text);
	}

}
