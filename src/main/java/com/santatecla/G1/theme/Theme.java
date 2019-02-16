package com.santatecla.G1.theme;

import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.book.Book;

@Entity
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	
	/********************************************
	 * RELATIONS WITH OTHER CLASES TO DDBB MODEL
	 ********************************************/
	
	//To avoid cicles on DB model
	@JsonView(Book.class)
	@OneToMany
	private Collection<Book> books;
	
	
	/********************************************
	 * METHODS OF THE CLASS
	 ********************************************/
	
	public Theme() {}
	
	public Theme(String name) {
		this.setName(name);
		//this.books = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Nombe: "+name ;
	}
	
}
