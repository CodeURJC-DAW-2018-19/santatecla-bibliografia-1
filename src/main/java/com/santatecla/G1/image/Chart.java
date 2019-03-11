package com.santatecla.G1.image;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.Theme.BasicView;

public class Chart {
	public interface BasicView {}
	
	@JsonView(BasicView.class)
	public List<Theme> themes;
	@JsonView(BasicView.class)
	public List<Integer> numBooks;
	
	public Chart() {};
	
	public Chart(List<Theme> themes, List<Integer> numBooks) {
		super();
		this.themes = themes;
		this.numBooks = numBooks;
	}
	public List<Theme> getThemes() {
		return themes;
	}
	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}
	public List<Integer> getNumBooks() {
		return numBooks;
	}
	public void setNumBooks(List<Integer> numBooks) {
		this.numBooks = numBooks;
	}
	
	
}
