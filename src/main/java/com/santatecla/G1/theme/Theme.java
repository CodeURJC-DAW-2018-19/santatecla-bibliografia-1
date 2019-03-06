package com.santatecla.G1.theme;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@OneToMany(cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Book> books;
	
	
	/********************************************
	 * METHODS OF THE CLASS
	 ********************************************/
	
	public Theme() {}
	
	public Theme(String name) {
		this.setName(name);
		this.books = new ArrayList<Book>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addBook(Book book) {
		this.books.add(book);
	}
	public List<Book> getBook(){
		return this.books;
	}
	
	@Override
	public String toString() {
		return "Nombe: "+name ;
	}
	
}
