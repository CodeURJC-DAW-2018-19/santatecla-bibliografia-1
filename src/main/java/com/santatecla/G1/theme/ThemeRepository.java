package com.santatecla.G1.theme;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme,Long>{
	
	List<Theme> findThemesByName(String text);
	

}
