package com.santatecla.G1.author;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santatecla.G1.book.Book;


@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	
	private String urlImage;
	private String birthDate;
	private String deathDate;
	private String bornPlace;
	@Column(length=500)
	private String urlMap;
	private int imgId;
	
	/********************************************
	* RELATIONS WITH OTHER CLASES TO DDBB MODEL
	********************************************/
	
	@OneToMany
	@JsonIgnore
	private List<Book> books;
	
	/********************************************
	 * METHODS OF THE CLASS
	 ********************************************/
	
	//Constructor to Spring
	public Author() {}

	//The type of the dates is Date, when we operate with date, to show we will use SimpleFormatDate
	public Author(String name, String bornDate, String deathDate, int imgId) {
		super();
		this.name = name;
		this.urlImage= "";
		this.urlMap="";
		this.bornPlace="";
		this.birthDate = bornDate;
		this.deathDate = deathDate;
		this.books = new ArrayList<>();
		this.imgId = imgId;
	}
	public Author(String name, String bornDate, int imgId) {
		super();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		this.name = name;
		this.urlImage= "";
		this.urlMap="";
		this.bornPlace="";
		this.birthDate = bornDate;
		this.deathDate = "";
		this.books = new ArrayList<>();
		this.imgId = imgId;
	}
	public Author(String name, String bornDate) {
		super();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		this.name = name;
		this.urlImage= "";
		this.urlMap="";
		this.bornPlace="";
		this.birthDate = bornDate;
		this.deathDate = "";
		this.books = new ArrayList<>();
		this.imgId = imgId;
	}

	public Author(String name, String urlImage, String bornDate, String deathDate, String bornPlace,
			String urlMap, int imgId) {
		super();
		this.name = name;
		this.urlImage = urlImage;
		this.birthDate = bornDate;
		this.deathDate = deathDate;
		this.bornPlace = bornPlace;
		this.urlMap = urlMap;
		this.imgId = imgId;
		this.books = new ArrayList<>();

	}
	
	public void update(Author oldAuthor) {
		this.name=oldAuthor.name;
		this.birthDate=oldAuthor.birthDate;
		this.deathDate=oldAuthor.deathDate;
		this.bornPlace=oldAuthor.bornPlace;
		this.urlImage=oldAuthor.urlImage;
		this.urlMap=oldAuthor.urlMap;
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

	public String getBornDate() {
		return birthDate;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setDeathDate(String deathDate) {
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
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return "Nombe: "+name + "; Fecha de nacimiento: " +birthDate + "; Fecha de defunción: "+ deathDate;
	}
}
