package com.santatecla.G1.theme;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.book.Book;

@Entity
public class Theme {
	public interface NameView {}
	public interface BasicView extends NameView, IdView {}
	interface IdView {}
	interface BooksView {}

	
	@JsonView(IdView.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonView(NameView.class)
	private String name;
	
	/********************************************
	 * RELATIONS WITH OTHER CLASES TO DDBB MODEL
	 ********************************************/
	
	//To avoid cicles on DB model
	
	@OneToMany(cascade=CascadeType.ALL)
	@JsonView(BooksView.class)
	private List<Book> books;
	
	
	/********************************************
	 * METHODS OF THE CLASS
	 ********************************************/
	
	public Theme() {this.books = new ArrayList<Book>();}
	
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

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void update(Theme t) {
		this.name=t.name;
		this.books=t.books;
	}
	
}
