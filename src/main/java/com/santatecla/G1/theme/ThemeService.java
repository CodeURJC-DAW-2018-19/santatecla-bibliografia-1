package com.santatecla.G1.theme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {

	@Autowired
	private ThemeRepository themeRepository;
	
	public Theme findById(long id) {
		return themeRepository.findById(id);
	}
	
	public void save(Theme theme) {
		themeRepository.save(theme);
	}
	
	public List<Theme> findAll(){
		return themeRepository.findAll();
	}
	
	public void deleteById(long id) {
		themeRepository.deleteById(id);
	}
	public Page<Theme> findAll(Pageable page){
		return themeRepository.findAll(page);
	}
}
