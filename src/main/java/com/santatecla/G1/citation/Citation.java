package com.santatecla.G1.citation;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Citation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(length=1000)
	private String text;
	private String author;
	private String book;
	
	//Constructor to Spring
	public Citation() {}
	
	/*public Citation(String text, Author author , Book book) {
		//super();
		this.text = text;
		this.author = author;
		this.book = book;
	}
	public Citation(String text, Author author ) {
		//super();
		this.text = text;
		this.author = author;
		this.book = null;
	}*/
	public Citation(String text) {
		//super();
		this.text = text;
		this.author = "";
		this.book = "";
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
	/*public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}*/
	
	@Override
	public String toString() {
		return "Cita: "+this.text;
	}
	
	
}

