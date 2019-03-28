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
	
	public List<Theme> findByNameContaining(String name) {
		return themeRepository.findByNameContaining(name);
	}

	public List<Theme> findByNameContaining(String name, Pageable page) {
		return themeRepository.findByNameContaining(name, page);
	}
	
	public Theme findByNameIgnoreCase(String name) {
		return themeRepository.findByNameIgnoreCase(name);
	}
	
	public void deleteById(long id) {
		themeRepository.deleteById(id);
	}
	public Page<Theme> findAll(Pageable page){
		return themeRepository.findAll(page);
	}
	public List<Theme> findThemesByName(String text){
		return 	themeRepository.findThemesByName(text);
	}
 
}
