package com.santatecla.G1.book;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.author.Author;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.theme.Theme;

@Entity
public class Book {
	public interface BasicView{}
	interface ThemeView{}
	interface AuthorView{}
	interface CitationsView{}
	
	@JsonView(BasicView.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonView(BasicView.class)
	private String title;
	
	@JsonView(BasicView.class)
	private String publishDate;
	
	@JsonView(BasicView.class)
	private String nameEdit;
	
	@JsonView(BasicView.class)
	private String urlEdit;
	
	@JsonView(BasicView.class)
	private String urlImgCoverPage;
	
	@JsonView(BasicView.class)
	private String urlImgEdit;
	
	@JsonView(BasicView.class)
	private int imgId;
	
	/********************************************
	 * RELATIONS WITH OTHER CLASES TO DDBB MODEL
	 ********************************************/
	
	@OneToOne(cascade=CascadeType.ALL)
	@JsonView(ThemeView.class)
	private Theme theme;
	
	//To avoid cicles on DB model	
	@OneToMany (cascade=CascadeType.ALL)
	@JsonView(CitationsView.class)
	public List<Citation> citation;
	

	@OneToOne()
	@JsonView(AuthorView.class)
	private Author author;
	
	/********************************************
	 * METHODS OF THE CLASS
	 ********************************************/	
	
	//Constructor to Spring
	public Book() {}

	public Book(String name, int imgId) {
		super();
		this.title = name;
		this.nameEdit = "";
		this.citation = new ArrayList<Citation>();
		this.urlImgCoverPage="";
		this.urlEdit="";
		this.urlImgEdit="";
		this.theme = null;
		this.publishDate="";
		this.imgId = imgId;
	}

	public void update(Book newBook) {
		this.title=newBook.title;
		this.nameEdit=newBook.nameEdit;
		this.publishDate=newBook.publishDate;
		this.urlEdit=newBook.urlEdit;
		this.urlImgCoverPage=newBook.urlImgCoverPage;
		this.urlImgEdit=newBook.urlImgEdit;
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

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
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

	public void addCitations(Citation citation) {
		this.citation.add(citation);
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public int getImgId() {
		return imgId;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setCitation(List<Citation> citation) {
		this.citation = citation;
	}

	@Override
	public String toString() {
		return "Title: "+ this.title + "(" + this.nameEdit + ")";
	} 
	
	
}
