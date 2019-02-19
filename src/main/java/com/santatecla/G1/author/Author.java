package com.santatecla.G1.author;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.santatecla.G1.book.Book;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.theme.Theme;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String urlImage;
	private Date bornDate;
	private Date deathDate;
	private String bornPlace;
	private String urlMap;
	
	/********************************************
	* RELATIONS WITH OTHER CLASES TO DDBB MODEL
	********************************************/
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Book> books;
	
	/********************************************
	 * METHODS OF THE CLASS
	 ********************************************/
	
	//Constructor to Spring
	public Author() {}

	//The type of the dates is Date, when we operate with date, to show we will use SimpleFormatDate
	public Author(String name, Date bornDate, Date deathDate) {
		super();
		this.name = name;
		this.bornDate = bornDate;
		this.deathDate = deathDate;
		this.books = new ArrayList<>();
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}


	public String getBornPlace() {
		return bornPlace;
	}

	public void setBornPlace(String bornPlace) {
		this.bornPlace = bornPlace;
	}

	public String getUrlMap() {
		return urlMap;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public void setUrlMap(String urlMap) {
		this.urlMap = urlMap;
	}
	
	public void addBook(Book book) {
		this.books.add(book);
	}
	public List<Book> getBooks(){
		return this.books;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return "Nombe: "+name + "; Fecha de nacimiento: " + formatter.format(bornDate) + "; Fecha de defunción: "+formatter.format(deathDate);
	}
}
