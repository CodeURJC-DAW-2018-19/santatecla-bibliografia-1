package com.santatecla.G1.book;

import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String urlBook;
	private SimpleDateFormat publishDate;
	private String nameEdit;
	private String urlEdit;
	
	//Constructor to Spring
	public Book() {}

	public Book(String name, String nameEdit) {
		super();
		this.name = name;
		this.nameEdit = nameEdit;
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

	public String getUrlBook() {
		return urlBook;
	}

	public void setUrlBook(String urlBook) {
		this.urlBook = urlBook;
	}

	public SimpleDateFormat getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(SimpleDateFormat publishDate) {
		this.publishDate = publishDate;
	}

	public String getNameEdit() {
		return nameEdit;
	}

	public void setNameEdit(String nameEdit) {
		this.nameEdit = nameEdit;
	}

	public String getUrlEdit() {
		return urlEdit;
	}

	public void setUrlEdit(String urlEdit) {
		this.urlEdit = urlEdit;
	}
	
	
}
