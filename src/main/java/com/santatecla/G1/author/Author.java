package com.santatecla.G1.author;

import java.text.SimpleDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String urlImage;
	private SimpleDateFormat bornDate;
	private SimpleDateFormat deathDate;
	private String bornPlace;
	private String urlMap;
	
	//Constructor to Spring
	public Author() {}

	public Author(String name) {
		super();
		this.name = name;
	//	this.bornDate = bornDate;
	//	this.deathDate = deathDate;
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

	public SimpleDateFormat getBornDate() {
		return bornDate;
	}

	public void setBornDate(SimpleDateFormat bornDate) {
		this.bornDate = bornDate;
	}

	public SimpleDateFormat getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(SimpleDateFormat deathDate) {
		this.deathDate = deathDate;
	}

	public void setUrlMap(String urlMap) {
		this.urlMap = urlMap;
	}
	
	@Override
	public String toString() {
		return "Nombe: "+name + "; Fecha de nacimiento" + bornDate + "; Fecha de defunción: "+deathDate;
	}
}
