package com.santatecla.G1.citation;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.theme.Theme;


@Entity
public class Citation {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(length=1000)
	private String text;
	private String textAux;
	
	/********************************************
	 * RELATIONS WITH OTHER CLASES TO DDBB MODEL
	 ********************************************/
	
	//To avoid cicles on DB model
	
	@OneToOne()
	@JsonIgnore
	private Book book;
	@OneToOne
	@JsonIgnore
	private Theme theme;
	
	

	/********************************************
	 * METHODS OF THE CLASS
	 ********************************************/
	
	//Constructor to Spring
	public Citation() {}
	

	public Citation(String text , Book book) {
		super();
		this.text = text;
		this.book = book;
		this.theme = book.getTheme();
	}
	
	public Citation(String text, String book) {
		super();
		this.text = text;
		this.textAux = book;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	
	public String getTextAux() {
		return textAux;
	}


	public void setTextAux(String textAux) {
		this.textAux = textAux;
	}


	@Override
	public String toString() {
		return "Cita: "+this.text;
	}
	
	
}

