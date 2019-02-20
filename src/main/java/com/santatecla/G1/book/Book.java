package com.santatecla.G1.book;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.theme.Theme;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private SimpleDateFormat publishDate;
	private String nameEdit;
	private String urlEdit;
	private String urlImgCoverPage;
	private String urlImgEdit;
	
	
	/********************************************
	 * RELATIONS WITH OTHER CLASES TO DDBB MODEL
	 ********************************************/
	
	@OneToOne(cascade=CascadeType.ALL)
	private Theme theme;
	
	//To avoid cicles on DB model	
	@OneToMany (cascade=CascadeType.ALL)
	public List<Citation> citation;
	

	@OneToOne()
	private Author author;
	
	/********************************************
	 * METHODS OF THE CLASS
	 ********************************************/	
	
	//Constructor to Spring
	public Book() {}

	public Book(String name, String nameEdit) {
		super();
		this.title = name;
		this.nameEdit = nameEdit;
		this.citation = new ArrayList<Citation>();
		this.theme = null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrlImgCoverPage() {
		return urlImgCoverPage;
	}

	public void setUrlImgCoverPage(String urlImgCoverPage) {
		this.urlImgCoverPage = urlImgCoverPage;
	}

	public String getUrlImgEdit() {
		return urlImgEdit;
	}

	public void setUrlImgEdit(String urlImgEdit) {
		this.urlImgEdit = urlImgEdit;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
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
	
	
	public List<Citation> getCitations() {
		return (List<Citation>) this.citation;
	}

	public void addCitations(Citation citations) {
		this.citation.add(citations);
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	@Override
	public String toString() {
		return "Title: "+ this.title + "(" + this.nameEdit + ")";
	} 
	
	
}
