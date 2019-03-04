package com.santatecla.G1.citation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void deleteById(long id) {
		citationRepository.deleteById(id);
	}
}
