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
	private String title;
	private SimpleDateFormat publishDate;
	private String nameEdit;
	private String urlEdit;
	private String urlImgCoverPage;
	private String urlImgEdit;

	
	//Constructor to Spring
	public Book() {}

	public Book(String name, String nameEdit) {
		super();
		this.title = name;
		this.nameEdit = nameEdit;
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
	@Override
	public String toString() {
		return "Title: "+ this.title + "(" + this.nameEdit + ")";
	} 
	
	
}
